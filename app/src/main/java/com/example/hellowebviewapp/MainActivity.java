package com.example.hellowebviewapp;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
//import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
//    private String accessUrl = "https://akira-watson.com/";
//    private String accessUrl = "https://www.google.co.jp";
private String accessUrl = "https://rocky-stream-71417.herokuapp.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ボタンを定義
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);

        // WebViewボタンのクリックイベントを追加
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.web);
                webView = findViewById(R.id.web_view);

                // JavaScriptを有効化(JavaScript インジェクションに注意)
                webView.getSettings().setJavaScriptEnabled(true);

                // Web Storage を有効化
                webView.getSettings().setDomStorageEnabled(true);

                // Hardware Acceleration ON
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

                webView.loadUrl(accessUrl);
            }
        });

        // Browserボタンのクリックイベントを追加
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(accessUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 戻るページがある場合
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
            }
            else{
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode,  event);
    }

}
