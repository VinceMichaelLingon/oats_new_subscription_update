package com.example.oatsv5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.Models.Subscription.FindSubscription;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubscribeActivity extends AppCompatActivity {
    private SharedPreferences userPref;

    private String sub, subStatus, subType;
    private ImageView selectedOneDay, selectedOneWeek;
    private TextView subStatusDisplay;

    private CardView cardOneDay, cardOneWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    public void init(){
        userPref = this.getSharedPreferences("user", this.MODE_PRIVATE);
        String userId = userPref.getString("id", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //call the interface
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<FindSubscription> call = retrofitInterface.getFindSubscription(userId);

        call.enqueue(new Callback<FindSubscription>() {
            @Override
            public void onResponse(Call<FindSubscription> call, Response<FindSubscription> response) {
                FindSubscription subscription = response.body();
                if(subscription.getSubscription() != null){
                    sub="on";
                    subStatus = subscription.getSubscription().getStatus();
                    subType = subscription.getSubscription().getSub_type();
                }else {
                    sub="off";
                }

                layout();

            }
            public void onFailure(Call<FindSubscription> call, Throwable t) {

            }
        });
    }

    public void layout(){
        selectedOneDay = findViewById(R.id.selectedOneDay);
        selectedOneWeek = findViewById(R.id.selectedOneWeek);
        subStatusDisplay = findViewById(R.id.subStatus);
        cardOneDay = findViewById(R.id.cardOneDay);
        cardOneWeek = findViewById(R.id.cardOneWeek);

        if(sub.equals("off")){
            selectedOneDay.setVisibility(View.GONE);
            selectedOneWeek.setVisibility(View.GONE);
        } else {
            if(subStatus.equals("Active")){
                subStatusDisplay.setText("You have an active Subscription Right now");
                if(subType.equals("oneDay")){
                    selectedOneDay.setVisibility(View.VISIBLE);
                    selectedOneWeek.setVisibility(View.GONE);
                }else{
                    selectedOneDay.setVisibility(View.GONE);
                    selectedOneWeek.setVisibility(View.VISIBLE);
                }
            } else {
                subStatusDisplay.setText("Oh no your subscription is expired! Please Resubscribe");
                selectedOneDay.setVisibility(View.GONE);
                selectedOneWeek.setVisibility(View.GONE);
            }
        }

        cardOneDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences payment = SubscribeActivity.this.getSharedPreferences("payment", SubscribeActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor editor = payment.edit();
                editor.putString("type", "oneDay");
                editor.apply();

                new android.app.AlertDialog.Builder(SubscribeActivity.this)
                        .setMessage("Pay for this promo?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SubscribeActivity.this, PaymentActivity.class);
                                SubscribeActivity.this.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        cardOneWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences payment = SubscribeActivity.this.getSharedPreferences("payment", SubscribeActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor editor = payment.edit();
                editor.putString("type", "oneWeek");
                editor.apply();

                new android.app.AlertDialog.Builder(SubscribeActivity.this)
                        .setMessage("Pay for this promo?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SubscribeActivity.this, PaymentActivity.class);
                                SubscribeActivity.this.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

}