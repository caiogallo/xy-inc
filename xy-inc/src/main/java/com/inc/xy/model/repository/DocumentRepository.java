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
import org.springframework.stereotype.Repository;

/**
 *
 * @author caio
 */
@Repository
public class DocumentRepository{
    private static final String _MODEL = "_model";
    private static final String ID = "id";
    private static final String _ID = "_id";
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Document> findByModel(final String model){
        Query query = new Query();
        query.addCriteria(Criteria.where(_MODEL).is(model));
        return mongoTemplate.find(query, Document.class);
    }
    
    public Document findByModelAndId(final String model, final long id){
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id).and(_MODEL).is(model));
        Document findOne = mongoTemplate.findOne(query, Document.class);
        return findOne;
    }

    
    public Document save(final Document document, 
            final String model){
        Document doc = document;        
        doc = verifyInsertOrUpdate(doc, model);                
        mongoTemplate.save(doc);     
        return document;
    }

    public boolean delete(final String model, final long id){
        Query q = new Query(Criteria.where(_MODEL).is(model).and(ID).is(id));
        return mongoTemplate.findAndRemove(q, Document.class) != null;
    }   
    
    public void clearModel(final String model){
        Query q = new Query(Criteria.where(_MODEL).is(model));
        mongoTemplate.remove(q, Document.class);
    }

    private Document verifyInsertOrUpdate(final Document doc, final String model) {
        Document document = doc;
        if(document.get(ID) != null){
            long id = document.get(ID, Long.class);
            Document findByModelAndId = findByModelAndId(model,id);            
            document.put(_ID, findByModelAndId.get(_ID));
        }else{
            document.put(ID, getNextSequence(model));
        }
        return document;
    }
    
    
    private long getNextSequence(final String model){
        Query query = new Query(Criteria.where(ID).is(model));

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
