package com.blueman.ammusic.HttpRequest;

import com.blueman.ammusic.Utils.Constants;
import com.blueman.ammusic.Utils.TLSSocketFactory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusixmatchClient {
    private static Retrofit retrofit = null;
    public static MusixmatchApi getClient(){
        if (retrofit == null){
            OkHttpClient client=new OkHttpClient();
            try {
                TLSSocketFactory tlsSocketFactory=new TLSSocketFactory();
                if (tlsSocketFactory.getTrustManager()!=null) {
                    client = new OkHttpClient.Builder()
                            .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.getTrustManager())
                            .build();
                }
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(MusixmatchApi.class);
    }
}
