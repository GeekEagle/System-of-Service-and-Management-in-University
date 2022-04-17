
CREATE LOGIN Student
    WITH PASSWORD = 'student1001'
GO

CREATE USER Stu1 FOR LOGIN Student
GO
use dbkeshe; 
create table ��ʦ��Ϣ��
(
    TeaID    char(10) not null
        constraint ��ʦ��Ϣ��_pk
            primary key,
    Teaname  char(20) not null,
    Teapro   char(30) not null,
    Teage    int
        constraint ck_Teage
            check ([Teage] > 0 AND [Teage] < 140),
    Iden     char(10)
        constraint ck_Iden
            check ([Iden] = 'Ⱥ��' OR [Iden] = '��Ա' OR [Iden] = '��������' OR [Iden] = '��Ա' OR [Iden] = 'Ԥ����Ա'),
    Teacol   char(30),
    Workyear int,
    Title    char(10)
        constraint ck_Title
            check ([Title] = '��ʦ' OR [Title] = '����' OR [Title] = '������' OR [Title] = '����' OR [Title] = 'Ժʿ'),
    constraint ck_Workyear
        check ([Workyear] < [��ʦ��Ϣ��].[Teage])
)
go

create table �γ���Ϣ��
(
    CourseID   char(10) not null
        constraint �γ���Ϣ��_pk
            primary key,
    CourseName char(30) not null,
    TeacherID  char(10)
        constraint �γ���Ϣ��_��ʦ��Ϣ��_Teanum_fk
            references ��ʦ��Ϣ��,
    counts     int,		--ѡ������
    Credit     float      not null,
    Classroom  char(3)
)
go

CREATE TABLE �༶��Ϣ��
(
    Clanum char(10) PRIMARY KEY,
    Teapro char(30),
    Teacol char(30),
    Account int
);
go

create table ѧ����Ϣ��
(
    StuID    char(10) not null
        constraint ѧ����Ϣ��_pk
            primary key,
    Stuname  char(20) not null,
    Stupro   char(30) not null,
    Stuage   int,
    Iden     char(10)
        constraint ck_Idenw
            check ([Iden] = 'Ⱥ��' OR [Iden] = '��Ա' OR [Iden] = '��������' OR [Iden] = '��Ա' OR [Iden] = 'Ԥ����Ա'),
    Teanum   char(10)
        constraint ѧ����Ϣ��_��ʦ��Ϣ��_Teanum_fk
            references ��ʦ��Ϣ��,
    Clanum   char(10)
        constraint ѧ����Ϣ��_�༶��Ϣ��_Teanum_fk
            references �༶��Ϣ��,
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

create table ѧ����ʦ��Ϣ��
(
    StuID char(10) not null PRIMARY KEY,
    Stuname char(20) not null,
    Assistname char(20),
    Turtorname char(20),
    ClassTeaname char(20),
    Clanum char(10) not null,
    College char(30) not null,
    --�༶�� ѧԺ
    FOREIGN KEY(StuID) REFERENCES ѧ����Ϣ��(StuID)
);
go

create table ����״̬��
(
    BudID     char(10) not null
        primary key,
    StuID     char(10) not null
        references ѧ����Ϣ��,
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

create table ־Ը��Ϣ��
(
    VolID   char(10) not null
        primary key,
    StuID   char(10)
        references ѧ����Ϣ��,
    Stuname char(10) not null,
    Stupro  char(10) not null,
    Volpro  char(10),
    Voldate date,
    Stuvol  float
        constraint ck_Stuvol
            check ([Stuvol] >= 0)
)
go

create table �񽱼�¼��
(
    Awanum   char(30) not null
        primary key,
    StuID    char(10)
        references ѧ����Ϣ��,
    Stuname  char(10) not null,
    Stupro   char(30) not null,
    Awardate date,
    Awarea   char(30),
    Awagra   char(30)
)
go

create table ���ּ�¼��
(
    Punum   char(10) not null
        primary key,
    StuID   char(10)
        references ѧ����Ϣ��,
    Stuname char(10) not null,
    Stupro  char(30) not null,
    Pundate date,
    Punarea char(30),
    Pungra  char(10)
)
go

create table ѡ����Ϣ��
(
    StuID    char(10) not null
        references ѧ����Ϣ��,
    CourseID char(10) not null
        references �γ���Ϣ��,
    Grade    int
        constraint ck_Grade
            check ([Grade] >= 0 AND [Grade] <= 100),
    Credit   float    not null,
    gpa      float,
    primary key (StuID, CourseID)
);
go

create table ������¼��
(
    Alpnum     char(10) not null
        primary key,
    Persontype char(10) not null,
    Aplpro     char(30),
    Apltable   char(30)
        constraint ck_table
            check ([Apltable] = '�񽱼�¼��' OR [Apltable] = '־Ը��Ϣ��' OR [Apltable] = '����״̬��' OR [Apltable] = '���ּ�¼��' OR
                   [Apltable] = '�༶��Ϣ��' OR [Apltable] = 'ѡ����Ϣ��' OR [Apltable] = '�γ���Ϣ��' OR [Apltable] = '��ʦ��Ϣ��' OR
                   [Apltable] = 'ѧ����Ϣ��'),
    Aplcon     char(30),
    Num        char(10),
    Date       date,
    Limits     bit,
    Accomplish bit,
    Result     char(30)
)
go


-- auto-generated definition
create table ��¼��Ϣ��
(
    userid   char(10) not null
        constraint ��¼��Ϣ_pk
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
INTO ��ʦ��Ϣ��
VALUES('100','����Ӣ','�����',45,'��Ա','��ѧԺ',15,'������');

INSERT
INTO �γ���Ϣ��
VALUES('C1020','���ݿ�ϵͳ','100',100,3,'103');

INSERT
INTO ѡ����Ϣ��
VALUES('1','C1020',90,0,0);

INSERT
INTO �༶��Ϣ��
VALUES('2019001','�����','��ϢѧԺ',1000);

INSERT
INTO ѧ����Ϣ��
VALUES('1','�κ�','�����',20,'��Ա','100','2019001','��ϢѧԺ','2019��','�����޶�','����',30,5500,1,0);

INSERT
INTO ����״̬��
VALUES('00001','1','�κ�','�����','2019��','�����޶�',1,5500,'2021/8/26');

INSERT
INTO ����״̬��
VALUES('00002','1','�κ�','�����','2019��','�����޶�',1,5500,'2021/8/26');

INSERT
INTO ־Ը��Ϣ��
VALUES('Vol001','1','�κ�','�����','����־Ը','2021/9/26',30);

INSERT
INTO �񽱼�¼��
VALUES('Award001','1','�κ�','�����','2021/9/26','�������','���Ƚ�');

INSERT
INTO ���ּ�¼��
VALUES('Punish001','1','�κ�','�����','2021/12/10','Υ��','ͨ������');



INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'1         ', N'123456              ', N'student   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'2         ', N'123456              ', N'student   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'100       ', N'12345678            ', N'teacher   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'3         ', N'123456              ', N'student   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'101       ', N'12345678            ', N'teacher   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'500       ', N'19260817            ', N'manager   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'501       ', N'19260817            ', N'manager   ');
INSERT INTO ��¼��Ϣ�� (userid, password, role) VALUES (N'2019011081', N'2019011081123       ', N'student   ');


GO
create view ��ʦ��Ϣ��_view
as select * from ��ʦ��Ϣ��;
go

create view ���ּ�¼��_view
as select * from ���ּ�¼��;
go

create view ѧ����Ϣ��_view
as select * from ѧ����Ϣ��;
go

create view ־Ը��Ϣ��_view
as select * from ־Ը��Ϣ��;
go

create view ������¼��_view
as select * from ������¼��;
go

create view �༶��Ϣ��_view
as select * from �༶��Ϣ��;
go

create view �񽱼�¼��_view
as select * from �񽱼�¼��;
go

create view �γ���Ϣ��_view
as select * from �γ���Ϣ��;
go

create view ����״̬��_view
as select * from ����״̬��;
go

create view ѡ����Ϣ��_view
as select * from ѡ����Ϣ��;
go

GO
CREATE VIEW ־Ըʱ��ͳ��_view(ѧ��ѧ��,־Ը��ʱ��)
as
SELECT StuID,SUM(Stuvol) FROM ־Ը��Ϣ��
GROUP BY StuID
go

--drop view ��ʦ��Ϣ��_view
--drop view ���ּ�¼��_view
--drop view ѧ����Ϣ��_view
--drop view ־Ը��Ϣ��_view
--drop view ������¼��_view
--drop view �༶��Ϣ��_view
--drop view �񽱼�¼��_view
--drop view �γ���Ϣ��_view
--drop view ����״̬��_view
--drop view ѡ����Ϣ��_view



CREATE ROLE Student
GRANT SELECT ON ѧ����Ϣ��
    TO Student
GRANT SELECT ON ѡ����Ϣ��
    TO Student
GRANT SELECT ON ־Ը��Ϣ��
    TO Student
GRANT SELECT ON ־Ըʱ��ͳ��_view
    TO Student
GRANT SELECT ON �񽱼�¼��
    TO Student
GRANT SELECT ON ���ּ�¼��
    TO Student
GRANT SELECT ON �γ���Ϣ��
    TO Student
GRANT UPDATE ON ѡ����Ϣ��
    TO Student
GRANT UPDATE,INSERT ON ������¼��(Alpnum,Persontype,Aplpro,Apltable,Num,Date)
    TO Student
GRANT SELECT ON ������¼��
    TO Student




CREATE ROLE Teacher
GRANT SELECT,UPDATE,INSERT ON ��ʦ��Ϣ��
    TO Teacher
GRANT SELECT,UPDATE,INSERT ON �γ���Ϣ��
    TO Teacher
GRANT SELECT ON ѡ����Ϣ��
    TO Teacher
GRANT UPDATE,INSERT ON ������¼��(Alpnum,Persontype,Aplpro,Apltable,Num,Date)
    TO Teacher
GRANT SELECT ON ������¼��
    TO Teacher

CREATE ROLE Manager
GRANT ALL PRIVILEGES ON ѧ����Ϣ��
    TO Manager
GRANT ALL PRIVILEGES ON �γ���Ϣ��
    TO Manager
GRANT ALL PRIVILEGES ON ѡ����Ϣ��
    TO Manager
GRANT ALL PRIVILEGES ON �༶��Ϣ��
    TO Manager
GRANT ALL PRIVILEGES ON ������¼��
    TO Manager
GRANT ALL PRIVILEGES ON ����״̬��
    TO Manager
GRANT ALL PRIVILEGES ON ־Ը��Ϣ��
    TO Manager
GRANT ALL PRIVILEGES ON �񽱼�¼��
    TO Manager
GRANT ALL PRIVILEGES ON ���ּ�¼��
    TO Manager
GRANT ALL PRIVILEGES ON δ�ɷ�ѧ��
    TO Manager

GO
CREATE TRIGGER ־Ըʱ��ͬ��
    ON ־Ը��Ϣ��
    AFTER UPDATE
    AS
    DECLARE @־Ըʱ�� FLOAT	--  @ ��ʾ�ֲ�����,  @@ ��ʾȫ�ֱ���
    SET @־Ըʱ��=(SELECT Stuvol FROM inserted)
    UPDATE ѧ����Ϣ��
    SET ѧ����Ϣ��.Stuvol=@־Ըʱ��+ѧ����Ϣ��.Stuvol
    where ѧ����Ϣ��.StuID=StuID;
GO

go


create trigger �ɼ�_trigger_insert on ѡ����Ϣ��  after insert as
begin
    declare @pronew int;
    declare @id char(10);
    set @pronew = (select Grade from inserted);
    set @id = (select StuID from inserted);
    begin
        update ѡ����Ϣ�� set ѡ����Ϣ��.gpa=dbo.get_gpa(@pronew) where StuID=@id;
        update ѧ����Ϣ�� set ѧ����Ϣ��.gpa=dbo.stu_gpa(@id) where StuID=@id;
    end
end
go

create trigger �ɼ�_trigger_update on ѡ����Ϣ��  after update as
    begin
        declare @pronew int;
        declare @proold int;
        declare @id char(10);
        set @proold = (select Grade from deleted);
        set @pronew = (select Grade from inserted);
        set @id = (select StuID from inserted);
        if (@pronew != @proold)
            begin
                update ѡ����Ϣ�� set ѡ����Ϣ��.gpa=dbo.get_gpa(@pronew) where @pronew<>@proold;
                update ѧ����Ϣ�� set ѧ����Ϣ��.gpa=dbo.stu_gpa(@id) where StuID=@id;
            end
    end


--���㼨�㣺
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
--�����ܼ���
--drop function stu_gpa
go
create function stu_gpa(@stuid char(10)) returns float
as begin
    declare @gpa_sum float;
    declare @credit_sum int;
    declare @gpa float;
    set @gpa_sum=(
        select sum(gpa*Credit)
        from ѡ����Ϣ��
        where StuID=@stuid)
    set @credit_sum=(
        select sum(Credit)
        from ѡ����Ϣ��
        where StuID=@stuid
    )
    if(@credit_sum=0)
        set @gpa=0;
    else
        set @gpa=@gpa_sum/@credit_sum
    return @gpa;
end
go



drop proc ��������
go
create proc ��������(@date char(10),@iden char(10),@id char(10),@Aplpro char(30),@con char(30))
as begin
    declare @alpn char(10)
    set @alpn = (select distinct count(*)  from ������¼��)+1
    insert into ������¼�� values (@alpn,@iden,@Aplpro,null,@con,@id,@date,NULL,NULL,NULL);
end

    drop proc ��������
go

create proc ��������(@alpn char(10))
as begin
    declare @apl char(30);
    declare @id char(10);
    declare @jud char(10);
    declare @con char(10);
    set @apl=(select Aplpro from ������¼��_view where alpnum=@alpn)
    set @id=(select Num from ������¼��_view where alpnum=@alpn)
    set @jud=(select Result from ������¼��_view where alpnum=@alpn)
    set @con=(select Aplcon from ������¼��_view where Alpnum=@alpn)
    if(@con not in ('�ɹ�','�ܾ�'))
        begin
            if (@apl='��ѧ')
                begin
                    update ѧ����Ϣ�� set Cond='��ѧ' where stuid=@id;
                    if exists (select * from ���ּ�¼��_view where ���ּ�¼��_view.StuID=@id)
                        delete from ���ּ�¼�� where StuID=@id;
                    if exists (select * from ־Ը��Ϣ��_view where ־Ը��Ϣ��_view.StuID=@id)
                        delete from ־Ը��Ϣ�� where StuID=@id;
                    if exists (select * from �񽱼�¼��_view where �񽱼�¼��_view.StuID=@id)
                        delete from �񽱼�¼�� where StuID=@id;
                    if exists (select * from ����״̬��_view where ����״̬��_view.StuID=@id)
                        delete from ����״̬�� where StuID=@id;
                    if exists (select * from ѡ����Ϣ��_view where ѡ����Ϣ��_view.StuID=@id)
                        delete from ѡ����Ϣ�� where StuID=@id;
                    delete from ��¼��Ϣ�� where userID=@id;
                end
            if (@apl='��ѧ')
                update ѧ����Ϣ�� set Cond='��ѧ' where stuid=@id;
            if (@apl='��ѧ��')
                begin
                    update ����״̬�� set Contui=1 where StuID=@id;
                    update ѧ����Ϣ�� set Contui=1 where StuID=@id;
                end

            if (@apl='��ҵ')
                update ѧ����Ϣ�� set Cond='��ҵ' where stuid=@id;
            if (@apl='תרҵ')
                begin
                    update ѧ����Ϣ�� set Stupro=@con where StuID=@id;
                end
            update ������¼��
            set Result='�ɹ�',Limits=1,Accomplish=1
            where Alpnum=@alpn;
        end
end
go
create proc �ܾ�����(@alpn char(10))
as begin
    update ������¼��
    set Result='�ܾ�',Limits=0,Accomplish=1
    where Alpnum=@alpn
end

go
create view ��ѯ������¼��_view (������,������Ŀ,��������,�Ƿ�����,�Ƿ����,������)
as select Alpnum,Aplpro,Date,Limits,Accomplish,Result
   from ������¼��
go


go
create trigger ѡ����������_trigger
    on ѡ����Ϣ��
    after insert
    as
    declare @course char(10),@num int;
    select @course=CourseID  from inserted;
    select @num=count(*) from �γ���Ϣ��,ѡ����Ϣ��
    where �γ���Ϣ��.CourseID=ѡ����Ϣ��.CourseID and ѡ����Ϣ��.CourseID=@course
    if (@num>100)
        begin
            print 'ѡ����������'
            rollback transaction
        end
go

go
create proc �񽱲���(@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    declare @alpn char(30)
    set @alpn = (select count(*) from �񽱼�¼��_view)+1
    insert into �񽱼�¼�� values (@alpn,@id,@name,@pro,@date,@area,@gra);
end
go--gengxin
create proc ���ֲ���(@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    declare @alpn char(10)
    set @alpn = (select max(Punum) from ���ּ�¼��_view)+1
    insert into ���ּ�¼�� values (@alpn,@id,@name,@pro,@date,@area,@gra);
end
go
create proc ��ɾ��(@apln char(10))
as begin
    delete  from �񽱼�¼�� where Awanum=@apln;
end
go
create proc ����ɾ��(@apln char(10))
as begin
    delete from ���ּ�¼�� where Punum=@apln;
end
go
create proc �񽱸���(@apln char(10),@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    delete from �񽱼�¼�� where Awanum=@apln;
    insert into �񽱼�¼�� values (@apln,@id,@name,@pro,@date,@area,@gra);
end
go
create proc ���ָ���(@apln char(10),@id char(10),@name char(10),@pro char(30),@date date,@area char(30),@gra char(30))
as begin
    delete from ���ּ�¼�� where Punum=@apln;
    insert into ���ּ�¼�� values (@apln,@id,@name,@pro,@date,@area,@gra);
end
create clustered
index index_ѧ�� on ѧ����Ϣ��(Cond)
create proc ����ѧ��(@id char(10),@name char(20),@pro char(30),@age int,@iden char(10),@teanum char(10),@class char(10),@college char(30),@grade char(10),@cond char(10),@loc char(20),@vol float,@tui int,@contui real)
as begin
    insert into ѧ����Ϣ�� values (@id,@name,@pro,@age,@iden,@teanum,@class,@college,@grade,@cond,@loc,@vol,@tui,@contui,NULL);
    insert into ѧ����ʦ��Ϣ�� values (@id,@name,NULL,@teanum,NULL,@class,@college);
end
drop proc ����ѧ��
go
--create proc ����ѧ��(@id char(10),@name char(20),@pro char(30),@age int,@iden char(10),@teanum char(10),@class char(10),@college char(30),@grade char(10),@cond char(10),@loc char(20),@tui int,@contui real)
create proc ����ѧ��(@id char(10),@name char(20),@pro char(30),@age int,@iden char(10),@teaname char(10),@class char(10),@college char(30),@grade char(10),@cond char(10),@loc char(20),@tui int,@contui real)
    as begin
        DECLARE @teanum char(10)
        set @teanum = (select TeaID from ��ʦ��Ϣ�� where Teaname = @teaname)
                insert into ѧ����Ϣ�� values (@id,@name,@pro,@age,@iden,@teanum,@class,@college,@grade,@cond,@loc,NULL,@tui,@contui,NULL);
                insert into ѧ����ʦ��Ϣ�� values (@id,@name,NULL,@teaname,NULL,@class,@college);
                insert into ��¼��Ϣ�� values (@id,rtrim(@id)+'123456','student');
    end
go

create proc ־Ը����(@stuid char(10),@name char(30),@stupro char(30),@volpro char(30),@date char(30),@time float)
as begin
    declare @alpn char(30)
    set @alpn = (select max(volid) from ־Ը��Ϣ��_view)+1
    insert into �񽱼�¼�� values (@alpn,@stuid,@name,@stupro,@volpro,@date,@time);
end
go

INSERT
INTO ��ʦ��Ϣ��
VALUES('100','����Ӣ','�����',45,'��Ա','��ѧԺ',15,'������'),
      ('101','��a','�����',47,'��Ա','��Ϣ��ѧ�빤��ѧԺ',14,'��ʦ'),
      ('102','��a','�����',50,'��Ա','��Ϣ��ѧ�빤��ѧԺ',20,'����'),
      ('103','��a','�����',40,'��Ա','��Ϣ��ѧ�빤��ѧԺ',8,'����'),
      ('104','��a','�����',44,'��Ա','��Ϣ��ѧ�빤��ѧԺ',9,'����'),
      ('105','��a','�����',39,'��Ա','��Ϣ��ѧ�빤��ѧԺ',11,'������'),
      ('106','³a','�����',39,'��Ա','��Ϣ��ѧ�빤��ѧԺ',12,'������'),
      ('107','��a','�����',45,'��Ա','��Ϣ��ѧ�빤��ѧԺ',10,'��ʦ'),
      ('108','��a','�����',43,'��Ա','��Ϣ��ѧ�빤��ѧԺ',15,'��ʦ'),
      ('109','��a','�����',40,'��Ա','��Ϣ��ѧ�빤��ѧԺ',15,'��ʦ'),
      ('130','��a','�����',47,'��Ա','��Ϣ��ѧ�빤��ѧԺ',10,'��ʦ'),
      ('110','��a','��ѧ',40,'��Ա','��ѧԺ',15,'��ʦ'),

      ('111','��a','��ѧ',46,'��Ա','��ѧԺ',15,'������'),
      ('112','��aa','����',40,'��Ա','��ѧԺ',15,'����'),

      ('113','��aa','Ӣ��',40,'��Ա','�����ѧԺ',15,'����'),
      ('114','��a','Ӣ��',39,'��Ա','�����ѧԺ',15,'��ʦ'),

      ('115','��b','����������',40,'��Ա','ʯ�͹���ѧԺ',15,'Ժʿ'),
      ('116','��bb','������ѧ',40,'��Ա','ʯ�͹���ѧԺ',15,'����'),

      ('117','��cc','���',38,'��Ա','���ù���ѧԺ',5,'��ʦ'),
      ('118','��nn','����',41,'��Ա','���ù���ѧԺ',15,'����'),

      ('119','��mm','����Դ',40,'��Ա','����Դ�����ѧԺ',15,'��ʦ'),
      ('120','��gg','����',40,'��Ա','����Դ�����ѧԺ',15,'����'),

      ('121','��yy','����ѧ',40,'��Ա','�����ѧѧԺ',15,'������'),
      ('122','��ii','������Դ����ʹ���',40,'��Ա','�����ѧѧԺ',15,'����')

--delete from ��ʦ��Ϣ��





INSERT
INTO �γ���Ϣ��
VALUES('C1020','���ݿ�ϵͳ','100',100,3,'103'),
      ('C1021','��������ԭ��','102',105,3,'417'),
      ('C1022','����ϵͳ','103',60,3,'214'),
      ('C1023','����ԭ��','130',100,3,'109'),
      ('D2000','������������ͳ��','110',90,3,'306'),
      ('D2001','��ɢ��ѧ','109',66,3,'502'),
      ('D2002','���Դ���','111',90,4,'113'),
      ('D2003','��ѧ����','112',90,4,'509'),
      ('D2004','ѧ��Ӣ��','113',64,6,'612'),
      ('D2005','ͨ��Ӣ��','114',55,6,'314'),
      ('S1000','����������','115',30,3,'314'),
      ('S1001','������ѧ','116',31,3,'416'),
      ('J1000','���ѧ','117',30,3,'114'),
      ('J1001','����ѧ','118',45,3,'514'),
      ('N1000','��Դ�����','119',30,3,'314'),
      ('N1001','������ѧ','120',30,3,'304'),
      ('Z1000','����ѧ','121',39,3,'314'),
      ('Z1001','���ʹ���','122',34,3,'114')
--delete �γ���Ϣ��


INSERT
INTO ѡ����Ϣ��
VALUES('1','C1020',90,0,0);


INSERT
INTO �༶��Ϣ��
VALUES('�����19-1','�����','��Ϣ��ѧ�빤��ѧԺ',23),
      ('�����19-2','�����','��Ϣ��ѧ�빤��ѧԺ',24),
      ('�����19-3','�����','��Ϣ��ѧ�빤��ѧԺ',20),
      ('�����19-4','�����','��Ϣ��ѧ�빤��ѧԺ',23),

      ('��ѧ19-1','��ѧ','��ѧԺ',23),
      ('����19-1','����','��ѧԺ',23),

      ('Ӣ��19-1','Ӣ��','�����ѧԺ',23),
      ('Ӣ��19-2','Ӣ��','�����ѧԺ',23),

      ('�꾮19-1','�꾮','ʯ�͹���ѧԺ',23),
      ('����19-1','��������','ʯ�͹���ѧԺ',23),

      ('���19-1','���','���ù���ѧԺ',23),
      ('���19-2','���','���ù���ѧԺ',23),

      ('����19-1','����','����Դ�����ѧԺ',23),

      ('����19-1','���ʿ�̽','�����ѧѧԺ',23)

--delete from �༶��Ϣ��

INSERT
INTO ѧ����Ϣ��
VALUES('1','�κ�','�����',20,'��Ա','100','�����19-1','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('2','�ζ�','�����',20,'Ⱥ��','101','�����19-2','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',40,5500,1,0),
      ('3','����','�����',20,'��������','102','�����19-3','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',50,5500,1,0),
      ('4','����','�����',20,'��Ա','103','�����19-4','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',60,5500,1,0),
      ('5','����','�����',20,'Ԥ����Ա','104','�����19-1','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',130,5500,1,0),
      ('6','����','�����',20,'��Ա','105','�����19-2','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',10,5500,1,0),
      ('7','����','�����',20,'��Ա','106','�����19-3','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('8','�ΰ�','�����',20,'��Ա','107','�����19-4','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('9','�ξ�','�����',20,'��Ա','108','�����19-1','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('10','��ʮ','�����',20,'��Ա','109','�����19-2','��Ϣ��ѧ�빤��ѧԺ','2019��','�����޶�','����',30,5500,1,0),

      ('20','����','��ѧ',20,'Ԥ����Ա','111','��ѧ19-1','��ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('21','��һ','����',20,'��Ա','112','����19-1','��ѧԺ','2019��','�����޶�','����',30,5500,1,0),

      ('30','����','Ӣ��',20,'��Ա','113','Ӣ��19-1','�����ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('31','��һ','Ӣ��',20,'��Ա','114','Ӣ��19-2','�����ѧԺ','2019��','�����޶�','����',30,5500,1,0),

      ('40','����','�꾮',20,'��Ա','115','�꾮19-1','ʯ�͹���ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('41','��һ','��������',20,'��Ա','116','����19-1','ʯ�͹���ѧԺ','2019��','�����޶�','����',30,5500,1,0),

      ('50','����','���',20,'��Ա','117','���19-1','���ù���ѧԺ','2019��','�����޶�','����',30,5500,1,0),
      ('51','��һ','���',20,'��Ա','118','���19-2','���ù���ѧԺ','2019��','�����޶�','����',30,5500,1,0),

      ('60','����','����',20,'��Ա','119','����19-1','����Դ�����ѧԺ','2019��','�����޶�','����',30,5500,1,0),

      ('70','����','���ʿ�̽',20,'��Ա','121','����19-1','�����ѧѧԺ','2019��','�����޶�','����',30,5500,1,0)
--delete from ѧ����Ϣ��


--ѧ����ʦ��Ϣ��
INSERT
INTO ѧ����ʦ��Ϣ��
VALUES('1','�κ�','����ɯ','��a','������','�����19-1','��Ϣ��ѧ�빤��ѧԺ'),
      ('2','�ζ�','����ɯ','��a','��a','�����19-2','��Ϣ��ѧ�빤��ѧԺ'),
      ('3','����','����ɯ','��a','³a','�����19-3','��Ϣ��ѧ�빤��ѧԺ'),
      ('4','����','����ɯ','��a','��a','�����19-4','��Ϣ��ѧ�빤��ѧԺ'),
      ('5','����','����ɯ','��a','������','�����19-1','��Ϣ��ѧ�빤��ѧԺ'),
      ('6','����','����ɯ','��a','��a','�����19-2','��Ϣ��ѧ�빤��ѧԺ'),
      ('7','����','����ɯ','��a','³a','�����19-3','��Ϣ��ѧ�빤��ѧԺ'),
      ('8','�ΰ�','����ɯ','��a','��a','�����19-4','��Ϣ��ѧ�빤��ѧԺ'),
      ('9','�ξ�','����ɯ','��a','������','�����19-1','��Ϣ��ѧ�빤��ѧԺ'),
      ('10','��ʮ','����ɯ','��a','��a','�����19-2','��Ϣ��ѧ�빤��ѧԺ'),

      ('20','����','����ɯ','��a','��aa','��ѧ19-1','��ѧԺ'),
      ('21','��һ','����ɯ','��aa','��a','����19-1','��ѧԺ'),

      ('30','����','����ɯ','��aa','��a','Ӣ��19-1','�����ѧԺ'),
      ('31','��һ','����ɯ','��a','��aa','Ӣ��19-2','�����ѧԺ'),

      ('40','����','����ɯ','��b','��bb','�꾮19-1','ʯ�͹���ѧԺ'),
      ('41','��һ','����ɯ','��bb','��b','����19-1','ʯ�͹���ѧԺ'),

      ('50','����','����ɯ','��cc','��nn','���19-1','���ù���ѧԺ'),
      ('51','��һ','����ɯ','��nn','��cc','���19-2','���ù���ѧԺ'),

      ('60','����','����ɯ','��mm','��gg','����19-1','�����ѧѧԺ'),

      ('70','����','����ɯ','��yy','��ii','����19-1','����Դ�����ѧԺ')



--delete ѧ����ʦ��Ϣ��


INSERT
INTO ����״̬��
VALUES('00001','1','�κ�','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00002','2','�ζ�','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00003','3','����','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00004','4','����','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00005','5','����','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00006','6','����','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00007','7','����','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00008','8','�ΰ�','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00009','9','�ξ�','�����','2019��','�����޶�',1,5500,'2021/8/26'),
      ('00010','10','��ʮ','�����','2019��','�����޶�',1,5500,'2021/8/26')
--delete from ����״̬��

INSERT
INTO ־Ը��Ϣ��
VALUES('Vol001','1','�κ�','�����','����־Ը','2021/9/26',30),
      ('Vol002','2','�ζ�','�����','����Ժ־Ը','2020/8/26',40),
      ('Vol003','3','����','�����','����־Ը','2020/9/27',10),
      ('Vol004','4','����','�����','����־Ը','2021/9/26',20),
      ('Vol005','5','����','�����','çɽ־Ը','2021/9/26',50),
      ('Vol006','6','����','�����','����־Ը','2021/9/28',10),
      ('Vol007','7','����','�����','����־Ը','2021/7/16',4),
      ('Vol008','8','�ΰ�','�����','֧��־Ը','2020/8/26',100),
      ('Vol009','9','�ξ�','�����','����־Ը','2021/9/26',24),
      ('Vol010','10','��ʮ','�����','����־Ը','2020/3/26',30)
--delete from ־Ը��Ϣ��

INSERT
INTO �񽱼�¼��
VALUES('Award001','1','�κ�','�����','2021/9/26','�������','���Ƚ�'),
      ('Award002','2','�ζ�','�����','2021/1/26','�������','һ�Ƚ�'),
      ('Award003','3','����','�����','2021/9/24','��ѧ��ģ','���Ƚ�'),
      ('Award004','4','����','�����','2021/5/26','�������','�صȽ�'),
      ('Award005','5','����','�����','2021/7/26','Ӣ�ﾺ��','���Ƚ�'),
      ('Award006','6','����','�����','2021/10/26','�������','���Ƚ�'),
      ('Award007','7','����','�����','2021/4/26','��ѧ����','һ�Ƚ�'),
      ('Award008','8','�ΰ�','�����','2021/6/26','�������','���Ƚ�'),
      ('Award009','9','�ξ�','�����','2021/9/14','����ʵ�龺��','�صȽ�'),
      ('Award010','10','��ʮ','�����','2021/9/15','�������','���Ƚ�')
--delete from �񽱼�¼��

INSERT
INTO ���ּ�¼��
VALUES('Punish001','1','�κ�','�����','2021/12/10','Υ��','ͨ������');