package cn.sb.audioInterface.controller;

import cn.sb.audioInterface.pojo.Audio;
import cn.sb.audioInterface.pojo.vo.AudioVo;
import cn.sb.audioInterface.service.AudioService;
import cn.sb.audioInterface.service.IoService;
import cn.sb.audioInterface.util.Result;
import cn.sb.audioInterface.util.TableData;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AudioController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Autowired
    AudioService audioService;

    @Autowired
    IoService ioService;
    //网易云歌曲接口
    private static String song_interface = "https://music.163.com/song/media/outer/url?id=";
    //歌词接口
    private static String lrc_interface = "http://music.163.com/api/song/media?id=";

    //music.js路径
    private static  String musicjsPath_hexo="D:\\hexo\\source\\dist\\music.js";

    private static  String musicjsPath_web="D:\\hexo\\AudioManager\\audioWeb\\static\\js\\music.js";

    //歌词存储路径
    private static String lrcPath_hexo="D:\\hexo\\source\\lrc\\";

    private static String lrcPath_web="D:\\hexo\\AudioManager\\audioWeb\\view\\lrc\\";


    /***
     * 获取layui所需tableData
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/audioList")
    public TableData list(
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false, defaultValue = "5") String limit
    ) {
        return TableData.ok(0, audioService.list((Integer.parseInt(page) - 1) * Integer.parseInt(limit), Integer.parseInt(limit)), audioService.count());
    }

    /***
     * 新增歌曲信息  下载歌词
     * @param audio
     * @return
     */
    @PostMapping("/addAudio")
    public Result addAudio(Audio audio) {
        try {
            URL url = new URL(lrc_interface + audio.getSong_id());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10 * 1000);//设置超时10秒
            InputStream inputStream = urlConnection.getInputStream();

            //防止中文乱码
            //歌词存储地址
            FileOutputStream fileOutputStream = new FileOutputStream(lrcPath_hexo + audio.getName() + ".lrc");
            FileOutputStream fileOutputStream2 = new FileOutputStream(lrcPath_web + audio.getName() + ".lrc");
            byte[] bytes = new byte[1024];
            int len;
            //循环写入文件
            while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
                fileOutputStream2.write(bytes, 0, len);
            }
            //释放资源
            fileOutputStream.close();
            fileOutputStream2.close();
            inputStream.close();
            urlConnection.disconnect();
            audio.setLrc("/lrc/" + audio.getName() + ".lrc");
            audio.setUrl(song_interface + audio.getSong_id());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return audioService.addAudio(audio) > 0 ? Result.ok("录入成功") : Result.error("录入失败");
    }


    /**
     * 删除歌曲
     * @param id
     * @return
     */
    @GetMapping("/delAudioById/{id}")
    public Result delAudioById(@PathVariable("id") Integer id) {

        return audioService.delAudio(id) > 0 ? Result.ok("删除成功") : Result.error("出现异常-操作失败-请联系管理员");
    }

    /***
     * 修改歌曲状态
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/setStatus/{id}/{status}")
    public Result setStatus(@PathVariable("id")Integer id,@PathVariable("status")Integer status){
        return audioService.setStatus(status, id)>0? Result.ok(status==1?"已开启":"已禁用"):Result.error("出现异常-操作失败-请联系管理员");
    }

    /**
     * 获取可用歌曲json数据
     * @return
     */
    @GetMapping("/getAllSong")
    public Result getAllSong() {
        if(musicjsPath_hexo==null||musicjsPath_web==null){
            return Result.error("请设置您的两个music.js路径");
        }
        List<AudioVo> list=audioService.getAllSong();
        if(list.size()==0){
            return Result.ok("暂无歌曲使用中");
        }
        String json=JSONArray.toJSONString(list);
        ioService.writeFile(musicjsPath_hexo, ioService.readFile(musicjsPath_hexo, json));
        ioService.writeFile(musicjsPath_web, ioService.readFile(musicjsPath_web, json));

        Map map= new HashMap<String,Object>();
        map.put("audio",list);
        return Result.ok(map);
    }

/*
    @GetMapping("/setPath")
    public Result setPath(String musicjsPath_hexo, String musicjsPath_web){
       if( !new File(musicjsPath_hexo).exists()){
           Result.error("请检查您的文件路径！");
       }
        this.musicjsPath_hexo=musicjsPath_hexo;
        this.musicjsPath_web=musicjsPath_web;
        return Result.ok("设置成功!");
    }*/


}
