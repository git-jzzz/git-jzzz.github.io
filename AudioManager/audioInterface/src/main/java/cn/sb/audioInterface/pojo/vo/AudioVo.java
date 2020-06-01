package cn.sb.audioInterface.pojo.vo;

import lombok.Data;

/***
 * AudioVo 返回播放器所需格式
 */
@Data
public class AudioVo  {
    private String name;
    private String artist;
    private String url;
    private String lrc;
    private String cover;
}
