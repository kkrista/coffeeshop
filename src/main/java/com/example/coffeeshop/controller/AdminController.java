package com.example.coffeeshop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.example.coffeeshop.dto.ProductDTO;
import com.example.coffeeshop.entity.Category;
import com.example.coffeeshop.entity.Manufacturer;
import com.example.coffeeshop.entity.Product;
import com.example.coffeeshop.entity.Supplier;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.ManufacturerService;
import com.example.coffeeshop.service.ProductService;
import com.example.coffeeshop.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {


	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	ProductService productService;

	@GetMapping("/admin")
	public String adminHome() {

		return "adminHome";
	}

	// Category section
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		return "404";

	}

	// Product section

	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProduct(null));
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
		model.addAttribute("suppliers", supplierService.getAllSupplier());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException {

		Product product = new Product();

		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setManufacturer(manufacturerService.getManufacturerById(productDTO.getManufacturerId()).get());
		product.setSupplier(supplierService.getSupplierById(productDTO.getSupplierId()).get());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);

		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setManufacturerId(product.getManufacturer().getId());
		productDTO.setSupplierId(product.getSupplier().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());

		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
		model.addAttribute("suppliers", supplierService.getAllSupplier());
		model.addAttribute("productDTO", productDTO);

		return "productsAdd";

	}

	// Manufacturer section
	@GetMapping("/admin/manufacturers")
	public String getMan(Model model) {
		model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
		return "manufacturers";
	}

	@GetMapping("/admin/manufacturers/add")
	public String getManAdd(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		return "manufacturersAdd";
	}

	@PostMapping("/admin/manufacturers/add")
	public String postManAdd(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
		manufacturerService.addManufacturer(manufacturer);
		return "redirect:/admin/manufacturers";
	}

	@GetMapping("/admin/manufacturers/delete/{id}")
	public String deleteMan(@PathVariable int id) {
		manufacturerService.removeManufacturerById(id);
		return "redirect:/admin/manufacturers";
	}

	@GetMapping("/admin/manufacturers/update/{id}")
	public String updateMan(@PathVariable int id, Model model) {
		Optional<Manufacturer> manufacturer = manufacturerService.getManufacturerById(id);
		if (manufacturer.isPresent()) {
			model.addAttribute("manufacturer", manufacturer.get());
			return "manufacturersAdd";
		}
		return "404";

	}

	// Supplier section

	@GetMapping("/admin/suppliers")
	public String getSupplier(Model model) {
		model.addAttribute("suppliers", supplierService.getAllSupplier());
		return "suppliers";
	}

	@GetMapping("/admin/suppliers/add")
	public String getSupplierAdd(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "suppliersAdd";
	}

	@PostMapping("/admin/suppliers/add")
	public String postSupplierAdd(@ModelAttribute("supplier") Supplier supplier) {
		supplierService.addSupplier(supplier);
		return "redirect:/admin/suppliers";
	}

	@GetMapping("/admin/suppliers/delete/{id}")
	public String deleteSupplier(@PathVariable int id) {
		supplierService.removeSupplierById(id);
		return "redirect:/admin/suppliers";
	}

	@GetMapping("/admin/suppliers/update/{id}")
	public String updateSupplier(@PathVariable int id, Model model) {
		Optional<Supplier> supplier = supplierService.getSupplierById(id);
		if (supplier.isPresent()) {
			model.addAttribute("supplier", supplier.get());
			return "suppliersAdd";
		}
		return "404";

	}

}
