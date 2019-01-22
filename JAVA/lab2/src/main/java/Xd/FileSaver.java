package Xd;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

public class FileSaver
{
    public static CtClass getClassFromCtPool(String classToSave) {
        CtClass fetchedClass = null;
        try {
            fetchedClass = JarFilee.classPool.get(classToSave);
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        return fetchedClass;
    }

    public static boolean saveJar(File destinationFile, List<String> allClasses) {
        //TODO save also files different than *.class files -- take them from JarReader
        // TODO check saving a jar
        try {
            OutputStream outputStream = new FileOutputStream(destinationFile);
            JarOutputStream jarOutputStream = new JarOutputStream(outputStream, JarFilee.jarFileJAR.getManifest());
            InputStream inputStream;
            for(String classToSave: allClasses) {
                classToSave = classToSave.replace(".class", "");
                CtClass compiledClassToSave = getClassFromCtPool(classToSave);
                classToSave = classToSave.replace(".", "/");
                classToSave = classToSave + ".class";
                JarEntry jarEntry = new JarEntry(classToSave);
                jarOutputStream.putNextEntry(jarEntry);
                jarOutputStream.write(compiledClassToSave.toBytecode());
                jarOutputStream.closeEntry();
            }

            for(ZipEntry jarEntry: JarFilee.notClassElements) {
                inputStream = JarFilee.jarFileJAR.getInputStream(jarEntry);
                System.out.println("jarEntry: " + jarEntry);
                ZipEntry zipEntry = new ZipEntry(jarEntry.getName());
                //JarEntry jarEntry2 = new JarEntry(jarEntry);
                byte[] buffer = new byte[1024];
                jarOutputStream.putNextEntry(zipEntry);
                int numberOfReadBytes;
                while((numberOfReadBytes = inputStream.read(buffer, 0, buffer.length)) != -1) {
                    jarOutputStream.write(buffer, 0, numberOfReadBytes);
                }
                jarOutputStream.closeEntry();
                inputStream.close();
            }
            jarOutputStream.close();
            outputStream.close();
        } catch (IOException | CannotCompileException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
