DELETE FROM dog_breed;
-- DELETE FROM user_role;
DELETE FROM account;
-- DELETE FROM role;

-- INSERT INTO dog_breed (breed_name, breed_type, breed_description, fur_color, origin, min_height_inches, max_height_inches, min_weight_pounds, max_weight_pounds, min_life_span, max_life_span, img_thumb, img_sourceurl, img_attribution, img_creative_commons)
INSERT INTO dog_breed (breed_name, breed_type, breed_description, fur_color, origin, min_height_inches, max_height_inches, min_weight_pounds, max_weight_pounds, min_life_span, max_life_span, img_thumb, img_sourceurl, img_attribution, img_creative_commons)
VALUES ('Afador', 'Mixed Breed Dogs', 'The Afador is a hybrid dog composed of an Afghan Hound and a Labrador Retriever that originated in Alaska around the year 2000.', 'black, brown, gray, red, fawn', 'Alaska', 20, 29, 50, 75, 10, 12, 'https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg', null, null, 0);

INSERT INTO dog_breed (breed_name, breed_type, breed_description, fur_color, origin, min_height_inches, max_height_inches, min_weight_pounds, max_weight_pounds, min_life_span, max_life_span, img_thumb, img_sourceurl, img_attribution, img_creative_commons)
VALUES ('Labrador Retriever', 'Retrievers', 'The Labrador Retriever is a medium to large-sized breed of retriever dog that originated in Canada.', 'black, yellow, chocolate', 'Canada', 21, 24, 55, 80, 10, 12, 'https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg', null, null, 0);

INSERT INTO dog_breed (breed_name, breed_type, breed_description, fur_color, origin, min_height_inches, max_height_inches, min_weight_pounds, max_weight_pounds, min_life_span, max_life_span, img_thumb, img_sourceurl, img_attribution, img_creative_commons)
VALUES ('German Shepherd', 'Herding Dogs', 'The German Shepherd is a breed of working dog known for its intelligence and versatility. It originated in Germany and is often used as a police, service, and search and rescue dog.', 'black, tan, sable, black and tan, black and red', 'Germany', 22, 26, 55, 90, 9, 13, 'https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg', null, null, 0);

INSERT INTO dog_breed (breed_name, breed_type, breed_description, fur_color, origin, min_height_inches, max_height_inches, min_weight_pounds, max_weight_pounds, min_life_span, max_life_span, img_thumb, img_sourceurl, img_attribution, img_creative_commons)
VALUES ('Golden Retriever', 'Retrievers', 'The Golden Retriever is a large-sized breed of retriever dog known for its friendly and intelligent nature. It originated in Scotland and is often used as a hunting and service dog.', 'golden, cream', 'Scotland', 21, 24, 55, 75, 10, 12, 'https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg', null, null, 0);

INSERT INTO account (email, username, password, role_id)
VALUES ('admin@admin.com', 'admin', '$2a$10$Gjk7748.woF3SuMphhlGeulsrPNt3l.lZLLuZ4Z05EIMuy6A5aX6e', 1);
--password: admin123

INSERT INTO account (email, username, password, role_id)
VALUES ('user@user.com', 'user', '$2a$10$so/N/jYj4VHruPT0QmHtOuqk10KlU9FMkxnpDLeNyE6ZjQt8ZedJe', 2);
--password: user123

-- INSERT INTO roles (role_name)
-- VALUES ('ROLE_ADMIN'),
--        ('ROLE_USER');
--
-- INSERT INTO user_role (user_id, role_id)
-- VALUES (2, 1),
--        (1, 2);
