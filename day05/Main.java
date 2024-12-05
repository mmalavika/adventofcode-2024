import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static String[] rulesArr = Input.RULES.split("\\r\\n");
    public static String[] pagesArr = Input.PAGES.split("\\r\\n");

    public static Map<Integer, ArrayList<Integer>> rulesMap = new HashMap<>();

    public static void createRuleMap() {
        for(int i = 0; i < rulesArr.length; i++) {
            List<Integer> rStr = Arrays.stream(rulesArr[i].split("\\|")).mapToInt(n -> Integer.parseInt(n)).boxed().toList();
            ArrayList<Integer> valList = rulesMap.getOrDefault(rStr.get(0), new ArrayList<>());
            valList.add(rStr.get(1));
            rulesMap.put(rStr.get(0), valList);
        }
    }

    public static int partOne() {
        int total = 0;
        for(int i = 0; i < pagesArr.length; i++) {
            List<Integer> pNums = Arrays.stream(pagesArr[i].split(",")).mapToInt(n -> Integer.parseInt(n)).boxed().collect(Collectors.toList());
            
            boolean isValid = true;
            outerloop:
            for(Integer pNum : pNums) {
                if(rulesMap.containsKey(pNum)) {
                    var rulesList = rulesMap.getOrDefault(pNum, new ArrayList<>());
                    var idx = pNums.indexOf(pNum);
                    
                    List<Integer> numsBeforeThis = new ArrayList<Integer>(pNums.subList(0, idx));
                    
                    for(Integer ns : numsBeforeThis) {
                        if(rulesList.contains(ns)) { 
                            isValid = false;
                            break outerloop;
                        }
                    }
                }
            }
            if(isValid) {
                int idx = (pNums.size()/2);
                var mid = pNums.get(idx);
                total += mid;
            }
        }
        return total;
    }

    public static int partTwo() {
        int total = 0;
        for(int p = 0; p < pagesArr.length; p++) {
            List<Integer> pNums = Arrays.stream(pagesArr[p].split(",")).mapToInt(n -> Integer.parseInt(n)).boxed().collect(Collectors.toList());

            boolean isValid = true;
            outerloop:
            for(Integer pNum : pNums) {
                if(rulesMap.containsKey(pNum)) {
                    var rulesList = rulesMap.getOrDefault(pNum, new ArrayList<>());
                    var idx = pNums.indexOf(pNum);
                    
                    List<Integer> numsBeforeThis = new ArrayList<Integer>(pNums.subList(0, idx));
                    
                    for(Integer ns : numsBeforeThis) {
                        if(rulesList.contains(ns)) {
                            isValid = false;
                            break outerloop;
                        }
                    }
                }
            } 
            if(!isValid) {
                List<Integer> pNumsSorted = new ArrayList<>(pNums);
                for(int i = 1; i < pNumsSorted.size(); i++) {
                    int thisNum = pNumsSorted.get(i);
                    List<Integer> rulesList = rulesMap.getOrDefault(thisNum, new ArrayList<Integer>());

                    for(int j = 0; j < i; j++) {
                        int prev = pNumsSorted.get(j);
                        if(rulesList.contains(prev)) {
                            Collections.swap(pNumsSorted, i, j);
                        }
                    }
                }
                int idx = (pNumsSorted.size()/2);
                var mid = pNumsSorted.get(idx);
                total += mid;
            }
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        createRuleMap();
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
