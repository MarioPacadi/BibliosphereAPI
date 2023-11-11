CREATE TABLE book (
    id INT PRIMARY KEY IDENTITY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    imageUrl VARCHAR(255),
    -- Add other fields as needed
);

CREATE TABLE account (
    id_user BIGINT PRIMARY KEY IDENTITY,
    email VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    role_id INT NULL,
    username VARCHAR(255) NULL
    -- Add other fields as needed
);

CREATE TABLE comment (
    id INT PRIMARY KEY IDENTITY,
    text TEXT NOT NULL,
    book_id INT REFERENCES book(id),
    user_id BIGINT REFERENCES account(id_user),
    -- Add other fields as needed
    CONSTRAINT FK_Comment_Book FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT FK_Comment_Account FOREIGN KEY (user_id) REFERENCES account(id_user)
);

CREATE TABLE rating (
    id INT PRIMARY KEY IDENTITY,
    rating INT NOT NULL,
    comment_id INT REFERENCES comment(id),
    -- Add other fields as needed
    CONSTRAINT FK_Rating_Comment FOREIGN KEY (comment_id) REFERENCES comment(id)
);
