package com.example.prac01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChildActivity extends AppCompatActivity {

    private EditText hoTenEditText;
    private EditText diemTBTLEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        hoTenEditText = findViewById(R.id.hoTenEditText);
        diemTBTLEditText = findViewById(R.id.diemTBTLEditText);
        Button submitButton = findViewById(R.id.submitButton);

        // Gửi kết quả về Activity cha khi nhấn nút
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = hoTenEditText.getText().toString();
                String diemTBTL = diemTBTLEditText.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("hoTen", hoTen);
                resultIntent.putExtra("diemTBTL", diemTBTL);

                setResult(RESULT_OK, resultIntent);
                finish(); // Kết thúc activity con và quay về activity cha
            }
        });
    }
}
