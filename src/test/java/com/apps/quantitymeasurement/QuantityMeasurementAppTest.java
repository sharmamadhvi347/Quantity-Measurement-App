package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void lengthFeetEqualsInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void weightKilogramEqualsGrams() {
        Quantity<WeightUnit> q1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void convertFeetToInches() {
        Quantity<LengthUnit> q = new Quantity<>(1, LengthUnit.FEET);

        assertEquals(12.0, q.convertTo(LengthUnit.INCHES));
    }

    @Test
    public void addLengths() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue());
    }

    @Test
    public void addWeightsInTargetUnit() {
        Quantity<WeightUnit> q1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(500, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.GRAM);

        assertEquals(1500.0, result.getValue());
    }

    @Test
    public void volumeLiterEqualsMilliliter() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    public void convertLiterToMilliliter() {
        Quantity<VolumeUnit> v = new Quantity<>(1, VolumeUnit.LITRE);

        assertEquals(1000.0, v.convertTo(VolumeUnit.MILLILITRE));
    }

    @Test
    public void addVolumeLitersAndMilliliters() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue());
    }

    @Test
    public void addVolumeWithTargetUnit() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

        assertTrue(result.getValue() > 4000); // ~4785 ml
    }
}