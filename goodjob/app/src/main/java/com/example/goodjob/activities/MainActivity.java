package com.example.goodjob.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.goodjob.R;
import com.example.goodjob.SuscriptionActivity;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.fragments.HomeFragment;
import com.example.goodjob.fragments.PreMyActivitesFragment;
import com.example.goodjob.fragments.ProductoEsperaFragment;
import com.example.goodjob.fragments.ProfileFragment;
import com.example.goodjob.fragments.RegistrarProductoFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;
    private FloatingActionButton publicarActividad;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        publicarActividad = findViewById(R.id.fabPublicarActividad);

        cargarFragment(new HomeFragment());

        // navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // here i'll do the magic trick
        if (ValidSession.empresaLogueada != null) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.empresa_menu);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarBotonPublicar();
        publicarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidSession.empresaLogueada != null)
                    startActivity(new Intent(MainActivity.this, PublicarActividadActivity.class));
                else
                    Toast.makeText(getApplicationContext(), "No puedes realizar esta acción", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void mostrarBotonPublicar() {
        if (ValidSession.empresaLogueada != null)
            publicarActividad.setVisibility(View.VISIBLE);
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
                case R.id.navigation_premiun:
                    startActivity(new Intent(MainActivity.this, SuscriptionActivity.class));
                    return true;
                case R.id.navigation_estado_mis_actividades:
                    if (ValidSession.usuarioLogueado == null) {
                        cuadroDialogo();
                        return true;
                    }
                    selectedFragment = new PreMyActivitesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, selectedFragment).commit();
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
        if (ValidSession.usuarioLogueado == null && ValidSession.empresaLogueada == null) {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_registrar_producto) {
            cargarFragment(new RegistrarProductoFragment());
        } else if (id == R.id.nav_productos_espera) {
            cargarFragment(new ProductoEsperaFragment());
        } else if (id == R.id.nav_productos_aceptados) {

        } else if (id == R.id.nav_productos_rechazados) {

        } else if (id == R.id.nav_actividades_registro) {

        } else if (id == R.id.nav_actividades_espera) {

        } else if (id == R.id.nav_actividades_aceptadas) {

        } else if (id == R.id.nav_actividades_rechazadas) {

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
}