package ru.aloyenz.teraworld;

import net.minecraft.world.gen.IChunkGenerator;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TargetFinder {

    public static String[] findWorldGenClasses() {
        Set<Class<? extends IChunkGenerator>> classes =
                new Reflections("").getSubTypesOf(IChunkGenerator.class);

        List<String> clazzes = new ArrayList<>();
        for (Class<? extends IChunkGenerator> clazz: classes) {
            clazzes.add(clazz.toGenericString());
        }

        String[] returns = clazzes.toArray(new String[clazzes.size()]);
        for (String test: returns) {
            System.out.println(test);
        }

        return returns;
    }
}
