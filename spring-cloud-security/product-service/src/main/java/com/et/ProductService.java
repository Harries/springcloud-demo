package com.et;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    // 构造函数，初始化一些静态数据
    public ProductService() {
        products.add(new Product(1L, "Product 1", 10.99, "Description for Product 1"));
        products.add(new Product(2L, "Product 2", 15.49, "Description for Product 2"));
        products.add(new Product(3L, "Product 3", 7.99, "Description for Product 3"));
    }

    // 获取所有产品
    public List<Product> getAllProducts() {
        return products;
    }

    // 根据ID获取产品
    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    // 添加新产品
    public Product addProduct(Product product) {
        product.setId((long) (products.size() + 1)); // 设置ID为下一个可用ID
        products.add(product);
        return product;
    }

    // 更新产品
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        return product;
    }

    // 删除产品
    public void deleteProduct(Long id) {
        Product product = getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        products.remove(product);
    }
}