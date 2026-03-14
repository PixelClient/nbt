# PixelClient NBT

A lightweight Java library for reading and writing Minecraft NBT (Named Binary Tag) data.  
Supports all 12 standard NBT tag types and optional GZIP compression.

---

## Features

- Implements all Minecraft NBT types:
  - `TAG_End`, `TAG_Byte`, `TAG_Short`, `TAG_Int`, `TAG_Long`, `TAG_Float`, `TAG_Double`, `TAG_Byte_Array`, `TAG_String`, `TAG_List`, `TAG_Compound`, `TAG_Int_Array`, `TAG_Long_Array`
- Supports compressed and uncompressed NBT streams
  - Support for custom compression algorithms to ensure TeaVM compatibility
- Safe handling of nested tags to avoid stack overflows when reading
- Fully compatible with Java 8+

---

## Usage

### Setup

This library doesn't provide a compression implementation by default. You need to tell it how to compress the data.
This decission was made to keep compatibility with TeaVM. If you are not using TeaVM you can use the JVM bassed compression.

```java
NBT.INSTANCE.setCompression(new JVMCompressionProvider());
// Or this if you don't need compression.
NBT.INSTANCE.setAssumeCompressed(false);
```

You can also create your own instance of the NBT class incase you don't want to use the global instance.

```java
NBT nbt = new NBT();
nbt.setCompression(new JVMCompressionProvider());
nbt.read(...);
```

### Reading NBT

```java
TagRoot root = NBT.INSTANCE.readNBT(new FileInputStream("pixel.nbt"));
Tag<?> tag = root.getTag();
````

### Writing NBT

```java
TagCompound compound = new TagCompound();
compound.putString("PixelClient is awesome!", "Try it at https://pixelclient.xyz");
compound.put("a list", new TagList<>());
try (FileOutputStream out = new FileOutputStream("pixel.nbt")) {
    NBT.INSTANCE.writeNBT(new TagRoot("", compound), out);
}
```

---

## Attribution

While the code was written by me, I have referenced these sources during writing:

- https://github.com/Querz/NBT
  - Idea for the IO system
  - Binary reader and writer reference
- https://github.com/BitBuf/nbt
  - Package structure for tags
  - Collection API improvements (Better usage of generic types)
  - Sample NBT files for tests

---

## License

MIT License (c) 2026 devjakob
https://github.com/PixelClient/nbt