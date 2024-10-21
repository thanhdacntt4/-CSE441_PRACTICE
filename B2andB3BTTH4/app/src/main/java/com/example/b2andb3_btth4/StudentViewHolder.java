package com.example.b2andb3_btth4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private TextView textName, textClass, textGpa;
    private ImageView imageProfile;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.text_name);
        textClass = itemView.findViewById(R.id.text_class);
        textGpa = itemView.findViewById(R.id.text_gpa);
        imageProfile = itemView.findViewById(R.id.image_profile);
    }

    public void bindData(Student student) {
        textName.setText(student.getHoten());
        textClass.setText(student.getLop());
        textGpa.setText(String.valueOf(student.getDiem()));

        // Hiển thị ảnh đại diện sử dụng Glide
        Glide.with(itemView.getContext())
                .load(student.getAnhdaidien())
                .placeholder(R.drawable.ic_placeholder)
                .into(imageProfile);
    }
}
