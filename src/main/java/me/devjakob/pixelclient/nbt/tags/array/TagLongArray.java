/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.array;

import java.util.Arrays;

public class TagLongArray extends TagArray<long[]> {

    public static final byte ID = 12;
    public static final long[] EMPTY_VALUE = null; // Should this be null or an empty array?

    public TagLongArray(long[] value) {
        super(ID, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagLongArray)) return false;
        TagLongArray other = (TagLongArray) o;
        return Arrays.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
