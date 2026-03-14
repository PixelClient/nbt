/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.io;

import me.devjakob.pixelclient.nbt.except.DepthLimitExceededException;

public interface DepthIO {

    default int decrementDepth(int depth) {
        if (depth < 0)
            throw new IllegalArgumentException("Negative depth is not allowed");
        if (depth == 0)
            throw new DepthLimitExceededException("Maximum depth has been reached");
        return --depth;
    }

}
