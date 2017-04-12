package com.inc.xy.model.mapper;

import com.inc.xy.rest.vo.DocumentResponseVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 *
 * @author caio
 */
@Component
public class MapperDocumentVO{
    public DocumentResponseVO mapFrom(Document document){
        DocumentResponseVO response = new DocumentResponseVO(new HashMap());
        if(document != null){
            for(Map.Entry<String,Object> pair : document.entrySet()){
                if(!pair.getKey().startsWith("_")){
                    response.getData().put(pair.getKey(), pair.getValue());
                }
            }
        }
        return response;
    }
    
    public List<DocumentResponseVO> mapFromList(List<Document> documents){
        List<DocumentResponseVO> responses = new ArrayList<DocumentResponseVO>();
        if(documents != null){
            for(Document doc: documents){
                responses.add(mapFrom(doc));
            }
        }
        return responses;
    }
}
