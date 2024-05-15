package com.ja.entity;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Represents a product entity in the database.
 * This entity contains details about the product such as name, description, price, image, etc.
 */
@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Unique identifier for the product

	@Column(nullable = false)
	private String name; // Name of the product

	@Column(nullable = false)
	private String description; // Description of the product

	@Column(nullable = false)
	private double price; // Price of the product

	@Lob
	@Column(nullable = true)
	private byte[] image; // Image of the product stored as a byte array

	@Column(nullable = false)
	private Date createDate; // Date when the product was created

	private int instock; // Quantity of the product in stock

	private String manufacturer; // Manufacturer of the product

	@Column(name = "pcategory")
	private String category; // Category of the product

	@Column(name = "pcondition")
	private String condition; // Condition of the product (e.g., new, used)

	/**
	 * Gets the unique identifier of the product.
	 *
	 * @return the unique identifier of the product.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the product.
	 *
	 * @param id the unique identifier to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name of the product.
	 *
	 * @return the name of the product.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product.
	 *
	 * @param name the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the product.
	 *
	 * @return the description of the product.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the product.
	 *
	 * @param description the description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price of the product.
	 *
	 * @return the price of the product.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product.
	 *
	 * @param price the price to set.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the image of the product.
	 *
	 * @return the image of the product.
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Sets the image of the product.
	 *
	 * @param image the image to set.
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Gets the creation date of the product.
	 *
	 * @return the creation date of the product.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creation date of the product.
	 *
	 * @param createDate the creation date to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the quantity of the product in stock.
	 *
	 * @return the quantity of the product in stock.
	 */
	public int getInstock() {
		return instock;
	}

	/**
	 * Sets the quantity of the product in stock.
	 *
	 * @param instock the quantity to set.
	 */
	public void setInstock(int instock) {
		this.instock = instock;
	}

	/**
	 * Gets the manufacturer of the product.
	 *
	 * @return the manufacturer of the product.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer of the product.
	 *
	 * @param manufacturer the manufacturer to set.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the category of the product.
	 *
	 * @return the category of the product.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category of the product.
	 *
	 * @param category the category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the condition of the product.
	 *
	 * @return the condition of the product.
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Sets the condition of the product.
	 *
	 * @param condition the condition to set.
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Returns a string representation of the product.
	 *
	 * @return a string representation of the product.
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", image="
				+ Arrays.toString(image) + ", createDate=" + createDate + ", instock=" + instock + ", manufacturer="
				+ manufacturer + ", category=" + category + ", condition=" + condition + "]";
	}
}
