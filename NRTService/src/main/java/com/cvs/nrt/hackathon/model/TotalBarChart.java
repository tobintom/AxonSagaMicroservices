package com.cvs.nrt.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public class TotalBarChart { 

	private List<String> labels = new ArrayList<String>();
	private List<ToolBarData> dataSet = new ArrayList<>();
	
	
	
	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<ToolBarData> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<ToolBarData> dataSet) {
		this.dataSet = dataSet;
	}
	
	public void addLabel(String label) {
		this.labels.add(label);
	}
	
	public void addDataSet(ToolBarData data) {
		this.dataSet.add(data);
	}

	public class ToolBarData{
		
		private List<String> data = new ArrayList<>();
		private String label;
		
		public List<String> getData() {
			return data;
		}
		
		public void addData(String dataItem) {
			data.add(dataItem);
		}
		public void setData(List<String> data) {
			this.data = data;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		
		
		
	}
}

