package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.NoticePlan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Select("select * from notice_plan")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "operatorId", column = "operator_id")
    })
    List<NoticePlan> queryAllNotice();

    @Select("select * from notice_plan where type = #{type}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "operatorId", column = "operator_id")
    })
    List<NoticePlan> queryAllNoticeByType(String type);

    @Select("select * from notice_plan where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "operatorId", column = "operator_id")
    })
    NoticePlan queryNoticeById(int id);

    @Select("select * from notice_plan where title like #{content} or content like #{content} or operator_name like #{content}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "operatorId", column = "operator_id")
    })
    List<NoticePlan> selectNotice(String content);

    @Insert("insert into notice_plan(" +
            "gmt_create, " +
            "gmt_modified, " +
            "type, " +
            "title, " +
            "content, " +
            "operator_name, " +
            "operator_id" +
            ") values(" +
            "#{gmtCreate}, " +
            "#{gmtModified}, " +
            "#{type}," +
            "#{title}, " +
            "#{content}, " +
            "#{operatorName}, " +
            "#{operatorId}" +
            ")")
    int insertNotice(NoticePlan plan);

    /**
     * 修改一个代理
     * @param plan
     * @return
     */
    @Update("update notice_plan set " +
            "gmt_create = #{gmtCreate}, " +
            "gmt_modified = #{gmtModified}, " +
            "type = #{type}, " +
            "title = #{title}, " +
            "content = #{content}, " +
            "operator_name = #{operatorName}, " +
            "operator_id = #{operatorId}" +
            " where id = #{id}")
    int updateNoticePlan(NoticePlan plan);

    /**
     * 删除一个代理
     * @param id
     * @return
     */
    @Delete("delete from notice_plan where id = #{id}")
    int deleteNoticePlan(Long id);
}
