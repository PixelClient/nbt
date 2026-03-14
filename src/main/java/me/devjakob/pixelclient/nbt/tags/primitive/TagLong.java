/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

public class TagLong extends TagNumber<Long> {

    public static final byte ID = 4;
    public static final long EMPTY_VALUE = 0;

    public TagLong(long value) {
        super(ID, value);
    }
}
