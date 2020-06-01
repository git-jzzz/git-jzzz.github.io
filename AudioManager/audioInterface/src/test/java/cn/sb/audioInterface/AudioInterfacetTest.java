package cn.sb.audioInterface;

import cn.sb.audioInterface.pojo.Audio;
import cn.sb.audioInterface.pojo.vo.AudioVo;
import cn.sb.audioInterface.service.AudioService;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AudioInterfacetTest {

    private static  String musicjsPath_hexo="D:\\hexo\\source\\dist\\music.js";

    private static  String musicjsPath_audio="D:\\File\\audioWeb\\static\\js\\music.js";

    @Autowired
    private AudioService audioService;
     public  void allSong() {
        List<AudioVo> list=audioService.getAllSong();
        System.out.println(JSONArray.toJSONString(list));
    }



    @Test
    public void read() {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();
        try { // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(musicjsPath_audio)); // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {// 此处根据实际需要修改某些行的内容
                buf.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        System.out.println(buf.toString());
        int start=buf.indexOf("\"audio\": ")+9 ;
        int end=buf.indexOf("});")-1;
        buf.replace(start,end, JSONArray.toJSONString(audioService.getAllSong()));
        System.out.println(buf.toString());
    }

    /**
     * 将内容回写到文件中
     */
    public void write(String filePath, String content) {
        BufferedWriter bw = null;try { // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath)); // 将内容写入文件中
            bw.write(content);}
        catch (Exception e) { e.printStackTrace();} finally { // 关闭流
            if (bw != null) {
                try { bw.close(); }catch (IOException e) { bw = null; } }}}
}
