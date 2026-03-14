/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.primitive;

import me.devjakob.pixelclient.nbt.tags.Tag;

public class TagString extends Tag<String> {

    public static final byte ID = 8;
    public static final String EMPTY_VALUE = null; // Should this be null or an empty string?

    public TagString(String value) {
        super(ID, value);
    }
}
