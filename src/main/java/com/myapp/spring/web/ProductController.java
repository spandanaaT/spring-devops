package com.myapp.spring.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.spring.model.Product;
import com.myapp.spring.repository.ProductRepository;

@RepositoryRestController
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository repository;

	@RequestMapping(method = RequestMethod.GET, value = "products")
	public @ResponseBody ResponseEntity<?> getProducts() {
		logger.info("Processing products request");

		List<Product> products = repository.findAll();

		CollectionModel<Product> resources = CollectionModel.of(products);

		resources.add(linkTo(ProductController.class).withSelfRel());

		// add other links as needed

		return ResponseEntity.ok(resources);
	}

}