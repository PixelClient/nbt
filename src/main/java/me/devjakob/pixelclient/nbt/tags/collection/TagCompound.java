/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags.collection;

import me.devjakob.pixelclient.nbt.tags.Tag;
import me.devjakob.pixelclient.nbt.tags.array.TagByteArray;
import me.devjakob.pixelclient.nbt.tags.array.TagIntArray;
import me.devjakob.pixelclient.nbt.tags.array.TagLongArray;
import me.devjakob.pixelclient.nbt.tags.primitive.*;

import java.util.*;
import java.util.function.Consumer;

public class TagCompound extends Tag<Map<String, Tag<?>>> implements Iterable<Map.Entry<String, Tag<?>>> {

    public static final byte ID = 10;

    public TagCompound() {
        this(8);
    }

    public TagCompound(int initialCapacity) {
        super(ID, new HashMap<>(initialCapacity));
    }

    // Direct Tag methods

    public void put(String key, Tag<?> tag) {
        if (tag == null) return;
        getValue().put(Objects.requireNonNull(key, "'key' can not be null"), tag);
    }

    @SuppressWarnings("unchecked")
    public <T extends Tag<?>> T get(String key) {
        return (T) getValue().get(key);
    }

    public TagByte getTagByte(String key) {
        return get(key);
    }

    public TagShort getTagShort(String key) {
        return get(key);
    }

    public TagInt getTagInt(String key) {
        return get(key);
    }

    public TagLong getTagLong(String key) {
        return get(key);
    }

    public TagFloat getTagFloat(String key) {
        return get(key);
    }

    public TagDouble getTagDouble(String key) {
        return get(key);
    }

    public TagString getTagString(String key) {
        return get(key);
    }

    public TagByteArray getTagByteArray(String key) {
        return get(key);
    }

    public TagIntArray getTagIntArray(String key) {
        return get(key);
    }

    public TagLongArray getTagLongArray(String key) {
        return get(key);
    }

    public <T extends Tag<?>> TagList<T> getTagList(String key) {
        return get(key);
    }

    public TagCompound getTagCompound(String key) {
        return get(key);
    }

    // Tag values

    public void putBoolean(String key, boolean value) {
        put(key, new TagByte(value ? (byte) 1 : (byte) 0));
    }

    public void putByte(String key, byte value) {
        put(key, new TagByte(value));
    }

    public void putShort(String key, short value) {
        put(key, new TagShort(value));
    }

    public void putInt(String key, int value) {
        put(key, new TagInt(value));
    }

    public void putLong(String key, long value) {
        put(key, new TagLong(value));
    }

    public void putFloat(String key, float value) {
        put(key, new TagFloat(value));
    }

    public void putDouble(String key, double value) {
        put(key, new TagDouble(value));
    }

    public void putString(String key, String value) {
        put(key, new TagString(value));
    }

    public void putByteArray(String key, byte[] value) {
        put(key, new TagByteArray(value));
    }

    public void putIntArray(String key, int[] value) {
        put(key, new TagIntArray(value));
    }

    public void putLongArray(String key, long[] value) {
        put(key, new TagLongArray(value));
    }

    public boolean getBoolean(String key) {
        Tag<?> tag = get(key);
        return tag instanceof TagByte && ((TagByte) tag).getValue() > 0;
    }

    public byte getByte(String key) {
        TagByte tag = get(key);
        return tag == null ? TagByte.EMPTY_VALUE : tag.getValue();
    }

    public short getShort(String key) {
        TagShort tag = get(key);
        return tag == null ? TagShort.EMPTY_VALUE : tag.getValue();
    }

    public int getInt(String key) {
        TagInt tag = get(key);
        return tag == null ? TagInt.EMPTY_VALUE : tag.getValue();
    }

    public long getLong(String key) {
        TagLong tag = get(key);
        return tag == null ? TagLong.EMPTY_VALUE : tag.getValue();
    }

    public float getFloat(String key) {
        TagFloat tag = get(key);
        return tag == null ? TagFloat.EMPTY_VALUE : tag.getValue();
    }

    public double getDouble(String key) {
        TagDouble tag = get(key);
        return tag == null ? TagDouble.EMPTY_VALUE : tag.getValue();
    }

    public String getString(String key) {
        TagString tag = get(key);
        return tag == null ? TagString.EMPTY_VALUE : tag.getValue();
    }

    public byte[] getByteArray(String key) {
        TagByteArray tag = get(key);
        return tag == null ? TagByteArray.EMPTY_VALUE : tag.getValue();
    }

    public int[] getIntArray(String key) {
        TagIntArray tag = get(key);
        return tag == null ? TagIntArray.EMPTY_VALUE : tag.getValue();
    }

    public long[] getLongArray(String key) {
        TagLongArray tag = get(key);
        return tag == null ? TagLongArray.EMPTY_VALUE : tag.getValue();
    }

    // Iterable

    @Override
    public Iterator<Map.Entry<String, Tag<?>>> iterator() {
        return getValue().entrySet().iterator();
    }

    @Override
    public void forEach(Consumer<? super Map.Entry<String, Tag<?>>> action) {
        getValue().entrySet().forEach(action);
    }

    @Override
    public Spliterator<Map.Entry<String, Tag<?>>> spliterator() {
        return Iterable.super.spliterator();
    }
}
