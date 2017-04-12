package com.inc.xy.rest.vo;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@XmlRootElement
public class DocumentResponseVO {
    public Map data;

    public DocumentResponseVO(Map data) {
        data = new HashMap(data);
    }
    
    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
    
    
}
