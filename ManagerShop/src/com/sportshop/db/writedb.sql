-- Altering table columns in dbo.Material and dbo.Color
ALTER TABLE dbo.Material MODIFY COLUMN valueMaterial NVARCHAR(50) NOT NULL;
ALTER TABLE dbo.Color MODIFY COLUMN valueColor NVARCHAR(50) NOT NULL;

-- Adding a new column 'statusDelete' to dbo.Products
ALTER TABLE dbo.Products ADD statusDelete BIT DEFAULT 1;

-- Dropping constraints and columns from dbo.Products
ALTER TABLE dbo.Products DROP FOREIGN KEY FK__Products__idSupp__44FF419A;
ALTER TABLE dbo.Products DROP COLUMN idSupplier;

-- Adding a new column 'status' to dbo.List
ALTER TABLE dbo.List ADD status BIT DEFAULT 1;

-- Dropping stored procedure PRDelete if exists and creating a new one
DROP PROCEDURE IF EXISTS PRDelete;
DELIMITER //
CREATE PROCEDURE PRDelete (idPrDetails INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
    END;

    START TRANSACTION;
    DELETE FROM dbo.ImageProducts WHERE idPrDeltails IN (SELECT idPrDeltails FROM dbo.ImageProducts WHERE @idPrDetails = idPrDeltails);
    DELETE FROM dbo.detailsProduct WHERE idPrDeltails IN (SELECT idPrDeltails FROM dbo.detailsProduct WHERE @idPrDetails = idPrDeltails);
    COMMIT;
END//
DELIMITER ;

-- Altering dbo.Invoice table
ALTER TABLE dbo.Invoice DROP FOREIGN KEY FK__Invoice__idCusto__34C8D9D1;
ALTER TABLE dbo.Invoice DROP COLUMN idCustomer;
ALTER TABLE dbo.Invoice DROP FOREIGN KEY FK__Invoice__idEmpol__35BCFE0A;
ALTER TABLE dbo.Invoice DROP COLUMN idEmpolyee;
ALTER TABLE dbo.Invoice DROP FOREIGN KEY FK__Invoice__idVouch__36B12243;
ALTER TABLE dbo.Invoice DROP COLUMN idVoucher;
ALTER TABLE dbo.Invoice RENAME TO InvoiceImportPr;
ALTER TABLE dbo.InvoiceImportPr ADD idAdmin INT;
ALTER TABLE dbo.InvoiceImportPr ADD FOREIGN KEY (idAdmin) REFERENCES dbo.User(idUser);

-- Altering dbo.detailsInvoice table
ALTER TABLE dbo.detailsInvoice DROP PRIMARY KEY;
ALTER TABLE dbo.detailsInvoice DROP COLUMN detailsInvoice;
ALTER TABLE dbo.detailsInvoice MODIFY COLUMN detailsInvoice INT AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE dbo.detailsInvoiceImportPr DROP FOREIGN KEY FK__detailsIn__idSup__4222D4EF;
ALTER TABLE dbo.detailsInvoiceImportPr DROP COLUMN idSupplier;
ALTER TABLE dbo.InvoiceImportPr ADD idSupplier INT;
ALTER TABLE dbo.InvoiceImportPr ADD FOREIGN KEY (idSupplier) REFERENCES dbo.Supplier(idSupplier);
ALTER TABLE dbo.InvoiceImportPr ADD description NVARCHAR(255);
ALTER TABLE dbo.detailsInvoiceImportPr ADD priceImport DECIMAL(10, 2);

-- Altering dbo.InvoiceSell table
ALTER TABLE dbo.InvoiceSell ADD totalMoney DECIMAL(10, 2);

-- Creating the InvoiceReturn table
CREATE TABLE InvoiceReturn (
    idInvoiceReturn INT AUTO_INCREMENT PRIMARY KEY,
    idInvoiceSell INT,
    idCustomer INT,
    description NVARCHAR(255),
    totalReturn DECIMAL(10, 2),
    idUser INT,
    FOREIGN KEY (idInvoiceSell) REFERENCES dbo.InvoiceSell(idInvoiceSell),
    FOREIGN KEY (idCustomer) REFERENCES dbo.Customer(idCustomer),
    FOREIGN KEY (idUser) REFERENCES dbo.User(idUser)
);

-- Creating the DetailInvoiceReturn table
CREATE TABLE DetailInvoiceReturn (
    idDetailInvoiceReturn INT AUTO_INCREMENT PRIMARY KEY,
    idInvoiceReturn INT,
    idPrDetails INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (idInvoiceReturn) REFERENCES dbo.InvoiceReturn(idInvoiceReturn),
    FOREIGN KEY (idPrDetails) REFERENCES dbo.detailsProduct(idPrDeltails)
);


-- Altering table dbo.InvoiceReturn
ALTER TABLE dbo.InvoiceReturn ADD idUser INT;
ALTER TABLE dbo.InvoiceReturn ADD CONSTRAINT fk_InvoiceReturn_User FOREIGN KEY(idUser) REFERENCES dbo.User(idUser);

ALTER TABLE dbo.InvoiceReturn ADD dateCreateInvoice DATE;

-- Dropping stored procedure sp_statistical if it exists and creating a new one
DROP PROCEDURE IF EXISTS sp_statistical;
DELIMITER //
CREATE PROCEDURE sp_statistical (IN year INT, IN month INT)
BEGIN
    SELECT Products.idProduct AS idProduct, nameProduct, SUM(detailsInvoiceSELL.quatity) AS quantitySell  
    FROM dbo.detailsProduct 
    JOIN dbo.detailsInvoiceSELL ON detailsInvoiceSELL.idPrDetails = detailsProduct.idPrDeltails
    JOIN dbo.InvoiceSell ON InvoiceSell.idInvoiceSell = detailsInvoiceSELL.idInvoiceSell
    JOIN dbo.Products ON Products.idProduct = detailsProduct.idProduct
    WHERE YEAR(InvoiceSell.dateCreateInvoice) = year AND MONTH(InvoiceSell.dateCreateInvoice) = month
    GROUP BY Products.idProduct, nameProduct
    ORDER BY quantitySell DESC;
END //
DELIMITER ;

-- Executing the sp_statistical procedure
CALL sp_statistical(2021, 10);

-- Dropping stored procedure sp_revenue if it exists and creating a new one
DROP PROCEDURE IF EXISTS sp_revenue;
DELIMITER //
CREATE PROCEDURE sp_revenue (IN year INT)
BEGIN
    SELECT MONTH(InvoiceSell.dateCreateInvoice) AS MonthDate, 
           SUM(detailsInvoiceSELL.quatity) AS quantity,
           CAST(SUM(detailsInvoiceSELL.price * detailsInvoiceSELL.quatity) AS INT) AS totalSell, 
           CAST(SUM(totalReturn) AS INT) AS totalReturn, 
           CAST(SUM(detailsInvoiceSELL.price * detailsInvoiceSELL.quatity) - SUM(totalReturn) AS INT) AS revenue
    FROM dbo.detailsInvoiceSELL  
    JOIN dbo.InvoiceSell ON InvoiceSell.idInvoiceSell = detailsInvoiceSELL.idInvoiceSell
    LEFT JOIN dbo.InvoiceReturn ON InvoiceReturn.idInvoiceSell = InvoiceSell.idInvoiceSell
    WHERE YEAR(InvoiceSell.dateCreateInvoice) = year
    GROUP BY MONTH(InvoiceSell.dateCreateInvoice);
END //
DELIMITER ;

-- Executing the sp_revenue procedure
CALL sp_revenue(2021);

-- Creating the view
CREATE VIEW view_revenue AS
SELECT MONTH(InvoiceSell.dateCreateInvoice) AS MonthDate, 
       SUM(detailsInvoiceSELL.quatity) AS quantity,
       SUM(detailsInvoiceSELL.price * detailsInvoiceSELL.quatity) AS totalSell, 
       SUM(totalReturn) AS totalReturn, 
       SUM(detailsInvoiceSELL.price * detailsInvoiceSELL.quatity) - SUM(totalReturn) AS revenue
FROM dbo.detailsInvoiceSELL  
JOIN dbo.InvoiceSell ON InvoiceSell.idInvoiceSell = detailsInvoiceSELL.idInvoiceSell
LEFT JOIN dbo.InvoiceReturn ON InvoiceReturn.idInvoiceSell = InvoiceSell.idInvoiceSell
JOIN dbo.detailsProduct ON detailsProduct.idPrDeltails = detailsInvoiceSELL.idPrDetails
JOIN dbo.detailsInvoiceImportPr ON detailsInvoiceImportPr.idPrDeltails = detailsProduct.idPrDeltails
JOIN dbo.InvoiceImportPr ON InvoiceImportPr.idInvoice = detailsInvoiceImportPr.idInvoice
WHERE YEAR(InvoiceSell.dateCreateInvoice) = 2021
GROUP BY MONTH(InvoiceSell.dateCreateInvoice);


-------------------------------
--2/12/2021
-- Altering column types in dbo.InvoiceImportPr, dbo.InvoiceReturn, and dbo.InvoiceSell
ALTER TABLE dbo.InvoiceImportPr MODIFY COLUMN dateCreateInvoice DATETIME;
ALTER TABLE dbo.InvoiceReturn MODIFY COLUMN dateCreateInvoice DATETIME;
ALTER TABLE dbo.InvoiceSell MODIFY COLUMN dateCreateInvoice DATETIME;

-- Dropping stored procedure sp_Quantity if it exists and creating a new one
DROP PROCEDURE IF EXISTS sp_Quantity;
DELIMITER //
CREATE PROCEDURE sp_Quantity()
BEGIN
    SELECT name, IF(gender = 0, 'Nữ', 'Nam') AS gender, phoneNumber, SUM(quatity) AS SumBuy 
    FROM dbo.Customer 
    JOIN dbo.InvoiceSell ON InvoiceSell.idCustomer = Customer.idCustomer
    JOIN dbo.detailsInvoiceSELL ON detailsInvoiceSELL.idInvoiceSell = InvoiceSell.idInvoiceSell
    GROUP BY name, gender, phoneNumber;
END //
DELIMITER ;

-- Creating the InvoiceChangeProducts table
CREATE TABLE InvoiceChangeProducts (
    idInvoiceChangeProducts INT AUTO_INCREMENT PRIMARY KEY,
    idCustomer INT,
    idInvoiceSell INT,
    dateCreateInvoice DATETIME,
    idDetailsNew INT,
    idDetailsOld INT,
    idUser INT,
    description NVARCHAR(255),
    FOREIGN KEY (idCustomer) REFERENCES dbo.Customer(idCustomer),
    FOREIGN KEY (idInvoiceSell) REFERENCES dbo.InvoiceSell(idInvoiceSell),
    FOREIGN KEY (idDetailsOld) REFERENCES dbo.detailsInvoiceSELL(idDetailsInvoiceSELL),
    FOREIGN KEY (idDetailsNew) REFERENCES dbo.Products(idProduct),
    FOREIGN KEY (idUser) REFERENCES dbo.User(idUser)
);

-- Altering table dbo.InvoiceSell to add columns
ALTER TABLE dbo.InvoiceSell ADD moneyCustom DECIMAL(10, 2);
ALTER TABLE dbo.InvoiceSell ADD moneyReturn DECIMAL(10, 2);

-- Creating the DetailsInvoiceChange table
CREATE TABLE DetailsInvoiceChange (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idInvoiceChangeProducts INT,
    idDetailsPr INT,
    quantity INT,
    FOREIGN KEY (idInvoiceChangeProducts) REFERENCES dbo.InvoiceChangeProducts(idInvoiceChangeProducts),
    FOREIGN KEY (idDetailsPr) REFERENCES dbo.detailsProduct(idPrDeltails)
);

-- Creating the DetailsChangeProducts table
CREATE TABLE DetailsChangeProducts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idDetailsPr INT,
    idDetailsInvoiceChange INT,
    quantity INT,
    FOREIGN KEY (idDetailsInvoiceChange) REFERENCES dbo.DetailsInvoiceChange(id),
    FOREIGN KEY (idDetailsPr) REFERENCES dbo.detailsProduct(idPrDeltails)
);


-- Dropping columns and constraint from InvoiceChangeProducts table
ALTER TABLE InvoiceChangeProducts DROP COLUMN idDetailsNew;
ALTER TABLE InvoiceChangeProducts DROP FOREIGN KEY FK__InvoiceCh__idDet__671F4F74;
ALTER TABLE InvoiceChangeProducts DROP COLUMN idDetailsOld;

-- Selecting all rows from InvoiceChangeProducts table
SELECT * FROM dbo.InvoiceChangeProducts;

-- Selecting all rows from detailsProduct table
SELECT * FROM detailsProduct;

-- Inserting data into InvoiceSell table
INSERT INTO dbo.InvoiceSell (
    name,
    birthday,
    gender,
    phoneNumber,
    address,
    salary,
    role,
    status,
    email
) VALUES (
    N'Nguyễn Văn Đức',
    '2002-09-25',
    1,
    '0332429178',
    'Hà Nội',
    3000000,
    1,
    1,
    'ducit2509@gmail.com'
);

-- Selecting all rows from User table
SELECT * FROM dbo.User;

-- Inserting data into Account table
INSERT INTO dbo.Account (
    idUser,
    username,
    password
) VALUES (
    29,
    N'admin',
    N'123'
);
