package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import core.DynaCode;

public class PostmanApp {

    public static void main(String[] args) throws Exception {

        final File dynamicSrcDir = new File(args.length > 0 ? args[0] : "src-dynamic");

        if (!dynamicSrcDir.exists())
            throw new IllegalStateException("invalid dynamic src directory");
        else
            System.out.println("dynamic src directory: " + dynamicSrcDir.getAbsolutePath());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

        // Postman postman = getInstance(dynamicSrcDir, Postman.class, "sample.PostmanImpl");

        while (true) {
            System.out.print("Enter a message: ");
            String msg = sysin.readLine();

            getInstance(dynamicSrcDir, Postman.class, "sample.PostmanImpl").deliverMessage(msg);
        }
    }

    private static <T> T getInstance(File srcDir, Class<T> type, String impl) {
        DynaCode dynacode = new DynaCode();
        dynacode.addSourceDir(srcDir);
        return (T) dynacode.newProxyInstance(type, impl);
    }

}
