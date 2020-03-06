package com.jsonljd.konga.lang.core.impl;

import com.jsonljd.konga.lang.core.IConfigContext;
import com.jsonljd.konga.lang.core.IFileParse;
import com.jsonljd.konga.lang.core.IRunService;
import com.jsonljd.konga.lang.entity.ConfigContext;
import com.jsonljd.konga.lang.entity.MainArg;
import com.jsonljd.konga.lang.entity.RepalceItem;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DefaultConfigContext
 * @Description TODO
 * @Date 2020/2/29 9:26
 * @Created by JSON.L
 */
@Slf4j
public final class DefaultConfigContext implements IConfigContext, IRunService {
    private MainArg arg;

    public DefaultConfigContext(MainArg mainArg) {
        this.arg = mainArg;
    }

    @Override
    public ConfigContext getConfig() {
        ConfigContext configContext = null;
        try {
            String confFile = this.arg.getConfFile();
            if(confFile==null){
                confFile = "./conf/konga.xml";
            }
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(confFile));
            Element node = document.getRootElement();
            List<Element> elementClass = node.elements("file");
            RepalceItem repalceItem;
            DefaultFileParse fileParse;
            String fileName;
            configContext = new ConfigContext();
            List<IFileParse> fileParseList = new ArrayList<>(elementClass.size());
            for (Element fileEle : elementClass) {
                fileEle.element("class");
                fileName = fileEle.element("name").getTextTrim();
                fileParse = new DefaultFileParse(arg.getReplaceRootPath(), fileName);
                List<Element> replaces = fileEle.element("item").elements("replace");
                for (Element repEle : replaces) {
                    repalceItem = new RepalceItem();
                    repalceItem.setTarget(repEle.element("target").getTextTrim());
                    repalceItem.setExpect(repEle.element("expect").getTextTrim());
                    fileParse.addReplaceItem(repalceItem);
                }
                fileParseList.add(fileParse);
            }
            configContext.setFileParseList(fileParseList);
            return configContext;
        } catch (Exception e) {
            log.error("getConfig err", e);
        }
        return null;
    }

    @Override
    public String getRootPath() {
        return this.arg.getReplaceRootPath();
    }

    @Override
    public void run() {
        ConfigContext configContext = getConfig();
        if (configContext != null) {
            for (IFileParse fileParse : configContext.getFileParseList()) {
                fileParse.parse();
            }
        }
    }
}
