package com.inc.xy.model.repository.impl;

import com.inc.xy.model.repository.ModelRepository;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author caio
 */
@Repository
public class ModelRepositoryImpl implements ModelRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    
    private static final String _MODEL = "_model";
    private static final String ID = "id";
    private static final String _ID = "_id";

    @Override
    public List<Document> findAllModels() {
        Query query = new Query(Criteria.where(_MODEL).ne(null));
        query.fields().exclude(_ID);
        query.fields().include(_MODEL);
        return mongoTemplate.find(query, Document.class);
    }
}
