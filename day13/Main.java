public class Main {

    public static String[] inputArr = Input.INPUT.split("\\r\\n\\n");

    public static int partOne() {
        record Pair(int x, int y) {}
        int total = 0;
        for(int i = 0; i < inputArr.length; i++) {
            var block = inputArr[i].split("\\r\\n");
            var strA = block[0];
            var strB = block[1];
            var prize = block[2];
            
            var btnA = new Pair(Integer.parseInt(strA.substring(strA.indexOf("+")+1, strA.indexOf(","))), 
                    Integer.parseInt(strA.substring(strA.lastIndexOf("+"))));
            var btnB = new Pair(Integer.parseInt(strB.substring(strB.indexOf("+")+1, strB.indexOf(","))), 
                    Integer.parseInt(strB.substring(strB.lastIndexOf("+"))));
            var prizeXY = new Pair(Integer.parseInt(prize.substring(prize.indexOf("=")+1, prize.indexOf(","))),
            Integer.parseInt(prize.substring(prize.lastIndexOf("=")+1)));

            int btnAPress = 0;
            int btnBPress = 0;

            //x1 * a + x2 * b = px
            //y1 * a + y2 * b = py
            int[][] lhs_ = new int[2][2];
            lhs_[0][0] = btnB.y;
            lhs_[1][0] = btnA.y * -1;
            lhs_[0][1] = btnB.x * -1;
            lhs_[1][1] = btnA.x;

            int[] rhs = new int[2];
            rhs[0] = prizeXY.x;
            rhs[1] = prizeXY.y;

            //multiply lhs and rhs
            int[] mult = new int[2];
            mult[0] = lhs_[0][0] * rhs[0] + lhs_[0][1] * rhs[1];
            mult[1] = lhs_[1][0] * rhs[0] + lhs_[1][1] * rhs[1];

            //divide
            int div = btnA.x * btnB.y - btnA.y * btnB.x;
            if(div != 0) {
                btnAPress = mult[0] / div;
                btnBPress = mult[1] / div;
            }

            int xCount = btnA.x * btnAPress + btnB.x * btnBPress;
            int yCount = btnA.y * btnAPress + btnB.y * btnBPress;

            if(xCount == prizeXY.x && yCount == prizeXY.y) {
                total += (btnAPress*3) + btnBPress;
            }
        }
        return total;
    }

    public static long partTwo() {
        record Pair(long x, long y) {}
        long total = 0;
        for(int i = 0; i < inputArr.length; i++) {
            var block = inputArr[i].split("\\r\\n");
            var strA = block[0];
            var strB = block[1];
            var prize = block[2];
            
            var btnA = new Pair(Long.parseLong(strA.substring(strA.indexOf("+")+1, strA.indexOf(","))), 
                    Long.parseLong(strA.substring(strA.lastIndexOf("+"))));
            var btnB = new Pair(Long.parseLong(strB.substring(strB.indexOf("+")+1, strB.indexOf(","))), 
                    Long.parseLong(strB.substring(strB.lastIndexOf("+"))));
            var prizeXY = new Pair(Long.parseLong(prize.substring(prize.indexOf("=")+1, prize.indexOf(","))) + 10000000000000L,
            Long.parseLong(prize.substring(prize.lastIndexOf("=")+1)) + 10000000000000L);

            long btnAPress = 0;
            long btnBPress = 0;

            //x1 * a + x2 * b = px
            //y1 * a + y2 * b = py
            long[][] lhs_ = new long[2][2];
            lhs_[0][0] = btnB.y;
            lhs_[1][0] = btnA.y * -1;
            lhs_[0][1] = btnB.x * -1;
            lhs_[1][1] = btnA.x;

            long[] rhs = new long[2];
            rhs[0] = prizeXY.x;
            rhs[1] = prizeXY.y;

            //multiply lhs and rhs
            long[] mult = new long[2];
            mult[0] = lhs_[0][0] * rhs[0] + lhs_[0][1] * rhs[1];
            mult[1] = lhs_[1][0] * rhs[0] + lhs_[1][1] * rhs[1];

            //divide
            long div = btnA.x * btnB.y - btnA.y * btnB.x;
            if(div != 0) {
                btnAPress = mult[0] / div;
                btnBPress = mult[1] / div;
            }

            long xCount = btnA.x * btnAPress + btnB.x * btnBPress;
            long yCount = btnA.y * btnAPress + btnB.y * btnBPress;

            if(xCount == prizeXY.x && yCount == prizeXY.y) {
                total += (btnAPress*3) + btnBPress;
            }
        }
        return total;
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
