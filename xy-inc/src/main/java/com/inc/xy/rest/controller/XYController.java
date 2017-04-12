package com.inc.xy.rest.controller;

import com.inc.xy.model.repository.DocumentRepository;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Document> getAll(@PathVariable("model") String model){
        List<Document> findByModel = documentRepository.findByModel(model);
        return findByModel;
    }
    
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Document save(@PathVariable(value = "model", required = true) String model, 
            @RequestBody(required = true) Document body){
        body.put("model", model);
        documentRepository.save(body, model);
        return null;
    }
    
    @RequestMapping(value = "/{id}", 
            method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Document getById(
            @PathVariable(value = "model", required = true) String model,
            @PathVariable(value = "id", required = true) long id){
        Document doc = documentRepository.findByModelAndId(model, id);
        return doc;
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Document edit(@PathVariable(value = "model", required = true) String model,
            @PathVariable(value = "id", required = true) long id,
            @RequestBody Document body){
        body.put("id", id);
        body.put("model", model);
        documentRepository.save(body, model);
        return body;
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "model", required = true) String model,
            @PathVariable(value = "id", required = true) long id){
        documentRepository.delete(model, id);
    }
}
