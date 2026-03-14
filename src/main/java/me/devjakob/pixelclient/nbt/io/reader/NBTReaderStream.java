/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io.reader;

import me.devjakob.pixelclient.nbt.except.MalformedNBTException;
import me.devjakob.pixelclient.nbt.io.DepthIO;
import me.devjakob.pixelclient.nbt.tags.Tag;
import me.devjakob.pixelclient.nbt.tags.TagEnd;
import me.devjakob.pixelclient.nbt.tags.TagRoot;
import me.devjakob.pixelclient.nbt.tags.array.TagByteArray;
import me.devjakob.pixelclient.nbt.tags.array.TagIntArray;
import me.devjakob.pixelclient.nbt.tags.array.TagLongArray;
import me.devjakob.pixelclient.nbt.tags.collection.TagCompound;
import me.devjakob.pixelclient.nbt.tags.collection.TagList;
import me.devjakob.pixelclient.nbt.tags.primitive.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NBTReaderStream extends DataInputStream implements NBTReader, DepthIO {

    private static final Map<Byte, TagReadHandler<NBTReaderStream>> tagHandlers = new HashMap<>();

    static {
        registerHandler(TagEnd.ID, (in, depth) -> TagEnd.INSTANCE);
        registerHandler(TagByte.ID, NBTReaderStream::readByte);
        registerHandler(TagShort.ID, NBTReaderStream::readShort);
        registerHandler(TagInt.ID, NBTReaderStream::readInt);
        registerHandler(TagLong.ID, NBTReaderStream::readLong);
        registerHandler(TagFloat.ID, NBTReaderStream::readFloat);
        registerHandler(TagDouble.ID, NBTReaderStream::readDouble);
        registerHandler(TagString.ID, NBTReaderStream::readString);
        registerHandler(TagByteArray.ID, NBTReaderStream::readByteArray);
        registerHandler(TagIntArray.ID, NBTReaderStream::readIntArray);
        registerHandler(TagLongArray.ID, NBTReaderStream::readLongArray);
        registerHandler(TagList.ID, NBTReaderStream::readList);
        registerHandler(TagCompound.ID, NBTReaderStream::readCompound);
    }

    private static void registerHandler(byte id, TagReadHandler<NBTReaderStream> handler) {
        tagHandlers.put(id, handler);
    }

    public NBTReaderStream(InputStream in) {
        super(in);
    }

    @Override
    public TagRoot read(int depth) throws IOException {
        byte id = readByte();
        return new TagRoot(readUTF(), readTagValue(id, depth));
    }

    @Override
    public Tag<?> readRaw(int depth) throws IOException {
        return readTagValue(readByte(), depth);
    }

    public Tag<?> readTagValue(byte type, int depth) throws IOException {
        TagReadHandler<NBTReaderStream> handler = tagHandlers.get(type);
        if (handler == null)
            throw new MalformedNBTException("Invalid tag with id " + type);
        return handler.handleTag(this, depth);
    }

    private static TagByte readByte(NBTReaderStream in, int depth) throws IOException {
        return new TagByte(in.readByte());
    }

    private static TagShort readShort(NBTReaderStream in, int depth) throws IOException {
        return new TagShort(in.readShort());
    }

    private static TagInt readInt(NBTReaderStream in, int depth) throws IOException {
        return new TagInt(in.readInt());
    }

    private static TagLong readLong(NBTReaderStream in, int depth) throws IOException {
        return new TagLong(in.readLong());
    }

    private static TagFloat readFloat(NBTReaderStream in, int depth) throws IOException {
        return new TagFloat(in.readFloat());
    }

    private static TagDouble readDouble(NBTReaderStream in, int depth) throws IOException {
        return new TagDouble(in.readDouble());
    }

    private static TagString readString(NBTReaderStream in, int depth) throws IOException {
        return new TagString(in.readUTF());
    }

    private static TagByteArray readByteArray(NBTReaderStream in, int depth) throws IOException {
        TagByteArray array = new TagByteArray(new byte[in.readInt()]);
        in.readFully(array.getValue());
        return array;
    }

    private static TagIntArray readIntArray(NBTReaderStream in, int depth) throws IOException {
        int[] data = new int[in.readInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.readInt();
        }
        return new TagIntArray(data);
    }

    private static TagLongArray readLongArray(NBTReaderStream in, int depth) throws IOException {
        long[] data = new long[in.readInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.readLong();
        }
        return new TagLongArray(data);
    }

    private static TagList<?> readList(NBTReaderStream in, int depth) throws IOException {
        byte valueTypeId = in.readByte();
        TagList<?> list = new TagList<>();
        int length = Math.max(0, in.readInt());
        for (int i = 0; i < length; i++) {
            list.addUnsafe(in.readTagValue(valueTypeId, in.decrementDepth(depth)));
        }
        return list;
    }

    private static TagCompound readCompound(NBTReaderStream in, int depth) throws IOException {
        TagCompound compound = new TagCompound();
        byte id; // This loop looks kind of messy, but I have no idea how else I should write it...
        while ((id = in.readByte()) != TagEnd.ID) {
            String key = in.readUTF();
            Tag<?> value = in.readTagValue(id, in.decrementDepth(depth));
            compound.put(key, value);
        }
        return compound;
    }
}
