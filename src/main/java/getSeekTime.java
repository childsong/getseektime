import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * @program: getseektime
 * @description: 用来得到磁盘寻道时间
 * @author: SongZhen
 * @create: 2018-11-24 16:34
 */
public class getSeekTime {

    public static String  path="F:\\test.txt";
    public static long num=5000000L;

    public static int testnum=5000000;


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        write();
        seek();
    }

    public static void write() throws IOException{
        String filePath="data/";
        DB db = Iq80DBFactory.factory.open((new File(filePath,"db/")),new Options().createIfMissing(true));
        for(int i=0;i<num;i++){
            byte[] bytes=(""+i).getBytes();
            byte[] bytesValue="a".getBytes();
            db.put(bytes,bytesValue);
        }

        byte[] bytes=new byte[testnum];
        db.put("test".getBytes(),bytes);
        db.close();

    }


    public static void seek() throws IOException {
        String filePath = "data/";
        DB db = Iq80DBFactory.factory.open((new File(filePath, "db/")), new Options().createIfMissing(true));
        for (int i = 0; i < 5; i++) {

            CurrentTimeUtil.setStartTime();
            db.get("test".getBytes());
            CurrentTimeUtil.setEndTime();
            CurrentTimeUtil.showExecuteTime(i+"-seq:");

            CurrentTimeUtil.setStartTime();
            for(int j=0;j<testnum;j++){
                // 随机读
                byte[] getValuebyte=db.get((""+j).getBytes());
            }
            CurrentTimeUtil.setEndTime();
            CurrentTimeUtil.showExecuteTime(i+"-random:");


        }
        db.close();
    }
}