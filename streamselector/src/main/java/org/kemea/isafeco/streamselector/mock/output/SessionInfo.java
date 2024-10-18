package org.kemea.isafeco.streamselector.mock.output;

import java.time.LocalDateTime;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;

public class SessionInfo {
	@JsonbProperty("id")
	private double id;

	@JsonbProperty("sdp")
	private String sdp;

	@JsonbProperty("encryption_key")
	private String encryptionKey;

	@JsonbProperty("decryption_key")
	private String decryptionKey;

	@JsonbProperty("status")
	private String status; // Should be an enum in a real application

	@JsonbProperty("created_at")
	@JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
	private LocalDateTime createdAt;

	@JsonbProperty("updated_at")
	@JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
	private LocalDateTime updatedAt;

	// Getters and Setters
	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getSdp() {
		return sdp;
	}

	public void setSdp(String sdp) {
		this.sdp = sdp;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public String getDecryptionKey() {
		return decryptionKey;
	}

	public void setDecryptionKey(String decryptionKey) {
		this.decryptionKey = decryptionKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
