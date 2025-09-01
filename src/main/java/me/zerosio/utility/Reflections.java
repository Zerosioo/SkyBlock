package me.zerosio.utility;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import me.zerosio.commands.builder.CommandBase;

public class Reflections {

    /**
     * Finds all classes in the specified package that extend CommandBase.
     *
     * @param packageName the package to scan.
     * @return a list of CommandBase classes.
     */
    public static List<Class<? extends CommandBase>> getSubTypesOf(String packageName) {
        List<Class<? extends CommandBase>> commandClasses = new ArrayList<>();
        JarFile jar = null;

        try {
            // Locate the jar file by checking all loaded URLs
            URL jarUrl = null;
            URLClassLoader classLoader = (URLClassLoader) Reflections.class.getClassLoader();
            for (URL url : classLoader.getURLs()) {
                if (url.getPath().endsWith(".jar")) {
                    jarUrl = url;
                    break;
                }
            }

            if (jarUrl == null || jarUrl.getPath() == null) {
                throw new IOException("Failed to locate jar file for scanning classes.");
            }

            File jarFile = new File(jarUrl.getPath());
            if (!jarFile.isFile()) {
                throw new IOException("Jar file not found: " + jarFile.getPath());
            }

            jar = new JarFile(jarFile);
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();

                if (name.endsWith(".class") && name.startsWith(packageName.replace('.', '/'))) {
                    String className = name.replace('/', '.').replace(".class", "");

                    try {
                        // Load the class
                        Class<?> clazz = Class.forName(className);

                        // Check if the class extends CommandBase
                        if (CommandBase.class.isAssignableFrom(clazz)
                                && !clazz.isInterface()
                                && !Modifier.isAbstract(clazz.getModifiers())) {

                            @SuppressWarnings("unchecked")
                            Class<? extends CommandBase> commandClass = (Class<? extends CommandBase>) clazz;
                            commandClasses.add(commandClass);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jar != null) {
                try {
                    jar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return commandClasses;
    }
}
