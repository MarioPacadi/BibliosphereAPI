

--#############################################################

-- Accounts
INSERT INTO account (email, username, password, role_id)
VALUES ('admin@admin.com', 'admin', '$2a$10$Gjk7748.woF3SuMphhlGeulsrPNt3l.lZLLuZ4Z05EIMuy6A5aX6e', 1);
--password: admin123

INSERT INTO account (email, username, password, role_id)
VALUES ('user@user.com', 'user', '$2a$10$so/N/jYj4VHruPT0QmHtOuqk10KlU9FMkxnpDLeNyE6ZjQt8ZedJe', 2);
--password: user123

--#############################################################

-- Books
INSERT INTO book (title, author, imageUrl)
VALUES ('Ascendance of a Bookworm Part 1 Volume 1', 'Miya Kazuki', 'https://static.wikia.nocookie.net/ascendance-of-a-bookworm/images/6/6f/LN_P1V1-CoverEN.jpg');

INSERT INTO book (title, author, imageUrl)
VALUES ('Ascendance of a Bookworm Part 1 Volume 2', 'Miya Kazuki', 'https://static.wikia.nocookie.net/ascendance-of-a-bookworm/images/8/84/LN_P1V2-CoverEN.jpg');

INSERT INTO book (title, author, imageUrl)
VALUES ('Ascendance of a Bookworm Part 1 Volume 3', 'Miya Kazuki', 'https://static.wikia.nocookie.net/ascendance-of-a-bookworm/images/8/82/LN_P1V3-CoverEN.jpg');

INSERT INTO book (title, author, imageUrl)
VALUES ('Ascendance of a Bookworm Part 2 Volume 1', 'Miya Kazuki', 'https://static.wikia.nocookie.net/ascendance-of-a-bookworm/images/d/da/LN_P2V1-CoverEN.jpg');

--#############################################################

-- Comments for Book 1
INSERT INTO comment (text, book_id, user_id)
VALUES ('Enjoyed every page!', 1, 1);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Greatest book ever', 1, 1);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Interesting twists and turns.', 1, 1);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Highly recommended!', 1, 2);

-- Comments for Book 2
INSERT INTO comment (text, book_id, user_id)
VALUES ('Slice of life!', 2, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Family and friends', 2, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Money, money, money.', 2, 1);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Book lovers enjoyment', 2, 1);

-- Comments for Book 3
INSERT INTO comment (text, book_id, user_id)
VALUES ('You will survive!', 3, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Death by hugs', 3, 1);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Gods?', 3, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('We need more Money!', 3, 1);

-- Comments for Book 4
INSERT INTO comment (text, book_id, user_id)
VALUES ('Pray to the Gods!', 4, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Pizza', 4, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('Darth Myne', 4, 2);

INSERT INTO comment (text, book_id, user_id)
VALUES ('We need more food!', 4, 2);

--#############################################################

--Ratings
INSERT INTO rating (rating, comment_id)
VALUES (4, 1);

INSERT INTO rating (rating, comment_id)
VALUES (5, 2);

INSERT INTO rating (rating, comment_id)
VALUES (4, 3);

INSERT INTO rating (rating, comment_id)
VALUES (5, 4);

INSERT INTO rating (rating, comment_id)
VALUES (3, 5);

INSERT INTO rating (rating, comment_id)
VALUES (5, 6);

INSERT INTO rating (rating, comment_id)
VALUES (4, 7);

INSERT INTO rating (rating, comment_id)
VALUES (5, 8);

INSERT INTO rating (rating, comment_id)
VALUES (4, 9);

INSERT INTO rating (rating, comment_id)
VALUES (5, 10);

INSERT INTO rating (rating, comment_id)
VALUES (5, 11);

INSERT INTO rating (rating, comment_id)
VALUES (4, 12);

INSERT INTO rating (rating, comment_id)
VALUES (5, 13);

INSERT INTO rating (rating, comment_id)
VALUES (4, 14);

INSERT INTO rating (rating, comment_id)
VALUES (3, 15);

INSERT INTO rating (rating, comment_id)
VALUES (5, 16);
