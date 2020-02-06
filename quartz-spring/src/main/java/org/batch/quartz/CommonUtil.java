package org.batch.quartz;

import java.util.UUID;

public class CommonUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
