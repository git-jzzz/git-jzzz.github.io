package cn.sb.audioInterface.service;

public interface IoService {

    /**
     * 读取文件
     * @param path
     * @return
     */
    String readFile(String path,String musicjson);

    void writeFile(String path,String content);
}
