package com.goodjob.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.goodjob.R;
import com.goodjob.adapter.SolicitudActividadesEsperaAdapter;
import com.goodjob.classes.Actividad;
import com.goodjob.classes.ValidSession;
import com.goodjob.interfaces.OnSolicitudActividadListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SolicitudActividadesEsperaFragment extends Fragment implements OnSolicitudActividadListener {

    private RecyclerView rvSolicitudActividades;
    private List<Actividad> actividades;

    public SolicitudActividadesEsperaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitud_actividades_espera, container, false);
        setRecyclerView(v);
        listarActividadesEnEspera(v);
        return v;
    }

    private void setRecyclerView(View v) {
        rvSolicitudActividades = v.findViewById(R.id.rvSolicitudActividadesEspera);
        rvSolicitudActividades.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rvSolicitudActividades.setHasFixedSize(true);
        actividades = new ArrayList<>();
    }

    private void listarActividadesEnEspera(View v) {
        String url = ValidSession.IP + "/ws_listarSolicitudesActividades.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject json;
                    Actividad a;
                    for (int i = 0; i < array.length(); i++) {
                        json = array.getJSONObject(i);
                        a = Actividad.loadActivityDataFromJsonObject(json);
                        actividades.add(a);
                    }
                    cargarAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        Volley.newRequestQueue(v.getContext()).add(request);
    }

    private void cargarAdapter() {
        SolicitudActividadesEsperaAdapter adapter = new SolicitudActividadesEsperaAdapter(actividades, this);
        rvSolicitudActividades.setAdapter(adapter);
    }

    @Override
    public void onSolicitudActividadClick(int posicion) {
        Actividad a = actividades.get(posicion);
        Bundle bundle = new Bundle();
        bundle.putParcelable("actividad", a);
        Fragment detalle = new SolicitudActividadesEsperaDetalleFragment();
        detalle.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, detalle)
                .commit();
    }
}
