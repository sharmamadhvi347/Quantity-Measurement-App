package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(12.0, Length.LengthUnit.INCHES)
                .equals(new Length(12.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInchesComparison() {
        assertTrue(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(12.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testInchesInequality() {
        assertFalse(new Length(12.0, Length.LengthUnit.INCHES)
                .equals(new Length(24.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testCrossUnitInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(13.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testMultipleFeetComparison() {
        assertTrue(new Length(2.0, Length.LengthUnit.FEET)
                .equals(new Length(24.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void yardEquals36Inches() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        assertTrue(new Length(100.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(39.3701, Length.LengthUnit.INCHES)));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        assertTrue(new Length(3.0, Length.LengthUnit.FEET)
                .equals(new Length(1.0, Length.LengthUnit.YARDS)));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        assertTrue(new Length(30.48, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void yardNotEqualToInches() {
        assertFalse(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(35.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);
        Length c = new Length(0.3333, Length.LengthUnit.YARDS);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                12.0, Length.LengthUnit.INCHES));
    }

    @Test
    public void convertFeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(3.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertTrue(result.equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length yards = new Length(2.0, Length.LengthUnit.YARDS);
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(yards, Length.LengthUnit.INCHES);

        assertTrue(result.equals(new Length(72.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void addFeetAndInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, Length.LengthUnit.INCHES);

        assertTrue(result.equals(new Length(24.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void addFeetAndInchesWithTargetUnitYards() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, Length.LengthUnit.YARDS);

        assertTrue(result.equals(new Length(0.67, Length.LengthUnit.YARDS)));
    }

    @Test
    public void addWithNullTargetUnitThrowsException() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, null));
    }
}