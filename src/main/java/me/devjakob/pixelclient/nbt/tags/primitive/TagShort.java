/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

public class TagShort extends TagNumber<Short> {

    public static final byte ID = 2;
    public static final short EMPTY_VALUE = 0;

    public TagShort(short value) {
        super(ID, value);
    }
}
