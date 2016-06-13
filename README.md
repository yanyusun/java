清搜平台JAVA工程
==================
# 服务端架构
<img src="http://114.215.239.181:9988/html/jiagoutu.png" />
==================
## 基础设施信息

服务器采用阿里ECS,用CentOS操作系统,挂载两个盘,挂载路径为:/media/disk_b.目前开发机器一台,IP为:114.215.239.181,部署有以下内容:

* Git私服,统一管理所有代码,采用SSH协议接入,端口为:6080

* Jenkins,持续化集成部署工具,采用账号/用户名接入,端口为:7070
    * dqys_dev_complie: 编译master分支
    * dqys_dev_deploy: 部署并重启tomcat9080(/mnt/tomcat8)
    * dqys_dev_slave_deploy: 部署并重启tomcat9081(/mnt/tomcat8_slave)
    * dqys_dev_res_deploy: 部署并重启tomcat8_res

* 一个Nginx代理+2个tomcat运行应用服务,采用循环算法,每个tomcat内部署所有应用服务(除资源服务外),端口为:nginx-9090,tomcat-9080/9081

* 一个tomcat运行资源服务,端口为:9988

* 一个tomcat运行现在的导航网站,端口为:80

* 一个redis集群,端口:6301-6305
    * 服务器路径:/etc/redis

* 两个单独redis服务,端口:6379/6380
    * 服务器路径:/etc/redis

* 一个rabbitmq服务,端口:5672,管理端口:15672


## 数据库信息信息

采用阿里的RDS服务,库名为:qs_plat

## 特殊服务停启命令汇总
* redis 用redis客户端连接实例,然后使用shutdown命令.
* rabbitmq 命令:/sbin/service rabbitmq-server start|stop
* git 命令:gitlab-ctl start|stop|restart
* jenkins 命令: sh /mnt/jenkins_start.sh

## 项目模块信息
### core.jar
封装系统通用功能,提供各中通用抽象
* 基本数据对象:基本接口控制器抽象/基本Dao/基本DTO/基本拦截器/基本实体对象/基本查询
* 数据库二级缓存的redis实现
* 系统常量:自定义请求头部/响应状态码及消息/系统配置类型/验证类型
* 拦截器:h5跨域拦截器/接口头部校验拦截器/JSONP拦截器
* 系统配置和区域的数据实体映射
* 通用工具类:
    * API字段翻译工具
    * 区域工具
    * 格式校验工具
    * Http请求工具
    * 请求响应工具
    * Redis操作工具
    * 请求头工具
    * 消息产生/消费工具
    * 签名工具
    * 请求流响应工具
    * 系统配置工具
    * 验证码生成工具
* mybatis框架配置
* 系统依赖配置
* 日志配置


### captcha.jar
验证码模块,提供图片验证码/短信验证码的相关功能

### auth-orm.jar
人员/权限相关的实体数据映射模块

### business-orm.jar
功能业务相关的实体数据映射模块

### auth-service.jar
人员/权限相关的业务逻辑服务模块

### business-service.jar
功能业务相关的业务逻辑服务模块

### auth-web.war
提供人员/权限/组织架构/角色等相关数据处理的rest接口

### business-web.war
提供业务逻辑/功能需求相关的数据处理rest接口

### resource-web.war
提供资源文件相关操作,如上传/下载/查看/分组/移动等处理的rest接口

### wms-web.war
系统后台管理相关的web应用,数据来源等都采用远程跨域调用其他模块的rest接口


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
* 目前资产包录入的实体已经建好,可以参考.路径: qs_business_orm/com.dqys.business.orm.dto

## 目前已经完成接口,postman链接
权限集合:https://www.getpostman.com/collections/77e4803f0787f1d4e7d1

资源集合:https://www.getpostman.com/collections/8d48298e631701246d14
