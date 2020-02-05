package com.goodjob.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.goodjob.classes.ValidSession;
import com.goodjob.fragments.EstadoMisActividadesFragment;
import com.goodjob.fragments.EstadoMisPublicacionesFragment;
import com.goodjob.fragments.HomeFragment;
import com.goodjob.fragments.ListaEmpresasEsperaFragment;
import com.goodjob.fragments.ListadoActividadEmpresaEstadoFragment;
import com.goodjob.fragments.ProductoEsperaFragment;
import com.goodjob.fragments.ProductosCanjeFragment;
import com.goodjob.fragments.ProfileFragment;
import com.goodjob.fragments.RegistrarProductoFragment;
import com.goodjob.fragments.SolicitudActividadesEsperaFragment;
import com.goodjob.fragments.SolicitudProductosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.goodjob.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        cargarFragment(new HomeFragment());

        // navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // here i'll do the magic trick
        if (ValidSession.INSTANCE.getEmpresaLogueada() != null) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.empresa_menu);
        } else if (ValidSession.INSTANCE.getUsuarioLogueado() != null && ValidSession.INSTANCE.getUsuarioLogueado().getTipoUsuario().equals("Administrador")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.administrador_menu);
        } else if (ValidSession.INSTANCE.getEmpresaLogueada() == null && ValidSession.INSTANCE.getUsuarioLogueado() == null) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.navigation_productos:
                    selectedFragment = new ProductosCanjeFragment();
                    break;
                case R.id.navigation_estado_mis_actividades:
                    if (ValidSession.INSTANCE.getUsuarioLogueado() == null && ValidSession.INSTANCE.getEmpresaLogueada() == null) {
                        cuadroDialogo();
                        return true;
                    } else if (ValidSession.INSTANCE.getEmpresaLogueada() != null) {
                        selectedFragment = new EstadoMisPublicacionesFragment();
                    } else {
                        selectedFragment = new EstadoMisActividadesFragment();
                    }
                    break;
            }
            cargarFragment(selectedFragment);
            return true;
        }
    };

    private void cuadroDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.inicio_sesion);
        builder.setMessage(R.string.iniciar_sesion);
        builder.setPositiveButton(R.string.ok_sesion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setMenu(menu);
        SearchView search = getSearchView(menu);
        setSearchViewHint(search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ImgProfile:
                sendToLoginOrProfile();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    }

    private SearchView getSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.buscador);
        return (SearchView) item.getActionView();
    }

    private void setSearchViewHint(SearchView search) {
        search.setQueryHint("Buscar...");
    }

    private void sendToLoginOrProfile() {
        if (ValidSession.INSTANCE.getUsuarioLogueado() == null && ValidSession.INSTANCE.getEmpresaLogueada() == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            navigation.setSelectedItemId(R.id.navigation_profile);
        }
    }

    // mavigation drawer

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_registrar_producto) { // empresas
            cargarFragment(new RegistrarProductoFragment());
        } else if (id == R.id.nav_productos_espera) {
            cargarFragmentProductosEmpresa(0);
        } else if (id == R.id.nav_productos_aceptados) {
            cargarFragmentProductosEmpresa(1);
        } else if (id == R.id.nav_productos_rechazados) {
            cargarFragmentProductosEmpresa(2);
        } else if (id == R.id.nav_actividades_registro) {
            startActivity(new Intent(getApplication(), PublicarActividadActivity.class));
        } else if (id == R.id.nav_actividades_espera) {
            cargarFragmentActividadesEmpresa(0);
        } else if (id == R.id.nav_actividades_aceptadas) {
            cargarFragmentActividadesEmpresa(1);
        } else if (id == R.id.nav_actividades_rechazadas) {
            cargarFragmentActividadesEmpresa(2);
        } else if (id == R.id.nav_solicitud_empresas) { // administradores
            cargarFragment(new ListaEmpresasEsperaFragment());
        } else if (id == R.id.nav_solicitud_actividades) {
            cargarFragment(new SolicitudActividadesEsperaFragment());
        } else if (id == R.id.nav_solicitud_productos) {
            cargarFragment(new SolicitudProductosFragment());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, fragment)
                .commit();
    }

    private void cargarFragmentActividadesEmpresa(int estado) {
        Fragment fragment = new ListadoActividadEmpresaEstadoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("estado", estado);
        fragment.setArguments(bundle);
        cargarFragment(fragment);
    }

    private void cargarFragmentProductosEmpresa(int estado) {
        Fragment fragment = new ProductoEsperaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("estado", estado);
        fragment.setArguments(bundle);
        cargarFragment(fragment);
    }
}