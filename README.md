# Demo project for Spring Boot
## 集成
- 基础框架
  - spring boot（1.5.8）
  - lombok
- 安全框架
  - guard (待续)
- 数据库连接池
  - druid
- 数据库
  - mysql
- 缓存
  - redis
- 持久层工具
  - mybatis
- ci/devops
  - docker
  - jenkins (待续)
  - k8s (待续)
- 文档
  - swagger

## 使用说明
- 使用db_schema初始化数据库
- config/application-database.yml 配置数据库连接
- config/application-redis.yml 配置redis连接(或者在Mapper文件中去除cache)
- 驼峰转下划线：VO类使用 @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
- swagger http://localhost:8080/swagger-ui.html 配置文件：com.example.demo.config.SwaggerConfig
- druid监控 http://localhost:8080/druid/index.html 配置文件：config/application-druid.yml
- docker  将打包好的jar跟Dockerfile放置同一目录，输入命令docker build
