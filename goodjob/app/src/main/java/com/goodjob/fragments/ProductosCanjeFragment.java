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
import com.goodjob.classes.ProductoCanje;
import com.goodjob.classes.ValidSession;
import com.goodjob.adapter.ProductoCanjeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductosCanjeFragment extends Fragment {

    private RecyclerView rvProductosCanjeables;
    private List<ProductoCanje> productosCanjes;

    public ProductosCanjeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_productos_canje, container, false);

        rvProductosCanjeables = v.findViewById(R.id.rvProductosCanje);
        rvProductosCanjeables.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProductosCanjeables.setHasFixedSize(true);
        productosCanjes = new ArrayList<>();

        cargarProductos();

        return v;
    }

    private void cargarProductos() {
        String url = ValidSession.INSTANCE.getIP() + "/ws_listarProductosCanjeables.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        ProductoCanje p = cargarDesdeJsonObject(object);
                        productosCanjes.add(p);
                    }
                    cargarAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(request);
    }

    private ProductoCanje cargarDesdeJsonObject(JSONObject data) {
        ProductoCanje p = new ProductoCanje();
        p.setId(data.optInt("id"));
        p.setProducto(data.optString("nombre"));
        p.setImagen(data.optString("url_imagen"));
        p.setStock(data.optInt("stock"));
        p.setValor(data.optDouble("valor"));
        p.setEmpresa(data.optString("empresa"));
        p.setLugarCanje(data.optString("lugar_canje"));
        return p;
    }

    private void cargarAdapter() {
        ProductoCanjeAdapter adapter = new ProductoCanjeAdapter(productosCanjes);
        rvProductosCanjeables.setAdapter(adapter);
    }
}
