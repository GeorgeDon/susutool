package pers.georgedon.susufile.common.codec;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileBlock {

    private String id; //32位的id
    private int Length;//4位数据块的长度
    private byte[] data;

}
