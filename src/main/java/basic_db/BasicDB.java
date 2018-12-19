package basic_db;

import java.io.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 简易KV数据库
 */
public class BasicDB {

    private static final int MAX_DATA_LENGTH = 1020;
    private static final byte[] ZERO_BYTES = new byte[MAX_DATA_LENGTH];
    private static final String DATA_SUFFIX = ".data";
    private static final String META_SUFFIX = ".meta";

    private RandomAccessFile db;
    private File metaFile;

    private Map<String,Long> indexMap;
    private Queue<Long> gaps;

    public BasicDB(String path,String name) throws IOException {
        File dataFile = new File(path+name+DATA_SUFFIX);
        metaFile = new File(path+name+META_SUFFIX);

        db = new RandomAccessFile(dataFile,"rw");
        if(metaFile.exists()){
            loadMeta();
        }else{
            indexMap = new HashMap<>();
            gaps = new ArrayDeque<>();
        }
    }

    private void loadMeta() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(metaFile)));
        try {
            loadIndex(dataInputStream);
            loadGaps(dataInputStream);
        }finally {
            dataInputStream.close();
        }
    }

    private void loadGaps(DataInputStream dataInputStream) throws IOException {
        int size = dataInputStream.readInt();
        gaps = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            dataInputStream.readLong();
        }
    }

    private void loadIndex(DataInputStream dataInputStream) throws IOException {
        int size = dataInputStream.readInt();
        indexMap = new HashMap<>((int) ((size/0.75f)+1),0.75f);
        for (int i = 0; i < size; i++) {
            String key = dataInputStream.readUTF();
            Long index = dataInputStream.readLong();
            indexMap.put(key,index);
        }
    }

    public void put(String key,byte[] value) throws IOException {
        Long index = indexMap.get(key);

        if(index == null){
            index = nextAvailablePos();
            indexMap.put(key,index);
        }
        writeData(index,value);
    }

    public byte[] get(String key) throws IOException {
        Long index = indexMap.get(key);
        if(index == null){
            return null;
        }

        db.seek(index);
        int size = db.readInt();
        byte[] tmp = new byte[size];
        db.readFully(tmp);
        return tmp;
    }

    public void remove(String key){
        Long index = indexMap.remove(key);
        if(index != null){
            gaps.offer(index);
        }
    }

    public void flush() throws IOException {
        saveMeta();
        db.getFD().sync();
    }

    public void close() throws IOException {
        flush();
        db.close();
    }

    private void saveMeta() throws IOException {
        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(metaFile)));
            saveIndex(outputStream);
            saveGaps(outputStream);
        }finally {
            outputStream.close();
        }
    }

    private void saveGaps(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(gaps.size());
        for (Long gap : gaps) {
            outputStream.writeLong(gap);
        }
    }

    private void saveIndex(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(indexMap.size());
        for (Map.Entry<String, Long> stringLongEntry : indexMap.entrySet()) {
            outputStream.writeUTF(stringLongEntry.getKey());
            outputStream.writeLong(stringLongEntry.getValue());
        }
    }

    private void writeData(Long index, byte[] value) throws IOException {
        int size = value.length;
        if(size > MAX_DATA_LENGTH){
            throw new IllegalArgumentException("maximum allowed length is "
                    + MAX_DATA_LENGTH + ", data length is " + size);
        }

        db.seek(index);
        db.writeInt(size);
        db.write(value);
        db.write(ZERO_BYTES,0,MAX_DATA_LENGTH-size);
    }

    private Long nextAvailablePos() throws IOException {
        if(!gaps.isEmpty()){
            return gaps.poll();
        }else{
            return db.length();
        }
    }


}
