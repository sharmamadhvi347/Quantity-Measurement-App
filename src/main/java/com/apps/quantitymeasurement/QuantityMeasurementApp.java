package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1,
                                                      double v2, LengthUnit u2) {
        return new Length(v1, u1).equals(new Length(v2, u2));
    }

    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit to) {
        return length.convertTo(to);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2,
                                                   LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value,
                                                     Length.LengthUnit from,
                                                     Length.LengthUnit to) {
        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit to) {
        return length.convertTo(to);
    }

    // 🔥 UC6 METHOD
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static void main(String[] args) {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = demonstrateLengthAddition(l1, l2);

        System.out.println("Result: " + result); // 2.00 FEET
    public static boolean demonstrateLengthComparison(
            double v1, Length.LengthUnit u1,
            double v2, Length.LengthUnit u2
    ) {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);
        return demonstrateLengthEquality(l1, l2);
    }

    // 🔥 Overloaded Method 1
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit from,
            Length.LengthUnit to
    ) {
        Length l = new Length(value, from);
        return l.convertTo(to);
    }

    // 🔥 Overloaded Method 2
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit to
    ) {
        return length.convertTo(to);
    }

    public static void main(String[] args) {

        System.out.println(demonstrateLengthConversion(1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES));

        System.out.println(demonstrateLengthConversion(3.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.FEET));

        System.out.println(demonstrateLengthConversion(2.54,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES));
    }
}