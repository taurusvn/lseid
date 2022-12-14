package com.dungc.ltc.ui;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dungc.ltc.R;
import com.dungc.ltc.databinding.FragmentHomeBinding;
import com.dungc.ltc.databinding.FragmentMallBinding;
import com.dungc.ltc.databinding.FragmentProfileBinding;
import com.dungc.ltc.utils.Utils;

public class MallFragment extends Fragment {
    private FragmentMallBinding binding;

    public MallFragment() {
        super(R.layout.fragment_mall);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMallBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.webView.interfereWebViewSetup(this::initWebView);
        binding.webView.startLoading("http://103.169.35.186/lseid/mall.php");
    }

    @SuppressLint("SetJavaScriptEnabled")
    void initWebView(WebView mWebView) {
        Utils.clearCacheWebView(mWebView);
        // For API level below 18 (This method was deprecated in API level 18)
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                return true;
            }
        });

        mWebView.addJavascriptInterface(new ProfileFragment.JSBridge(), "Android");
    }

    static class JSBridge {
        @JavascriptInterface
        public void showMessageInNative(String message) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
