package spokay;

import spokay.problems.Day1Trebuchet;
import spokay.problems.Day2CubeConundrum;
import spokay.problems.Day3GearRatios;

public class CodeChallengeMain {

    public static void main(String[] args) {
        runProblem(new Day3GearRatios());
    }

    public static void runProblem(Problem pb){
        pb.run();
    }

}
