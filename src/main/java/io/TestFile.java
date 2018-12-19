package io;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * File相关方法
 */
public class TestFile {
    public static void main(String[] args) throws IOException {
        File f = new File("stu.txt");
        System.out.println(f.getName());
        System.out.println(f.isAbsolute());
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());

        System.out.println();

        System.out.println(f.getParent());
        System.out.println(f.getParentFile());
        System.out.println(f.getAbsoluteFile().getParent());
        System.out.println(f.getCanonicalFile().getParent());
        System.out.println(f.getCanonicalFile().getParentFile());

        System.out.println();

        System.out.println(File.separator + File.pathSeparator);

        System.out.println();

        f = new File("test.txtx");
        System.out.println(f.createNewFile());

        System.out.println();

        File.createTempFile("upload_",".jpg",new File("."));

        System.out.println();

        f.delete();

        listTxt();

        System.out.println();

        File f1 = new File("C:\\Users\\wgg9696\\snailcar-netty\\laoma");
        System.out.println(sumSize(f1));

        System.out.println();

        System.out.println(findFile(f1,"test.txt"));

        System.out.println();

        deleteDirectory(new File("C:\\Users\\wgg9696\\snailcar-netty\\laoma\\tmp"));



    }

    //列出当前目录下的所有后缀为.txt的文件
    public static void listTxt() throws IOException {
        File f = new File(".");

        File[] files = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".txt"))
                    return true;
                return false;
            }
        });

        for (File file : files) {
            System.out.println(file.getCanonicalPath());
        }
    }

   //计算一个目录下的所有文件的大小（包括子目录）
    public static long sumSize(File f){
        long size = 0;

        if(f.isFile()){
            return f.length();
        }

        for (File file : f.listFiles()) {
            if(file.isFile()){
                size += file.length();
            }else{
                size += sumSize(file);
            }
        }
        return size;
    }

    //在一个目录下，查找所有给定文件名的文件
    public static Collection<File> findFile(File directory, String fileName){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            if(file.isFile() && file.getName().equals(fileName)){
                arrayList.add(file);
            }else if(file.isDirectory()){
                arrayList.addAll(findFile(file,fileName));
            }
        }
        return arrayList;
    }

    //删除非空目录
    public static void deleteDirectory(File file) throws IOException {
        if(file.isFile()){
            if(!file.delete()){
                throw new IOException("Fail to delete file:" + file.getCanonicalPath());
            }
        }else{
            for (File file1 : file.listFiles()) {
                deleteDirectory(file1);
            }

            if(!file.delete()){
                throw new IOException("Fail to delete file:" + file.getCanonicalPath());
            }
        }
    }
}
