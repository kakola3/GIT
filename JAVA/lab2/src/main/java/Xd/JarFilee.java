package Xd;

import javafx.stage.FileChooser;
import javassist.*;

import java.io.*;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarFilee {
    //public static File jarFile;
    public static JarFile jarFileJAR;
    public static Manifest manifest;
    public static ArrayList<String> onlyClassesArray = new ArrayList<String>();
    public static ClassPool classPool;
    public static ArrayList<ZipEntry> notClassElements;

    public static ArrayList<String> allFiles;

    public void openJar(File file) throws IOException, NotFoundException {
        this.jarFileJAR = FileChooserr.injectJarReader(file);
        //this.jarFile = file;
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

                // Enumeration<? extends ZipEntry> enumOfJar = jarFileJAR.entries();

                // BufferedWriter writer = new BufferedWriter(new FileWriter("c:/temp/samplefile1.txt"));
                // writer.write(fileContent);

            }
            writer.close();
            is.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        this.onlyClassesArray = onlyClasses(); // doing
        allFiles = getAllElements(jarFileJAR);
        System.out.println("allFiles: " + allFiles);
        notClassElements = getNotClassElements(jarFileJAR);
        System.out.println("notClassElements: " + notClassElements);
        //getNotClassElements();
        //onlyMethods("com.diamond.iain.javagame.utils.OSValidator"); // tests
    }

    public ArrayList<String> onlyClasses() throws IOException {
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
        classPool = ClassPool.getDefault();
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
            for (CtMethod method : methods) {
                CtClass[] parameters = method.getParameterTypes();

                fullMethodHeader = new StringBuilder();

                fullMethodHeader.append(makeAccessModifier(method))
                        .append(method.getReturnType().getName()).append(" ")
                        .append(method.getName()).append("(");
                for (int i = 0; i < parameters.length; i++) {
                    String separator = ", ";
                    if (i == parameters.length - 1) separator = "";
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
        if (java.lang.reflect.Modifier.isPublic(modifier)) accessModifier.append("public ");
        if (java.lang.reflect.Modifier.isPrivate(modifier)) accessModifier.append("private ");
        if (java.lang.reflect.Modifier.isProtected(modifier)) accessModifier.append("protected ");
        if (java.lang.reflect.Modifier.isFinal(modifier)) accessModifier.append("final ");
        if (java.lang.reflect.Modifier.isSynchronized(modifier)) accessModifier.append("synchronized ");
        if (java.lang.reflect.Modifier.isInterface(modifier)) accessModifier.append("interface ");
        if (java.lang.reflect.Modifier.isStatic(modifier)) accessModifier.append("static ");
        if (java.lang.reflect.Modifier.isTransient(modifier)) accessModifier.append("transient ");
        if (Modifier.isVolatile(modifier)) accessModifier.append("volatile ");
        return accessModifier.toString();
    }

    public static ArrayList<String> getConstructorsFromClass(String chosenClass) {
        ArrayList<String> constructorsFullHeaders = new ArrayList<String>();
        ArrayList<CtConstructor> constructors = new ArrayList<CtConstructor>();
        try {
            CtClass ctExampleClass = classPool.get(chosenClass);
            ctExampleClass.stopPruning(true); //TODO find out what is pruning
            CtConstructor[] classConstructors = ctExampleClass.getDeclaredConstructors();
            for (CtConstructor constructor : classConstructors) {
                constructors.add(constructor);
            }
            constructorsFullHeaders = makeConstructorsHeaders(constructors);
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        return constructorsFullHeaders;
    }

    public static ArrayList<String> makeConstructorsHeaders(ArrayList<CtConstructor> constructors) {
        StringBuilder fullConstructorHeader;
        ArrayList<String> fullConstructorsHeader = new ArrayList<String>();
        try {
            for (CtConstructor constructor : constructors) {
                CtClass[] parameters = constructor.getParameterTypes();

                fullConstructorHeader = new StringBuilder();
                fullConstructorHeader
                        .append(constructor.getName()).append("(");
                for (int i = 0; i < parameters.length; i++) {
                    String separator = ", ";
                    if (i == parameters.length - 1) separator = "";
                    fullConstructorHeader
                            .append(parameters[i].getName()).append(separator);
                }
                fullConstructorHeader.append(")");
                fullConstructorsHeader.add(fullConstructorHeader.toString());
            }
        } catch (NotFoundException e) {
            System.out.println(e);
        }

        return fullConstructorsHeader;
    }

    public static ArrayList<String> getFieldsFromClass(String chosenClass) {
        ArrayList<CtField> fields = new ArrayList<CtField>();
        ArrayList<String> fieldsFullHeaders = null;
        try {
            CtClass ctExampleClass = classPool.get(chosenClass);
            ctExampleClass.stopPruning(true); //TODO find out what is pruning
            CtField[] classFields = ctExampleClass.getDeclaredFields();
            for (CtField field : classFields) {
                fields.add(field);
            }
            fieldsFullHeaders = makeFieldsHeaders(fields);
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        return fieldsFullHeaders;
    }

    public static ArrayList<String> makeFieldsHeaders(ArrayList<CtField> fields) {
        StringBuilder fullMethodHeader;
        ArrayList<String> fullFieldsHeader = new ArrayList<String>();
        try {
            for (CtField field : fields) {
                fullMethodHeader = new StringBuilder();

                fullMethodHeader.append(makeAccessModifier(field))
                        .append(" ")
                        .append(field.getType().getName()).append(" ")
                        .append(field.getName());
                fullFieldsHeader.add(fullMethodHeader.toString());
            }
        } catch (NotFoundException e) {
            System.out.println(e);
        }

        return fullFieldsHeader;
    }

    public static String showPackage(String className) {
        String packageName = className;
        String[] output = packageName.split("\\.");
        String cutOutput = "";
        for (int i = 0; i < output.length - 1; i++) {
            System.out.println("output[i] if: " + output[i]);
            cutOutput += output[i] + ".";
        }

        cutOutput = cutOutput.substring(0, cutOutput.length() - 1);

        System.out.println("cutOutput: " + cutOutput);
        return cutOutput;
    }

    public ArrayList<String> getAllElements(JarFile jarFile) {
        ArrayList<String> allFiles = new ArrayList<>();
        Enumeration<? extends ZipEntry> enumOfJar = jarFile.entries();
        while (enumOfJar.hasMoreElements()) {
            ZipEntry element = enumOfJar.nextElement();
            String file = element.toString();
            if (file.endsWith(".class")) {
                file = file.replace("/", ".");
                allFiles.add(file);
            }
        }
        return allFiles;
    }

    public ArrayList<ZipEntry> getNotClassElements(JarFile jarFile) {
        ArrayList<ZipEntry> notClassElements = new ArrayList<>();
        Enumeration<? extends ZipEntry> enumOfJar = jarFile.entries();
        while (enumOfJar.hasMoreElements()) {
            ZipEntry element = enumOfJar.nextElement();
            String file = element.toString().trim();
            if (!file.endsWith("/") && !file.endsWith(".MF") && !file.equals(".class") && !file.contains(".class") && !file.endsWith(".class")) {
                notClassElements.add(element);
            }
        }
        return notClassElements;
    }

    public static void updateMethodsForClass(String className,String removableMethod) throws NotFoundException {
        CtClass ctExampleClass = classPool.get(className);
        ctExampleClass.stopPruning(true); //TODO find out what is pruning
        CtMethod[] classMethods = ctExampleClass.getDeclaredMethods();
        for(CtMethod ctMethod : classMethods){
            System.out.println("ctMethod: " + ctMethod.getName());
            String ctMethodForRemove = ctMethod.getName();
            System.out.println("ctMethodForRemove: " + ctMethodForRemove);
            if(removableMethod.contains(ctMethodForRemove)) {
                ctExampleClass.removeMethod(ctMethod);
                System.out.println("ctMethod2: " + ctMethod);
            }
        }
        System.out.println("Methods after removing: " + classMethods.toString());
    }
}

