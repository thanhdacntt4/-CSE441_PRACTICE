package com.example.b1_btth4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.b1_btth4.Student;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etName, etMSSV, etClass, etGPA;
    private Button btnAdd;
    private DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etName);
        etMSSV = findViewById(R.id.etMSSV);
        etClass = findViewById(R.id.etClass);
        etGPA = findViewById(R.id.etGPA);
        btnAdd = findViewById(R.id.btnAdd);

        studentRef = FirebaseDatabase.getInstance().getReference("sinhvien");

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String mssv = etMSSV.getText().toString();
            String lop = etClass.getText().toString();
            double diem;

            try {
                diem = Double.parseDouble(etGPA.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(AddStudentActivity.this, "Điểm không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (name.isEmpty() || mssv.isEmpty() || lop.isEmpty()) {
                Toast.makeText(AddStudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Student student = new Student(mssv, name, lop, diem);
            studentRef.child(mssv).setValue(student).addOnSuccessListener(aVoid -> {
                Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                finish(); // Quay lại MainActivity
            }).addOnFailureListener(e -> {
                Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show();
            });
        });
    }
}
