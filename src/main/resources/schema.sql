IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'dog_breed')
    BEGIN
        CREATE TABLE dog_breed (
          id INT IDENTITY PRIMARY KEY,
          breedName VARCHAR(255) NOT NULL,
          breedType VARCHAR(255) NOT NULL,
          breedDescription VARCHAR(MAX) NOT NULL,
          furColor VARCHAR(255) NOT NULL,
          origin VARCHAR(255) NOT NULL,
          minHeightInches INT NOT NULL,
          maxHeightInches INT NOT NULL,
          minWeightPounds INT NOT NULL,
          maxWeightPounds INT NOT NULL,
          minLifeSpan INT NOT NULL,
          maxLifeSpan INT NOT NULL,
          imgThumb VARCHAR(MAX) NOT NULL,
          imgSourceURL VARCHAR(MAX),
          imgAttribution VARCHAR(MAX),
          imgCreativeCommons BIT NOT NULL
        );
    END;


IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'account')
    BEGIN
        CREATE TABLE account (
             id INT IDENTITY PRIMARY KEY,
             username VARCHAR(100) NOT NULL UNIQUE,
             password VARCHAR(1000) NOT NULL,
             email VARCHAR(1000) NULL,
             roleId int not null,
        );
    END;

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'role')
    BEGIN
        CREATE TABLE role (
           id INT IDENTITY PRIMARY KEY,
           role_name VARCHAR(100) NOT NULL UNIQUE
        );
    END;

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'user_role')
    BEGIN
        CREATE TABLE user_role (
            user_id INT NOT NULL,
            role_id INT NOT NULL,
            CONSTRAINT fk_account FOREIGN KEY (user_id) REFERENCES account(id),
            CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id)
        );
    END;
