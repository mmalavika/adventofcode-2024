import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n");

    public static int partOne() {
        char[][] inputAsChars = new char[inputArr.length+8][inputArr[0].length()+8];
        int x = 4;
        int y = 3;
        List<Pair> allXPos = new ArrayList<>();
        for(String in : inputArr) {
            x = 4;
            y++;
            for(char c : in.toCharArray()) {
                if(c == 'X') {
                    allXPos.add(new Pair(x, y));
                }
                inputAsChars[x++][y] = c;
            }
        }

        int count = 0;
        for(Pair pt : allXPos) {
            //up
            if(inputAsChars[pt.x][pt.y-1] == 'M' && inputAsChars[pt.x][pt.y-2] == 'A' && inputAsChars[pt.x][pt.y-3] == 'S') count++;
            //down
            if(inputAsChars[pt.x][pt.y+1] == 'M' && inputAsChars[pt.x][pt.y+2] == 'A' && inputAsChars[pt.x][pt.y+3] == 'S') count++;
            //left
            if(inputAsChars[pt.x-1][pt.y] == 'M' && inputAsChars[pt.x-2][pt.y] == 'A' && inputAsChars[pt.x-3][pt.y] == 'S') count++;
            //right
            if(inputAsChars[pt.x+1][pt.y] == 'M' && inputAsChars[pt.x+2][pt.y] == 'A' && inputAsChars[pt.x+3][pt.y] == 'S') count++;
            //top left
            if(inputAsChars[pt.x-1][pt.y-1] == 'M' && inputAsChars[pt.x-2][pt.y-2] == 'A' && inputAsChars[pt.x-3][pt.y-3] == 'S') count++;
            //top right
            if(inputAsChars[pt.x-1][pt.y+1] == 'M' && inputAsChars[pt.x-2][pt.y+2] == 'A' && inputAsChars[pt.x-3][pt.y+3] == 'S') count++;
            //bottom left
            if(inputAsChars[pt.x+1][pt.y-1] == 'M' && inputAsChars[pt.x+2][pt.y-2] == 'A' && inputAsChars[pt.x+3][pt.y-3] == 'S') count++;
            //bottom right
            if(inputAsChars[pt.x+1][pt.y+1] == 'M' && inputAsChars[pt.x+2][pt.y+2] == 'A' && inputAsChars[pt.x+3][pt.y+3] == 'S') count++;
        }
        return count;
    }

    public static int partTwo() {
        char[][] inputAsChars = new char[inputArr.length+8][inputArr[0].length()+8];
        int x = 4;
        int y = 3;
        List<Pair> allAPos = new ArrayList<>();
        for(String in : inputArr) {
            x = 4;
            y++;
            for(char c : in.toCharArray()) {
                if(c == 'A') {
                    allAPos.add(new Pair(x, y));
                }
                inputAsChars[x++][y] = c;
            }
        }

        int count = 0;
        for(Pair pt : allAPos) {
            //top left M, bottom right S
            if(inputAsChars[pt.x-1][pt.y-1] == 'M' && inputAsChars[pt.x+1][pt.y+1] == 'S') {
                //top right M, bottom left S
                if(inputAsChars[pt.x-1][pt.y+1] == 'M' && inputAsChars[pt.x+1][pt.y-1] == 'S') count++;
                //top right S, bottom left M
                if(inputAsChars[pt.x-1][pt.y+1] == 'S' && inputAsChars[pt.x+1][pt.y-1] == 'M') count++;
            }
            //top left S, bottom right M
            if(inputAsChars[pt.x-1][pt.y-1] == 'S' && inputAsChars[pt.x+1][pt.y+1] == 'M') {
                //top right M, bottom left S
                if(inputAsChars[pt.x-1][pt.y+1] == 'M' && inputAsChars[pt.x+1][pt.y-1] == 'S') count++;
                //top right S, bottom left M
                if(inputAsChars[pt.x-1][pt.y+1] == 'S' && inputAsChars[pt.x+1][pt.y-1] == 'M') count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
