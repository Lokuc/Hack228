package com.severgames.hack228;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public static boolean isServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        if(v.getId()==R.id.button4){
            isServer=false;
            Intent intent = new Intent(MainActivity.this, ConnectTo.class);
            startActivity(intent);
        }else if (v.getId()==R.id.button3){
            isServer=true;
            Intent intent = new Intent(MainActivity.this, CreateServ.class);
            startActivity(intent);
        }
    }


}