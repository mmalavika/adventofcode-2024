import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");

    public static long partOne() {
        Long total = 0L;
        for(String eq : inputArr) {
            Long lhs = Long.parseLong(eq.substring(0, eq.indexOf(":")));
            List<Long> rhs = Arrays.stream(eq.substring(eq.indexOf(":")+2).split(" ")).mapToLong(n -> Long.parseLong(n)).boxed().toList();
            
            List<List<Long>> computations = new ArrayList<>();
            computations.add(List.of(rhs.get(0)));

            for(int i = 1; i < rhs.size(); i++) {
                List<Long> prev = computations.get(i-1);
                List<Long> current = new ArrayList<>();
                for(long num : prev) {
                    Long sum = num + rhs.get(i);
                    Long mult = num * rhs.get(i);
                    current.add(sum);
                    current.add(mult);
                }
                computations.add(current);
            }

            List<Long> last = computations.get(computations.size()-1);
            if(last.contains(lhs)) total += lhs;
        }
        return total;
    }

    public static Long partTwo() {
        Long total = 0L;
        for(String eq : inputArr) {
            Long lhs = Long.parseLong(eq.substring(0, eq.indexOf(":")));
            List<Long> rhs = Arrays.stream(eq.substring(eq.indexOf(":")+2).split(" ")).mapToLong(n -> Long.parseLong(n)).boxed().toList();
            
            List<List<Long>> computations = new ArrayList<>();
            computations.add(List.of(rhs.get(0)));

            for(int i = 1; i < rhs.size(); i++) {
                List<Long> prev = computations.get(i-1);
                List<Long> current = new ArrayList<>();
                for(long num : prev) {
                    Long sum = num + rhs.get(i);
                    Long mult = num * rhs.get(i);
                    Long concat = Long.parseLong(Long.toString(num) + Long.toString(rhs.get(i)));
                    current.add(sum);
                    current.add(mult);
                    current.add(concat);
                }
                computations.add(current);
            }

            List<Long> last = computations.get(computations.size()-1);
            if(last.contains(lhs)) total += lhs;
        }
        return total;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
