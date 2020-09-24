package com.severgames.hack228;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CreateServ extends AppCompatActivity {

    String a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_serv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    final DatagramSocket socket = new DatagramSocket();
                    socket.connect(InetAddress.getByName("0.0.0.0"), 10002);
                    a = socket.getLocalAddress().getHostAddress();
                } catch (SocketException | UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        ((TextView)findViewById(R.id.textView2)).setText(a);
        System.out.println(a+"bgfh");
    }

    public void onClick(View v){
        System.out.println("click");
        if(v.getId()==R.id.button2){
            Intent intent = new Intent(CreateServ.this, MainHack.class);
            System.out.println("go");
            startActivity(intent);
            finish();
        }
    }

}