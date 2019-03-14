package com.apparchar.apparchar.Vista;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.apparchar.apparchar.Presentador.CreacionEventoPresenter;
import com.apparchar.apparchar.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CreacionEvento extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button horaI;
    Button bhoraF;
    Button guardar;
    boolean time;
    int dia, mes, year, hora, minuto;
    int diaF, mesF, yearF, horaI2, minutoI2;
    int horaF, minutoF;
    int horaF2, minutoF2;
    EditText idEvento, direccion, descripcion, horaInicio, horaFinal, fecha,nombre;
    String timeI,timeF,date;
    ArrayList a,cat,categoriasCheck;
    LinearLayout categorias;
    CreacionEventoPresenter presentador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_creacion_evento);
        Toast.makeText(this,getIntent().getExtras().get("nit").toString(),Toast.LENGTH_SHORT).show();
        horaI = (Button) findViewById(R.id.horaI);
        bhoraF = (Button) findViewById(R.id.horaF);
        guardar = (Button) findViewById(R.id.guardar);
        horaInicio = (EditText) findViewById(R.id.horaInicio);
        horaFinal = (EditText) findViewById(R.id.horaFinal);
        idEvento = (EditText) findViewById(R.id.idEvento);
        direccion = (EditText) findViewById(R.id.direccion);
        descripcion = (EditText) findViewById(R.id.descripcion);
        fecha = (EditText) findViewById(R.id.fechaEvento);
        nombre=(EditText) findViewById(R.id.nombre);
        categorias=(LinearLayout) findViewById(R.id.categoriasCheck);


        presentador=new CreacionEventoPresenter(this);
        a = new ArrayList();
        cat= new ArrayList();
        categoriasCheck=new ArrayList();
        cat.add("Trago");
        cat.add("Rumba");
        cat.add("comedia");
        cat.add("comida");
        cat.add("lectura");
        cat.add("musica");
        cat.add("deporte");
        for (int i=0;i<cat.size();i++){
            CheckBox checkBox=new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(String.valueOf(cat.get(i)));
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check= ((CheckBox) v).isChecked();
                    if (check){
                        categoriasCheck.add(cat.get(v.getId()));
                    }
                }
            });
            categorias.addView(checkBox);
        }




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idEventos = Integer.parseInt(idEvento.getText().toString());
                String direccions = direccion.getText().toString();
                String descripcions = descripcion.getText().toString();
                String nombres= nombre.getText().toString();
                presentador.CrearEvento(idEventos,nombres,timeI,timeF,direccions,descripcions,categoriasCheck,date);


                a.add(idEventos);
                a.add(nombres);
                a.add(date);
                a.add(timeI);
                a.add(timeF);
                a.add(direccions);
                a.add(descripcions);
                a.add(categoriasCheck);

                Intent i = new Intent(CreacionEvento.this, EventoCreado.class);
                i.putExtra("datos", a);
                startActivity(i);
            }
        });


    }

    public void horaI(View vista) {
        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(CreacionEvento.this, CreacionEvento.this, hora, minuto, true);
        timePickerDialog.show();
        time = true;

    }

    public void horaF(View vista) {


        Calendar c = Calendar.getInstance();
        horaF = c.get(Calendar.HOUR_OF_DAY);
        minutoF = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(CreacionEvento.this, CreacionEvento.this, horaF, minutoF, true);
        timePickerDialog.show();

    }

    public void Fecha(View vista) {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreacionEvento.this, CreacionEvento.this, year, mes, dia);
        datePickerDialog.show();

    }
    public void change(ArrayList lista){
        Intent intent=new Intent(this,CreacionEvento.class);
        intent.putExtra("lista",lista);
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
        fecha.setText(date);

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
        horaInicio.setText(timeI);
        horaFinal.setText(timeF);

    }


}

