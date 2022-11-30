package com.example.myapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Listausu2 extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListAdapter miadapter;
    private List<listausuarios2> milista = new ArrayList<>();
    String s = "";
    RequestQueue rq;
    ListView lista;
    String descripcion,nombre,id_mercado;
Button atras;

    String url = "https://cosecha.tech/applugares_api_service/selectmercado.php";

    Timer timer = new Timer();
    private ListausuViewModel mViewModel;

    private View bindinga;
    public static Listausu2 newInstance() {
        return new Listausu2();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista2);
        lista = (ListView) findViewById(R.id.lista_usuarios);
        lista.setOnItemClickListener(this);
        List<String> names = new ArrayList<String>();

        atras=findViewById(R.id.btnatras2);
        atras.setOnClickListener(new View.OnClickListener() {//Método para darle función al botón

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listausu2.this,pantalla_botones.class);//Envió hacia otro Activity
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        //******************************************************************************************




        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            JsonArrayRequest jsonArrayrequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    JSONObject jsonObject = null;

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            id_mercado = jsonObject.getString("id_mercado");
                            nombre = jsonObject.getString("nombre");
                            descripcion = jsonObject.getString("descripcion");

                            if (this != null) {
                                milista.add(new listausuarios2(nombre,id_mercado));
                                miadapter = new listausuariosadapte2(Listausu2.this, R.layout.lista_items, milista);
                                lista.setAdapter(miadapter);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error de Conexión", Toast.LENGTH_SHORT).show();
                }
            }
            );
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            rq = Volley.newRequestQueue(getApplicationContext());
            rq.add(jsonArrayrequest);


                                      }








        //   textView.setText(s);








    public void onItemClick(AdapterView<?> Adapterview, View view, int position, long id) {


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);//Alert dialog cerrar sesión
            alertDialog.setTitle(milista.get(position).getNombre());
            alertDialog.setMessage("Está seguro que desea seleccionar este mercado?");
            alertDialog.setIcon( R.drawable.mercado);
            alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {


                   // Intent intent = new Intent(Listausu.this, Inicio.class);
                  //  intent.putExtra("nombre", milista.get(position).getNombre());
                   // intent.putExtra("id_parques", milista.get(position).getId_parques());

                  //  startActivity(intent);
                 //   finish();
                    Toast.makeText(Listausu2.this,"Pantalla de selección del mercado." ,Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.show();







    }

}