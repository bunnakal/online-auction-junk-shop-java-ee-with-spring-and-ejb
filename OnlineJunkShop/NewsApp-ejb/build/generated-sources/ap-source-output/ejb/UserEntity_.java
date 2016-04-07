package ejb;

import ejb.CommentEntity;
import ejb.PostEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-23T20:57:13")
@StaticMetamodel(UserEntity.class)
public class UserEntity_ { 

    public static volatile SingularAttribute<UserEntity, String> lastName;
    public static volatile SingularAttribute<UserEntity, String> firstName;
    public static volatile SingularAttribute<UserEntity, String> password;
    public static volatile SingularAttribute<UserEntity, Date> birthdate;
    public static volatile ListAttribute<UserEntity, CommentEntity> comments;
    public static volatile SingularAttribute<UserEntity, String> gender;
    public static volatile SingularAttribute<UserEntity, String> phone;
    public static volatile SingularAttribute<UserEntity, String> avata;
    public static volatile SingularAttribute<UserEntity, String> privilege;
    public static volatile SingularAttribute<UserEntity, Long> id;
    public static volatile ListAttribute<UserEntity, PostEntity> posts;
    public static volatile SingularAttribute<UserEntity, String> email;

}