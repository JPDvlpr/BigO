package algorithms;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class BigOAlgorithms {

    /**
     * main execution of algorithms has random generator, scanner.
     * Executes each algorithm, unused ones are commented out
     * @param args
     */
    public static void main(String[] args) {
        Random objGenerator = new Random();
        int input = 10000;
        int[] inputArray = new int[input];
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("results_E.txt"),
                StandardCharsets.UTF_8));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 10000; i <= 200000; i += 10000) {
            double totalTime = 0;
            for (int runNumber = 1; runNumber <= 20; runNumber++) {
                //create random array
                inputArray = new int[i];

                for (int j = 0; j < i; j++) {
                    inputArray[j] = objGenerator.nextInt(inputArray.length / 2);
                }
                //get starting time
                LocalTime before = LocalTime.now();

                //run algorithm
//                findDuplicatesA(inputArray);
//                findDuplicatesB(inputArray);
//                findDuplicatesC(inputArray);
//                findDuplicatesD(inputArray);
                findDuplicatesE(inputArray);

                //time elapsed
                double nanosElapsed = Math.abs(ChronoUnit.NANOS.between(LocalTime.now(), before));
                int NANOS_IN_SECOND = 1000000000;
                double secondsElapsed = nanosElapsed / NANOS_IN_SECOND;
                totalTime += secondsElapsed;
            }
            try {
                if (writer != null) {
                    writer.write(inputArray.length + ", " + totalTime / 20);
                    System.out.println(inputArray.length);
                }
                if (writer != null) {
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * first algoirthm uses sort
     * @param input integer array input
     * @return input length - newset size
     */
    public static int findDuplicatesA(int[] input) {
        Arrays.sort(input);
        boolean match = false;
        int counter = 0;
        for (int i = 1; i < input.length; i++) {
            if (input[i] == input[i - 1]) {
                if (!match) {
                    match = true;
                    counter++;
                }
            } else {
                match = false;
            }
        }
        return counter;
    }

    /**
     * algorithm b creates an arraylist and loops
     * through elements
     * @param input integer array input
     * @return input length - newset size
     */
    public static int findDuplicatesB(int[] input) {
        // Create a new ArrayList
        ArrayList<Integer> newList = new ArrayList<>();

        for (int indices : input) {
            if (!newList.contains(indices)) {
                newList.add(indices);
            }
        }
        return input.length - newList.size();
    }

    /**
     * a treeset is created and loops through
     * elements to add them
     * @param input integer array input
     * @return input length - newset size
     */
    public static int findDuplicatesC(int[] input) {
        TreeSet<Integer> newSet = new TreeSet<>();
        for (int newArray : input) {
            newSet.add(newArray);
        }
        return input.length - newSet.size();
    }

    /**
     * hashset is used, elements are looped through
     * and added
     * @param input integer array input
     * @return input length - newset size
     */
    public static int findDuplicatesD(int[] input) {
        HashSet<Integer> newSet = new HashSet<>();
        for (int newArray : input) {
            newSet.add(newArray);
        }
        return input.length - newSet.size();
    }

    /**
     * counter is kept with the input array if
     * there is a match the counter increments
     * @param input integer array input
     * @return counter
     */
    private static int findDuplicatesE(int[] input) {
        int counter = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j]) {
                    counter++;
                    break;
                }
            }
        }
        final int counter1 = counter;
        return counter1;
    }
}