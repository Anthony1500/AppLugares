package com.example.myapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class pantalla_botones extends AppCompatActivity {
    Button parques,mercados,restaurantes,culturaarte,historia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_botones);
        parques=findViewById(R.id.btparque);
        mercados=findViewById(R.id.btmercado);
        restaurantes=findViewById(R.id.btrestaurante);
        culturaarte=findViewById(R.id.culturaarte);
        historia=findViewById(R.id.historia);

        parques.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla_botones.this,Listausu.class);//Envió hacia otro Activity

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        mercados.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla_botones.this,Listausu2.class);//Envió hacia otro Activity

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        restaurantes.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla_botones.this,Listausu3.class);//Envió hacia otro Activity

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        culturaarte.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla_botones.this,Listausu4.class);//Envió hacia otro Activity

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        historia.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla_botones.this,Listausu5.class);//Envió hacia otro Activity

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
