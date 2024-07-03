package spokay.problems;

import spokay.Problem;
import spokay.PuzzleInputs;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2CubeConundrum implements Problem {
    String puzzleInput = PuzzleInputs.exempleDay2;

    @Override
    public void run() {
        String[] lines = puzzleInput.split("\n");

        int gameIdSums = 0;
        int minimumSetsSums = 0;

        for (String line : lines){
            String[] splittedLine = line.split(": ");

            int gameId = Integer.parseInt(splittedLine[0].substring(5));

            List<HashMap<String, Integer>> gameSets = new ArrayList<>();

            String[] setStrings = splittedLine[1].split("; ");

            Arrays.stream(setStrings)
                    .map(
                            this::getMapForSet
                    )
                    .forEach(
                            gameSets::add
                    );

            System.out.println(gameSets);

            AtomicInteger maxRed = new AtomicInteger(0);
            AtomicInteger maxGreen = new AtomicInteger(0);
            AtomicInteger maxBlue = new AtomicInteger(0);

            for (HashMap<String, Integer> set : gameSets){
                set.forEach(
                        (key, value) -> {
                            if (Objects.equals(key, "red") && value > maxRed.get()){
                                maxRed.set(value);
                            }
                            if (Objects.equals(key, "green") && value > maxGreen.get()){
                                maxGreen.set(value);
                            }
                            if (Objects.equals(key, "blue") && value > maxBlue.get()){
                               maxBlue.set(value);
                            }
                        }
                );
            }

            System.out.printf("%s\n%s\n%s%n", maxRed, maxGreen, maxBlue);


            if (maxRed.get() <= 12 && maxGreen.get() <= 13 && maxBlue.get() <= 14){
                gameIdSums += gameId;
                System.out.println("counted game " +gameId);
            }

            minimumSetsSums += maxRed.get() * maxGreen.get() * maxBlue.get();
            System.out.println("----");
        }

        System.out.println(gameIdSums);
        System.out.println(minimumSetsSums);
    }

    private HashMap<String, Integer> getMapForSet(String set) {
        String[] splittedSet = set.split(", ");

        HashMap<String, Integer> setMap = new HashMap<>();

        Arrays.stream(splittedSet)
                .map(
                        str -> str.split(" ")
                )
                .forEach(
                        strArr -> setMap.put(strArr[1], Integer.valueOf(strArr[0]))
                );

        return setMap;
    }
}
