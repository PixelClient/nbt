/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

public class TagFloat extends TagNumber<Float> {

    public static final byte ID = 5;
    public static final float EMPTY_VALUE = 0;

    public TagFloat(float value) {
        super(ID, value);
    }
}
