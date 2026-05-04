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
        double thisInches = this.convertToBaseUnit();
        double thatInches = that.convertToBaseUnit();
        return Math.abs(thisInches - thatInches) < 0.01;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length that = (Length) o;
        return compare(that);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double inches = convertToBaseUnit();
        double converted = inches / targetUnit.getConversionFactor();
        return new Length(round(converted), targetUnit);
    }

    // 🔥 UC6 ADD METHOD
    public Length add(Length that) {
        if (that == null) {
            throw new IllegalArgumentException("Cannot add null");
        }

        double thisInches = this.convertToBaseUnit();
        double thatInches = that.convertToBaseUnit();

        double sumInches = thisInches + thatInches;

        double resultValue = sumInches / this.unit.getConversionFactor();

        return new Length(round(resultValue), this.unit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}