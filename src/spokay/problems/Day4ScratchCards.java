package spokay.problems;

import spokay.Problem;
import spokay.PuzzleInputs;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day4ScratchCards implements Problem {
    public String puzzleInput = PuzzleInputs.day4Input;

    @Override
    public void run() {
        String[] lines = puzzleInput.split("\n");

        int pointsSum = 0;
        for (String line : lines){
            String[] lineSeparated = line.split(": ")[1].split(" \\| ");

            System.out.println("line input ----->" + Arrays.toString(lineSeparated));

            HashMap<String, List<Integer>> numbersList = new HashMap<>();

            ArrayList<Integer> winnersNumbers = Arrays
                    .stream(lineSeparated[0].split(" "))
                    .filter(str -> !Objects.equals(str, ""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));


            ArrayList<Integer> possesedNumbers = Arrays
                    .stream(lineSeparated[1].split(" "))
                    .filter(str -> !Objects.equals(str, ""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));

            System.out.println("Winning numbers ------> " + winnersNumbers);
            System.out.println("Possesed numbers------> " + possesedNumbers);
            System.out.println("---------------");


            AtomicInteger linePoints = new AtomicInteger(0);
            winnersNumbers.stream()
                    .filter(possesedNumbers::contains)
                    .forEach(num -> {
                        if (linePoints.get() == 0){
                            linePoints.set(1);
                        } else{
                            linePoints.set(linePoints.get()*2);
                        }
                    });


            System.out.println(linePoints);
//            System.out.println(Arrays.toString(lineSeparated[0].split(" ")));



//            System.out.println(winningNumbers);

            pointsSum += linePoints.get();
        }

        System.out.println(pointsSum);
    }

    static class ScratchCard{
        ArrayList<Integer> winningNumbers;
        ArrayList<Integer> possesedNumbers;
        List<CopyScratchCard> winningCopies = new ArrayList<>();

        public ScratchCard(ArrayList<Integer> possesedNumbers, ArrayList<Integer> winningNumbers) {
            this.possesedNumbers = possesedNumbers;
            this.winningNumbers = winningNumbers;
        }

    }
    static class OriginalScratchCard extends ScratchCard{


        public OriginalScratchCard(ArrayList<Integer> possesedNumbers, ArrayList<Integer> winningNumbers) {
            super(possesedNumbers, winningNumbers);
        }


    }

    static class CopyScratchCard extends ScratchCard{

        public CopyScratchCard(ArrayList<Integer> possesedNumbers, ArrayList<Integer> winningNumbers) {
            super(possesedNumbers, winningNumbers);
        }
    }
}
