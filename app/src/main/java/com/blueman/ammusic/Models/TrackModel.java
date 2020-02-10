
package com.blueman.ammusic.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackModel implements Serializable
{

    @SerializedName("message")
    @Expose
    private Message message;
    private final static long serialVersionUID = -1209702393287294029L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrackModel() {
    }

    /**
     * 
     * @param message
     */
    public TrackModel(Message message) {
        super();
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
