package fr.amu.controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.amu.models.Product;
import fr.amu.services.ProductService;

@Controller
public class ProductController {
	@Autowired
	private HttpSession httpSession;
	@Autowired
	ServletContext context;
	@Autowired
	ProductService ps;
	
	// Ajoute un produit et renvoie en attribut la liste des produits à "homepage"
	@RequestMapping( value = "/add", method = RequestMethod.POST)
	public String add(@Valid HttpServletRequest request, Map<String, Object> model){
		String sessionUser= (String) httpSession.getAttribute("user");
		 Product product = new Product();
		 product.setTitle(request.getParameter("title"));
		 product.setCategory(request.getParameter("category"));
		 product.setDescription(request.getParameter("description"));
		 ps.addProduct(product);
		 model.put("products", ps.getProducts() );
		System.out.println("session user = " + sessionUser);
		return "homepage";
	}

	//Supprime un produit et renvoie la liste des produits à "homepage"
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String remove( HttpServletRequest request, Map<String, Object> model){
		String sessionUser= (String) httpSession.getAttribute("user");
		try {
		 ps.removeProduct(Integer.parseInt(request.getParameter("productId")));
			}catch (NumberFormatException e) {
		 e.printStackTrace();
	    }
		 model.put("products", ps.getProducts() );
		System.out.println("session user = " + sessionUser);
		return "homepage";
	}
	
	// filtre les produits par catégorie et renvoie cette liste à "homepage"
	@RequestMapping( value = "/category", method = RequestMethod.POST)
	public String filterByCategory( HttpServletRequest request, Map<String, Object> model){
		String sessionUser= (String) httpSession.getAttribute("user");
		if(request.getParameter("category").equals("all")) {
			model.put("products", ps.getProducts() );
			return "homepage";
		}	
		model.put("products", ps.getCategoryProducts(request.getParameter("category")));
		System.out.println("session user = " + sessionUser);
		return "homepage";
	}

}


