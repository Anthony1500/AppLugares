package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tiket extends AppCompatActivity {
    TextView datos, datos1,datos2,datos3,datos4;
    Button boton_tiket;
    String info,info1,info2,info3,info4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_tiket);
        datos = findViewById(R.id.textView2);
        datos1 = findViewById(R.id.t1);
        datos2 = findViewById(R.id.t2);
        datos3 = findViewById(R.id.t3);
        datos4 = findViewById(R.id.t4);
        Bundle datr = getIntent().getExtras();
        info = datr.getString("datos");
        info1 = datr.getString("datos1");
        info2= datr.getString("datos2");
        info3 = datr.getString("datos3");
        info4 = datr.getString("datos4");
        datos.setText(info);
        datos1.setText(info1);
        datos2.setText(info2);
        datos3.setText(info3);
        datos4.setText(info4);

        boton_tiket=(Button)findViewById(R.id.button);
        boton_tiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Inicio.class);
                startActivity(intent);

            }
        });
    }

}