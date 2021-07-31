package org.example.entities;

import java.util.UUID;

public class KeyHelper {

/*        *
         * 获取UUID
         */
        public static String getUUID() {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }


}
