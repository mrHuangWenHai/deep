package com.deep.domain.service;


import com.deep.domain.model.Message;
import com.deep.domain.model.MessageExample;
import com.deep.infra.persistence.sql.mapper.MessageMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {

    @Resource
    private MessageMapper messageMapper;

    public int insertMessage(Message message) {
        int flag = messageMapper.insertSelective(message);
        return flag;
    }


    public List<Message> findMessageSelective(MessageExample messageExample) {
        List<Message> find = this.messageMapper.selectByExample(messageExample);
        return find;
    }

    public List<Message> findMessageSelectiveWithRowbounds(MessageExample messageExample,int pageNumb,int limit) {

        int offset = pageNumb * limit;

        RowBounds rowBounds=new RowBounds(offset,limit);

        List<Message>find=this.messageMapper.selectByExampleWithRowbounds(messageExample,rowBounds);

        return find;

    }
    public void deleteMessageById(Integer id)
    {
        messageMapper.deleteByPrimaryKey(id);
    }

}
