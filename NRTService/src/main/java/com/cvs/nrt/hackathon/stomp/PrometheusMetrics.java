package com.cvs.nrt.hackathon.stomp;

import java.util.List;

import com.cvs.nrt.hackathon.model.NRTObject_Cassandra;

public class PrometheusMetrics {
	
	List<NRTObject_Cassandra> claims;
	
	public PrometheusMetrics(List<NRTObject_Cassandra> claims) {
		this.claims = claims;
	}

	public double getTotalRejects() {
		return 3;
	}
}
