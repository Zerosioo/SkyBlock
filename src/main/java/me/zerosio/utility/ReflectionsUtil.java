package me.zerosio.utility;

import org.bukkit.Bukkit;

import me.zerosio.Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReflectionsUtil {
	  public static Set<Class> findAllClassesUsing(String packageName) {
	        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll(".", "/"));
	    	Bukkit.getServer().broadcastMessage("1" + stream);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	    	Bukkit.getServer().broadcastMessage("1");
	        return reader.lines()
	          .filter(line -> line.endsWith(".class"))
	          .map(line -> getClass(line, packageName))
	          .collect(Collectors.toSet());
	    }
	 
	    private static Class getClass(String className, String packageName) {
	        try {
	            return Class.forName(packageName + "."
	              + className.substring(0, className.lastIndexOf('.')));
	        } catch (ClassNotFoundException e) {
	        }
	        return null;
	    }

	public static List<String> getClasses(String packName){
	  	return getClasses(packName, false);
	}
	    public static List<String> getClasses(String packName, boolean includeInner){
	    	List<String> classes = new ArrayList();
	    	packName = packName.replace(".", "/");
	    	CodeSource src = Core.class.getProtectionDomain().getCodeSource();
	    	if (src != null) {
	    	  URL jar = src.getLocation();
	    	  ZipInputStream zip = null;
			try {
				zip = new ZipInputStream(jar.openStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	  while(true) {
	    	    ZipEntry e = null;
				try {
					e = zip.getNextEntry();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	    	    if (e == null)
	    	      break;
	    	    String name = e.getName();
	    	    if (name.startsWith(packName) && (!name.contains("$") || includeInner) && !name.endsWith("/")) {
	    	    	classes.add((name.replace("/", ".").substring(0, name.length() - 6)));
	    	    }
	    	  }
	    	}
	    	return classes;
	    }
	    public static Class getClassFromList(String clazz, Collection<Class> classes) {
	    	if(clazz == null) return null;
	    	
			for(Class c : classes) {
				if(c.getSimpleName().equals(clazz)) {
					return c;
				}
			}
			return null;
	    }
	    public static <T> T getInstance(Class clazz) {
	    	if(clazz == null) return null;
	    	
			try {
				return (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
	
			}
			return null;
	    	
	    }

	    public static Object getPrivateField(String fieldName, Class oclass, Object object){
	    	Field field = null;
	    	try {
	    		field = oclass.getDeclaredField(fieldName);
	    		field.setAccessible(true);
	    		return field.get(object);
	    	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	    		e.printStackTrace();
	    	}
	    	return field;
	    }


	public static void set(String field, Object o1, Object o2){
		try {
			Field f = o1.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(o1,o2);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
