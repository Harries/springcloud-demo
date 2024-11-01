package com.et.controller;

import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
public class FunctionController {

    private final FunctionCatalog functionCatalog;

    public FunctionController(FunctionCatalog functionCatalog) {
        this.functionCatalog = functionCatalog;
    }

    @GetMapping("/function/{name}")
    public String applyFunction(@PathVariable String name, @RequestParam String input) {
        Function<String, String> function = functionCatalog.lookup(Function.class, name);
        if (function != null) {
            return function.apply(input);
        } else {
            return "Function not found";
        }
    }
}