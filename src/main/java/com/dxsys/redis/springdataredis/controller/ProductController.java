package com.dxsys.redis.springdataredis.controller;

import com.dxsys.redis.springdataredis.entity.Product;
import com.dxsys.redis.springdataredis.repository.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Slf4j
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productDao.save(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @GetMapping("get/{id}")
    @Cacheable(key = "#id", value = "Product", unless = "#result.price > 1000")
    public Product getProduct(@PathVariable String id) {
        log.info("called from db" + id);
        return productDao.findProductById(id);
    }

    @DeleteMapping("delete/{id}")
    @CacheEvict(key = "#id", value = "Product")
    public String remove(@PathVariable String  id) {
        return productDao.deleteProduct(id);
    }

}
