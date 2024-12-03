import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");

    public static Pattern mulMatcher = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");

    public static int partOne() {
        int total = 0;

        for(String line : inputArr) {
            Matcher m = mulMatcher.matcher(line);
            while(m.find()) {
                String extract = m.group();

                String[] nums = extract.substring(extract.indexOf("(")+1, extract.indexOf(")")).split(",");

                var mult = Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                total += mult;
            }
        }

        return total;
    }

    public static int partTwo() {
        int total = 0;

        var line = Input.INPUT.replaceAll("\\r\\n", "");

        var newLine = line.replaceAll("don't\\(\\).*?do\\(\\)", "");

        Matcher m = mulMatcher.matcher(newLine);
        while(m.find()) {
            String extract = m.group();

            String[] nums = extract.substring(extract.indexOf("(")+1, extract.indexOf(")")).split(",");

            var mult = Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
            total += mult;
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
