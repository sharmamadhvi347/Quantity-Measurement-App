package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, Length.LengthUnit u1,
                                                      double v2, Length.LengthUnit u2) {
        return new Length(v1, u1).equals(new Length(v2, u2));
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

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2,
                                                   Length.LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }
}