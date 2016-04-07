package ejb;

import ejb.Address;
import ejb.CommentEntity;
import ejb.UserEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-23T20:57:13")
@StaticMetamodel(PostEntity.class)
public class PostEntity_ { 

    public static volatile SingularAttribute<PostEntity, Address> address;
    public static volatile ListAttribute<PostEntity, CommentEntity> comments;
    public static volatile SingularAttribute<PostEntity, Long> inCategory;
    public static volatile SingularAttribute<PostEntity, Long> postBy;
    public static volatile SingularAttribute<PostEntity, String> shortDescription;
    public static volatile SingularAttribute<PostEntity, String> fullDescription;
    public static volatile SingularAttribute<PostEntity, String> phone;
    public static volatile SingularAttribute<PostEntity, Double> price;
    public static volatile SingularAttribute<PostEntity, String> postPhoto;
    public static volatile SingularAttribute<PostEntity, Date> postDate;
    public static volatile SingularAttribute<PostEntity, String> postTitle;
    public static volatile SingularAttribute<PostEntity, Long> id;
    public static volatile SingularAttribute<PostEntity, UserEntity> user;
    public static volatile SingularAttribute<PostEntity, String> email;
    public static volatile SingularAttribute<PostEntity, String> status;

}