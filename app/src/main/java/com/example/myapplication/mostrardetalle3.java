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

public class mostrardetalle3 extends AppCompatActivity {
    Button atras,mapa;
    RequestQueue rq;
    private GoogleMap mMap;
    String idrestaurante;
    String url;
    String ubicacion,nombre,id_restaurante;
    TextView texto,titulo;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrardetalle2);
        idrestaurante = getIntent().getStringExtra("idrestaurante");
        url = "https://cosecha.tech/applugares_api_service/selectinforestaurante.php?idrestaurante="+idrestaurante;
        rq = Volley.newRequestQueue(mostrardetalle3.this);
        atras=findViewById(R.id.btnatras3);
        mapa=findViewById(R.id.mapa);
        texto=findViewById(R.id.texto);
        imagen= findViewById(R.id.imagenparque);
        titulo=findViewById(R.id.titulo);

        atras.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mostrardetalle3.this,Listausu3.class);//Envió hacia otro Activity
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

                        if( jsonObject.getString("nombre").equals("Il Vulcano Pizzeria")) {
                            imagen.setImageResource(R.drawable.vulcanopizzeria);
                            id_restaurante = jsonObject.getString("id_restaurante");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("El Garage De Chelmo")) {
                            imagen.setImageResource(R.drawable.garagechelmo);
                            id_restaurante = jsonObject.getString("id_restaurante");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Tierrita Linda Cafeteria")) {
                            imagen.setImageResource(R.drawable.tierritalinda);
                            id_restaurante = jsonObject.getString("id_restaurante");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Gallinas de Pinllo")) {
                            imagen.setImageResource(R.drawable.tierritalinda);
                            id_restaurante = jsonObject.getString("id_restaurante");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }

                    } catch (JSONException e) {
                        Toast.makeText(mostrardetalle3.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError errore) {
                Toast.makeText(mostrardetalle3.this, "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        }
        );
        rq= Volley.newRequestQueue(mostrardetalle3.this);
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