package com.example.thuchanh3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {
    private EditText editTextId, editTextName, editTextGpa;
    private RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextId = findViewById(R.id.edit_text_id);
        editTextName = findViewById(R.id.edit_text_name);
        editTextGpa = findViewById(R.id.edit_text_gpa);
        radioGroupGender = findViewById(R.id.radio_group_gender);
        Button addButton = findViewById(R.id.button_add);

        // Trong AddStudentActivity
        addButton.setOnClickListener(v -> {
            // Lấy thông tin từ form
            String id = editTextId.getText().toString();
            String name = editTextName.getText().toString();
            String gpaStr = editTextGpa.getText().toString();
            double gpa = gpaStr.isEmpty() ? 0 : Double.parseDouble(gpaStr);

            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender != null ? selectedGender.getText().toString().toLowerCase() : "";

            // Tạo sinh viên mới
            Student newStudent = new Student(id, name, gender, gpa, "", "", ""); // Giả sử bạn sẽ thêm các thông tin khác sau

            // Truyền dữ liệu trở lại MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newStudent", newStudent);
            setResult(RESULT_OK, resultIntent);
            finish(); // Kết thúc Activity và quay về MainActivity
        });

    }
}
