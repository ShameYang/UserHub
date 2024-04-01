drop table if exists user;

create table user
(
    id          bigint auto_increment comment '主键'
        primary key,
    user_name   varchar(256)                       not null comment '昵称',
    usr_account varchar(256)                       not null comment '账号',
    avatar_url  varchar(1024)                      null comment '头像',
    gender      tinyint                            null comment '性别 0-男 1-女',
    password    varchar(512)                       not null comment '密码',
    phone       varchar(128)                       null comment '电话',
    email       varchar(512)                       null comment '邮箱',
    user_status tinyint  default 0                 not null comment '用户状态 0-正常',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除'
)
    comment '用户';