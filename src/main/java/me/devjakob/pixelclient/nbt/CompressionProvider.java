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

public interface CompressionProvider {

    class InvalidCompressionProvider implements CompressionProvider {

        public static final InvalidCompressionProvider INSTANCE = new InvalidCompressionProvider();

        private static final UnsupportedOperationException WARNING = new UnsupportedOperationException(
                "No GZIP compression provider has been supplied. Did you forget to call NBT.setCompressionProvider?");

        @Override
        public InputStream newGZIPInputStream(InputStream stream) {
            throw WARNING;
        }

        @Override
        public OutputStream newGZIPOutputStream(OutputStream stream) {
            throw WARNING;
        }

    }

    InputStream newGZIPInputStream(InputStream stream) throws IOException;

    OutputStream newGZIPOutputStream(OutputStream stream) throws IOException;

}
