/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt;

import me.devjakob.pixelclient.nbt.io.reader.NBTReader;
import me.devjakob.pixelclient.nbt.io.reader.NBTReaderStream;
import me.devjakob.pixelclient.nbt.io.writer.NBTWriter;
import me.devjakob.pixelclient.nbt.io.writer.NBTWriterStream;
import me.devjakob.pixelclient.nbt.tags.TagRoot;

import java.io.*;

public class NBT {

    public static final NBT INSTANCE = new NBT();

    private boolean assumeCompressed = true;
    private int nbtDepth = 512;
    private CompressionProvider compression = CompressionProvider.InvalidCompressionProvider.INSTANCE;

    public void setAssumeCompressed(boolean assumeCompressed) {
        this.assumeCompressed = assumeCompressed;
    }

    public void setNbtDepth(int nbtDepth) {
        this.nbtDepth = nbtDepth;
    }

    public void setCompression(CompressionProvider compression) {
        this.compression = compression;
    }

    // Readers

    public TagRoot readNBT(byte[] bytes) throws IOException {
        return readNBT(bytes, assumeCompressed);
    }

    public TagRoot readNBT(InputStream stream) throws IOException {
        return readNBT(stream, assumeCompressed);
    }

    public TagRoot readNBT(byte[] bytes, boolean compressed) throws IOException {
        return readNBT(new ByteArrayInputStream(bytes), assumeCompressed);
    }

    public TagRoot readNBT(InputStream stream, boolean compressed) throws IOException {
        try (InputStream input = compressed
                ? compression.newGZIPInputStream(stream)
                : stream;
             NBTReader reader = new NBTReaderStream(input)) {
            return reader.read(nbtDepth);
        }
    }

    // Writers

    public byte[] writeNBT(TagRoot tag) throws IOException {
        return writeNBT(tag, assumeCompressed);
    }

    public void writeNBT(TagRoot tag, OutputStream stream) throws IOException {
        writeNBT(tag, stream, assumeCompressed);
    }

    public byte[] writeNBT(TagRoot tag, boolean compressed) throws IOException {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            writeNBT(tag, bytes, compressed);
            return bytes.toByteArray();
        }
    }

    public void writeNBT(TagRoot tag, OutputStream stream, boolean compressed) throws IOException {
        try (OutputStream output = compressed
                ? compression.newGZIPOutputStream(stream)
                : stream;
             NBTWriter writer = new NBTWriterStream(output)) {
            writer.write(tag, nbtDepth);
        }
    }
}
