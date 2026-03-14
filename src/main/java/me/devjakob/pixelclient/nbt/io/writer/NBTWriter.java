/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io.writer;

import me.devjakob.pixelclient.nbt.tags.Tag;
import me.devjakob.pixelclient.nbt.tags.TagRoot;

import java.io.Closeable;
import java.io.IOException;

public interface NBTWriter extends Closeable {

    void write(TagRoot tag, int depth) throws IOException;

    void writeRaw(Tag<?> tag, int depth) throws IOException;

}
