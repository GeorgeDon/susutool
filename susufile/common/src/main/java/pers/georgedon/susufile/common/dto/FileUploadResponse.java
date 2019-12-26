package pers.georgedon.susufile.common.dto;

import lombok.Data;
import pers.georgedon.susufile.common.Enum.FileUploadCode;

import java.io.Serializable;

@Data
public class FileUploadResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private FileUploadCode code;
    private String id;

}
