package com.cobra.es.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component(value = "esConfig")
public class EsConfig {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM");
    private static String ALL_CALL_INDEX_PATTERN = "*";
    private static String testIndexNamePrefix = "test_";
    private static String callIndexNamePrefix = "call_";

    @PostConstruct
    private void init() {
        setCallIndexNameSuffix("2020_08");
    }

    public static String getTestIndexName() {
        return testIndexNamePrefix + EsThreadLocal.get(testIndexNamePrefix);
    }

    public static String getCallIndexName() {
        return callIndexNamePrefix + EsThreadLocal.get(callIndexNamePrefix);
    }

    public static void setTestIndexSuffix(String value) {
        EsThreadLocal.set(testIndexNamePrefix, value);
    }


    /**
     * 指定具体索引名称，格式：yyyy_MM
     *
     * @param value
     */
    public static void setCallIndexNameSuffix(String value) {
        EsThreadLocal.set(callIndexNamePrefix, value);
    }

    /**
     * 设置当月索引
     */
    public static void setCurrentCallIndexNameSuffix() {
        EsThreadLocal.set(callIndexNamePrefix, LocalDate.now().format(formatter));
    }

    /**
     * 设置下个月索引
     */
    public static void setNextCallIndexNameSuffix() {
        EsThreadLocal.set(callIndexNamePrefix, LocalDate.now().plusMonths(1).format(formatter));
    }

    public static class EsThreadLocal {
        public static ThreadLocal<ConcurrentMap<String, String>> localIndex = new ThreadLocal<>();

        public static String get(String key) {
            return localIndex.get().get(key);
        }

        public static synchronized void set(String key, String value) {
            ConcurrentMap<String, String> map = localIndex.get();
            if (map == null) {
                map = new ConcurrentHashMap<>();
            }
            map.put(key, value);
            localIndex.set(map);
        }

        public static synchronized void destroy() {
            localIndex.remove();
        }
    }

}
