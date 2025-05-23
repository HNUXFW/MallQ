package com.example.mallq.controller;

import com.example.mallq.entity.Product;
import com.example.mallq.repository.ProductRepository;
import com.example.mallq.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "商品管理", description = "商品相关的API")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @Operation(summary = "获取所有商品", description = "获取系统中所有商品的列表")
    @GetMapping
    public Result<List<Product>> getAllProducts() {
        return Result.success(productRepository.findAll());
    }

    @Operation(summary = "创建商品", description = "创建新商品")
    @PostMapping
    public Result<Product> createProduct(
            @Parameter(description = "商品信息") @RequestBody Product product) {
        return Result.success(productRepository.save(product));
    }

    @Operation(summary = "获取商品详情", description = "根据商品ID获取商品详细信息")
    @GetMapping("/{id}")
    public Result<Product> getProduct(
            @Parameter(description = "商品ID") @PathVariable Long id) {
        return productRepository.findById(id)
                .map(Result::success)
                .orElse(Result.error(404, "商品不存在"));
    }
} 