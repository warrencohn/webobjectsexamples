
DELETE from ACCOUNT 
go  
DELETE from PROFILE 
go  
DELETE from CATEGORY 
go  
DELETE from PRODUCT 
go  
DELETE from INVENTORY 
go  
DELETE from SUPPLIER 
go  
DELETE from ITEM 
go  
INSERT INTO ACCOUNT (USERID, PASSWORD, EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE) VALUES('wojava','wojava','yourname@yourdomain.com','ABC', 'XYZ', 'OK', '901 San Antonio Road','MS UCUP02-206', 'Palo Alto', 'CA', '94303', 'USA', '555-555-5555') 
go  
INSERT INTO PROFILE (USERID,LANGPREF,FAVCATEGORY,BANNEROPT,MYLISTOPT) VALUES('wojava','English','dogs',1,1) 
go  
INSERT INTO CATEGORY (CATID, NAME, DESCN) VALUES  ('FISH','Fish','<image src=../images/fish_icon.gif><font size=5 color=blue> Fish</font>') 
go  
INSERT INTO CATEGORY (CATID, NAME, DESCN) VALUES  ('DOGS','Dogs','<image src=../images/dogs_icon.gif><font size=5 color=blue> Dogs</font>') 
go  
INSERT INTO CATEGORY (CATID, NAME, DESCN) VALUES ('REPTILES','Reptiles','<image src=../images/reptiles_icon.gif><font size=5 color=blue>Reptiles</font>') 
go  
INSERT INTO CATEGORY (CATID, NAME, DESCN) VALUES  ('CATS','Cats','<image src=../images/cats_icon.gif><font size=5 color=blue> Cats</font>') 
go  
INSERT INTO CATEGORY (CATID, NAME, DESCN) VALUES  ('BIRDS','Birds','<image src="../images/birds_icon.gif"><font size="5" color="blue"> Birds</font>') 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-1',0) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-2',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-3',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-4',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-5',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-6',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-7',0) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-8',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-9',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-10',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-11',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-12',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-13',0) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-14',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-15',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-16',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-17',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-18',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-19',0) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-20',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-21',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-22',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-23',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-24',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-25',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-26',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-27',10) 
go  
INSERT INTO INVENTORY (ITEMID, QTY ) VALUES ('EST-28',10) 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('FI-SW-01','FISH','Angelfish','<image src="../images/fish1.jpg">Salt Water fish from Australia') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('FI-SW-02','FISH','Tiger Shark','<image src="../images/fish4.gif">Salt Water fish from Australia') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('FI-FW-01','FISH', 'Koi','<image src="../images/fish3.gif">Fresh Water fish from Japan') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('FI-FW-02','FISH', 'Goldfish','<image src="../images/fish2.gif">Fresh Water fish from China') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('K9-BD-01','DOGS','Bulldog','<image src="../images/dog2.gif">Friendly dog from England') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('K9-PO-02','DOGS','Poodle','<image src="../images/dog6.gif">Cute dog from France') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('K9-DL-01','DOGS', 'Dalmation','<image src="../images/dog5.gif">Great dog for a Fire Station') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('K9-RT-01','DOGS', 'Golden Retriever','<image src="../images/dog1.gif">Great family dog') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('K9-RT-02','DOGS', 'Labrador Retriever','<image src="../images/dog5.gif">Great hunting dog') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('K9-CW-01','DOGS', 'Chihuahua','<image src="../images/dog4.gif">Great companion dog') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('RP-SN-01','REPTILES','Rattlesnake','<image src="../images/lizard3.gif">Doubles as a watch dog') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('RP-LI-02','REPTILES','Iguana','<image src="../images/lizard2.gif">Friendly green friend') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('FL-DSH-01','CATS','Manx','<image src="../images/cat3.gif">Great for reducing mouse populations') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('FL-DLH-02','CATS','Persian','<image src="../images/cat1.gif">Friendly house cat, doubles as a princess') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('AV-CB-01','BIRDS','Amazon Parrot','<image src="../images/bird4.gif">Great companion for up to 75 years') 
go  
INSERT INTO PRODUCT (PRODUCTID, CATEGORY, NAME, DESCN) VALUES ('AV-SB-02','BIRDS','Finch','<image src="../images/bird1.gif">Great stress reliever') 
go  
INSERT INTO SUPPLIER (SUPPID, NAME, STATUS, ADDR1,ADDR2,CITY, STATE,ZIP,PHONE) VALUES (1,'XYZ Pets','AC','600 Avon Way','','Los Angeles','CA','94024','212-947-0797') 
go  
INSERT INTO SUPPLIER (SUPPID, NAME, STATUS, ADDR1,ADDR2,CITY, STATE,ZIP,PHONE) VALUES (2,'ABC Pets','AC','700 Abalone Way','','San Francisco','CA','94024','415-947-0797') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-1','FI-SW-01',16.50,10.00,1,'P','Large') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-2','FI-SW-01',16.50,10.00,1,'P','Small') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES('EST-3','FI-SW-02',18.50,12.00,1,'P','Toothless') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-4','FI-FW-01',18.50,12.00,1,'P','Spotted') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-5','FI-FW-01',18.50,12.00,1,'P','Spotless') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-6','K9-BD-01',18.50,12.00,1,'P','Male Adult') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-7','K9-BD-01',18.50,12.00,1,'P','Female Puppy') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-8','K9-PO-02',18.50,12.00,1,'P','Male Puppy') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-9','K9-DL-01',18.50,12.00,1,'P','Spotless Male Puppy') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-10','K9-DL-01',18.50,12.00,1,'P','Spotted Adult Female') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-11','RP-SN-01',18.50,12.00,1,'P','Venomless') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-12','RP-SN-01',18.50,12.00,1,'P','Rattleless') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-13','RP-LI-02',18.50,12.00,1,'P','Green Adult') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-14','FL-DSH-01',58.50,12.00,1,'P','Tailless') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES('EST-15','FL-DSH-01',23.50,12.00,1,'P','With tail') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-16','FL-DLH-02',93.50,12.00,1,'P','Adult Female') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES('EST-17','FL-DLH-02',93.50,12.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-18','AV-CB-01',193.50,92.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-19','AV-SB-02',15.50, 2.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-20','FI-FW-02',5.50, 2.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-21','FI-FW-02',5.29, 1.00,1,'P','Adult Female') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-22','K9-RT-02',135.50, 100.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES('EST-23','K9-RT-02',145.49, 100.00,1,'P','Adult Female') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-24','K9-RT-02',255.50, 92.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-25','K9-RT-02',325.29, 90.00,1,'P','Adult Female') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-26','K9-CW-01',125.50, 92.00,1,'P','Adult Male') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-27','K9-CW-01',155.29, 90.00,1,'P','Adult Female') 
go  
INSERT INTO  ITEM (ITEMID, PRODUCTID, LISTPRICE, UNITCOST, SUPPLIER, STATUS, ATTR1) VALUES ('EST-28','K9-RT-01',155.29, 90.00,1,'P','Adult Female') 
go  
commit 
go  

  