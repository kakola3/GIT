package Xd;

import javassist.*;

import java.io.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarFilee {
    public static File jarFile;
    String jarPath;
    public static ArrayList<String> onlyClassesArray = new ArrayList<String>();
    public static ClassPool classPool;

    public void openJar(File file) throws IOException, NotFoundException {
        this.jarFile = file;
        try {
            ZipInputStream is = new ZipInputStream(new FileInputStream(file));
            ZipEntry ze;

            byte[] buf = new byte[4096];

            StringBuilder sb = new StringBuilder();
            String line;
            BufferedWriter writer = new BufferedWriter(new FileWriter("samplefile.txt"));
            while ((ze = is.getNextEntry()) != null) {
                line = String.valueOf(ze.getName());
                System.out.println(line);
                System.out.println("----------- " + ze);
                //outer.println(line);
                writer.write(line);
                writer.write("\n");

                // BufferedWriter writer = new BufferedWriter(new FileWriter("c:/temp/samplefile1.txt"));
                // writer.write(fileContent);

            }
            writer.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.onlyClassesArray = onlyClasses();
        //onlyMethods("com.diamond.iain.javagame.utils.OSValidator");
    }

    public ArrayList<String> onlyClasses() throws IOException{
        ArrayList<String> onlyClassesArray = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("samplefile.txt"));
        String line;
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        while ((line = br.readLine()) != null) {
            if (line.endsWith(".class")) {
                onlyClassesArray.add(line.replace(".class", "").replace("/", "."));
                System.out.println(line.replace(".class", "").replace("/", "."));
            }
        }
        return onlyClassesArray;
    }

    public static ArrayList<String> onlyMethods(String className) throws NotFoundException, IOException {
       // jarPath = jarFile.getPath();
        //JarFile jarFile = new JarFile(jarPath);
       // System.out.println("jarFIle: " + jarPath);
       // this.classPool = ClassPool.getDefault();
        classPool = ClassPool.getDefault();
       // this.classPool.appendClassPath(JavaFX.file.getAbsolutePath());
        classPool.appendClassPath(JavaFX.file.getAbsolutePath());
        ArrayList<CtMethod> methods = new ArrayList<CtMethod>();
        ArrayList<String> methodsFullHeaders = null;
        try {
            CtClass ctExampleClass = classPool.get(className);
            ctExampleClass.stopPruning(true); //TODO find out what is pruning
            CtMethod[] classMethods = ctExampleClass.getDeclaredMethods();
            for (CtMethod method : classMethods) {
                methods.add(method);
            }
            methodsFullHeaders = makeMethodsHeaders(methods);
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        System.out.println("methodsFullHeaders:" + methodsFullHeaders);
        return methodsFullHeaders;
    }

    public static ArrayList<String> makeMethodsHeaders(ArrayList<CtMethod> methods) {
        StringBuilder fullMethodHeader;
        ArrayList<String> fullMethodsHeader = new ArrayList<String>();
        try {
            for (CtMethod method: methods) {
                CtClass[] parameters = method.getParameterTypes();

                fullMethodHeader = new StringBuilder();

                fullMethodHeader.append(makeAccessModifier(method))
                        .append(method.getReturnType().getName()).append(" ")
                        .append(method.getName()).append("(");
                for(int i = 0; i < parameters.length; i++) {
                    String separator = ", ";
                    if (i == parameters.length -1) separator = "";
                    fullMethodHeader
                            .append(parameters[i].getName()).append(separator);
                }
                fullMethodHeader.append(")");
                fullMethodsHeader.add(fullMethodHeader.toString());
            }
        } catch (NotFoundException e) {
            System.out.println(e);
        }

        return fullMethodsHeader;
    }

    public static String makeAccessModifier(CtMember method) {
        StringBuilder accessModifier = new StringBuilder();
        int modifier = method.getModifiers();
        if(java.lang.reflect.Modifier.isPublic(modifier)) accessModifier.append("public ");
        if(java.lang.reflect.Modifier.isPrivate(modifier)) accessModifier.append("private ");
        if(java.lang.reflect.Modifier.isProtected(modifier)) accessModifier.append("protected ");
        if(java.lang.reflect.Modifier.isFinal(modifier)) accessModifier.append("final ");
        if(java.lang.reflect.Modifier.isSynchronized(modifier)) accessModifier.append("synchronized ");
        if(java.lang.reflect.Modifier.isInterface(modifier)) accessModifier.append("interface ");
        if(java.lang.reflect.Modifier.isStatic(modifier)) accessModifier.append("static ");
        if(java.lang.reflect.Modifier.isTransient(modifier)) accessModifier.append("transient ");
        if(Modifier.isVolatile(modifier)) accessModifier.append("volatile ");
        return accessModifier.toString();
    }
}
