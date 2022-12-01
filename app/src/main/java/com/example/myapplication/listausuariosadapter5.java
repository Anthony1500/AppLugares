package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class listausuariosadapter5 extends ArrayAdapter<listausuarios5> {
    private List<listausuarios5> milista;
    private Context mcontext;
    private int  recurso;

    public listausuariosadapter5(@NonNull Context context, int resource, List<listausuarios5> objects) {
        super(context, resource, objects);
        this.milista=objects;
        this.mcontext=context;
        this.recurso=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
       
        if(view==null)
            view= LayoutInflater.from(mcontext).inflate(R.layout.lista_items,null);

        listausuarios5 listausu =milista.get(position);


        TextView puesto =view.findViewById(R.id.puesto);
        puesto.setText(listausu.getNombre());
        TextView estado =view.findViewById(R.id.estado);
        estado.setText("");
        TextView idpuesto =view.findViewById(R.id.idpuesto);
        idpuesto.setText(listausu.id_hoteles);
        notifyDataSetChanged();
        return view;
    }
}
