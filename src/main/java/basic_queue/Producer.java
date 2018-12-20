package basic_queue;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * 生产者
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException {
        try {
            BasicQueue basicQueue = new BasicQueue("C:\\Users\\wgg9696\\snailcar-netty\\laoma","test");
            Random random = new Random();
            int i = 0;

            while(true){
                String msg = new String("msg"+i++);
                basicQueue.enqueue(msg.getBytes());
                System.out.println("produce" + msg);
                Thread.sleep(random.nextInt(2000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
