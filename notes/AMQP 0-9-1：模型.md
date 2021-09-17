> 上一篇文章（[RabbitMQ：下载 & 安装](./RabbitMQ：下载 & 安装.md)）中，我们下载并且安装了RabbitMQ，并且成功注册了RabbitMQ服务。
>
> 本文我们将学习RabbitMQ中最基础、最重要的概念：AMQP 0-9-1协议模型。

# 0 前言

 要学好RabbitMQ，我们千万不要死记硬背那些调用API。而是要理解它的执行逻辑，即通讯协议。

RabbitMQ支持很多通讯协议，包括AMQP 0-9-1、AMQP 1.0、MQTT和STOMP等。其中，最重要、最常用的是AMQP 0-9-1（默认）。我们只需要充分理解这个协议，就能够解决日常工作中绝大部分RabbitMQ相关的问题。

AMQP（Advanced Message Queuing Protocol，高级消息队列协议）是二进制消息协议，即底层传输的消息是二进制数据。它的版本划分方式为*major-minor[-revision]*或*major.minor[.revision]*。AMQP 0-9-1是AMQP的0-9-1版本（major=0，minor=9，rivision=1）。

AMQP可以分成三个部分：

- 模型（AMQ Model）：定义了AMQP中的`Exchange`、`Message`和`Queue`等实体模型。

- 方法层（Functional Layer）：定义了实现实体模型相关功能的命令。
- 传输层（Transport Layer）：定义了实现方法层命令的底层二进制数据传输格式。

这三个部分之间起着层层递进的关系：

从本质上来看，AMQP 0-9-1定义了两个核心功能：

- 接收消息。
- 转发消息。



![AMQP0-9-1](AMQP 0-9-1：模型-img/AMQP0-9-1.png)

# 1 Message

【消息的格式/作用】

# 2 Exchange

【交换机的作用/工作原理】

# 3 Message Queue

【消息队列的作用/工作原理】

# 4 Binding

【绑定的作用/工作原理】

# 5 Connection & Channel

【连接/通道的作用】

# 6 Virtual Host

【虚拟主机的作用】