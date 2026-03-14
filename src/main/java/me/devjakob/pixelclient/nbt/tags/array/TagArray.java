/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.array;

import me.devjakob.pixelclient.nbt.tags.Tag;

public abstract class TagArray<V> extends Tag<V> {

    protected TagArray(byte id, V value) {
        super(id, value);
        if (!value.getClass().isArray())
            throw new IllegalArgumentException("Value of TagArray has to be an array.");
    }
}
