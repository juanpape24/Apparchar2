package com.apparchar.apparchar;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
//import com.example.loginregisterapp.Conexion.MyLoopjTask;
//import com.example.loginregisterapp.Conexion.OnLoopjCompleted;
import com.loopj.android.http.*;

/*
public class MainActivity extends AppCompatActivity implements OnLoopjCompleted,View.OnClickListener {
    EditText username, password;
    Button submit;
    String u, p;
    RequestParams params;
    String MYURL = "http://192.168.137.1:8080/App/SERVRegister";
String resultado="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit_btn);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        u = username.getText().toString();
        p = password.getText().toString();
        params = new RequestParams();
        params.put("k1", u);
        params.put("k2", p);
        String nameServlet = "SERVRegister";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet,this,this);
        loopjTask.executeLoopjCall();
        //     Toast.makeText(MainActivity.this, "submit sucess " + resultado, Toast.LENGTH_SHORT).show();

        Log.i("info", "onSuccess despues: " + resultado);
        Log.i("info", "termino: " + resultado);

    }
    @Override
    public void taskCompleted(String results) {
        resultado=results;
        Toast.makeText(MainActivity.this, "submit sucess " + resultado, Toast.LENGTH_SHORT).show();
    }
}*/
