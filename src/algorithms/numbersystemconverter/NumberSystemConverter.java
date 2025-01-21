package algorithms.numbersystemconverter;

import stack.dynamicstack.Stack;
import hashtable.hashtablewithlinkedlist.HashTable;

public class NumberSystemConverter {
    
    private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final HashTable<Character, Integer> digitsMapping;
    
    static {
        digitsMapping = new HashTable<>();
        for (Integer i = 0; i < 36; i++)
            digitsMapping.put(DIGITS.charAt(i), i);
    }
    
    public static String convert(String number, int fromBase, int toBase) {
        if (!validateConversion(number, fromBase, toBase))
            throw new IllegalArgumentException("This method only supports a non-negative integer conversion from and to bases in range 2 to 36");
    
        Integer decNumber = convertToDec(number, fromBase);
        
        Stack<Integer> stack = new Stack<>();
        
        while (decNumber > 0) {
            Integer rem = decNumber % toBase;
            stack.push(rem);
            decNumber /= toBase;
        }
        
        String numberInNewBase = "";
        while (!stack.isEmpty())
            numberInNewBase += DIGITS.charAt(stack.pop());
        
        return numberInNewBase;
    }
    
    public static Integer convertToDec(String number, int fromBase) {
        Integer decNumber = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            Double multiplier = Math.pow(fromBase, (number.length() - 1) - i);
            Integer digit = digitsMapping.get(number.charAt(i));
            decNumber = decNumber + digit * multiplier.intValue();         
        }
        return decNumber;
    }

    private static boolean validateConversion(String number, int fromBase, int toBase) {
        return number.charAt(0) != '-' && (fromBase >= 2 && fromBase <= 36)  && (fromBase >= 2 && fromBase <= 36);
    }
}
