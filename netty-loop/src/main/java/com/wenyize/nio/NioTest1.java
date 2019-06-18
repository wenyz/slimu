package com.wenyize.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class NioTest1 {

    public static void main(String[] args) throws  Exception {
//        FileOutputStream fileOutputStream = new FileOutputStream("NioTest1.txt");
//
//        FileChannel fileChannel = fileOutputStream.getChannel();
//
//        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
//        byte[] messages = "hello world welcome ,nihao".getBytes();
//        for (int i = 0; i <messages.length ; i++) {
//            byteBuffer.put(messages[i]);
//
//        }
//
//        byteBuffer.flip();
//
//        fileChannel.write(byteBuffer);
//        fileOutputStream.close();


        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        byte[] messages = "kkk".getBytes();

        for (int i = 0; i <messages.length ; i++) {
            byteBuffer.put(messages[i]);
        }

        byteBuffer.flip();
        byteBuffer.flip();
        for (int i = 0; i <messages.length ; i++) {
            byteBuffer.put(messages[i]);
        }

        byte[] kkk = new byte[10];

        int index = 0;
        while (byteBuffer.hasRemaining()){
            kkk[index] = byteBuffer.get();
            index++;
        }
        System.out.println(new String(kkk));




    }
}
