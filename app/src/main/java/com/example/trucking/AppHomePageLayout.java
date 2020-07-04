package com.example.trucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

public class AppHomePageLayout extends AppCompatActivity {

    BoomMenuButton bmb;
    ArrayList<Integer> imageIDList;
    ArrayList<String> titleList;

    EditText LoadLocation,unLoadLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home_page_layout);

        LoadLocation=findViewById(R.id.EditText_LoadingLocation);
        unLoadLocation=findViewById(R.id.EditText_UnloadingLocation);

        imageIDList = new ArrayList<>();
        titleList = new ArrayList<>();
        setInitialData();
        bmb = (BoomMenuButton) findViewById(R.id.boomMenu);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(imageIDList.get(i))
                    .normalText(titleList.get(i))
                    .subNormalText("Click to enter");
            bmb.addBuilder(builder);
        }

    }

    private void setInitialData() {
        imageIDList.add(R.drawable.user);
        imageIDList.add(R.drawable.settings);
        imageIDList.add(R.drawable.logout);
        titleList.add("User Account");
        titleList.add("Settings");
        titleList.add("Logout");
    }

    public void Post(View view) {
        String loadinglocation = LoadLocation.getText().toString();
        String unLoadingLocation = unLoadLocation.getText().toString();
        //put these loading and unloading strings in card view
        
    }
}