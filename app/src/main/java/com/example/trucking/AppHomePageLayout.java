package com.example.trucking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

public class AppHomePageLayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BoomMenuButton bmb;
    private DrawerLayout drawer;
    ArrayList<Integer> imageIDList;
    ArrayList<String> titleList;
    TextView noTrips;

    EditText LoadLocation,unLoadLocation;
    TextView WhatsYour;

    private String Email=null;
    String Fullname=null;
    String Mobile=null;

    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_Home:

//                Intent intent=new Intent(this,AppHomePageLayout.class);
//                startActivity(intent);
//                finish();
                break;
            case R.id.nav_Account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragment()).commit();
                break;
            case R.id.nav_tripHistory:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TripHistoryFragment()).commit();
                break;
            case R.id.nav_Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START );

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home_page_layout);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.DrawerLayout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        WhatsYour = findViewById(R.id.TextView_WhatsYour);
        noTrips =findViewById(R.id.TextView_NoTrips);

        sharedPreferences=getSharedPreferences("Login_Status", MODE_PRIVATE);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Email=sharedPreferences.getString("Email","No name defined");

//        Email = getIntent().getExtras().getString("Email");
        getUserDetails(Email);
//        WhatsYour.setText(Fullname + " enter load and unload location.");
//        For toolbar
//        mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
//        mActionBarToolbar.setTitle(Fullname=null+"'s Homapage");

// '       LoadLocation = findViewById(R.id.EditText_LoadingLocation);
//'        unLoadLocation = findViewById(R.id.EditText_UnloadingLocation);

        imageIDList = new ArrayList<>();
        titleList = new ArrayList<>();
        //setInitialData();
//    /    bmb = findViewById(R.id.boomMenu);
//        bmb.setButtonEnum(ButtonEnum.Ham);
//        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
//        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
//        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
//
//            HamButton.Builder builder = new HamButton.Builder()
//                    .normalImageRes(imageIDList.get(i))
//                    .normalText(titleList.get(i))
//                    .subNormalText("Click to enter");
//            bmb.addBuilder(builder);
//        }

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void getUserDetails(final String email) {
        //Toast.makeText(this, "databaseref:  "+databaseReference, Toast.LENGTH_LONG).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            User u=null;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot dataSnapshot1:  dataSnapshot.getChildren()){
                    User user=dataSnapshot1.getValue(User.class);
                    if(user.getEmail().equals(email)){
                        Fullname=user.getFullname();
                        Mobile=user.getMobile();
                        //Email=user.getEmail();
                        Log.i("Mile gese","Hoise mama");
                        Toast.makeText(AppHomePageLayout.this, "FullName: "+Fullname, Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//    public void onStart(){
//        super.onStart();
//        WhatsYour.setText(Fullname + " enter load and unload location.");
//    }
//    private void setInitialData() {
//        imageIDList.add(R.drawable.user);
//        imageIDList.add(R.drawable.settings);
//        imageIDList.add(R.drawable.logout);
//        titleList.add(Fullname);
//        titleList.add("Settings");
//        titleList.add("Logout");
//    }

    public void Post(View view) {
//        String loadinglocation = LoadLocation.getText().toString();
//        String unLoadingLocation = unLoadLocation.getText().toString();
        noTrips.setVisibility(View.INVISIBLE);
        //put these loading and unloading strings in card view
        
    }
}