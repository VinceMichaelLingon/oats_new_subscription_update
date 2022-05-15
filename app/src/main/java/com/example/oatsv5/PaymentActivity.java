package com.example.oatsv5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    private SharedPreferences userPref;
    private CardView cardOneDay, cardOneWeek;
    private ImageView qrOneDay, qrOneWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userPref = this.getSharedPreferences("payment", this.MODE_PRIVATE);

        cardOneDay = findViewById(R.id.cardOneDay);
        cardOneWeek = findViewById(R.id.cardOneWeek);
        qrOneDay = findViewById(R.id.qrOneDay);
        qrOneWeek = findViewById(R.id.qrOneWeek);

        init();

    }

    public void init(){
        String type = userPref.getString("type", "");
        Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();

        if(type.equals("oneDay")){
            cardOneDay.setVisibility(View.VISIBLE);
            cardOneWeek.setVisibility(View.GONE);
            qrOneDay.setVisibility(View.VISIBLE);
            qrOneWeek.setVisibility(View.GONE);
        }else {
            cardOneDay.setVisibility(View.GONE);
            cardOneWeek.setVisibility(View.VISIBLE);
            qrOneDay.setVisibility(View.GONE);
            qrOneWeek.setVisibility(View.VISIBLE);
        }
    }

}