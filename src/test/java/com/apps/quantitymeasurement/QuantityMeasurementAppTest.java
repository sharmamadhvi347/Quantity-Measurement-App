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

    // -------------------- UC12 TESTS --------------------

    @Test
    public void testSubtraction_SameUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5, LengthUnit.FEET);

        assertEquals(new Quantity<>(5, LengthUnit.FEET), a.subtract(b));
    }

    @Test
    public void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), a.subtract(b));
    }

    @Test
    public void testSubtraction_ExplicitUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(new Quantity<>(114, LengthUnit.INCHES),
                a.subtract(b, LengthUnit.INCHES));
    }

    @Test
    public void testDivision_SameUnit() {
        Quantity<WeightUnit> a = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(5, WeightUnit.KILOGRAM);

        assertEquals(2.0, a.divide(b));
    }

    @Test
    public void testDivision_CrossUnit() {
        Quantity<LengthUnit> a = new Quantity<>(24, LengthUnit.INCHES);
        Quantity<LengthUnit> b = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(1.0, a.divide(b));
    }

    @Test
    public void testDivision_ByZero() {
        Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> a.divide(b));
    }

    @Test
    public void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> b = new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> a.subtract((Quantity) b));
    }
}