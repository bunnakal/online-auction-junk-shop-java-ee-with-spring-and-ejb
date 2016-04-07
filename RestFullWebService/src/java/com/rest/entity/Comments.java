/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.id = :id"),
    @NamedQuery(name = "Comments.findByCommentDate", query = "SELECT c FROM Comments c WHERE c.commentDate = :commentDate"),
    @NamedQuery(name = "Comments.findByComment", query = "SELECT c FROM Comments c WHERE c.comment = :comment"),
    @NamedQuery(name = "Comments.findByCommentBy", query = "SELECT c FROM Comments c WHERE c.commentBy = :commentBy"),
    @NamedQuery(name = "Comments.findByCommentOn", query = "SELECT c FROM Comments c WHERE c.commentOn = :commentOn")})
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentDate")
    @Temporal(TemporalType.DATE)
    private Date commentDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentBy")
    private long commentBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentOn")
    private long commentOn;
    
    private Users user;
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "commentBy",referencedColumnName = "commentBy",insertable=false, updatable=false)
    public Users getUser() {
        return user;
    }
    
    public void setUser(Users user) {
        this.user = user;
    }
    
    @ManyToOne
    @JoinColumn(name = "commentOn",referencedColumnName = "commentOn",insertable=false, updatable=false)
    public Posts getPost() {
        return post;
    }
    
    public void setPost(Posts post) {
        this.post = post;
    }
    
    public Comments() {
    }

    public Comments(Integer id) {
        this.id = id;
    }

    public Comments(Integer id, Date commentDate, String comment, long commentBy, long commentOn) {
        this.id = id;
        this.commentDate = commentDate;
        this.comment = comment;
        this.commentBy = commentBy;
        this.commentOn = commentOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(long commentBy) {
        this.commentBy = commentBy;
    }

    public long getCommentOn() {
        return commentOn;
    }

    public void setCommentOn(long commentOn) {
        this.commentOn = commentOn;
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
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rest.entity.Comments[ id=" + id + " ]";
    }
    
}
