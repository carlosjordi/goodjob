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
import com.goodjob.adapter.ListadoActividadesEmpresaAdapter;
import com.goodjob.classes.ListadoActividadesEmpresa;
import com.goodjob.classes.ValidSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListadoActividadEmpresaEstadoFragment extends Fragment {

    private RecyclerView rvActividades;
    private List<ListadoActividadesEmpresa> actividadesEmpresas;
    private Integer estado = null;
    private TextView estadoActividades;


    public ListadoActividadEmpresaEstadoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listado_actividad_empresa_estado, container, false);

        Bundle bundle = this.getArguments();
        estado = bundle.getInt("estado");
        actividadesEmpresas = new ArrayList<>();
        estadoActividades = view.findViewById(R.id.estado_actividad_text);
        establecerEstadoActividadTexto();
        setRecycler(view);
        cargarData();

        return view;
    }

    private void establecerEstadoActividadTexto(){
        switch (estado) {
            case 0:
                estadoActividades.setText("en Espera");
                break;
            case 1:
                estadoActividades.setText("Aceptados");
                break;
            case 2:
                estadoActividades.setText("Rechazadas");
                break;
        }
    }



    private void setRecycler(View view) {
        rvActividades = view.findViewById(R.id.rvActividadesEmpresa);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvActividades.setLayoutManager(llm);
        rvActividades.setHasFixedSize(true);
    }

    private void cargarData() {
        String url = ValidSession.INSTANCE.getIP() + "/ws_listarActividadesEmpresaPorEstado.php?id_empresa="
                + ValidSession.INSTANCE.getEmpresaLogueada().getId() + "&estado=" + estado;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        ListadoActividadesEmpresa lae = ListadoActividadesEmpresa.Companion.crearDesdeJson(data);
                        actividadesEmpresas.add(lae);
                        cargarAdapter();
                    }
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
        ListadoActividadesEmpresaAdapter adapter = new ListadoActividadesEmpresaAdapter(actividadesEmpresas);
        rvActividades.setAdapter(adapter);
    }
}
