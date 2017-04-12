package com.inc.xy.rest.controller;

import com.inc.xy.model.repository.DocumentRepository;
import com.inc.xy.rest.vo.DocumentResponseVO;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity<DocumentResponseVO> save(
            @PathVariable(value = "model", required = true) String model, 
            @RequestBody(required = true) Document body){
        body.put("model", model);
        body.remove("id");
        
        documentRepository.save(body, model);        
        return new ResponseEntity(
                new DocumentResponseVO(body), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", 
            method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DocumentResponseVO> getById(
            @PathVariable(value = "model", required = true) String model,
            @PathVariable(value = "id", required = true) long id){
        Document doc = documentRepository.findByModelAndId(model, id);
        return doc != null ? 
                new ResponseEntity(new DocumentResponseVO(doc), HttpStatus.OK): 
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DocumentResponseVO> edit(
            @PathVariable(value = "model", required = true) String model,
            @PathVariable(value = "id", required = true) long id,
            @RequestBody Document body){
        body.put("id", id);
        body.put("model", model);
        documentRepository.save(body, model);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable(value = "model", required = true) String model,
            @PathVariable(value = "id", required = true) long id){
        boolean delete = documentRepository.delete(model, id);
        return delete ? new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
