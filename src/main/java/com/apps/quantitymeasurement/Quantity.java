package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // -------------------- COMMON HELPERS --------------------

    private void validate(Quantity<U> other) {
        if (other == null || other.unit == null) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        if (Double.isNaN(value) || Double.isNaN(other.value) ||
                Double.isInfinite(value) || Double.isInfinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric values");
        }
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private static double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // -------------------- EQUALITY --------------------

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double a = this.unit.convertToBaseUnit(this.value);
        double b = other.unit.convertToBaseUnit(other.value);

        return Math.abs(a - b) < 0.01;
    }

    // -------------------- STRING --------------------

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // -------------------- CONVERSION --------------------

    public <T extends IMeasurable> double convertTo(T targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        double base = unit.convertToBaseUnit(value);
        return round(targetUnit.convertFromBaseUnit(base));
    }

    // -------------------- ADDITION --------------------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other);

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double result = this.toBase() + other.toBase();
        double finalValue = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    // ==================== UC12 START ====================

    // -------------------- SUBTRACTION --------------------

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other);

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double result = this.toBase() - other.toBase();
        double finalValue = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    // -------------------- DIVISION --------------------

    public double divide(Quantity<U> other) {
        validate(other);

        double divisor = other.toBase();

        if (Math.abs(divisor) < 1e-9) {
            throw new ArithmeticException("Division by zero");
        }

        return this.toBase() / divisor;
    }

    // ==================== UC12 END ====================
}