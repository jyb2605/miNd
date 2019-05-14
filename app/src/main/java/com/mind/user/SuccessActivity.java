package com.mind.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;

public class SuccessActivity extends AppCompatActivity{
    AVLoadingIndicatorView avi;

    Handler handler;
    static ExitDialog exit_dialog;
    static Activity activity;

    static boolean isRunning= false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning= false;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        isRunning= true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_success);
        isRunning= true;
        activity=this;



        handler = new Handler();
        exit_dialog = new ExitDialog(this, this);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
