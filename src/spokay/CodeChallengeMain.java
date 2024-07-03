package spokay;

import spokay.problems.Day1Trebuchet;
import spokay.problems.Day2CubeConundrum;
import spokay.problems.Day3GearRatios;
import spokay.problems.Day4ScratchCards;

public class CodeChallengeMain {

    public static void main(String[] args) {
        runProblem(new Day4ScratchCards());
    }

    public static void runProblem(Problem pb){
        pb.run();
    }

}
