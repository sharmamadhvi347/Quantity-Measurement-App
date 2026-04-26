package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_DifferentValue() {
        var f1 = new QuantityMeasurementApp.Feet(1.0);
        var f2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void testFeetEquality_Null() {
        var f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void testFeetEquality_DifferentType() {
        var f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals("test"));
    }

    @Test
    void testFeetEquality_SameReference() {
        var f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1));
    }
}