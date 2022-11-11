package top.kingdon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyTest {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\code\\数据标注\\test1.txt")));
        OutputStream outputStream = new FileOutputStream("D:\\code\\数据标注\\out.txt");
        String line;
        do {
            line = bufferedReader.readLine();
            int  a= line.indexOf("应");
            int b = line.indexOf("为") + 1;
            int c = line.indexOf("个");
            String param = line.substring(0,a).trim();
            String conts = line.substring(b,c).trim();
            int cont = Integer.parseInt(conts);
            System.out.println(param);
            System.out.println(cont);
            while(cont-- >0){
                outputStream.write(param.getBytes(StandardCharsets.UTF_8));
            }

        } while (line != null);




    }


}
