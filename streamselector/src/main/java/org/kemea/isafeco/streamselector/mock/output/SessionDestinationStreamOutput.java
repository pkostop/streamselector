package org.kemea.isafeco.streamselector.mock.output;

import com.google.gson.annotations.SerializedName;

import jakarta.json.bind.annotation.JsonbProperty;

public class SessionDestinationStreamOutput {

	@SerializedName(value = "session_decryption_key")
	@JsonbProperty("session_decryption_key")
	private String sessionDecryptionKey;

	@JsonbProperty("session_destination_service_ip")
	@SerializedName(value = "session_destination_service_ip")
	private String sessionDestinationServiceIp;

	@JsonbProperty("session_destination_service_port")
	@SerializedName(value = "session_destination_service_port")
	private int sessionDestinationServicePort;

	@JsonbProperty("session_destination_service_protocol")
	@SerializedName(value = "session_destination_service_protocol")
	private String sessionDestinationServiceProtocol;

	@JsonbProperty("session_sdp")
	@SerializedName(value = "session_sdp")
	private String sessionSdp;

	// Getters and Setters
	public String getSessionDecryptionKey() {
		return sessionDecryptionKey;
	}

	public void setSessionDecryptionKey(String sessionDecryptionKey) {
		this.sessionDecryptionKey = sessionDecryptionKey;
	}

	public String getSessionDestinationServiceIp() {
		return sessionDestinationServiceIp;
	}

	public void setSessionDestinationServiceIp(String sessionDestinationServiceIp) {
		this.sessionDestinationServiceIp = sessionDestinationServiceIp;
	}

	public int getSessionDestinationServicePort() {
		return sessionDestinationServicePort;
	}

	public void setSessionDestinationServicePort(int sessionDestinationServicePort) {
		this.sessionDestinationServicePort = sessionDestinationServicePort;
	}

	public String getSessionDestinationServiceProtocol() {
		return sessionDestinationServiceProtocol;
	}

	public void setSessionDestinationServiceProtocol(String sessionDestinationServiceProtocol) {
		this.sessionDestinationServiceProtocol = sessionDestinationServiceProtocol;
	}

	public String getSessionSdp() {
		return sessionSdp;
	}

	public void setSessionSdp(String sessionSdp) {
		this.sessionSdp = sessionSdp;
	}

}
