
  CREATE TABLE "MANAGER"."BOOKS" 
   (	"ID" NUMBER(*,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	"AUTHOR" VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(1000 BYTE), 
	"IMAGEURI" VARCHAR2(255 BYTE), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "MANAGER"."BOOK_ON_INSERT" 
   before insert on "MANAGER"."BOOKS" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select BOOK_ID_SEQ.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "MANAGER"."BOOK_ON_INSERT" ENABLE;