package com.example.goodjob.fragments;


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
import com.example.goodjob.R;
import com.example.goodjob.adapter.SolicitudProductoAdapter;
import com.example.goodjob.classes.SolicitudProducto;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.interfaces.OnSolicitudProductoListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitudProductosFragment extends Fragment implements OnSolicitudProductoListener {

    private RecyclerView rvSolicitudProductos;
    private List<SolicitudProducto> productos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solicitud_productos, container, false);
        cargarViewsYRecycler(view);
        listarSolicitudProductos(view);
        return view;
    }

    private void cargarViewsYRecycler(View view) {
        productos = new ArrayList<>();
        rvSolicitudProductos = view.findViewById(R.id.rvSolicitudProductos);
        rvSolicitudProductos.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvSolicitudProductos.setHasFixedSize(true);
    }

    private void listarSolicitudProductos(View view) {
        String url = ValidSession.IP + "/ws_listarSolicitudProductos.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        SolicitudProducto sp = cargarDesdeJson(data);
                        productos.add(sp);
                    }
                    cargarAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(view.getContext()).add(request);
    }

    private SolicitudProducto cargarDesdeJson(JSONObject data) {
        SolicitudProducto sp = new SolicitudProducto();
        sp.setId(data.optInt("id"));
        sp.setEmpresa(data.optString("empresa"));
        sp.setNombre(data.optString("nombre"));
        sp.setValor(data.optDouble("valor"));
        sp.setStock(data.optInt("stock"));
        sp.setLugarCanje(data.optString("lugar_canje"));
        sp.setImagenUrl(data.optString("imagen_url"));
        return sp;
    }

    private void cargarAdapter() {
        SolicitudProductoAdapter adapter = new SolicitudProductoAdapter(productos, this);
        rvSolicitudProductos.setAdapter(adapter);
    }

    @Override
    public void onSolicitudProductoClicl(int posicion) {
        SolicitudProducto sp = productos.get(posicion);
        Bundle bundle = new Bundle();
        bundle.putParcelable("producto", sp);
        Fragment detalle = new SolicitudProductoDetalleFragment();
        detalle.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, detalle)
                .commit();
    }
}
