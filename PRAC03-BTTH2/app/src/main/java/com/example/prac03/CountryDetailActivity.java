package com.example.prac03;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailActivity extends AppCompatActivity {

    private TextView countryNameTextView;
    private TextView countryCapitalTextView;
    private TextView countryPopulationTextView;
    private ImageView countryFlagImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        countryNameTextView = findViewById(R.id.country_name);
        countryCapitalTextView = findViewById(R.id.country_capital);
        countryPopulationTextView = findViewById(R.id.country_population);
        countryFlagImageView = findViewById(R.id.country_flag);

        // Nhận dữ liệu từ Intent
        String countryName = getIntent().getStringExtra("country_name");
        String countryCapital = getIntent().getStringExtra("country_capital");
        int countryFlag = getIntent().getIntExtra("country_flag", 0);
        int countryPopulation = getIntent().getIntExtra("country_population", 0);

        // Gán dữ liệu cho các view
        countryNameTextView.setText(countryName);
        countryCapitalTextView.setText(countryCapital);
        countryPopulationTextView.setText(String.valueOf(countryPopulation));
        countryFlagImageView.setImageResource(countryFlag);
    }
}
