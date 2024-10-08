package com.example.thuchanh3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentClickListener {

    private static final int ADD_STUDENT_REQUEST = 1;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<Student> studentList;
    private List<Student> filteredStudentList; // Danh sách sinh viên đã lọc
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        studentList = new ArrayList<>();
        filteredStudentList = new ArrayList<>(); // Khởi tạo danh sách sinh viên đã lọc
        adapter = new StudentAdapter(filteredStudentList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Đọc dữ liệu từ file JSON
        loadStudentsFromJson();

        // Thêm sự kiện tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText); // Gọi hàm lọc khi người dùng nhập
                return true;
            }
        });

        ImageButton addButton = findViewById(R.id.btn_add_student);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, ADD_STUDENT_REQUEST);
        });
    }

    // Phương thức để đọc file JSON và phân tích cú pháp
    private void loadStudentsFromJson() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("students.json")));
            Gson gson = new Gson();
            Type studentListType = new TypeToken<List<Student>>() {}.getType();
            studentList = gson.fromJson(reader, studentListType);
            filteredStudentList.addAll(studentList); // Thêm dữ liệu vào danh sách đã lọc
            adapter.notifyDataSetChanged(); // Cập nhật adapter
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
        }
    }

    @Override
    public void onStudentClick(Student student) {
        Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
        intent.putExtra("student", student);
        intent.putExtra("position", filteredStudentList.indexOf(student)); // Gửi vị trí của sinh viên đã lọc
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            Student newStudent = (Student) data.getSerializableExtra("newStudent");
            if (newStudent != null) {
                studentList.add(newStudent);
                filteredStudentList.add(newStudent); // Thêm vào danh sách đã lọc
                adapter.notifyItemInserted(filteredStudentList.size() - 1);
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            if (data != null) {
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    removeStudent(position);
                }
            }
        }
    }

    private void removeStudent(int position) {
        if (position >= 0 && position < filteredStudentList.size()) {
            Student studentToRemove = filteredStudentList.get(position);
            filteredStudentList.remove(position); // Xóa sinh viên khỏi danh sách đã lọc
            studentList.remove(studentToRemove); // Xóa sinh viên khỏi danh sách gốc
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, filteredStudentList.size());
        }
    }

    // Phương thức lọc sinh viên
    private void filter(String text) {
        filteredStudentList.clear();
        if (text.isEmpty()) {
            filteredStudentList.addAll(studentList); // Nếu không có gì, hiển thị toàn bộ danh sách
        } else {
            for (Student student : studentList) {
                if (student.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredStudentList.add(student); // Thêm sinh viên vào danh sách đã lọc nếu tên chứa chuỗi tìm kiếm
                }
            }
        }
        adapter.notifyDataSetChanged(); // Cập nhật adapter
    }
}
