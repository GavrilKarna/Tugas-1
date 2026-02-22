package com.example.springmvclab.controller;

import com.example.springmvclab.model.Product;
import com.example.springmvclab.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatisticsController {

    private final ProductService productService;

    public StatisticsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {

        var products = productService.findAll();

        model.addAttribute("totalProducts", products.size());

        Map<String, Long> totalPerCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()
                ));
        model.addAttribute("totalPerCategory", totalPerCategory);

        Product mostExpensive = products.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
        model.addAttribute("mostExpensive", mostExpensive);

        Product cheapest = products.stream()
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
        model.addAttribute("cheapest", cheapest);

        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
        model.addAttribute("averagePrice", averagePrice);

        long lowStockCount = products.stream()
                .filter(p -> p.getStock() < 20)
                .count();
        model.addAttribute("lowStockCount", lowStockCount);

        return "statistics";
    }
}