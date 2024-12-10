import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");
    public static char[][] topMap = new char[inputArr.length][inputArr[0].length()];
    public static record Point(int x, int y) {};
    public static List<Point> zeroPoints = new ArrayList<>();

    public static void findZeroPoints() {
        for(int i = 0; i < inputArr.length; i++) {
            topMap[i] = inputArr[i].toCharArray();
            for(int j = 0; j < topMap[i].length; j++) {
                if(topMap[i][j] == '0') {
                    zeroPoints.add(new Point(i, j));
                }                
            }
        }
    }

    public static int partOne() {
        int count = 0;
        for(Point zero : zeroPoints) {
            Set<Point> nextToCheck = new HashSet<>();
            nextToCheck.add(zero);

            while(!nextToCheck.isEmpty()) {
                List<Point> found = new ArrayList<>();
                for(Point n : nextToCheck) {
                    if(topMap[n.x][n.y] == '9') {
                        count++;
                    } else {
                        found.addAll(nextPoints(n));
                    }
                }
                nextToCheck.clear();
                nextToCheck.addAll(found);
            }
        }
        
        return count;
    }

    public static List<Point> nextPoints(Point pos) {
        List<Point> next = new ArrayList<>();
        var curr = Integer.parseInt(String.valueOf(topMap[pos.x][pos.y]));

         //up
        if(pos.x-1 >= 0 && Integer.parseInt(String.valueOf(topMap[pos.x-1][pos.y])) == curr + 1) next.add(new Point(pos.x-1, pos.y));
        
        //down
        if(pos.x+1 < topMap.length && Integer.parseInt(String.valueOf(topMap[pos.x+1][pos.y])) == curr + 1) next.add(new Point(pos.x+1, pos.y));

        //left
        if(pos.y-1 >= 0 && Integer.parseInt(String.valueOf(topMap[pos.x][pos.y-1])) == curr + 1) next.add(new Point(pos.x, pos.y-1));

        //right
        if(pos.y+1 < topMap.length && Integer.parseInt(String.valueOf(topMap[pos.x][pos.y+1])) == curr + 1) next.add(new Point(pos.x, pos.y+1));

        return next;
    }

    public static int partTwo() {
        int count = 0;
        for(Point zero : zeroPoints) {
            List<Point> nextToCheck = new ArrayList<>();
            nextToCheck.add(zero);

            while(!nextToCheck.isEmpty()) {
                List<Point> found = new ArrayList<>();
                for(Point n : nextToCheck) {
                    if(topMap[n.x][n.y] == '9') {
                        count++;
                    } else {
                        found.addAll(nextPoints(n));
                    }
                }
                nextToCheck.clear();
                nextToCheck.addAll(found);
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        findZeroPoints();
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
