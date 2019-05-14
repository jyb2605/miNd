package com.mind.user;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.TextView;




public class ExitDialog extends Dialog {
    Context con;
    static TextView second;
    Handler handler;
    Activity activity;

    public ExitDialog(@NonNull Context context, Activity activity) {
        super(context);
        con=context;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);
        second = (TextView) findViewById(R.id.second);


//        setCustomActionbar();


    }
}
