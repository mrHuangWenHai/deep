package com.deep.domain.service;


import com.deep.domain.model.Client;
import com.deep.domain.model.Talk;
import com.deep.infra.persistence.sql.mapper.TalkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TalkService {
    @Autowired
    private TalkMapper mapper;

    public int member_record(String talk_id, Long member_id, boolean member_role) {
        return mapper.member_record(talk_id, member_id, member_role);
    }

    public int talkRecode(String talk_id, String talker_name, Long talker_id, Long receiver_id, String content, Date time, boolean isLink, boolean isHandle) {
        return mapper.talkRecord(talk_id, talker_name, talker_id, receiver_id, content, time, isLink, isHandle);
    }

    public List<String> getTalkRecordIDs(Long member_id) {
        return mapper.getTalkRecordIDs(member_id);
    }

    public List<Talk> getTalkRecord(String talk_id) {
        return mapper.getTalkRecord(talk_id);
    }

    public List<Client> getClientList(Long expert_id) {
        return mapper.getClientList(expert_id);
    }

    public List<Talk> getSummaryTalkRecord(String talk_id) {
        return mapper.getSummaryTalkRecord(talk_id);
    }

    public String getExpertName(String talk_id, Boolean isMainExpert) {
        return mapper.getExpertName(talk_id, isMainExpert);
    }

}
