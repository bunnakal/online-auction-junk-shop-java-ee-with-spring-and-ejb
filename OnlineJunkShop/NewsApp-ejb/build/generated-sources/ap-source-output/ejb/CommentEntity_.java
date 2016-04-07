package ejb;

import ejb.PostEntity;
import ejb.UserEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-23T20:57:13")
@StaticMetamodel(CommentEntity.class)
public class CommentEntity_ { 

    public static volatile SingularAttribute<CommentEntity, PostEntity> post;
    public static volatile SingularAttribute<CommentEntity, Long> commentBy;
    public static volatile SingularAttribute<CommentEntity, Date> commentDate;
    public static volatile SingularAttribute<CommentEntity, String> comment;
    public static volatile SingularAttribute<CommentEntity, Long> id;
    public static volatile SingularAttribute<CommentEntity, Long> commentOn;
    public static volatile SingularAttribute<CommentEntity, UserEntity> user;

}