package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInchesComparison() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesInequality() {
        assertFalse(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(24.0, LengthUnit.INCHES)));
    }

    @Test
    public void testCrossUnitInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(13.0, LengthUnit.INCHES)));
    }

    @Test
    public void yardEquals36Inches() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(36.0, LengthUnit.INCHES)));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        assertTrue(new Length(100.0, LengthUnit.CENTIMETERS)
                .equals(new Length(39.3701, LengthUnit.INCHES)));
    }

    @Test
    public void convertFeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

        assertTrue(result.equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void addFeetAndInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void addWithTargetUnitInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, LengthUnit.INCHES);

        assertTrue(result.equals(new Length(24.0, LengthUnit.INCHES)));
    }

    @Test
    public void addWithTargetUnitYards() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, LengthUnit.YARDS);

        assertTrue(result.equals(new Length(0.67, LengthUnit.YARDS)));
    }

    @Test
    public void kilogramEquals1000Grams() {
        assertTrue(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(1000, WeightUnit.GRAM)));
    }

    @Test
    public void kilogramNotEqualToPound() {
        assertFalse(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(1, WeightUnit.POUND)));
    }

    @Test
    public void kilogramEqualsApproxPound() {
        assertTrue(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(2.20462, WeightUnit.POUND)));
    }

    @Test
    public void gramEqualsPound() {
        assertTrue(new Weight(453.592, WeightUnit.GRAM)
                .equals(new Weight(1, WeightUnit.POUND)));
    }

    @Test
    public void kilogramEqualsTonne() {
        assertTrue(new Weight(1000, WeightUnit.KILOGRAM)
                .equals(new Weight(1, WeightUnit.TONNE)));
    }

    @Test
    public void kilogramToGramConversion() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue());
    }

    @Test
    public void poundToKilogramConversion() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue());
    }

    @Test
    public void addKilogramAndGram() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM));
        assertEquals(2.0, result.getValue());
    }

    @Test
    public void addWeightsWithTargetUnit() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(2000.0, result.getValue());
    }
}