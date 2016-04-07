/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Bunna Kal
 */
@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(DATE)
    private Date commentDate;
    private String comment;
    private Long commentBy;
    private Long commentOn;
    
    @ManyToOne
    @JoinColumn(name = "commentBy",insertable=false, updatable=false)
    public UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    @ManyToOne
    @JoinColumn(name = "commentOn",insertable=false, updatable=false)
    public PostEntity post;

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
    
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentBy(Long commentBy) {
        this.commentBy = commentBy;
    }


    public void setCommentOn(Long commentOn) {
        this.commentOn = commentOn;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public String getComment() {
        return comment;
    }

    public Long getCommentBy() {
        return commentBy;
    }

    public Long getCommentOn() {
        return commentOn;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof CommentEntity)) {
            return false;
        }
        CommentEntity other = (CommentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinejunkshop.model.Comment[ id=" + id + " ]";
    }
    
}
