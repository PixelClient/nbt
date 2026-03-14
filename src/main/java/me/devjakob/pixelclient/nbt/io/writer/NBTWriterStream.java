/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io.writer;

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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBTWriterStream extends DataOutputStream implements NBTWriter, DepthIO {

    private static final Map<Byte, TagWriteHandler<NBTWriterStream>> tagHandlers = new HashMap<>();

    static {
        registerHandler(TagEnd.ID, (out, tag, depth) -> {
        });
        registerHandler(TagByte.ID, NBTWriterStream::writeByte);
        registerHandler(TagShort.ID, NBTWriterStream::writeShort);
        registerHandler(TagInt.ID, NBTWriterStream::writeInt);
        registerHandler(TagLong.ID, NBTWriterStream::writeLong);
        registerHandler(TagFloat.ID, NBTWriterStream::writeFloat);
        registerHandler(TagDouble.ID, NBTWriterStream::writeDouble);
        registerHandler(TagString.ID, NBTWriterStream::writeString);
        registerHandler(TagByteArray.ID, NBTWriterStream::writeByteArray);
        registerHandler(TagIntArray.ID, NBTWriterStream::writeIntArray);
        registerHandler(TagLongArray.ID, NBTWriterStream::writeLongArray);
        registerHandler(TagList.ID, NBTWriterStream::writeList);
        registerHandler(TagCompound.ID, NBTWriterStream::writeCompound);
    }

    private static void registerHandler(byte id, TagWriteHandler<NBTWriterStream> handler) {
        tagHandlers.put(id, handler);
    }

    public NBTWriterStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(TagRoot tag, int depth) throws IOException {
        writeTagNamed(tag.getName(), tag.getTag(), depth);
    }

    @Override
    public void writeRaw(Tag<?> tag, int depth) throws IOException {
        writeTagNamed("", tag, depth);
    }

    public void writeTagNamed(String name, Tag<?> tag, int depth) throws IOException {
        writeByte(tag.getId());
        if (tag.getId() != 0) {
            writeUTF(name == null ? "" : name);
        }
        writeTagValue(tag, depth);
    }

    public void writeTagValue(Tag<?> tag, int depth) throws IOException {
        TagWriteHandler<NBTWriterStream> handler = tagHandlers.get(tag.getId());
        if (handler == null)
            throw new MalformedNBTException("Invalid tag with id " + tag.getId());
        handler.handleTag(this, tag, depth);
    }

    // Writers

    private static void writeByte(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeByte(((TagByte) tag).asByte());
    }

    private static void writeShort(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeShort(((TagShort) tag).asShort());
    }

    private static void writeInt(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeInt(((TagInt) tag).asInt());
    }

    private static void writeLong(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeLong(((TagLong) tag).asLong());
    }

    private static void writeFloat(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeFloat(((TagFloat) tag).asFloat());
    }

    private static void writeDouble(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeDouble(((TagDouble) tag).asDouble());
    }

    private static void writeString(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        out.writeUTF(((TagString) tag).getValue());
    }

    private static void writeByteArray(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        byte[] value = ((TagByteArray) tag).getValue();
        out.writeInt(value.length);
        out.write(value);
    }

    private static void writeIntArray(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        int[] value = ((TagIntArray) tag).getValue();
        out.writeInt(value.length);
        for (int i : value) out.writeInt(i);
    }

    private static void writeLongArray(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        long[] value = ((TagLongArray) tag).getValue();
        out.writeInt(value.length);
        for (long i : value) out.writeLong(i);
    }

    private static void writeList(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        TagList<?> list = (TagList<?>) tag;
        List<? extends Tag<?>> value = list.getValue();
        out.writeByte(list.getValueType());
        out.writeInt(value.size());
        for (Tag<?> child : value) {
            out.writeTagValue(child, out.decrementDepth(depth));
        }
    }

    private static void writeCompound(NBTWriterStream out, Tag<?> tag, int depth) throws IOException {
        TagCompound compound = (TagCompound) tag;
        for (Map.Entry<String, Tag<?>> entry : compound) {
            if (entry.getValue().getId() == TagEnd.ID)
                throw new MalformedNBTException("Illegal TAG_End inside TAG_Compound.");
            out.writeTagNamed(entry.getKey(), entry.getValue(), out.decrementDepth(depth));
        }
        out.writeByte(TagEnd.ID);
    }
}
