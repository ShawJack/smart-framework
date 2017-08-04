package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ithink on 2017-6-18.
 */
public class StreamUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e){
            LOGGER.error("get String failure", e);
            throw new RuntimeException(e);
        } finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    LOGGER.error("close reader failure", e);
                    throw new RuntimeException(e);
                }

            }
        }

        return sb.toString();
    }

}
