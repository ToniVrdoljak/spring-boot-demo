package com.springbootdemo;


import org.hibernate.tool.api.reveng.RevengStrategy;
import org.hibernate.tool.internal.reveng.strategy.AbstractStrategy;

public class XyzExampleStrategy extends AbstractStrategy implements RevengStrategy {

    public String classNameToCompositeIdName(String var1) {
        return var1 + "test";
    }

    public XyzExampleStrategy() {
    }

}