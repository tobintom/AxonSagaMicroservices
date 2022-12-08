package com.cvs.nrt.hackathon.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map; 
import java.util.function.Function;
import java.util.stream.Collectors;

import com.cvs.nrt.hackathon.model.DashboardData;
import com.cvs.nrt.hackathon.model.MedicineDashboardData;
import com.cvs.nrt.hackathon.model.MedicineTop;
import com.cvs.nrt.hackathon.model.NRTKafkaObject;
import com.cvs.nrt.hackathon.model.NRTObject_Cassandra;
import com.cvs.nrt.hackathon.model.PolarChartData;
import com.cvs.nrt.hackathon.model.TableData;
import com.cvs.nrt.hackathon.model.TopgridValues;
import com.cvs.nrt.hackathon.model.TotalBarChart;
import com.cvs.nrt.hackathon.model.TotalBarChart.ToolBarData; 



public class NRTDataMapper {

	int itotal=0;
    int iapproved = 0;
    int idenied = 0;
    double total = 0.000;
		
	public  DashboardData getDashBoardCassandraData(List<NRTObject_Cassandra> claims,List<NRTObject_Cassandra> earlierClaims) {
		DashboardData data = new DashboardData();
		try {
		if(claims!=null && claims.size()>0) {
			
			//Top Grid			
			TopgridValues totalClaims = new TopgridValues();
			totalClaims.setCurrent(String.valueOf(claims.size()));
			totalClaims.setPrevious(String.valueOf(earlierClaims.size()));
			totalClaims.setChange(String.format("%.2f", 100 * ((claims.size() - earlierClaims.size()) / ((double)earlierClaims.size()!=0?(double)earlierClaims.size():1) ))+"%");		 
			data.addTopGridData(totalClaims);			
			
			long total = claims.size();
			long totalRejectedNum = claims.stream().filter(c -> (c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))).count();
			long totalApprovedNum = total-totalRejectedNum;
			
			long prevtotal = earlierClaims.size();
			long prevtotalRejectedNum = earlierClaims.stream().filter(c -> (c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))).count();
			long prevtotalApprovedNum = prevtotal-prevtotalRejectedNum;
			
			TopgridValues totalApproved = new TopgridValues();
			totalApproved.setCurrent(String.valueOf(totalApprovedNum));
			totalApproved.setPrevious(String.valueOf(prevtotalApprovedNum));
			totalApproved.setChange(String.format("%.2f", 100 * ((totalApprovedNum - prevtotalApprovedNum) / ((double)prevtotalApprovedNum!=0?(double)prevtotalApprovedNum:1)))+"%");
			data.addTopGridData(totalApproved);
			
			TopgridValues totalRejected = new TopgridValues();
			totalRejected.setCurrent(String.valueOf(totalRejectedNum));
			totalRejected.setPrevious(String.valueOf(prevtotalRejectedNum));
			totalRejected.setChange(String.format("%.2f", 100 * ((totalRejectedNum - prevtotalRejectedNum) / ((double)prevtotalRejectedNum!=0?(double)prevtotalRejectedNum:1)))+"%");
			data.addTopGridData(totalRejected);
			
			double totalSummitedAmt = claims.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Total_AmountDuouble).sum();
			double prevtotalSummitedAmt = earlierClaims.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Total_AmountDuouble).sum();
			TopgridValues totalSubmitted = new TopgridValues();
			
			if(totalSummitedAmt>100000)
				totalSubmitted.setCurrent("$"+ String.format("%.2fM",(totalSummitedAmt/1000000.0))); 
			else
				totalSubmitted.setCurrent("$"+ String.format("%.2f",(totalSummitedAmt))); 
			 
			if(prevtotalSummitedAmt>100000)
				totalSubmitted.setPrevious("$"+ String.format("%.2fM",(prevtotalSummitedAmt/1000000.0)));
			else
				totalSubmitted.setPrevious("$"+ String.format("%.2f",(prevtotalSummitedAmt)));
			
			totalSubmitted.setChange(String.format("%.2f", 100 * ((totalSummitedAmt - prevtotalSummitedAmt) / ((double)prevtotalSummitedAmt!=0?(double)prevtotalSummitedAmt:1)))+"%");
			data.addTopGridData(totalSubmitted);
			
			double totalApprovedAmt = claims.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Patient_Pay_AmtDouble).sum();
			double prevtotalApprovedAmt = earlierClaims.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Patient_Pay_AmtDouble).sum();
			TopgridValues totalApprovedC = new TopgridValues();
			
			if(totalApprovedAmt>100000)
				totalApprovedC.setCurrent("$"+ String.format("%.2fM",(totalApprovedAmt/1000000.0)));
			else
				totalApprovedC.setCurrent("$"+ String.format("%.2f",(totalApprovedAmt)));
				
			 
			//totalApprovedC.setPrevious("$"+ String.format("%.2fM",(prevtotalApprovedAmt/ 1000000.0)));
			if(prevtotalApprovedAmt>100000)
				totalApprovedC.setPrevious("$"+ String.format("%.2fM",(prevtotalApprovedAmt/1000000.0)));
			else
				totalApprovedC.setPrevious("$"+ String.format("%.2f",(prevtotalApprovedAmt)));
			 			
			totalApprovedC.setChange(String.format("%.2f", 100 * ((totalApprovedAmt - prevtotalApprovedAmt) / ((double)prevtotalApprovedAmt!=0?(double)prevtotalApprovedAmt:1)))+"%");
			data.addTopGridData(totalApprovedC);
			
			//Carrier Table
			Map<String, List<NRTObject_Cassandra>> claimsByCarrier = claims.stream()
					  .collect(Collectors.groupingBy(NRTObject_Cassandra::getTCD_Carrier_ID));
			
			Iterator<String> carriers= claimsByCarrier.keySet().iterator();
			while(carriers.hasNext()) {
				String carrier = carriers.next();
				List<NRTObject_Cassandra> cls = claimsByCarrier.get(carrier);
				TableData d = new TableData();
				d.setCarrier(carrier);
				d.setTotalClaims(String.valueOf(cls.size()));
				double taAmtApp = cls.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Patient_Pay_AmtDouble).sum();
				if(taAmtApp>100000)
					d.setTotalAmountApproved("$" + String.format("%.2fM",  (taAmtApp/1000000.0)));	
				else
					d.setTotalAmountApproved("$" + String.format("%.2f",  (taAmtApp)));					
				
				double tsAmt = cls.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Total_AmountDuouble).sum();
				if(tsAmt>100000)
					d.setTotalAmountSubmitted("$" + String.format("%.2fM",  (tsAmt/1000000.0)));
				else
					d.setTotalAmountSubmitted("$" + String.format("%.2f",  (tsAmt)));				
				
				long trNum = cls.stream().filter(c -> (c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))).count();
				long taNum = cls.size()-trNum;
				d.setTotalApproved(String.valueOf(taNum));
				d.setTotalDenied(String.valueOf(trNum));
				data.addTableData(d);				
			}
			
			//Reject Codes
			PolarChartData rejects = new PolarChartData();			 
			ArrayList<String> rejectCodes = new ArrayList<>();
			claims.stream().forEach(a ->{
				if(a.getRejectCodes()!=null) {
					rejectCodes.addAll( a.getRejectCodes());
				}
			});
			
			Map<String, Long> collect = rejectCodes.stream()
		            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			List<String> keys = collect.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(6).map(Map.Entry::getKey).collect(Collectors.toList());
			
			
			for(String a:keys) {
				rejects.addData(collect.get(a));
				rejects.addLabel(a); 
			}			
			data.setRejects(rejects);
			
			//Settlement Codes			
			PolarChartData settlements = new PolarChartData();			 
			ArrayList<String> settlementCodes = new ArrayList<>();
			claims.stream().forEach(a ->{
				if(a.getSettlementCodes()!=null) {
					settlementCodes.addAll(a.getSettlementCodes());
				}
			});
			
			Map<String, Long> collects = settlementCodes.stream()
		            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			
			List<String> keyss = collects.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(6).map(Map.Entry::getKey).collect(Collectors.toList());
			
			for(String a:keyss) {
				settlements.addData(collects.get(a));
				settlements.addLabel(a); 
			}			
			data.setSettlements(settlements);
			
			//Total Pie
			boolean found = claims.stream().anyMatch(p->p.getClaimType()!=null && p.getClaimType().trim().length()>0);			 
			if(found) {
				PolarChartData totalPie = new PolarChartData();	
				Map<String, Long> types = claims.stream().filter(p->p.getClaimType()!=null && p.getClaimType().trim().length()>0)
			            .collect(Collectors.groupingBy(NRTObject_Cassandra::getClaimType, Collectors.counting()));
				
				for(String k:types.keySet()) {
					totalPie.addData(types.get(k));
					String label = "";
					if(k!=null && k.trim().equalsIgnoreCase("R")) {
						label = "Retail";
					}else if(k!=null && k.trim().equalsIgnoreCase("S")) {
						label = "Specialty";
					}else if(k!=null && k.trim().equalsIgnoreCase("M")) {
						label = "Mail";
					}else {
						label = "Paper";
					}
					totalPie.addLabel(label);				 
				}			
				data.setClaimType(totalPie);
			}
			
			//Drugs
			TotalBarChart drugs = new TotalBarChart();
			drugs.addLabel("Drugs");
			ArrayList<String> drugsList = new ArrayList<>();
			claims.stream().forEach(a ->{
				if(a.getTCD_Sbm_Product_Name()!=null) {
					drugsList.add(a.getTCD_Sbm_Product_Name());
				}
				 
			});
			
			Map<String, Long> collectD = drugsList.stream()
		            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			List<String> keysD = collectD.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(7).map(Map.Entry::getKey).collect(Collectors.toList());
			
			for(String a1:keysD) {
				ToolBarData bar = drugs.new ToolBarData();
				bar.addData(String.valueOf(collectD.get(a1)));
				bar.setLabel(String.valueOf(a1)); 
				
				drugs.addDataSet(bar);
			}			
			data.setDrugs(drugs);
			
			//Pharmacies
			TotalBarChart pharmacies = new TotalBarChart();
			pharmacies.addLabel("Pharmacies");
			ArrayList<String> pharmaciesList = new ArrayList<>();
			claims.stream().forEach(a ->{
				if(a.getPHA_Pharmacy_Name_Full()!=null) {
					pharmaciesList.add(a.getPHA_Pharmacy_Name_Full());
				}
			});
			
			Map<String, Long> collectP = pharmaciesList.stream()
		            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			List<String> keysP = collectP.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(7).map(Map.Entry::getKey).collect(Collectors.toList());
			
			for(String a:keysP) {
				ToolBarData bar = pharmacies.new ToolBarData();
				bar.addData(String.valueOf(collectP.get(a)));
				bar.setLabel(String.valueOf(a)); 
				
				pharmacies.addDataSet(bar);
			}			
			data.setPharmacies(pharmacies);
			
			//Top Chart
			TotalBarChart top = new TotalBarChart();
			
			LocalDate endDate = LocalDate.now();
			LocalDate startDate = endDate.minusDays(180); 
			
			//number of months
		    long weekNumber = ChronoUnit.MONTHS.between(YearMonth.from(startDate), YearMonth.from(endDate));
		     
		     Map<LocalDate, LocalDate> months = new LinkedHashMap<>();
		     for (LocalDate chunkEnd = endDate, chunkStart; ! chunkEnd.isBefore(startDate); chunkEnd = chunkStart.minusDays(1)) {
		         chunkStart = chunkEnd. withDayOfMonth(1);
		         if (chunkStart.isBefore(startDate))
		             chunkStart = startDate;
		         months.put(chunkStart, chunkEnd);
		     }
		      
		     DateTimeFormatter dateTimeLabelFormatter = DateTimeFormatter.ofPattern("MM/dd");
		     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		     Map<String, ArrayList<String>> weeksMap = new HashMap<>();
		     weeksMap.put("Total Claims", new ArrayList<String>());
		     weeksMap.put("Approved", new ArrayList<String>());
		     weeksMap.put("Denied", new ArrayList<String>());
		     
		     
		     List<LocalDate> alKeys
	            = new ArrayList<LocalDate>(months.keySet());
	  
	        // reverse order of keys
	        Collections.reverse(alKeys);		     
		     
		     for (LocalDate startOfWeek : alKeys) {
		          LocalDate endOfWeek = months.get(startOfWeek);
		          //top.addLabel(dateTimeLabelFormatter.format(startOfWeek) +"-" +dateTimeLabelFormatter.format(endOfWeek));
		          top.addLabel(startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
		          itotal=0;
				  iapproved = 0;
				  idenied = 0;
		          claims.stream().forEach(c->{
		        	  
		        	  if(checkBetween(LocalDate.parse(c.getTCD_Sbm_Date_of_Service(), dateTimeFormatter), startOfWeek, endOfWeek)) {
		        		  itotal = itotal+1;
		        		  if(c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R")) {
		        			  idenied = idenied +1;		        			  
		        		  }else {
		        			  iapproved = iapproved+1;
		        		  }
		        	  }
		          });
		          weeksMap.get("Total Claims").add(String.valueOf(itotal));
		          weeksMap.get("Approved").add(String.valueOf(iapproved));
		          weeksMap.get("Denied").add(String.valueOf(idenied));
		      }
			  
		       weeksMap.keySet().forEach(k ->{
		    	   ToolBarData d = top.new ToolBarData();
		    	   d.setLabel(k);
		    	   d.setData(weeksMap.get(k));
		    	   top.addDataSet(d);		    	   
		       });
			
//		    //number of weeks
//		    long weekNumber = ChronoUnit.WEEKS.between(startDate, endDate);
//		    if(weekNumber*7 <30) {
//		        weekNumber = weekNumber+1;
//		     } 
//		     Map<LocalDate, LocalDate> weeks = new LinkedHashMap<>();
//		     for (int i = 0; i < weekNumber; i++) {
//		            LocalDate endOfWeek = startDate.plusDays(6);
//		            weeks.put(startDate, endOfWeek);
//		            startDate = endOfWeek.plusDays(1);
//		     }
//		     DateTimeFormatter dateTimeLabelFormatter = DateTimeFormatter.ofPattern("MM/dd");
//		     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//		     Map<String, ArrayList<String>> weeksMap = new HashMap<>();
//		     weeksMap.put("Total Claims", new ArrayList<String>());
//		     weeksMap.put("Approved", new ArrayList<String>());
//		     weeksMap.put("Denied", new ArrayList<String>());
//		     
//		     for (LocalDate startOfWeek : weeks.keySet()) {
//		          LocalDate endOfWeek = weeks.get(startOfWeek);
//		          top.addLabel(dateTimeLabelFormatter.format(startOfWeek) +"-" +dateTimeLabelFormatter.format(endOfWeek));
//		          itotal=0;
//				  iapproved = 0;
//				  idenied = 0;
//		          claims.stream().forEach(c->{
//		        	  
//		        	  if(checkBetween(LocalDate.parse(c.getTCD_Sbm_Date_of_Service(), dateTimeFormatter), startOfWeek, endOfWeek)) {
//		        		  itotal = itotal+1;
//		        		  if(c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("P")) {
//		        			  iapproved = iapproved+1;
//		        		  }else {
//		        			  idenied = idenied +1;
//		        		  }
//		        	  }
//		          });
//		          weeksMap.get("Total Claims").add(String.valueOf(itotal));
//		          weeksMap.get("Approved").add(String.valueOf(iapproved));
//		          weeksMap.get("Denied").add(String.valueOf(idenied));
//		      }
//			  
//		       weeksMap.keySet().forEach(k ->{
//		    	   ToolBarData d = top.new ToolBarData();
//		    	   d.setLabel(k);
//		    	   d.setData(weeksMap.get(k));
//		    	   top.addDataSet(d);		    	   
//		       });
		     
		     
		      data.setTotalBarChart(top);
			}		
		}catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
		return data;
	}
	
	
	public  MedicineDashboardData getMedicineDashBoardData(List<NRTObject_Cassandra> claims) {
		MedicineDashboardData data = new MedicineDashboardData();
		try {
		if(claims!=null && claims.size()>0) {
			
			//Top Grid			
			MedicineTop totalClaims = new MedicineTop();
			totalClaims.setCurrent(String.valueOf(claims.size()));
			data.addTopGridData(totalClaims);			
		 
			MedicineTop totalcarriers = new MedicineTop();
			Map<String, Long> countForId = claims.stream()
			        .collect(Collectors.groupingBy(NRTObject_Cassandra::getTCD_Carrier_ID, Collectors.counting()));
			totalcarriers.setCurrent(String.valueOf(countForId.keySet().size()));			
			data.addTopGridData(totalcarriers);
			
			MedicineTop totaldispensed = new MedicineTop();
			double totalDispensedAmt = claims.stream().mapToDouble(NRTObject_Cassandra::getTCD_Sbm_Qty_DispensedDouble).sum();
			if(totalDispensedAmt>100000)
				totaldispensed.setCurrent(String.format("%.2fM",(totalDispensedAmt/1000000.0))); 
			else
				totaldispensed.setCurrent(String.format("%.2f",(totalDispensedAmt))); 			
		 			
			data.addTopGridData(totaldispensed);
			 
			MedicineTop totalSubmitted = new MedicineTop();
			double totalSummitedAmt = claims.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Total_AmountDuouble).sum();			
			if(totalSummitedAmt>100000)
				totalSubmitted.setCurrent("$"+ String.format("%.2fM",(totalSummitedAmt/1000000.0))); 
			else
				totalSubmitted.setCurrent("$"+ String.format("%.2f",(totalSummitedAmt))); 			 
			data.addTopGridData(totalSubmitted);
			
			MedicineTop totalApprovedC = new MedicineTop();			
			double totalApprovedAmt = claims.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Patient_Pay_AmtDouble).sum();			
			if(totalApprovedAmt>100000)
				totalApprovedC.setCurrent("$"+ String.format("%.2fM",(totalApprovedAmt/1000000.0)));
			else
				totalApprovedC.setCurrent("$"+ String.format("%.2f",(totalApprovedAmt)));
			
			data.addTopGridData(totalApprovedC);
			
			//Carrier Table
			Map<String, List<NRTObject_Cassandra>> claimsByCarrier = claims.stream()
					  .collect(Collectors.groupingBy(NRTObject_Cassandra::getTCD_Carrier_ID));
			
			Iterator<String> carriers= claimsByCarrier.keySet().iterator();
			while(carriers.hasNext()) {
				String carrier = carriers.next();
				List<NRTObject_Cassandra> cls = claimsByCarrier.get(carrier);
				TableData d = new TableData();
				d.setCarrier(carrier);
				d.setTotalClaims(String.valueOf(cls.size()));
				double taAmtApp = cls.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Patient_Pay_AmtDouble).sum();
				if(taAmtApp>100000)
					d.setTotalAmountApproved("$" + String.format("%.2fM",  (taAmtApp/1000000.0)));	
				else
					d.setTotalAmountApproved("$" + String.format("%.2f",  (taAmtApp)));					
				
				double tsAmt = cls.stream().mapToDouble(NRTObject_Cassandra::getPDT_Rbl_Total_AmountDuouble).sum();
				if(tsAmt>100000)
					d.setTotalAmountSubmitted("$" + String.format("%.2fM",  (tsAmt/1000000.0)));
				else
					d.setTotalAmountSubmitted("$" + String.format("%.2f",  (tsAmt)));				
				
				long trNum = cls.stream().filter(c -> (c.getTCD_Claim_Status()!=null && c.getTCD_Claim_Status().trim().equalsIgnoreCase("R"))).count();
				long taNum = cls.size()-trNum;
				d.setTotalApproved(String.valueOf(taNum));
				d.setTotalDenied(String.valueOf(trNum));
				data.addTableData(d);				
			}		
			
			//Reject Codes
			PolarChartData rejects = new PolarChartData();			 
			ArrayList<String> rejectCodes = new ArrayList<>();
			claims.stream().forEach(a ->{
				if(a.getRejectCodes()!=null) {
					rejectCodes.addAll( a.getRejectCodes());
				}
			});
			
			Map<String, Long> collect = rejectCodes.stream()
		            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			List<String> keys = collect.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(6).map(Map.Entry::getKey).collect(Collectors.toList());
						
			for(String a:keys) {
				rejects.addData(collect.get(a));
				rejects.addLabel(a); 
			}			
			data.setRejects(rejects);
			
			//Claim Type
			boolean found = claims.stream().anyMatch(p->p.getClaimType()!=null && p.getClaimType().trim().length()>0);			 
			if(found) {
				PolarChartData totalPie = new PolarChartData();	
				Map<String, Long> types = claims.stream().filter(p->p.getClaimType()!=null && p.getClaimType().trim().length()>0)
			            .collect(Collectors.groupingBy(NRTObject_Cassandra::getClaimType, Collectors.counting()));
				
				for(String k:types.keySet()) {
					totalPie.addData(types.get(k));
					String label = "";
					if(k!=null && k.trim().equalsIgnoreCase("R")) {
						label = "Retail";
					}else if(k!=null && k.trim().equalsIgnoreCase("S")) {
						label = "Specialty";
					}else if(k!=null && k.trim().equalsIgnoreCase("M")) {
						label = "Mail";
					}else {
						label = "Paper";
					}
					totalPie.addLabel(label);				 
				}			
				data.setClaimType(totalPie);
			}
			
			//Gender
			boolean gfound = claims.stream().anyMatch(p->p.getMBR_Sex()!=null && p.getMBR_Sex().trim().length()>0);			 
			if(gfound) {
				PolarChartData genderPie = new PolarChartData();	
				Map<String, Long> types = claims.stream().filter(p->p.getMBR_Sex()!=null && p.getMBR_Sex().trim().length()>0)
			            .collect(Collectors.groupingBy(NRTObject_Cassandra::getMBR_Sex, Collectors.counting()));
				
				for(String k:types.keySet()) {
					genderPie.addData(types.get(k));
					String label = "";
					if(k!=null && k.trim().equalsIgnoreCase("M")) {
						label = "Male";
					}else if(k!=null && k.trim().equalsIgnoreCase("F")) {
						label = "Female";
					}else {
						label = "X";
					}
					genderPie.addLabel(label);				 
				}			
				data.setGenderType(genderPie);
			}
			
			//Doctors
			TotalBarChart doctors = new TotalBarChart();
			doctors.addLabel("Physicians");
			ArrayList<String> doctorList = new ArrayList<>();
			claims.stream().forEach(a ->{
				if(a.getPhysician_First_Name()!=null && a.getPhysician_First_Name().trim().length()>0) {
					doctorList.add(getValue(a.getPhysician_First_Name()) + " " + getValue(a.getPhysician_Last_Name()) );
				}				 
			});
			
			Map<String, Long> collectD = doctorList.stream()
		            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			List<String> keysD = collectD.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(7).map(Map.Entry::getKey).collect(Collectors.toList());
			
			for(String a1:keysD) {
				ToolBarData bar = doctors.new ToolBarData();
				bar.addData(String.valueOf(collectD.get(a1)));
				bar.setLabel(String.valueOf(a1)); 				
				doctors.addDataSet(bar);
			}			
			data.setDoctors(doctors);
			
			//Total Bar Chart			 
			TotalBarChart top = new TotalBarChart();
			
			LocalDate endDate = LocalDate.now();
			LocalDate startDate = endDate.minusDays(210); 	 
		     
		    Map<LocalDate, LocalDate> months = new LinkedHashMap<>();
		    for (LocalDate chunkEnd = endDate, chunkStart; ! chunkEnd.isBefore(startDate); chunkEnd = chunkStart.minusDays(1)) {
		         chunkStart = chunkEnd. withDayOfMonth(1);
		         if (chunkStart.isBefore(startDate))
		             chunkStart = startDate;
		         months.put(chunkStart, chunkEnd);
		     } 
		    
		     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		     Map<String, ArrayList<String>> weeksMap = new HashMap<>();
		     weeksMap.put("Total Dispensed", new ArrayList<String>());		      
		     
		     List<LocalDate> alKeys
	            = new ArrayList<LocalDate>(months.keySet());
	  
	        // reverse order of keys
	        Collections.reverse(alKeys);		     
		     
		     for (LocalDate startOfWeek : alKeys) {
		    	 total = 0;
		          LocalDate endOfWeek = months.get(startOfWeek);
		          //top.addLabel(dateTimeLabelFormatter.format(startOfWeek) +"-" +dateTimeLabelFormatter.format(endOfWeek));
		          top.addLabel(startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.US));    
				  
		          claims.stream().forEach(c->{		        	  
		        	  if(checkBetween(LocalDate.parse(c.getTCD_Sbm_Date_of_Service(), dateTimeFormatter), startOfWeek, endOfWeek)) {		        		   
		        		  if(c.getTCD_Claim_Status()!=null && !c.getTCD_Claim_Status().trim().equalsIgnoreCase("R")) {
		        			  total = total + c.getTCD_Sbm_Qty_DispensedDouble();	
		        		  }		        		   
		        	  }
		          });
		          weeksMap.get("Total Dispensed").add(String.valueOf(total));
		          
		      }
			  
		       weeksMap.keySet().forEach(k ->{
		    	   ToolBarData d = top.new ToolBarData();
		    	   d.setLabel(k);
		    	   d.setData(weeksMap.get(k));
		    	   top.addDataSet(d);		    	   
		       });	     
		    data.setTotalBarChart(top);			
			
			//Age Distribution	
		    PolarChartData ageDistribution = new PolarChartData();		
		    LocalDate ageendDate = LocalDate.now();
			LocalDate agestartDate = ageendDate.minusDays(210); 			 
		     
		    Map<LocalDate, LocalDate> agemonths = new LinkedHashMap<>();
		    for (LocalDate chunkEnd = ageendDate, chunkStart; ! chunkEnd.isBefore(agestartDate); chunkEnd = chunkStart.minusDays(1)) {
		         chunkStart = chunkEnd. withDayOfMonth(1);
		         if (chunkStart.isBefore(agestartDate))
		             chunkStart =agestartDate;
		         agemonths.put(chunkStart, chunkEnd);
		     }	           
		     
		     List<LocalDate> agealKeys
	            = new ArrayList<LocalDate>(agemonths.keySet());
	  
	        // reverse order of keys
	        Collections.reverse(agealKeys);	
	        Map<String, List<Long>> ages = new LinkedHashMap<>();
		     
		     for (LocalDate startOfWeek : agealKeys) {
		          LocalDate endOfWeek = agemonths.get(startOfWeek);
		          String label = startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
		          List<Long> agesForMonth = new ArrayList<Long>();		           		           
				  
		          claims.stream().forEach(c->{		        	  
		        	  if(checkBetween(LocalDate.parse(c.getTCD_Sbm_Date_of_Service(), dateTimeFormatter), startOfWeek, endOfWeek)) {		        		   
		        		  agesForMonth.add(calculateAge(c.getMBR_Date_of_Birth()));      		   
		        	  }
		          });
		           
		          ages.put(label, agesForMonth);	           
		      }
		     
		    ages.keySet().forEach(key->{		    	 
		    	ageDistribution.addLabel(key);		    	
		    	double average = ages.get(key)
		                .stream()
		                .mapToDouble(a -> a)
		                .average().orElse(0);
		    	long averageAge =  Double.valueOf(average).longValue();
		    	ageDistribution.addData(averageAge);
		    }); 
			  
		    data.setAge(ageDistribution);			
		    
		    //Days Supply 
		    PolarChartData daysDistribution = new PolarChartData();
		    LocalDate daysendDate = LocalDate.now();
			LocalDate daysstartDate = daysendDate.minusDays(210); 			 
		     
		    Map<LocalDate, LocalDate> daysmonths = new LinkedHashMap<>();
		    for (LocalDate chunkEnd = daysendDate, chunkStart; ! chunkEnd.isBefore(daysstartDate); chunkEnd = chunkStart.minusDays(1)) {
		         chunkStart = chunkEnd. withDayOfMonth(1);
		         if (chunkStart.isBefore(daysstartDate))
		             chunkStart =daysstartDate;
		         daysmonths.put(chunkStart, chunkEnd);
		     }	           
		     
		     List<LocalDate> daysalKeys
	            = new ArrayList<LocalDate>(daysmonths.keySet());
	  
	        // reverse order of keys
	        Collections.reverse(daysalKeys);	
	        Map<String, List<Double>> days = new LinkedHashMap<>();
		     
		     for (LocalDate startOfWeek : daysalKeys) {
		          LocalDate endOfWeek = daysmonths.get(startOfWeek);
		          String label = startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
		          List<Double> daysForMonth = new ArrayList<Double>();		           		           
				  
		          claims.stream().forEach(c->{		        	  
		        	  if(checkBetween(LocalDate.parse(c.getTCD_Sbm_Date_of_Service(), dateTimeFormatter), startOfWeek, endOfWeek)) {		        		   
		        		  daysForMonth.add(c.getTCD_Sbm_Days_SupplyDouble());      		   
		        	  }
		          });
		           
		          days.put(label, daysForMonth);	           
		      }
		     
		     days.keySet().forEach(key->{		    	 
		    	daysDistribution.addLabel(key);		    	
		    	double average = days.get(key)
		                .stream()
		                .mapToDouble(a -> a)
		                .average().orElse(0);
		    	long averageAge =  Double.valueOf(average).longValue();
		    	daysDistribution.addData(averageAge);
		    }); 
			  
		    data.setDays(daysDistribution);			
		    
			}		
		}catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
		return data;
	}
	
	
	
	private  boolean checkBetween(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
	    return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <=0;
	}
	
	
	public NRTObject_Cassandra getCassandraObjectFromKafka(NRTKafkaObject obj) {
		NRTObject_Cassandra object = new NRTObject_Cassandra();
		if(obj!=null) {
			object.setADD_Time(obj.getAdd_time());
			object.setCAR_Carrier_Name(obj.getCarrier_nm());
			object.setCHG_Date(obj.getChg_dt());
			object.setCHG_Time(obj.getChg_time());
			object.setClaimType(obj.getClaim_type());
			//object.setClientCode(null);
			if(obj.getClm_msg_1()!=null && obj.getClm_msg_1().trim().length()>0) {
				object.addCLM_MSG(obj.getClm_msg_1());
			}
			if(obj.getClm_msg_2()!=null && obj.getClm_msg_2().trim().length()>0) {
				object.addCLM_MSG(obj.getClm_msg_2());
			}
			if(obj.getClm_msg_3()!=null && obj.getClm_msg_3().trim().length()>0) {
				object.addCLM_MSG(obj.getClm_msg_3());
			}
			object.setFam_Benefit_Amount(obj.getFmly_benefit_amt());
			object.setMbe_Benefit_Amount(obj.getMbr_benefit_amt());
			object.setMBR_Date_of_Birth(obj.getMbr_dt_of_birth());
			object.setMBR_First_Name(obj.getMbr_first_nm());
			object.setMBR_Last_Name(obj.getMbr_last_nm());
			object.setMBR_Multiple_Birth_Code(obj.getMbr_multiple_birth_cde());
			object.setMBR_Sex(obj.getMbr_sex());
			object.setMPA_NDC_GPI_Code(obj.getNdc_gpi_cde());
			object.setMPA_Use_Plan_Drug_Sts(obj.getPlan_drug_stts());
			object.setNPI_ID(obj.getNpi_id());
			object.setNPI_Nbr(obj.getNpi_nbr());
			object.setOPD_Fam_OOP_Max_Amount(obj.getFmly_oop_max_amt());
			object.setOPD_Mbr_OOP_Max_Amount(obj.getMbr_oop_max_amt());
			object.setPA_Effective_Date(obj.getPa_effective_dt());
			object.setPA_Term_date(obj.getPa_term_dt());
			object.setPD3_Rbl_Copay_Prcnt_Amt(obj.getRbl_copay_prcnt_amt());
			object.setPDT_App_Flat_Sls_Tax_Amt(obj.getApp_flat_sales_tax_amt());
			object.setPDT_Fam_Accu_Deduct_PTD(obj.getFmly_accu_deduct());
			object.setPDT_Ind_Accu_Deduct_PTD(obj.getInd_accu_deduct());
			object.setPDT_Rbl_Amt_Appl_Per_Dedu(obj.getRbl_amt_appl_per_deduct());
			object.setPDT_Rbl_Copay_Amount(obj.getRbl_copay_amt());
			object.setPDT_Rbl_Dispensing_Fee(obj.getRbl_dispensing_fee());
			object.setPDT_Rbl_Ingredient_Cost(obj.getRbl_ingredient_cost());
			object.setPDT_Rbl_Patient_Pay_Amt(obj.getRbl_pat_pay_amt());
			object.setPDT_Rbl_Total_Amount(obj.getRbl_total_amt());
			object.setPDT_Remaining_OOP_Max_Amt(obj.getRemaining_oop_max_amt());
			object.setPHA_Dispenser_Type(obj.getDispenser_type());
			object.setPHA_Pharmacy_Address_1(obj.getPhmcy_address_1());
			object.setPHA_Pharmacy_City(obj.getPhmcy_city());
			object.setPHA_Pharmacy_Name_Full(obj.getPhmcy_nm());
			object.setPHA_Pharmacy_NCPDP_Nbr(obj.getPhmcy_ncpdp_nbr());
			object.setPHA_Pharmacy_Phone(obj.getPhmcy_phone());
			object.setPHA_Pharmacy_State(obj.getPhmcy_state());
			object.setPHA_Pharmacy_Zip(obj.getPhmcy_zip());
			object.setPhysician_First_Name(obj.getPhysician_first_nm());
			object.setPhysician_Last_Name(obj.getPhysician_last_nm());
			//Reject Codes
			if(obj.getRej_reject_cde_1()!=null && obj.getRej_reject_cde_1().trim().length()>0) {
				object.addRejectCode(obj.getRej_reject_cde_1() +":"+obj.getRej_reject_cde_1_desc());			
			}
			if(obj.getRej_reject_cde_2()!=null && obj.getRej_reject_cde_2().trim().length()>0) {
				object.addRejectCode(obj.getRej_reject_cde_2() +":"+obj.getRej_reject_cde_2_desc());			
			}
			if(obj.getRej_reject_cde_3()!=null && obj.getRej_reject_cde_3().trim().length()>0) {
				object.addRejectCode(obj.getRej_reject_cde_3() +":"+obj.getRej_reject_cde_3_desc());			
			}
			
			object.setRxClmNum(obj.getRx_clm_nbr());
		 
			//Settlement Codes
			if(obj.getStlmnt_cde_1()!=null && obj.getStlmnt_cde_1().trim().length()>0 && !obj.getStlmnt_cde_1().trim().equals("0")) {
				object.addSettlementCode(obj.getStlmnt_cde_1() +":"+obj.getStlmnt_cde_1_msg());			
			}
			if(obj.getStlmnt_cde_2()!=null && obj.getStlmnt_cde_2().trim().length()>0 && !obj.getStlmnt_cde_2().trim().equals("0")) {
				object.addSettlementCode(obj.getStlmnt_cde_2() +":"+obj.getStlmnt_cde_2_msg());			
			}
			if(obj.getStlmnt_cde_3()!=null && obj.getStlmnt_cde_3().trim().length()>0 && !obj.getStlmnt_cde_3().trim().equals("0")) {
				object.addSettlementCode(obj.getStlmnt_cde_3() +":"+obj.getStlmnt_cde_3_msg());			
			}
			if(obj.getStlmnt_cde_4()!=null && obj.getStlmnt_cde_4().trim().length()>0 && !obj.getStlmnt_cde_4().trim().equals("0")) {
				object.addSettlementCode(obj.getStlmnt_cde_4() +":"+obj.getStlmnt_cde_4_msg());			
			}
			if(obj.getStlmnt_cde_5()!=null && obj.getStlmnt_cde_5().trim().length()>0 && !obj.getStlmnt_cde_5().trim().equals("0")) {
				object.addSettlementCode(obj.getStlmnt_cde_5() +":"+obj.getStlmnt_cde_5_msg());			
			}
			
			object.setTCD_Account_ID(obj.getAccount_id());
			object.setTCD_Carrier_ID(obj.getCarrier_id());
			object.setTCD_Claim_Origination_Flg(obj.getClm_origination_flg());
			object.setTCD_Claim_Seq(obj.getClm_seq());
			object.setTCD_Claim_Status(obj.getClm_stts());
			object.setTCD_CMS_Contract_Id(obj.getCms_contract_id());
			object.setTCD_COB_Claim_Indicator(obj.getCob_clm_indc());
			object.setTCD_Date_Submitted(obj.getDt_submitted());
			object.setTCD_Final_Plan_Cde(obj.getFinal_plan_cde());
			object.setTCD_Generic_Ind_MedSpan(obj.getGeneric_indc_medSpan());
			object.setTCD_Group_ID(obj.getGroup_id());
			object.setTCD_Mbr_Prior_Auth_Nbr(obj.getMbr_prior_auth_nbr());
			object.setTCD_Member_ID(obj.getMember_id());
			object.setTCD_Prior_Auth_Reason_Cde(obj.getPrior_auth_rsn_cde());
			object.setTCD_Product_Key(obj.getPrd_key());
			object.setTCD_Reimbursement_Flag(obj.getReimbursement_flag());
			object.setTCD_Rx_Network_ID(obj.getRx_ntwrk_id());
			object.setTCD_Sbm_Bin_Nbr(obj.getSbm_bin_nbr());
			object.setTCD_Sbm_Compound_Code(obj.getSbm_compound_cde());
			object.setTCD_Sbm_Date_of_Service(obj.getSbm_dt_of_service());
			object.setTCD_Sbm_Days_Supply(obj.getSbm_days_supply());
			object.setTCD_Sbm_Fill_Number(obj.getSbm_fill_nbr());
			object.setTCD_Sbm_Group_Nbr(obj.getSbm_group_nbr());
			object.setTCD_Sbm_PA_Number(obj.getSbm_pa_nbr());
			object.setTCD_Sbm_Prescriber_ID(obj.getSbm_prescriber_id());
			object.setTCD_Sbm_Processor_Ctl_Nbr(obj.getSbm_processor_ctl_Nbr());
			object.setTCD_Sbm_Product_ID(obj.getSbm_product_id());
			object.setTCD_Sbm_Product_Name(obj.getPrd_desc());
			object.setTCD_Sbm_Product_Sel_Code(obj.getSbm_product_sel_cde());
			object.setTCD_Sbm_Qty_Dispensed(obj.getSbm_qty_dispensed());
			object.setTCD_Sbm_Rx_Nbr(obj.getSbm_rx_nbr());
			object.setTCD_Sbm_Srv_Prov_ID(obj.getSbm_srv_prov_id());
			object.setTier(obj.getTier());			
			
		}		
		return object;
	}
	
	private long calculateAge(String birthDateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate birthDate = LocalDate.parse(birthDateString,formatter);
			LocalDate currentDate = LocalDate.now();
	        if ((birthDate != null) && (currentDate != null)) {
	            return Period.between(birthDate, currentDate).getYears();
	        } else {
	            return 0;
	        }
		}catch(Exception e) {
			return 0;
		}
    }
	
	private String getValue(String input) {
		return (input!=null?input.trim():"");
	}

}
