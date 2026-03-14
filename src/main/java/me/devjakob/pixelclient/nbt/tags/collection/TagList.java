/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.collection;

import me.devjakob.pixelclient.nbt.tags.Tag;
import me.devjakob.pixelclient.nbt.tags.TagEnd;

import java.util.*;
import java.util.function.Consumer;

public class TagList<V extends Tag<?>> extends Tag<List<V>> implements Iterable<V>/*, Comparable<TagList<V>>*/ {

    public static final byte ID = 9;

    private byte valueType;

    public TagList() {
        this(new ArrayList<>());
    }

    public TagList(List<V> value) {
        super(ID, value);
        Objects.requireNonNull(value, "'value' can not be null");
        if (value.isEmpty()) {
            this.valueType = TagEnd.ID;
        } else {
            this.valueType = value.get(0).getId();
        }
    }

    public byte getValueType() {
        return valueType;
    }

    // Collection methods

    public int size() {
        return getValue().size();
    }

    public boolean contains(V value) {
        return getValue().contains(value);
    }

    public boolean add(V value) {
        Objects.requireNonNull(value, "'value' can not be null");
        if (getValue().isEmpty())
            this.valueType = value.getId();
        if (value.getId() != this.valueType)
            return false;
        return getValue().add(value);
    }

    @SuppressWarnings("unchecked")
    public boolean addUnsafe(Tag<?> value) {
        Objects.requireNonNull(value, "'value' can not be null");
        if (getValue().isEmpty())
            this.valueType = value.getId();
        if (value.getId() != this.valueType)
            return false;
        return add((V) value);
    }

    public boolean remove(V value) {
        boolean success = getValue().remove(value);

        if (getValue().isEmpty())
            valueType = TagEnd.ID;

        return success;
    }

    public void clear() {
        this.valueType = TagEnd.ID;
        getValue().clear();
    }

    public V get(int index) {
        return getValue().get(index);
    }

    // Iterable

    @Override
    public Iterator<V> iterator() {
        return getValue().iterator();
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        getValue().forEach(action);
    }

    @Override
    public Spliterator<V> spliterator() {
        return getValue().spliterator();
    }
}
