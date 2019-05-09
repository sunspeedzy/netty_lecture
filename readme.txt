这个工程的代码，是《2018年最新深入netty 零基础到精通 张龙老师（完结）》的课程代码

查看端口的话程序，
windows上：以管理员身份运行 Git Bash，然后运行 netstat -b | grep  -A 1 "<IP>/<端口号>"
$ netstat -b | grep  -A 1 "8899"
  TCP    127.0.0.1:8899         zw_zhangyan01:56082    ESTABLISHED
 [java.exe]
  TCP    127.0.0.1:8899         zw_zhangyan01:56719    ESTABLISHED
 [java.exe]
--
  TCP    127.0.0.1:56082        zw_zhangyan01:8899     ESTABLISHED
 [java.exe]
--
  TCP    127.0.0.1:56719        zw_zhangyan01:8899     ESTABLISHED
 [java.exe]

Mac上：lsof -i:<端口号>
$ lsof -i:8899

zy.netty.firstexample的代码是 第5课 Netty执行流程分析与重要组件介绍 制作、第6课 Netty回调与Channel执行流程分析 修改

zy.netty.secondexample的代码是 第7课 Netty的Socket编程详解 制作

zy.netty.thirdexample的代码是 第8课 多客户端连接与通信、第9课 Netty读写检测机制与长连接要素 制作
  实现一个简单的聊天服务，多个客户端通过服务端连接进行聊天
  需求：
  客户端A 先连接服务端后，有其他客户端连接服务端，已连接的客户端会收到此客户端上线；
  A发消息，B、C能收到，A中也能显示

  第8课制作了服务端代码，第9课制作客户端代码

  首先启动 服务端程序，然后启动第一个客户端、第二个、第三个，查看服务端、各客户端输出，在客户端输入，再次查看输出

  如果客户端与服务器端已经建立了一个长连接，这时客户端挂掉，
  服务器端不会感知到这个连接已断掉，ServerHandler的handlerRemoved方法也不会被调用。这是TCP长连接编程的一个很重要的特点。
  通过心跳机制，服务端可以知道客户端是否断掉。

zy.netty.fourthexample的代码是 第9课 Netty读写检测机制与长连接要素 制作
  IdleStateHandler 在一段时间内服务端没有从客户端读、向客户端写或两者皆有的话，会触发 IdleStateEvent 事件
                   它是 Netty提供的 空闲检测 用的 Handler

