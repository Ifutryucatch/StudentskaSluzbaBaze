DROP SCHEMA IF EXISTS studentskasluzba;
CREATE SCHEMA studentskasluzba DEFAULT CHARACTER SET utf8;
USE studentskasluzba;


CREATE TABLE nastavnici(
	nastavnik_id INT UNIQUE AUTO_INCREMENT,
    ime VARCHAR(20) NOT NULL,
    prezime VARCHAR(20) NOT NULL,
    zvanje VARCHAR(20) NOT NULL,
    primary key(nastavnik_id)
);

CREATE TABLE studenti(
	student_id INT auto_increment UNIQUE,
    indeks varchar(20) UNIQUE NOT NULL,
    ime varchar (20) NOT NULL,
	prezime VARCHAR(20) NOT NULL,
    grad VARCHAR(20) NOT NULL,
    primary key(student_id)
);

CREATE TABLE predmeti(
	predmet_id INT unique auto_increment,
    naziv varchar(20),
    primary key(predmet_id)
);

CREATE TABLE predaje(
	nastavnik_id INT not null,
    predmet_id int not null,
    primary key(nastavnik_id, predmet_id),
    
    foreign key(nastavnik_id) references 
    nastavnici(nastavnik_id) on delete restrict,
    
    foreign key(predmet_id) references 
    predmeti(predmet_id) on delete restrict
);

CREATE TABLE pohadja(
	student_id int not null,
    predmet_id int not null,
    primary key(student_id, predmet_id),
    
    foreign key (student_id) references 
    studenti(student_id) on delete restrict,
    foreign key(predmet_id) references 
    predmeti(predmet_id) on delete restrict
);

CREATE TABLE ispitni_rokovi(
	rok_id int auto_increment unique,
    naziv varchar(20) not null,
    pocetak date not null,
    kraj date not null,
    primary key(rok_id)
);

CREATE TABLE ispitne_prijave(
	student_id int not null,
    predmet_id int not null,
    rok_id int not null,
    teorija int not null,
    zadaci int not null,
    primary key(student_id, predmet_id, rok_id),
    
	foreign key (student_id) references 
    studenti(student_id) on delete restrict,
	foreign key(predmet_id) references 
    predmeti(predmet_id) on delete restrict,
    foreign key(rok_id) references 
    ispitni_rokovi(rok_id) on delete restrict
);

INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 1/12', 'Petar', 'Mihajlovic', 'Novi Sad');
INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 2/12', 'Dejan', 'Ivanovic', 'Loznica');
INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 3/12', 'Zoran', 'Kovacevic', 'Indjija');
INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 4/12', 'Marko', 'Popovic', 'Novi Sad');

INSERT INTO nastavnici (ime, prezime, zvanje) VALUES ('Marko', 'Popovic', 'Docent');
INSERT INTO nastavnici (ime, prezime, zvanje) VALUES ('Milan', 'Janjic', 'Docent');
INSERT INTO nastavnici (ime, prezime, zvanje) VALUES ('Zeljko', 'Djuric', 'Asistent');
	
INSERT INTO predmeti (naziv) VALUES ('Algebra');
INSERT INTO predmeti (naziv) VALUES ('Analiza 1');


INSERT INTO ispitni_rokovi (naziv, pocetak, kraj) VALUES ('Januarski', '2015-01-15', '2015-01-29');
INSERT INTO ispitni_rokovi (naziv, pocetak, kraj) VALUES ('Februarski', '2015-02-01', '2015-02-14');

INSERT INTO predaje VALUES (1, 1); 
INSERT INTO predaje VALUES (1, 2);
INSERT INTO predaje VALUES (2, 2);
INSERT INTO predaje VALUES (3, 1);
	
INSERT INTO pohadja VALUES (1, 1);
INSERT INTO pohadja VALUES (1, 2);
INSERT INTO pohadja VALUES (2, 1);
INSERT INTO pohadja VALUES (3, 1);

INSERT INTO ispitne_prijave VALUES (1, 1, 1, 20, 70);
INSERT INTO ispitne_prijave VALUES (1, 2, 1, 10, 54);
INSERT INTO ispitne_prijave VALUES (2, 1, 1, 10, 10);
INSERT INTO ispitne_prijave VALUES (2, 1, 2, 40, 30);
INSERT INTO ispitne_prijave VALUES (3, 1, 1, 10, 30);
