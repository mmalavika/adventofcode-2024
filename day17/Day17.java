import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Day17 {

    public static String[] registerArr = Input17.REGISTERS.split("\\r\\n");
    public static String[] programArr = Input17.PROGRAM.split(",");

    public static String partOne() {
    long registerA = Long.parseLong(registerArr[0]);
    long registerB = Long.parseLong(registerArr[1]);
    long registerC = Long.parseLong(registerArr[2]);

        StringJoiner output = new StringJoiner(",");
        int i = 0;
        while(i < programArr.length-1) {
            var opcode = Integer.parseInt(programArr[i]);
            var operand = Integer.parseInt(programArr[i+1]);

            switch (opcode) {
                case 0:
                    long divA = registerA / (1 << (comboOperand(operand, registerA, registerB, registerC)));
                    registerA = divA;
                    break;
                case 1:
                    long xor = registerB ^ operand;
                    registerB = xor;
                    break;
                case 2:
                    long mod = comboOperand(operand, registerA, registerB, registerC) % 8;
                    registerB = mod;
                    break;
                case 3:
                    if(registerA != 0) {
                        i = operand;
                    } else {
                        i += 2;
                    }
                    break;
                case 4:
                    long xor_ = registerB ^ registerC;
                    registerB = xor_;
                    break;
                case 5:
                    var out = comboOperand(operand, registerA, registerB, registerC) % 8;
                    output.add(String.valueOf(out));
                    break;
                case 6:
                    long divB = registerA / (1 << (comboOperand(operand, registerA, registerB, registerC)));
                    registerB = divB;
                    break;
                case 7:
                    long divC = registerA / (1 << (comboOperand(operand, registerA, registerB, registerC)));
                    registerC = divC;
                    break;
                default:
                    break;              
            }
            if(opcode != 3) {
                i += 2;
            }
        }
        return output.toString();
    }

    public static Long comboOperand(int operand, long registerA, long registerB, long registerC) {
        return switch(operand) {
            case 0 -> 0L;
            case 1 -> 1L;
            case 2 -> 2L;
            case 3 -> 3L;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> null;
        };
    }

    public static long partTwo() { 
        List<String> program = new ArrayList<>();
        List<String> prgRemaining = new ArrayList<>(List.of(programArr));

        long aValue = 0;
        while(!prgRemaining.isEmpty()) {
            aValue--;
            program.addFirst(prgRemaining.removeLast());
            
            String prg = program.stream().collect(Collectors.joining(","));
            do {
                aValue++;
                var output = getOutputValue(aValue, 0, 0);
                if(output.equals(prg)) break;
            } while(true);
            
            if(!prgRemaining.isEmpty()) {
                aValue = aValue * 8;
            }
        }

        return aValue;
    }

    public static String getOutputValue(long registerA, long registerB, long registerC) {
        StringJoiner output = new StringJoiner(",");
        int i = 0;
        while(i < programArr.length-1) {
            var opcode = Integer.parseInt(programArr[i]);
            var operand = Integer.parseInt(programArr[i+1]);

            switch (opcode) {
                case 0:
                    long divA = registerA / (1 << (comboOperand(operand, registerA, registerB, registerC)));
                    registerA = divA;
                    break;
                case 1:
                    long xor = registerB ^ operand;
                    registerB = xor;
                    break;
                case 2:
                    long mod = comboOperand(operand, registerA, registerB, registerC) % 8;
                    registerB = mod;
                    break;
                case 3:
                    if(registerA != 0) {
                        i = operand;
                    } else {
                        i += 2;
                    }
                    break;
                case 4:
                    long xor_ = registerB ^ registerC;
                    registerB = xor_;
                    break;
                case 5:
                    var out = comboOperand(operand, registerA, registerB, registerC) % 8;
                    output.add(String.valueOf(out));
                    break;
                case 6:
                    long divB = registerA / (1 << (comboOperand(operand, registerA, registerB, registerC)));
                    registerB = divB;
                    break;
                case 7:
                    long divC = registerA / (1 << (comboOperand(operand, registerA, registerB, registerC)));
                    registerC = divC;
                    break;
                default:
                    break;              
            }
            if(opcode != 3) {
                i += 2;
            }
        }
        return output.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("Part one = " + partOne());
        System.out.println("Part two = " + partTwo());
    }
}
