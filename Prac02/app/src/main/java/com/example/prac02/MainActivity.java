package com.example.prac02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextBirthDate, editTextSalary;
    private Button buttonAddEmployee;
    private TextView textViewEmployees;
    private List<Employee> employeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextSalary = findViewById(R.id.editTextSalary);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        textViewEmployees = findViewById(R.id.textViewEmployees);

        // Thiết lập sự kiện khi nhấn nút "Add Employee"
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ các ô nhập liệu
                int id = Integer.parseInt(editTextId.getText().toString());
                String name = editTextName.getText().toString();
                String birthDate = editTextBirthDate.getText().toString();
                double salary = Double.parseDouble(editTextSalary.getText().toString());

                // Tạo đối tượng Employee mới
                Employee newEmployee = new Employee(id, name, birthDate, salary);

                // Thêm vào danh sách nhân viên
                employeeList.add(newEmployee);

                // Cập nhật danh sách hiển thị trên TextView
                updateEmployeeList();
            }
        });
    }

    // Phương thức cập nhật danh sách hiển thị trên TextView
    private void updateEmployeeList() {
        StringBuilder employeeDisplay = new StringBuilder();
        for (Employee employee : employeeList) {
            employeeDisplay.append(employee.toString()).append("\n");
        }
        textViewEmployees.setText(employeeDisplay.toString());
    }
}
