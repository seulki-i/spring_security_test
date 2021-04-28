create table user_info
(
    user_id varchar(255) default '' not null
        primary key,
    user_name varchar(255) null,
    user_pw varchar(255) null,
    role varchar(255) null,
    is_enable tinyint(1) not null,
    department_name varchar(255) null
);

