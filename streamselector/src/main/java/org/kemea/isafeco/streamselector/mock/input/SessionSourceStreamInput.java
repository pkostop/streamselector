package org.kemea.isafeco.streamselector.mock.input;

import jakarta.json.bind.annotation.JsonbProperty;

public class SessionSourceStreamInput {
	@JsonbProperty(value = "application_id")
	Long applicationId;

	@JsonbProperty(value = "user_login")
	String userLogin;

	@JsonbProperty(value = "userPassword")
	String user_password;

	@JsonbProperty(value = "sessionSdp")
	String sessionSdp;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getSessionSdp() {
		return sessionSdp;
	}

	public void setSessionSdp(String sessionSdp) {
		this.sessionSdp = sessionSdp;
	}

}
