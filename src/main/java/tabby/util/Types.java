package tabby.util;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import static org.neo4j.graphdb.Direction.*;


public class Types {

    public static Direction directionFor(String type) {
        if (type.contains("<")) return INCOMING;
        if (type.contains(">")) return OUTGOING;
        return BOTH;
    }

    public static RelationshipType relationshipTypeFor(String name) {
        name = name.replace("<", "").replace(">", "");
        return name.trim().isEmpty() ? null : RelationshipType.withName(name);
    }

    public static boolean isAlias(Relationship relationship){
        if(relationship != null){
            RelationshipType type = relationship.getType();
            return "ALIAS".equals(type.name());
        }
        return false;
    }
}
