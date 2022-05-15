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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.DashboardActivity;
import com.example.oatsv5.Models.Bookmarks.BookmarkedJSONResponse;
import com.example.oatsv5.Models.Bookmarks.BookmarkedResult;
import com.example.oatsv5.Models.Bookmarks.DeleteBookmarkJSONResponse;
import com.example.oatsv5.Models.Courses.Courses;
import com.example.oatsv5.Models.Courses.CoursesJSONResponse;
import com.example.oatsv5.Models.Departments.DepartmentJSONResponse;
import com.example.oatsv5.Models.Departments.Departments;
import com.example.oatsv5.Models.LoginGuestResult;
import com.example.oatsv5.Models.RegisterGuestResult;
import com.example.oatsv5.Models.Spinner.Course;
import com.example.oatsv5.Models.Spinner.Department;
import com.example.oatsv5.Models.StudentAuth.StudentLoginJSONResponse;
import com.example.oatsv5.Models.StudentAuth.StudentRegisterJSONResponse;
import com.example.oatsv5.R;
import com.example.oatsv5.RetrofitInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentAuth extends Fragment {

    View view;

    private String token;
    private SharedPreferences userPref;

    TextInputEditText tuptId, editRegSFname, editRegSLname, contactNumS, editRegSEmail, editRegSPass, editLogSEmail, editLogSPassword;

    TextView goToStudentSignUp, gotToStudentSignIn;
    LinearLayout studentLogin, studentRegister;
    Spinner selectDept, selectCourse;
    private Button regS, loginS;
    private String departmentItems[] = {"BSAD", "Electrical and Allied", "Mechanical", "etc"};
    private String courseItems[] = {"BSIT", "BSME", "BTIT", "etc"};
    private ArrayAdapter<String> adapterItemsDept, adapterItemsCourse;

    Retrofit retrofit;
    RetrofitInterface retrofitInterface;

    List<Departments> departmentsArrayList;
    List<Courses> coursesArrayList;

    ArrayList<Department> departmentsList;
    ArrayList<Course> coursesList;

    String deptId;
    String courseId;

    public StudentAuth(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_student_auth, container, false);
        init();
        return view;
    }

    public void init(){
        goToStudentSignUp = view.findViewById(R.id.goToStudentSignUp);
        gotToStudentSignIn = view.findViewById(R.id.goToStudentSignIn);
        studentLogin = view.findViewById(R.id.studentLogin);
        studentRegister = view.findViewById(R.id.studentRegister);
        selectDept = view.findViewById(R.id.selectDept);
        selectCourse = view.findViewById(R.id.selectCourse);
        regS = view.findViewById(R.id.regS);
        loginS = view.findViewById(R.id.loginS);
        departmentsList = new ArrayList<Department>();
        coursesList = new ArrayList<Course>();
        editLogSEmail = view.findViewById(R.id.editLogSEmail);
        editLogSPassword = view.findViewById(R.id.editLogSPassword);

        //EditTexts
        tuptId = view.findViewById(R.id.tuptId);
        editRegSFname = view.findViewById(R.id.editRegSFname);
        editRegSLname = view.findViewById(R.id.editRegSLname);
        contactNumS = view.findViewById(R.id.contactNumS);
        editRegSEmail = view.findViewById(R.id. editRegSEmail);
        editRegSPass = view.findViewById(R.id.editRegSPass);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        setupSpinners();

        regS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerStudent();
            }
        });

        loginS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginStudent();
            }
        });

        goToStudentSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentRegister.setVisibility(view.VISIBLE);
                studentLogin.setVisibility(view.GONE);
            }
        });

        gotToStudentSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentLogin.setVisibility(view.VISIBLE);
                studentRegister.setVisibility(view.GONE);
            }
        });
    }

    public void setupSpinners(){
        Call<DepartmentJSONResponse> call = retrofitInterface.getDepartments();
        call.enqueue(new Callback<DepartmentJSONResponse>() {
            @Override
            public void onResponse(Call<DepartmentJSONResponse> call, Response<DepartmentJSONResponse> response) {

                DepartmentJSONResponse departmentJSONResponse = response.body();
                departmentsArrayList = new ArrayList<>(Arrays.asList(departmentJSONResponse.getDepartmentResult()));

                for(Departments department : departmentsArrayList){
                    departmentsList.add(new Department(""+department.get_id(), ""+department.getDeptname()));
                }

            }
            public void onFailure(Call<DepartmentJSONResponse> call, Throwable t) {

            }
        });
        departmentsList.add(new Department("", "--SELECT DEPARTMENT--"));


        ArrayAdapter<Department> departmentAdapter = new ArrayAdapter<Department>(getActivity(), android.R.layout.simple_spinner_dropdown_item, departmentsList);
        selectDept.setAdapter(departmentAdapter);

        coursesList.add(new Course("", "--SELECT COURSE--"));

        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<Course>(getActivity(), android.R.layout.simple_spinner_dropdown_item, coursesList);
        selectCourse.setAdapter(courseAdapter);

        selectDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Department spn = (Department) parent.getItemAtPosition(i);
                deptId = spn.id;


                if(deptId != ""){
                    Toast.makeText(getContext(), ""+spn.id, Toast.LENGTH_SHORT).show();
                    Call<CoursesJSONResponse> call = retrofitInterface.getCourses(deptId);

                    call.enqueue(new Callback<CoursesJSONResponse>() {
                        @Override
                        public void onResponse(Call<CoursesJSONResponse> call, Response<CoursesJSONResponse> response) {

                            CoursesJSONResponse coursesJSONResponse = response.body();
                            coursesArrayList = new ArrayList<>(Arrays.asList(coursesJSONResponse.getCourses()));

                            for(Courses courseResult : coursesArrayList){
                                coursesList.add(new Course(""+courseResult.get_id(), ""+courseResult.getCoursecode()));
                            }

                        }
                        public void onFailure(Call<CoursesJSONResponse> call, Throwable t) {

                        }
                    });
                } else {
                    coursesList.clear();
                    coursesList.add(new Course("", "--SELECT COURSE--"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selectCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Course spn = (Course) parent.getItemAtPosition(i);
                courseId = spn.id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void registerStudent(){
        HashMap<String, String> map = new HashMap<>();
        map.put("user_fname", editRegSFname.getText().toString());
        map.put("user_lname", editRegSLname.getText().toString());
        map.put("user_tupid", tuptId.getText().toString());
        map.put("user_contact", contactNumS.getText().toString());
        map.put("departments", deptId);
        map.put("courses", courseId);
        map.put("user_tupmail", editRegSEmail.getText().toString());
        map.put("passwords", editRegSPass.getText().toString());

        Call<StudentRegisterJSONResponse> call = retrofitInterface.executeRegisterStudent(map);

        call.enqueue(new Callback<StudentRegisterJSONResponse>() {
            @Override
            public void onResponse(Call<StudentRegisterJSONResponse> call, Response<StudentRegisterJSONResponse> response) {

                StudentRegisterJSONResponse registerResponse = response.body();

                if (response !=null && response.isSuccessful()){
                    new android.app.AlertDialog.Builder(getContext())
                            .setMessage("REGISTERED SUCCESSFULLY!\n \ncheck your email and verify your account")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    studentLogin.setVisibility(view.VISIBLE);
                                    studentRegister.setVisibility(view.GONE);
                                    Toast.makeText(getContext(), "please verify your account before signing in!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .show();
                } else if (response.code() == 400){
                    new android.app.AlertDialog.Builder(getContext())
                            .setMessage("ERROR 400\n\n")
                            .setPositiveButton("Ok", null)
                            .show();
                }
            }
            public void onFailure(Call<StudentRegisterJSONResponse> call, Throwable t) {

            }
        });
    }

    public void loginStudent(){
        HashMap<String, String> map = new HashMap<>();
        map.put("user_tupmail", editLogSEmail.getText().toString());
        map.put("user_password", editLogSPassword.getText().toString());

        Call<StudentLoginJSONResponse> call = retrofitInterface.executeLoginStudent(map);

        call.enqueue(new Callback<StudentLoginJSONResponse>() {
            @Override
            public void onResponse(Call<StudentLoginJSONResponse> call, Response<StudentLoginJSONResponse> response) {

                StudentLoginJSONResponse loginResponse = response.body();

                if (response !=null && response.isSuccessful()){
                    Toast.makeText(getContext(), "logged in successfully", Toast.LENGTH_SHORT).show();

                    StudentLoginJSONResponse result = response.body();

                    token = response.body().getToken();

                    getStudentUser();

                } else if (response.code() == 400){
                    new android.app.AlertDialog.Builder(getContext())
                            .setMessage("ERROR 400\n\n")
                            .setPositiveButton("Ok", null)
                            .show();
                }
            }
            public void onFailure(Call<StudentLoginJSONResponse> call, Throwable t) {

            }
        });
    }

    public void getStudentUser(){
        Call<StudentLoginJSONResponse> call = retrofitInterface.getStudentUser(token);
        call.enqueue(new Callback<StudentLoginJSONResponse>() {
            @Override
            public void onResponse(Call<StudentLoginJSONResponse> call, Response<StudentLoginJSONResponse> response) {
                Toast.makeText(getContext(), "ang token ay: "+token, Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){



                    userPref = getActivity().getApplicationContext().getSharedPreferences("user", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", token);
                    editor.putString("id", ""+response.body().getStudentUser().getUser_tupid());
                    editor.putString("name", ""+response.body().getStudentUser().getUser_fname()+" "+response.body().getStudentUser().getUser_lname());
                    editor.putString("contact", ""+response.body().getStudentUser().getUser_contact());
                    editor.putString("email", ""+response.body().getStudentUser().getUser_tupmail());
                    editor.putString("deptName", ""+response.body().getStudentUser().getStudentDepartment().getDeptname());
                    editor.putString("deptId", ""+response.body().getStudentUser().getStudentDepartment().getDeptId());
                    editor.putString("course", ""+response.body().getStudentUser().getStudentCourse().getCoursename());
                    editor.putString("role", "Student");

                    if(response.body().getStudentSub() != null){
                        editor.putString("sub", "on");
//                        editor.putString("subStatus", ""+response.body().getSubTypeStudent().getStatus());
//                        editor.putString("subType", ""+response.body().getSubTypeStudent().getSub_type());
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
            public void onFailure(Call<StudentLoginJSONResponse> call, Throwable t) {

            }

        });
    }

}
