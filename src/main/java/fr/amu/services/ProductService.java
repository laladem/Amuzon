package fr.amu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Product;
import fr.amu.models.ProductDAO;

@Service
public class ProductService{
	
	@Autowired
	ProductDAO productDAO;
	
	public void addProduct(Product product) {
		productDAO.add(product);
	}

	public Product getProduct(int id) {
		return productDAO.findById(id);
	}

	public void removeProduct(int id) {
		Product pr = new Product();
		pr.setId(id);
		productDAO.delete(pr);
	}

	public List<Product> getProducts(){
		return productDAO.findAll();
	}

	public List<Product> getCategoryProducts(String category){
		return productDAO.findByCategory(category);
	}

	
}

/*
 * Classe Service intermediaire entre le DAO et l'application
 * */
