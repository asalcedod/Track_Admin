package com.uninorte.carlos.track_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class filterActivity extends AppCompatActivity {
    final Calendar c = Calendar.getInstance();

    int maxYear = c.get(Calendar.YEAR) - 20; // this year ( 2011 ) - 20 = 1991
    int maxMonth = c.get(Calendar.MONTH);
    int maxDay = c.get(Calendar.DAY_OF_MONTH);

    int minYear = 1960;
    int minMonth = 0; // january
    int minDay = 25;
    private DatePicker BirthDateDP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

    }

    public void onClick(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
