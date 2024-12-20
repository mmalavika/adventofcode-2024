import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");
    
    public static record Point(int x, int y) { }

    public static long partOne() {
        int gridX = 101;
        int gridY = 103;
        List<Point> guards = new ArrayList<>();

        for(int i = 0; i < inputArr.length; i++) {
            var line = inputArr[i].split(" ");
            var pStr = line[0];
            var vStr = line[1];
            var point = new Point(Integer.parseInt(pStr.substring(pStr.indexOf("=")+1, pStr.indexOf(","))),
            Integer.parseInt(pStr.substring(pStr.indexOf(",")+1)));
            var velX = Integer.parseInt(vStr.substring(vStr.indexOf("=")+1, vStr.indexOf(",")));
            var velY = Integer.parseInt(vStr.substring(vStr.indexOf(",")+1));

            int nextX = point.x;
            int nextY = point.y;
            for(int c = 1; c <= 100; c++) {
                nextX = (nextX + velX + gridX) % gridX;
                nextY = (nextY + velY + gridY) % gridY;
            }   
            guards.add(new Point(nextX, nextY));     
        }

        long topLeft = 0;
        long topRight = 0;
        long bottomLeft = 0;
        long bottomRight = 0;
        for(var guard : guards) {
            int gridXMid = gridX / 2;
            int gridYMid = gridY / 2;
            if(guard.x < gridXMid && guard.y < gridYMid) {
                topLeft++;
            } else if(guard.x > gridXMid && guard.y < gridYMid) {
                topRight++;
            } else if(guard.x < gridXMid && guard.y > gridYMid) {
                bottomLeft++;
            } else if(guard.x > gridXMid && guard.y > gridYMid) {
                bottomRight++;
            }
        }

        return topLeft * topRight * bottomLeft * bottomRight;
    }

    public static int partTwo() {
        int gridX = 101;
        int gridY = 103;
        List<Point> guards = new ArrayList<>();
        List<Point> vels = new ArrayList<>();

        for(int i = 0; i < inputArr.length; i++) {
            var line = inputArr[i].split(" ");
            var pStr = line[0];
            var vStr = line[1];
            var point = new Point(Integer.parseInt(pStr.substring(pStr.indexOf("=")+1, pStr.indexOf(","))),
            Integer.parseInt(pStr.substring(pStr.indexOf(",")+1)));
            var velX = Integer.parseInt(vStr.substring(vStr.indexOf("=")+1, vStr.indexOf(",")));
            var velY = Integer.parseInt(vStr.substring(vStr.indexOf(",")+1));
            guards.add(point);
            vels.add(new Point(velX, velY));        
        }

        int count = 0;
        while(true) {
            count++;
            for(int i = 0; i < guards.size(); i++) {
                var g = guards.get(i);
                var vel = vels.get(i);
                int nextX = (g.x + vel.x + gridX) % gridX;
                int nextY = (g.y + vel.y + gridY) % gridY;
                guards.set(i, new Point(nextX, nextY));
            }

            Set<Point> currentForm = new HashSet<>();
            currentForm.addAll(guards);
            
            if(currentForm.size() == guards.size()) {
                break;
            }
        }  
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
