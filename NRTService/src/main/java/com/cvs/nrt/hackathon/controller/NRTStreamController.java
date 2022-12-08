package com.cvs.nrt.hackathon.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvs.nrt.hackathon.model.DashboardData;
import com.cvs.nrt.hackathon.model.MedicineDashboardData;
import com.cvs.nrt.hackathon.model.NRTObject_Cassandra;
import com.cvs.nrt.hackathon.model.ProductName;
import com.cvs.nrt.hackathon.repository.NRTCassandraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = {"http://localhost:4200","http://hpz155cg01043bq.corp.cvscaremark.com:4200"})
@RestController
@RequestMapping("/nrt")
public class NRTStreamController {
	
	@Autowired
	NRTCassandraRepository claimRepository;
	
	@GetMapping("/last30daysRaw")
	public ResponseEntity<List<NRTObject_Cassandra>> findClaimsRawInLast30Days(){
		try {
			LocalDate to = LocalDate.now();
			LocalDate from = to.minusDays(180);
			 
			String a = (from.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			
			//List<RxClaimObject> claims = new ArrayList<RxClaimObject>();
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();
			
			claimRepository.findClaimsGreaterThanDate(a).forEach(claims::add);
			if (claims.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(claims, HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/last60daysRaw")
	public ResponseEntity<List<NRTObject_Cassandra>> findClaimsRawInLast60Days(){
		try {
			LocalDate to = LocalDate.now();
			LocalDate from = to.minusDays(360);
			 
			String a = (from.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			
			//List<RxClaimObject> claims = new ArrayList<RxClaimObject>();
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();
			
			claimRepository.findClaimsGreaterThanDate(a).forEach(claims::add);
			if (claims.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(claims, HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/last30days")
	public ResponseEntity<DashboardData> findClaimsInLast30Days(){
		try {
			LocalDate to = LocalDate.now();
			LocalDate from = to.minusDays(180);
			LocalDate earlierFrom = to.minusDays(360);
			 
			String a = (from.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			String b = (earlierFrom.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			
			//List<RxClaimObject> claims = new ArrayList<RxClaimObject>();
			//List<RxClaimObject> earlierClaims = new ArrayList<RxClaimObject>();
			
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();
			List<NRTObject_Cassandra> earlierClaims = new ArrayList<NRTObject_Cassandra>();
			
			claimRepository.findClaimsGreaterThanDate(a).forEach(claims::add);
			claimRepository.findClaimsBetweenDates(b, a).forEach(earlierClaims::add);
			if (claims.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			NRTDataMapper _mapper = new NRTDataMapper();
			return new ResponseEntity<>(_mapper.getDashBoardCassandraData(claims, earlierClaims),HttpStatus.OK);
			
		}catch (Exception e) {e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/last60days")
	public ResponseEntity<DashboardData> findClaimsInLast60Days(){
		try {
			LocalDate to = LocalDate.now();
			LocalDate from = to.minusDays(180);			 
			LocalDate earlierFrom = to.minusDays(360);
			 
			String a = (from.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			String b = (earlierFrom.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			
			//List<RxClaimObject> claims = new ArrayList<RxClaimObject>();
			//List<RxClaimObject> earlierClaims = new ArrayList<RxClaimObject>();
			
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();
			List<NRTObject_Cassandra> earlierClaims = new ArrayList<NRTObject_Cassandra>();
			
			claimRepository.findClaimsGreaterThanDate(a).forEach(claims::add);
			claimRepository.findClaimsBetweenDates(b, a).forEach(earlierClaims::add);
			if (claims.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			NRTDataMapper _mapper = new NRTDataMapper();
			return new ResponseEntity<>(_mapper.getDashBoardCassandraData(claims, earlierClaims),HttpStatus.OK);
			
		}catch (Exception e) {e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@GetMapping("/findClaimsByProdId/{id}")
	public ResponseEntity<MedicineDashboardData> findClaimsByProdId(@PathVariable("id") String productKey){
		try {			
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();			
			claimRepository.findClaimsUsingDrug(productKey).forEach(claims::add); 
			if (claims.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			NRTDataMapper _mapper = new NRTDataMapper();
			return new ResponseEntity<>(_mapper.getMedicineDashBoardData(claims),HttpStatus.OK);
			
		}catch (Exception e) {e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAllDrugs")
	public ResponseEntity<String> findAllDrugs(){
		List<String> drugs = new ArrayList<String>();
		List<ProductName> products = new ArrayList<ProductName>();
		String _retJSON = "";
		try {			
			List<NRTObject_Cassandra> claims = new ArrayList<NRTObject_Cassandra>();				
			claimRepository.findAll().forEach(claims::add);
			 
			if (claims.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			claims.forEach(c ->{
				if(!drugs.contains(c.getTCD_Sbm_Product_Name())) {
					drugs.add(c.getTCD_Sbm_Product_Name());
					ProductName p = new ProductName();
					p.setProductId(c.getTCD_Sbm_Product_ID());
					p.setProductKey(c.getTCD_Product_Key());
					p.setProductName(c.getTCD_Sbm_Product_Name());
					products.add(p);
				}
			});			 
			
			ObjectMapper mapper = new ObjectMapper();
			_retJSON = mapper.writeValueAsString(products);
			
			return new ResponseEntity<String>(_retJSON,HttpStatus.OK);
			
		}catch (Exception e) {e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}
