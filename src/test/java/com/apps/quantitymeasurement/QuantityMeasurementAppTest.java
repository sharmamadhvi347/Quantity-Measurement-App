package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testYardToYard_SameValue() {
        assertTrue(new Length(1, Length.LengthUnit.YARDS)
                .equals(new Length(1, Length.LengthUnit.YARDS)));
    }

    @Test
    void testYardToYard_DifferentValue() {
        assertFalse(new Length(1, Length.LengthUnit.YARDS)
                .equals(new Length(2, Length.LengthUnit.YARDS)));
    }

    @Test
    void testYardToFeet() {
        assertTrue(new Length(1, Length.LengthUnit.YARDS)
                .equals(new Length(3, Length.LengthUnit.FEET)));
    }

    @Test
    void testFeetToYard() {
        assertTrue(new Length(3, Length.LengthUnit.FEET)
                .equals(new Length(1, Length.LengthUnit.YARDS)));
    }

    @Test
    void testYardToInches() {
        assertTrue(new Length(1, Length.LengthUnit.YARDS)
                .equals(new Length(36, Length.LengthUnit.INCHES)));
    }

    @Test
    void testCentimeterToInches() {
        assertTrue(new Length(1, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES)));
    }

    @Test
    void testCentimeterNotEqualToFeet() {
        assertFalse(new Length(1, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1, Length.LengthUnit.FEET)));
    }

    @Test
    void testTransitiveProperty() {
        Length yard = new Length(1, Length.LengthUnit.YARDS);
        Length feet = new Length(3, Length.LengthUnit.FEET);
        Length inches = new Length(36, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    void testNullComparison() {
        Length l = new Length(1, Length.LengthUnit.YARDS);
        assertFalse(l.equals(null));
    }

    @Test
    void testSameReference() {
        Length l = new Length(1, Length.LengthUnit.YARDS);
        assertTrue(l.equals(l));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1, null);
        });
    }
}