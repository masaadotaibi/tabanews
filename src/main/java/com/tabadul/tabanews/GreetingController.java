package com.tabadul.tabanews;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/greeting")
    // this way, by default our /greeting endpoint can receive any http method request (GET, POST, PUT, etc.)
//    @RequestMapping(method = RequestMethod.GET, path = "/greeting")
    // GetMapping uses @RequestMapping internally with (method = RequestMethod.GET) set, so the above annotation is
    // identical to the below uncommented one
    @GetMapping("/greeting")
    Greeting greeting(@RequestParam(defaultValue = "World") String name) {
        // no need to pass (value = "name") in @RequestParam, as it can be defaulted to the name of the method parameter
        // as we are using jackson dependency (imported by default with spring-boot-starter-web dependency), this object
        // will automatically be serialized into JSON payload

        // below method is used if we don't want to enforce the user to enter a query parameter. This way, the user of
        // our end point can use /greeting without passing query parameters, and if he does so, he will receive response
        // {"id":1,"content":"Hello, null"}      Notice that the name query parameter is replaced by null by default
//        Greeting greeting(@RequestParam(required = false) String name) {
        // below method is used if we want to enforce the user using our endpoint to pass a query parameter string
//        Greeting greeting(@RequestParam String name) {
        // below method is used if we want to have a default value to return if there is no passed query parameters, but
        // in our case, we require a query parameter, so we would enforce it the way we did. Also, no need to mark the
        // method as a public.
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
