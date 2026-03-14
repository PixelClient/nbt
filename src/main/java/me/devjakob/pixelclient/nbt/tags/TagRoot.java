/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

package me.devjakob.pixelclient.nbt.tags;

import java.util.Objects;

public class TagRoot {

    private String name;
    private Tag<?> tag;

    public TagRoot(String name, Tag<?> tag) {
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag<?> getTag() {
        return tag;
    }

    public void setTag(Tag<?> tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagRoot)) return false;

        TagRoot other = (TagRoot) o;

        return Objects.equals(name, other.name)
                && Objects.equals(tag, other.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag);
    }
}
