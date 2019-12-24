package pers.georgedon.susufile.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import pers.georgedon.susufile.handler.FileUploadServerHandler;

public class FileServer {

    private void init(){
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try
        {
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {

                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception
                        {
                            ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8),
                                    //按照回车换行符对数据包进行解码
                                    new LineBasedFrameDecoder(1024),
                                    new StringDecoder(CharsetUtil.UTF_8),
                                    new FileUploadServerHandler());
                        }
                    });
            ChannelFuture f=b.bind(6001).sync();
            System.out.println("Start file server at port : "+6001);
            f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}