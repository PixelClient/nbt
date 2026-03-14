/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io.reader;

import me.devjakob.pixelclient.nbt.tags.Tag;
import me.devjakob.pixelclient.nbt.tags.TagRoot;

import java.io.Closeable;
import java.io.IOException;

public interface NBTReader extends Closeable {

    TagRoot read(int depth) throws IOException;

    Tag<?> readRaw(int depth) throws IOException;

}
