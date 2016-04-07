package ejb;

import ejb.TeamEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-23T20:57:13")
@StaticMetamodel(PlayerEntity.class)
public class PlayerEntity_ { 

    public static volatile ListAttribute<PlayerEntity, TeamEntity> teams;
    public static volatile SingularAttribute<PlayerEntity, String> playerName;
    public static volatile SingularAttribute<PlayerEntity, Long> id;

}