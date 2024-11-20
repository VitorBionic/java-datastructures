package recursion.examples.drawingEnglishRule;

public class Program {
    public static void main(String[] args) {
        drawRuler(12, 4);
    }
    
    public static void drawOneTick(int tickLength) {
        drawOneTick(tickLength, -1);
    }

    public static void drawOneTick(int tickLength, int tickLabel) {
        for (int i = 0; i < tickLength; i++)
            System.out.print("-");
        if (tickLabel >= 0)
            System.out.print(" " + tickLabel);
        System.out.println();
    }

    public static void drawTicks(int tickLength) {
        if (tickLength > 0) {
            drawTicks(tickLength - 1);
            drawOneTick(tickLength);
            drawTicks(tickLength - 1);
        }
    }

    public static void drawRuler(int ninches, int majorLength) {
        drawOneTick(majorLength, 0);
        for (int i = 1; i <= ninches; i++) {
            drawTicks(majorLength - 1);
            drawOneTick(majorLength, i);
        }
    }
}
