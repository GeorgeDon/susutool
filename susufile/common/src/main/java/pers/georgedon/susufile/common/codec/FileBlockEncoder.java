package pers.georgedon.susufile.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FileBlockEncoder extends MessageToByteEncoder<FileBlock> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, FileBlock fileBlock, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(fileBlock.getId().getBytes());
        byteBuf.writeInt(fileBlock.getLength());
        byteBuf.writeBytes(fileBlock.getData());
    }
}
