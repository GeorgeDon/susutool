package pers.georgedon.susufile.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUploadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
}
