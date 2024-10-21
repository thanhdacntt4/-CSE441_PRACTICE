package com.example.prac03;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách quốc gia
        countryList = new ArrayList<>();
        countryList.add(new Country("Vietnam", 97000000, R.drawable.vietnam_flag, "Hanoi"));
        countryList.add(new Country("USA", 331000000, R.drawable.usa_flag, "Washington D.C."));
        countryList.add(new Country("Japan", 126000000, R.drawable.japan_flag, "Tokyo"));

        adapter = new CountryAdapter(countryList, country -> {
            Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
            intent.putExtra("country_name", country.getName());
            intent.putExtra("country_capital", country.getCapital());
            intent.putExtra("country_flag", country.getFlagImage());
            intent.putExtra("country_population", country.getPopulation());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
