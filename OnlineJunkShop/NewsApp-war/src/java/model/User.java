/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author SkinnyGirl
 */
public class User implements Serializable{

    public User() {

    }
    public Long id;
   
    @Size(min=2, max=30)
    private String firstName;

    @Size(min=2, max=30)
    private String lastName;

    private String gender;
    
    private Date birthdate;

    @NotEmpty(message = "Please enter your phone number.")
    private String phone;
    
    @NotEmpty(message = "Please enter your email address.")
    @Email
    private String email;

    @Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
    private String password;

    private String avata;

    private String privilege;
    
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvata() {
        return avata;
    }

    public String getPhone() {
        return phone;
    }
}
