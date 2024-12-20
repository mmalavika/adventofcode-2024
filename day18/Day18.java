import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Day18 {

    public static String[] inputArr = Input18.INPUT.split("\\r\\n");
    public static int size = 71;
    public static int bytes = 1024;
    public static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static char[][] grid = new char[size][size];

    public static record Point(int x, int y) {}
    public static record PointWithDir(int x, int y, int dir) {}

    public static void buildGrid() {
        for(int i = 0; i < bytes; i++) {
            var pt = inputArr[i].split(",");
            var x = Integer.parseInt(pt[0]);
            var y = Integer.parseInt(pt[1]);
            if(x < 0 || y < 0 || x >= size || y >= size) continue;
            grid[y][x] = '#';
        }
    }

    public static int partOne() {
        boolean[][][] visited = new boolean[size][size][4];
        Map<PointWithDir, PointWithDir> parentMap = new HashMap<>();

        Queue<PointWithDir> queue = new LinkedList<>();
        var start = new PointWithDir(0, 0, 0);
        queue.add(start);
        parentMap.put(start, null);
        visited[0][0][0] = true;

        PointWithDir pt = null;
        while(!queue.isEmpty()) {
            pt = queue.poll();

            if(pt.x == size-1 && pt.y == size-1) {
                break;
            }

            List<Point> neighbours = neighbourPoints(pt.x, pt.y);
            for(int i = 0; i < neighbours.size(); i++) {
                var n = neighbours.get(i);
                if(grid[n.y][n.x] != '#') {
                    if(!visited[n.y][n.x][i]) {
                        visited[n.y][n.x][i] = true;
                        queue.add(new PointWithDir(n.x, n.y, i));
                        parentMap.put(new PointWithDir(n.x, n.y, i), pt);
                    }
                }
            }
        }

        var curr = pt;
        List<PointWithDir> path = new ArrayList<>();
        while(curr != null) {
            path.add(0, curr);
            curr = parentMap.get(curr);
        }

        return path.size()-1;
    }

    public static List<Point> neighbourPoints(int x, int y) {
        List<Point> nextPoints = new ArrayList<>();
        for(var dir : directions) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            if(nextX < 0 || nextY < 0 || nextX >= size || nextY >= size) continue;

            nextPoints.add(new Point(nextX, nextY));
        }
        return nextPoints;
    }

    public static String partTwo() {
        for(int i = bytes; i < inputArr.length; i++) {
            var pt = inputArr[i].split(",");
            var x = Integer.parseInt(pt[0]);
            var y = Integer.parseInt(pt[1]);
            if(x < 0 || y < 0 || x >= size || y >= size) continue;
            grid[y][x] = '#';

            if(!doesPathExist()) return inputArr[i];
        }
        return "";
    }

    public static boolean doesPathExist() {
        boolean[][][] visited = new boolean[size][size][4];
        Map<PointWithDir, PointWithDir> parentMap = new HashMap<>();

        Queue<PointWithDir> queue = new LinkedList<>();
        var start = new PointWithDir(0, 0, 0);
        queue.add(start);
        parentMap.put(start, null);
        visited[0][0][0] = true;

        PointWithDir pt = null;

        boolean pathFound = false;
        while(!queue.isEmpty()) {
            pt = queue.poll();

            if(pt.x == size-1 && pt.y == size-1) {
                pathFound = true;
                break;
            }

            List<Point> neighbours = neighbourPoints(pt.x, pt.y);
            for(int i = 0; i < neighbours.size(); i++) {
                var n = neighbours.get(i);
                if(grid[n.y][n.x] != '#') {
                    if(!visited[n.y][n.x][i]) {
                        visited[n.y][n.x][i] = true;
                        queue.add(new PointWithDir(n.x, n.y, i));
                        parentMap.put(new PointWithDir(n.x, n.y, i), pt);
                    }
                }
            }
        }
        return pathFound;
    }
    
    public static void main(String[] args) {
        buildGrid();
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
