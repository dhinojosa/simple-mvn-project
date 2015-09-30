package com.evolutionnext.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CalcStatsTest {

    @Test
    public void testMinimumValueWithNoElements() {
        List<Integer> integers = new ArrayList<Integer>();
        CalcStats calcStats = new CalcStats(integers);
        assertNull(calcStats.getMinimum());
    }

    @Test
    public void testMinimumValueWithOneElement() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(5);
        CalcStats calcStats = new CalcStats(integers);
        assertEquals(new Integer(5), calcStats.getMinimum());
    }

    @Test
    public void testMinimumValueWithTwoElements() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(5);
        integers.add(-10);
        CalcStats calcStats = new CalcStats(integers);
        assertEquals(new Integer(-10), calcStats.getMinimum());
    }


    @Test
    public void testMultipleElementAllTheSameValue() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(5);
        integers.add(5);
        integers.add(5);
        integers.add(5);
        CalcStats calcStats = new CalcStats(integers);
        assertEquals(new Integer(5), calcStats.getMinimum());
    }

    @Test
    public void testThatWePassANullAtContructionTime() {
        CalcStats calcStats = new CalcStats(null);
        assertNull(calcStats.getMinimum());
    }
}