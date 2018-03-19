package com.deep.domain.service;

import com.deep.domain.model.Pic;
import com.deep.domain.model.PicExample;
import com.deep.infra.persistence.sql.mapper.PicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PicService {

    @Resource
    private PicMapper picMapper;

    public int insertPic(Pic pic)
    {

        int flag=picMapper.insert(pic);

        return flag;

    }

    public List<Pic> findPicSelective(PicExample picExample)
    {
        List<Pic>find=this.picMapper.selectByExample(picExample);
        return find;
    }

//    public List<Pic> searchByBrand(String brand)
//    {
//        return this.picMapper.selectByBrand(brand);
//    }
//    public List<Pic> searchByUdate(Date udate)
//    {
//        return this.picMapper.selectByUdate(udate);
//    }
//    public List<Pic> searchByExpert(String expert)
//    {
//        return this.picMapper.selectByExpert(expert);
//    }
//    public List<Pic> searchBySymptom(String symptom)
//    {
//        return this.picMapper.selectBySymptom(symptom);
//    }
//    public List<Pic> searchByUploader(String uploader)
//    {
//        return this.picMapper.selectByUploader(uploader);
//    }
//    public List<Pic> searchByFilename(String filename)
//    {
//        return this.picMapper.selectByFilename(filename);
//    }

}
