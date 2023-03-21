package com.hibernateentitygenerationconfig;

import org.hibernate.internal.util.StringHelper;
import org.hibernate.tool.api.reveng.TableIdentifier;
import org.hibernate.tool.internal.reveng.strategy.AbstractStrategy;

public class CustomRevengStrategy extends AbstractStrategy {

    @Override
    public boolean excludeTable(TableIdentifier ti) {
        return ti.getName().equals("flyway_schema_history");
    }

    @Override
    public String tableToClassName(TableIdentifier ti) {
        String pkgName = "com.springbootdemo.model";
        String className = singularize(this.toUpperCamelCase(ti.getName()));
        return StringHelper.qualify(pkgName, className);
    }

    private String singularize(String name) {
        String result = name;

        String lower = name.toLowerCase();
        if (lower.endsWith("ies")) {
            // Inverse of pluralization rule: If it ends with y with a consonant just before it, change the y to ies
            // cities --> city
            result = name.substring(0, name.length() - 3) + "y";
        } else if (lower.endsWith("ses") || lower.endsWith("xes") || lower.endsWith("zes") || lower.endsWith("shes") || lower.endsWith("ches")) {
            // Inverse of pluralization rule: If it ends with s,x,z,ch or sh, end it with es
            // switches --> switch or buses --> bus
            result = name.substring(0, name.length() - 2);
        } else if (lower.endsWith("s")) {
            // Inverse of regular plural inflection
            // customers --> customer
            result = name.substring(0, name.length() - 1);
        }

        return result;
    }
}
