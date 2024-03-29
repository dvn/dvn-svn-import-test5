/*
 * Dataverse Network - A web application to distribute, share and analyze quantitative data.
 * Copyright (C) 2007
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses
 * or write to the Free Software Foundation,Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

/*
 * TemplateFieldSelectValue.java
 *
 * Created on August 9, 2006, 3:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.harvard.iq.dvn.core.study;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;

/**
 *
 * @author Ellen Kraffmiller
 */
@Entity
public class TemplateFieldSelectValue implements java.io.Serializable {

    /** Creates a new instance of TemplateFieldSelectValue */
    public TemplateFieldSelectValue() {
    }

    /**
     * Holds value of property id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Holds value of property displayOrder.
     */
    private int displayOrder;

    /**
     * Getter for property order.
     * @return Value of property order.
     */
    public int getDisplayOrder() {
        return this.displayOrder;
    }

    /**
     * Setter for property order.
     * @param order New value of property order.
     */
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * Holds value of property value.
     */
    private String value;

    /**
     * Getter for property value.
     * @return Value of property value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Setter for property value.
     * @param value New value of property value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Holds value of property templateField.
     */
    @ManyToOne
    @JoinColumn(nullable=false)
    private TemplateField templateField;

    /**
     * Getter for property templateField.
     * @return Value of property templateField.
     */
    public TemplateField getTemplateField() {
        return this.templateField;
    }

    /**
     * Setter for property templateField.
     * @param templateField New value of property templateField.
     */
    public void setTemplateField(TemplateField templateField) {
        this.templateField = templateField;
    }
  /**
     * Holds value of property version.
     */
    @Version
    private Long version;

    /**
     * Getter for property version.
     * @return Value of property version.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Setter for property version.
     * @param version New value of property version.
     */
    public void setVersion(Long version) {
        this.version = version;
    }    
    
  public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemplateFieldSelectValue)) {
            return false;
        }
        TemplateFieldSelectValue other = (TemplateFieldSelectValue)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }               

}
