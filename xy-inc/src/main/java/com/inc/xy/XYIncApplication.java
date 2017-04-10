package com.inc.xy;

import com.inc.xy.model.Document;
import com.inc.xy.model.repository.DocumentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author caio
 */
@SpringBootApplication
public class XYIncApplication implements CommandLineRunner{
    @Autowired 
    private DocumentRepository documentRepository;
            
    public static void main(String[] args) {
        SpringApplication.run(XYIncApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        documentRepository.deleteAll();
        
        Document d = new Document();
        d.getData().put("nome", "jose da silva");
        documentRepository.insert(d);
        
        List<Document> findAll = documentRepository.findAll();
        for(Document doc: findAll){
            System.out.println(doc.getData());
        }
        
        
        
    }
    
    
}
