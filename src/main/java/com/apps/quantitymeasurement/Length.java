package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid length");
        }
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    private boolean compare(Length that) {
        return Math.abs(this.convertToBaseUnit() - that.convertToBaseUnit()) < 0.01;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        return compare((Length) o);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double inches = convertToBaseUnit();
        double converted = inches / targetUnit.getConversionFactor();
        return new Length(round(converted), targetUnit);
    }

    public Length add(Length that) {
        return add(that, this.unit);
    }

    public Length add(Length that, LengthUnit targetUnit) {
        if (that == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumInches = this.convertToBaseUnit() + that.convertToBaseUnit();
        double result = sumInches / targetUnit.getConversionFactor();

        return new Length(round(result), targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}