package spokay.problems;

import spokay.Problem;
import spokay.PuzzleInputs;

import java.util.*;
import java.util.stream.Collectors;

public class Day5SeedFertilizer implements Problem {
    String puzzleInput = PuzzleInputs.exempleDay5;

    @Override
    public void run() {

        ArrayList<String> lines = Arrays.stream(puzzleInput.split("\n")).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> seeds = Arrays.stream(lines.get(0).split("seeds: ")[1].split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(seeds);

        HashMap<String, List<int[]>> mappings = new HashMap<>();

        lines.remove(0);

        List<Integer> emptyIndexes = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).isEmpty()){
                emptyIndexes.add(i);
            }
        }

        // use sublist method with the retrieved indexes of empty lines

        List<List<String>> splittedMappingLines = new ArrayList<>();

        for (int i = 0; i < emptyIndexes.size(); i++) {
            int stop = (i == emptyIndexes.size() - 1) ? lines.size() : emptyIndexes.get(i + 1);
            List<String> subList = lines.subList(emptyIndexes.get(i) + 1, stop);
            splittedMappingLines.add(subList);
        }

        // convert the values to maps

        splittedMappingLines.forEach(
                portion -> {
                    String name = portion.get(0).split(" ")[0];
                    List<int[]> values = new ArrayList<>();
                    portion
                            .stream()
                            .skip(1)
                            .forEach(
                                mapping -> values.add(
                                    Arrays.stream(
                                            mapping
                                                    .split(" ")
                                            )
                                            .mapToInt(Integer::parseInt)
                                            .toArray()
                            )
                    );
                    mappings.put(name, values);
                }
        );

        System.out.println(Arrays.toString(mappings.get("seed-to-soil").get(0)));

    }
}
