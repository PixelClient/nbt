/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags;

public class TagEnd extends Tag<Void> {

    public static final byte ID = 0;
    public static final TagEnd INSTANCE = new TagEnd();

    private TagEnd() {
        super(ID, null);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TagEnd;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
