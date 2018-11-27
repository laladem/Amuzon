package fr.amu.controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.amu.services.ProductService;

@Controller
public class IndexController {
	@Autowired
	private HttpSession httpSession;
	@Autowired
	ServletContext context;
	@Autowired
	ProductService ps;
	
	@GetMapping("/") // raccourci pour @RequestMapping( value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		String sessionUser= (String) httpSession.getAttribute("user");
		model.put("products", ps.getProducts() );
		System.out.println("session user = " + sessionUser);
		return "homepage";
	}



}


