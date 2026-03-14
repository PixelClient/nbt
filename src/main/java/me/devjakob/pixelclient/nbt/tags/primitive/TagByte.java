/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

public class TagByte extends TagNumber<Byte> {

    public static final byte ID = 1;
    public static final byte EMPTY_VALUE = 0;

    public TagByte(byte value) {
        super(ID, value);
    }
}
