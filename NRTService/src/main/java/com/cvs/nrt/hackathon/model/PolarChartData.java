package com.cvs.nrt.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public class PolarChartData { 

	private List<String> labels = new ArrayList<>();
	private List<Long> data = new ArrayList<>();
	
	public void addLabel(String label) {
		labels.add(label);
	}
	
	public void addData(Long item) {
		this.data.add(item);
	}
	
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<Long> getData() {
		return data;
	}
	public void setData(List<Long> data) {
		this.data = data;
	}
	
	
}
