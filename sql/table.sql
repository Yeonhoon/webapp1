create table members(
    mid varchar(20) primary key,
    mname varchar(20) not null,
    mpassword varchar(20) not null
    );
    
create table boards (
    bno                   number(5) primary key,
    btitle                varchar(1000) not null,
    bcontent              clob not null,
    bwriter               varchar(20) references members(mid) on delete cascade,
    bdate                 date not null,
    bhitcount             number(5) not null,
    battach_savefile_name varchar(100) null,
    battach_original_name varchar(100) null,
    battach_type          varchar(100) null
    );
    
create sequence bno_seq;
create table accounts(
    account_num varchar(50) primary key,
    account_owner varchar(10) not null,
    account_balance number(10) not null
    );