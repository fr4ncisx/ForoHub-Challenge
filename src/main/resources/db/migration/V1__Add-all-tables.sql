CREATE TABLE
    User (
        id bigint auto_increment,
        username varchar(100) unique,
        email varchar(100),
        password varchar(100),
        PRIMARY KEY (id)
    );

CREATE TABLE
    Topic (
        id bigint auto_increment,
        title varchar(100) UNIQUE,
        message TEXT,
        creation_date date,
        course varchar(100),
        status varchar(50),
        user_id bigint not null,
        answers TEXT,
        PRIMARY KEY (id),
        FOREIGN KEY (user_id) references User (id)
    );

CREATE TABLE
    Answer (
        id bigint auto_increment,
        message TEXT,
        topic_id bigint not null,
        creation_date DATE,
        user_id bigint not null,
        solved boolean,
        PRIMARY KEY (id),
        FOREIGN KEY (user_id) references User (id),
        FOREIGN KEY (topic_id) references Topic (id)
    );

    
