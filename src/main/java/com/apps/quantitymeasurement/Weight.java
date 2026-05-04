package com.apps.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weight weight = (Weight) o;
        return Double.compare(this.convertToBaseUnit(), weight.convertToBaseUnit()) == 0;
    }

    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Weight(converted, targetUnit);
    }

    public Weight add(Weight thatWeight) {
        return addAndConvert(thatWeight, this.unit);
    }

    public Weight add(Weight weight, WeightUnit targetUnit) {
        return addAndConvert(weight, targetUnit);
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private Weight addAndConvert(Weight weight, WeightUnit targetUnit) {
        double sumBase = this.convertToBaseUnit() + weight.convertToBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new Weight(result, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    public static void main(String[] args) {
        Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));
        System.out.println(w1.add(w2));
        System.out.println(w1.convertTo(WeightUnit.POUND));
    }
}