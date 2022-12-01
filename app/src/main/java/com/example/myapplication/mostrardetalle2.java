package com.example.myapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mostrardetalle2 extends AppCompatActivity {
    Button atras,mapa;
    RequestQueue rq;
    private GoogleMap mMap;
    String idmercado ;
    String url;
    String ubicacion,nombre,id_mercado;
    TextView texto,titulo;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrardetalle2);
        idmercado = getIntent().getStringExtra("idmercado");
        url = "https://cosecha.tech/applugares_api_service/selectinfomercado.php?idmercado="+idmercado;
        rq = Volley.newRequestQueue(mostrardetalle2.this);
        atras=findViewById(R.id.btnatras3);
        mapa=findViewById(R.id.mapa);
        imagen= findViewById(R.id.imagenparque);
        titulo=findViewById(R.id.titulo);
        texto=findViewById(R.id.texto);


        atras.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mostrardetalle2.this,Listausu2.class);//Envió hacia otro Activity
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        if( jsonObject.getString("nombre").equals("Mercado Modelo")) {
                            imagen.setImageResource(R.drawable.mercadomodelo);
                            id_mercado = jsonObject.getString("id_mercado");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Mercado Central")) {
                            imagen.setImageResource(R.drawable.mercadocentral);
                            id_mercado = jsonObject.getString("id_mercado");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Mercado Mayorista")) {
                            imagen.setImageResource(R.drawable.mercadomayorista);
                            id_mercado = jsonObject.getString("id_mercado");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }

                    } catch (JSONException e) {
                        Toast.makeText(mostrardetalle2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError errore) {
                Toast.makeText(mostrardetalle2.this, "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        }
        );
        rq= Volley.newRequestQueue(mostrardetalle2.this);
        rq.add(jsonArrayRequest);
        //****************************************************************************************

        mapa.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(ubicacion)); //o la direccion/consulta que quiera "http://maps.google.com/maps?q="+ myLatitude  +"," + myLongitude +"("+ labLocation + ")&iwloc=A&hl=es"
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        //******************************************************************************************










    }


}