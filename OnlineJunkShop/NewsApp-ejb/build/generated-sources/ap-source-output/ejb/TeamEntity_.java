package ejb;

import ejb.PlayerEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-23T20:57:13")
@StaticMetamodel(TeamEntity.class)
public class TeamEntity_ { 

    public static volatile SingularAttribute<TeamEntity, String> teamName;
    public static volatile ListAttribute<TeamEntity, PlayerEntity> players;
    public static volatile SingularAttribute<TeamEntity, Long> id;

}