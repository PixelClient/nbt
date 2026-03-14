/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io.writer;

import me.devjakob.pixelclient.nbt.tags.Tag;

import java.io.IOException;

public interface TagWriteHandler<Stream> {

    void handleTag(Stream stream, Tag<?> tag, int depth) throws IOException;

}
