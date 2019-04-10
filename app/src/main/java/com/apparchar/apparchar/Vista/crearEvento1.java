package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.R;

import java.util.ArrayList;

public class crearEvento1 extends AppCompatActivity {

    //Widgets
    Button b1;
    TextView t1 ;
    EditText e1,e2;

    //Informacion obtenida
    ArrayList<String> info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearevento_1);
        b1 = (Button) findViewById(R.id.next1);
        t1=(TextView)findViewById(R.id.t1);
        t1.setTypeface(Typeface.createFromAsset(crearEvento1.this.getAssets(),"Fonts/Abel_Regular.ttf"));
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        info=new ArrayList<>();

    }

    public void next1(View view) {
        if(Check1()) {
            info.add(e1.getText().toString());
            info.add(e2.getText().toString());
            Intent intent = new Intent(this, crearEvento2.class);
            intent.putStringArrayListExtra("info1",info);
            intent.putExtra("user",getIntent().getStringExtra("user"));
            intent.putExtra("nit",getIntent().getStringExtra("nit"));
            startActivity(intent);
        }
    }

    public boolean Check1(){
        if(!e1.getText().equals("")&&!e2.getText().equals("")){
          return true;
        }else{
            Toast.makeText(this,"Llena todos los campos",Toast.LENGTH_SHORT);
        }
    return false;
    }


}



