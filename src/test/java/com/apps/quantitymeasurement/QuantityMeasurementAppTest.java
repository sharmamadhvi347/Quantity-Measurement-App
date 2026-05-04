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
    public void testFeetEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
        assertTrue(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    void testNegativeConversion() {
        assertEquals(-12.0,
                Length.convert(-1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPS);
    }

    // ✅ SAME UNIT

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(12.0, LengthUnit.INCHES)));
        assertTrue(new Length(12.0, Length.LengthUnit.INCHES)
                .equals(new Length(12.0, Length.LengthUnit.INCHES)));
    void testSameUnitConversion() {
        assertEquals(5.0,
                Length.convert(5.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.FEET),
                EPS);
    }

    // ✅ EXCEPTIONS

    @Test
    public void testFeetInchesComparison() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(2.0, Length.LengthUnit.FEET)));
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
    public void testInchesInequality() {
        assertFalse(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(24.0, LengthUnit.INCHES)));
        assertFalse(new Length(12.0, Length.LengthUnit.INCHES)
                .equals(new Length(24.0, Length.LengthUnit.INCHES)));
    void testConvertUsingObject() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = feet.convertTo(Length.LengthUnit.INCHES);

        assertTrue(inches.equals(
                new Length(36.0, Length.LengthUnit.INCHES)
        ));
    }

    // ✅ OVERLOADED METHOD

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
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, null));
    void testOverloadedConversion() {
        Length yards = new Length(2.0, Length.LengthUnit.YARDS);

        Length inches = QuantityMeasurementApp
                .demonstrateLengthConversion(yards,
                        Length.LengthUnit.INCHES);

        assertTrue(inches.equals(
                new Length(72.0, Length.LengthUnit.INCHES)
        ));
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