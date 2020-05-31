package com.example.tounsia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity {
    PieChart pieChart;
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);


        back =findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(StatisticActivity.this, HomeActivity.class);
                startActivity(a);
            }
        });

        PieChart pieChart = findViewById(R.id.pie);
        float[] calls = {6500, 585, 5655 , 5655 ,585 };
        String[] violTypes = {"عنف نفسي / معنوي", "عنف جنس", "عنف لفظي","عنف اقتصادي","عنف ضد الأطفال"};
        float totalCalls = 6500;
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0;i<violTypes.length;i++) {
            float casesPercentage = calls[i] * 100 / totalCalls;
            PieEntry pieEntry = new PieEntry(casesPercentage, violTypes[i]);
            pieEntries.add(pieEntry);
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setDataSet(pieDataSet);
        pieChart.getDescription().setEnabled(false);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.animate();


    }
}
