package com.models.pack;

import java.util.HashMap;

public class AnalyticsModel {
	private HashMap<Category, Float> categorySpending;
	private Float total;
	
	public AnalyticsModel() {
		categorySpending = new HashMap<Category, Float>();
	}

	public HashMap<Category, Float> getCategorySpending() {
		return categorySpending;
	}

	public void setCategorySpending(HashMap<Category, Float> categorySpending) {
		this.categorySpending = categorySpending;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	
}
