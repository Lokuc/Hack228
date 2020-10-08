package com.severgames.hack228;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class MainHack extends AppCompatActivity {

    ServerSocket ss;
    Socket s = null;
    Scanner in;
    PrintWriter out;
    String tmp;
    Vibrator vibrator;
    TextView deEnc;
    long lastNum;
    long time;
    long tmpTime;
    int num;
    Date date;
    String prev;

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
                                deEncryption(Massage);
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
                                deEncryption(Massage);
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
        deEnc=findViewById(R.id.textView3);
        time=0;
        num=0;
        lastNum=0;
        prev="";//01101
        date=new Date();


    }

    private void deEncryption(String msg){
        date = new Date();
        tmpTime=date.getTime();
        if(time==0){
            time=date.getTime();
        }
        if((tmpTime-time) >1200L){       //0 1 2
            prev=prev+" "+lastNum;
            num=0;
            lastNum=0;
        }
        if(msg.equals("1")){
            if(num==0){
                lastNum=1;
            }else {
                lastNum += Math.pow(2L, num);
            }
        }
        num++;
        time=tmpTime;
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        deEnc.setText(prev+" "+lastNum);
                    }
                });
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createOneShotVibrationUsingVibrationEffect(int lon) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            VibrationEffect effect = VibrationEffect.createOneShot(lon, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);
        }

    }

    public void onClick(View v){
        prev="";
        deEnc.setText(lastNum+"");
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