CREATE DATABASE InvoiceManagementSystem;

USE InvoiceManagementSystem;

CREATE TABLE Customertbl (
    CustomerId INT AUTO_INCREMENT PRIMARY KEY,
    Customer_Name VARCHAR(40)
);

CREATE TABLE Orderstbl (
    InvoiceId INT AUTO_INCREMENT PRIMARY KEY,
    CustomerId INT,
    Order_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Paid', 'Unpaid'),
    Total_Amount DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (CustomerId) REFERENCES Customertbl(CustomerId)
);

CREATE TABLE Productstbl (
    ProductId INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(40),
    Price DECIMAL(6,2)
);

CREATE TABLE Orders_Quantitytbl (
    InvoiceId INT,
    ProductId INT,
    Quantity INT,
    PRIMARY KEY (InvoiceId, ProductId),
    FOREIGN KEY (InvoiceId) REFERENCES Orderstbl(InvoiceId),
    FOREIGN KEY (ProductId) REFERENCES Productstbl(ProductId)
);


INSERT INTO Customertbl (Customer_Name) VALUES
('CarlErisMedina'),
('CharlesAdrianAtienza'),
('GhinwardTindoy'),
('LesterTalento'),
('RuthGraceCabanlig');

INSERT INTO Productstbl (ProductName, Price) VALUES
('Top', 180.00),
('T-Shirt', 150.00),
('Short', 150.00),
('Pants', 200.00),
('Jacket', 250.00),
('Dress', 250.00);


INSERT INTO Orderstbl (CustomerId, Status) VALUES
(1, 'Paid'),
(2, 'Unpaid'),
(3, 'Unpaid'),
(4, 'Paid'),
(5, 'Paid');


INSERT INTO Orders_Quantitytbl (InvoiceId, ProductId, Quantity) VALUES
(1, 1, 2),
(2, 2, 1), 
(3, 3, 2), 
(4, 4, 3), 
(5, 5, 1);
