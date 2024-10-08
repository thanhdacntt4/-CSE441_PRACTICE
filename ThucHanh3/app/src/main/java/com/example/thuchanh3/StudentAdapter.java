package com.example.thuchanh3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students;
    private OnStudentClickListener listener;

    public StudentAdapter(List<Student> students, OnStudentClickListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.name.setText(student.getName());
        holder.gpa.setText("GPA: " + student.getGpa());

        // Hiển thị ảnh dựa trên giới tính
        if (student.getGender().equals("male")) {
            holder.image.setImageResource(R.drawable.ic_male);  // Đặt ảnh nam
        } else if (student.getGender().equals("female")) {
            holder.image.setImageResource(R.drawable.ic_female);  // Đặt ảnh nữ
        }

        holder.itemView.setOnClickListener(v -> listener.onStudentClick(student));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name, gpa;
        ImageView image;

        public StudentViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            gpa = itemView.findViewById(R.id.student_gpa);
            image = itemView.findViewById(R.id.student_image);
        }
    }

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }
}
