package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {

    private double value;
    private U unit;

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

    public double convertTo(U targetUnit) {
        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        double baseValue = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    public Quantity<U> add(Quantity<U> other) {
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = unit.convertFromBaseUnit(sumBase);
        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }
}