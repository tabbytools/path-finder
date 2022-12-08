package tabby.path;

import org.neo4j.graphalgo.EvaluationContext;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.traversal.Traverser;
import tabby.data.State;

import java.util.List;


@Deprecated
public class BidirectionalTraversalPathFinder extends BasePathFinder{

    public BidirectionalTraversalPathFinder(EvaluationContext context, PathExpander<State> expander, int maxDepth, boolean depthFirst) {
        super(context, expander, maxDepth, depthFirst);
    }

    @Override
    protected Traverser instantiateTraverser(Node start, List<Node> ends) {
        return null;
    }

    @Override
    protected Traverser instantiateTraverser(List<Node> starts, List<Node> ends) {
        return null;
    }

    @Override
    protected Traverser instantiateTraverser(Node start, Node end) {

        return null;
    }
}
