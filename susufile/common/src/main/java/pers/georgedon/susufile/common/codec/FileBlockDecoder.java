package pers.georgedon.susufile.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class FileBlockDecoder extends ByteToMessageDecoder {

    private final static int HEAD_LENGTH = 36;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        if (in.readableBytes() < HEAD_LENGTH) {
            return;
        }
        in.markReaderIndex();
        byte[] idBytes = new byte[32];
        in.readBytes(idBytes);
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] body = new byte[dataLength];
        in.readBytes(body);
        String id = new String(idBytes, Charset.forName("UTF-8"));
        list.add(new FileBlock(id, dataLength, body));
    }
}
