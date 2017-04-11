package com.inc.xy.model.repository;

import com.inc.xy.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author caio
 */
public interface DocumentRepository extends MongoRepository<Document, String>{
    public Document findByModel(String model);
}
