package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity  {
    String estado,idpuesto,puestotxt;
    TextView txt_puesto;
    EditText placa,nombre,he,celular,puesto;
    Button boton_tiket,boton_lista,botonregistro;
    ProgressDialog progressDialog;
    RequestQueue rq, rq2;//Definimos variables a utilizar
    JsonRequest jrq;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        puestotxt = getIntent().getStringExtra("puesto");
        idpuesto = getIntent().getStringExtra("idpuesto");
        estado="Ocupado";
        placa = (EditText) findViewById(R.id.usuario_placa);
        nombre = (EditText) findViewById(R.id.usuario_nombre);
        he = (EditText) findViewById(R.id.usuario_he);
        celular = (EditText) findViewById(R.id.usuario_celular);

        txt_puesto =(TextView)  findViewById(R.id.txt_estado);
        botonregistro=(Button)findViewById(R.id.btn_tiket1);
        txt_puesto.setText(puestotxt);




        botonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                rcliente();






               }
        });


    }




    private void rcliente() {

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, "https://apps.indoamerica.edu.ec/catastros/apptaxi/clie.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datos incetados")) ;
                Toast.makeText(Inicio.this, "datos insertados", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<>();
                parametros.put("placa",placa.getText().toString());
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("hora_ingreso",he.getText().toString());
                parametros.put("celular",celular.getText().toString());
                parametros.put("idpuesto",idpuesto);
                parametros.put("estado",estado);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}

