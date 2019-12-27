package pers.georgedon.susufile.fileserver.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import pers.georgedon.susufile.fileserver.handler.FileUploadServerHandler;

public class FileServerRequestInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new ObjectEncoder(),
                new ObjectDecoder(
                        ClassResolvers.weakCachingConcurrentResolver(null)),
                new FileUploadServerHandler());
    }
}
