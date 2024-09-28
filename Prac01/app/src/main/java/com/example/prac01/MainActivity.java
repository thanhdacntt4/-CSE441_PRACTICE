package com.example.prac01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openChildActivityButton = findViewById(R.id.openChildActivityButton);


        // Mở Activity con khi nhấn vào nút
        openChildActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChildActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Nhận dữ liệu từ Activity con
            String hoTen = data.getStringExtra("hoTen");
            String diemTBTL = data.getStringExtra("diemTBTL");

            // Hiển thị dữ liệu nhận được lên TextView
            resultTextView.setText("Họ và Tên: " + hoTen + "\nĐiểm TBTL: " + diemTBTL);
        }
    }
}
