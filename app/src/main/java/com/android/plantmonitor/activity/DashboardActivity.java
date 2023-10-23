package com.android.plantmonitor.activity;


import android.os.StrictMode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.plantmonitor.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView tempCard, rainCard, humidityCard, phCard, yieldCard, co2Card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tempCard = (CardView) findViewById(R.id.temperatureCard);
        rainCard = findViewById(R.id.rainCard);
        humidityCard = findViewById(R.id.humidityCard);
        phCard = findViewById(R.id.phCard);
        yieldCard = findViewById(R.id.yieldCard);
        co2Card = findViewById(R.id.co2Card);
        tempCard.setOnClickListener(this);
        rainCard.setOnClickListener(this);
        humidityCard.setOnClickListener(this);
        phCard.setOnClickListener(this);
        yieldCard.setOnClickListener(this);
        co2Card.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
            Intent i;
            int id = v.getId();
                if(id == R.id.temperatureCard) {
                    i = new Intent(this, Temperature.class);
                    startActivity(i);
                }
                else if(id == R.id.rainCard) {
                    i = new Intent(this, Rain.class);
                    startActivity(i);
                }
                else if(id == R.id.humidityCard) {
                    i = new Intent(this, Humidity.class);
                    startActivity(i);
                }
                else if(id == R.id.phCard) {
                    i = new Intent(this, Ph.class);
                    startActivity(i);
                }
                else if(id == R.id.yieldCard) {
                    i = new Intent(this, Yield.class);
                    startActivity(i);
                }
                else if(id == R.id.co2Card) {
                    i = new Intent(this, Co2.class);
                    startActivity(i);
                }
                else {
                    i = new Intent(this, Temperature.class);
                    startActivity(i);
                }

        }

}
