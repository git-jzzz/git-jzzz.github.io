package cn.sb.audioInterface.mapper;

import cn.sb.audioInterface.pojo.Audio;
import cn.sb.audioInterface.pojo.vo.AudioVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AudioMapper {
    /***
     * 新增歌曲信息
     * @param audio
     * @return
     */
    int addAudio(Audio audio);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delAudio(int id);

    /**
     * 修改
     *
     * @param audio
     * @return
     */
    int updAudio(Audio audio);

    /**
     * 列表获取所有歌曲
     *
     * @param startRow
     * @param pageSize
     * @return
     */
    List<Audio> list(@Param("startRow") int startRow, @Param("pageSize") int pageSize);

    /**
     * 总数
     *
     * @return
     */
    int count();

    /**
     * 获取所有待使用歌曲
     *
     * @return
     */
    List<AudioVo> getAllSong();

    /**
     * 修改状态
     *
     * @param status
     * @param id
     * @return
     */
    int setStatus(@Param("status") int status, @Param("id") int id);
}
