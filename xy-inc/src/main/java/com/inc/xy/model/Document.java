
package com.inc.xy.model;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.Id;

/**
 *
 * @author caio
 */
public class Document {
    @Id
    private String id;
    
    private Map data;

    public Map getData() {
        if(data == null){
            this.data = new HashMap();
        }
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Document{" + "data=" + data + '}';
    }
    
    
}
