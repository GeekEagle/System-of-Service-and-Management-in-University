
CREATE LOGIN Student
    WITH PASSWORD = 'student1001'
GO

CREATE USER Stu1 FOR LOGIN Student
GO
use dbkeshe; 
create table 教师信息表
(
    TeaID    char(10) not null
        constraint 教师信息表_pk
            primary key,
    Teaname  char(20) not null,
    Teapro   char(30) not null,
    Teage    int
        constraint ck_Teage
            check ([Teage] > 0 AND [Teage] < 140),
    Iden     char(10)
        constraint ck_Iden
            check ([Iden] = '群众' OR [Iden] = '团员' OR [Iden] = '积极分子' OR [Iden] = '党员' OR [Iden] = '预备党员'),
    Teacol   char(30),
    Workyear int,
    Title    char(10)
        constraint ck_Title
            check ([Title] = '讲师' OR [Title] = '助理' OR [Title] = '副教授' OR [Title] = '教授' OR [Title] = '院士'),
    constraint ck_Workyear
        check ([Workyear] < [教师信息表].[Teage])
)
go

create table 课程信息表
(
    CourseID   char(10) not null
        constraint 课程信息表_pk
            primary key,
    CourseName char(30) not null,
    TeacherID  char(10)
        constraint 课程信息表_教师信息表_Teanum_fk
            references 教师信息表,
    counts     int,		--选修人数
    Credit     float      not null,
    Classroom  char(3)
)
go

CREATE TABLE 班级信息表
(
    Clanum char(10) PRIMARY KEY,
    Teapro char(30),
    Teacol char(30),
    Account int
);
go

create table 学生信息表
(
    StuID    char(10) not null
        constraint 学生信息表_pk
            primary key,
    Stuname  char(20) not null,
    Stupro   char(30) not null,
    Stuage   int,
    Iden     char(10)
        constraint ck_Idenw
            check ([Iden] = '群众' OR [Iden] = '团员' OR [Iden] = '积极分子' OR [Iden] = '党员' OR [Iden] = '预备党员'),
    Teanum   char(10)
        constraint 学生信息表_教师信息表_Teanum_fk
            references 教师信息表,
    Clanum   char(10)
        constraint 学生信息表_班级信息表_Teanum_fk
            references 班级信息表,
    College  char(30),
    Grade    char(10) not null,
    Cond     char(10),
    Location char(20),
    Stuvol   float,
    Tuition  int,
    Contui   real,
    gpa      float
)
go

create table 学生老师信息表
(
    StuID char(10) not null PRIMARY KEY,
    Stuname char(20) not null,
    Assistname char(20),
    Turtorname char(20),
    ClassTeaname char(20),
    Clanum char(10) not null,
    College char(30) not null,
    --班级号 学院
    FOREIGN KEY(StuID) REFERENCES 学生信息表(StuID)
);
go

create table 财务状态表
(
    BudID     char(10) not null
        primary key,
    StuID     char(10) not null
        references 学生信息表,
    Stuname   char(20) not null,
    Stupro    char(30) not null,
    Grage     char(10),
    Condition char(10),
    Contui    bit,
    Tuition   int
        constraint ck_Tuition
            check ([Tuition] >= 0),
    Tuidate   date
);
go

create table 志愿信息表
(
    VolID   char(10) not null
        primary key,
    StuID   char(10)
        references 学生信息表,
    Stuname char(10) not null,
    Stupro  char(10) not null,
    Volpro  char(10),
    Voldate date,
    Stuvol  float
        constraint ck_Stuvol
            check ([Stuvol] >= 0)
)
go

create table 获奖记录表
(
    Awanum   char(30) not null
        primary key,
    StuID    char(10)
        references 学生信息表,
    Stuname  char(10) not null,
    Stupro   char(30) not null,
    Awardate date,
    Awarea   char(30),
    Awagra   char(30)
)
go

create table 处分记录表
(
    Punum   char(10) not null
        primary key,
    StuID   char(10)
        references 学生信息表,
    Stuname char(10) not null,
    Stupro  char(30) not null,
    Pundate date,
    Punarea char(30),
    Pungra  char(10)
)
go

create table 选课信息表
(
    StuID    char(10) not null
        references 学生信息表,
    CourseID char(10) not null
        references 课程信息表,
    Grade    int
        constraint ck_Grade
            check ([Grade] >= 0 AND [Grade] <= 100),
    Credit   float    not null,
    gpa      float,
    primary key (StuID, CourseID)
);
go

create table 操作记录表
(
    Alpnum     char(10) not null
        primary key,
    Persontype char(10) not null,
    Aplpro     char(30),
    Apltable   char(30)
        constraint ck_table
            check ([Apltable] = '获奖记录表' OR [Apltable] = '志愿信息表' OR [Apltable] = '财务状态表' OR [Apltable] = '处分记录表' OR
                   [Apltable] = '班级信息表' OR [Apltable] = '选课信息表' OR [Apltable] = '课程信息表' OR [Apltable] = '教师信息表' OR
                   [Apltable] = '学生信息表'),
    Aplcon     char(30),
    Num        char(10),
    Date       date,
    Limits     bit,
    Accomplish bit,
    Result     char(30)
)
go


-- auto-generated definition
create table 登录信息表
(
    userid   char(10) not null
        constraint 登录信息_pk
            primary key nonclustered,
    password char(20) not null
        constraint ck_password
            check (len([password]) >= 6 AND len([password]) < 20),
    role     char(10) not null
        constraint ck_role
            check ([role] = 'manager' OR [role] = 'teacher' OR [role] = 'student')
)
go

INSERT
INTO 教师信息表
VALUES('100','张丽英','计算机',45,'党员','理学院',15,'副教授');

INSERT
INTO 课程信息表
VALUES('C1020','数据库系统','100',100,3,'103');

INSERT
INTO 选课信息表
VALUES('1','C1020',90,0,0);

INSERT
INTO 班级信息表
VALUES('2019001','计算机','信息学院',1000);

INSERT
INTO 学生信息表
VALUES('1','何浩','计算机',20,'团员','100','2019001','信息学院','2019级','正常修读','北京',30,5500,1,0);

INSERT
INTO 财务状态表
VALUES('00001','1','何浩','计算机','2019级','正常修读',1,5500,'2021/8/26');

INSERT
INTO 财务状态表
VALUES('00002','1','何浩','计算机','2019级','正常修读',1,5500,'2021/8/26');

INSERT
INTO 志愿信息表
VALUES('Vol001','1','何浩','计算机','防疫志愿','2021/9/26',30);

INSERT
INTO 获奖记录表
VALUES('Award001','1','何浩','计算机','2021/9/26','程序设计','二等奖');

INSERT
INTO 处分记录表
VALUES('Punish001','1','何浩','计算机','2021/12/10','违规','通报批评');



INSERT INTO 登录信息表 (userid, password, role) VALUES (N'1         ', N'123456              ', N'student   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'2         ', N'123456              ', N'student   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'100       ', N'12345678            ', N'teacher   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'3         ', N'123456              ', N'student   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'101       ', N'12345678            ', N'teacher   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'500       ', N'19260817            ', N'manager   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'501       ', N'19260817            ', N'manager   ');
INSERT INTO 登录信息表 (userid, password, role) VALUES (N'2019011081', N'2019011081123       ', N'student   ');


GO
create view 教师信息表_view
as select * from 教师信息表;
go

create view 处分记录表_view
as select * from 处分记录表;
go

create view 学生信息表_view
as select * from 学生信息表;
go

create view 志愿信息表_view
as select * from 志愿信息表;
go

create view 操作记录表_view
as select * from 操作记录表;
go

create view 班级信息表_view
as select * from 班级信息表;
go

create view 获奖记录表_view
as select * from 获奖记录表;
go

create view 课程信息表_view
as select * from 课程信息表;
go

create view 财务状态表_view
as select * from 财务状态表;
go

create view 选课信息表_view
as select * from 选课信息表;
go

GO
CREATE VIEW 志愿时长统计_view(学生学号,志愿总时长)
as
SELECT StuID,SUM(Stuvol) FROM 志愿信息表
GROUP BY StuID
go

--drop view 教师信息表_view
--drop view 处分记录表_view
--drop view 学生信息表_view
--drop view 志愿信息表_view
--drop view 操作记录表_view
--drop view 班级信息表_view
--drop view 获奖记录表_view
--drop view 课程信息表_view
--drop view 财务状态表_view
--drop view 选课信息表_view



CREATE ROLE Student
GRANT SELECT ON 学生信息表
    TO Student
GRANT SELECT ON 选课信息表
    TO Student
GRANT SELECT ON 志愿信息表
    TO Student
GRANT SELECT ON 志愿时长统计_view
    TO Student
GRANT SELECT ON 获奖记录表
    TO Student
GRANT SELECT ON 处分记录表
    TO Student
GRANT SELECT ON 课程信息表
    TO Student
GRANT UPDATE ON 选课信息表
    TO Student
GRANT UPDATE,INSERT ON 操作记录表(Alpnum,Persontype,Aplpro,Apltable,Num,Date)
    TO Student
GRANT SELECT ON 操作记录表
    TO Student




CREATE ROLE Teacher
GRANT SELECT,UPDATE,INSERT ON 教师信息表
    TO Teacher
GRANT SELECT,UPDATE,INSERT ON 课程信息表
    TO Teacher
GRANT SELECT ON 选课信息表
    TO Teacher
GRANT UPDATE,INSERT ON 操作记录表(Alpnum,Persontype,Aplpro,Apltable,Num,Date)
    TO Teacher
GRANT SELECT ON 操作记录表
    TO Teacher

CREATE ROLE Manager
GRANT ALL PRIVILEGES ON 学生信息表
    TO Manager
GRANT ALL PRIVILEGES ON 课程信息表
    TO Manager
GRANT ALL PRIVILEGES ON 选课信息表
    TO Manager
GRANT ALL PRIVILEGES ON 班级信息表
    TO Manager
GRANT ALL PRIVILEGES ON 操作记录表
    TO Manager
GRANT ALL PRIVILEGES ON 财务状态表
    TO Manager
GRANT ALL PRIVILEGES ON 志愿信息表
    TO Manager
GRANT ALL PRIVILEGES ON 获奖记录表
    TO Manager
GRANT ALL PRIVILEGES ON 处分记录表
    TO Manager
GRANT ALL PRIVILEGES ON 未缴费学生
    TO Manager

GO
CREATE TRIGGER 志愿时长同步
    ON 志愿信息表
    AFTER UPDATE
    AS
    DECLARE @志愿时长 FLOAT	--  @ 表示局部变量,  @@ 表示全局变量
    SET @志愿时长=(SELECT Stuvol FROM inserted)
    UPDATE 学生信息表
    SET 学生信息表.Stuvol=@志愿时长+学生信息表.Stuvol
    where 学生信息表.StuID=StuID;
GO

go


create trigger 成绩_trigger_insert on 选课信息表  after insert as
begin
    declare @pronew int;
    declare @id char(10);
    set @pronew = (select Grade from inserted);
    set @id = (select StuID from inserted);
    begin
        update 选课信息表 set 选课信息表.gpa=dbo.get_gpa(@pronew) where StuID=@id;
        update 学生信息表 set 学生信息表.gpa=dbo.stu_gpa(@id) where StuID=@id;
    end
end
go

create trigger 成绩_trigger_update on 选课信息表  after update as
    begin
        declare @pronew int;
        declare @proold int;
        declare @id char(10);
        set @proold = (select Grade from deleted);
        set @pronew = (select Grade from inserted);
        set @id = (select StuID from inserted);
        if (@pronew != @proold)
            begin
                update 选课信息表 set 选课信息表.gpa=dbo.get_gpa(@pronew) where @pronew<>@proold;
                update 学生信息表 set 学生信息表.gpa=dbo.stu_gpa(@id) where StuID=@id;
            end
    end


--计算绩点：
go
create function get_gpa(@grade int) returns float
as begin
    if (@grade>=50)
        begin
            return @grade/10.0-5;
        end
    return 0;
end
go
drop function get_gpa
--计算总绩点
--drop function stu_gpa
go
create function stu_gpa(@stuid char(10)) returns float
as begin
    declare @gpa_sum float;
    declare @credit_sum int;
    declare @gpa float;
    set @gpa_sum=(
        select sum(gpa*Credit)
        from 选课信息表
        where StuID=@stuid)
    set @credit_sum=(
        select sum(Credit)
        from 选课信息表
        where StuID=@stuid
    )
    if(@credit_sum=0)
        set @gpa=0;
    else
        set @gpa=@gpa_sum/@credit_sum
    return @gpa;
end
go



drop proc 发起申请
go
create proc 发起申请(@date char(10),@iden char(10),@id char(10),@Aplpro char(30),@con char(30))
as begin
    declare @alpn char(10)
    set @alpn = (select distinct count(*)  from 操作记录表)+1
    insert into 操作记录表 values (@alpn,@iden,@Aplpro,null,@con,@id,@date,NULL,NULL,NULL);
end

    drop proc 接受申请
go

create proc 接受申请(@alpn char(10))
as begin
    declare @apl char(30);
    declare @id char(10);
    declare @jud char(10);
    declare @con char(10);
    set @apl=(select Aplpro from 操作记录表_view where alpnum=@alpn)
    set @id=(select Num from 操作记录表_view where alpnum=@alpn)
    set @jud=(select Result from 操作记录表_view where alpnum=@alpn)
    set @con=(select Aplcon from 操作记录表_view where Alpnum=@alpn)
    if(@con not in ('成功','拒绝'))
        begin
            if (@apl='退学')
                begin
                    update 学生信息表 set Cond='退学' where stuid=@id;
                    if exists (select * from 处分记录表_view where 处分记录表_view.StuID=@id)
                        delete from 处分记录表 where StuID=@id;
                    if exists (select * from 志愿信息表_view where 志愿信息表_view.StuID=@id)
                        delete from 志愿信息表 where StuID=@id;
                    if exists (select * from 获奖记录表_view where 获奖记录表_view.StuID=@id)
                        delete from 获奖记录表 where StuID=@id;
                    if exists (select * from 财务状态表_view where 财务状态表_view.StuID=@id)
                        delete from 财务状态表 where StuID=@id;
                    if exists (select * from 选课信息表_view where 选课信息表_view.StuID=@id)
                        delete from 选课信息表 where StuID=@id;
                    delete from 登录信息表 where userID=@id;
                end
            if (@apl='休学')
                update 学生信息表 set Cond='休学' where stuid=@id;
            if (@apl='交学费')
                begin
                    update 财务状态表 set Contui=1 where StuID=@id;
                    update 学生信息表 set Contui=1 where StuID=@id;
                end

            if (@apl='毕业')
                update 学生信息表 set Cond='毕业' where stuid=@id;
            if (@apl='转专业')
                begin
                    update 学生信息表 set Stupro=@con where StuID=@id;
                end
            update 操作记录表
            set Result='成功',Limits=1,Accomplish=1
            where Alpnum=@alpn;
        end
end
go
create proc 拒绝申请(@alpn char(10))
as begin
    update 操作记录表
    set Result='拒绝',Limits=0,Accomplish=1
    where Alpnum=@alpn
end

go
create view 查询操作记录表_view (申请编号,申请项目,申请日期,是否允许,是否完成,申请结果)
as select Alpnum,Aplpro,Date,Limits,Accomplish,Result
   from 操作记录表
go


go
create trigger 选课人数限制_trigger
    on 选课信息表
    after insert
    as
    declare @course char(10),@num int;
    select @course=CourseID  from inserted;
    select @num=count(*) from 课程信息表,选课信息表
    where 课程信息表.CourseID=选课信息表.CourseID and 选课信息表.CourseID=@course
    if (@num>100)
        begin
            print '选课人数已满'
            rollback transaction
        end
go

go
create proc 获奖插入(@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    declare @alpn char(30)
    set @alpn = (select count(*) from 获奖记录表_view)+1
    insert into 获奖记录表 values (@alpn,@id,@name,@pro,@date,@area,@gra);
end
go--gengxin
create proc 处分插入(@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    declare @alpn char(10)
    set @alpn = (select max(Punum) from 处分记录表_view)+1
    insert into 处分记录表 values (@alpn,@id,@name,@pro,@date,@area,@gra);
end
go
create proc 获奖删除(@apln char(10))
as begin
    delete  from 获奖记录表 where Awanum=@apln;
end
go
create proc 处分删除(@apln char(10))
as begin
    delete from 处分记录表 where Punum=@apln;
end
go
create proc 获奖更新(@apln char(10),@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    delete from 获奖记录表 where Awanum=@apln;
    insert into 获奖记录表 values (@apln,@id,@name,@pro,@date,@area,@gra);
end
go
create proc 处分更新(@apln char(10),@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    delete from 处分记录表 where Punum=@apln;
    insert into 处分记录表 values (@apln,@id,@name,@pro,@date,@area,@gra);
end
create clustered
index index_学生 on 学生信息表(Cond)
create proc 插入学生(@id char(10),@name char(20),@pro char(30),@age int,@iden char(10),@teanum char(10),@class char(10),@college char(30),@grade char(10),@cond char(10),@loc char(20),@vol float,@tui int,@contui real)
as begin
    insert into 学生信息表 values (@id,@name,@pro,@age,@iden,@teanum,@class,@college,@grade,@cond,@loc,@vol,@tui,@contui,NULL);
    insert into 学生老师信息表 values (@id,@name,NULL,@teanum,NULL,@class,@college);
end
drop proc 插入学生
go
--create proc 插入学生(@id char(10),@name char(20),@pro char(30),@age int,@iden char(10),@teanum char(10),@class char(10),@college char(30),@grade char(10),@cond char(10),@loc char(20),@tui int,@contui real)
create proc 插入学生(@id char(10),@name char(20),@pro char(30),@age int,@iden char(10),@teaname char(10),@class char(10),@college char(30),@grade char(10),@cond char(10),@loc char(20),@tui int,@contui real)
    as begin
        DECLARE @teanum char(10)
        set @teanum = (select TeaID from 教师信息表 where Teaname = @teaname)
                insert into 学生信息表 values (@id,@name,@pro,@age,@iden,@teanum,@class,@college,@grade,@cond,@loc,NULL,@tui,@contui,NULL);
                insert into 学生老师信息表 values (@id,@name,NULL,@teaname,NULL,@class,@college);
                insert into 登录信息表 values (@id,rtrim(@id)+'123456','student');
    end
go

create proc 志愿插入(@stuid char(10),@name char(30),@stupro char(30),@volpro char(30),@date char(30),@time float)
as begin
    declare @alpn char(30)
    set @alpn = (select max(volid) from 志愿信息表_view)+1
    insert into 获奖记录表 values (@alpn,@stuid,@name,@stupro,@volpro,@date,@time);
end
go

INSERT
INTO 教师信息表
VALUES('100','张丽英','计算机',45,'党员','理学院',15,'副教授'),
      ('101','张a','计算机',47,'党员','信息科学与工程学院',14,'讲师'),
      ('102','王a','计算机',50,'党员','信息科学与工程学院',20,'教授'),
      ('103','黄a','计算机',40,'党员','信息科学与工程学院',8,'教授'),
      ('104','左a','计算机',44,'党员','信息科学与工程学院',9,'教授'),
      ('105','连a','计算机',39,'党员','信息科学与工程学院',11,'副教授'),
      ('106','鲁a','计算机',39,'党员','信息科学与工程学院',12,'副教授'),
      ('107','范a','计算机',45,'党员','信息科学与工程学院',10,'讲师'),
      ('108','张a','计算机',43,'党员','信息科学与工程学院',15,'讲师'),
      ('109','吴a','计算机',40,'党员','信息科学与工程学院',15,'讲师'),
      ('130','朱a','计算机',47,'党员','信息科学与工程学院',10,'讲师'),
      ('110','赵a','数学',40,'党员','理学院',15,'讲师'),

      ('111','戴a','数学',46,'党员','理学院',15,'副教授'),
      ('112','王aa','物理',40,'党员','理学院',15,'教授'),

      ('113','赵aa','英语',40,'党员','外国语学院',15,'教授'),
      ('114','连a','英语',39,'党员','外国语学院',15,'讲师'),

      ('115','高b','油气井工程',40,'党员','石油工程学院',15,'院士'),
      ('116','张bb','工程力学',40,'党员','石油工程学院',15,'教授'),

      ('117','王cc','会计',38,'党员','经济管理学院',5,'讲师'),
      ('118','孙nn','金融',41,'党员','经济管理学院',15,'教授'),

      ('119','李mm','新能源',40,'党员','新能源与材料学院',15,'讲师'),
      ('120','崔gg','材料',40,'党员','新能源与材料学院',15,'教授'),

      ('121','陈yy','地质学',40,'党员','地球科学学院',15,'副教授'),
      ('122','刘ii','地质资源与地质工程',40,'党员','地球科学学院',15,'教授')

--delete from 教师信息表





INSERT
INTO 课程信息表
VALUES('C1020','数据库系统','100',100,3,'103'),
      ('C1021','计算机组成原理','102',105,3,'417'),
      ('C1022','操作系统','103',60,3,'214'),
      ('C1023','编译原理','130',100,3,'109'),
      ('D2000','概率论与数理统计','110',90,3,'306'),
      ('D2001','离散数学','109',66,3,'502'),
      ('D2002','线性代数','111',90,4,'113'),
      ('D2003','大学物理','112',90,4,'509'),
      ('D2004','学术英语','113',64,6,'612'),
      ('D2005','通用英语','114',55,6,'314'),
      ('S1000','油气井工程','115',30,3,'314'),
      ('S1001','工程力学','116',31,3,'416'),
      ('J1000','会计学','117',30,3,'114'),
      ('J1001','金融学','118',45,3,'514'),
      ('N1000','能源与材料','119',30,3,'314'),
      ('N1001','材料力学','120',30,3,'304'),
      ('Z1000','地质学','121',39,3,'314'),
      ('Z1001','地质工程','122',34,3,'114')
--delete 课程信息表


INSERT
INTO 选课信息表
VALUES('1','C1020',90,0,0);


INSERT
INTO 班级信息表
VALUES('计算机19-1','计算机','信息科学与工程学院',23),
      ('计算机19-2','计算机','信息科学与工程学院',24),
      ('计算机19-3','计算机','信息科学与工程学院',20),
      ('计算机19-4','计算机','信息科学与工程学院',23),

      ('数学19-1','数学','理学院',23),
      ('物理19-1','物理','理学院',23),

      ('英语19-1','英语','外国语学院',23),
      ('英语19-2','英语','外国语学院',23),

      ('钻井19-1','钻井','石油工程学院',23),
      ('油气19-1','油气运输','石油工程学院',23),

      ('会计19-1','会计','经济管理学院',23),
      ('会计19-2','会计','经济管理学院',23),

      ('材料19-1','材料','新能源与材料学院',23),

      ('地质19-1','地质勘探','地球科学学院',23)

--delete from 班级信息表

INSERT
INTO 学生信息表
VALUES('1','何浩','计算机',20,'团员','100','计算机19-1','信息科学与工程学院','2019级','正常修读','北京',30,5500,1,0),
      ('2','何二','计算机',20,'群众','101','计算机19-2','信息科学与工程学院','2019级','正常修读','北京',40,5500,1,0),
      ('3','何三','计算机',20,'积极分子','102','计算机19-3','信息科学与工程学院','2019级','正常修读','北京',50,5500,1,0),
      ('4','何四','计算机',20,'党员','103','计算机19-4','信息科学与工程学院','2019级','正常修读','北京',60,5500,1,0),
      ('5','何五','计算机',20,'预备党员','104','计算机19-1','信息科学与工程学院','2019级','正常修读','北京',130,5500,1,0),
      ('6','何六','计算机',20,'团员','105','计算机19-2','信息科学与工程学院','2019级','正常修读','北京',10,5500,1,0),
      ('7','何七','计算机',20,'团员','106','计算机19-3','信息科学与工程学院','2019级','正常修读','北京',30,5500,1,0),
      ('8','何八','计算机',20,'团员','107','计算机19-4','信息科学与工程学院','2019级','正常修读','北京',30,5500,1,0),
      ('9','何九','计算机',20,'团员','108','计算机19-1','信息科学与工程学院','2019级','正常修读','北京',30,5500,1,0),
      ('10','何十','计算机',20,'团员','109','计算机19-2','信息科学与工程学院','2019级','正常修读','北京',30,5500,1,0),

      ('20','张零','数学',20,'预备党员','111','数学19-1','理学院','2019级','正常修读','北京',30,5500,1,0),
      ('21','张一','物理',20,'团员','112','物理19-1','理学院','2019级','正常修读','北京',30,5500,1,0),

      ('30','李零','英语',20,'团员','113','英语19-1','外国语学院','2019级','正常修读','北京',30,5500,1,0),
      ('31','李一','英语',20,'团员','114','英语19-2','外国语学院','2019级','正常修读','北京',30,5500,1,0),

      ('40','赵零','钻井',20,'团员','115','钻井19-1','石油工程学院','2019级','正常修读','北京',30,5500,1,0),
      ('41','赵一','油气运输',20,'团员','116','油气19-1','石油工程学院','2019级','正常修读','北京',30,5500,1,0),

      ('50','王零','会计',20,'团员','117','会计19-1','经济管理学院','2019级','正常修读','北京',30,5500,1,0),
      ('51','王一','会计',20,'团员','118','会计19-2','经济管理学院','2019级','正常修读','北京',30,5500,1,0),

      ('60','吴零','材料',20,'团员','119','材料19-1','新能源与材料学院','2019级','正常修读','北京',30,5500,1,0),

      ('70','范零','地质勘探',20,'团员','121','地质19-1','地球科学学院','2019级','正常修读','北京',30,5500,1,0)
--delete from 学生信息表


--学生老师信息表
INSERT
INTO 学生老师信息表
VALUES('1','何浩','代丽莎','王a','吴卫江','计算机19-1','信息科学与工程学院'),
      ('2','何二','代丽莎','王a','连a','计算机19-2','信息科学与工程学院'),
      ('3','何三','代丽莎','王a','鲁a','计算机19-3','信息科学与工程学院'),
      ('4','何四','代丽莎','王a','张a','计算机19-4','信息科学与工程学院'),
      ('5','何五','代丽莎','王a','吴卫江','计算机19-1','信息科学与工程学院'),
      ('6','何六','代丽莎','王a','连a','计算机19-2','信息科学与工程学院'),
      ('7','何七','代丽莎','王a','鲁a','计算机19-3','信息科学与工程学院'),
      ('8','何八','代丽莎','王a','张a','计算机19-4','信息科学与工程学院'),
      ('9','何九','代丽莎','王a','吴卫江','计算机19-1','信息科学与工程学院'),
      ('10','何十','代丽莎','王a','连a','计算机19-2','信息科学与工程学院'),

      ('20','张零','张丽莎','戴a','王aa','数学19-1','理学院'),
      ('21','张一','张丽莎','王aa','戴a','物理19-1','理学院'),

      ('30','李零','李丽莎','赵aa','连a','英语19-1','外国语学院'),
      ('31','李一','李丽莎','连a','赵aa','英语19-2','外国语学院'),

      ('40','赵零','赵丽莎','高b','张bb','钻井19-1','石油工程学院'),
      ('41','赵一','赵丽莎','张bb','高b','油气19-1','石油工程学院'),

      ('50','王零','王丽莎','王cc','孙nn','会计19-1','经济管理学院'),
      ('51','王一','王丽莎','孙nn','王cc','会计19-2','经济管理学院'),

      ('60','吴零','吴丽莎','李mm','崔gg','材料19-1','地球科学学院'),

      ('70','范零','范丽莎','陈yy','刘ii','地质19-1','新能源与材料学院')



--delete 学生老师信息表


INSERT
INTO 财务状态表
VALUES('00001','1','何浩','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00002','2','何二','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00003','3','何三','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00004','4','何四','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00005','5','何五','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00006','6','何六','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00007','7','何七','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00008','8','何八','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00009','9','何九','计算机','2019级','正常修读',1,5500,'2021/8/26'),
      ('00010','10','何十','计算机','2019级','正常修读',1,5500,'2021/8/26')
--delete from 财务状态表

INSERT
INTO 志愿信息表
VALUES('Vol001','1','何浩','计算机','防疫志愿','2021/9/26',30),
      ('Vol002','2','何二','计算机','养老院志愿','2020/8/26',40),
      ('Vol003','3','何三','计算机','社区志愿','2020/9/27',10),
      ('Vol004','4','何四','计算机','社区志愿','2021/9/26',20),
      ('Vol005','5','何五','计算机','莽山志愿','2021/9/26',50),
      ('Vol006','6','何六','计算机','防疫志愿','2021/9/28',10),
      ('Vol007','7','何七','计算机','防疫志愿','2021/7/16',4),
      ('Vol008','8','何八','计算机','支教志愿','2020/8/26',100),
      ('Vol009','9','何九','计算机','防疫志愿','2021/9/26',24),
      ('Vol010','10','何十','计算机','防疫志愿','2020/3/26',30)
--delete from 志愿信息表

INSERT
INTO 获奖记录表
VALUES('Award001','1','何浩','计算机','2021/9/26','程序设计','二等奖'),
      ('Award002','2','何二','计算机','2021/1/26','程序设计','一等奖'),
      ('Award003','3','何三','计算机','2021/9/24','数学建模','二等奖'),
      ('Award004','4','何四','计算机','2021/5/26','程序设计','特等奖'),
      ('Award005','5','何五','计算机','2021/7/26','英语竞赛','二等奖'),
      ('Award006','6','何六','计算机','2021/10/26','程序设计','二等奖'),
      ('Award007','7','何七','计算机','2021/4/26','数学竞赛','一等奖'),
      ('Award008','8','何八','计算机','2021/6/26','程序设计','二等奖'),
      ('Award009','9','何九','计算机','2021/9/14','物理实验竞赛','特等奖'),
      ('Award010','10','何十','计算机','2021/9/15','程序设计','二等奖')
--delete from 获奖记录表

INSERT
INTO 处分记录表
VALUES('Punish001','1','何浩','计算机','2021/12/10','违规','通报批评');