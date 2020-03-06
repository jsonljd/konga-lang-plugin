package com.jsonljd.konga.lang.core;

import com.jsonljd.konga.lang.entity.ConfigContext;

/**
 * @Classname IConfigContext
 * @Description TODO
 * @Date 2020/2/29 9:05
 * @Created by JSON.L
 */
public interface IConfigContext {
    ConfigContext getConfig();

    String getRootPath();
}
