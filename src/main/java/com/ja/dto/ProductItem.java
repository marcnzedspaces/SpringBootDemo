package com.ja.dto;

import com.ja.entity.Product;

/**
 * Represents an item in the shopping cart.
 * Each item consists of a product, the quantity of that product, and the total price for that quantity.
 */
public class ProductItem {

	private Product product; // The product associated with this item
	private int quantity; // The quantity of the product in the cart
	private double totalPrice; // The total price for the quantity of this product

	/**
	 * Constructor to create a ProductItem with a specified product.
	 * Initializes the quantity to 1 and the total price to 0.
	 *
	 * @param product The product to be associated with this item.
	 */
	public ProductItem(Product product) {
		this.product = product;
		this.quantity = 1;
		this.totalPrice = 0;
	}

	/**
	 * Gets the product associated with this item.
	 *
	 * @return The product associated with this item.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product associated with this item.
	 *
	 * @param product The product to be associated with this item.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Gets the quantity of the product in this item.
	 *
	 * @return The quantity of the product.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the product in this item.
	 *
	 * @param quantity The quantity of the product.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Calculates and gets the total price for the quantity of this product.
	 *
	 * @return The total price for the quantity of this product.
	 */
	public double getTotalPrice() {
		// Calculate the total price based on the product price and quantity
		totalPrice = product.getPrice() * quantity;
		return totalPrice;
	}

	/**
	 * Sets the total price for the quantity of this product.
	 *
	 * @param totalPrice The total price for the quantity of this product.
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
