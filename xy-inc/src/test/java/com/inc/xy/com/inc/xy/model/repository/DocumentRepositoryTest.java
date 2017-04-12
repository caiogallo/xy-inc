package com.inc.xy.com.inc.xy.model.repository;

import com.inc.xy.XYIncApplicationTest;
import com.inc.xy.model.repository.DocumentRepository;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author caio
 */
public class DocumentRepositoryTest extends XYIncApplicationTest{
    @Autowired
    private DocumentRepository documentRepository;
    
    @Test
    public void findByModel(){    
        documentRepository.clearModel(MODEL);
        
        documentRepository.save(mockProductDocument(), MODEL);
        
        int listSize = documentRepository.findByModel(MODEL).size();
        
        Assert.assertEquals(1, listSize);
        
    }
    
    @Test
    public void findByModelAndId(){
        documentRepository.clearModel(MODEL);
        
        Document savedDocument = documentRepository.save(mockProductDocument(), MODEL);
        
        long id = (long) savedDocument.get("id");

        Document findDocument = documentRepository.findByModelAndId(MODEL, id);
        
        Assert.assertNotNull(findDocument);
    }
    
    @Test
    public void save(){
        documentRepository.clearModel(MODEL);
        
        Document savedDocument = documentRepository.save(mockProductDocument(), MODEL);
        
        long id = (long) savedDocument.get("id");

        Document findDocument = documentRepository.findByModelAndId(MODEL, id);
        
        Assert.assertNotNull(findDocument);
    }
    
    @Test
    public void delete(){
        documentRepository.clearModel(MODEL);
        
        Document savedDocument = documentRepository.save(mockProductDocument(), MODEL);
        
        long id = (long) savedDocument.get("id");
        
        boolean delete = documentRepository.delete(MODEL, id);
       
        Assert.assertTrue(delete);
    }
    
    @Test
    public void clearModel(){
        documentRepository.clearModel(MODEL);
        
        documentRepository.save(mockProductDocument(), MODEL);
        
        documentRepository.clearModel(MODEL);
        
        int size = documentRepository.findByModel(MODEL).size();
        
        Assert.assertEquals(0, size);
        
    }
    
    private Document mockProductDocument(){
        Document doc = new Document();
        doc.put("name", "mouse");
        doc.put("price", 100);
        doc.put("created_at", "2017-04-17");
        doc.put("_model", MODEL);
        return doc;
    }
}
