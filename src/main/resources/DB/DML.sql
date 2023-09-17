
/* Store Procedures */

-- ------------------------------------------ USER ------------------------------------------ --

-- ----- FindUserById ----- --
DELIMITER //
CREATE PROCEDURE FindUserById(
    IN p_user_id INT
)
BEGIN
SELECT * FROM user WHERE user_id = p_user_id;
END //
DELIMITER ;


-- ----- SaveOrUpdateUser ----- --
DELIMITER //
CREATE PROCEDURE SaveOrUpdateUser
(IN p_user_id INT, IN p_user_name VARCHAR(255),
 IN p_mobile VARCHAR(15), IN p_email VARCHAR(255),
 IN p_password VARCHAR(255),IN p_biography varchar(12500) )

BEGIN
IF (p_user_id IS NULL)
THEN
	INSERT INTO user(user_name, mobile, email, password, biography)
	VALUES (p_user_name, p_mobile, p_email, p_password, p_biography);
ELSE
UPDATE user
    SET user_name = p_user_name, mobile = p_mobile, email = p_email,
        password = p_password, biography = p_biography
    WHERE user_id = p_user_id;
END IF;
END //
DELIMITER ;


-- ----- HasMobileExists ----- --
DELIMITER //
CREATE PROCEDURE HasMobileExists(IN p_mobile VARCHAR(15), OUT p_exists INT)
BEGIN
SELECT CASE
           WHEN COUNT(*) > 0 THEN 1
           ELSE 0
           END INTO p_exists
FROM user WHERE mobile = p_mobile;
END //
DELIMITER ;


-- ----- FindUserByMobile ----- --
DELIMITER //
CREATE PROCEDURE FindUserByMobile(
    IN p_mobile VARCHAR(15)
)
BEGIN
SELECT * FROM user WHERE mobile = p_mobile;
END //
DELIMITER ;



-- ----- FindUserByCommentId ----- --
DELIMITER //
CREATE PROCEDURE FindUserByCommentId(
    IN p_comment_id INT
)
BEGIN
SELECT u.*
FROM user u
         JOIN comment c ON u.user_id = c.user_id
WHERE c.comment_id = p_comment_id;
END //
DELIMITER ;


-- ------------------------------------------ POST ------------------------------------------ --

-- ----- SaveOrUpdatePost ----- --
DELIMITER //
CREATE PROCEDURE SaveOrUpdatePost(
    INOUT p_post_id INT,
    IN p_user_id INT,
    IN p_content TEXT
)
BEGIN
	IF p_post_id IS NULL THEN
    INSERT INTO post(user_id, content)
    VALUES (p_user_id, p_content);
    SET p_post_id = LAST_INSERT_ID();
ELSE
UPDATE post
SET user_id = p_user_id, content = p_content
WHERE post_id = p_post_id;
END IF;
SELECT * FROM post WHERE post_id = p_post_id;
END //
DELIMITER ;


-- ----- FindPostById ----- --
DELIMITER //
CREATE PROCEDURE FindPostById(
    IN p_post_id INT
)
BEGIN
SELECT * FROM post WHERE post_id = p_post_id;
END //
DELIMITER ;


-- ----- GetAllPosts ----- --
DELIMITER //
CREATE  PROCEDURE GetAllPosts()
BEGIN
    SELECT * FROM post;
END //
DELIMITER ;


-- ----- FindUserByPostId ----- --
DELIMITER //
CREATE PROCEDURE FindUserByPostId(IN p_post_id INT)
BEGIN
    SELECT u.user_name FROM user u
    JOIN post p
    ON u.user_id = p.user_id
    WHERE p.post_id = p_post_id;
END //
DELIMITER ;

-- ----- DeletePostByPostId ----- --
DELIMITER //
CREATE PROCEDURE DeletePostByPostId(
    IN p_post_id INT
)
BEGIN
    DELETE FROM post WHERE post_id = p_post_id;
END //
DELIMITER ;

-- ----- DeletePostAndComments ----- --
DELIMITER //
CREATE PROCEDURE DeletePostAndComments(
    IN p_post_id INT
)
BEGIN
DELETE FROM comment WHERE post_id = p_post_id;

DELETE FROM post WHERE post_id = p_post_id;
END //
DELIMITER ;

-- ------------------------------------------ COMMENT ------------------------------------------ --

-- ----- FindCommentsByPostId ----- --
DELIMITER //
CREATE PROCEDURE FindCommentsByPostId(
    IN p_post_id INT
)
BEGIN
SELECT *
    FROM comment
    WHERE post_id = p_post_id;
END //
DELIMITER ;


-- ----- SaveOrUpdateComment ----- --
DELIMITER //
CREATE PROCEDURE SaveOrUpdateComment(
    INOUT p_comment_id INT,
    IN p_user_id INT,
    IN p_post_id INT,
    IN p_content TEXT
)
BEGIN
IF p_comment_id IS NULL THEN
    INSERT INTO comment(user_id, post_id, content)
    VALUES (p_user_id, p_post_id, p_content);

    SET p_comment_id = LAST_INSERT_ID();
ELSE
    UPDATE comment
    SET user_id = p_user_id, post_id = p_post_id, content = p_content
    WHERE comment_id = p_comment_id;
END IF;
    SELECT * FROM comment WHERE comment_id = p_comment_id;
END //
DELIMITER ;




