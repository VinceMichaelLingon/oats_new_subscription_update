package com.example.oatsv5.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.Models.Bookmarks.BookmarkedJSONResponse;
import com.example.oatsv5.Models.Subscription.FindSubscription;
import com.example.oatsv5.R;
import com.example.oatsv5.RetrofitInterface;
import com.example.oatsv5.SubscribeActivity;
import com.example.oatsv5.ViewPDF;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private Context context;
    private View view;
    private SharedPreferences userPref;
    private TextView profileGuestName, profileGuestProfession, profileGuestContact, profileGuestEmail, profileGuestCompany, profileGuestCompanyAdd, subTypeGuest, subStatusGuest,
            profileStudentName, profileStudentId, profileStudentContact, profileStudentEmail, profileStudentDept, profileStudentCourse, subTypeStudent, subStatusStudent;
    private ImageButton subscribe;
    private String sub, subStatus, subType, role;

    private CardView subCardGuest, subCardStudent;
    public ProfileFragment (){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userPref = getContext().getSharedPreferences("user", getContext().MODE_PRIVATE);
        role = userPref.getString("role", "");

        initSub();

        if(role.equals("Student")){
            view = inflater.inflate(R.layout.layout_profile_student, container, false);
            context = view.getContext();
            return view;
        } else {
            view = inflater.inflate(R.layout.layout_profile_guest, container, false);
            context = view.getContext();
            return view;
        }
    }

    public void initSub(){
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
                if(role.equals("Student")){
                    initStudent();
                } else {
                    initGuest();
                }
            }
            public void onFailure(Call<FindSubscription> call, Throwable t) {

            }
        });
    }

    public void initStudent(){
        String tupId = userPref.getString("id", "");
        String name = userPref.getString("name", "");
        String contact = userPref.getString("contact","");
        String email = userPref.getString("email", "");
        String deptName = userPref.getString("deptName", "");
        String course = userPref.getString("course", "");



        profileStudentName = view.findViewById(R.id.profileStudentName);
        profileStudentId = view.findViewById(R.id.profileStudentId);
        profileStudentContact = view.findViewById(R.id.profileStudentContact);
        profileStudentEmail = view.findViewById(R.id.profileStudentEmail);
        profileStudentDept = view.findViewById(R.id.profileStudentDept);
        profileStudentCourse = view.findViewById(R.id.profileStudentCourse);

        subStatusStudent = view.findViewById(R.id.subStatusStudent);
        subTypeStudent = view.findViewById(R.id.subTypeStudent);

        subscribe = view.findViewById(R.id.subscribe);

        profileStudentName.setText(name);
        profileStudentId.setText(tupId);
        profileStudentContact.setText(contact);
        profileStudentEmail.setText(email);
        profileStudentDept.setText(deptName);
        profileStudentCourse.setText(course);

        if(sub.equals("on")){
            if(subStatus.equals("Expired")){
                subStatusStudent.setText("");
                subTypeStudent.setText(subStatus);
            }else {
                subStatusStudent.setText(subStatus);
                subTypeStudent.setText(subType);
            }
        } else {
            subStatusStudent.setText("");
            subTypeStudent.setText("Not Subscribed");
        }

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSub();
            }
        });

    }

    public void initGuest(){
        String name = userPref.getString("name", "");
        String profession = userPref.getString("profession", "");
        String contact = userPref.getString("contact", "");
        String email = userPref.getString("email", "");
        String company = userPref.getString("company", "");
        String companyAdd = userPref.getString("company_address", "");

        profileGuestName = view.findViewById(R.id.profileGuestName);
        profileGuestProfession = view.findViewById(R.id.profileGuestProfession);
        profileGuestContact = view.findViewById(R.id.profileGuestContact);
        profileGuestEmail = view.findViewById(R.id.profileGuestEmail);
        profileGuestCompany = view.findViewById(R.id.profileGuestCompany);
        profileGuestCompanyAdd = view.findViewById(R.id.profileGuestCompanyAdd);

        subStatusGuest = view.findViewById(R.id.subStatusGuest);
        subTypeGuest = view.findViewById(R.id.subTypeGuest);

        subscribe = view.findViewById(R.id.subscribe);

        profileGuestName.setText(name);
        profileGuestProfession.setText(profession);
        profileGuestContact.setText(contact);
        profileGuestEmail.setText(email);
        profileGuestCompany.setText(company);
        profileGuestCompanyAdd.setText(companyAdd);

        if(sub.equals("on")){
            if(subStatus.equals("Expired")){
                subStatusGuest.setText("");
                subTypeGuest.setText(subStatus);
            }else {
                subStatusGuest.setText(subStatus);
                subTypeGuest.setText(subType);
            }
        } else {
            subStatusGuest.setText("");
            subTypeGuest.setText("Not Subscribed");
        }

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSub();
            }
        });

    }

    public void goToSub(){
        Intent i = new Intent(context, SubscribeActivity.class);
        context.startActivity(i);
    }
}
