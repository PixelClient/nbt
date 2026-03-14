/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io.reader;

import me.devjakob.pixelclient.nbt.tags.Tag;

import java.io.IOException;

public interface TagReadHandler<Stream> {

    Tag<?> handleTag(Stream stream, int depth) throws IOException;

}
