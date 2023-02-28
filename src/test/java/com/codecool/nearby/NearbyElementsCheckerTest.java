package com.codecool.nearby;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NearbyElementsCheckerTest {

    private NearbyElementsChecker nearbyElementsChecker;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() throws Exception {
        // redirect System.out.println
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        int[][] testArray =
                {
                        {2, 0, 4, 1241, 12, 5, 11, 1110, -42, 424, 1, 12323},
                        {1, 3, 5, 7},
                        {321, 320, 32, 3, 41241, -11, -12, -13, -66, -688},
                        {25, -3, 41, 325, 7, 424, 42},
                };
        nearbyElementsChecker = new NearbyElementsChecker(testArray);
    }

    @After
    public void restoreStreams() {
        // reset System.out.println
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void getNearbyElements_CentralWithNeighbors_AllNecessaryNeighborsInGivenDistance() {
        int[] results = nearbyElementsChecker.getNearbyElements(0, 2, 2);
        assertArrayEquals(results, new int[]{2, 0, 1241, 12});
    }

    @Test
    public void getNearbyElements_EdgeLeftWithNeighbors_AllNecessaryNeighborsInGivenDistance() {
        int[] results = nearbyElementsChecker.getNearbyElements(1, 0, 1);
        assertArrayEquals(results, new int[]{3});
    }

    @Test
    public void getNearbyElements_EdgeRightWithNeighbors_AllNecessaryNeighborsInGivenDistance() {
        int[] results = nearbyElementsChecker.getNearbyElements(1, 3, 5);
        assertArrayEquals(results, new int[]{1, 3, 5});
    }

    @Test
    public void getNearbyElements_OutOfRangeWithNeighbors_AllNecessaryNeighborsInGivenDistance() {
        int[] results = nearbyElementsChecker.getNearbyElements(1, 4, 5);
        assertArrayEquals(results, new int[]{1, 3, 5, 7});
    }

    @Test
    public void getNearbyElements_OutOfRangeWithoutNeighbors_Empty() {
        int[] results = nearbyElementsChecker.getNearbyElements(1, 9, 5);
        assertEquals(0, results.length);
    }

    @Test
    public void getNearbyElements_RowOutOfRange_Null() {
        int[] results = nearbyElementsChecker.getNearbyElements(4, 7, 2);
        assertNull(results);
    }

    @Test
    public void prettyPrint_allNumberPrinted() {
        int[][] testArray =
                {
                        {1, 3, 5, 7}
                };
        nearbyElementsChecker = new NearbyElementsChecker(testArray);

        nearbyElementsChecker.prettyPrint();

        assertEquals(String.format(" 1 3 5 7%1$s", System.lineSeparator()), outContent.toString());
    }

    @Test
    public void prettyPrint_variableLengthNumbers_SpacingIsOneLongerThanLongestNumber() {
        int[][] testArray =
                {
                        {1, 3, 5, 7},
                        {321, 13, 66, 4688},
                };
        nearbyElementsChecker = new NearbyElementsChecker(testArray);

        nearbyElementsChecker.prettyPrint();

        assertEquals(String.format("    1    3    5    7%1$s  321   13   66 4688%1$s", System.lineSeparator()), outContent.toString());
    }

    @Test
    public void prettyPrint_longestNumberNegative_SpacingIsOneLongerThanLongestNumberWithSign() {
        int[][] testArray =
                {
                        {1, 3, 5, 7},
                        {321, -13, -66, -688},
                };
        nearbyElementsChecker = new NearbyElementsChecker(testArray);

        nearbyElementsChecker.prettyPrint();

        assertEquals(String.format("    1    3    5    7%1$s  321  -13  -66 -688%1$s", System.lineSeparator()), outContent.toString());
    }
}
