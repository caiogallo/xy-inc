/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inc.xy.model.repository;

import com.inc.xy.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author caio
 */
public interface DocumentRepository extends MongoRepository<Document, String>{
    
}
