 CREATE TABLESPACE tbs_temp_dev_derby DATAFILE '/u01/data/tbs_temp_dev_derby.dbf' SIZE 50M
    EXTENT MANAGEMENT LOCAL AUTOALLOCATE;

CREATE USER dev_derby
  IDENTIFIED BY welcome1
  DEFAULT TABLESPACE tbs_perm_dev_derby;
 
ALTER USER dev_derby QUOTA 100M ON tbs_perm_dev_derby ;
 
GRANT UNLIMITED TABLESPACE TO dev_derby ;
 
GRANT create session TO dev_derby;
GRANT create table TO dev_derby;
GRANT create view TO dev_derby;
GRANT create any trigger TO dev_derby;
GRANT create any procedure TO dev_derby;
GRANT create sequence TO dev_derby;
GRANT create synonym TO dev_derby;
GRANT create insert TO dev_derby;