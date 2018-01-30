package me.libme.rec.fn.u2i;

import me.libme.kernel._c.cache.JCacheService;
import scalalg.me.libme.module.hbase.HBaseConnector;

import java.util.Map;

/**
 * Created by J on 2018/1/26.
 */
public class HBaseRepo implements Unique2IntRepo {


    private final String tableName;

    private final String family;

    private final String intMark;

    private final HBaseConnector.HBaseExecutor hBaseExecutor;

    public HBaseRepo(String tableName, String family, String intMark, HBaseConnector.HBaseExecutor hBaseExecutor) {
        this.tableName = tableName;
        this.family = family;
        this.intMark = intMark;
        this.hBaseExecutor = hBaseExecutor;
    }

    @Override
    public void initialize(JCacheService<String, Integer> cacheService) {
        Map<String, String> data= hBaseExecutor.queryOperations().scan(tableName,family,intMark);
        data.forEach((key,value)->cacheService.put(key,Integer.valueOf(value)));
    }


    @Override
    public void put(String key, Integer value) {
        hBaseExecutor.columnOperations().insert(tableName,family,intMark,key,value.toString());
    }

    @Override
    public void remove(String key) {
        hBaseExecutor.columnOperations().delete(tableName,family,intMark,key);
    }
}
