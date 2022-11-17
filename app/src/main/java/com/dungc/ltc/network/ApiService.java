package com.dungc.ltc.network;

import com.dungc.ltc.model.Notify;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("lseid/notification.php")
    Call<List<Notify>> getNotifications();
}
