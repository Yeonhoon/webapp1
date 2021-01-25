create table hero(
    hname varchar(50) primary key,
    htype varchar(10) not null,
    hposition varchar(10) not null
    );
    
create table heroboard(
    bno number(5) primary key,
    btitle varchar(100) not null,
    bwriter varchar(50) references hero(hname) on delete cascade,
    bcontent clob
);
    