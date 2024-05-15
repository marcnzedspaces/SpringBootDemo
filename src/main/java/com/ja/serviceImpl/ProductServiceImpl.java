package com.ja.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.dto.ProductCart;
import com.ja.entity.Product;
import com.ja.repository.ProductRepository;
import com.ja.service.ProductService;

/**
 * Implementation of the {@link ProductService} interface.
 * Provides methods for saving, retrieving, and managing products and product carts.
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;  // Injecting the ProductRepository dependency

	/**
	 * Saves a given product using the repository.
	 *
	 * @param product the product to be saved
	 */
	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);  // Saving the product to the database
	}

	/**
	 * Retrieves all active products from the repository.
	 *
	 * @return a list of all active products
	 */
	@Override
	public List<Product> getAllActiveProducts() {
		return productRepository.findAll();  // Retrieving all products from the database
	}

	/**
	 * Retrieves a product by its ID using the repository.
	 *
	 * @param id the ID of the product
	 * @return an Optional containing the product if found, or empty if not found
	 */
	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);  // Finding the product by ID
	}

	/**
	 * Finds or creates a ProductCart associated with the current HTTP session.
	 *
	 * @param session the current HTTP session
	 * @return the ProductCart associated with the session
	 */
	@Override
	public ProductCart findProductCart(HttpSession session) {
		// Retrieving the ProductCart from the session
		ProductCart productCart = (ProductCart) session.getAttribute("productCart");

		// If no ProductCart exists, create a new one and add it to the session
		if (productCart == null) {
			productCart = new ProductCart();
			session.setAttribute("productCart", productCart);
		}

		return productCart;  // Returning the ProductCart
	}

	/**
	 * Removes the ProductCart from the current HTTP session.
	 *
	 * @param session the current HTTP session
	 */
	public void removeCart(HttpSession session) {
		session.removeAttribute("productCart");  // Removing the ProductCart from the session
	}
}
