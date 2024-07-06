package spokay.problems;

import spokay.Problem;
import spokay.PuzzleInputs;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day4ScratchCards implements Problem {
    public String puzzleInput = PuzzleInputs.exempleDay4;

    @Override
    public void run() {
        String[] lines = puzzleInput.split("\n");

        int pointsSum = 0;

        HashMap<Integer, ScratchCard> scratchCards = new HashMap<>();

        for (String line : lines){
            String[] splittedLine = line.split(": ");
            int cardNumber = Integer.parseInt(splittedLine[0].split("Card ")[1].trim());
            System.out.println("Card Number : " + cardNumber);
            String[] numbersSeparated = splittedLine[1].split(" \\| ");

            System.out.println("line input ----->" + Arrays.toString(numbersSeparated));


            ArrayList<Integer> winnersNumbers = Arrays
                    .stream(numbersSeparated[0].split(" "))
                    .filter(str -> !Objects.equals(str, ""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));


            ArrayList<Integer> possessedNumbers = Arrays
                    .stream(numbersSeparated[1].split(" "))
                    .filter(str -> !Objects.equals(str, ""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));



            AtomicInteger linePoints = new AtomicInteger(0);
            winnersNumbers.stream()
                    .filter(possessedNumbers::contains)
                    .forEach(num -> linePoints.getAndIncrement());

            System.out.println(linePoints);


            System.out.println("Winning numbers ------> " + winnersNumbers);
            System.out.println("Possesed numbers------> " + possessedNumbers);
            System.out.println("---------------");


            scratchCards.put(cardNumber, new ScratchCard(cardNumber, linePoints.get()));
//            System.out.println(linePoints);



//            System.out.println(winningNumbers);

        }

        HashMap<Integer, Integer> amountOfInstances = new HashMap<>();

        for (int i = 1; i <= scratchCards.size(); i++) {
            amountOfInstances.put(i, 0);
        }

        for (Map.Entry<Integer, ScratchCard> scratchCard : scratchCards.entrySet()){
            ScratchCard originalScratchCard = scratchCard.getValue();

            ScratchCard currentCard = originalScratchCard;
            int initialBranch = originalScratchCard.cardNumber;
            int amountOfNewBranches = currentCard.amountOfWinningNumbers;
            int offset = 1;
//            System.out.println(offset);
            do {
                offset = 1;
                while (offset <= currentCard.amountOfWinningNumbers) {
                    if (scratchCards.containsKey(currentCard.cardNumber + offset)){
                        amountOfInstances.replace(currentCard.cardNumber, amountOfInstances.get(currentCard.cardNumber) + 1);
                        currentCard = scratchCards.get(currentCard.cardNumber + offset);
                        amountOfNewBranches = currentCard.amountOfWinningNumbers;
                    }else{
                        break;
                    }
                    offset++;
                }

            } while (amountOfNewBranches != 0);

        }

        System.out.println(amountOfInstances);


//        System.out.println(amountOfInstanceForScratchCard(scratchCards.get(1), new HashMap<>(), scratchCards));


//        System.out.println(pointsSum);
    }

    public static class ScratchCard{
        Integer cardNumber;
        int amountOfWinningNumbers;
        List<ScratchCard> winningCopies = new ArrayList<>();
        boolean scratchCardProcessed = false;
        int instances = 0;


        public ScratchCard(Integer cardNumber, int winningNumbers) {
            this.cardNumber = cardNumber;
            this.amountOfWinningNumbers = winningNumbers;
        }

    }


    /*public int amountOfInstanceForScratchCard(ScratchCard card, HashMap<Integer, Integer> memoizationMap, HashMap<Integer, ScratchCard> originalCards) {
        if (card.amountOfWinningNumbers == 0) {
            return 1; // Base case: card itself
        }
        if (memoizationMap.containsKey(card.cardNumber)) {
            return memoizationMap.get(card.cardNumber); // Return memoized result
        }
        int totalInstances = 1; // Count the card itself
        for (int i = 1; i <= card.amountOfWinningNumbers; i++) {
            int nextCardNumber = card.cardNumber + i;
            ScratchCard nextCard = originalCards.get(nextCardNumber); // Assuming scratchCards map is accessible
            if (nextCard != null) {
                totalInstances += amountOfInstanceForScratchCard(nextCard, memoizationMap, originalCards); // Recursive call
            }
        }
        memoizationMap.put(card.cardNumber, totalInstances); // Memoize result
        return totalInstances;
    }*/
}
