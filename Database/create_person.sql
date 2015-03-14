CREATE TABLE PERSON
     (
        NUM             NUMBER          NOT NULL , 
        FIRSTNAME       VARCHAR2(20)        NULL , 
        GENDER_CODE     CHAR(1)             NULL , 
        BIRTH_DTTM      DATE                NULL , 
        INACTIVE_DATE   DATE                NULL , 
        LASTNAME        VARCHAR2(30)        NULL , 
        CONSTRAINT PK_PERSON PRIMARY KEY (NUM)
     )
;
CREATE SEQUENCE person_seq;



CREATE OR REPLACE TRIGGER person_bir 
BEFORE INSERT ON person 
FOR EACH ROW

BEGIN
  SELECT person_seq.NEXTVAL
  INTO   :new.NUM
  FROM   dual;
END;
/


INSERT INTO Person (FIRSTNAME, GENDER_CODE, BIRTH_DTTM, INACTIVE_DATE, LASTNAME)
VALUES ('Lindsay','F',TO_DATE('1989-03-08','YYYY-MM-DD'),null,'Vermeer');
