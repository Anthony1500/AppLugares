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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class mostrardetalle extends AppCompatActivity {
Button atras,mapa;
    RequestQueue rq;
    private GoogleMap mMap;
    String idparques ;
    String url;
    String ubicacion,nombre,id_parques;
    TextView texto,titulo;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrardetalle2);
        idparques = getIntent().getStringExtra("idparques");
        url = "https://cosecha.tech/applugares_api_service/selectinfoparques.php?idparques="+idparques;
        rq = Volley.newRequestQueue(mostrardetalle.this);
        atras=findViewById(R.id.btnatras3);
        mapa=findViewById(R.id.mapa);
        texto=findViewById(R.id.texto);
        imagen= findViewById(R.id.imagenparque);
        titulo=findViewById(R.id.titulo);
        atras.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mostrardetalle.this,Listausu.class);//Envió hacia otro Activity
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

                        if( jsonObject.getString("nombre").equals("Parque 12 de Noviembre")) {
                            imagen.setImageResource(R.drawable.parque12);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque el sueño")) {
                            imagen.setImageResource(R.drawable.parquesueno);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque Montalvo")) {
                            imagen.setImageResource(R.drawable.parquemontalvo);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque Cevallos")) {
                            imagen.setImageResource(R.drawable.parquecevallos);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque Juan benigno Bela")) {
                            imagen.setImageResource(R.drawable.juanbenignobela);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque de la familia")) {
                            imagen.setImageResource(R.drawable.lafamilia);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque de Atocha")) {
                            imagen.setImageResource(R.drawable.atocha);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }
                        if( jsonObject.getString("nombre").equals("Parque de las flores")) {
                            imagen.setImageResource(R.drawable.flores);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            titulo.setText(jsonObject.getString("nombre"));
                            texto.setText(jsonObject.getString("descripcion"));
                            ubicacion= jsonObject.getString("ubicacion");
                        }

                    } catch (JSONException e) {
                        Toast.makeText(mostrardetalle.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError errore) {
                Toast.makeText(mostrardetalle.this, "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        }
        );
        rq= Volley.newRequestQueue(mostrardetalle.this);
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