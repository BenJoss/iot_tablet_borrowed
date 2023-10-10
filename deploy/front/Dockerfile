# 使用Nginx作为生产环境的基础镜像
FROM nginx:stable-alpine as production-stage


# 覆盖官方nginx镜像默认的配置
 COPY default.conf /etc/nginx/conf.d/default.conf

# 将项目根目录下dist文件夹下的所有文件复制到镜像中 /usr/share/nginx/html/ 目录下
COPY dist/ /usr/share/nginx/html/

# 暴露端口
EXPOSE 8282

# 启动Nginx服务器
CMD ["nginx", "-g", "daemon off;"]