package com.inc.xy.model.repository;

import com.inc.xy.model.repository.model.SequenceId;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author caio
 */
@Repository
public class DocumentRepository{
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Document> findByModel(String model){
        BasicQuery query = new BasicQuery("{\"model\": \"" + model + "\"}");
        return mongoTemplate.find(query, Document.class);
    }
    
    public Document findByModelAndId(String model, long id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id).and("model").is(model));
        Document findOne = mongoTemplate.findOne(query, Document.class);
        return findOne;
    }
    
    public boolean save(final Document document, 
            final String model){
        
        Document doc = document;
        
        // verifica se documento existe para merge
        if(doc.get("id") != null){
            long id = doc.get("id", Long.class);
            Document findByModelAndId = findByModelAndId(model,id);            
            doc.put("_id", findByModelAndId.get("_id"));
        }else{
            doc.put("id", getNextSequence(model));
        }
        
        mongoTemplate.save(doc);
        return true;
    }
    
    public boolean delete(final String model,
            final long id){
        Query q = new Query(Criteria.where("model").is(model).and("id").is(id));
        return mongoTemplate.findAndRemove(q, Document.class) != null;
    }    

    private long getNextSequence(String model){
        Query query = new Query(Criteria.where("id").is(model));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        SequenceId seqId =
          mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        
        if(seqId == null){
            seqId = new SequenceId();
            seqId.setId(model);
            seqId.setSeq(1);
            
            mongoTemplate.save(seqId);
        }

        return seqId.getSeq();
    }
}
