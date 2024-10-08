package com.example.thuchanh3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView birthDateTextView;
    private TextView majorTextView;
    private ImageView genderImageView;
    private int position; // Vị trí của sinh viên trong danh sách

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        nameTextView = findViewById(R.id.text_view_name);
        emailTextView = findViewById(R.id.text_view_email);
        birthDateTextView = findViewById(R.id.text_view_birth_date);
        majorTextView = findViewById(R.id.text_view_major);
        genderImageView = findViewById(R.id.image_view_gender);
        Button deleteButton = findViewById(R.id.button_delete); // Nút xóa sinh viên

        // Nhận đối tượng Student và vị trí từ Intent
        Student student = (Student) getIntent().getSerializableExtra("student");
        position = getIntent().getIntExtra("position", -1); // Nhận vị trí sinh viên

        // Hiển thị thông tin sinh viên
        if (student != null) {
            nameTextView.setText(student.getName());
            emailTextView.setText(student.getEmail());
            birthDateTextView.setText(student.getBirthDate());
            majorTextView.setText(student.getMajor());
            genderImageView.setImageResource(student.getGenderImage());
        }

        // Xử lý sự kiện click nút xóa
        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", position); // Trả về vị trí để xóa
            setResult(RESULT_OK, resultIntent);
            finish(); // Kết thúc Activity và quay về MainActivity
        });
    }
}
