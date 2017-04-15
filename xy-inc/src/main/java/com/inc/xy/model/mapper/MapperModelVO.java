package com.inc.xy.model.mapper;

import com.inc.xy.rest.v1.vo.ModelResponseVO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 *
 * @author caio
 */
@Component
public class MapperModelVO{
    public ModelResponseVO mapFrom(Document document){
        ModelResponseVO response = new ModelResponseVO();
        response.setName(document.get("_model", String.class));
        return response;
    }
    
    public Set<ModelResponseVO> mapFromList(List<Document> documents){
        Set<ModelResponseVO> responses = new HashSet<ModelResponseVO>();
        if(documents != null){
            for(Document doc: documents){
                responses.add(mapFrom(doc));
            }
        }
        return responses;
    }
}
