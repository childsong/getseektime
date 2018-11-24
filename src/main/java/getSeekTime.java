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
    public static long num=50000000L;

    public static int testnum=50000;


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        seek();
    }

    public static void write() throws IOException{
        File file=new File(path);
        if(!file.exists())
            file.createNewFile();
        RandomAccessFile raf=new RandomAccessFile(file,"rw");
        byte[] x=new byte[1024];
        for(long i=0;i<num;i++)
            raf.write(x);
        raf.close();
    }


    public static void seek() throws IOException{
        System.out.println("bn");
        File file=new File(path);
        RandomAccessFile raf=new RandomAccessFile(file,"rw");
        long begin=System.currentTimeMillis();
        for(int i=0;i<testnum;i++){
            Random r=new Random();
            long l=r.nextLong();
            l=l%(num*1024);
            if(l<0)
                l=0-l;
            raf.seek(l);
            byte[] w=new byte[1];
            raf.read(w);//读取一字节，时间可忽略
        }
        long end=System.currentTimeMillis();
        System.out.println((end-begin)/testnum); // 结果可能是0，因为精度不够
    }


}