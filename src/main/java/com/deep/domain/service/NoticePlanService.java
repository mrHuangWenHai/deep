package com.deep.domain.service;

import com.deep.domain.model.NoticePlan;

import com.deep.infra.persistence.sql.mapper.NoticeMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/3/8  20:13
 */
@Service
public class NoticePlanService {
    @Resource

    private NoticeMapper noticeMapper;

    /**
     * 查询所有的通知公告
     * @return
     */
    public List<NoticePlan> getAll() {
        return noticeMapper.queryAllNotice();
    }

    /**
     * 根据通知公告的ID获取单条通知
     * @param id
     * @return
     */
    public NoticePlan getOneNotice(int id) {
        return noticeMapper.queryNoticeById(id);
    }

    /**
     * 根据类型获取所有的发布信息
     * @param type
     * @return
     */
    public List<NoticePlan> getNoticesByOneType(String type) {
        return noticeMapper.queryAllNoticeByType(type);
    }

    /**
     * 插入一个通知公告
     * @param plan
     * @return
     */
    public int insertNoticePlan(NoticePlan plan) {
        return noticeMapper.insertNotice(plan);
    }

    /**
     * 修改一个通知公告
     * @param noticePlan
     * @return
     */
    public int updateNoticePlan(NoticePlan noticePlan) {
        return noticeMapper.updateNoticePlan(noticePlan);
    }

    /**
     * 删除羊场的信息
     * @param id
     * @return
     */
    public int deleteNoticePlan(Long id) {
        return noticeMapper.deleteNoticePlan(id);
    }

    /**
     * 站内搜索接口
     * @return
     */
    public List<NoticePlan> selectInSite(String content) {
        return noticeMapper.selectNotice(content);
    }

    /**
     * 上传文件接口
     * @param file  文件内容
     * @param filePath  文件路径
     * @param fileName  文件名
     * @throws Exception   异常处理
     */
    public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+/*Token中表明上传者身份的信息*/fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
