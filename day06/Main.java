import java.util.HashSet;
import java.util.Set;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");

    public static record Point(int x, int y) {}
    public static record PointWithDir(int x, int y, String dir) {}

    public static Set<Point> visited = new HashSet<>();
    public static Point start = null;

    public static char[][] buildCharMap() {
        char[][] inputAsChars = new char[inputArr.length][inputArr[0].length()];
        for(int i = 0; i < inputArr.length; i++) {
            inputAsChars[i] = inputArr[i].toCharArray(); 
            if(inputArr[i].contains("^")) {
                start = new Point(i, inputArr[i].indexOf("^"));
                visited.add(start);
            }
        }
        return inputAsChars;
    }

    public static int partOne() {
        char[][] inputAsChars = buildCharMap();
        var dir = "up";
        int x = start.x;
        int y = start.y;
        
        try {
            boolean changeDir = false;
            do {
                changeDir = false;
                switch (dir) {
                    case "up":
                        if(inputAsChars[x-1][y] == '#') { changeDir = true; } else if(inputAsChars[x-1][y] == '.') { visited.add(new Point(x-1, y)); }
                        break;
                    case "right":
                        if(inputAsChars[x][y+1] == '#') { changeDir = true; } else if(inputAsChars[x][y+1] == '.') { visited.add(new Point(x, y+1)); }
                        break;
                    case "down":
                        if(inputAsChars[x+1][y] == '#') { changeDir = true; } else if(inputAsChars[x+1][y] == '.') { visited.add(new Point(x+1, y)); }
                        break;
                    case "left":
                        if(inputAsChars[x][y-1] == '#') { changeDir = true; } else if(inputAsChars[x][y-1] == '.') { visited.add(new Point(x, y-1)); }
                        break;
                }
                
                if (changeDir) {
                    switch (dir) {
                    case "up":
                        dir = "right";
                        break;
                    case "right":
                        dir = "down";
                        break;
                    case "down":
                        dir = "left";
                        break;
                    case "left":
                        dir = "up";
                        break;
                    }
                } else {
                    switch (dir) {
                        case "up":
                            x--;
                            break;
                        case "right":
                            y++;
                            break;
                        case "down":
                            x++;
                            break;
                        case "left":
                            y--;
                            break;
                    }    
                }
            } while(x >= 0 || x <= inputArr.length || y >= 0 || y <= inputArr[0].length());
        } catch (Exception e) {
            // we're out of bounds
        }

        return visited.size();
    }

    public static int partTwo() {
        int loopCount = 0;
        for(Point p : visited) {
            char[][] inputAsChars = buildCharMap();
            inputAsChars[p.x][p.y] = '#';

            var dir = "up";
            int x = start.x;
            int y = start.y;
            
            Set<PointWithDir> loopPoints = new HashSet<>();
            
            try {
                boolean changeDir = false;
                do {
                    var curSpot = new PointWithDir(x, y, dir);
                    if(loopPoints.contains(curSpot)) {
                        loopCount++;
                        break;
                    } else {
                        loopPoints.add(curSpot);
                    }

                    changeDir = false;
                    switch (dir) {
                        case "up":
                            if(inputAsChars[x-1][y] == '#') { changeDir = true; }
                            break;
                        case "right":
                            if(inputAsChars[x][y+1] == '#') { changeDir = true; }
                            break;
                        case "down":
                            if(inputAsChars[x+1][y] == '#') { changeDir = true; }
                            break;
                        case "left":
                            if(inputAsChars[x][y-1] == '#') { changeDir = true; }
                            break;
                    }
                    
                    if (changeDir) {
                        switch (dir) {
                        case "up":
                            dir = "right";
                            break;
                        case "right":
                            dir = "down";
                            break;
                        case "down":
                            dir = "left";
                            break;
                        case "left":
                            dir = "up";
                            break;
                        }
                    } else {
                        switch (dir) {
                            case "up":
                                x--;
                                break;
                            case "right":
                                y++;
                                break;
                            case "down":
                                x++;
                                break;
                            case "left":
                                y--;
                                break;
                        }    
                    }

                } while(true);
            } catch (Exception e) {
                // we're out of bounds
            }
        }
        return loopCount;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
