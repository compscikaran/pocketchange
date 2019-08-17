package com.mvc.dao;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.pack.Category;
import com.models.pack.ExpenseModel;
import com.mvc.entities.Expense;

@Repository
public class ExpenseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ExpenseDAO() {
		System.out.println("Created ExpenseDAO");
	}
	
	public String createExpense(Expense emp) {		
		Session sess = sessionFactory.getCurrentSession();
		Serializable s = sess.save(emp);
		return s.toString();
		
	}
	
	public List<Expense> allExpenses() {
		Session sess = sessionFactory.getCurrentSession();
		Criteria cr = sess.createCriteria(Expense.class);
		List<Expense> expenseList = cr.list();
		return expenseList;
	}
	
	public List<Expense> getExpensesByCategory(Category c) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria cr = sess.createCriteria(Expense.class);
		cr.add(Restrictions.eqOrIsNull("category", c));
		List<Expense> expenseList= cr.list();
		return expenseList;
	}
	
	public Expense getExpense(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Expense exp = (Expense) sess.load(Expense.class, id);
		return exp;
	}
	
	public String updateExpense(Expense emp) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(emp);
		return "Update Successfull";
	}
	
	public String deleteExpense(Expense emp) {
		Session sess = sessionFactory.getCurrentSession();
		sess.delete(emp);
		return "Delete Successfull";
	}
}
