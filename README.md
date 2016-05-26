清搜平台JAVA工程
==================
# 服务端架构
<img src="http://114.215.239.181:9988/html/jiagoutu.png" />
==================
## 基础设施信息

服务器采用阿里ECS,用CentOS操作系统,挂载两个盘,挂载路径为:/media/disk_b.目前开发机器一台,IP为:114.215.239.181,部署有以下内容:

* Git私服,统一管理所有代码,采用SSH协议接入,端口为:6080

* Jenkins,持续化集成部署工具,采用账号/用户名接入,端口为:7070

* 一个Nginx代理+2个tomcat运行应用服务,采用循环算法,每个tomcat内部署所有应用服务(除资源服务外),端口为:nginx-9090,tomcat-9080/9081

* 一个tomcat运行资源服务,端口为:9988

* 一个tomcat运行现在的导航网站,端口为:80

* 一个redis集群,端口:6301-6305

* 两个单独redis服务,端口:6379/6380

* 一个rabbitmq服务,端口:5672,管理端口:15672


## 数据库信息信息

采用阿里的RDS服务,库名为:qs_plat

## 各种账号汇集
* 阿里ECS账号:root  Du0&qIng^ys@2016
* 阿里RDS账号:进阿里控制台
* git管理账号:root  dUo&q1ng@2016
* jenkins管理账号:Pan   pan@Qs_2016
* rabbitmq管理账号:qsrabbit     q_s4Rabbit@2016

## 特殊服务停启命令汇总
* redis 用redis客户端连接实例,然后使用shutdown命令.
* rabbitmq 命令:/sbin/service rabbitmq-server start|stop
* git 命令:gitlab-ctl start|stop|restart
* jenkins 命令: sh /mnt/jenkins_start.sh

## 项目模块信息
core.jar

captcha.jar

auth-orm.jar        business-orm.jar

auth-service.jar    business-service.jar

auth-web.war        business-web.war        resource-web.war    wms-web.war

具体依赖见各模块的pom

## 开发注意点
* 核心基于灵活的配置和nosql的全局存储,新加一类配置需要增加一个SysPropertyTypeEnum的枚举项,所有操作都再wms模块中
* 目前消息队列只有一个发送邮件 后期需要增加队列的在spring-core中增加配置
* 系统数据模型引入乐观锁机制,每个表都需要有6个基本字段,实例类基于抽象的BaseModel.使用工具映射,需要根据本地环境操作配置文件,且每次好映射后需要修改mapper.xml
* 建议controller和service之间的交互统一采用ServiceResult或者PagingResult,能满足绝大部分需求.
* 提供了跨域支持,使用CrossOriginIntercetpor实现.
* 系统配置项的初始化和消息的消费目前写再wms模块中,根据需要调整.
* 跟前端交互统一使用json格式.部分新增操作(参数多的)也建议使用json数据格式.
* 已经实现数据库二级缓存,根据需要开启

## 目前已经完成接口,postman链接
权限集合:https://www.getpostman.com/collections/77e4803f0787f1d4e7d1

资源集合:https://www.getpostman.com/collections/8d48298e631701246d14
