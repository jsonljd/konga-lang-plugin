package com.jsonljd.konga.lang;

import com.jsonljd.konga.lang.core.impl.DefaultConfigContext;
import com.jsonljd.konga.lang.entity.MainArg;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @Classname StartService
 * @Description TODO
 * @Date 2020/2/29 9:03
 * @Created by JSON.L
 */
public class StartService {
    public static void main(String[] args) {
        MainArg arg = new MainArg();
        CmdLineParser parser = new CmdLineParser(arg);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }

        DefaultConfigContext configContext = new DefaultConfigContext(arg);
        configContext.run();
    }
}
