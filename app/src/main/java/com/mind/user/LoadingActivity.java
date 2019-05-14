package com.mind.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LoadingActivity extends AppCompatActivity {
    public static String SERVER_IP = "52.79.189.195";
    public static int SERVER_PORT = 19999;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    static LoadingActivity activity;

    static boolean loading_activity_running = true;
    Gson gson = new Gson();
    AVLoadingIndicatorView avi;


    TextView start;
    TextView end;
    TextView start_address;
    TextView end_address;


    void startAnim() {
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi.hide();
        // or avi.smoothToHide();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        activity = this;

        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallPulseIndicator");
        loading_activity_running = true;
        startAnim();

        start = (TextView) findViewById(R.id.start);
        start_address = (TextView) findViewById(R.id.start_address);
        end = (TextView) findViewById(R.id.end);
        end_address = (TextView) findViewById(R.id.end_address);

        start.setText(MainActivity.start_point.name);
        start_address.setText(MainActivity.start_point.address);
        end.setText(MainActivity.end_point.name);
        end_address.setText(MainActivity.end_point.address);





        new Thread(new Runnable() {
            public void run() {
                if (SocketUtil.socket.isConnected()) {
                    try {
                        Point[] req = new Point[2];
                        req[0] = MainActivity.start_point;
                        req[1] =  MainActivity.end_point;
                        String json = gson.toJson(req);
                        SocketUtil.output.writeUTF("c`1/"+ json + "`");
                        SocketUtil.output.flush();
                        //002 : 기사가 승객의 콜 수락
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        ).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loading_activity_running = false;
    }
}
