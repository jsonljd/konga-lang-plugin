package com.jsonljd.konga.lang.entity;

import com.jsonljd.konga.lang.core.IFileParse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname ConfigContext
 * @Description TODO
 * @Date 2020/2/29 9:25
 * @Created by JSON.L
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigContext {
    private List<IFileParse> fileParseList;
}
