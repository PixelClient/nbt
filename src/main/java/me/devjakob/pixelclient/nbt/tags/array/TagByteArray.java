/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.array;

import java.util.Arrays;

public class TagByteArray extends TagArray<byte[]> {

    public static final byte ID = 7;
    public static final byte[] EMPTY_VALUE = null; // Should this be null or an empty array?

    public TagByteArray(byte[] value) {
        super(ID, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagByteArray)) return false;
        TagByteArray other = (TagByteArray) o;
        return Arrays.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
