package pers.georgedon.susufile.fileclient;

import pers.georgedon.susufile.fileclient.client.FileClient;

public class SusuFileClientApplication {

    public static void main(String[] args) {
        FileClient fileClient=new FileClient();
        fileClient.upload();
    }
}
