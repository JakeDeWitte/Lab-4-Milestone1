package com.example.lab4milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mockFileDownloader() {
        Button startButton = (Button) findViewById(R.id.startButton);
        TextView downloadProgressText = (TextView) findViewById(R.id.downloadProgress);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("Downloading...");
            }
        });

        for (int downloadProgress = 0; downloadProgress <= 100; downloadProgress = downloadProgress + 10) {
            if (flag) {
                Log.d(TAG, "Download Progress: " + downloadProgress + "%");

                int finalDownloadProgress = downloadProgress;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadProgressText.setText("Download Progress: " + finalDownloadProgress + "%");
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

            runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadProgressText.setText("");
                    }
                });
                flag = true;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startButton.setText("Start");
                }
            });
        }






    public void startDownload(View view) {
        ExampleRunnable runnable = new ExampleRunnable();
        new Thread(runnable).start();
    }

    public void endDownload(View view) {
        flag = false;
    }

    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            mockFileDownloader();
        }
    }

}