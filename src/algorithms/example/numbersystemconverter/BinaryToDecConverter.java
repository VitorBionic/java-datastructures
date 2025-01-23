package algorithms.example.numbersystemconverter;

import stack.dynamicstack.Stack;

public class BinaryToDecConverter {
    
    public static String convertToBin(Integer decNumber) {
        if (decNumber < 0)
            throw new IllegalArgumentException("This method does not support negative number, try the others methods of the class");
        
        Stack<Integer> stack = new Stack<>();
        
        if (decNumber == 0)
            stack.push(0);
        while (decNumber != 0) {
            stack.push(decNumber % 2);
            decNumber /= 2;
        }
        
        String binaryNumber = "";
        while (!stack.isEmpty())
            binaryNumber +=  stack.pop().toString();
        
        return binaryNumber;
    }
    
    // Leftmost bit assigned by 1 means negative number and 0 means positive
    public static String convertToBinWithSign(Integer decNumber) {
        Stack<Integer> stack = new Stack<>();
        
        String binaryNumber = "";
        if (decNumber > 0)
            binaryNumber += "0";
        else if (decNumber < 0) {
            binaryNumber += "1";
            decNumber *= -1;
        }
        
        if (decNumber == 0)
            stack.push(0);
        while (decNumber != 0) {
            stack.push(decNumber % 2);
            decNumber /= 2;
        }
        
        
        while (!stack.isEmpty())
            binaryNumber +=  stack.pop().toString();
        
        return binaryNumber;
    }
    
    public static String convertToBinOneComplement(Integer decNumber) {
        Stack<Integer> stack = new Stack<>();
        
        boolean negative = false;
        String binaryNumber = "";
        if (decNumber != 0)
            binaryNumber = "0";
        if (decNumber < 0) {
            binaryNumber = "1";
            negative = true;
            decNumber *= -1;
        }
        
        if (decNumber == 0)
            stack.push(0);
        while (decNumber != 0) {
            stack.push(decNumber % 2);
            decNumber /= 2;
        }
        
        while (!stack.isEmpty()) {
            if (!negative)
                binaryNumber += stack.pop().toString();
            else {
                Integer bit = stack.pop() == 0 ? 1 : 0;
                binaryNumber += bit;
            }
        }
        
        return binaryNumber;
    }
    
    public static String convertToBinTwoComplement(Integer decNumber) {
        String binaryNumOC = convertToBinOneComplement(decNumber);
        if (binaryNumOC.charAt(0) == '0')
            return binaryNumOC;
        
        Stack<Character> stack = new Stack<>();
        
        int carry = 1;
        for (int i = binaryNumOC.length() - 1; i >= 0; i--) {
            Character currentBit = binaryNumOC.charAt(i);
            if (carry > 0) {
                if (binaryNumOC.charAt(i) == '0') {
                    currentBit = '1';
                    carry = 0;
                } else
                    currentBit = '0';
            }
            stack.push(currentBit);
        }
        
        String binaryNumTC = "";
        while (!stack.isEmpty())
            binaryNumTC += stack.pop().toString();
        
        return binaryNumTC;
    }
}
