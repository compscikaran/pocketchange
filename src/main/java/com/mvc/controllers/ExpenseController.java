package com.mvc.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.models.pack.Category;
import com.models.pack.ExpenseModel;
import com.models.pack.FilterModel;
import com.mvc.services.ExpenseService;


@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	@RequestMapping("/")
	public ModelAndView index(Model m) {
		FilterModel fm = new FilterModel();
		List<ExpenseModel> emList = service.allExpenses();
		List<Category> categories = Arrays.asList(Category.values());
		m.addAttribute("categoryList", categories);
		m.addAttribute("elist", emList);
		m.addAttribute("filtered", false);
		m.addAttribute("fmodel", fm);
		m.addAttribute("title", "All Expenses");
		m.addAttribute("total", service.total(emList));
		return new ModelAndView("index");
	}
	
	@RequestMapping("/new")
	public ModelAndView addExpense(Model m) {
		ExpenseModel model = new ExpenseModel();
		List<Category> categories = Arrays.asList(Category.values());
		m.addAttribute("categoryList", categories);
		m.addAttribute("exp", model);
		return new ModelAndView("add");
	}
	
	@RequestMapping(value = "/create", method =RequestMethod.POST)
	public RedirectView saveExpense(@ModelAttribute("exp") ExpenseModel exp) {
		if(exp.getTitle() == null || exp.getAmount() == 0 || exp.getCategory() == null) {
			return new RedirectView("new");
		}
		else {
			System.out.println("Expense Added ID:" + service.addExpense(exp));
			return new RedirectView("./");
		}
	}
	
	
	@RequestMapping("/update")
	public ModelAndView updateExpense(Model m, HttpServletRequest request) {
		int id = Integer.valueOf(request.getParameter("id"));
		ExpenseModel model = service.getExpense(id);
		m.addAttribute("exp", model);
		return new ModelAndView("update");
	}
	
	@RequestMapping(value = "/modify", method =RequestMethod.PUT)
	public RedirectView saveUpdateExpense(@ModelAttribute("exp") ExpenseModel exp) {
		if(exp.getTitle() == null || exp.getAmount() == 0 || exp.getCategory() == null) {
			return new RedirectView("update");
		}
		else {
			System.out.println("Expense Updated ID:" + service.updateString(exp));
			return new RedirectView("./");
		}
	}
	
	@RequestMapping(value = "/delete", method =RequestMethod.DELETE)
	public RedirectView deleteExpense(HttpServletRequest request) {
		int id = Integer.valueOf(request.getParameter("id"));
		System.out.println(service.deleteExpense(id));
		return new RedirectView("./");
	}
	
	@RequestMapping("/filter")
	public ModelAndView filter(Model m, @ModelAttribute("fmodel") FilterModel fm) {
		Category category = fm.getCategory();
		List<ExpenseModel> emList = service.getExpensesByCategory(category);
		m.addAttribute("elist", emList);
		m.addAttribute("filtered", true);
		m.addAttribute("title", category.name());
		m.addAttribute("total", service.total(emList));
		return new ModelAndView("index");
	}
	
}
	
	