package com.cvs.nrt.hackathon.stomp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List; 
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.cvs.nrt.hackathon.controller.NRTDataMapper;
import com.cvs.nrt.hackathon.model.DashboardData;
import com.cvs.nrt.hackathon.model.NRTObject_Cassandra;
import com.cvs.nrt.hackathon.repository.NRTCassandraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import io.micrometer.core.instrument.MeterRegistry;

@Service 
public class NRTStreamService {
 
	@Autowired
	NRTCassandraRepository nrtRepository;
	
	private final MeterRegistry meterRegistry;	
	private final AtomicLong totalRejectsGauge;
	private final AtomicLong plan_limits_exceeded_guage;
	private final AtomicLong host_processing_error_gauge;
	
	//Initialize the Meter Registry
	public NRTStreamService(MeterRegistry registry) {
		this.meterRegistry = registry;	
		totalRejectsGauge = meterRegistry.gauge("rxclaim_total_rejects", new AtomicLong(0));
		plan_limits_exceeded_guage = meterRegistry.gauge("rxclaim_reject_plan_limits_exceeded", new AtomicLong(0));
		host_processing_error_gauge = meterRegistry.gauge("rxclaim_reject_host_processing_error", new AtomicLong(0));
	}
	
	public String streamRxClaimCassandraData() {
		String _retJSON = "";
		try {
			LocalDate to = LocalDate.now();
			LocalDate from = to.minusDays(180);
			LocalDate earlierFrom = to.minusDays(360);
			 
			String a = (from.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			String b = (earlierFrom.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();
			List<NRTObject_Cassandra> earlierClaims = new ArrayList<NRTObject_Cassandra>();
			
			nrtRepository.findClaimsGreaterThanDate(a).forEach(claims::add);
			nrtRepository.findClaimsBetweenDates(b, a).forEach(earlierClaims::add);
			if (claims.isEmpty()) {
				_retJSON =  "";
			}
			NRTDataMapper _mapper = new NRTDataMapper();
			DashboardData data = _mapper.getDashBoardCassandraData(claims, earlierClaims);			
			ObjectMapper mapper = new ObjectMapper();
			_retJSON = mapper.writeValueAsString(data);
			
			//Calculate Metrics
			 totalRejectsGauge.set(claims.stream().filter(c -> (c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))).count());
			 plan_limits_exceeded_guage.set(claims.stream().filter(c -> (
					 c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))
					 && c.getRejectCodes()!=null && c.getRejectCodes().size()>0
					 && c.getRejectCodes().contains("76 : PLAN LIMITATIONS EXCEEDED")
					 ).count());
			 host_processing_error_gauge.set(claims.stream().filter(c -> (
					 c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))
					 && c.getRejectCodes()!=null && c.getRejectCodes().size()>0
					 && c.getRejectCodes().contains("99 : Host Processing Error    ")
					 ).count());
			
		}catch (Exception e) {
			e.printStackTrace();
			_retJSON = "";
		}
		return _retJSON;
	}	

}
