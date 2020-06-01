package cn.sb.audioInterface.service;

import cn.sb.audioInterface.pojo.Audio;
import cn.sb.audioInterface.pojo.vo.AudioVo;

import java.util.List;

public interface AudioService {
    int addAudio(Audio audio);
    int delAudio(int id);
    int updAudio(Audio audio);
    List<Audio> list( int startRow,  int pageSize);
    int count();
    List<AudioVo> getAllSong();
    int setStatus(int status,int id);
}
