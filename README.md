# project
该项目是一个基于分布式下的互联网电商项目
架构图如下：
![架构图](https://github.com/carpediemliu/project/blob/master/jiagou.png)
前端：原生JS，JQuey,EasyUI
后端Java
利用ActiveMQ做为消息中间件方便不同服务之间的相互调用
用到了如下框架：
1.dubbo:当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用	于提高机器利用率的资源调度和治理中心。

2.FastDFS：分布式文件系统，用于搭建一个图片服务器，专门保存图片。存储空间可以横向扩展，可以实现服务器的高可用。支持每个节点有备份机。

3.Redis集群：用于添加缓存，减少查询数据库的压力。

4.SolrCloud：用于实现搜索功能，快速高效。

5.Activemq：使用Activemq发送接收消息，通过消息队列实现商品同步。

6.Freemarker：FreeMarker是一个用Java语言编写的模板引擎，它基于模板来生成文本输出。FreeMarker与Web容器无关，即在Web运行时，它并不知道Servlet或HTTP。它不仅可以用作表现层的实现技术，而且还可以用于生成XML，JSP或Java 等。

7.MyCAT：一个彻底开源的，面向企业应用开发的“大数据库集群”支持事务、ACID、可以替代Mysql的加强版数据库，可以低成本的将现有的单机数据库和应用平滑迁移到“云”端，解决数据存储和业务规模迅速增长情况下的数据瓶颈问题。
