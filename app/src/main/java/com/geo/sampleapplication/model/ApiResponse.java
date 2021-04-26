package com.geo.sampleapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApiResponse{

	@SerializedName("ApiResponse")
	private List<ApiResponseItem> apiResponse;

	public List<ApiResponseItem> getApiResponse(){
		return apiResponse;
	}
}