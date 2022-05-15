package com.example.oatsv5;

import com.example.oatsv5.Models.Bookmarks.BookmarkedJSONResponse;
import com.example.oatsv5.Models.Bookmarks.CreateBookmarkJSONResponse;
import com.example.oatsv5.Models.Bookmarks.DeleteBookmarkJSONResponse;
import com.example.oatsv5.Models.Courses.CoursesJSONResponse;
import com.example.oatsv5.Models.Departments.DepartmentJSONResponse;
import com.example.oatsv5.Models.LoginGuestResult;
import com.example.oatsv5.Models.RegisterGuestResult;
import com.example.oatsv5.Models.StudentAuth.StudentLoginJSONResponse;
import com.example.oatsv5.Models.StudentAuth.StudentRegisterJSONResponse;
import com.example.oatsv5.Models.Subscription.FindSubscription;
import com.example.oatsv5.Models.Thesis.ThesisFilterDeptJSONResponse;
import com.example.oatsv5.Models.Thesis.ThesisJSONResponse;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/api/thesis")
    Call<ThesisJSONResponse> getThesesResult();

    //thesis filtered by student department
    @GET("/api/thesis/filterDept/{id}")
    Call<ThesisFilterDeptJSONResponse> getThesisFilterDept(@Path("id") String deptId);


    //course and department
    @GET("/api/department")
    Call<DepartmentJSONResponse>getDepartments();

    @GET("/api/course")
    Call<CoursesJSONResponse>getCourses(@Query("department.id") String deptId);


    //auth
    @POST("user/login")
    Call<StudentLoginJSONResponse> executeLoginStudent(@Body HashMap<String, String> map);

    @POST("user/register")
    Call<StudentRegisterJSONResponse> executeRegisterStudent (@Body HashMap<String, String> map);

    @GET("user/inforMobile")
    Call<StudentLoginJSONResponse> getStudentUser(@Header("Authorization") String authToken);

    @POST("guest/login")
    Call<LoginGuestResult> executeLogin(@Body HashMap<String, String> map);

    @POST("guest/register")
    Call<RegisterGuestResult> executeRegister (@Body HashMap<String, String> map);

    @GET("/guest/inforMobile")
    Call<LoginGuestResult> getGuestUser(@Header("Authorization") String authToken);


    //bookmarks
    @GET("api/bookmark/{id}")
    Call<BookmarkedJSONResponse> getBookmarkResult(@Path("id") String userId);

    @POST("api/bookmark/new")
    Call<CreateBookmarkJSONResponse> executeBookmark(@Body HashMap<String, String> map);

    @DELETE("api/bookmark/delete/{id}")
    Call<DeleteBookmarkJSONResponse> executeDeleteBookmark(@Path("id") String BookmarkId);

    @POST("api/download/log")
    Call<Void> PostDataIntoServer(@Body HashMap<String, String> map);

    @POST("api/view/log")
    Call<Void> PostDataIntoViewsServer(@Body HashMap<String, String> map);

    @GET("api/subscription/{id}")
    Call<FindSubscription> getFindSubscription(@Path("id") String userId);

}
