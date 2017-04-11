
package com.inc.xy.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;

/**
 *
 * @author caio
 */
public class Document {
    @Id
    private String model;
    
    private Map structure;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Map getStructure() {
        if(structure == null){
            this.structure = new HashMap();
        }
        return structure;
    }

    public void setStructure(Map structure) {
        this.structure = structure;
    }
    
    
}
