DROP SEQUENCE CHECKUP_SEQ;
DROP TABLE CHECKUP;

CREATE SEQUENCE CHECKUP_SEQ;

CREATE TABLE CHECKUP(
   --컬럼 총 20개
   CHECKUP_SEQ NUMBER PRIMARY KEY NOT NULL,--검진번호
   MEMBER_SEQ NUMBER REFERENCES MEMBER(MEMBER_SEQ) NOT NULL,--회원번호
   CHECKUP_TYPE VARCHAR2(20),--검진타입(기본1, 개인2, 정밀(당뇨)3)
   CHECKUP_YEAR VARCHAR2(20),--측정년도
   CHECKUP_MONTH VARCHAR2(20),--측정월
   CHECKUP_DAY VARCHAR2(20),--측정일
   CHECKUP_REGIST VARCHAR2(20),--주민등록번호
   CHECKUP_HEIGHT VARCHAR2(20),--신장
   CHECKUP_WEIGHT VARCHAR2(20),--체중
   CHECKUP_LEYE VARCHAR2(20),--시력좌
   CHECKUP_REYE VARCHAR2(20),--시력우
   CHECKUP_LHEAR VARCHAR2(20),--청각좌
   CHECKUP_RHEAR VARCHAR2(20),--청각우
   CHECKUP_MAXBLOOD VARCHAR2(20),--혈압최고
   CHECKUP_MINBLOOD VARCHAR2(20),--혈압최저
   CHECKUP_KIDNEY VARCHAR2(20),--요단백
   CHECKUP_HEMO VARCHAR2(20),--혈색소
   
   CHECKUP_FUNDO VARCHAR2(20),--안저검사좌/우
   CHECKUP_BEFORESUGAR VARCHAR2(20),--식전혈당
   CHECKUP_AFTERSUGAR VARCHAR2(20)--식후혈당
);


INSERT INTO CHECKUP VALUES(
   CHECKUP_SEQ.NEXTVAL,1,
     '1','2019','10','01','900923',
     '180','78','0.8','0.8',
     '1', '1','115','84','1',
     '13.9','1','95','105'
     );
    
     INSERT INTO CHECKUP VALUES(
   CHECKUP_SEQ.NEXTVAL,1,
     '1','2019','11','06','900923',
     '181','84','0.7','0.7',
     '1', '1','125','91','1',
     '17.9','1','99','110'
     );
     
     INSERT INTO CHECKUP VALUES(
   CHECKUP_SEQ.NEXTVAL,1,
     '1','2019','12','03','900923',
     '181','86','0.7','0.7',
     '1', '1','130','98','1',
     '17.9','1','89','110'
     );
     
    INSERT INTO CHECKUP VALUES(
   CHECKUP_SEQ.NEXTVAL,1,
     '1','2020','01','08','900923',
     '181','86','0.7','0.7',
     '1', '1','115','86','1',
     '17.9','1','97','113'
     );
SELECT * FROM CHECKUP ORDER BY CHECKUP_SEQ ASC;

DELETE FROM CHECKUP WHERE CHECKUP_SEQ = 8;