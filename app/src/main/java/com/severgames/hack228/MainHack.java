package com.severgames.hack228;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainHack extends AppCompatActivity {

    ServerSocket ss;
    Socket s = null;
    Scanner in;
    PrintWriter out;
    String tmp;
    Vibrator vibrator;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hack);
        System.out.println("hg");
        createOneShotVibrationUsingVibrationEffect(100);
        if(MainActivity.isServer){

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void run() {
                    try {
                        //создаю сервак
                        ss = new ServerSocket(8080);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        // бесконечный цикл
                        while(true){
                            s = ss.accept();
                            if(s!=null){
                                in = new Scanner(s.getInputStream());
                                out = new PrintWriter(s.getOutputStream());
                                createOneShotVibrationUsingVibrationEffect(150);
                                break;
                            }
                        }
                        while (true) {
                            // если есть входящее сообщение
                            if (in.hasNext()) {
                                // считываем его
                                String Massage = in.nextLine();
                                System.out.println(Massage);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }else{

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void run() {
                    try {
                        // подключаемся к серверу
                        s = new Socket(ConnectTo.ip,8080);
                        in = new Scanner(s.getInputStream());
                        out = new PrintWriter(s.getOutputStream());
                        createOneShotVibrationUsingVibrationEffect(150);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        // бесконечный цикл
                        while (true) {
                            // если есть входящее сообщение
                            if (in.hasNext()) {
                                // считываем его
                                String Massage = in.nextLine();
                                System.out.println(Massage);
                                if(Massage.equals("0")){
                                    createOneShotVibrationUsingVibrationEffect(100);
                                }else{
                                    createOneShotVibrationUsingVibrationEffect(200);
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createOneShotVibrationUsingVibrationEffect(int lon) {
        // 1000 : Vibrate for 1 sec
        // VibrationEffect.DEFAULT_AMPLITUDE - would perform vibration at full strength
        System.out.println("wibr");
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            VibrationEffect effect = VibrationEffect.createOneShot(lon, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);
        }

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //Обрабатываем нажатие кнопки увеличения громкости:
            case KeyEvent.KEYCODE_VOLUME_UP:
                sendMsg("1");
                return false;
            //Обрабатываем нажатие кнопки уменьшения громкости:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                sendMsg("0");
                return false;
        }
        return false;
    }


    public void sendMsg(String msg){
        tmp=msg;
        // отправляем сообщение
        new Thread(new Runnable() {
            @Override
            public void run() {
                out.println(tmp);
                out.flush();
            }
        }).start();
    }
}