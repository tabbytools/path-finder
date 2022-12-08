package tabby.calculator;

import java.util.Set;


public interface Calculator {

    int[] v2(int[][] callSite, Set<Integer> polluted);

    default int[] calculate(int[][] callSite, Set<Integer> polluted){
        try{
            return v2(callSite, polluted);
        }catch (Exception e){
        }
        return new int[0];
    }

}
