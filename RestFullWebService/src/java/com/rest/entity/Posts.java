/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Bunna Kal
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findById", query = "SELECT p FROM Posts p WHERE p.id = :id"),
    @NamedQuery(name = "Posts.findByPostTitle", query = "SELECT p FROM Posts p WHERE p.postTitle = :postTitle"),
    @NamedQuery(name = "Posts.findByPostDate", query = "SELECT p FROM Posts p WHERE p.postDate = :postDate"),
    @NamedQuery(name = "Posts.findByShortDescription", query = "SELECT p FROM Posts p WHERE p.shortDescription = :shortDescription"),
    @NamedQuery(name = "Posts.findByFullDescription", query = "SELECT p FROM Posts p WHERE p.fullDescription = :fullDescription"),
    @NamedQuery(name = "Posts.findByPostPhoto", query = "SELECT p FROM Posts p WHERE p.postPhoto = :postPhoto"),
    @NamedQuery(name = "Posts.findByStatus", query = "SELECT p FROM Posts p WHERE p.status = :status"),
    @NamedQuery(name = "Posts.findByPrice", query = "SELECT p FROM Posts p WHERE p.price = :price"),
    @NamedQuery(name = "Posts.findByPhone", query = "SELECT p FROM Posts p WHERE p.phone = :phone"),
    @NamedQuery(name = "Posts.findByEmail", query = "SELECT p FROM Posts p WHERE p.email = :email"),
    @NamedQuery(name = "Posts.findByStreet", query = "SELECT p FROM Posts p WHERE p.street = :street"),
    @NamedQuery(name = "Posts.findByLocation", query = "SELECT p FROM Posts p WHERE p.location = :location"),
    @NamedQuery(name = "Posts.findByInCategory", query = "SELECT p FROM Posts p WHERE p.inCategory = :inCategory"),
    @NamedQuery(name = "Posts.findByPostBy", query = "SELECT p FROM Posts p WHERE p.postBy = :postBy")})
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "postTitle")
    private String postTitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postDate")
    @Temporal(TemporalType.DATE)
    private Date postDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "shortDescription")
    private String shortDescription;
    @Size(max = 500)
    @Column(name = "fullDescription")
    private String fullDescription;
    @Size(max = 250)
    @Column(name = "postPhoto")
    private String postPhoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "price")
    private String price;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "email")
    private String email;
    @Size(max = 400)
    @Column(name = "street")
    private String street;
    @Size(max = 400)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inCategory")
    private long inCategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postBy")
    private long postBy;

    private List<Comments> comments;
    
    private Users user;
    
//    @Embedded
//    private Address address;
//    public Address getAddress() {
//        return address;
//    }
//    public void setAddress() {
//        this.address = address;
//    }

    public int getCountComment() {
        return this.getComments().size();
    }

    // relationship to users many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postBy", referencedColumnName = "postBy",insertable = false, updatable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    // relationship to comments one to many
    @OneToMany(mappedBy = "post", cascade={CascadeType.ALL})
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
    public Posts() {
    }

    public Posts(Integer id) {
        this.id = id;
    }

    public Posts(Integer id, String postTitle, Date postDate, String shortDescription, String status, String price, String phone, String email, long inCategory, long postBy) {
        this.id = id;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.shortDescription = shortDescription;
        this.status = status;
        this.price = price;
        this.phone = phone;
        this.email = email;
        this.inCategory = inCategory;
        this.postBy = postBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(String postPhoto) {
        this.postPhoto = postPhoto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getInCategory() {
        return inCategory;
    }

    public void setInCategory(long inCategory) {
        this.inCategory = inCategory;
    }

    public long getPostBy() {
        return postBy;
    }

    public void setPostBy(long postBy) {
        this.postBy = postBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rest.entity.Posts[ id=" + id + " ]";
    }
    
}
