package com.example.fahrenheit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
EditText txtCel,txtFar;
Button btnCel, btnFar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
            txtFar = findViewById(R.id.txtFar);
            txtCel = findViewById(R.id.txtCel);
            btnFar = findViewById(R.id.btnFar);
            btnCel = findViewById(R.id.btnCel);
            btnCel.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    DecimalFormat dcf=new DecimalFormat("#.00");
                    String doC = txtCel.getText()+"";
                    int C=Integer.parseInt(doC);
                    txtFar.setText(dcf.format(C * 1.8 + 32));
                }
            });
            btnFar.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    DecimalFormat dcf=new DecimalFormat("#.00");
// TODO Auto-generated method stub
                    String doF = txtFar.getText()+"";
                    int F=Integer.parseInt(doF);
                    txtCel.setText(dcf.format((F - 32) / 1.8));
                }
            });


    }
}



