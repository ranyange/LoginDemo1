-- 注册用户表格
create table table_user(
	id integer primary key,
	username varchar2(20) unique,
	nickname varchar2(20),
	password varchar2(32),
	locked number(1) check(locked in(0,1))
);

create sequence  sqce_user start with 100;

insert into table_user values(sqce_user.nextval,'TestUser','TestUserDemo','test',1);

-- 联系人表
					--<th>姓名</th>
					--<th>生日</th>
					--<th>年龄</th>
					--<th>电话号码</th>
					--<th>邮编</th>
create table table_contact(
	id integer primary key,
	name varchar2(50),
	birthday date,
	age number(3),
	phone varchar2(12),
	zipcode varchar2(6)
);
--oracle 的内置函数 to_date();
create sequence sequence_contact start with 10000;

insert into table_contact values (sequence_contact.nextval,'daodao',sysdate,20,'13027783160','476400');
commit;

--删除表和序列用 drop来删除就好;

-- 查询所有用户 
select * from table_user;

--