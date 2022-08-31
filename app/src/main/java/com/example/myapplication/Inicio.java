package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    EditText placa,nombre,he,celular,puesto;
    Button boton_tiket,boton_lista,botonregistro;
    ProgressDialog progressDialog;
    RequestQueue rq, rq2;//Definimos variables a utilizar
    JsonRequest jrq;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        placa = (EditText) findViewById(R.id.usuario_placa);
        nombre = (EditText) findViewById(R.id.usuario_nombre);
        he = (EditText) findViewById(R.id.usuario_he);
        celular = (EditText) findViewById(R.id.usuario_celular);
        puesto = (EditText) findViewById(R.id.usuario_puesto);

        botonregistro=(Button)findViewById(R.id.btn_tiket1);
        boton_lista=(Button)findViewById(R.id.btn_lista);


        boton_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), lista.class);
                startActivity(intent);

            }
        });


        botonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registrase();
                rcliente();
                rpu();





               }
        });


    }


    private void registrase() {
    final String placa1=placa.getText().toString().trim();
        final String he1=he.getText().toString().trim();

      final ProgressDialog progressDialog= new ProgressDialog(this);
      progressDialog.setMessage("Cargando");
      progressDialog.show();
      StringRequest request=new StringRequest(Request.Method.POST, "https://apps.indoamerica.edu.ec/catastros/apptaxi/e.php", new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              if (response.equalsIgnoreCase("datas incetados")) ;
              Toast.makeText(Inicio.this, "datos insertados", Toast.LENGTH_SHORT).show();
              Bundle enviar= new Bundle();
              enviar.putString("datos",  placa.getText().toString());
              enviar.putString("datos1",  nombre.getText().toString());
              enviar.putString("datos2",  he.getText().toString());
              enviar.putString("datos3",  celular.getText().toString());
              enviar.putString("datos4",  puesto.getText().toString());
              Intent intent = new Intent(Inicio.this, tiket.class);
              intent.putExtras(enviar);
              startActivity(intent);
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
              parametros.put("placa1",placa.getText().toString());
              parametros.put("hora1",he.getText().toString());

              return parametros;
          }
      };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void rcliente() {

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, "https://apps.indoamerica.edu.ec/catastros/apptaxi/clie.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datas incetados")) ;
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
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("celular",celular.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    private void rpu() {

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, "https://apps.indoamerica.edu.ec/catastros/apptaxi/pue.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datas incetados")) ;
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
                parametros.put("puesto",puesto.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}

