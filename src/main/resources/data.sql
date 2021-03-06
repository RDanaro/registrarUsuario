DROP TABLE IF EXISTS USUARIO;
DROP TABLE IF EXISTS PHONE;

CREATE TABLE USUARIO (
  ID VARCHAR(36) PRIMARY KEY,
  NAME VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(100) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  CREATED TIMESTAMP NOT NULL,
  MODIFIED TIMESTAMP NOT NULL,
  LAST_LOGIN TIMESTAMP NOT NULL,
  TOKEN VARCHAR(255)NOT NULL,
  IS_ACTIVE BOOLEAN NOT NULL,
  UNIQUE (EMAIL)
);

CREATE TABLE PHONE (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  NUMBER VARCHAR(36) NOT NULL,
  CITY_CODE VARCHAR(36) NOT NULL,
  COUNTRY_CODE VARCHAR(36) NOT NULL,
  USER_ID VARCHAR(36) NOT NULL,
  UNIQUE (USER_ID, NUMBER)
);

ALTER TABLE PHONE ADD FOREIGN KEY (USER_ID) REFERENCES USUARIO(ID);