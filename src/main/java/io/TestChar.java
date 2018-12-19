package io;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 字符流相关
 */
public class TestChar {

    private static List<Student> students = Arrays.asList(new Student[]{new Student("张三", 10, 12.5d)
            , new Student("李四", 12, 99d),
            new Student("王五", 25, 100d)
    });

    public static void main(String[] args) throws IOException {
       // testOutputStreamWriter();
//        testInputStreamReader();
//        compareInputStreamReader();
//        System.out.println(Charset.defaultCharset());

//        testFileReaderAndWriter();
//        testBufferedWriter();
//        testBufferedReader();
//        testScanner();
//        testSystemin();
        testRedirection();
    }

    public static void compare() throws IOException {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("test.dat"));
        outputStream.writeInt(123);
        outputStream.close();

        OutputStream outputStream1 = new FileOutputStream("test.txt");
        outputStream1.write(Integer.toString(123).getBytes("UTF-8"));
        outputStream1.close();
    }

    public static void testOutputStreamWriter() throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("hello.txt"),"GB2312");
        writer.write("hello, 123, 老马");
        writer.close();
    }

    public static void testInputStreamReader() throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream("hello.txt"),"GB2312");
        char[] chars = new char[1024];
        int read = reader.read(chars);
        System.out.println(new String(chars,0,read));
        reader.close();
    }

    public static void compareInputStreamReader() throws IOException {
        InputStream inputStream = new FileInputStream("hello.txt");
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        System.out.println(new String(bytes,0,read,"GB2312"));
        inputStream.close();
    }

    public static void testFileReaderAndWriter() throws IOException {
//        FileWriter fileWriter = new FileWriter("hello.txt");
//        fileWriter.write("hello, 123, 老马");
//        fileWriter.close();

        FileReader fileReader = new FileReader("hello.txt");
        char[] chars = new char[1024];
        int read = fileReader.read(chars);
        System.out.println(new String(chars,0,read));
        fileReader.close();
    }

    public static void testBufferedWriter() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("stu.txt"));
        for (Student student : students) {
            writer.write(student.getName()+","+student.getAge()+","+student.getScore());
            writer.newLine();
        }
        writer.close();
    }

    public static void testBufferedReader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("stu.txt"));
        String line = null;
        ArrayList<Student> studentArrayList = new ArrayList<>();
        while((line = reader.readLine())!= null){
            String[] fields = line.split(",");
            Student s = new Student();
            s.setName(fields[0]);
            s.setScore(Double.parseDouble(fields[2]));
            s.setAge(Integer.parseInt(fields[1]));
            studentArrayList.add(s);
        }
        System.out.println(studentArrayList.toString());
    }

    public static void testScanner() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("stu.txt"));
        String line = null;
        ArrayList<Student> arrayList = new ArrayList<>();
        Scanner scanner1 = null;
        while((line = bufferedReader.readLine())!= null){
            scanner1 = new Scanner(line).useDelimiter(",");
            Student s = new Student();
            s.setName(scanner1.next());
            s.setAge(scanner1.nextInt());
            s.setScore(scanner1.nextDouble());
            arrayList.add(s);
            scanner1.close();
        }
        bufferedReader.close();
        System.out.println(arrayList.toString());
    }

    public static void testSystemin(){
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        System.out.println(i);
    }

    /**
     * 测试重定向
     * @throws FileNotFoundException
     */
    public static void testRedirection() throws FileNotFoundException {
        System.setIn(new FileInputStream("stu.txt"));
        System.setOut(new PrintStream("out.txt"));
        System.setErr(new PrintStream("err.txt"));

        try {
            Scanner in = new Scanner(System.in);
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
