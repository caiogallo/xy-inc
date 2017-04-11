package com.inc.xy.rest.controller;

import com.inc.xy.model.Document;
import com.inc.xy.model.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caio
 */
@RestController
@RequestMapping("/xy-inc/v1/{model}")
public class XYController {
    
    @Autowired
    private DocumentRepository documentRepository;
    
    @RequestMapping("/hello")
    public void hello(){
        System.out.println("hello");
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getAll(@PathVariable("model") String model){
        Document findByModel = documentRepository.findByModel(model);
        System.out.println(findByModel);
    }
}
