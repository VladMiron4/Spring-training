
CREATE Table  IF NOT EXISTS Customer (
Id  UUID NOT NULL ,
FirstName   VARCHAR(255),
LastName   VARCHAR(255),
Username  VARCHAR(255),
Password  VARCHAR(255),
EmailAddress  VARCHAR(255),
PRIMARY KEY(Id)
);

CREATE TABLE  IF NOT EXISTS "order"(
Id UUID NOT NULL,
Customer UUID NOT NULL REFERENCES Customer(Id),
CreatedAt  DATE,
Address_City VARCHAR(255),
Address_County VARCHAR(255),
Address_Country VARCHAR(255),
Address_StreetAddress VARCHAR(255),
Primary Key(Id)
);

CREATE TABLE IF NOT EXISTS ProductCategory
(
Id   UUID NOT NULL,
Name VARCHAR(255),
Description   VARCHAR(255),
PRIMARY KEY(Id)
);

CREATE TABLE  IF NOT EXISTS Product
(
Id UUID NOT NULL,
Name   VARCHAR(255),
Price   DECIMAL,
Weight   DOUBLE PRECISION,
Category UUID NOT NULL REFERENCES ProductCategory(Id),
Supplier   VARCHAR(255),
ImageUrl  VARCHAR(255),
PRIMARY KEY (Id)
);

CREATE TABLE  IF NOT EXISTS Location
(
Id UUID NOT NULL,
Name VARCHAR(255),
Address_City VARCHAR(255),
Address_County VARCHAR(255),
Address_Country VARCHAR(255),
Address_StreetAddress VARCHAR(255),
PRIMARY KEY (Id)
);

CREATE TABLE  IF NOT EXISTS Stock  (
Product UUID NOT NULL REFERENCES Product(Id),
Location UUID NOT NULL REFERENCES Location(Id),
Quantity INTEGER,
PRIMARY KEY (Product,Location)
);

CREATE TABLE  IF NOT EXISTS OrderDetail
(
"order" UUID  NOT NULL REFERENCES "order"(Id),
Product UUID  NOT NULL REFERENCES Product(Id),
ShippedFrom UUID  REFERENCES Location(ID),
Quantity INTEGER,
PRIMARY KEY("order",Product)
);