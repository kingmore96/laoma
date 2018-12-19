package basic_db;

import io.Student;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestBasicDB {
    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String,Student> map = new HashMap<>();
        map.put("小王",new Student("小王",12,25d));
        map.put("小1",new Student("小1",13,25d));
        saveStudents(map);
    }

    private static byte[] toBytes(Student student) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(bout);
        dout.writeUTF(student.getName());
        dout.writeInt(student.getAge());
        dout.writeDouble(student.getScore());
        return bout.toByteArray();
    }

    public static void saveStudents(Map<String, Student> students)
            throws IOException {
        BasicDB db = new BasicDB("C:\\Users\\wgg9696\\snailcar-netty\\laoma\\", "students");
        for (Map.Entry<String, Student> kv : students.entrySet()) {
            db.put(kv.getKey(), toBytes(kv.getValue()));
        }
        db.close();
    }
}
