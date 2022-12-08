package com.cvs.nrt.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public class MedicineDashboardData {
	
	private List<MedicineTop> topGridData = new ArrayList<MedicineTop>();
	private List<TableData> tableData = new ArrayList<>();
	private PolarChartData rejects = new PolarChartData();
	private PolarChartData claimType = new PolarChartData();
	private PolarChartData genderType = new PolarChartData();
	private TotalBarChart doctors = new TotalBarChart();
	private TotalBarChart totalBarChart = new TotalBarChart();	
	private PolarChartData age = new PolarChartData();
	private PolarChartData days = new PolarChartData();
	
	
			
	public PolarChartData getDays() {
		return days;
	}

	public void setDays(PolarChartData days) {
		this.days = days;
	}

	public PolarChartData getAge() {
		return age;
	}

	public void setAge(PolarChartData age) {
		this.age = age;
	}

	public TotalBarChart getDoctors() {
		return doctors;
	}

	public void setDoctors(TotalBarChart doctors) {
		this.doctors = doctors;
	}

	public PolarChartData getGenderType() {
		return genderType;
	}

	public void setGenderType(PolarChartData genderType) {
		this.genderType = genderType;
	}

	public PolarChartData getClaimType() {
		return claimType;
	}

	public void setClaimType(PolarChartData claimType) {
		this.claimType = claimType;
	}
	public PolarChartData getRejects() {
		return rejects;
	}
	public void setRejects(PolarChartData rejects) {
		this.rejects = rejects;
	}
	public void addTableData(TableData dataItem) {
		this.tableData.add(dataItem);
	}
	
	public List<TableData> getTableData() {
		return tableData;
	}

	public void setTableData(List<TableData> tableData) {
		this.tableData = tableData;
	}

	public TotalBarChart getTotalBarChart() {
		return totalBarChart;
	}

	public void setTotalBarChart(TotalBarChart totalBarChart) {
		this.totalBarChart = totalBarChart;
	}

	public void addTopGridData(MedicineTop value) {
		topGridData.add(value);
	}

	public List<MedicineTop> getTopGridData() {
		return topGridData;
	}

	public void setTopGridData(List<MedicineTop> topGridData) {
		this.topGridData = topGridData;
	}

}
