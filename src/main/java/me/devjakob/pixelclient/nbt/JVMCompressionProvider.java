/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class JVMCompressionProvider implements CompressionProvider {

    public static JVMCompressionProvider INSTANCE = new JVMCompressionProvider();

    @Override
    public InputStream newGZIPInputStream(InputStream stream) throws IOException {
        return new GZIPInputStream(stream);
    }

    @Override
    public OutputStream newGZIPOutputStream(OutputStream stream) throws IOException {
        return new GZIPOutputStream(stream);
    }
}
