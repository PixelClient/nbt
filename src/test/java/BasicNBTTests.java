/*
 * Copyright (c) 2026 devjakob
 * https://github.com/PixelClient/nbt
 *
 * Licensed under the MIT License. See LICENSE file for details.
 */

import me.devjakob.pixelclient.nbt.JVMCompressionProvider;
import me.devjakob.pixelclient.nbt.NBT;
import me.devjakob.pixelclient.nbt.except.DepthLimitExceededException;
import me.devjakob.pixelclient.nbt.tags.TagRoot;
import me.devjakob.pixelclient.nbt.tags.array.TagByteArray;
import me.devjakob.pixelclient.nbt.tags.collection.*;
import me.devjakob.pixelclient.nbt.tags.primitive.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BasicNBTTests {

    @BeforeAll
    static void prepareTests() throws IOException {
        NBT.INSTANCE.setAssumeCompressed(false);
    }

    @Test
    void testCompoundRoundtrip() throws Exception {
        TagCompound compound = new TagCompound();
        compound.put("byte", new TagByte((byte) 5));
        compound.put("int", new TagInt(123));
        compound.put("string", new TagString("hello"));

        TagRoot root = new TagRoot("test", compound);

        byte[] data = NBT.INSTANCE.writeNBT(root, false);
        TagRoot read = NBT.INSTANCE.readNBT(data);

        assertEquals(root, read);
    }

    @Test
    void testAllPrimitiveTags() throws Exception {
        TagCompound compound = new TagCompound();

        compound.put("byte", new TagByte((byte) 1));
        compound.put("short", new TagShort((short) 2));
        compound.put("int", new TagInt(3));
        compound.put("long", new TagLong(4L));
        compound.put("float", new TagFloat(5.5f));
        compound.put("double", new TagDouble(6.6));
        compound.put("string", new TagString("abc"));

        TagRoot root = new TagRoot("root", compound);

        byte[] data = NBT.INSTANCE.writeNBT(root);
        TagRoot read = NBT.INSTANCE.readNBT(data);

        assertEquals(root, read);
    }

    @Test
    void testListRoundtrip() throws Exception {
        TagList<TagInt> list = new TagList<>();
        list.add(new TagInt(1));
        list.add(new TagInt(2));
        list.add(new TagInt(3));

        TagRoot root = new TagRoot("", list);

        byte[] data = NBT.INSTANCE.writeNBT(root, false);
        TagRoot read = NBT.INSTANCE.readNBT(data);

        assertEquals(root, read);
    }

    @Test
    void testByteArray() throws Exception {
        byte[] arr = { 1, 2, 3, 4 };

        TagRoot root = new TagRoot("", new TagByteArray(arr));

        byte[] data = NBT.INSTANCE.writeNBT(root);
        TagRoot read = NBT.INSTANCE.readNBT(data);

        assertArrayEquals(arr, ((TagByteArray) read.getTag()).getValue());
    }

    @Test
    void testCompressedRoundtrip() throws Exception {
        NBT nbt = new NBT();
        nbt.setCompression(new JVMCompressionProvider());

        TagRoot root = new TagRoot("test", new TagInt(42));

        byte[] data = nbt.writeNBT(root);
        TagRoot read = nbt.readNBT(data);

        assertEquals(root, read);
    }

    @Test
    void testInvalidTagId() {
        byte[] invalid = { 99 }; // Invalid tag data

        assertThrows(IOException.class, () -> {
            NBT.INSTANCE.readNBT(invalid);
        });
    }

    @Test
    void testDepthLimit() {
        NBT nbt = new NBT();
        nbt.setAssumeCompressed(false);
        nbt.setNbtDepth(1);

        TagCompound inner = new TagCompound();
        TagCompound inner2 = new TagCompound();
        inner2.put("deep", new TagCompound());
        inner.put("deep", inner2);

        TagRoot root = new TagRoot("", inner);

        assertThrows(DepthLimitExceededException.class, () -> {
            nbt.writeNBT(root);
        });
    }

    @Test
    void testGZipSample() throws IOException {
        NBT nbt = new NBT();
        nbt.setAssumeCompressed(true);
        nbt.setCompression(new JVMCompressionProvider());
        commonSample(nbt.readNBT(BasicNBTTests.class.getResourceAsStream("/samplegzip.nbt")));
    }

    @Test
    void testSample() throws IOException {
        commonSample(NBT.INSTANCE.readNBT(BasicNBTTests.class.getResourceAsStream("/sample.nbt")));
    }

    void commonSample(TagRoot root) {
        assertEquals("root", root.getName());
        assertInstanceOf(TagCompound.class, root.getTag());
        TagCompound base = (TagCompound) root.getTag();
        assertEquals(45, base.getByte("byte"));
        assertEquals(345, base.getShort("short"));
        assertEquals(-981735, base.getInt("int"));
        assertEquals(-398423290489L, base.getLong("long"));
        assertEquals("https://dewy.dev", base.getString("string"));
        assertArrayEquals(new byte[] { 0, -124, 13, -6, 127 }, base.getByteArray("bytes"));
        assertArrayEquals(new int[] { 0, -1348193, 817519, -2147483648, 4 }, base.getIntArray("ints"));
        assertArrayEquals(new long[] { 12490812, 903814091904L, -3, -9223372036854775808L, 9223372036854775807L, 0 }, base.getLongArray("longs"));
        TagList<TagCompound> compounds = base.getTagList("compounds");
        assertEquals(1, compounds.size());
        TagCompound sub = base.getTagCompound("sub");
        TagList<TagCompound> listMoment = sub.getTagList("listmoment");

        for (int i = 0; i < listMoment.size(); i++) {
            TagCompound compound = listMoment.get(i);
            assertEquals(i, compound.getDouble("i"));
            assertEquals(i *  0.0007417847340701729D, compound.getDouble("n"), 1e-15);
        }
    }
}
