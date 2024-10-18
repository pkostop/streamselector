package org.kemea.isafeco.streamselector.mock.input;

import com.google.gson.annotations.SerializedName;

import jakarta.json.bind.annotation.JsonbProperty;

public class SessionDestinationStreamInput {
	@JsonbProperty(value = "session_id")
	@SerializedName(value = "session_id")
	Integer sessionId;

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

}
