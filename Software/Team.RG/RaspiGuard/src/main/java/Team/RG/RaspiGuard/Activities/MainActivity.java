package Team.RG.RaspiGuard.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import Team.RG.RaspiGuard.Fragments.HomeFragment;
import Team.RG.RaspiGuard.Fragments.LogFragment;
import Team.RG.RaspiGuard.Fragments.ManageFragment;
import Team.RG.RaspiGuard.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {


            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            username = extras.getString("USERNAME"); // retrieve the data using keyName

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.remove(getString(R.string.msSpinPositionSave));
            editor.apply();


            // Toast.makeText(getApplicationContext(), "Logged in as : " + username, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception

        }
        //TRY CATCH BLOCK TO IGNORE  AUTHENTICATION FOR DEVELOPMENT

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new HomeFragment());
        ft.commit();

        navigationView.setCheckedItem(R.id.nav_home);


        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();

        fixToken();


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {

        //    Intent intent = new Intent(getApplicationContext(), Settings.class);
        //     startActivity(intent);
        //    return true;
        //  }

        return super.onOptionsItemSelected(item);
    }

    public void fixToken() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = preferences.getString("username", "");

        StringRequest stringRequest = new StringRequest("http://165.227.44.224/fixToken.php" + "?user=" + username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainLayout, new HomeFragment());
            ft.commit();
            // Handle the camera action

            //   Intent intent = new Intent(getApplicationContext(), Settings.class);
            // startActivity(intent);


        } else if (id == R.id.nav_manage) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainLayout, new ManageFragment());
            ft.commit();

            //     Intent intent = new Intent(getApplicationContext(), Settings.class);
            //      startActivity(intent);

        } else if (id == R.id.nav_log) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainLayout, new LogFragment());
            ft.commit();

            //    Intent intent = new Intent(getApplicationContext(), ActivityLog.class);
            //   startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
