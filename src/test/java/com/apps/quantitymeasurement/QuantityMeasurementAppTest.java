package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    double EPS = 1e-6;

    // ✅ BASIC CONVERSION TESTS

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0,
                Length.convert(24.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.FEET),
                EPS);
    }

    @Test
    void testYardsToInches() {
        assertEquals(36.0,
                Length.convert(1.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testCentimetersToInches() {
        assertEquals(1.0,
                Length.convert(2.54,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES),
                EPS);
    }

    // ✅ ROUND TRIP

    @Test
    void testRoundTrip() {
        double original = 5.0;

        double converted = Length.convert(
                original,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        double back = Length.convert(
                converted,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET);

        assertEquals(original, back, EPS);
    }

    // ✅ ZERO + NEGATIVE

    @Test
    void testZeroConversion() {
        assertEquals(0.0,
                Length.convert(0.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testNegativeConversion() {
        assertEquals(-12.0,
                Length.convert(-1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPS);
    }

    // ✅ SAME UNIT

    @Test
    void testSameUnitConversion() {
        assertEquals(5.0,
                Length.convert(5.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.FEET),
                EPS);
    }

    // ✅ EXCEPTIONS

    @Test
    void testNullUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(1.0, null, Length.LengthUnit.FEET));
    }

    @Test
    void testInvalidValueThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(Double.NaN,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }

    // ✅ OBJECT CONVERSION

    @Test
    void testConvertUsingObject() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = feet.convertTo(Length.LengthUnit.INCHES);

        assertTrue(inches.equals(
                new Length(36.0, Length.LengthUnit.INCHES)
        ));
    }

    // ✅ OVERLOADED METHOD

    @Test
    void testOverloadedConversion() {
        Length yards = new Length(2.0, Length.LengthUnit.YARDS);

        Length inches = QuantityMeasurementApp
                .demonstrateLengthConversion(yards,
                        Length.LengthUnit.INCHES);

        assertTrue(inches.equals(
                new Length(72.0, Length.LengthUnit.INCHES)
        ));
    }
}