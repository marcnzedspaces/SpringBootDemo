/**
 * Package containing controllers for handling web requests.
 */
package com.ja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling requests related to the home page.
 *
 * This controller is annotated with `@Controller` to indicate it handles
 * web requests.
 *
 * Package Comment: Added a comment describing the purpose of this package (handling web requests with controllers).
 * HomeController Class Comment: Added comments explaining the purpose of the HomeController class and that it's a Spring controller.
 * home() Method Comment: Explained the functionality of the home() method in detail, including the handling of GET requests for specific paths and returning the logical view name.
 */
@Controller
public class HomeController {

	/**
	 * Handles GET requests to the root path (`/`) and the `/home` path.
	 *
	 * This method is annotated with `@GetMapping` to specify that it handles
	 * GET requests. The `value` attribute defines the paths this method will
	 * handle. It returns the logical view name "index", which will be resolved
	 * by Spring MVC to render the corresponding template.
	 *
	 * @return (String) The logical view name "index".
	 */
	@GetMapping(value= {"/","/home"})
	public String home() {
		return "index";
	}
}
