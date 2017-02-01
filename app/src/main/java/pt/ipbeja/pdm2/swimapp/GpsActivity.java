package pt.ipbeja.pdm2.swimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class GpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        Intent intentGps = getIntent();
        String gps = intentGps.getStringExtra("gps");
        WebView webGps = (WebView) findViewById(R.id.webViewGps);
        webGps.loadUrl(gps);
    }
}
