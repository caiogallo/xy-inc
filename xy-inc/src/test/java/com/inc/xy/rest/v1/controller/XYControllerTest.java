package com.inc.xy.rest.v1.controller;

import com.inc.xy.XYIncApplicationTest;
import static com.inc.xy.XYIncApplicationTest.MODEL;
import com.inc.xy.model.repository.DocumentRepository;
import com.inc.xy.rest.v1.vo.DocumentResponseVO;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author caio
 */
public class XYControllerTest extends XYIncApplicationTest{
    @Autowired
    private XYController controller;
    
    @Autowired
    private DocumentRepository documentRepository;
    
    @Test
    public void getAll(){
        documentRepository.clearModel(MODEL);
        
        controller.save(MODEL, mockProductDocument());
        
        int size = controller.getAll(MODEL).getBody().size();
        
        Assert.assertEquals(1, size);
    }
    
    @Test
    public void getById(){
        documentRepository.clearModel(MODEL);
        
        HttpEntity<DocumentResponseVO> save = controller.save(MODEL, mockProductDocument());
        
        long id = (long) save.getBody().getData().get("id");
        
        HttpEntity<DocumentResponseVO> byId = controller.getById(MODEL, id);
        
        Assert.assertNotNull(byId);
    }
    
    @Test
    public void save(){
        documentRepository.clearModel(MODEL);
        
        HttpEntity<DocumentResponseVO> save = controller.save(MODEL, mockProductDocument());
        
        Assert.assertNotNull(save);
    }
    
    @Test
    public void delete(){
        documentRepository.clearModel(MODEL);
        
        HttpEntity<DocumentResponseVO> save = controller.save(MODEL, mockProductDocument());
        
        long id = (long) save.getBody().getData().get("id");
        
        HttpEntity delete = controller.delete(MODEL, id);
        
        ResponseEntity entity = (ResponseEntity) delete;
        
        Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK); 
    }
    
    @Test
    public void edit(){
        documentRepository.clearModel(MODEL);
        
        HttpEntity<DocumentResponseVO> save = controller.save(MODEL, mockProductDocument());
        
        long id = (long) save.getBody().getData().get("id");
        
        Document mockProductDocument = mockProductDocument();
        mockProductDocument.put("name", "keyboard");
        
        controller.edit(MODEL, id, mockProductDocument);
        
        HttpEntity<DocumentResponseVO> byId = controller.getById(MODEL, id);
        
        Assert.assertEquals("keyboard", byId.getBody().getData().get("name"));
    }
    
    private DocumentResponseVO mockDocumentResponseVO(){
        Map map = new HashMap();
        map.put("name", "produto_teste");
        map.put("price", 10.67);
        DocumentResponseVO vo = new DocumentResponseVO(map);
        return vo;
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
