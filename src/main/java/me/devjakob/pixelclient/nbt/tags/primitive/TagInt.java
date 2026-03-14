/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

public class TagInt extends TagNumber<Integer> {

    public static final byte ID = 3;
    public static final int EMPTY_VALUE = 0;

    public TagInt(int value) {
        super(ID, value);
    }
}
