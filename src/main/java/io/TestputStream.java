package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二进制字节流相关
 */
public class TestputStream {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("hello.txt");
            String data = "你好啊";
            byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
            outputStream.write(bytes);
            System.out.println("finish!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            InputStream inputStream = new FileInputStream("hello.txt");

            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            String data = new String(bytes,0,read,"UTF-8");
            System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        try {
            InputStream inputStream = new FileInputStream("hello.txt");

            byte[] bytes = new byte[1024];
            int bytesRead = 0;
            int off = 0;
            while((bytesRead = inputStream.read(bytes,off,1024-off)) != -1){
                off += bytesRead;
            }

            String data = new String(bytes,0,off,"UTF-8");
            System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            InputStream inputStream = new FileInputStream("hello.txt");

            byte[] bytes = new byte[1024];
            int bytesRead = 0;
            OutputStream outputStream1 = new ByteArrayOutputStream();

            while((bytesRead = inputStream.read(bytes))!= -1){
                outputStream1.write(bytes,0,bytesRead);
            }

            String data = ((ByteArrayOutputStream) outputStream1).toString("UTF-8");
            System.out.println(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Student> students = Arrays.asList(new Student[]{new Student("张三", 10, 12.5d)
                , new Student("李四", 12, 99d),
                new Student("王五", 25, 100d)
        });

        writeStudents(students);

        System.out.println(readStudents());

    }

    public static String readStudents() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("student.txt"));

        int size = dataInputStream.readInt();
        ArrayList<Student> students = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Student s = new Student();
            s.setName(dataInputStream.readUTF());
            s.setAge(dataInputStream.readInt());
            s.setScore(dataInputStream.readDouble());
            students.add(s);
        }

        System.out.println("finished!");
        return students.toString();
    }

    public static void writeStudents(List<Student> students) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(new FileOutputStream("student.txt"));
            ((DataOutputStream) outputStream).writeInt(students.size());

            for (Student student : students) {
                ((DataOutputStream) outputStream).writeUTF(student.getName());
                ((DataOutputStream) outputStream).writeInt(student.getAge());
                ((DataOutputStream) outputStream).writeDouble(student.getScore());
            }
            System.out.println("finish!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            outputStream.close();
        }
    }

}
