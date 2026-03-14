/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.array;

import java.util.Arrays;

public class TagIntArray extends TagArray<int[]> {

    public static final byte ID = 11;
    public static final int[] EMPTY_VALUE = null; // Should this be null or an empty array?

    public TagIntArray(int[] value) {
        super(ID, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagIntArray)) return false;
        TagIntArray other = (TagIntArray) o;
        return Arrays.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
