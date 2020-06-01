package cn.sb.audioInterface.service;

import cn.sb.audioInterface.mapper.AudioMapper;
import cn.sb.audioInterface.pojo.Audio;
import cn.sb.audioInterface.pojo.vo.AudioVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AudioServiceImpl implements  AudioService{

    @Resource
    AudioMapper audioMapper;

    @Override
    public int addAudio(Audio audio) {
        return audioMapper.addAudio(audio);
    }

    @Override
    public int delAudio(int id) {
        return audioMapper.delAudio(id);
    }

    @Override
    public int updAudio(Audio audio) {
        return audioMapper.updAudio(audio);
    }

    @Override
    public List<Audio> list(int startRow, int pageSize) {
        return audioMapper.list(startRow,pageSize);
    }

    @Override
    public int count() {
        return audioMapper.count();
    }

    @Override
    public List<AudioVo> getAllSong() {
        return audioMapper.getAllSong();
    }

    @Override
    public int setStatus(int status, int id) {
        return audioMapper.setStatus(status, id);
    }
}
