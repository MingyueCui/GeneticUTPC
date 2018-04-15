package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


/**
 * 调用Python库完成转换成DNF的任务
 *
 * @author misen
 *
 */
public class DnfTransfer {

    public static String toDnf(String expr) {
        String result = callPython(expr);
        return result;
    }

    private static String callPython(String expr) {
        String result = "";
        try {
            FileWriter writer = new FileWriter("./tmp", true);
            writer.append(expr);
            writer.close();

            Process process = Runtime.getRuntime().exec("python ./to_f_dnf.py");
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();

            File file = new File("./tmp");
            file.delete();
        } catch (IOException e) {

        }

        return result;
    }

}
