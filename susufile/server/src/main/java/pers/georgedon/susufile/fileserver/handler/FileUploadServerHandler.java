package pers.georgedon.susufile.fileserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import pers.georgedon.susufile.common.Enum.FileUploadCode;
import pers.georgedon.susufile.common.dto.FileUploadDTO;
import pers.georgedon.susufile.common.dto.FileUploadFileData;
import pers.georgedon.susufile.common.dto.FileUploadResponse;
import pers.georgedon.susufile.common.utils.IDGenerator;

import java.io.File;
import java.io.RandomAccessFile;

public class FileUploadServerHandler extends ChannelInboundHandlerAdapter {

    private String file_dir = "/tmp";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
        System.out.println("服务端：channelActive()");
//        FileUploadResponse response = new FileUploadResponse();
//        response.setCode(FileUploadCode.OK);
//        response.setId(IDGenerator.createId());
//        ctx.writeAndFlush("ok");
//        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        System.out.println("服务端：channelInactive()");
        ctx.flush();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到客户端发来的文件,正在处理....");
        if (msg instanceof FileUploadDTO) {
            handleFileUpLoadRequest(ctx, (FileUploadDTO) msg);
        }
        if (msg instanceof FileUploadFileData) {
            FileUploadFileData ef = (FileUploadFileData) msg;
            byte[] bytes = ef.getBytes();
            int start = ef.getStarPos();
            String fileName = ef.getFileName();//文件名
            String path = file_dir + File.separator + fileName;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);//移动文件记录指针的位置,
            randomAccessFile.write(bytes);
//            if (bytes.length > 0) {
//                ctx.writeAndFlush(start);//向客户端发送消息
//                randomAccessFile.close();
//
//            } else {
//                ctx.close();
//            }
            System.out.println("处理完毕,文件路径:" + path + ",");
        }
    }

    private void handleFileUpLoadRequest(ChannelHandlerContext ctx, FileUploadDTO request) {
        String path = file_dir + File.separator + request.getFileName();
        File file = new File(path);
        FileUploadResponse response = new FileUploadResponse();
        if (file.exists()) {
            //todo
        }
        response.setCode(FileUploadCode.OK);
        response.setId(IDGenerator.createId());
        ctx.writeAndFlush(response);
    }
}

