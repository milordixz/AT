CREATE TABLE test (
    id        integer CONSTRAINT idPK PRIMARY KEY,
    login       varchar(40) NOT NULL,
    password        varchar(40) NOT NULL,
    firstname   varchar(40) NOT NULL,
    lastname        varchar(40) NOT NULL
    
);

INSERT INTO test (id ,login, password, firstname , lastname ) VALUES (1,'test1', '123456', 'Alexander', 'Bogrov');
INSERT INTO test (id ,login, password, firstname , lastname ) VALUES (2,'test2', '654321', 'Vladimir', 'Petrov');
INSERT INTO test (id ,login, password, firstname , lastname ) VALUES (3,'test3', '111222', 'Ponchik', 'Sidorov');
INSERT INTO test (id ,login, password, firstname , lastname ) VALUES (4,'test4', '222333', 'Evgen', 'Galshak');
INSERT INTO test (id ,login, password, firstname , lastname ) VALUES (5,'test5', '444555', 'Eichiti', 'Utkur');