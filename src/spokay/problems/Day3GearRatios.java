package spokay.problems;

import spokay.Problem;
import spokay.PuzzleInputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3GearRatios implements Problem {
    String puzzleInput = PuzzleInputs.day3Input;
    @Override
    public void run() {
        String[] lines = puzzleInput.split("\n");
        char[][] engine = new char[lines.length][lines[0].length()];
        List<Integer> validatedNumbers = new ArrayList<>();

        for (int y = 0; y < lines.length; y++){
            char[] line = lines[y].toCharArray();
            System.out.println(line);
            System.arraycopy(line, 0, engine[y], 0, line.length);
        }


        List<Symbol> symbols = new ArrayList<>();

        System.out.println(Arrays.deepToString(engine));


        for (int y = 0; y < engine.length; y++){
            // y axis loop
            char[] line = engine[y];
            for (int x = 0; x < line.length; x++){
                // x axis loop
                if (isSymbol(line[x])){
                    List<AdjascentDigit> adjascentDigits = new ArrayList<>();
                    System.out.println(line[x] + " : " + y + " / " + x);
                    for (int j = y - 1; j <= y + 1; j++){
                        for (int h = x - 1; h <= x + 1; h++){
                            // check the neighbours
                            // skip the borders and the symbol itself
                            if(j == y && h == x)
                                continue;
                            if (j < 0)
                                continue;
                            if (j >= engine.length)
                                continue;
                            if (h < 0)
                                continue;
                            if (h >= line.length)
                                continue;

                            if (Character.isDigit(engine[j][h])){
                                boolean isAlreadyCounted = false;
                                for (AdjascentDigit digit : adjascentDigits){
                                    if (digit.getY() == j && Math.abs(digit.getX() - h) <= 1) {
                                        isAlreadyCounted = true;
                                        digit.setX(h);
                                        break;
                                    }
                                }
                                if (!isAlreadyCounted) {
//                                    int[] symbolCoords = new int[]{y, x};
                                    AdjascentDigit adjDigit = new AdjascentDigit(j, h);
                                    adjascentDigits.add(adjDigit);
                                }

                            }
                        }
                    }
                    symbols.add(new Symbol(y, x, adjascentDigits));
                }

            }
        }

        for (Symbol symbol : symbols){
            if (symbol.getLinkedDigits().size() == 2){
                int firstNumber = 0;
                int secondNumber = 0;
                for (AdjascentDigit digit : symbol.getLinkedDigits()){
                    int xLeftCheck = 1;
                    int xRightCheck = 1;
                    System.out.println(engine[digit.getY()][digit.getX()]);
                    String number = Character.toString(engine[digit.getY()][digit.getX()]);

                    while ((digit.getX() - xLeftCheck) >= 0){
                        char nextLeftChar = engine[digit.getY()][(digit.getX() - xLeftCheck)];
                        boolean nextLeftIsDigit = Character.isDigit(nextLeftChar);
                        if (nextLeftIsDigit){
                            number = nextLeftChar + number;
                            System.out.println(nextLeftChar + " found -- > currentNumber = " + number);
                            xLeftCheck++;
                        }
                        else{
                           break;
                        }
                    }

                    while ((digit.getX() + xRightCheck) < engine[0].length){
                        char nextRightChar = engine[digit.getY()][(digit.getX() + xRightCheck)];
                        boolean nextRightIsDigit = Character.isDigit(nextRightChar);
                        if (nextRightIsDigit){
                            number = number + nextRightChar;
                            System.out.println(nextRightChar + " found -- > currentNumber = " + number);
                            xRightCheck++;
                        }
                        else{
                           break;
                        }
                    }

                    if (firstNumber == 0){
                        firstNumber = Integer.parseInt(number);
                        System.out.println(firstNumber);
                    }else if (secondNumber == 0){
                        secondNumber = Integer.parseInt(number);
                        System.out.println(secondNumber);
                    }

                    System.out.println("--------------");
                }
                int gearRatio = firstNumber * secondNumber;
                validatedNumbers.add(gearRatio);
            }
        }



        System.out.println(validatedNumbers);
//        System.out.println(adjascentDigits);
        int sum = 0;
        for (Integer i : validatedNumbers){
            sum+=i;
        }
        System.out.println(sum);
    }

    public boolean isSymbol(char ch){
        return ch == '*';
    }

    static class Symbol{
        public int y;
        public int x;
        public List<AdjascentDigit> linkedDigits;

        public Symbol(int y, int x, List<AdjascentDigit> adjascentDigits) {
            this.y = y;
            this.x = x;
            this.linkedDigits = adjascentDigits;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public List<AdjascentDigit> getLinkedDigits() {
            return linkedDigits;
        }
    }


    static class AdjascentDigit{
        public int y;
        public int x;

        public AdjascentDigit(int y, int x){
            this.y = y;
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
