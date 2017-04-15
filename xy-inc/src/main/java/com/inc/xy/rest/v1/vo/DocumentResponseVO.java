package com.inc.xy.rest.v1.vo;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@XmlRootElement
public class DocumentResponseVO implements Serializable{

    private static final long serialVersionUID = -2197603422286095530L;
    
    public Map data;

    public DocumentResponseVO(Map data) {
        this.data = data;
    }
    
    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentResponseVO other = (DocumentResponseVO) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
    
    
}
