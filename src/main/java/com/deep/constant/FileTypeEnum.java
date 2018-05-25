package com.deep.constant;

public enum FileTypeEnum {
    /*******常用图片格式*******/
    PNG ("89504E47"),
    GIF ("47494638"),
    TIF ("49492A00"),
    MPG ("000001BA"),
    JPG ("FFD8FFE0"),
    JPG1("FFD8FFE1"),

    /*************常用视频格式****************************/
    AVI("41564920"),
    RM ("2E524D46"),
    MOV("6D6F6F76"),
    ASF("3026B27"),
    MP4_1("00000014"),
    MP4("00000018");

    public String value;

    FileTypeEnum(String value){
       this.value = value;
    }

}
