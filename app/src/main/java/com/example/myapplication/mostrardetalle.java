package com.example.myapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mostrardetalle extends AppCompatActivity {
Button atras;
    RequestQueue rq;
    String url = "https://cosecha.tech/applugares_api_service/selectparques.php";
    String descripcion,nombre,id_parques;
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrardetalle);
        rq = Volley.newRequestQueue(mostrardetalle.this);
        atras=findViewById(R.id.btnatras3);
        texto=findViewById(R.id.texto);
        atras.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mostrardetalle.this,Listausu.class);//Envió hacia otro Activity
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        //******************************************************************************************
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        id_parques = jsonObject.getString("id_parques");
                        nombre = jsonObject.getString("nombre");
                        texto.setText(jsonObject.getString("descripcion"));
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












    }
}