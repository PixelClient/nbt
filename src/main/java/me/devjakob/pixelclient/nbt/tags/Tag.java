/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags;

import java.util.Objects;

public abstract class Tag<V> {

    protected final byte id;
    protected V value;

    protected Tag(byte id, V value) {
        this.id = id;
        this.value = value;
    }


    public byte getId() {
        return id;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag<?> tag = (Tag<?>) o;

        return id == tag.id && Objects.equals(value, tag.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
