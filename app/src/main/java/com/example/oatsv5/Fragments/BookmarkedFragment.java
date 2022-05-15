package com.example.oatsv5.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.oatsv5.Adapters.BookmarkedRecyclerAdapter;
import com.example.oatsv5.Adapters.ThesisRecyclerAdapter;
import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.Models.Bookmarks.BookmarkedJSONResponse;
import com.example.oatsv5.Models.Bookmarks.BookmarkedResult;
import com.example.oatsv5.Models.Thesis.ThesesResult;
import com.example.oatsv5.Models.Thesis.ThesisJSONResponse;
import com.example.oatsv5.R;
import com.example.oatsv5.RetrofitInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookmarkedFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    String token;
    private Context context;
    private SwipeRefreshLayout refreshLayout;
    List<BookmarkedResult> bookmarkedList;

    private SharedPreferences userPref;
    private String userId;

    public BookmarkedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookmarked, container, false);
        context = view.getContext();
        init();
        return view;
    }

    public void init(){
        userPref = context.getSharedPreferences("user", context.MODE_PRIVATE);
        userId = userPref.getString("id", "");
        System.out.println("ang user id ay : "+userId);
        recyclerView = view.findViewById(R.id.bookmarkedRecycleViewer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout = view.findViewById(R.id.swipeBookmarked);

        bookmarkedList();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bookmarkedList();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    public void bookmarkedList(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //call the interface
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<BookmarkedJSONResponse> call = retrofitInterface.getBookmarkResult(userId);


        call.enqueue(new Callback<BookmarkedJSONResponse>() {
            @Override
            public void onResponse(Call<BookmarkedJSONResponse> call, Response<BookmarkedJSONResponse> response) {

                BookmarkedJSONResponse bookmarkedJSONResponse = response.body();
                bookmarkedList = new ArrayList<>(Arrays.asList(bookmarkedJSONResponse.bookmarkedArray()));

                PutDataIntoRecyclerView(bookmarkedList);
            }
            public void onFailure(Call<BookmarkedJSONResponse> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<BookmarkedResult> bookmarkedList) {
        BookmarkedRecyclerAdapter bookmarkedRecyclerAdapter = new BookmarkedRecyclerAdapter(getContext(), bookmarkedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bookmarkedRecyclerAdapter);
    }
}
