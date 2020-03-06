package com.jsonljd.konga.lang.core.impl;

import com.jsonljd.konga.lang.core.IReplaceService;
import com.jsonljd.konga.lang.entity.RepalceItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname DefaultReplaceService
 * @Description TODO
 * @Date 2020/2/29 10:47
 * @Created by JSON.L
 */
public class DefaultReplaceService implements IReplaceService {
    private List<RepalceItem> repalceItems = new ArrayList<>();


    @Override
    public int getReplaceSize() {
        if(repalceItems!=null){
            return repalceItems.size();
        }
        return 0;
    }

    @Override
    public void addReplaceItem(RepalceItem repalceItem) {
        repalceItems.add(repalceItem);
    }

    @Override
    public String replace(String orgStr) {
        if(getReplaceSize()==0){
            return orgStr;
        }
        for(RepalceItem repalceItem:repalceItems){
            orgStr = regReplace(orgStr,repalceItem.getTarget(),repalceItem.getExpect());
        }
        return orgStr;
    }

    private String regReplace(String content,String pattern,String newString){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        String result = m.replaceAll(newString);
        return result;
    }
}
