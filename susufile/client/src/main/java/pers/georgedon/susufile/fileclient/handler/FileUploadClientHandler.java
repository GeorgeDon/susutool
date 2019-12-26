package pers.georgedon.susufile.fileclient.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import pers.georgedon.susufile.common.dto.FileUploadDTO;
import pers.georgedon.susufile.common.dto.FileUploadResponse;

@AllArgsConstructor
public class FileUploadClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端开始");
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setFileName("test");
        ctx.writeAndFlush(fileUploadDTO);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端读取到数据");
        FileUploadResponse response = (FileUploadResponse) msg;
        System.out.println(response.getId());
//        ctx.close();
    }
}
