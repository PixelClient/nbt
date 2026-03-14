/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

public class TagDouble extends TagNumber<Double> {

    public static final byte ID = 6;
    public static final double EMPTY_VALUE = 0;

    public TagDouble(double value) {
        super(ID, value);
    }
}
