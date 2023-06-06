package com.example.producttask.controller;

import com.example.producttask.model.Product;
import com.example.producttask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
@Autowired
    private  ProductRepository productRepository;


    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseBody
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setImage(updatedProduct.getImage());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);
    }
}
