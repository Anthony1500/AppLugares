package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.ActivityListaBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Listausu extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListAdapter miadapter;
    private List<listausuarios> milista = new ArrayList<>();
    String s = "";
    RequestQueue rq;
    ListView lista;
    String descripcion,nombre,id_parques;
    String url = "https://cosecha.tech/applugares_api_service/selectparques.php";

    Timer timer = new Timer();
    private ListausuViewModel mViewModel;

    private View bindinga;
    public static Listausu newInstance() {
        return new Listausu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista = (ListView) findViewById(R.id.lista_usuarios);
        lista.setOnItemClickListener(this);
        List<String> names = new ArrayList<String>();






        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            JsonArrayRequest jsonArrayrequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    JSONObject jsonObject = null;

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            id_parques = jsonObject.getString("id_parques");
                            nombre = jsonObject.getString("nombre");
                            descripcion = jsonObject.getString("descripcion");

                            if (this != null) {
                                milista.add(new listausuarios(nombre, id_parques));
                                miadapter = new listausuariosadapter(Listausu.this, R.layout.lista_items, milista);
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
            alertDialog.setMessage("Está seguro que desea seleccionar este parque?");
            alertDialog.setIcon( R.drawable.asignar);
            alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {


                   // Intent intent = new Intent(Listausu.this, Inicio.class);
                  //  intent.putExtra("nombre", milista.get(position).getNombre());
                   // intent.putExtra("id_parques", milista.get(position).getId_parques());

                  //  startActivity(intent);
                 //   finish();
                    Toast.makeText(Listausu.this,"Pantalla de selección del parque." ,Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.show();







    }

}