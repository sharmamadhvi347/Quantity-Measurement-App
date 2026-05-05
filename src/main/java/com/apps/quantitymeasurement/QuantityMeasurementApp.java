package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {
        double converted = quantity.convertTo(targetUnit);
        return new Quantity<>(converted, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.add(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(120, LengthUnit.INCHES);

        System.out.println("Length equal: " + demonstrateEquality(l1, l2));

        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        System.out.println("Weight equal: " + demonstrateEquality(w1, w2));

        System.out.println("10 feet in inches: " +
                demonstrateConversion(l1, LengthUnit.INCHES).getValue());

        System.out.println("Sum length: " +
                demonstrateAddition(l1, l2).getValue());

        System.out.println("Sum weight in grams: " +
                demonstrateAddition(w1, w2, WeightUnit.GRAM).getValue());
    }
}