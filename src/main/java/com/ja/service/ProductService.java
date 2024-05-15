package com.ja.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.ja.dto.ProductCart;
import com.ja.entity.Product;

/**
 * Service interface for managing products.
 * Provides methods for saving, retrieving, and managing products and product carts.
 */
public interface ProductService {

	/**
	 * Saves a given product.
	 *
	 * @param product the product to be saved
	 */
	public void saveProduct(Product product);

	/**
	 * Retrieves all active products.
	 *
	 * @return a list of all active products
	 */
	public List<Product> getAllActiveProducts();

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id the ID of the product
	 * @return an Optional containing the product if found, or empty if not found
	 */
	public Optional<Product> getProductById(Long id);

	/**
	 * Finds or creates a ProductCart associated with the current HTTP session.
	 *
	 * @param session the current HTTP session
	 * @return the ProductCart associated with the session
	 */
	public ProductCart findProductCart(HttpSession session);
}
