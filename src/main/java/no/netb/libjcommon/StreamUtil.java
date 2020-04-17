package no.netb.libjcommon;

import java.io.*;
import java.nio.charset.Charset;

public class StreamUtil {

    public static final int BUF_SZ = 1024;

    public static String writeToString(InputStream inputStream, Charset charset) throws IOException {
        char[] buffer = new char[BUF_SZ];
        StringWriter stringWriter = new StringWriter();
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        
        int charsRead = reader.read(buffer, 0, buffer.length);
        while (charsRead > -1) {
            stringWriter.write(buffer, 0, charsRead);
            charsRead = reader.read(buffer, 0, buffer.length);
        }

        return stringWriter.toString();
    }

    public static byte[] writeToByteArray(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[BUF_SZ];

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUF_SZ);

        int bytesRead = inputStream.read(buffer);
        while (bytesRead > -1) {
            outputStream.write(buffer, 0, bytesRead);
            bytesRead = inputStream.read(buffer);
        }

        return outputStream.toByteArray();
    }
}
