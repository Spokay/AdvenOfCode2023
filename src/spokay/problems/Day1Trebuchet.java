package spokay.problems;

import spokay.Problem;
import spokay.PuzzleInputs;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Day1Trebuchet implements Problem {
    String puzzleInput = PuzzleInputs.day1Input;

    HashMap<String, Character> digitsSpellings = fillSpellings();

    @Override
    public void run() {
        String[] lines = puzzleInput.split("\n");
        Integer finalResult = 0;


        for (String line : lines){
            System.out.print("Expected result : " + line + "\n");
            String currentString = line.trim();
            HashMap<Character, int[]> foundDigits = new HashMap<>();

            for (Map.Entry<String, Character> digitToSpellEntry : digitsSpellings.entrySet()){
                currentString = currentString.replace(digitToSpellEntry.getValue().toString(), digitToSpellEntry.getKey());
            }

            System.out.println("Parsed string :" + currentString);

            for (Map.Entry<String, Character> spellToDigitEntry : digitsSpellings.entrySet()){
                if (currentString.contains(spellToDigitEntry.getKey())){
                    int digitFirstPos = currentString.indexOf(spellToDigitEntry.getKey());
                    int digitLastPos = currentString.lastIndexOf(spellToDigitEntry.getKey());

                    foundDigits.put(spellToDigitEntry.getValue(), new int[]{digitFirstPos, digitLastPos});

                    System.out.println(foundDigits);
                }
            }
            System.out.println(foundDigits);

            /*ArrayList<Character> digits = currentString.chars()
                    .mapToObj(i -> (char) i)
                    .filter(Character::isDigit)
                            .collect(Collectors.toCollection(ArrayList::new));*/

//            String resultString = foundDigits.get(0).toString() + foundDigits.get(foundDigits.size() - 1);
//            System.out.println(resultString);

            int firstDigitPos = Integer.MAX_VALUE;
            int lastDigitPos = Integer.MIN_VALUE;

            Character firstDigitChar = null;
            Character lastDigitChar = null;

            for (Map.Entry<Character, int[]> digitEntry : foundDigits.entrySet()){
                if (digitEntry.getValue()[0] <= firstDigitPos){
                    firstDigitPos = digitEntry.getValue()[0];
                    firstDigitChar = digitEntry.getKey();
                }
                if (digitEntry.getValue()[1] >= lastDigitPos){
                    lastDigitPos = digitEntry.getValue()[1];
                    lastDigitChar = digitEntry.getKey();
                }
            }
            Integer lineRes = Integer.valueOf("%c%c".formatted(firstDigitChar,lastDigitChar));
            System.out.println(lineRes);
            finalResult += lineRes;
        };

        System.out.println(finalResult);
    }

    HashMap<String, Character> fillSpellings(){
        return new HashMap<>(){
            {
                put("one", '1');
                put("two", '2');
                put("three", '3');
                put("four", '4');
                put("five", '5');
                put("six", '6');
                put("seven", '7');
                put("eight", '8');
                put("nine", '9');
            }
        };
    }
}
