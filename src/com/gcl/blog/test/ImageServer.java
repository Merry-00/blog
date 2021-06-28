package com.gcl.blog.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ImageServer {
    public static void main(String[] args) throws Exception {
        new Thread(new ImageServer01()).start();
    }
}


class ImageServer01  implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 2839564863495205814L;

    ServerSocket ss;


    public ImageServer01() throws Exception {

        //监听端口
        ss = new ServerSocket(9000);
        new Thread(this).start();
    }

    @Override
    public void run() {
        int i = 0;
        System.out.println("server startup.");
        while (true) {
            try {
                Socket s = ss.accept();
                // 每个客户端一个处理线程
                new Handler(s, i).start();
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}

class Handler extends Thread {
    Socket s;
    int id;

    public Handler(Socket s, int id) {
        this.s = s;
        this.id = id;

    }

    @Override
    public void run() {
        System.out.println("in handling..");

        FileOutputStream fos = null;
        InputStream is = null;
        try {
            is = s.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));


            String filename = System.currentTimeMillis()+".jpg";

            String filepath = "C:\\Users\\86166\\Desktop\\img\\";

            File dirFile = new File(filepath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            System.out.println("read line " + id + " :" + filename);
            File file = new File(filepath+filename);

            int len = 0;
            int BUFSIZE = 1*1024;

            byte[] bytes = new byte[BUFSIZE];

            fos = new FileOutputStream(file);
            while ((len = is.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, len);
                fos.flush();
            }

            System.out.println("done.");




            FileInputStream fs= new FileInputStream(filepath+filename);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 服务端就不要手贱 关了socket否则Python 会出现错误Errno 10054让客户端关掉就行啦
            try {
                System.out.println("close");
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
