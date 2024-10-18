package org.kemea.isafeco.streamselector.mock.output;

import com.google.gson.annotations.SerializedName;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

public class SessionSourceStreamOutput {
	@SerializedName("session_source_service_protocol")
	@JsonbProperty(value = "session_source_service_protocol")
	String sessionSourceServiceProtocol;
	@SerializedName("session_source_service_ip")
	@JsonbProperty(value = "session_source_service_ip")
	String sessionSourceServiceIp;
	@SerializedName("session_source_service_port")
	@JsonbProperty(value = "session_source_service_port")
	Integer sessionSourceServicePort;
	@SerializedName("session_id")
	@JsonbProperty(value = "session_id")
	Integer sessionId;
	@SerializedName("session_encryption_key")
	@JsonbProperty(value = "session_encryption_key")
	String sessionEncryptionKey;
	@JsonbTransient
	String sessionSDP;

	public String getSessionSourceServiceProtocol() {
		return sessionSourceServiceProtocol;
	}

	public void setSessionSourceServiceProtocol(String sessionSourceServiceProtocol) {
		this.sessionSourceServiceProtocol = sessionSourceServiceProtocol;
	}

	public String getSessionSourceServiceIp() {
		return sessionSourceServiceIp;
	}

	public void setSessionSourceServiceIp(String sessionSourceServiceIp) {
		this.sessionSourceServiceIp = sessionSourceServiceIp;
	}

	public Integer getSessionSourceServicePort() {
		return sessionSourceServicePort;
	}

	public void setSessionSourceServicePort(Integer sessionSourceServicePort) {
		this.sessionSourceServicePort = sessionSourceServicePort;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionEncryptionKey() {
		return sessionEncryptionKey;
	}

	public void setSessionEncryptionKey(String sessionEncryptionKey) {
		this.sessionEncryptionKey = sessionEncryptionKey;
	}

	public String getSessionSDP() {
		return sessionSDP;
	}

	public void setSessionSDP(String sessionSDP) {
		this.sessionSDP = sessionSDP;
	}

}
