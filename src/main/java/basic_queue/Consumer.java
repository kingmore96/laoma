package basic_queue;

import java.io.IOException;
import java.util.Random;

/**
 * 消费者
 */
public class Consumer {
    public static void main(String[] args) {
        try {
            BasicQueue basicQueue = new BasicQueue("C:\\Users\\wgg9696\\snailcar-netty\\laoma","test");
            Random random = new Random();
            while(true){
                byte[] dequeue = basicQueue.dequeue();
                if(dequeue == null){
                    Thread.sleep(random.nextInt(4000));
                    continue;
                }else {
                    System.out.println("consume msg"+ new String(dequeue,0,dequeue.length,"UTF-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
