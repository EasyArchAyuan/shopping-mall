
### 安装Docker

docker的安装：*
*[https://www.runoob.com/docker/centos-docker-install.html](https://www.runoob.com/docker/centos-docker-install.html)**
<br/>

### 安装MySQL

1：执行如下命令拉取MySQL的Docker镜像

```bash
docker pull hub.c.163.com/library/mysql:latest
```

2：镜像拉取后，执行如下命令运行MySQL（注意：MYSQL_ROOT_PASSWORD中的密码需要与项目配置文件中的MySQL密码保持一致）

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d hub.c.163.com/library/mysql:latest
```

3：MySQL运行成功后，需要对MySQL的数据进行初始化

4：新建一个名为shopping的数据库，创建成功后双击该数据库创立连接，接着右击，选择**运行SQL文件**，运行项目根目录下的 *
*`shopping.sql`** 文件即可。

### 安装Redis

1：执行如下命令拉取Redis的Docker镜像

```bash
docker pull hub.c.163.com/library/redis:latest
```

2：执行如下命令运行Redis

```bash
docker run -d -p 6379:6379 --name redis hub.c.163.com/library/redis:latest
```

3：成功运行后，访问配置文件中填写的服务器ip地址即可 http://127.0.0.1:8866
