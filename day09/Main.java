import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static char[] inputArr = Input.INPUT.toCharArray();

    public static long partOne() {
        List<String> numsDots = new ArrayList<>();
        int idx = 0;
        for(int i = 0; i < inputArr.length; i++) {
            if(i % 2 == 0) {
                var nums = Collections.nCopies(Integer.parseInt(String.valueOf(inputArr[i])), String.valueOf(idx));
                numsDots.addAll(nums);
                idx++;
            } else {
                var dots = Collections.nCopies(Integer.parseInt(String.valueOf(inputArr[i])), ".");
                numsDots.addAll(dots);
            }
        }

        for(int i = numsDots.size()-1; i >= 0; i--) {
            if(numsDots.get(i) == "." || numsDots.indexOf(".") > i) continue;
            Collections.swap(numsDots, i, numsDots.indexOf("."));
        }

        List<String> noDots = numsDots.subList(0, numsDots.indexOf("."));
        long checksum = 0;
        for(int i = 0; i < noDots.size(); i++) {
            var mult = Integer.parseInt(noDots.get(i)) * i;
            checksum += mult;
        }
        return checksum;
    }

    public static long partTwo() {
        record Tuple(int idx, int length) {};
        List<Tuple> numsDots = new ArrayList<>();
        
        int idx = 0;
        for(int i = 0; i < inputArr.length; i++) {
            if(i % 2 == 0) {
                numsDots.add(new Tuple(idx, Integer.parseInt(String.valueOf(inputArr[i]))));
                idx++;
            } else {
                numsDots.add(new Tuple(-1, Integer.parseInt(String.valueOf(inputArr[i]))));
            }
        }

        for(int i = idx-1; i >= 0; i--) {

            Tuple toSwap = null;
            var posOfSwap = 0;
            for(int p = 0; p < numsDots.size(); p++) {
                if(numsDots.get(p).idx == i) {
                    toSwap = numsDots.get(p);
                    posOfSwap = p;
                    break;
                }
            }

            var lenToMatch = toSwap.length;
            for(int j = 0; j < numsDots.size(); j++) {  
                var curr = numsDots.get(j);
                if(curr.idx == -1) {
                    if(curr.length == lenToMatch) {
                        curr = new Tuple(toSwap.idx, lenToMatch);
                        numsDots.set(j, curr);
                        toSwap = new Tuple(-1, lenToMatch);
                        numsDots.set(posOfSwap, toSwap);
                        break;
                    } else if(curr.length >= lenToMatch) {
                        int diff = curr.length-lenToMatch;
                        curr = new Tuple(toSwap.idx, lenToMatch);
                        toSwap = new Tuple(-1, lenToMatch);
                        numsDots.set(posOfSwap, toSwap);
                        numsDots.set(j, curr);
                        numsDots.add(j+1, new Tuple(-1, diff));
                        break;
                    }
                } else {
                    if(curr.idx == toSwap.idx) break;
                }
            }
        }

        long checksum = 0;
        int count = 0;
        for(int i = 0; i < numsDots.size(); i++) {
            var curr = numsDots.get(i);
            if(curr.idx == -1) {
                count += curr.length;
            } else {
                for(int j = 0; j < curr.length; j++) {
                    checksum += curr.idx * count;
                    count++;
                }
            }
        }
        return checksum;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
