package com.mvc.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.models.pack.Category;
import com.models.pack.ExpenseModel;
import com.mvc.dao.ExpenseDAO;
import com.mvc.entities.Expense;

@Service
@Transactional
public class ExpenseService {
	
	@Autowired
	private ExpenseDAO edao;
	
	public String capitalize(String s) {
		String firstLetter = s.charAt(0) + "";
		String rest = s.substring(1);
		return firstLetter.toUpperCase() + rest;
	}
	
	public String addExpense(ExpenseModel e) {
		Expense exp = new Expense();
		exp.setTitle(e.getTitle());
		exp.setAmount(e.getAmount());
		exp.setCategory(e.getCategory());
		exp.setStamp(new Date());
		return edao.createExpense(exp);
	}
	
	public String updateString(ExpenseModel e) {
		Expense exp = edao.getExpense(e.getExpid());
		exp.setTitle(e.getTitle());
		exp.setCategory(e.getCategory());
		exp.setAmount(e.getAmount());
		return edao.updateExpense(exp);
	}
	
	public String deleteExpense(int id) {
		Expense exp = edao.getExpense(id);
		return edao.deleteExpense(exp);
	}	
	public List<ExpenseModel> allExpenses() {
		List<Expense> expenseList =  edao.allExpenses();
		List<ExpenseModel> emList = new ArrayList<ExpenseModel>();
		Comparator<Expense> reverseDates = Collections.reverseOrder(new Comparator<Expense>() {

			@Override
			public int compare(Expense o1, Expense o2) {
				return o1.getStamp().compareTo(o2.getStamp());
			}
		});
		Collections.sort(expenseList, reverseDates);
		for (Expense expense : expenseList) {
			ExpenseModel em = new ExpenseModel();
			em.setTitle(expense.getTitle());
			em.setAmount(expense.getAmount());
			em.setCategory(expense.getCategory());
			em.setStamp(expense.getStamp());
			em.setExpid(expense.getExpid());
			emList.add(em);
		}
		return emList;
	}
	
	public List<ExpenseModel> getExpensesByCategory(Category category) {
		List<Expense> expenseList =  edao.getExpensesByCategory(category);
		List<ExpenseModel> emList = new ArrayList<ExpenseModel>();
		for (Expense expense : expenseList) {
			ExpenseModel em = new ExpenseModel();
			em.setTitle(expense.getTitle());
			em.setAmount(expense.getAmount());
			em.setCategory(expense.getCategory());
			em.setStamp(expense.getStamp());
			em.setExpid(expense.getExpid());
			emList.add(em);
		}
		return emList;
	}
	
	public ExpenseModel getExpense(int id) {
		Expense exp = edao.getExpense(id);
		ExpenseModel model = new ExpenseModel();
		model.setExpid(exp.getExpid());
		model.setTitle(exp.getTitle());
		model.setAmount(exp.getAmount());
		model.setCategory(exp.getCategory());
		return model;
	}
	
	public float total(List<ExpenseModel> emList) {
		float sum = 0;
		for (ExpenseModel em : emList) {
			sum += em.getAmount();
		}
		return sum;
	}
	
}
