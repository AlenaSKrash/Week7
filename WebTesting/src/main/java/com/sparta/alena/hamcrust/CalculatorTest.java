package com.sparta.alena.hamcrust;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

public class CalculatorTest {
    // two junit tests
    @Test
    @DisplayName("Add returns correct value")
    public void addTest() {
        Calculator calculator = new Calculator(2, 6);
        Assertions.assertEquals(8.0, calculator.add());
    }

    @Test
    @DisplayName("DivisibleBy returns correct value")
    public void divisibleByTest() {
        Calculator calculator = new Calculator(6, 2.0);
        Assertions.assertTrue(calculator.divisibleBy());
    }
    // same tests using hamcrest
    @Test
    @DisplayName("Add returns correct value - Hamcrest")
    public void addTestHamcrest() {
        Calculator calculator = new Calculator(2, 6);
        MatcherAssert.assertThat(calculator.add(), equalTo(8.0));
    }

    @Test
    @DisplayName("DivisibleBy returns correct value - Hamcrest")
    public void divisibleByTestHamcrest() {
        Calculator calculator = new Calculator(6, 2.0);
        MatcherAssert.assertThat(calculator.divisibleBy(), is(true));
    }

    @Test
    public void someMoreMatches() {
        var calculator = new Calculator(6.0, 4.0);
        MatcherAssert.assertThat(calculator.divisibleBy(), is(false));
        MatcherAssert.assertThat(calculator.toString(), containsString("Calculator"));
        MatcherAssert.assertThat(calculator.add(), greaterThanOrEqualTo(9.5));
        MatcherAssert.assertThat(calculator, instanceOf(Calculator.class));
    }

    @Test
    public void stringMatcherTests() {
        var subject = "A man, a plan, a canal - Panama";
        MatcherAssert.assertThat(subject, not(emptyOrNullString()));
        MatcherAssert.assertThat(subject, equalToIgnoringCase("a man, a plan, a canal - panama"));
        MatcherAssert.assertThat(subject, endsWith("anama"));
        MatcherAssert.assertThat(subject, not(containsString("potato")));
    }

    // collection constraints
    @Test
    public void testListOfStrings() {
        var fruit = new ArrayList<String>(Arrays.asList(new String[]{"apple", "pear", "banana", "peach"}));
        MatcherAssert.assertThat(fruit, hasSize(4));
        MatcherAssert.assertThat(fruit, hasItems("pear", "banana"));
        MatcherAssert.assertThat(fruit, containsInAnyOrder("apple", "banana", "pear", "peach"));
    }

    @Test
    public void testList() {
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(new Integer[]{4, 2, 7, 5, 9  }));
        MatcherAssert.assertThat(nums, everyItem(greaterThanOrEqualTo(2)));
        MatcherAssert.assertThat(nums, allOf(everyItem(greaterThan(5)), everyItem(lessThan(10))));
    }
}