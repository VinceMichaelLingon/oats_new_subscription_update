package com.example.oatsv5.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.DashboardActivity;
import com.example.oatsv5.MainAuth;
import com.example.oatsv5.Models.LoginGuestResult;
import com.example.oatsv5.Models.RegisterGuestResult;
import com.example.oatsv5.Models.Thesis.ThesisJSONResponse;
import com.example.oatsv5.R;
import com.example.oatsv5.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestAuth extends Fragment {

    View view;
    TextView goToGuestSignUp, goToGuestSignIn;
    LinearLayout GuestLogin, GuestRegister;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://oatsweb-y4tm5.ondigitalocean.app/";


    private String token;

    private SharedPreferences userPref;

    public GuestAuth(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_guest_auth, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        init();
        return view;
    }

    public void init(){
        Button loginBtn = view.findViewById(R.id.loginGuest);
        EditText GemailEdit = view.findViewById(R.id.editLogGEmail);
        EditText GpasswordEdit = view.findViewById(R.id.editLogGPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();
                map.put("guest_mail", GemailEdit.getText().toString());
                map.put("guest_password", GpasswordEdit.getText().toString());

                Call<LoginGuestResult> call = retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<LoginGuestResult>() {
                    @Override
                    public void onResponse(Call<LoginGuestResult> call, Response<LoginGuestResult> response) {
                        if (response != null && response.isSuccessful()) {
                            LoginGuestResult result = response.body();

                            token = response.body().getToken();

                            getGuestUser();


                        } else if (response.code() == 500) {
                            Toast.makeText(getContext(), "Wrong Credentials!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginGuestResult> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        goToGuestSignUp = view.findViewById(R.id.goToGuestSignUp);
        goToGuestSignIn = view.findViewById(R.id.goToGuestSignIn);
        GuestLogin = view.findViewById(R.id.guestLogin);
        GuestRegister = view.findViewById(R.id.guestRegister);



        goToGuestSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuestRegister.setVisibility(view.VISIBLE);
                GuestLogin.setVisibility(view.GONE);

            }
        });

        goToGuestSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuestLogin.setVisibility(view.VISIBLE);
                GuestRegister.setVisibility(view.GONE);
            }
        });

        Button regGuest = view.findViewById(R.id.regG);
        EditText regGfname = view.findViewById(R.id.editRegGFname);
        EditText regGlname = view.findViewById(R.id.editRegGLname);
        EditText regGcontact = view.findViewById(R.id.editRegGContact);
        EditText regGprof = view.findViewById(R.id.editRegGProf);
        EditText regGcomp = view.findViewById(R.id.editRegGComp);
        EditText regGcompadd = view.findViewById(R.id.editRegGCompAdd);
        EditText regGmail = view.findViewById(R.id.editRegGEmail);
        EditText regGPass = view.findViewById(R.id.editRegGPass);

        regGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("guest_fname", regGfname.getText().toString());
                map.put("guest_lname", regGlname.getText().toString());
                map.put("guest_contact", regGcontact.getText().toString());
                map.put("guest_profession", regGprof.getText().toString());
                map.put("guest_company", regGcomp.getText().toString());
                map.put("guest_company_address", regGcompadd.getText().toString());
                map.put("guest_mail", regGmail.getText().toString());
                map.put("passwords", regGPass.getText().toString());


                Call<RegisterGuestResult> call = retrofitInterface.executeRegister(map);
                call.enqueue(new Callback<RegisterGuestResult>() {
                    private Call<RegisterGuestResult> call;
                    private Response<RegisterGuestResult> response;

                    @Override
                    public void onResponse(Call<RegisterGuestResult> call, Response<RegisterGuestResult> response) {
                        this.call = call;
                        this.response = response;

                        RegisterGuestResult registerResponse = response.body();

                        if (response !=null && response.isSuccessful()){
                            new android.app.AlertDialog.Builder(getContext())
                                    .setMessage("REGISTERED SUCCESSFULLY!\n \ncheck your email and verify your account")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            GuestLogin.setVisibility(view.VISIBLE);
                                            GuestRegister.setVisibility(view.GONE);
                                            Toast.makeText(getContext(), "please verify your account before signing in!", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .show();
                        } else if (response.code() == 500) {
                            new android.app.AlertDialog.Builder(getContext())
                                    .setMessage("Account Already Registered!")
                                    .setPositiveButton("Ok", null)
                                    .show();
                        }else if (response.code() == 400){
                            System.out.println(response);
                            new android.app.AlertDialog.Builder(getContext())
                                    .setMessage("ERROR 400\n\n"+response)
                                    .setPositiveButton("Ok", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterGuestResult> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void getGuestUser(){
        Call<LoginGuestResult> call = retrofitInterface.getGuestUser(token);
        call.enqueue(new Callback<LoginGuestResult>() {
            @Override
            public void onResponse(Call<LoginGuestResult> call, Response<LoginGuestResult> response) {
                if (response.isSuccessful()){

                    userPref = getActivity().getApplicationContext().getSharedPreferences("user", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", token);
                    editor.putString("role", "Guest");
                    editor.putString("id", ""+response.body().getGuestUser().get_id());
                    editor.putString("name", ""+response.body().getGuestUser().getGuest_fname()+" "+response.body().getGuestUser().getGuest_lname());
                    editor.putString("email", ""+response.body().getGuestUser().getGuest_mail());
                    editor.putString("contact", ""+response.body().getGuestUser().getGuest_contact());
                    editor.putString("profession", ""+response.body().getGuestUser().getGuest_profession());
                    editor.putString("company", ""+response.body().getGuestUser().getGuest_company());
                    editor.putString("company_address", ""+response.body().getGuestUser().getGuest_company_address());


                    if(response.body().getGuestSub() != null){
                        editor.putString("sub", "on");
//                        editor.putString("subStatus", ""+response.body().getSubType().getStatus());
//                        editor.putString("subType", ""+response.body().getSubType().getSub_type());
                    } else{
                        editor.putString("sub", "off");
                    }

                    editor.apply();


                    startActivity(new Intent(getContext(), DashboardActivity.class));

                }else {
                    Toast.makeText(getContext(), "Wrong Credentials!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginGuestResult> call, Throwable t) {

            }

        });
    }

}
