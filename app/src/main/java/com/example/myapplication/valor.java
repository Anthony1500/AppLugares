package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class valor extends AppCompatActivity {
    String estado,idpuesto,puestotxt;
    TextView txt_puesto,transcurso;
    EditText placa,nombre,he,valor_he,he1;
    Button boton_tiket,boton_lista,botonfinalizarpago,botonregresar;
    ProgressDialog progressDialog;
    String currentDateandTime;
    RequestQueue rq;
    ListView lista;
    String url ;
    private final int SECONDS_IN_ONE_DAY = 86_400;
    private final int SECONDS_IN_ONE_HOUR = 3_600;
    private final int SECONDS_IN_ONE_MINUTE = 60;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valor);
        puestotxt = getIntent().getStringExtra("puesto");
        idpuesto = getIntent().getStringExtra("idpuesto");
        url = "https://apps.indoamerica.edu.ec/catastros/apptaxi/selectvehcliente.php?idpuesto="+idpuesto;
        estado="Ocupado";
        placa = (EditText) findViewById(R.id.valor_placa);
        nombre = (EditText) findViewById(R.id.valor_nombre);
        he = (EditText) findViewById(R.id.valor_horaentrada);
        he1 = (EditText) findViewById(R.id.valor_horasalida);
        botonregresar=(Button)findViewById(R.id.btn_regresar);
        txt_puesto =(TextView)  findViewById(R.id.txt_estado);
       transcurso=(TextView)  findViewById(R.id.transcurso);
        botonfinalizarpago=(Button)findViewById(R.id.btn_pagar);
        valor_he=(EditText)findViewById(R.id.valor_he);
        txt_puesto.setText(puestotxt);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        currentDateandTime = sdf.format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
      String  currentDateandTime1 = sdf1.format(new Date());
        he1.setText(currentDateandTime1);
        placa.setKeyListener(null);
        placa.setClickable(true);
        nombre.setKeyListener(null);
        nombre.setClickable(true);
        he.setKeyListener(null);
        he.setClickable(true);
        placa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(valor.this, "Campo solo lectura. ", Toast.LENGTH_SHORT).show();
            }
        });
        nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(valor.this, "Campo solo lectura. ", Toast.LENGTH_SHORT).show();
            }
        });
        he.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(valor.this, "Campo solo lectura. ", Toast.LENGTH_SHORT).show();
            }
        });

        botonfinalizarpago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcliente();

            }
        });
        botonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(valor.this, Listausu.class);

                startActivity(intent);
                finish();

            }
        });



        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        JsonArrayRequest jsonArrayrequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;
                final ProgressDialog progressDialog= new ProgressDialog(valor.this);
                progressDialog.setMessage("Cargando");
                progressDialog.show();
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        placa.setText(jsonObject.getString("placa"));
                        nombre.setText(jsonObject.getString("nombre"));
                        he.setText(jsonObject.getString("hora_ingreso"));
                        calculate(jsonObject.getString("hora_ingreso"),currentDateandTime);

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
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

    private void rcliente() {

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, "https://apps.indoamerica.edu.ec/catastros/apptaxi/e.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datos actualizados")) ;
                Toast.makeText(valor.this, "datos actualizados", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent intent = new Intent(valor.this, Listausu.class);

                startActivity(intent);
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
                parametros.put("idpuesto",idpuesto);



                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void calculate(String hora1, String hora2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date startDate = simpleDateFormat.parse(hora1);
            Date endDate = simpleDateFormat.parse(hora2);

            long start = startDate.getTime() / 1000;
            long end = endDate.getTime() / 1000;

            if(end < start)
                end += SECONDS_IN_ONE_DAY;

            int difference = (int) (end - start);

            int days = difference / SECONDS_IN_ONE_DAY;
            difference %= SECONDS_IN_ONE_DAY;
            int hours = difference / SECONDS_IN_ONE_HOUR;
            difference %= SECONDS_IN_ONE_HOUR;
            int minutes = difference / SECONDS_IN_ONE_MINUTE;
            difference %= SECONDS_IN_ONE_MINUTE;

           transcurso.setText("Tiempo transcurrido : "+"horas: " + hours + ", minutos: " + minutes);
         if(hours>0 || minutes>0){
             double hora;
             double minutos;
             double total;
             hora= hours*(60*(0.01));
             minutos= minutes*0.01;
             total=hora+minutos;

             valor_he.setText("$"+String.format(Locale.ROOT, "%.2f", total ));
         }else if(hours==0 || minutes>0){

             double minutos;
             double total;
             minutos= minutes*0.01;
             total=minutos;


             valor_he.setText("$"+String.format(Locale.ROOT, "%.2f", total ));
         }else if(hours>0 || minutes==0) {
             double hora;
             double total;
             hora= hours*(60*(0.01));
             total=hora;

             valor_he.setText("$"+String.format(Locale.ROOT, "%.2f", total ));
         }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
