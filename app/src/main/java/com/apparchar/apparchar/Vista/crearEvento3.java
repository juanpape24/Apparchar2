package com.apparchar.apparchar.Vista;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.apparchar.apparchar.R;

import java.util.ArrayList;
import java.util.Calendar;

public class crearEvento3 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
  //Widgets
    Button b3;
    TextView t3;
    EditText e4,e5,e6;


    //Variables Locales
    boolean time;
    int dia, mes, year, hora, minuto;
    int diaF, mesF, yearF, horaI2, minutoI2;
    int horaF, minutoF;
    int horaF2, minutoF2;
    String timeI, timeF, date;
    ArrayList<String> info;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearevento_3);
        b3 = (Button) findViewById(R.id.next3);
        t3=(TextView)findViewById(R.id.t3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        t3.setTypeface(Typeface.createFromAsset(crearEvento3.this.getAssets(),"Fonts/Abel_Regular.ttf"));
        info=getIntent().getStringArrayListExtra("info1");
    }


    /*
    Escuchador boton hora de inicio
     */

    public void Hora1(View view){
        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(crearEvento3.this, crearEvento3.this, hora, minuto, true);
        timePickerDialog.show();
        time = true;
    }


     /*
    Escuchador boton fecha
     */

    public void Fecha1(View view){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(crearEvento3.this, crearEvento3.this, year, mes, dia);
        datePickerDialog.show();

    }


     /*
    Escuchador boton hora de finalizacion
     */

    public void Hora2(View view){

        Calendar c = Calendar.getInstance();
        horaF = c.get(Calendar.HOUR_OF_DAY);
        minutoF= c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(crearEvento3.this, crearEvento3.this, horaF, minutoF, true);
        timePickerDialog.show();
    }

    public void next3(View view) {
        Intent intent = new Intent(this, crearEvento4.class);
        info.add(date);
        info.add(timeI);
        info.add(timeF);
        intent.putStringArrayListExtra("info1",info);
        intent.putStringArrayListExtra("categoriasCheck",getIntent().getStringArrayListExtra("categoriasCheck"));
        intent.putExtra("user",getIntent().getStringExtra("user"));
        intent.putExtra("nit",getIntent().getStringExtra("nit"));
        startActivity(intent);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearF = i;
        mesF = i1 + 1;
        diaF = i2;
        String month, day;

        if (mesF < 10) {
            month = 0 + String.valueOf(mesF);
        } else {
            month = String.valueOf(mesF);
        }
        if (diaF < 10) {
            day = 0 + String.valueOf(diaF);
        } else {
            day = String.valueOf(diaF);
        }

        date = day + "/" + month + "/" + yearF;
        e4.setText(date);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String hour, minute, hourF, minuteF;
        if (time) {
            horaI2 = i;
            minutoI2 = i1;
            time = false;
        } else {
            horaF2 = i;
            minutoF2 = i1;
        }

        if (horaI2 < 10) {
            hour = 0 + String.valueOf(horaI2);
        } else {
            hour = String.valueOf(horaI2);
        }
        if (minutoI2 < 10) {
            minute = 0 + String.valueOf(minutoI2);
        } else {
            minute = String.valueOf(minutoI2);
        }
        if (horaF2 < 10) {
            hourF = 0 + String.valueOf(horaF2);
        } else {
            hourF = String.valueOf(horaF2);
        }
        if (minutoF2 < 10) {
            minuteF = 0 + String.valueOf(minutoF2);
        } else {
            minuteF = String.valueOf(minutoF2);
        }
        timeI = hour + ":" + minute;
        timeF = hourF + ":" + minuteF;

        e5.setText(timeI);
        e6.setText(timeF);

    }
}