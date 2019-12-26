package pers.georgedon.susufile.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUploadFileData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fileName;// 文件名
    private int starPos;// 开始位置
    private byte[] bytes;// 文件字节数组
    private String id;
}
