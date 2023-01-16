CREATE TABLE student (
  student_id serial primary key,
  name varchar(45)
);

CREATE TABLE student_score (
  score_id serial primary key,
  subject varchar(45),
  score int,
  student_id int,
  FOREIGN KEY (student_id) REFERENCES student (student_id)
);

INSERT INTO student(name) VALUES ('Sergey'), ('Olga'), ('Fedor');

INSERT INTO student_score(student_id, subject, score)
VALUES
  (1,'English',4),
  (1,'Math',5),
  (2,'English',3),
  (3,'English',5);

  select name as firstname, subject as all_subject, score as all_Score
  from student join student_score using(student_id);
  
  select name as firstname, subject as english, score
  from student join student_score using(student_id)
  Where subject = 'English';
  
  select name as firstname, max(score) as best_score
  from student join student_score using(student_id)
  group by name
  having name = 'Sergey';