package com.example.b1_btth4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.b1_btth4.Student;
import com.example.b1_btth4.StudentViewHolder;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference studentRef;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Student, StudentViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo Firebase Database và tham chiếu đến nhánh "sinhvien"
        database = FirebaseDatabase.getInstance();
        studentRef = database.getReference("sinhvien");

        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Thiết lập FirebaseRecyclerOptions để lấy dữ liệu từ Firebase
        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(studentRef, Student.class)
                .build();

        // Thiết lập adapter cho RecyclerView
        adapter = new FirebaseRecyclerAdapter<Student, StudentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull Student model) {
                holder.bind(model);

                holder.btnDelete.setOnClickListener(v -> {
                    studentRef.child(getRef(position).getKey()).removeValue(); // Xóa sinh viên khỏi Firebase
                });

                holder.btnEdit.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                    intent.putExtra("mssv", model.getMssv());
                    startActivity(intent); // Chuyển sang màn hình sửa sinh viên
                });
            }

            @NonNull
            @Override
            public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
                return new StudentViewHolder(view);
            }
        };

        // Đặt adapter cho RecyclerView
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddStudent).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddStudentActivity.class)); // Chuyển sang màn hình thêm sinh viên
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening(); // Bắt đầu lắng nghe sự thay đổi từ Firebase
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening(); // Dừng lắng nghe khi ứng dụng không còn hiển thị
    }
}
