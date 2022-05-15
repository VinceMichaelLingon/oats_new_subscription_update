package com.example.oatsv5.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.Models.Bookmarks.BookmarkedJSONResponse;
import com.example.oatsv5.Models.Bookmarks.BookmarkedResult;
import com.example.oatsv5.Models.Bookmarks.DeleteBookmarkJSONResponse;
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

public class BookmarkedRecyclerAdapter extends RecyclerView.Adapter<BookmarkedRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<BookmarkedResult> bookmarkedList;

    public BookmarkedRecyclerAdapter(Context mContext, List<BookmarkedResult> bookmarkedList){
        this.mContext = mContext;
        this.bookmarkedList= bookmarkedList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        v = layoutInflater.inflate(R.layout.bookmarked_files,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(bookmarkedList.get(position).getThesisDetails().getTitle());
        holder.published_at.setText(bookmarkedList.get(position).getThesisDetails().getPublishedAt().toString());
        holder.bookmarked_abstract.setText(bookmarkedList.get(position).getThesisDetails().getThesisAbstract().substring(0, 150)+"...");

        holder.btn_remove_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //call the interface
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                Call<DeleteBookmarkJSONResponse> call = retrofitInterface.executeDeleteBookmark(bookmarkedList.get(position).get_id());


                call.enqueue(new Callback<DeleteBookmarkJSONResponse>() {
                    @Override
                    public void onResponse(Call<DeleteBookmarkJSONResponse> call, Response<DeleteBookmarkJSONResponse> response) {

                        DeleteBookmarkJSONResponse deleteBookmarkJSONResponse = response.body();
                        new android.app.AlertDialog.Builder(mContext)
                                .setMessage(""+deleteBookmarkJSONResponse.getMsg())
                                .setPositiveButton("Ok", null)
                                .show();
                    }
                    public void onFailure(Call<DeleteBookmarkJSONResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookmarkedList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout thesisCard;
        private TextView title, published_at,bookmarked_abstract;
        private ImageButton btn_remove_bookmark;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.bookmarked_thesis_title);
            published_at = itemView.findViewById(R.id.bookmarked_published_at);
            bookmarked_abstract = itemView.findViewById(R.id.bookmarked_abstract);
            btn_remove_bookmark = itemView.findViewById(R.id.btn_remove_bookmark);
        }
    }
}
