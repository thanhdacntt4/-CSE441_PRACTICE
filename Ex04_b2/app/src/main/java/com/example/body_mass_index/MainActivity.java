package com.example.body_mass_index;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {
Button btnChanDoan ;
EditText editTen,editChieucao,editCannang,editBMI,editChandoan;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        btnChanDoan= findViewById(R.id.btnBMI);
        editTen = findViewById(R.id.edtten);
        editChieucao = findViewById(R.id.edtchieucao);
        editCannang = findViewById(R.id.edtcannang);
        editBMI = findViewById(R.id.edtBMI);
        editChandoan = findViewById(R.id.edtChuanDoan);
        btnChanDoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double H=Double.parseDouble(editChieucao.getText()+"");
                double W=Double.parseDouble(editCannang.getText()+"");
                double BMI=W/Math.pow(H,2);
                String chandoan="";
                if(BMI<18)
                {
                    chandoan="Bạn gầy";
                }
                else if(BMI<=24.9)
                {
                    chandoan="Bạn bình thường";
                }
                else if(BMI<=29.9)
                {
                    chandoan="Bạn béo phì độ 1";
                }
                else if(BMI<=34.9)
                {
                    chandoan="Bạn béo phì độ 2";
                }
                else
                {
                    chandoan="Bạn béo phì độ 3";
                }
                DecimalFormat dcf=new DecimalFormat("#.0");
                editBMI.setText(dcf.format(BMI));
                editChandoan.setText(chandoan);
            }
        });


    }
}



