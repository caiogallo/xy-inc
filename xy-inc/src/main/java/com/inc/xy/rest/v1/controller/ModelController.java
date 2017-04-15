package com.inc.xy.rest.v1.controller;

import com.inc.xy.model.mapper.MapperModelVO;
import com.inc.xy.model.repository.DocumentRepository;
import com.inc.xy.model.repository.ModelRepository;
import com.inc.xy.rest.v1.vo.ModelResponseVO;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
