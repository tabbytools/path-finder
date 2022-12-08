package tabby.evaluator.judgment;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.Evaluation;
import tabby.data.State;

import java.util.List;


public interface Judgment {

    Evaluation judge(Path path, State state, List<Node> endNodes, int maxDepth);
}
