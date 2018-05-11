package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.Client;
import com.deep.domain.model.Talk;
import com.deep.domain.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component(value = "talkMapper")
public interface TalkMapper {
    //select evaluation,count(evaluation) from client_evaluation where expert_id = 2 GROUP BY evaluation

    @Insert("insert into talk_account(talk_id, member_id, member_role) values (#{talk_id}, #{member_id}, #{member_role})")
    int member_record(@Param("talk_id") String talk_id, @Param("member_id") Long member_id, @Param("member_role") boolean member_role);

    @Insert("insert into talk(talk_id, talker_name, talker_id, receiver_id, content, start_time, isLink, isHandle) values(#{talk_id},#{talker_name}, #{talker_id},#{receiver_id},#{content},#{start_time},#{isLink},#{isHandle})")
    int talkRecord(@Param("talk_id") String talk_id, @Param("talker_name") String talker_name, @Param("talker_id") Long talker_id, @Param("receiver_id") Long receiver_id, @Param("content") String content, @Param("start_time") Date start_time, @Param("isLink") boolean isLink, @Param("isHandle") boolean isHandle);

//    @Select("select * from talk where talker_id = #{talker_id} and receiver_id = #{receiver_id} and isHandle = 0 order by start_time")
//    List<Talk> getleaveWord(@Param("talker_id") Long talker_id, @Param("receiver_id") Long receiver_id);
//
//    @Select("select talker_id from talk where receiver_id = #{receiver_id} and isHandle = 0 group by talker_id")
//    List<Long> leaveWordSender(@Param("receiver_id") Long receiver_id);

    @Select("select talk_id from talk_account where member_id = #{member_id}")
    List<String> getTalkRecordIDs(@Param("member_id") Long member_id);

    @Select("select * from talk where talk_id = #{talk_id}")
    List<Talk> getTalkRecord(@Param("talk_id") String talk_id);

    @Select("select * from talk where talk_id = #{talk_id} limit 0,3")
    List<Talk> getSummaryTalkRecord(@Param("talk_id") String talk_id);

//    @Update("update talk set isHandle = 1 where receiver_id = #{receiver_id}")
//    int changeStatus(@Param("receiver_id") Long receiver_id);

    @Select("select user_realname from user_manage where id in (select member_id from talk_account where member_role = #{member_role} and talk_id = #{talk_id}")
    String getExpertName(@Param("talk_id") String talk_id, @Param("member_role") Boolean member_role);

    @Select("select id,user_realname from user_manage where id in (select distinct member_id from talk_account where member_role = 0 and talk_id in (select talk_id from talk_account where member_id = #{member_id}))")
    List<Client> getClientList(@Param("member_id") Long member_id);
}
