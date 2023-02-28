package com.codecool.nearby;

import java.util.Arrays;

/**
 * The task of the class is to find and return the nearby elements from a two-dimensional int array.
 * To keep it as an one class application, the entry point (main method) and test data is kept here also.
 */
public class NearbyElementsChecker {

    /*
     * Constructor. The array to work with is given here to the instance.
     *
     * @param elements the 2 dimensional int array to search through
     */
    private int[][] elements;

    public NearbyElementsChecker(int[][] elements) {
        this.elements = elements;
    }

    /**
     * Instance method for returning nearby elements.
     *
     * @param row   row of the target element
     * @param col   column of the target element
     * @param range maximum distance of nearby elements to give back
     * @return array holding the nearby elements within range, except for the target element, may be of zero length
     * @return null if row is out of bounds
     */
    public int[] getNearbyElements(int row, int col, int range) {
        int elementsRowSize = elements.length;
        int currentRowSize;
        int start;
        int end;
        int[] results = new int[0];

        if (row < 0 || row >= elementsRowSize) {
            return null;
        } else {
            currentRowSize = elements[row].length;
        }

        if (col + range < 0 || col - range > currentRowSize) {
            return results;
        } else {
            start = Math.max(col - range, 0);
            if (col == currentRowSize - 1) {
                end = currentRowSize - 1;
            } else {
                end = Math.min(col + range, currentRowSize);
            }
        }

        if (end - start == 0) {
            return results;
        }

        results = new int[end - start];
        int index = 0;
        for (int i = start; i <= end; i++) {
            if (i == col) {
                continue;
            }
            results[index] = elements[row][i];
            index++;
        }
        return results;
    }

    /**
     * Instance method for pretty printing the elements
     */
    public void prettyPrint() {
        int max = 0;
        for (int[] row : elements) {
            for (int number : row) {
                if (String.valueOf(number).length() > max) {
                    max = String.valueOf(number).length();
                }
            }
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                result.append(" ");
                for (int k = 0; k < max - String.valueOf(elements[i][j]).length(); k++) {
                    result.append(" ");
                }
                result.append(elements[i][j]);

            }

            if (i < elements.length - 1) {
                result.append("\r\n");
            }

        }

        System.out.println(result);
    }

    // The main method with this declaration is the entry point of Java applications.
    public static void main(String[] args) {

        int[][] testArray = new int[][]{
                {2, 0, 4, 1241, 12, 5, 11, 1110, -42, 424, 1, 12323},
                {1, 3, 5, 7},
                {321, 320, 32, 3, 41241, -11, -12, -13, -66, -688},
                {25, -3, 41, 325, 7, 424, 42}
        };

        // Create an instance of our class with the test data.
        NearbyElementsChecker nearbyElementsChecker = new NearbyElementsChecker(testArray);
        nearbyElementsChecker.getNearbyElements(1, 3, 5);

        // Print the two-dimensional array
        nearbyElementsChecker.prettyPrint();
    }

}
