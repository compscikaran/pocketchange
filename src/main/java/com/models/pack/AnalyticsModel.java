package com.models.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnalyticsModel {
	private List<Float> categorySpending;
	private Float total;
	private List<Float> monthlySpending;
	
	public AnalyticsModel() {
		categorySpending = new ArrayList<Float>();
		monthlySpending = new ArrayList<Float>();
	}

	public List<Float> getMonthlySpending() {
		return monthlySpending;
	}

	public void setMonthlySpending(List<Float> monthlySpending) {
		this.monthlySpending = monthlySpending;
	}

	public List<Float> getCategorySpending() {
		return categorySpending;
	}

	public void setCategorySpending(List<Float> categorySpending) {
		this.categorySpending = categorySpending;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
}
