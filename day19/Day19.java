import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {

    public static String[] patterns = Input19.PATTERNS.split(", ");
    public static String[] designs = Input19.DESIGNS.split("\\r\\n");
    public static Map<String, Long> store = new HashMap<>();

    public static long partOne() {
        long count = 0;
        for(var des : designs) {
            if(doesPatternMatch(des)) count++;
        }
        return count;
    }

    public static boolean doesPatternMatch(String des) {
        for(var pat : patterns) {
            if(des.equals(pat)) {
                return true;
            } else if(des.startsWith(pat) && doesPatternMatch(des.substring(pat.length()))) {
                return true;
            }
        }
        return false;
    }

    public static long partTwo() {
        List<String> designsMatch = new ArrayList<>();
        for(var des : designs) {
            if(doesPatternMatch(des)) designsMatch.add(des);
        }

        for(var desM : designsMatch) {
            getAllCombinations(desM);
        }

        long total = 0;
        for(var desM : designsMatch) {
            total += store.get(desM);
        }

        return total;
    }
    
    public static long getAllCombinations(String des) {
        if(store.containsKey(des)) {
            return store.get(des);
        }
        long count = 0;
        for(var pat : patterns) {
            if(des.equals(pat)) {
                count++;
            } else if(des.startsWith(pat)) {
                count += getAllCombinations(des.substring(pat.length()));
            }
        }
        store.put(des, count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
