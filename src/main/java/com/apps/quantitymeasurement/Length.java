package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid length");
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private boolean compare(Length that) {
        return Math.abs(this.toBase() - that.toBase()) < 0.01;
        return Math.abs(this.convertToBaseUnit() - that.convertToBaseUnit()) < 0.01;
        double thisInches = this.convertToBaseUnit();
        double thatInches = that.convertToBaseUnit();
        return Math.abs(thisInches - thatInches) < 0.01;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        return compare((Length) o);
        Length that = (Length) o;
        return compare(that);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Length(converted, targetUnit);
    }

    public Length add(Length that) {
        return add(that, this.unit);
    }

    public Length add(Length that, LengthUnit targetUnit) {
        if (that == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumBase = this.toBase() + that.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;

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