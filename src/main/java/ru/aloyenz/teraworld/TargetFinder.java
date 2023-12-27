package ru.aloyenz.teraworld;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import net.minecraft.world.gen.IChunkGenerator;
import org.reflections.Reflections;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.io.InputStream;

public class TargetFinder {

    public static String[] findWorldGenClasses() throws IOException {
        Set<Class<? extends IChunkGenerator>> classes =
                new Reflections("").getSubTypesOf(IChunkGenerator.class);

        Set<ClassNode> classNodes = new HashSet<>();

        for (Class<? extends IChunkGenerator> clazz : classes) {

            String className = clazz.getName();
            String classAsPath = className.replace('.', '/') + ".class";
            InputStream stream = TargetFinder.class.getClassLoader().getResourceAsStream(classAsPath);

            ClassNode classNode = new ClassNode();
            assert stream != null;
            new ClassReader(stream).accept(classNode, ClassReader.SKIP_DEBUG);

        }

        return returns;
    }
}
