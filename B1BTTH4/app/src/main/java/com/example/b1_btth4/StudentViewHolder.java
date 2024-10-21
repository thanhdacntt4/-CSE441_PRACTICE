package com.example.b1_btth4;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b1_btth4.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    public TextView tvHoten, tvMSSV, tvLop, tvDiem;
    public Button btnEdit, btnDelete;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHoten = itemView.findViewById(R.id.tvHoten);
        tvMSSV = itemView.findViewById(R.id.tvMSSV);
        tvLop = itemView.findViewById(R.id.tvLop);
        tvDiem = itemView.findViewById(R.id.tvDiem);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }

    public void bind(com.example.b1_btth4.Student student) {
        tvHoten.setText(student.getHoten());
        tvMSSV.setText(student.getMssv());
        tvLop.setText(student.getLop());
        tvDiem.setText(String.valueOf(student.getDiem()));
    }
}
