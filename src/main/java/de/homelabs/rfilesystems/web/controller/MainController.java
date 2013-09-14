package de.homelabs.rfilesystems.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping({"/",""})
	public ModelAndView handleMain(HttpServletRequest request, HttpServletResponse rsponse, ModelMap model){
		
		return new ModelAndView("main", model);
	}
}
