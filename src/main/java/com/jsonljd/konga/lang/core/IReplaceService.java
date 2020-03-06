package com.jsonljd.konga.lang.core;

import com.jsonljd.konga.lang.entity.RepalceItem;

/**
 * @Classname IReplaceService
 * @Description TODO
 * @Date 2020/2/29 9:06
 * @Created by JSON.L
 */
public interface IReplaceService {
    int getReplaceSize();

    void addReplaceItem(RepalceItem repalceItem);

    String replace(String orgStr);
}
