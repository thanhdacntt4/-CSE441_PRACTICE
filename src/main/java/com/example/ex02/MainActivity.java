package com.example.ex02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex02.R;

public class MainActivity extends AppCompatActivity {
    // Khai báo các biến giao diện tại đây
    EditText edtA, edtB, edtKQ;
    Button btncong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ Id cho các biến giao diện
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btncong = findViewById(R.id.btntong);

        // Xử lý tương tác với người dùng
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                    int a = Integer.parseInt(edtA.getText().toString());
                    int b = Integer.parseInt(edtB.getText().toString());

                    // Tính tổng
                    int kq = a + b;

                    // Hiển thị kết quả lên EditText edtKQ
                    edtKQ.setText(String.valueOf(kq));
                } catch (NumberFormatException e) {
                    edtKQ.setText("Lỗi nhập liệu");
                }
            }
        });
    }
}
