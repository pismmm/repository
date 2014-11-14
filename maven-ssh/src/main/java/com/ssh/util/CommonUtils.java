package com.ssh.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shizhenchao on 2014-9-28.
 */
public class CommonUtils {
    public static List<String> splitByComma2StringList(String inputStr) {
        List<String> integers = null;
        if (inputStr.contains(",")) {
            integers = Arrays.asList(inputStr.split(","));
        }
        return integers;
    }
}
