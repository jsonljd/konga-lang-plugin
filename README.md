# konga-lang-plugin
konga的语言包，中文翻译

#   前言

>   Kong([KONG Admin API](http://getkong.org))已经有好多公司在用，[Konga](https://github.com/pantsel/konga)是管理Kong的管理工具之一,感觉也是比较好用，由于Konga是由国外牛人开发的，所以并没有提供过多的语言支持，并且翻查源码都是写死的，为了支持国内用户方便使用，特开发本语言插件包，希望有用，如果喜欢请star 一下。

#   TODO
[   √  ]  基本框架

[   √  ]  表层的语言翻译 

[       ]  细节的语言翻译

[       ]  多个国语言包

#   预览

![图1](https://github.com/jsonljd/konga-lang-plugin/blob/master/doc/resources/1.png?raw=true)

![图2](https://github.com/jsonljd/konga-lang-plugin/blob/master/doc/resources/2.png?raw=true)

![图3](https://github.com/jsonljd/konga-lang-plugin/blob/master/doc/resources/3.png?raw=true)



#   设计原则性
*   尽量不损坏Konga源代码，保证Konga的官方版本升级的可用性。

*   原则上不过度翻译Kong的概念术语 consumers、 upstreams、services、routes 等，顾只处理表层的翻译，深层的就不做过多的处理，避免过度翻译，加大使用难度。

#   使用方法

>     由于Konga的作者采用两种使用方式，安装nodejs部署运行和docker方式运行，顾使用方法也采用两个

## 直接运行（使用前需要安装java，jdk或jre）
```
cd /usr/konga                   #konga根目录和assets同级
wget https://github.com/jsonljd/konga-lang-plugin/releases/download/1.0.0/deploy.zip                      #下载语言包
unzip deploy.zip                #解压语言包
npm stop                        #如果已经运行，需要先停止
cp -R assets assets_bak         #保存原来的web文件，如果有问题可以恢复
rm -rf .tmp                     #删除node启动的临时文件夹
cd ./lang                       #进入语言包
sh ./start.sh start             #执行语言包处理  执行的情况可以查看logs文件夹日志
cd ..                           #返回上一层
npm start                       #启动konga
```

##  docker方式
>   [查看语言包镜像仓库](https://hub.docker.com/r/jsonljd/konga-lang-plugin)
```
mkdir dockertmp                                   #创建一个临时目录
cd dockertmp
docker ps -a                                      #查找konga的容器id
docker stop {konga容器id}                         #停止正在运行的容器
docker cp {konga容器id}:/app/assets ./            #将容器的文件复制到本地 

docker pull jsonljd/konga-lang-plugin:latest      #拉取语言插件镜像
docker run -d --name konga-lang-plugin \
 -v ./assets:/app/assets \
 jsonljd/konga-lang-plugin                   #运行镜像，需要设置逻辑目录

docker cp ./assets {konga容器id}:/app      #覆盖成功后即可
docker start {konga容器id}                 #重启容器
```
