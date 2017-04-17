package com.inc.xy.rest.v1.controller;

import com.inc.xy.model.mapper.MapperModelVO;
import com.inc.xy.model.repository.ModelRepository;
import com.inc.xy.rest.v1.vo.ModelResponseVO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caio
 */
@RestController
@RequestMapping("/xy-inc/models/v1")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;
    
    @Autowired
    private MapperModelVO mapper;

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<ModelResponseVO>> getAllModels() {
        List<Document> findAllModels = modelRepository.findAllModels();
        return findAllModels != null
                ? new ResponseEntity(mapper.mapFromList(findAllModels), HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{model}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Set<ModelResponseVO>> getColumnsByModel(
            @PathVariable(value = "model", required = true) String model){
        List<Document> columnsByModel = modelRepository.getColumnsByModel(model);
        Set<ModelResponseVO> response = new HashSet<ModelResponseVO>();
        if(columnsByModel != null){
            for(Document d: columnsByModel){
                for(Map.Entry<String, Object> entry: d.entrySet()){                    
                    response.add(new ModelResponseVO(entry.getKey()));
                }
            }
        }
        return columnsByModel != null
                ? new ResponseEntity(response, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
