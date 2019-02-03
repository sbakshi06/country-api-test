package com.hellofresh.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ConversionUtil {

    public static <T> T convertFileContentToObject(String fileName, TypeReference<T> type) throws IOException {
        ClassLoader classLoader = ConversionUtil.class.getClassLoader();
        String jsonStringFileContent = IOUtils.toString(classLoader.getResourceAsStream(fileName), Charset.defaultCharset());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStringFileContent, type);
    }

}
