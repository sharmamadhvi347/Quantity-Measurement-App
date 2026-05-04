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

    @Test
    public void testAddition_SameUnit_Feet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testAddition_CrossUnit_FeetAndInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testAddition_CrossUnit_InchesAndFeet() {
        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(24.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(5.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(3.0, Length.LengthUnit.FEET)));
    }
}