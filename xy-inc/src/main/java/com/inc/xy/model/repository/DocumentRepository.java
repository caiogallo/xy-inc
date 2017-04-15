/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inc.xy.model.repository;

import java.util.List;
import org.bson.Document;
/**
 *
 * @author caio
 */
public interface DocumentRepository {

    void clearModel(final String model);

    boolean delete(final String model, final long id);

    List<Document> findByModel(final String model);

    Document findByModelAndId(final String model, final long id);

    Document save(final Document document, final String model);
    
}
