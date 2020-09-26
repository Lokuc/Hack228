package com.severgames.hack228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class CreateServ extends AppCompatActivity {

    String a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_serv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                        NetworkInterface intf = (NetworkInterface) en.nextElement();
                        for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                String ipAddress = inetAddress.getHostAddress().toString();
                                Log.e("IP address", "" + ipAddress);
                                a = ipAddress;
                                TextView tv = findViewById(R.id.textView2);
                                tv.setText(a);
                            }
                        }
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
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