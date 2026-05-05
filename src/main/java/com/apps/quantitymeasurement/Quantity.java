package com.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ==================== ENUM (CORE UC13) ====================

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (Math.abs(b) < 1e-9)
                throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        public double compute(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    // ==================== VALIDATION (DRY) ====================

    private void validateArithmeticOperands(
            Quantity<U> other, U targetUnit, boolean targetRequired) {

        if (other == null || other.unit == null)
            throw new IllegalArgumentException("Invalid quantity");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit types");

        if (Double.isNaN(value) || Double.isNaN(other.value) ||
                Double.isInfinite(value) || Double.isInfinite(other.value))
            throw new IllegalArgumentException("Invalid numeric values");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // ==================== CORE HELPER ====================

    private double performBaseArithmetic(
            Quantity<U> other, ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    private static double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ==================== EQUALITY ====================

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double a = unit.convertToBaseUnit(value);
        double b = other.unit.convertToBaseUnit(other.value);

        return Math.abs(a - b) < 0.01;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // ==================== CONVERSION ====================

    public <T extends IMeasurable> double convertTo(T targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Incompatible unit types");

        double base = unit.convertToBaseUnit(value);
        return round(targetUnit.convertFromBaseUnit(base));
    }

    // ==================== ADD ====================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double result = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double finalValue = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    // ==================== SUBTRACT ====================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double result = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double finalValue = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    // ==================== DIVIDE ====================

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }
}