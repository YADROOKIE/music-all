create  table  `base_music` (
                                `id`  int  primary key  auto_increment,
                                `music_name`  char(128)  NOT NULL  COMMENT '音乐名称',
                                `type`        char(128)  NOT NULL  COMMENT '音乐类型',
                                `singer`      char(128)  NOT NULL  COMMENT  '歌手',
                                `cover`       varchar(255) NOT NULL COMMENT '音乐封面',
                                `description` varchar(1024)        default  '' ,
                                `lyrics`      text                 COMMENT '歌词',
                                `music_len`   char(10)  default  '' COMMENT '歌曲时长',
                                `file_path`   char(255) NOT NULL  COMMENT '存在本地的路径',
                                `create_at`   datetime default  now()
)engine = innodb , charset = utf8mb4;

create   table  `user_song_list`(
                                    `id` int primary key  auto_increment,
                                    `list_name` char(128)  NOT NULL  COMMENT '歌单名称',
                                    `user_id`   char(19)   NOT NULL  COMMENT '用户ID',
                                    `description` varchar(1024)  default '' COMMENT '描述',
                                    `create_at` datetime default now()
)engine = innodb , charset = utf8mb4;

create  table  `song_list_music`(
                                    `id` int primary key  auto_increment,
                                    `l_id` int NOT NULL  COMMENT '歌单ID',
                                    `m_id` int NOT NULL  COMMENT '音乐ID',
                                    `create_at` datetime default  now()
)engine = innodb , charset = utf8mb4;


create  table  `user_collect_music`(
                                       `id`  int  primary key  auto_increment,
                                       `user_id` char(19) not null  COMMENT '用户ID',
                                       `m_id` int  not null  COMMENT '音乐ID',
                                       `create_at` datetime default  now()
)engine = innodb , charset = utf8mb4;


create  table `play_record`
(
    `id`        int primary key auto_increment,
    `user_id`   char(19) not null COMMENT '用户ID',
    `m_id`      int      not null COMMENT '音乐ID',
    `create_at` datetime default now()
)engine = innodb , charset = utf8mb4;

create  table  `view_record`(
                                `id` int primary key  auto_increment,
                                `user_id` char(19) not null  COMMENT '用户ID',
                                `m_id` int  not null  COMMENT '音乐ID',
                                `create_at` datetime default  now()
)engine = innodb , charset = utf8mb4;