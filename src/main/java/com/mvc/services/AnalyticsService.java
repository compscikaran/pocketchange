package com.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.models.pack.AnalyticsModel;
import com.models.pack.Category;
import com.mvc.dao.ExpenseDAO;
import com.mvc.entities.Expense;

@Service
@Transactional
public class AnalyticsService {
	
	@Autowired
	private ExpenseDAO edao;
	
	
	public AnalyticsModel calculateCategorySpending(AnalyticsModel model) {
		for (Category cat : Category.values()) {
			List<Expense> eList = edao.getExpensesByCategory(cat);
			Float result =  sumExpenses(eList);
			model.getCategorySpending().add(result);
		}
		return model;
	}
	
	public AnalyticsModel calculateMonthlySpending(AnalyticsModel model) {
		for (int i = 1; i < 13; i++) {
			List<Expense> eList = edao.getExpensesByMonth(i);
			Float result = sumExpenses(eList);
			model.getMonthlySpending().add(result);
		}
		return model;
	}
	
	public Float sumExpenses(List<Expense> expList) {
		Float result = 0f;
		for (Expense expense : expList) {
			result += expense.getAmount();
		}
		return result;
	}
	
	
}
