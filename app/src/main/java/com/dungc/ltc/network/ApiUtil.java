package com.dungc.ltc.network;

public class ApiUtil {

    public static final String BASE_URL = "http://103.169.35.186/";

    public static ApiService getService() {
        return ApiClient.getClient(BASE_URL).create(ApiService.class);
    }
}
