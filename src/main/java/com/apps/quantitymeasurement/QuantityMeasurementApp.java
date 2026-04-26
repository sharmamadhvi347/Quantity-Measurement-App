package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static void demonstrateLengthComparison(double v1, Length.LengthUnit u1,
                                                   double v2, Length.LengthUnit u2) {

        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        System.out.println("Comparing: " + v1 + " " + u1 + " and " + v2 + " " + u2);
        System.out.println("Equal? " + l1.equals(l2));
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {

        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET,
                12.0, Length.LengthUnit.INCHES);

        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS,
                3.0, Length.LengthUnit.FEET);

        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS,
                36.0, Length.LengthUnit.INCHES);

        demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS,
                39.3701, Length.LengthUnit.INCHES);

        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET,
                1.0, Length.LengthUnit.YARDS);

        demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETERS,
                1.0, Length.LengthUnit.FEET);
    }
}