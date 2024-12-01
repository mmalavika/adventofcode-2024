import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");

    public static int partOne() {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (String str : inputArr) {
            var pairs = str.split("   ");
            leftList.add(Integer.parseInt(pairs[0]));
            rightList.add(Integer.parseInt(pairs[1]));
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        int total = 0;

        for(int i = 0; i < leftList.size(); i++) {
            var leftNum = leftList.get(i);
            var rightNum = rightList.get(i);

            int diff = Math.abs(leftNum - rightNum);
            total = total + diff;   
        }

        return total;
    }

    public static int partTwo() {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (String str : inputArr) {
            var pairs = str.split("   ");
            leftList.add(Integer.parseInt(pairs[0]));
            rightList.add(Integer.parseInt(pairs[1]));
        }

        int total = 0;

        for(Integer left : leftList) {
            var numOccurs = rightList.stream().filter(right -> Objects.equals(left, right)).toArray().length;
            var simScore = left * numOccurs;
            total = total + simScore;
        }

        return total;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
