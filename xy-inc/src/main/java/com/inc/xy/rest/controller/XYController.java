package com.inc.xy.rest.controller;

import com.inc.xy.model.mapper.MapperDocumentVO;
import com.inc.xy.model.repository.DocumentRepository;
import com.inc.xy.rest.vo.DocumentResponseVO;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private static final String ID = "id";
    private static final String _MODEL = "_model";
    
    @Autowired
    private DocumentRepository documentRepository;
    
    @Autowired
    private MapperDocumentVO mapperDocumentVO;
    
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<DocumentResponseVO>> getAll(@PathVariable("model") String model){
        
        List<Document> findByModel = documentRepository.findByModel(model);
        return findByModel != null ?
                new ResponseEntity(mapperDocumentVO.mapFromList(findByModel), HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DocumentResponseVO> save(
            @PathVariable(value = "model", required = true) String model, 
            @RequestBody(required = true) Document body){
        
        body.put(_MODEL, model);
        body.remove(ID);        
        documentRepository.save(body, model);        
        return new ResponseEntity(mapperDocumentVO.mapFrom(body), HttpStatus.OK);
    }

    
    @RequestMapping(value = "/{id}", 
            method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DocumentResponseVO> getById(
            @PathVariable(value = "model", required = true) String model,
            @PathVariable(value = ID, required = true) long id){
        
        Document doc = documentRepository.findByModelAndId(model, id);
        return doc != null ? 
                new ResponseEntity(mapperDocumentVO.mapFrom(doc), HttpStatus.OK): 
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DocumentResponseVO> edit(
            @PathVariable(value = "model", required = true) String model,
            @PathVariable(value = ID, required = true) long id,
            @RequestBody Document body){
        
        boolean existe = documentRepository.findByModelAndId(model, id) != null;
        
        if(existe){
            body.put(ID, id);
            body.put(_MODEL, model);
            documentRepository.save(body, model);
            return new ResponseEntity(mapperDocumentVO.mapFrom(body), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public HttpEntity delete(
            @PathVariable(value = "model", required = true) String model,
            @PathVariable(value = ID, required = true) long id){
        
        boolean delete = documentRepository.delete(model, id);
        return delete ? 
                new ResponseEntity(HttpStatus.OK): 
                new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
