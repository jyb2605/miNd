package com.mind.user;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {
    public static String SERVER_IP = "112.149.54.212";
    public static int SERVER_PORT = 19999;
    static Socket socket;
    static DataInputStream input;
    static DataOutputStream output;
    static Thread tcp;
    static Handler handler = new Handler();
    static int i;

    SocketUtil() {

        if (socket == null) {
            tcp = new Thread(new Runnable() {
                public void run() {
                    connect();
                }
            }
            );
            tcp.start();

        }
    }


    public static void connect() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            while (socket != null) {
                if (socket.isConnected()) {
                    output.writeUTF("p`1`1`연결`");
                    //고객이 택시호출
                    output.flush();
                    break;
                }
            }
            MessageReciver messageReceiver = new MessageReciver();
            messageReceiver.start();
        } catch (Exception e) {
            System.out.println("서버에 접속할 수 없습니다.");
//            e.printStackTrace();
        }
    }

    public static class MessageReciver extends Thread {
        public void run() {
            try {
                String received;
                while ((received = input.readUTF()) != null) {
                    final String[] buffer = received.split("`");
//                    Log.e("TCP", buffer[0]);
                    switch (buffer[0].charAt(0)) {
                        case 'n':
//                            chatMessage = buffer[1]; //입장
//                            System.out.println(buffer[1]);
                            break;
                        case 'c':
//                            trash_can_num = buffer[1];
                            Log.e("TCP", buffer[1]);
                            if (LoadingActivity.loading_activity_running) {
                                if (buffer[1].trim().equals("2")) {
                                    LoadingActivity.activity.startActivity(new Intent(LoadingActivity.activity, SuccessActivity.class));
                                    LoadingActivity.activity.finish();
                                }
                            }
                            else if (SuccessActivity.isRunning) {
                                if (buffer[1].trim().equals("3")) {
                                    if (SuccessActivity.exit_dialog != null) {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                SuccessActivity.exit_dialog.show();
                                            }
                                        });

                                        for (i = 5; i > 0; i--) {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    SuccessActivity.exit_dialog.second.setText(i + "초 이후");
                                                }
                                            });
                                        }
                                        SuccessActivity.exit_dialog.dismiss();
                                        SuccessActivity.activity.finish();
                                    }
                                }
                            }
                            break;
                        case 'x':
//                            chatMessage = buffer[1]; //퇴장
                            break;
                        default:
                            break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
