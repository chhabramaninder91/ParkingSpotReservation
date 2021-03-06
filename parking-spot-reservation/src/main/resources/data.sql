DROP TABLE IF EXISTS PARKING_SPOT;
DROP TABLE IF EXISTS RESERVATION_DETAILS;
DROP TABLE IF EXISTS USER_DETAILS;
 
CREATE TABLE USER_DETAILS ( 
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL
);
 
INSERT INTO USER_DETAILS (FIRST_NAME, LAST_NAME) VALUES
  ('Sachin','Tendulkar'),
  ('Virat', 'Kohli'),
  ('Mahendra', 'Dhoni');
  
CREATE TABLE RESERVATION_DETAILS (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  UNIQUE_NAME VARCHAR(25) NOT NULL,
  STARTTIME TIMESTAMP NOT NULL,
  ENDTIME TIMESTAMP NOT NULL,
  USER_ID BIGINT,
  ACTIVE BOOLEAN
  FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS(ID)
 );

INSERT INTO RESERVATION_DETAILS (USER_ID, UNIQUE_NAME, STARTTIME, ENDTIME) VALUES
  (1, 'RES0000001', '2020-01-12 14:25:56Z', '2020-01-12 16:25:000Z');
  
  
CREATE TABLE PARKING_SPOT (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  LATITUDE DOUBLE NOT NULL,
  LONGITUDE DOUBLE NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  ADDRESS VARCHAR(200) NOT NULL,
  RESERVATION_ID BIGINT,
  FOREIGN KEY (RESERVATION_ID) REFERENCES RESERVATION_DETAILS(ID)
);

--CREATE INDEX IX_PARKING_SPOT_LONG_LAT ON public.PARKING_SPOT USING btree (LATITUDE, LONGITUDE);
 
INSERT INTO PARKING_SPOT (RESERVATION_ID, LONGITUDE, LATITUDE, NAME, ADDRESS) VALUES
  (1, 123.89, 234.98, 'Shivaji Nagar', '20, Shivaji Nagar');
  INSERT INTO PARKING_SPOT (LATITUDE, LONGITUDE, NAME, ADDRESS) VALUES
  (0.5143, -1.7197, 'MagarPatta', 'MagarPatta,Pune');