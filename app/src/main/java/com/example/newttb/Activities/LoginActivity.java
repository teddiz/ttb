package com.example.newttb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newttb.Model.LoginResponse;
import com.example.newttb.Networking.ServiceClient;
import com.example.newttb.Networking.ServiceGenerator;
import com.example.newttb.R;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etKodeEng,etPass;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etKodeEng=findViewById(R.id.et_code_eng);
        etPass=findViewById(R.id.editTextTextPassword);

        pd=new ProgressDialog(this);

        boolean cekLogin=getSharedPreferences("Session",MODE_PRIVATE).getBoolean("login",false);
        if(cekLogin){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }


    }

    public void login(View view) {
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        if(etKodeEng.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this,"Kode Eng Tidak Boleh osong",Toast.LENGTH_SHORT).show();
            return;

        }
        if(etPass.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this,"Password Tidak Boleh osong",Toast.LENGTH_SHORT).show();
            return;

        }
        /*String Kode_Eng=etKodeEng.getText().toString().trim();
        String Pass_Eng=etPass.getText().toString().trim();*/
        final String Kode_Eng=etKodeEng.getText().toString().trim().toUpperCase(Locale.ROOT);
        final String pass_Eng=etPass.getText().toString().trim();

        ServiceClient service= ServiceGenerator.createService(ServiceClient.class);
        Call<LoginResponse> requestLogin=service.loginEng("loginEng","login",Kode_Eng,pass_Eng);
        requestLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if(response.body().getHasil().equals("success")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    SharedPreferences sp=getSharedPreferences("session",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("login",true);
                    editor.putBoolean("kodeEng", Boolean.parseBoolean(Kode_Eng));
                    editor.putBoolean("pass", Boolean.parseBoolean(pass_Eng));
                    editor.putString("namaEng",response.body().getNamaEng());

                    editor.commit();

                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login Gagal",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this,"Koneksi Error",Toast.LENGTH_SHORT).show();


            }
        });



    }
}