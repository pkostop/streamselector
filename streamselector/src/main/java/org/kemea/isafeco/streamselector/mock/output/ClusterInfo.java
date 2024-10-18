package org.kemea.isafeco.streamselector.mock.output;

import jakarta.json.bind.annotation.JsonbProperty;

public class ClusterInfo {
	@JsonbProperty("cluster_id")
	private double clusterId;

	@JsonbProperty("contract_id")
	private long contractId;

	// Getters and Setters
	public double getClusterId() {
		return clusterId;
	}

	public void setClusterId(double clusterId) {
		this.clusterId = clusterId;
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}
}
