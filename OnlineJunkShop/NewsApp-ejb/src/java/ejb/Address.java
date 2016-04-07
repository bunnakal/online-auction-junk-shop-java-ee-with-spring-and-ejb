/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Bunna Kal
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable{
    private String street;
    private String localtion;
    
    public void setStreet(String street) {
        this.street = street;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }
    
    @Column(name="street",nullable = true)
    public String getStreet() {
        return street;
    }
    
    @Column(name="location",nullable = true)
    public String getLocaltion() {
        return localtion;
    }
    
}
