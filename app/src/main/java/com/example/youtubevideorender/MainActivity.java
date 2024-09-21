package com.example.youtubevideorender;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String VIDEO_ID = "NCoekcDxbrI";
    private WebView webView;
    private FrameLayout customViewContainer;
    private View customView;
    private WebChromeClient.CustomViewCallback customViewCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyCustomView());
        webView.setWebViewClient(new WebViewClient());

              // Load the YouTube video in an iframe
        String iframe = "<iframe width=\"100%\" height=\"50%\" " +
                "src=\"https://www.youtube.com/embed/" + VIDEO_ID + "\" " +
                "frameborder=\"0\" allow=\"autoplay; fullscreen\" " +
                "allowfullscreen></iframe>";
        webView.loadData(iframe, "text/html", "utf-8");
    }
    private class MyCustomView extends WebChromeClient
    {
        View fullscreen = null;

        @Override
        public void onHideCustomView()
        {
            fullscreen.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback)
        {
            webView.setVisibility(View.GONE);

            if(fullscreen != null)
            {
                ((FrameLayout)getWindow().getDecorView()).removeView(fullscreen);
            }

            fullscreen = view;
            ((FrameLayout)getWindow().getDecorView()).addView(fullscreen, new FrameLayout.LayoutParams(-1, -1));
            fullscreen.setVisibility(View.VISIBLE);
        }
    }

}
