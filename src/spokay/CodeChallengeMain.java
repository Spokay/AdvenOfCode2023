package spokay;

import spokay.problems.*;

public class CodeChallengeMain {

    public static void main(String[] args) {
        runProblem(new Day5SeedFertilizer());
    }

    public static void runProblem(Problem pb){
        pb.run();
    }

}
