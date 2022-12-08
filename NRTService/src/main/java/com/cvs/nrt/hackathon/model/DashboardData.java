package com.cvs.nrt.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public class DashboardData {
 
	private List<TopgridValues> topGridData = new ArrayList<TopgridValues>();
	private TotalBarChart totalBarChart = new TotalBarChart();
	private List<TableData> tableData = new ArrayList<>();
	private PolarChartData rejects = new PolarChartData();
	private PolarChartData settlements = new PolarChartData();
	private PolarChartData claimType = new PolarChartData();
	private TotalBarChart drugs = new TotalBarChart();
	private TotalBarChart pharmacies = new TotalBarChart();

	public TotalBarChart getTotalBarChart() {
		return totalBarChart;
	}

	public void setTotalBarChart(TotalBarChart totalBarChart) {
		this.totalBarChart = totalBarChart;
	}

	public List<TopgridValues> getTopGridData() {
		return topGridData;
	}

	public void setTopGridData(List<TopgridValues> topGridData) {
		this.topGridData = topGridData;
	}
	
	public void addTopGridData(TopgridValues value) {
		topGridData.add(value);
	}

	public List<TableData> getTableData() {
		return tableData;
	}

	public void setTableData(List<TableData> tableData) {
		this.tableData = tableData;
	}
	
	public void addTableData(TableData dataItem) {
		this.tableData.add(dataItem);
	}

	public PolarChartData getRejects() {
		return rejects;
	}

	public void setRejects(PolarChartData rejects) {
		this.rejects = rejects;
	}

	public PolarChartData getSettlements() {
		return settlements;
	}

	public void setSettlements(PolarChartData settlements) {
		this.settlements = settlements;
	}

	public PolarChartData getClaimType() {
		return claimType;
	}

	public void setClaimType(PolarChartData claimType) {
		this.claimType = claimType;
	}

	public TotalBarChart getDrugs() {
		return drugs;
	}

	public void setDrugs(TotalBarChart drugs) {
		this.drugs = drugs;
	}

	public TotalBarChart getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(TotalBarChart pharmacies) {
		this.pharmacies = pharmacies;
	}
 
	
	
}
