package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class registro extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
Button atras,guardar,cancelar;
    EditText tnombre,tcontrasenia,tcorreo;
    ProgressDialog progressDialog;
    RequestQueue rq;
    JsonRequest jrq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        atras=findViewById(R.id.btnatras);
        tnombre=(EditText) findViewById(R.id.nombre);
        tcontrasenia=(EditText) findViewById(R.id.contrasenia);
        tcorreo=(EditText) findViewById(R.id.correo);
        guardar=(Button) findViewById(R.id.btnguardar);
        cancelar= (Button) findViewById(R.id.btncancelar);
        rq = Volley.newRequestQueue(registro.this);
        atras.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registro.this,login.class);//Envió hacia otro Activity
                startActivity(intent);
            }
        });
        //******************************************************************************************

        guardar.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(registro.this);
                progressDialog.setMessage("Por favor espera...");//Método del Progress Dialog
                progressDialog.setCancelable(false);
                progressDialog.show();
                agregar();


            }


        });

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.dismiss();
        Toast.makeText(this, "Se guardo Correctamente", Toast.LENGTH_SHORT).show();
    }


    private void agregar(){

        String url="https://cosecha.tech/applugares_api_service/registro.php?nombre="+tnombre.getText().toString()+"&contrasenia="+tcontrasenia.getText().toString()+"&correo="+tcorreo.getText().toString();
        jrq= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);//Envió y recepción de datos
    }
}