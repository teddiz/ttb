package com.example.newttb.Networking;

import com.example.newttb.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceClient {
    @POST("exec")
    @FormUrlEncoded
    Call<LoginResponse> loginEng(@Field(value = "sheetName",encoded = true)String sheetName,
                                 @Field(value = "action",encoded = true)String action,
                                 @Field(value = "kodeEng",encoded = true)String kodeEng,
                                 @Field(value = "pass",encoded = true)String pass);
}
