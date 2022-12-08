package tabby.evaluator.judgment;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.Evaluation;
import tabby.data.State;

import java.util.List;


public class CommonJudgment implements Judgment{

    @Override
    public Evaluation judge(Path path, State state, List<Node> endNodes, int maxDepth) {
        boolean includes = true;
        boolean continues = true;
        int length = path.length();
        if(length == 0) return Evaluation.of(false, true);

        Node node = path.endNode();

        if(length >= maxDepth){
            continues = false; 
            if(endNodes != null && !endNodes.contains(node)){
                includes = false; 
            }
        }else if(endNodes != null && endNodes.contains(node)){
            
            continues = false;
        } else {
            includes = false;
        }

        return Evaluation.of(includes, continues);
    }
}
