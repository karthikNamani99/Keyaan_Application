package com.example.keyaanapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keyaanapplication.models.LoginResponse;
import com.example.keyaanapplication.R;
import com.example.keyaanapplication.retrofit.Api;
import com.example.keyaanapplication.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginPage";

    EditText userid,passwordText;
    Button btn_login;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_main);

        api = ApiUtils.getAPIService();

        userid = findViewById(R.id.input_userid);
        passwordText = findViewById(R.id.input_password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_login:

                String email = userid.getText().toString();
                String password = passwordText.getText().toString();

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userid.setError("enter a valid email address");
                }
                else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                    passwordText.setError("between 4 and 10 alphanumeric characters");

                }
                else {
//                    Intent intent=new Intent(getApplicationContext(),Login_Page_main.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    verifydata(email, password);
                }

                break;
        }
    }

    private void verifydata(String email,String password) {
        api.loginuser(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.i(TAG, "post submitted to API." + response.body().toString());
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {


                Toast.makeText(getApplicationContext(), "Please Enter Valid Login Details", Toast.LENGTH_LONG).show();
//                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_login.setEnabled(true);

    }
}
