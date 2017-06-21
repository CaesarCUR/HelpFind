package com.example.codejam.helpfind.entity;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by zhouming on 2017/6/21.
 */

public class StaticMethods {

    /**
     * Transfer input stream to String object
     * @param is java.io.InputStream object
     * @return string-like data which transfer from stream-like data
     * @throws IOException
     */
    public static String getStringFromInputStream(java.io.InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int len = -1;

        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        is.close();

        String outData = os.toString();   // transfer io stream to string object
        os.close();
        return outData;
    }
}
