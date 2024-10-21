package com.example.b1_btth4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import com.example.b1_btth4.Student;

public class EditStudentActivity extends AppCompatActivity {

    private EditText etName, etMSSV, etClass, etGPA;
    private Button btnUpdate;
    private DatabaseReference studentRef;
    private String mssv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etName = findViewById(R.id.etName);
        etMSSV = findViewById(R.id.etMSSV);
        etClass = findViewById(R.id.etClass);
        etGPA = findViewById(R.id.etGPA);
        btnUpdate = findViewById(R.id.btnUpdate);

        mssv = getIntent().getStringExtra("mssv");
        studentRef = FirebaseDatabase.getInstance().getReference("sinhvien").child(mssv);

        // Lấy dữ liệu sinh viên hiện tại
        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Student student = snapshot.getValue(Student.class);
                    if (student != null) {
                        etName.setText(student.getHoten());
                        etMSSV.setText(student.getMssv());
                        etClass.setText(student.getLop());
                        etGPA.setText(String.valueOf(student.getDiem()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditStudentActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String lop = etClass.getText().toString();
            double diem;

            try {
                diem = Double.parseDouble(etGPA.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(EditStudentActivity.this, "Điểm không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (name.isEmpty() || lop.isEmpty()) {
                Toast.makeText(EditStudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Student student = new Student(mssv, name, lop, diem);
            studentRef.setValue(student).addOnSuccessListener(aVoid -> {
                Toast.makeText(EditStudentActivity.this, "Cập nhật sinh viên thành công", Toast.LENGTH_SHORT).show();
                finish(); // Quay lại MainActivity
            }).addOnFailureListener(e -> {
                Toast.makeText(EditStudentActivity.this, "Cập nhật sinh viên thất bại", Toast.LENGTH_SHORT).show();
            });
        });
    }
}
