package com.inc.xy.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caio
 */
@RestController
@RequestMapping("/xy-inc/v1")
public class XYController {
    
    @RequestMapping("/hello")
    public void hello(){
        System.out.println("hello");
    }

}
