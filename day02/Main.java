import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");

    public static int partOne() {
        int count = 0;
        for(String str : inputArr) {
            String[] nums = str.split(" ");

            boolean isValid = false;
            var dir = Integer.parseInt(nums[0]) < Integer.parseInt(nums[1]) ? "I" : "D";
            for(int i = 0; i < nums.length-1; i++) {
                int numToCheck = Integer.parseInt(nums[i]);
                int nextNum = Integer.parseInt(nums[i+1]);

                if(numToCheck < nextNum && dir == "D") {
                    isValid = false;
                    break;
                } else if(numToCheck > nextNum && dir == "I") {
                    isValid = false;
                    break;
                } else {
                    var diff = Math.abs(numToCheck - nextNum);
                    if(diff >= 1 && diff <= 3) {
                        isValid = true;
                    } else {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) count++;
        }
        return count;
    }

    public static int partTwo() {
        int count = 0;
        for(String str : inputArr) {
            List<String> nums = new ArrayList<>(Arrays.asList(str.split(" ")));

            if(isValidLine(nums)) {
                count++;
            } else {
                for(int i = 0; i < nums.size(); i++) {
                    var copy = new ArrayList<>(nums);
                    copy.remove(i);
                    if (isValidLine(copy)) { 
                        count++;
                        break; 
                    } 
                }
            }
        }        
        return count;
    }
    
    public static boolean isValidLine(List<String> nums) {
        boolean isValid = false;
        var dir = Integer.parseInt(nums.get(0)) < Integer.parseInt(nums.get(1)) ? "I" : "D";
        for(int i = 0; i < nums.size()-1; i++) {
            int numToCheck = Integer.parseInt(nums.get(i));
            int nextNum = Integer.parseInt(nums.get(i+1));

            if(numToCheck < nextNum && dir == "D") {
                isValid = false;
                break;
            } else if(numToCheck > nextNum && dir == "I") {
                isValid = false;
                break;
            } else {
                var diff = Math.abs(numToCheck - nextNum);
                if(diff >= 1 && diff <= 3) {
                    isValid = true;
                } else {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
