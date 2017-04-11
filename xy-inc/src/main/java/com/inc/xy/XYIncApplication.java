package com.inc.xy;

import ch.qos.logback.core.net.server.Client;
import com.inc.xy.model.Document;
import com.inc.xy.model.repository.DocumentRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        Document product = new Document();
        product.setModel("Product");
        product.getStructure().put("id", "int");
        product.getStructure().put("name", "String");
        product.getStructure().put("price", "decimal");
        product.getStructure().put("create_date", "date");
        documentRepository.insert(product);
        
        Document client = new Document();
        client.setModel("Client");
        client.getStructure().put("first_name", "String");
        client.getStructure().put("last_name", "String");
        documentRepository.insert(client);
        
        
        
        List<Document> findAll = documentRepository.findAll();
        for(Document d: findAll){
            System.out.println(d.getModel()+ ", " + d.getStructure());
        }
        
        
        
    }
    
    
}
