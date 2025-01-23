package algorithms.example.numbersystemconverter;

public class Program {
    public static void main(String[] args) {
        
        System.out.println(BinaryToDecConverter.convertToBin(12));
        
        System.out.println();
        
        System.out.println(BinaryToDecConverter.convertToBinWithSign(12));
        System.out.println(BinaryToDecConverter.convertToBinWithSign(-12));
        System.out.println(BinaryToDecConverter.convertToBinWithSign(0));
        
        System.out.println();
        
        System.out.println(BinaryToDecConverter.convertToBinOneComplement(7));
        System.out.println(BinaryToDecConverter.convertToBinOneComplement(-7));
        System.out.println(BinaryToDecConverter.convertToBinOneComplement(0));
        
        System.out.println();
        
        System.out.println(BinaryToDecConverter.convertToBinTwoComplement(7));
        System.out.println(BinaryToDecConverter.convertToBinTwoComplement(-7));
        System.out.println(BinaryToDecConverter.convertToBinTwoComplement(0));
        
        System.out.println();
        
        System.out.println(NumberSystemConverter.convertToDec("101100", 2));
        System.out.println(NumberSystemConverter.convertToDec("72", 8));
        System.out.println(NumberSystemConverter.convertToDec("A12B", 16));
        
        System.out.println();
        
        System.out.println(NumberSystemConverter.convert("101100", 2, 10));
        System.out.println(NumberSystemConverter.convert("72", 8, 10));
        System.out.println(NumberSystemConverter.convert("A12B", 16, 10));
        System.out.println(NumberSystemConverter.convert("101100", 2, 16));
        System.out.println(NumberSystemConverter.convert("72", 8, 2));
        System.out.println(NumberSystemConverter.convert("A12B", 16, 8));
        
    }
}
