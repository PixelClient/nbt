/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.except;

public class DepthLimitExceededException extends RuntimeException {

    public DepthLimitExceededException(String msg) {
        super(msg);
    }

}