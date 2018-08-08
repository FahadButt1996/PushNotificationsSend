package com.ttl.pushnotificationsend;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by fahad.waqar on 06-Aug-18.
 */

public class PushNotificationSendModel {
    @SerializedName("to")
    String to;
    @SerializedName("data")
    JSONArray data;
    @SerializedName("notification")
    JSONArray notification;
}
