package com.severgames.hack228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConnectTo extends AppCompatActivity {

    public static String ip = "192.168.0.104";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to);
    }

    public void onClick(View v){
        if(v.getId()==R.id.button){
            //ip=((EditText)findViewById(R.id.editTextTextPersonName2)).getText().toString();
            System.out.println(ip+"gh");
            Intent intent = new Intent(ConnectTo.this, MainHack.class);
            startActivity(intent);
            finish();
        }
    }

}