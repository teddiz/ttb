package com.example.newttb.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("hasil")
	private String hasil;

	@SerializedName("NamaEng")
	private String namaEng;

	public String getHasil(){
		return hasil;
	}

	public String getNamaEng(){
		return namaEng;
	}
}