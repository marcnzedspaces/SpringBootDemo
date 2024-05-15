package com.ja.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ja.dto.ProductCart;
import com.ja.entity.Product;
import com.ja.service.ProductService;

/**
 * Controller class for handling product-related requests.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ProductService productService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handles the request to save product details.
	 *
	 * @param name The name of the product.
	 * @param price The price of the product.
	 * @param description The description of the product.
	 * @param instock The stock quantity of the product.
	 * @param manufacturer The manufacturer of the product.
	 * @param category The category of the product.
	 * @param condition The condition of the product.
	 * @param file The image file of the product.
	 * @param request The HttpServletRequest object.
	 * @param model The Model object.
	 * @return A ResponseEntity indicating the result of the operation.
	 */
	@PostMapping("/saveProductDetails")
	@ResponseBody
	public ResponseEntity<?> createProduct(
			@RequestParam("name") String name,
			@RequestParam("price") double price,
			@RequestParam("description") String description,
			@RequestParam("instock") int instock,
			@RequestParam("manufacturer") String manufacturer,
			@RequestParam("category") String category,
			@RequestParam("condition") String condition,
			@RequestParam("image") MultipartFile file,
			HttpServletRequest request,
			Model model) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory::" + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());

			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			String[] manufacturers = manufacturer.split(",");
			String[] categories = category.split(",");
			String[] conditions = condition.split(",");
			System.out.println(descriptions[0]);
			Date createDate = new Date();

			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdir();
				}
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Product product = new Product();
			product.setName(names[0]);
			product.setImage(imageData);
			product.setPrice(price);
			product.setDescription(descriptions[0]);
			product.setCreateDate(createDate);
			product.setInstock(instock);
			product.setManufacturer(manufacturers[0]);
			product.setCategory(categories[0]);
			product.setCondition(conditions[0]);
			productService.saveProduct(product);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product saved with file - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Displays the product image.
	 *
	 * @param id The ID of the product.
	 * @param response The HttpServletResponse object.
	 * @param product The optional product object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException If an I/O error occurs.
	 */
	@GetMapping("/display/{id}")
	@ResponseBody
	public void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {

		log.info("Id :: " + id);
		product = productService.getProductById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getImage());
		response.getOutputStream().close();
	}

	/**
	 * Shows the details of a product.
	 *
	 * @param id The ID of the product.
	 * @param product The optional product object.
	 * @param model The Model object.
	 * @return The view name.
	 */
	@GetMapping("/productDetails")
	public String showProductDetails(@RequestParam("id") Long id, Optional<Product> product, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				product = productService.getProductById(id);
				log.info("products :: " + product);

				if (product.isPresent()) {
					model.addAttribute("id", product.get().getId());
					model.addAttribute("description", product.get().getDescription());
					model.addAttribute("name", product.get().getName());
					model.addAttribute("price", product.get().getPrice());
					model.addAttribute("manufacturer", product.get().getManufacturer());
					model.addAttribute("category", product.get().getCategory());
					model.addAttribute("instock", product.get().getInstock());

					return "productdetails";
				}
				return "redirect:/home";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	/**
	 * Displays the list of active products.
	 *
	 * @param model The Model object.
	 * @return The view name.
	 */
	@GetMapping("/show")
	public String show(Model model) {
		List<Product> products = productService.getAllActiveProducts();
		model.addAttribute("products", products);
		return "products";
	}

	/**
	 * Adds a product to the cart.
	 *
	 * @param session The HttpSession object.
	 * @param product The product to add.
	 * @param model The Model object.
	 * @return The view name.
	 */
	@PostMapping("/addToCart")
	public String add(HttpSession session, @RequestParam("id") Product product, Model model) {
		ProductCart productCart = productService.findProductCart(session);
		String message = productCart.addProductItem(product, 1);
		if ("Product out of stock".equals(message)) {
			model.addAttribute("message", message);
		}

		return "cart";
	}

	/**
	 * Removes a product from the cart.
	 *
	 * @param session The HttpSession object.
	 * @param id The ID of the product to remove.
	 * @return The view name.
	 */
	@GetMapping("/remove")
	public String remove(HttpSession session, @RequestParam("id") Long id) {
		Product product = productService.getProductById(id).get();
		ProductCart productCart = productService.findProductCart(session);
		productCart.removeProductItem(product);

		return "cart";
	}

	/**
	 * Clears the cart.
	 *
	 * @param session The HttpSession object.
	 * @return The view name.
	 */
	@GetMapping("/clear")
	public String clear(HttpSession session) {
		ProductCart productCart = productService.findProductCart(session);
		productCart.clearProductItem();
		return "cart";
	}

	/**
	 * Checks out the cart.
	 *
	 * @param session The HttpSession object.
	 * @return The view name.
	 */
	@PostMapping("/checkout")
	public String checkout(HttpSession session) {
		ProductCart productCart = productService.findProductCart(session);
		List<Product> products = productCart.checkout();
		for (Product product : products) {
			productService.saveProduct(product);
		}
		return "cart";
	}
}
