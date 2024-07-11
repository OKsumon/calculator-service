package com.example.calculatorservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculatorController {

    private List<String> calculationHistory = new ArrayList<>();

    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        double result = a + b;
        calculationHistory.add(String.format("%.2f + %.2f = %.2f", a, b, result));
        return result;
    }

    @GetMapping("/minus")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        double result = a - b;
        calculationHistory.add(String.format("%.2f - %.2f = %.2f", a, b, result));
        return result;
    }

    @GetMapping("/product")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        double result = a * b;
        calculationHistory.add(String.format("%.2f * %.2f = %.2f", a, b, result)); // Corrected formatting
        return result;
    }

    @GetMapping("/divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        double result = a / b;
        
        if (result % 1 == 0) { // Check if the result is a whole number
            calculationHistory.add(String.format("%.0f / %.0f = %.0f", a, b, result)); // Format as integers
        } else {
            calculationHistory.add(String.format("%.2f / %.2f = %.2f", a, b, result)); // Keep as decimals
        }

        return result;
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return calculationHistory;
    }

    @GetMapping("/clear")
    public String clearHistory() {
        calculationHistory.clear();
        return "History cleared";
    }
}
