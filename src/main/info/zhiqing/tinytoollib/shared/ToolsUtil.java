package info.zhiqing.tinytoollib.shared;

/**
 * Created by zhiqing on 16-12-31.
 */
public class ToolsUtil {

    private static int prefixOfSize(String size) {
        return Integer.parseInt(size);
    }

    private static String suffixOfSize(String size) {
        String result = "";
        size = size.toUpperCase();
        if(size.endsWith("B")) {
            result = "B";
        } else if(size.endsWith("K") || size.endsWith("KB")) {
            result = "K";
        } else if(size.endsWith("M") || size.endsWith("MB")) {
            result = "M";
        } else if(size.endsWith("G") || size.endsWith("GB")) {
            result = "G";
        }
        return result;
    }

    public static long caseSize(String size) {
        long result = prefixOfSize(size);
        if (suffixOfSize(size).equals("G")) {
            result *= (1024 * 1024 * 1024);
        } else if(suffixOfSize(size).equals("M")) {
            result *= (1024 * 1024);
        } else if(suffixOfSize(size).equals("K")) {
                result *= 1024;
        }
        return result;
    }

    public static String caseSize(long size) {
        String result = "";
        if(size / (1024 * 1024 *1024) > 0) {
            result = size / (1024 * 1024 * 1024) + "GB";
        } else if (size / (1024 * 1024) > 0) {
            result = size / (1024 * 1024) + "MB";
        } else if (size / 1024 > 0) {
            result = size / 1024 + "KB";
        } else {
            result = size + "B";
        }
        return result;
    }
}
