package com.example.split1;

/**
 * DataSource
 *
 * @Author tootwo2
 * @Date 2021/3/7 10:31 下午
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> dsHolder = new ThreadLocal<String>();

    public static String getDataSource() {
        return dsHolder.get();
    }

    public static void putDataSource(String value) {
        dsHolder.set(value);
    }

    public static void clear(){
        dsHolder.remove();
    }
}
