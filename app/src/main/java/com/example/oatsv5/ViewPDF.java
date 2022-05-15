package com.example.oatsv5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.oatsv5.Constants.Constants;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPDF extends AppCompatActivity {


    private String pdfBase64, title, thesisId;
    private URL url = null;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        PDFView pdfView = findViewById(R.id.pdfView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("pdf", Context.MODE_PRIVATE);
        pdfBase64 = sharedPreferences.getString("pdf", "");

        String cleanPdf = pdfBase64.replace("data:application/pdf;base64,", "");
        byte[] decodedString = android.util.Base64.decode(cleanPdf, 0);


        pdfView.fromBytes(decodedString)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .linkHandler(new DefaultLinkHandler(pdfView))
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();

        FloatingActionButton download = findViewById(R.id.downloadPdf);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //call the interface
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pdf", Context.MODE_PRIVATE);
                String title = sharedPreferences.getString("title", "");
                String id = sharedPreferences.getString("id", "");
                String dept = sharedPreferences.getString("department","");

                System.out.println("thesisID"+id);

                String tryy = "try lang to";
                String postTitle = title.toString();
                String postId = id.toString();
                String postDept = dept.toString();


                HashMap<String, String> map = new HashMap<>();
                map.put("thesis_id",postId);
                map.put("thesis_title",postTitle);
//
                map.put("thesis_department",postDept);

                Call<Void> call = retrofitInterface.PostDataIntoServer(map);
                call.enqueue(new Callback<Void>() {
                    private Call<Void> call;
                    private Response<Void> response;
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        this.call = call;
                        this.response = response;
                        if (response != null) {

                            System.out.println("looged");
                            Toast.makeText(ViewPDF.this, "logged", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "not logged", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
                if (!checkStoragePermission()) {
                    requestStoragePermission();
                } else {
                    startDownloading();
                }
            }
        });
    }

    private void startDownloading() {
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("pdf", Context.MODE_PRIVATE);
        pdfBase64 = sharedPreferences.getString("pdf", "");

        String cleanPdf = pdfBase64.replace("data:application/pdf;base64,", "");
        byte[] decodedString = android.util.Base64.decode(cleanPdf, 0);

        File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File dir = new File (filepath.getAbsoluteFile()+"/OATS_FILES/");
        dir.mkdir();
        File file = new File(dir, "OATS-"+System.currentTimeMillis()+".pdf");

        try {
            outputStream  = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(decodedString);
            outputStream.write(decodedString);
            Toast.makeText(getApplicationContext(), "File downloaded" , Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkStoragePermission(){
        boolean res = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return res;
    }

    @SuppressWarnings("deprecation")
    public void requestStoragePermission(){
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }
}