package org.training.turkcell.order.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.turkcell.order.rest.models.Person;

@RestController
@RequestMapping("/hello")
public class RestPlayGroundRestController {

    // @RequestMapping(value = "/hello/hello1",method = RequestMethod.GET)
    @GetMapping("/hello1")
    public String hello1() {
        return "Hello world - GET";
    }

    @PostMapping("/hello1")
    public String hello1a() {
        return "Hello world - POST";
    }

    @GetMapping("/hello2/{abc}/{surname}")
    public String hello2(@PathVariable("abc") String name,
                         @PathVariable String surname) {
        return "Hello 2 - " + name + " " + surname;
    }


    @GetMapping("/hello3")
    public String hello3(@RequestParam String name,
                         @RequestParam String surname) {
        return "Hello 2 - " + name + " " + surname;
    }

    @PostMapping("/hello4")
    public String hello3(@RequestBody Person personParam) {
        return "Hello 3 - " + personParam;
    }




}
