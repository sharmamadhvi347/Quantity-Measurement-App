package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ---------------- LENGTH METHODS ----------------

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println(result
                ? "The two length measurements are equal."
                : "The two length measurements are not equal.");
        return result;
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                      double value2, LengthUnit unit2) {
        return demonstrateLengthEquality(
                new Length(value1, unit1),
                new Length(value2, unit2)
        );
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        Length result = new Length(value, fromUnit).convertTo(toUnit);
        System.out.println(result);
        return result;
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        Length result = length.convertTo(toUnit);
        System.out.println(result);
        return result;
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        Length result = l1.add(l2);
        System.out.println(result);
        return result;
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {
        Length result = l1.add(l2, targetUnit);
        System.out.println(result);
        return result;
    }

    // ---------------- WEIGHT METHODS ----------------

    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        boolean result = weight1.equals(weight2);
        System.out.println(result
                ? "The two weight measurements are equal."
                : "The two weight measurements are not equal.");
        return result;
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1,
                                                      double value2, WeightUnit unit2) {
        return demonstrateWeightEquality(
                new Weight(value1, unit1),
                new Weight(value2, unit2)
        );
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        Weight result = new Weight(value, fromUnit).convertTo(toUnit);
        System.out.println(result);
        return result;
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit) {
        Weight result = weight.convertTo(toUnit);
        System.out.println(result);
        return result;
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        Weight result = w1.add(w2);
        System.out.println(result);
        return result;
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit targetUnit) {
        Weight result = w1.add(w2, targetUnit);
        System.out.println(result);
        return result;
    }

    // ---------------- MAIN ----------------

    public static void main(String[] args) {

        // LENGTH DEMO
        demonstrateLengthEquality(
                new Length(1, LengthUnit.FEET),
                new Length(12, LengthUnit.INCHES)
        );

        // WEIGHT DEMO
        demonstrateWeightEquality(
                new Weight(1, WeightUnit.KILOGRAM),
                new Weight(1000, WeightUnit.GRAM)
        );

        demonstrateWeightConversion(1, WeightUnit.KILOGRAM, WeightUnit.POUND);

        demonstrateWeightAddition(
                new Weight(1, WeightUnit.KILOGRAM),
                new Weight(1000, WeightUnit.GRAM)
        );
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