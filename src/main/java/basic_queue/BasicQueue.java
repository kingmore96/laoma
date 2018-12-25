package basic_queue;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 简易消息队列
 */
public class BasicQueue {
    private static final int MAX_MSG_NUM = 1024*1024;
    private static final int MAX_MSG_BODY_SIZE = 1020;
    private static final int MSG_SIZE = MAX_MSG_BODY_SIZE + 4;
    private static final int DATA_FILE_SIZE = MAX_MSG_NUM * MSG_SIZE;
    private static final int META_FILE_SIZE = 8;

    private MappedByteBuffer dataBuf;
    private MappedByteBuffer metaBuf;



    public BasicQueue(String path,String queueName) throws IOException {
        if(!path.endsWith(File.separator)){
            path += File.separator;
        }

        RandomAccessFile dataFile = null;
        RandomAccessFile metaFile = null;

        try {
            dataFile = new RandomAccessFile(path+queueName+".data","rw");
            metaFile = new RandomAccessFile(path+queueName+".meta","rw");

            dataBuf = dataFile.getChannel().map(FileChannel.MapMode.READ_WRITE,0,DATA_FILE_SIZE);
            metaBuf = metaFile.getChannel().map(FileChannel.MapMode.READ_WRITE,0,META_FILE_SIZE);

        }finally {
            if(dataFile != null){
                dataFile.close();
            }

            if(metaFile != null){
                metaFile.close();
            }
        }
    }

    private int head(){
        return metaBuf.getInt(0);
    }

    private void head(int newIndex){
        metaBuf.putInt(0,newIndex);
    }

    private int tail(){
        return metaBuf.getInt(4);
    }

    private void tail(int newIndex){
        metaBuf.putInt(4,newIndex);
    }

    private boolean isEmpty(){
        return head() == tail();
    }

    private boolean isFull(){
        return ((tail()+MSG_SIZE)%DATA_FILE_SIZE) == head();
    }

    /**
     * 入队
     * @param data
     */
    public void enqueue(byte[] data){
        if(data.length>MAX_MSG_BODY_SIZE){
            throw new IllegalArgumentException("msg size is " + data.length
                    + ", while maximum allowed length is " + MAX_MSG_BODY_SIZE);
        }

        if(isFull()){
            throw new IllegalStateException("queue is full");
        }

        int tail = tail();
        dataBuf.position(tail);
        dataBuf.putInt(data.length);
        dataBuf.put(data);

        if(tail + MSG_SIZE >= DATA_FILE_SIZE){
            tail(0);
        }else{
            tail(tail+MSG_SIZE);
        }
    }

    /**
     * 出队
     * @return
     */
    public byte[] dequeue(){
        if(isEmpty()){
            return null;
        }

        int head = head();
        dataBuf.position(head);
        int size = dataBuf.getInt();
        byte[] bytes = new byte[size];
        dataBuf.get(bytes);

        if(head + MSG_SIZE >= DATA_FILE_SIZE){
            head(0);
        }else{
            head(head+MSG_SIZE);
        }

        return bytes;
    }
}
