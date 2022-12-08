package tabby.expander.processor;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import tabby.calculator.Calculator;
import tabby.data.State;
import tabby.util.JsonHelper;
import tabby.util.Types;


public class CommonProcessor extends BaseProcessor{

    private boolean isAbstract = false;
    private boolean isFromAbstractClass = false;

    @Override
    public void init(Node node, State preState, Relationship lastRelationship, Calculator calculator) {
        super.init(node, preState, lastRelationship, calculator);

//        Map<String, Object> properties = node.getProperties("IS_ABSTRACT", "IS_FROM_ABSTRACT_CLASS");
//        this.isAbstract = (boolean) properties.getOrDefault("IS_ABSTRACT", false);
//        this.isFromAbstractClass = (boolean) properties.getOrDefault("IS_FROM_ABSTRACT_CLASS", false);
    }

    @Override
    public Relationship process(Relationship next) {
        Relationship ret = null;
        String nextId = next.getId() + "";
        if(Types.isAlias(next)){
            nextState.put(nextId, polluted.stream().mapToInt(Integer::intValue).toArray());
            nextState.addAliasEdge(next.getId());
            ret = next;
        }else{
            String pollutedStr = (String) next.getProperty("POLLUTED_POSITION");
            if(pollutedStr == null) return ret;
            int[][] callSite = JsonHelper.parse(pollutedStr);



            int[] nextPos = calculator.calculate(callSite, polluted);
            if(nextPos != null && nextPos.length > 0){
                nextState.put(nextId, nextPos);
                ret = next;
            }
        }
        return ret;
    }

    @Override
    public boolean isNeedProcess() {
        return true;
    }

    @Override
    public State getNextState() {
        return nextState;
    }
}
