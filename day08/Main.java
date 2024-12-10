import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");
    public static char[][] inputAsChars = new char[inputArr.length][inputArr[0].length()];

    public static record Point(int x, int y) {}

    public static void buildCharMap() {
        for(int i = 0; i < inputArr.length; i++) {
            inputAsChars[i] = inputArr[i].toCharArray(); 
        }
    }

    public static int partOne() {
        int count = 0;

        char[][] inputCharCopy = new char[inputArr.length][inputArr[0].length()];
        for(int i = 0; i < inputAsChars.length; i++) {
            for(int j = 0; j < inputAsChars[0].length; j++) {
                var curr = inputAsChars[i][j];
                if(curr != '.') {
                    List<Point> pts = findAllMatching(curr, i, j);
                    for(Point pt : pts) {
                        try {
                            int diffX = Math.abs(pt.x - i);
                            int diffY = Math.abs(pt.y - j);
                            var locX = 0;
                            var locY = 0;
                            if(pt.x > i) {
                                //go down
                                locX = i + 2*diffX;
                            } else {
                                //go up
                                locX = i - 2*diffX;
                            }
                            if(pt.y > j) {
                                //go right
                                locY = j + 2*diffY;
                            } else {
                                //go left
                                locY = j - 2*diffY;
                            }
                            if(inputCharCopy[locX][locY] != '#') {
                                inputCharCopy[locX][locY] = '#';
                                count++;
                            }
                        } catch(Exception e) {
                        }                        
                    }
                }
            }
        }
        return count;
    }

    public static List<Point> findAllMatching(char charToMatch, int x, int y) {
        List<Point> pts = new ArrayList<>();
        for(int i = 0; i < inputAsChars.length; i++) {
            for(int j = 0; j < inputAsChars[0].length; j++) {
                var curr = inputAsChars[i][j];
                if(curr == charToMatch && i !=x && j != y) {
                    pts.add(new Point(i, j));
                }
            }
        }
        return pts;
    }

    public static int partTwo() {
        int count = 0;

        char[][] inputCharCopy = new char[inputArr.length][inputArr[0].length()];
        for(int i = 0; i < inputAsChars.length; i++) {
            for(int j = 0; j < inputAsChars[0].length; j++) {
                var curr = inputAsChars[i][j];
                if(curr != '.') {
                    List<Point> pts = findAllMatching(curr, i, j);
                    for(Point pt : pts) {
                        try {
                            int diffX = Math.abs(pt.x - i);
                            int diffY = Math.abs(pt.y - j);
                            var locX = 0;
                            var locY = 0;
                            if(pt.x > i) {
                                //go down
                                locX = i + 2*diffX;
                            } else {
                                //go up
                                locX = i - 2*diffX;
                            }
                            if(pt.y > j) {
                                //go right
                                locY = j + 2*diffY;
                            } else {
                                //go left
                                locY = j - 2*diffY;
                            }
                            if(inputCharCopy[locX][locY] != '#') {
                                inputCharCopy[locX][locY] = '#';
                                count++;
                            }
                        } catch(Exception e) {
                        }

                        var locX = pt.x;
                        var locY = pt.y;
                        int diffX = Math.abs(pt.x - i);
                        int diffY = Math.abs(pt.y - j);
                        try {
                            while(true) {
                                if(inputCharCopy[locX][locY] != '#') {
                                    inputCharCopy[locX][locY] = '#';
                                    count++;
                                }
                                if(pt.x > i) {
                                    locX += diffX;
                                } else {
                                    locX -= diffX;
                                }
                                if(pt.y > j) {
                                    locY += diffY;
                                } else {
                                    locY -= diffY;
                                }
                            }
                        } catch(Exception e) {
                        }                
                    }
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        buildCharMap();
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
