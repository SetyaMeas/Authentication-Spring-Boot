package com.example.Authentication.Utils;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

	public Map<String, Object> errorRes(String msg) {
		Map<String, Object> jsonData = new HashMap<>();

		jsonData.put("error", msg);

		return jsonData;
	}

	public Map<String, Object> sucessRes(String key, Object value) {
		Map<String, Object> jsonData = new HashMap<>();

		jsonData.put(key, value);

		return jsonData;
	}
}
