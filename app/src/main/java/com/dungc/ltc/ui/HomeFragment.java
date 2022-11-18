package com.dungc.ltc.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.dungc.ltc.R;
import com.dungc.ltc.databinding.FragmentHomeBinding;
import com.dungc.ltc.fcm.MyFirebaseMessagingService;
import com.dungc.ltc.utils.Utils;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getActivity()!= null) {
//            handleIntent(getActivity().getIntent());
//        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.ivNotify.setOnClickListener(view1 -> {
//            redirectNotification();
//        });
        binding.webView.interfereWebViewSetup(this::initWebView);
        binding.webView.startLoading("http://103.169.35.186/lseid/news.php");
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

    private void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Log.d("HomeFragment", "Redirect notify -->");
            String title = bundle.getString(MyFirebaseMessagingService.NOTIFICATION_KEY);
            redirectNotification();
        }
    }

    private void redirectNotification() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(
                R.id.action_home_to_notify,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(R.anim.anim_enter_from_right)
                        .setExitAnim(R.anim.anim_exit_to_left)
                        .setPopEnterAnim(R.anim.anim_enter_from_left)
                        .setPopExitAnim(R.anim.anim_exit_to_right)
                        .build()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
