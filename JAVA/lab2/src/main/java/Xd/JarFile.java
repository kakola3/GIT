package Xd;

import java.io.*;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarFile
{
//        PrintWriter outer;
//        {
//            try {
//                outer = new PrintWriter("finalFile.txt");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }

    public void openJar(File file){
        try {
            ZipInputStream is = new ZipInputStream(new FileInputStream(file));
            ZipEntry ze;

            byte[] buf = new byte[4096];

            StringBuilder sb = new StringBuilder();
            String line;
            BufferedWriter writer = new BufferedWriter(new FileWriter("samplefile.txt"));
            while ((ze = is.getNextEntry()) != null) {
                    line = String.valueOf(ze);
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
    }
    public void deleteOldFile(){
        File fileToDelete = new File("C:\\Users\\kacpe\\Desktop\\studia\\semestr 5\\Języki Formalne i Kompilatory\\lab2\\finalFile.txt");
        System.out.println("Path: " + fileToDelete.getPath());
        fileToDelete.delete();
        System.out.println("File named 'finalFile.txt' has been deleted!");
    }
}
