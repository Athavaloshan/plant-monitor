package com.android.plantmonitor.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.core.content.ContextCompat;

import java.lang.Math;
import java.util.Calendar;

import com.android.plantmonitor.R;

public class Co2 extends AppCompatActivity {
    private LineChart mChart;
    private Button dashboard_btn, datePicker_start_btn, datePicker_end_btn, show_btn;
    int startYear = 2020, endYear = 2023;
    private int  getRandomNumber(){
        int max = 35;
        int min = 10;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;

        return rand;
        // Output is different everytime this code is executed
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2);
        mChart = findViewById(R.id.chart);
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        renderData();

        dashboard_btn = findViewById(R.id.dashboard_tmp_btn);
        dashboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Co2.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });


        datePicker_start_btn = findViewById(R.id.datePicker_start);
        datePicker_end_btn = findViewById(R.id.datePicker_end);

        datePicker_start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openDialog(true);
            }
        });

        datePicker_end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openDialog(false);
            }

        });

        show_btn = findViewById(R.id.graph_show_btn);
        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                renderData();}
        });
    }
    private void openDialog(boolean isStart){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if (isStart) {
                    startYear = year;
                }
                else {
                    endYear = year;
                }
            }
        }, year, month, day);
        dialog.show();
    }
    public void renderData() {
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(endYear);
        xAxis.setAxisMinimum(startYear);
        xAxis.setDrawLimitLinesBehindData(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(35f);
        leftAxis.setAxisMinimum(10f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        mChart.getAxisRight().setEnabled(false);
        setData();
    }

    private void setData() {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = startYear; i <= endYear; ++i) {
            values.add(new Entry(i, getRandomNumber()));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.invalidate();
        } else {
            set1 = new LineDataSet(values, "Sample Data");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.DKGRAY);
            set1.setCircleColor(Color.DKGRAY);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_black);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            mChart.setData(data);
        }
    }
}
