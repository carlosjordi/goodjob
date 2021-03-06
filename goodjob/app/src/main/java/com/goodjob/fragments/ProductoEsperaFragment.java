package com.goodjob.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.goodjob.R;
import com.goodjob.classes.ProductoEspera;
import com.goodjob.classes.ValidSession;
import com.goodjob.adapter.ProductoEsperaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductoEsperaFragment extends Fragment {

    private RecyclerView rvProductosEspera;
    private List<ProductoEspera> productosEnEspera;
    private Integer estado = null;
    private TextView estadoProducto;


    public ProductoEsperaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producto_espera, container, false);

        prepararRecyclerView(view);
        productosEnEspera = new ArrayList<>();
        Bundle bundle = this.getArguments();
        estado = bundle.getInt("estado");
        estadoProducto = view.findViewById(R.id.estado_producto_text);
        establecerEstadoProductoTexto();
        cargarData();

        return view;
    }

    private void establecerEstadoProductoTexto(){
        switch (estado) {
            case 0:
                estadoProducto.setText("En espera");
                break;
            case 1:
                estadoProducto.setText("Aceptados");
                break;
            case 2:
                estadoProducto.setText("Rechazados");
                break;
        }
    }

    private void prepararRecyclerView(View view) {
        rvProductosEspera = view.findViewById(R.id.rvProductosEspera);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvProductosEspera.setLayoutManager(llm);
        rvProductosEspera.setHasFixedSize(true);
    }

    /*
        El nombre aquí va a quedar incongruente, pero debido a apuros lo dejaré así.
        Si gustas de modificarlo luego para que quede más legible, hazlo.
        La confusión puede darse por el nombre(obviamente), ya que no solo lista productos en espera
        sino que lista productos en base al estado que le mandes:
        0: en espera
        1: aceptados
        2: rechazados
    * */
    private void cargarData() {
        String url = ValidSession.INSTANCE.getIP() + "/ws_listarProductosEnEspera.php?id_empresa=" + ValidSession.INSTANCE.getEmpresaLogueada().getId() +
                "&estado=" + estado;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        ProductoEspera pe = ProductoEspera.Companion.cargarDesdeJson(object);
                        productosEnEspera.add(pe);
                    }
                    cargarAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void cargarAdapter() {
        ProductoEsperaAdapter adapter = new ProductoEsperaAdapter(productosEnEspera);
        rvProductosEspera.setAdapter(adapter);
    }
}
