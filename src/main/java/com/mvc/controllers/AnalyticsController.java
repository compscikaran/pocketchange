package com.mvc.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.models.pack.AnalyticsModel;
import com.models.pack.Category;
import com.mvc.services.AnalyticsService;

@Controller
public class AnalyticsController {
	
	@Autowired
	private AnalyticsService service1;
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(Model m) {
		AnalyticsModel model = new AnalyticsModel();
		model = service1.calculateCategorySpending(model);
		
		m.addAttribute("model", model);
		return new ModelAndView("dashboard");
	}
}
