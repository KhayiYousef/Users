package com.example.Mirco.services.Response;

public class AuthResponse {

    private final String token;
    
    private final String id;

    public AuthResponse(String token,String id) {
        this.token = token;
        this.id = id;
    }

    
    public String getId() {
		return id;
	}


	public String getToken() {
        return token;
    }
}

