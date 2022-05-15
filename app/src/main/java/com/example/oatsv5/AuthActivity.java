//package com.example.oatsv5;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.example.oatsv5.Constants.Constants;
//import com.example.oatsv5.Models.Courses.Courses;
//import com.example.oatsv5.Models.Courses.CoursesJSONResponse;
//import com.example.oatsv5.Models.Departments.DepartmentJSONResponse;
//import com.example.oatsv5.Models.Departments.Departments;
//import com.example.oatsv5.Models.GuestUser;
//import com.example.oatsv5.Models.LoginGuestResult;
//import com.example.oatsv5.Models.RegisterGuestResult;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class AuthActivity extends AppCompatActivity {
//
//    private Retrofit retrofit;
//    private RetrofitInterface retrofitInterface;
////    private String BASE_URL = "http://192.168.100.141:8000/";
//    String token;
//    Button btnStudent;
//    Button btnGuest;
//    Button btnAdmin;
//    List<Departments>departmentsList;
//    private List<GuestUser> guestUserList;
//    private Context mContext;
//    List<Courses>coursesList;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_auth);
//        retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
////        addListenerOnButton();
////        addListenerOnButton2();
////        addListenerOnButton3();
//        getSupportActionBar().hide();
//
//        findViewById(R.id.btnlogGuest).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleLoginGuestDialog();
//
//            }
//        });
//        findViewById(R.id.btnRegGuest).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleRegGuestDialog();
//            }
//        });
//
//        findViewById(R.id.btnLogStudent).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleLoginStudentDialog();
//            }
//        });
//        findViewById(R.id.btnRegStudent).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleRegStudentDialog();
//            }
//        });
//
//
//    }
//
//
//
//
//    private void handleLoginStudentDialog() {
//
//    }
//
//
//    private void handleRegGuestDialog() {
//        View view = getLayoutInflater().inflate(R.layout.register_guest_dialog, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view).show();
//
//        Button regGuest = view.findViewById(R.id.regG);
//        EditText regGfname = view.findViewById(R.id.editRegGFname);
//        EditText regGlname = view.findViewById(R.id.editRegGLname);
//        EditText regGcontact = view.findViewById(R.id.editRegGContact);
//        EditText regGprof = view.findViewById(R.id.editRegGProf);
//        EditText regGcomp = view.findViewById(R.id.editRegGComp);
//        EditText regGcompadd = view.findViewById(R.id.editRegGCompAdd);
//        EditText regGmail = view.findViewById(R.id.editRegGEmail);
//        EditText regGPass = view.findViewById(R.id.editRegGPass);
//
//        regGuest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("guest_fname", regGfname.getText().toString());
//                map.put("guest_lname", regGlname.getText().toString());
//                map.put("guest_contact", regGcontact.getText().toString());
//                map.put("guest_profession", regGprof.getText().toString());
//                map.put("guest_company", regGcomp.getText().toString());
//                map.put("guest_company_address", regGcompadd.getText().toString());
//                map.put("guest_mail", regGmail.getText().toString());
//                map.put("passwords", regGPass.getText().toString());
//
//                Call<RegisterGuestResult> call = retrofitInterface.executeRegister(map);
//                call.enqueue(new Callback<RegisterGuestResult>() {
//                    private Call<RegisterGuestResult> call;
//                    private Response<RegisterGuestResult> response;
//
//                    @Override
//                    public void onResponse(Call<RegisterGuestResult> call, Response<RegisterGuestResult> response) {
//                        this.call = call;
//                        this.response = response;
//                        if (response != null && response.isSuccessful()) {
//                            Toast.makeText(AuthActivity.this, "Registered Successfully !", Toast.LENGTH_LONG).show();
//                        } else if (response.code() == 500) {
//                            Toast.makeText(AuthActivity.this, "Account Already Registered!", Toast.LENGTH_LONG).show();
//                        } else if (response.code() == 400) {
//                            Toast.makeText(AuthActivity.this, response.message(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<RegisterGuestResult> call, Throwable t) {
//                        Toast.makeText(AuthActivity.this, t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//
//                });
//
//            }
//
//        });
//
//
//
//    }
//
//
//    private void handleLoginGuestDialog() {
//        View view = getLayoutInflater().inflate(R.layout.login_guest_dialog, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view).show();
//
//        Button loginBtn = view.findViewById(R.id.loginGuest);
//        EditText GemailEdit = view.findViewById(R.id.editLogGEmail);
//        EditText GpasswordEdit = view.findViewById(R.id.editLogGPassword);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("guest_mail", GemailEdit.getText().toString());
//                map.put("guest_password", GpasswordEdit.getText().toString());
//
//                Call<LoginGuestResult> call = retrofitInterface.executeLogin(map);
//                call.enqueue(new Callback<LoginGuestResult>() {
//                    @Override
//                    public void onResponse(Call<LoginGuestResult> call, Response<LoginGuestResult> response) {
//                        if (response != null && response.isSuccessful()) {
//                            LoginGuestResult result = response.body();
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AuthActivity.this);
//                            builder1.setTitle(result.getEmail());
//                            builder1.setMessage(result.getPassword());
//                            builder1.show();
//                            Toast.makeText(AuthActivity.this, response.body().getToken(), Toast.LENGTH_LONG).show();
//                            token = response.body().getToken();
//                            getGuestUser();
//                            startActivity(new Intent(AuthActivity.this, DashboardActivity.class));
//                            finish();
//                        } else if (response.code() == 500) {
//                            Toast.makeText(AuthActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginGuestResult> call, Throwable t) {
//                        Toast.makeText(AuthActivity.this, t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            private void getGuestUser(){
//                Call<LoginGuestResult> call = retrofitInterface.getGuestUser(token);
//                call.enqueue(new Callback<LoginGuestResult>() {
//                    @Override
//                    public void onResponse(Call<LoginGuestResult> call, Response<LoginGuestResult> response) {
//                        if (response.isSuccessful()){
//
//                            Toast.makeText(AuthActivity.this, response.body().getGuestUser().getGuest_fname(),Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(AuthActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginGuestResult> call, Throwable t) {
//
//                    }
//
//                });
//            }
//        });
//    }
//
//
//    private void handleRegStudentDialog() {
//        View view = getLayoutInflater().inflate(R.layout.register_user_dialog, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view).show();
////        Spinner regUDept;
//        ArrayList<String> deptName = new ArrayList<String>();
//        Button regUser = view.findViewById(R.id.btn_regUser);
//        EditText regUfname = view.findViewById(R.id.editRegUFname);
//        EditText regUlname = view.findViewById(R.id.editRegULname);
//        EditText regTUPID = view.findViewById(R.id.editRegUTUPID);
//        EditText regUContact = view.findViewById(R.id.editRegUContact);
//
////        regUDept = (Spinner) findViewById(R.id.RegUDept);
//        Spinner regUDept = view.findViewById(R.id.RegUDept);
//        Spinner regUCourse = view.findViewById(R.id.RegUCourse);
//        EditText regUEmail = view.findViewById(R.id.editRegUemail);
//        EditText regUPass = view.findViewById(R.id.editRegUPassword);
//
//
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//        Call<DepartmentJSONResponse> call = retrofitInterface.getDepartments();
//        call.enqueue(new Callback<DepartmentJSONResponse>() {
//            @Override
//            public void onResponse(Call<DepartmentJSONResponse> call, Response<DepartmentJSONResponse> response) {
//                DepartmentJSONResponse departmentJSONResponse = response.body();
//                departmentsList = new ArrayList<>(Arrays.asList(departmentJSONResponse.getDepartments()));
////                Toast.makeText(AuthActivity.this,,Toast.LENGTH_SHORT).show();
//                final Departments[] depts = departmentJSONResponse.getDepartments();
//                String departs = "";
//                for (Departments arr : depts) {
//                    departs = departs + arr.getDeptname() + ", " + "";
//                    Toast.makeText(AuthActivity.this, departs, Toast.LENGTH_SHORT).show();
//
////                    try {
////                        JSONObject jsonObject = new JSONObject(String.valueOf(depts));
////                        departmentsList.add(new Departments("00", "----Select Dept----"));
////                        Toast.makeText(AuthActivity.this,, Toast.LENGTH_SHORT).show();
//////                        for (int i = 0; i < jsonObject.length(); i++) {
//////                            Departments departments = new Departments();
//////                            JSONObject jsonObject1 = jsonObject.getJSONObject(String.valueOf(i));
//////                            departments.set_id(jsonObject1.getString("_id"));
//////                            departments.setDeptname(jsonObject1.getString("deptname"));
//////                            departmentsList.add(departments);
//////                        }
////
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DepartmentJSONResponse> call, Throwable t) {
//
//            }
//        });
//
//
////
////
////        RetrofitInterface retrofitInterfacecourse = retrofit.create(RetrofitInterface.class);
////        Call<CoursesJSONResponse> callcourse = retrofitInterfacecourse.getCourses();
////      callcourse.enqueue(new Callback<CoursesJSONResponse>() {
////          @Override
////          public void onResponse(Call<CoursesJSONResponse> call, Response<CoursesJSONResponse> response) {
////              CoursesJSONResponse coursesJSONResponse = response.body();
////              coursesList = new ArrayList<>(Arrays.asList(coursesJSONResponse.getCourses()));
//////                Toast.makeText(AuthActivity.this,,Toast.LENGTH_SHORT).show();
////              final Courses[] crss = coursesJSONResponse.getCourses();
////              String crses = "";
////              for (Courses arr : crss) {
////                  crses = crses + arr.getCoursename() + ", " + "";
////                  Toast.makeText(AuthActivity.this, crses, Toast.LENGTH_SHORT).show();
////          }
////
////
////
////          @Override
////          public void onFailure(Call<CoursesJSONResponse> call, Throwable t) {
////
////          });
//
//
////ending bracket to
//    }
//}
//
//
//
//
//
////        call.enqueue(new Callback<DepartmentJSONResponse>() {
////            @Override
////            public void onResponse(Call<DepartmentJSONResponse> call, Response<DepartmentJSONResponse> response) {
////                if (response.isSuccessful()) {
////
//////                    Log.i("success", response.body().toString());
//////                    String getResponse = response.body().toString();
//////                    ArrayList<Departments> deptList = new ArrayList<Departments>();
//////                    Toast.makeText(AuthActivity.this, response.body().getDepartments().getDeptname(),Toast.LENGTH_SHORT).show();
//////
//////                    try {
//////                        JSONObject jsonObject = new JSONObject(getResponse);
//////                        deptList.add(new Departments("00","----Select Dept----"));
//////                        for (int i = 0; i < jsonObject.length(); i++) {
//////                            Departments departments = new Departments();
//////                            JSONObject jsonObject1 = jsonObject.getJSONObject(String.valueOf(i));
//////                            departments.set_id(jsonObject1.getString("_id"));
//////                            departments.setDeptname(jsonObject1.getString("deptname"));
//////                            deptList.add(departments);
//////                        }
//////                        for(int i=0;i<deptList.size();i++){
////                            deptName.add(deptList.get(i).getDeptname().toString());
////
////
////                    }
////
////       } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                    ArrayAdapter<String> spinStateAdapter =new ArrayAdapter<String>(AuthActivity.this, android.R.layout.simple_spinner_item,deptName);
////                    spinStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                    regUDept.setAdapter(spinStateAdapter);
////                    regUDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                        @Override
////                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////
////                        }
////
////                        @Override
////                        public void onNothingSelected(AdapterView<?> adapterView) {
////
////                        }
////                    });
//
//
////                }
////            }
//
////            @Override
////            public void onFailure(Call<DepartmentJSONResponse> call, Throwable t) {
////
////            }
////        });
////  }
////}
//
////        call.enqueue(new Callback<ThesisJSONResponse>() {
////            @Override
////            public void onResponse(Call<ThesisJSONResponse> call, Response<ThesisJSONResponse> response) {
////
////
////                if (response.isSuccessful()) {
////
////                    Log.i("success", response.body().toString());
////                    try {
////                        String getResponse = response.body().toString();
////                        List<MoreDepartments> deptList = new ArrayList<MoreDepartments>();
////                        JSONArray jsonArray = new JSONArray(getResponse);
////                        deptList.add(new MoreDepartments(-1, "----Select Dept----"));
////                        for (int i = 0; i < jsonArray.length(); i++) {
////                            MoreDepartments moreDepartments = new MoreDepartments();
////                            JSONObject jsonObject = jsonArray.getJSONObject(i);
////                            moreDepartments.set_id(jsonObject.getInt("_id"));
////                            moreDepartments.setDeptname(jsonObject.getString("deptname"));
////                        }
////                    } catch (JSONException ex) {
////                        ex.printStackTrace();
////
////                    }
////                }
////
////
//
//
//
//
//
//
//
//
//
//
////
////        Button btnGuest = findViewById(R.id.btnGuest);
////        btnGuest.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View g) {
////                Toast.makeText(getApplicationContext(),"Login As Guest",Toast.LENGTH_SHORT).show();
////                Intent guestlogin = new Intent(getApplicationContext(), GuestLogin.class);
////                startActivity(guestlogin);
////            }
////        });
////
////        Button btnAdmin = findViewById(R.id.btnAdmin);
////        btnAdmin.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View a) {
////                Toast.makeText(getApplicationContext(),"Login As Admin",Toast.LENGTH_SHORT).show();
////                Intent adminlogin = new Intent(getApplicationContext(), AdminLogin.class);
////                startActivity(adminlogin);
////            }
////        });
////    }
//
////    private void addListenerOnButton() {
////        final Context context = this;
////
////        btnStudent = (Button) findViewById(R.id.btnStudent);
////
////        btnStudent.setOnClickListener(new OnClickListener() {
////
////            @Override
////            public void onClick(View arg0) {
////
////                Intent intent = new Intent(context, StudentLogin.class);
////                startActivity(intent);
////                Toast.makeText(getApplicationContext(),"Login As Student",Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
////
////        private void addListenerOnButton2() {
////            final Context context = this;
////
////            btnGuest = (Button) findViewById(R.id.btnGuest);
////
////            btnGuest.setOnClickListener(new OnClickListener() {
////
////                @Override
////                public void onClick(View arg1) {
////
////                    Intent intent = new Intent(context, GuestLogin.class);
////                    startActivity(intent);
////                    Toast.makeText(getApplicationContext(),"Login As Guest",Toast.LENGTH_SHORT).show();
////                }
////
////            });
////        }
////
////    private void addListenerOnButton3() {
////
////        final Context context = this;
////
////        btnAdmin = (Button) findViewById(R.id.btnAdmin);
////
////        btnAdmin.setOnClickListener(new OnClickListener() {
////
////            @Override
////            public void onClick(View arg2) {
////
////                Intent intent = new Intent(context, AdminLogin.class);
////                startActivity(intent);
////                Toast.makeText(getApplicationContext(),"Login As Admin",Toast.LENGTH_SHORT).show();
////            }
////
////        });
//
