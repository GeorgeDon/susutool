package pers.georgedon.susufile.fileclient.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import pers.georgedon.susufile.fileclient.handler.FileUploadClientHandler;

public class FileClient {

    public void upload() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new ObjectEncoder());
                            ch.pipeline()
                                    .addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
                            ch.pipeline().addLast(new FileUploadClientHandler());
                        }
                    });
            ChannelFuture f = b.connect("127.0.0.1", 6001).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
