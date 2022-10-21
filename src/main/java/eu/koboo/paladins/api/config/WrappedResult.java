package eu.koboo.paladins.api.config;

import java.util.UUID;

public record WrappedResult(String value) {

    private static final WrappedResult EMPTY = new WrappedResult(null);

    public static WrappedResult empty() {
        return EMPTY;
    }

    public static WrappedResult of(String value) {
        return new WrappedResult(value);
    }

    public String asString() {
        return value;
    }

    public String asStringOr(String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asString();
    }

    public UUID asUUID() {
        return UUID.fromString(value);
    }

    public UUID asUUIDOr(UUID defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asUUID();
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(value);
    }

    public boolean asBooleanOr(boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asBoolean();
    }

    public int asInt() {
        checkNotNull();
        return Integer.parseInt(value);
    }

    public int asIntOr(int defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asInt();
    }

    public short asShort() {
        return Short.parseShort(value);
    }

    public short asShortOr(short defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asShort();
    }

    public double asDouble() {
        return Double.parseDouble(value);
    }

    public double asDoubleOr(double defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asDouble();
    }

    public float asFloat() {
        return Float.parseFloat(value);
    }

    public float asFloatOr(float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asFloat();
    }

    private void checkNotNull() {
        if (value == null) {
            throw new NullPointerException("Value in WrappedResult is null!");
        }
    }
}
