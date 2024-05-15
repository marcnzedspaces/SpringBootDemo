package com.ja.dto;

import java.util.ArrayList;
import java.util.List;

import com.ja.entity.Product;

/**
 * This class represents a shopping cart for products.
 */
public class ProductCart {

	private List<ProductItem> productItems;
	private double grandTotal;

	/**
	 * Default constructor initializing the product items list and grand total.
	 */
	public ProductCart() {
		productItems = new ArrayList<>();
		this.grandTotal = 0;
	}

	/**
	 * Finds a product item in the cart.
	 *
	 * @param product The product to find.
	 * @return The product item if found, otherwise null.
	 */
	public ProductItem findProductItem(Product product) {
		// Iterate through the list of product items to find a match
		for (ProductItem productItem : productItems) {
			if (productItem.getProduct().getId() == product.getId()) {
				return productItem;
			}
		}
		return null;
	}

	/**
	 * Adds a product item to the cart.
	 *
	 * @param product  The product to add.
	 * @param quantity The quantity of the product.
	 * @return A message indicating the result of the operation.
	 */
	public String addProductItem(Product product, int quantity) {
		// Check if the product is already in the cart
		ProductItem productItem = findProductItem(product);
		if (productItem != null) {
			// Check stock availability
			if (product.getInstock() == 0) {
				return "Product out of stock";
			} else if (productItem.getQuantity() < productItem.getProduct().getInstock()) {
				// Increase the quantity if there is enough stock
				productItem.setQuantity(productItem.getQuantity() + quantity);
				return "Add product successfully";
			} else {
				return "Product out of stock";
			}
		} else if (productItem == null && product.getInstock() == 0) {
			return "Product out of stock";
		} else {
			// Add a new product item to the cart
			productItem = new ProductItem(product);
			productItems.add(productItem);
			return "Add product successfully";
		}
	}

	/**
	 * Gets the grand total price of all products in the cart.
	 *
	 * @return The grand total price.
	 */
	public double getGrandTotal() {
		grandTotal = 0;
		// Calculate the grand total by summing up the total price of each product item
		for (ProductItem productItem : productItems) {
			grandTotal += productItem.getTotalPrice();
		}
		return grandTotal;
	}

	/**
	 * Gets the list of product items in the cart.
	 *
	 * @return The list of product items.
	 */
	public List<ProductItem> getProductItems() {
		return productItems;
	}

	/**
	 * Removes a product item from the cart.
	 *
	 * @param product The product to remove.
	 */
	public void removeProductItem(Product product) {
		// Find and remove the product item from the list
		ProductItem productItem = findProductItem(product);
		if (productItem != null) {
			productItems.remove(productItem);
		}
	}

	/**
	 * Clears all product items from the cart.
	 */
	public void clearProductItem() {
		productItems.clear();
	}

	/**
	 * Checks out the cart and updates the stock of products.
	 *
	 * @return A list of products with updated stock.
	 */
	public List<Product> checkout() {
		List<Product> products = new ArrayList<>();
		// Update the stock of each product and add it to the checkout list
		for (ProductItem productItem : productItems) {
			Product product = productItem.getProduct();
			product.setInstock(product.getInstock() - productItem.getQuantity());
			products.add(product);
		}
		// Clear the cart after checkout
		productItems.clear();
		return products;
	}
}
