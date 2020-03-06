package com.jsonljd.konga.lang.core.impl;

import com.jsonljd.konga.lang.core.IFileParse;
import com.jsonljd.konga.lang.core.IReplaceService;
import com.jsonljd.konga.lang.entity.RepalceItem;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Classname DefaultFileParse
 * @Description TODO
 * @Date 2020/2/29 10:26
 * @Created by JSON.L
 */
@Slf4j
public class DefaultFileParse implements IFileParse {
    public static final String UTF_8 = "UTF-8";
    private String filePath;
    private IReplaceService replaceService;

    public DefaultFileParse(String filePath, String fileName) {
        this.filePath = filePath +File.separator+ fileName;
        this.replaceService = new DefaultReplaceService();
    }

    public void addReplaceItem(RepalceItem repalceItem) {
        replaceService.addReplaceItem(repalceItem);
    }

    @Override
    public void parse() {
        if (replaceService.getReplaceSize() > 0) {
            File file = new File(this.filePath);
            if(!file.exists()){
                log.info(this.filePath + " file is not exists ");
                return;
            }
            StringBuilder result = new StringBuilder();

            FileInputStream fi = null;
            InputStreamReader is = null;

            BufferedReader br = null;
            FileOutputStream fos = null;
            PrintWriter pw = null;
            StringBuilder logStr = new StringBuilder();
            long s0 = System.currentTimeMillis();
            try {
                logStr.append("fileName");
                logStr.append(":");
                logStr.append(this.filePath);
                fi = new FileInputStream(file);
                is = new InputStreamReader(fi, UTF_8);
                br = new BufferedReader(is);//构造一个BufferedReader类来读取文件
                String s = null;
                while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    try {
                        result.append(replaceService.replace(s));
                    }catch (Exception e){
                        log.error("s:"+s,e);
                        e.printStackTrace();
                    }
                    result.append(System.lineSeparator());
                }

                fos = new FileOutputStream(file);
                pw = new PrintWriter(fos);
                pw.write(result.toString().toCharArray());
                pw.flush();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                logStr.append(",");
                logStr.append("times");
                logStr.append(":");
                logStr.append(System.currentTimeMillis()-s0);
                logStr.append("ms");
                log.info(logStr.toString());
                close(br);
                close(fos);
                close(pw);

                close(fi);
                close(is);
            }
        }
    }

    private void close(Closeable able) {
        if (able != null) {
            try {
                able.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
