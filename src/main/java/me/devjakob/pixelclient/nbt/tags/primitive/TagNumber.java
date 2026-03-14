/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

import me.devjakob.pixelclient.nbt.tags.Tag;

public abstract class TagNumber<V extends Number & Comparable<V>> extends Tag<V> {

    protected TagNumber(byte id, V value) {
        super(id, value);
    }

    public byte asByte() {
        return getValue().byteValue();
    }

    public short asShort() {
        return getValue().shortValue();
    }

    public int asInt() {
        return getValue().intValue();
    }

    public long asLong() {
        return getValue().longValue();
    }

    public float asFloat() {
        return getValue().floatValue();
    }

    public double asDouble() {
        return getValue().doubleValue();
    }
}
