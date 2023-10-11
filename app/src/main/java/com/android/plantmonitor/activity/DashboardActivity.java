package com.android.plantmonitor.activity;
import retrofit2.*;
import  okhttp3.*;
import android.os.StrictMode;
import com.android.plantmonitor.data.ApiService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.plantmonitor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.converter.scalars.ScalarsConverterFactory;
public class DashboardActivity extends AppCompatActivity {
    private Button button;

    private void getRequestRetrofit() throws IOException{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = "https://git.eclipse.org/r/";
        url = "http://127.0.0.1";
        url = "http://www.appdomain.com/users/";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(url)
                .build();
        ApiService service = retrofit.create(ApiService.class);


        retrofit2.Call<String> stringCall = service.getGreeting();
        stringCall.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                Log.i("Response", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("onSuccess", response.body().toString());
                    } else {
                        Log.e("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call,Throwable t) {
                Log.e("","Athavan Failure GET request :" + t.toString() );
            }
        });
    }
    public void whenGetRequest_thenCorrect() throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.d("", "whenGetRequest_thenCorrect: send a http get request");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1")
                .build();

        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response.code());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        button = findViewById(R.id.button_dashboard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    whenGetRequest_thenCorrect();
                    getRequestRetrofit();
                }

                catch(IOException e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);

                startActivity(intent);
            }
        });



    }

}

//<!--        android:networkSecurityConfig="@xml/network_security_config"-->