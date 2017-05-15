package com.uninorte.carlos.track_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class filterActivity extends AppCompatActivity {
    final Calendar c = Calendar.getInstance();
    public Spinner diai,mesi,añoi,diaf,mesf,añof,horai,mini,horaf,minf;
    String diain;
    String mesin;
    String añoin;
    int diafi;
    String mesfi;
    String añofi;
    String horain,minin,horafi,minfi,key,key1,key2;
    int maxYear = c.get(Calendar.YEAR) - 20; // this year ( 2011 ) - 20 = 1991
    int maxMonth = c.get(Calendar.MONTH);
    int maxDay = c.get(Calendar.DAY_OF_MONTH);
    private DatabaseReference mDatabase;
    int minYear = 1960;
    int minMonth = 0; // january
    int minDay = 25;
    private DatePicker BirthDateDP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        key=getIntent().getStringExtra("name");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        diai = (Spinner) findViewById(R.id.diai);
        diaf = (Spinner) findViewById(R.id.diaf);
        mesi = (Spinner) findViewById(R.id.mesi);
        mesf = (Spinner) findViewById(R.id.mesf);
        añoi = (Spinner) findViewById(R.id.añoi);
        añof = (Spinner) findViewById(R.id.añof);
        horai = (Spinner) findViewById(R.id.horai);
        mini = (Spinner) findViewById(R.id.mini);
        horaf = (Spinner) findViewById(R.id.horaf);
        minf = (Spinner) findViewById(R.id.minf);
        List<String> dias = new ArrayList<String>();
        dias.add("Dia");
        for(int i=0;i<31;i++){
            if(i<9) {
                dias.add("0" + (i + 1));
            }else{
                dias.add("" + (i + 1));
            }
        }
        ArrayAdapter<String> dia1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dias);
        dia1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diai.setAdapter(dia1);
        diai.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diain = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                diain="";
            }
        });
        ArrayAdapter<String> dia2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dias);
        dia2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diaf.setAdapter(dia2);
        diaf.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diafi = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                diafi=0;
            }
        });
        List<String> meses = new ArrayList<String>();
        meses.add("Mes");
        for(int i=0;i<12;i++){
            if(i<9) {
                meses.add("0" + (i + 1));
            }else{
                meses.add("" + (i + 1));
            }
        }
        ArrayAdapter<String> mes1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses);
        mes1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mesi.setAdapter(mes1);
        mesi.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mesin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mesin="";
            }
        });
        ArrayAdapter<String> mes2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses);
        mes2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mesf.setAdapter(mes2);
        mesf.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mesfi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mesfi="";
            }
        });
        List<String> años = new ArrayList<String>();
        años.add("Año");
        for(int i=2010;i<2017;i++){
            años.add(""+(i+1));
        }
        ArrayAdapter<String> año1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, años);
        año1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        añoi.setAdapter(año1);
        añoi.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                añoin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                añoin="0";
            }
        });
        ArrayAdapter<String> año2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, años);
        año2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        añof.setAdapter(año2);
        añof.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                añofi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                añofi="00";
            }
        });
        List<String> horas = new ArrayList<String>();
        horas.add("Hora");
        for(int i=0;i<=23;i++){
            if(i<10) {
                horas.add("0" + (i));
            }else{
                horas.add("" + (i));
            }
        }
        ArrayAdapter<String> hora1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        hora1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horai.setAdapter(hora1);
        horai.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horain = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horain="";
            }
        });
        ArrayAdapter<String> hora2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        hora2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horaf.setAdapter(hora2);
        horaf.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horafi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horafi="";
            }
        });
        List<String> mins = new ArrayList<String>();
        mins.add("Minuto");
        for(int i=0;i<=59;i++){
            if(i<10) {
                mins.add("0" + (i));
            }else{
                mins.add("" + (i));
            }
        }
        ArrayAdapter<String> min1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mins);
        min1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mini.setAdapter(min1);
        mini.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                minin="";
            }
        });
        ArrayAdapter<String> min2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mins);
        min2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minf.setAdapter(min2);
        minf.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minfi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                minfi="";
            }
        });
    }

    public void onClick(View view) {
        key1=añoin+"-"+mesin+"-"+diain+" "+horain+":"+minin;
        key2=añofi+"-"+mesfi+"-"+diafi+" "+horafi+":"+minfi;
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("name",getIntent().getStringExtra("name"));
        intent.putExtra("keyi",key1);
        intent.putExtra("keyf",key2);
        startActivity(intent);
        finish();
    }
}
