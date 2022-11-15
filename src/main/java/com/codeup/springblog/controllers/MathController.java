package com.codeup.springblog.controllers;

//This controller should listen for requests for several routes that correspond to basic arithmetic
// operations and produce the result of the arithmetic.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MathController {
    @GetMapping("/add/{numA}/and/{numB}")
    @ResponseBody

    public int add(@PathVariable int numA, @PathVariable int numB) {
        return numA + numB;
    }
    @GetMapping("/subtract/{numA}/from/{numB}")
    @ResponseBody
    public int subtract(@PathVariable int numA, @PathVariable int numB) {
        return numA - numB;
    }
    @GetMapping("/multiply/{numA}/by/{numB}")
    @ResponseBody
    public int multiply(@PathVariable int numA, @PathVariable int numB) {
        return numA * numB;
    }
    @GetMapping("/divide/{numA}/by/{numB}")
    @ResponseBody
    public int divide(@PathVariable int numA, @PathVariable int numB) {
        return numA / numB;
    }
}