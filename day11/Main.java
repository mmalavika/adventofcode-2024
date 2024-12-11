import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    /* 
    If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
    If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
    If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.
    */

    public static String[] inputArr = Input.INPUT.split(" ");
    public static List<Long> input = Arrays.stream(inputArr).mapToLong(n -> Long.parseLong(n)).boxed().toList();
    public static List<Long> stonesAfterPart1 = new ArrayList<>();

    public static int partOne() {
        List<Long> stones = new ArrayList<>(input);

        for(int blink = 1; blink <= 25; blink++) {
            List<Long> stonesCopy = new ArrayList<>();
            for(int i = 0; i < stones.size(); i++) {
                var stone = stones.get(i);
                if(stone == 0) {
                    stonesCopy.add(i, 1L);
                    continue;
                } else if(String.valueOf(stone).length() % 2 == 0) {
                    var chars = String.valueOf(stone);
                    long left = chars.substring(0, chars.length()/2) == "" ? 0 : Long.parseLong(chars.substring(0, chars.length()/2));
                    long right = chars.substring(chars.length()/2) == "" ? 0 : Long.parseLong(chars.substring(chars.length()/2));
                    stonesCopy.add(i, left);
                    stonesCopy.add(i+1, right);
                    continue;                  
                } else {
                    long newNum = stone * 2024;
                    stonesCopy.add(i, newNum);
                    continue;
                }
            }
            stones = stonesCopy;
        }

        stonesAfterPart1.addAll(stones);
        return stones.size();
    }

    public static long partTwo() {
        Map<Long, Long> count = new HashMap<>();

        for(Long st : stonesAfterPart1) {
            count.put(st, count.getOrDefault(st, 0L)+1);
        }

        for(int blink = 1; blink <= 50; blink++) {
            Map<Long, Long> countCopy = new HashMap<>();
            for(var e : count.entrySet()) {
                var stone = e.getKey();
                if(stone == 0) {
                    countCopy.put(1L, countCopy.getOrDefault(1L, 0L) + e.getValue());
                    continue;
                } else if(String.valueOf(stone).length() % 2 == 0) {
                    var chars = String.valueOf(stone);
                    long left = chars.substring(0, chars.length()/2) == "" ? 0 : Long.parseLong(chars.substring(0, chars.length()/2));
                    long right = chars.substring(chars.length()/2) == "" ? 0 : Long.parseLong(chars.substring(chars.length()/2));
                    countCopy.put(left, countCopy.getOrDefault(left, 0L) + e.getValue());
                    countCopy.put(right, countCopy.getOrDefault(right, 0L) + e.getValue());
                    continue;                  
                } else {
                    long newNum = stone * 2024;
                    countCopy.put(newNum, countCopy.getOrDefault(newNum, 0L) + e.getValue());
                    continue;
                }
            }
            count = countCopy;
        }

        long sum = 0;
        for (long c : count.values()) {
            sum += c;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
