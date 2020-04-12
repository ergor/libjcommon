package no.netb.libjcommon;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    public static String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        e.printStackTrace(pw);
        pw.flush();
        stringWriter.flush();
        return stringWriter.toString();
    }
}
