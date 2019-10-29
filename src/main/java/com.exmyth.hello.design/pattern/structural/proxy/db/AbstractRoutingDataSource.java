package com.exmyth.hello.design.pattern.structural.proxy.db;
//org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
/**
 * @author maming.zhong
 * @date 2019-10-29 16:44
 * @description
 */
public abstract class AbstractRoutingDataSource {
    protected abstract Object determineCurrentLookupKey();
}
