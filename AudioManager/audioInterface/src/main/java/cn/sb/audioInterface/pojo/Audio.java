package cn.sb.audioInterface.pojo;

import lombok.Data;

@Data
public class Audio {
    private int id;
    private String song_id;
    private String name;
    private String artist;
    private String url;
    private String lrc;
    private String cover;
    private int status;
}
