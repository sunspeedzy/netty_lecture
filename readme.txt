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

WebSocket 用来解决 HTTP 协议的不足
HTTP 是无状态的，即多次请求是无关的，所以使用cookie、session来解决HTTP协议数据传输时的状态问题。
HTTP 是基于请求和响应模式的，一定是先由 客户端向服务端发送请求，建立一个到服务端的连接，
     服务端收到请求后进行处理并构造response对象响应给客户端，当服务端把响应发送给客户端后，这个连接就断掉了，
     客户端再次发出请求需要重新建立连接
在HTTP1.1 中，客户端与服务端建立的连接可以保持一定的时间，在这个时间内客户端向服务端发送的请求可以复用这个连接
早期，客户端通过轮询可以实现聊天室的场景，但是消息不能即时从服务端发送到客户端，且客户端发送的大部分请求是浪费网络带宽的

WebSocket 可以实现客户端和服务器端的真正意义的长连接，长连接的建立需要客户端向服务端发起，
          长连接建立后客户端与服务器端对等，彼此之间可以发送消息，且消息中不用包含头信息，
          实现了服务器端向客户端的推送，也节省了带宽
详见 https://www.cnblogs.com/wade-luffy/p/6178989.html

zy.netty.fifthexample的代码是 10_Netty对WebSocket的支援 和 11_Netty实现服务器端与客户端的长连接通信 制作
webapp/test.html 是 11_Netty实现服务器端与客户端的长连接通信 制作
  用来展示如何用Netty编写WebSocket程序，客户端用编写HTML页面的方式制作。
  启动 MyServer后，通过 Open in Browser 打开 test.html，Server端会触发 handlerAdded 事件。
客户端长时间（如1.5小时）不与服务端交互，再次刷新 test.html，handlerAdded 会被触发，而 handlerRemoved 事件不会被触发。
客户端连接服务端后，短时间再刷新，会依次触发 handlerRemoved handlerAdded 两个事件。
  尝试在能联通的两台电脑上，分别运行 客户端 和 服务端 程序，然后断掉网络连接，看看服务端或客户端是否能够感知连接断掉。
以此体会 心跳机制 对长连接支持的必要性。

Google Protobuf
  进行RPC的数据传输，Google Protobuf使用的协议可以以更好的、体积更小的方式对数据进行编码和解码，即序列化和反序列化。
与 Apache Thrift 属于同领域的技术。

RMI：远程方法调用，跨机器的对象方法调用技术。只针对Java对象
RMI的本质就是实现在不同JVM之间的调用,它的实现方法就是在两个JVM中各开一个Stub和Skeleton，
二者通过socket通信来实现参数和返回值的传递。

RPC 与 RMI 类似，但很多 RPC 框架是跨语言的。RPC 编写模式类似：
1. 定义一个接口说明文件（文本文件，独立于编程语言）：描述了对象（结构体）、对象成员、接口方法等一系列信息。
2. 通过 RPC 框架所提供的编译器，将接口说明文件编译成具体语言文件
3. 在客户端与服务器端分别引入 RPC 编译器所生成的文件，即可像调用本地方法一样调用远程方法

protobuf 中的代码由 15_Protobuf集成Netty与多协议消息传递 制作

zy.netty.sixthexample的代码是 15_Protobuf集成Netty与多协议消息传递 制作


