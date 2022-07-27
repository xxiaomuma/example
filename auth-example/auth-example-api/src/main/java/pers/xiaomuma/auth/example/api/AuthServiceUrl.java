package pers.xiaomuma.auth.example.api;


public interface AuthServiceUrl {

	String SERVICE_NAME = "auth-example";

	interface Auth {
		String GET_TOKEN = "/oauth/token";
		String CHECK_TOKEN_URL = "/oauth/check_token";
	}

}
