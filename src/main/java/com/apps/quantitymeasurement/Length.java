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
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toInches() {
        return this.value * this.unit.getConversionFactor();
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private boolean compare(Length other) {
        return Double.compare(
                round(this.toInches()),
                round(other.toInches())
        ) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;
        return compare(other);
    }

    // 🔥 UC5 CORE METHOD
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double inches = this.toInches();
        double converted = inches / targetUnit.getConversionFactor();

        return new Length(round(converted), targetUnit);
    }

    // 🔥 STATIC CONVERSION API (IMPORTANT FOR TESTS)
    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (from == null || to == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double inches = value * from.getConversionFactor();
        return inches / to.getConversionFactor();
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}