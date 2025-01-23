package algorithms.example.palindromecheck;

import queue.deque.dynamicdeque.Deque;

public class Program {
    public static void main(String[] args) {
        String[] strs = new String[8];
        strs[0] = "rotator";
        strs[1] = "Won’t lovers revolt now?";
        strs[2] = "racecar";
        strs[3] = "Sir, I demand, I am a maid named Iris.";
        strs[4] = "Step on no pets!";
        strs[5] = "abacaxi";
        strs[6] = "i'm the goat";
        strs[7] = "avocado";
        
        
        for (String str : strs) {
            System.out.println(String.format("Is \"%s\" a palindrome:", str));
            System.out.println(palindromeCheck(str));
        }
    }
    
    public static boolean palindromeCheck(String str) {
        boolean palindromeFlag = true;
        str = str.toLowerCase().replaceAll("[(){} ,?;.'!’-]", "");
        Deque<Character> deque = new Deque<>();
        for (int i = 0; i < str.length(); i++)
            deque.addLast(str.charAt(i));
        while (deque.getSize() > 1 && palindromeFlag) {
            if (!deque.removeFirst().equals(deque.removeLast()))
                palindromeFlag = false;
        }
        return palindromeFlag;
    }
}
