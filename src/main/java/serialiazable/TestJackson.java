package serialiazable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.Student;

import java.io.File;
import java.io.IOException;

public class TestJackson {
    public static void main(String[] args) throws IOException {
        Student student = new Student("wangwu",12,25d);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String s = mapper.writeValueAsString(student);
        System.out.println(s);

        mapper.writeValue(new File("student.json"),student);

        Student student1 = mapper.readValue(new File("student.json"), Student.class);
        System.out.println(student1.toString());

        mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String s1 = mapper.writeValueAsString(student);
        System.out.println(s1);

        Student student2 = mapper.readValue(s1, Student.class);
        System.out.println(student2.toString());
    }
}
