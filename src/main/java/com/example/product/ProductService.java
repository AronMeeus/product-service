package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        //return products;
        List<Product> topics = new ArrayList<>();
        productRepository.findAll()
                .forEach(topics::add);
        return topics;
    }

    public Product getProduct(String id) {
        //return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        //topics.add(topic);
        productRepository.save(product);
    }

    public void updateProduct(String id, Product product) {
        /* for (int i=0; i < topics.size(); i++) {
            Topic t = topics.get(i);
            if (t.getId().equals(id)) {
                topics.set(i, topic);
                return;
            }
        } */
        productRepository.save(product);
    }

    public void deleteProduct(Product id) {
        // topics.removeIf(t -> t.getId().equals(id));
        productRepository.delete(id);
    }
}
