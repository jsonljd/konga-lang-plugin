package com.jsonljd.konga.lang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kohsuke.args4j.Option;

/**
 * @Classname MainArg
 * @Description TODO
 * @Date 2020/2/29 9:17
 * @Created by JSON.L
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MainArg {
    @Option(name="-c",usage = "config file path 配置文件")
    private String confFile;

    @Option(name="-r",usage = "konga的根目录")
    private String replaceRootPath;
}
