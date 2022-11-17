package com.dungc.ltc.utils;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;

public class Utils {
    public static void clearCacheWebView(WebView mWebview) {
        WebStorage.getInstance().deleteAllData();
        // Clear all the cookies
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
        mWebview.clearCache(true);
        mWebview.clearFormData();
        mWebview.clearHistory();
        mWebview.clearSslPreferences();
    }
}
