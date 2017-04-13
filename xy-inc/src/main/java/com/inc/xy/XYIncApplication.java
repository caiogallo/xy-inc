package com.inc.xy;

import com.inc.xy.model.repository.impl.DocumentRepositoryImpl;
import java.util.List;
import org.bson.Document;
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
    private DocumentRepositoryImpl documentRepository;
            
    public static void main(String[] args) {
        SpringApplication.run(XYIncApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        
        
        
    }
    
    
}
