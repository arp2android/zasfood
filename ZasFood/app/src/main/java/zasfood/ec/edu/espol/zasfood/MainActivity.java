package zasfood.ec.edu.espol.zasfood;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment = null;
    Toolbar toolbar;
    public static RutaFragment rutaView = new RutaFragment();
    public static RetoFragment retos = new RetoFragment();
    public static String idUser;
    public static Context c;

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new RutaFragment());
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginActivity.loginActivity.finish();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.rgb(118, 215, 247));
        setSupportActionBar(toolbar);
        c = this;
        toolbar.setTitle(R.string.Ruta);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(Color.rgb(118, 215, 247));
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        Uri imageUri = getIntent().getParcelableExtra("imageUri");
        idUser = getIntent().getStringExtra("id");

        View v = navigationView.getHeaderView(0);
        TextView nameScreen = v.findViewById(R.id.user);
        TextView emailScreen = v.findViewById(R.id.userEmail);
        ImageView user = v.findViewById(R.id.userProfile);
        nameScreen.setText(name);
        emailScreen.setText(email);
        Picasso.with(this).load(imageUri).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(user);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mapa) {
            toolbar.setTitle(R.string.Ruta);
            fragment = rutaView;
        } else if (id == R.id.reto) {
            toolbar.setTitle(R.string.desafio);
            fragment = retos;
        } else if (id == R.id.cupon) {
            toolbar.setTitle(R.string.Cupones);
            fragment = new CuponFragment();
        } else if (id == R.id.ajustes) {
            toolbar.setTitle(R.string.setting);
        } else if (id == R.id.share) {
            toolbar.setTitle(R.string.share);
        } else if (id == R.id.about) {
            toolbar.setTitle(R.string.About);
        }else if (id == R.id.administrar){
            toolbar.setTitle(R.string.Admin);
            fragment = new AdminFragment();
        }else if (id == R.id.verificar){
            toolbar.setTitle(R.string.Veri);
            fragment = new ValidarFragment();
        }
        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
