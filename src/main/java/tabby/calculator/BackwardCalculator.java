package tabby.calculator;

import tabby.util.PositionHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BackwardCalculator implements Calculator{

    @Override
    public int[] v2(int[][] callSite, Set<Integer> polluted) {
        Set<Integer> newPolluted = new HashSet<>();

        for(int p : polluted){
            int pos = p + 1;
            if(pos < callSite.length && pos >= 0){
                int[] call = callSite[pos];
                if(PositionHelper.isNotPollutedPosition(call)) return null;
                newPolluted.addAll(Arrays.stream(call).boxed().collect(Collectors.toSet()));
            }else if(p == PositionHelper.SOURCE){
                
                newPolluted.add(PositionHelper.SOURCE);
            } else{
                return null;
            }
        }

        newPolluted.remove(PositionHelper.NOT_POLLUTED_POSITION);
        return newPolluted.stream().mapToInt(Integer::intValue).toArray();
    }
}
