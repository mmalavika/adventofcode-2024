import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");
    public static char[][] inputAsChars = new char[inputArr.length][inputArr[0].length()];
    public static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static record Point(int x, int y) {}
    public static record Tuple(int area, int perimeter) {}

    public static void buildCharMap() {
        for(int i = 0; i < inputArr.length; i++) {
            inputAsChars[i] = inputArr[i].toCharArray(); 
        }
    }

    public static int partOne() {
        boolean[][] visited = new boolean[inputArr.length][inputArr[0].length()];
        Map<Character, List<Tuple>> alphabetMap = new HashMap<>();
        for(char alph : alphabet) {
            alphabetMap.put(alph, new ArrayList<>());
        }

        for(int i = 0; i < inputAsChars.length; i++) {
            for(int j = 0; j < inputAsChars[0].length; j++) {
                if(!visited[i][j]) {
                    var curr = inputAsChars[i][j];
                    int area = 0;
                    int perimeter = 0;

                    Queue<Point> nextPoints = new LinkedList<>();
                    nextPoints.add(new Point(i, j));
                    visited[i][j] = true;

                    while(!nextPoints.isEmpty()) {
                        var pt = nextPoints.poll();
                        area++;

                        for(var dir : directions) {
                            int nextX = pt.x + dir[0];
                            int nextY = pt.y + dir[1];
                            if(nextX >= 0 && nextX < inputAsChars.length && nextY >= 0 && nextY < inputAsChars[0].length && inputAsChars[nextX][nextY] == curr) {
                                if(!visited[nextX][nextY]) {
                                    nextPoints.add(new Point(nextX, nextY));
                                    visited[nextX][nextY] = true;
                                }
                            } else {
                                perimeter++;
                            }
                        }
                    }
                    
                    var mapList = alphabetMap.get(curr);
                    mapList.add(new Tuple(area, perimeter));
                    alphabetMap.put(curr, mapList);
                }
            }
        }

        int total = 0;
        for(var e : alphabetMap.entrySet()) {
            var mapList = e.getValue();
            for(var t : mapList) {
                total += t.area * t.perimeter;
            }
        }
        return total;
    }

    public static int partTwo() {
        record PointWithDir(int x, int y, int dir) {}
        boolean[][] visited = new boolean[inputArr.length][inputArr[0].length()];
        Map<Character, List<Tuple>> alphabetMap = new HashMap<>();
        for(char alph : alphabet) {
            alphabetMap.put(alph, new ArrayList<>());
        }

        for(int i = 0; i < inputAsChars.length; i++) {
            for(int j = 0; j < inputAsChars[0].length; j++) {
                if(!visited[i][j]) {
                    var curr = inputAsChars[i][j];
                    int area = 0;
                    int perimeter = 0;

                    Set<PointWithDir> edges = new HashSet<>();

                    Queue<Point> nextPoints = new LinkedList<>();
                    nextPoints.add(new Point(i, j));
                    visited[i][j] = true;

                    while(!nextPoints.isEmpty()) {
                        var pt = nextPoints.poll();
                        area++;

                        var neighbours = neighbourPoints(pt.x, pt.y);
                        for(int n = 0; n < neighbours.size(); n++) {
                            var nPoint = neighbours.get(n);
                            if(nPoint.x >= 0 && nPoint.x < inputAsChars.length && nPoint.y >= 0 && nPoint.y < inputAsChars[0].length 
                                && inputAsChars[nPoint.x][nPoint.y] == curr) {
                                    if(!visited[nPoint.x][nPoint.y]) {
                                        nextPoints.add(nPoint);
                                        visited[nPoint.x][nPoint.y] = true;
                                    }
                            } else {
                                perimeter++;

                                edges.add(new PointWithDir(nPoint.x, nPoint.y, n));

                                for(var next : neighbourPoints(nPoint.x, nPoint.y)) {
                                    if(edges.contains(new PointWithDir(next.x, next.y, n))) {
                                        perimeter--;
                                    }
                                }
                            }
                        }
                    }
                    
                    var mapList = alphabetMap.get(curr);
                    mapList.add(new Tuple(area, perimeter));
                    alphabetMap.put(curr, mapList);
                }
            }
        }

        int total = 0;
        for(var e : alphabetMap.entrySet()) {
            var mapList = e.getValue();
            for(var t : mapList) {
                total += t.area * t.perimeter;
            }
        }
        return total;
    }

    public static List<Point> neighbourPoints(int x, int y) {
        List<Point> nextPoints = new ArrayList<>();
        for(var dir : directions) {
            int nextX = x + dir[0];
            int nextY = y + dir[1]; 

            nextPoints.add(new Point(nextX, nextY));
        }
        return nextPoints;
    }
    
    public static void main(String[] args) {
        buildCharMap();
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
