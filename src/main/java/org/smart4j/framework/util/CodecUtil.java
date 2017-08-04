package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编解码工具类
 * Created by ithink on 2017-6-18.
 */
public class CodecUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL编码
     */
    public static String encodeURL(String source){
        String result;

        try {
            result = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 将URL解码
     */
    public static String decodeURL(String source){
        String result;

        try {
            result = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }

        return result;
    }

}
