
use social_platform;

-- --------------------- User ---------------------------

-- Insert or Update User --
DELIMITER //
CREATE PROCEDURE SaveOrUpdateUser(IN p_user_id INT, IN p_user_name VARCHAR(255), IN p_mobile VARCHAR(15), IN p_email VARCHAR(255), IN p_password VARCHAR(255))
BEGIN
IF (p_user_id IS NULL)
THEN
	INSERT INTO user(user_name, mobile, email, password)
	VALUES (p_user_name, p_mobile, p_email, p_password);
ELSE
UPDATE user
SET user_name = p_user_name, mobile = p_mobile, email = p_email, password = p_password
WHERE user_id = p_user_id;
END IF;
END //
DELIMITER ;

-- Check if email exists --
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

-- Find User By Email--
DELIMITER //
CREATE PROCEDURE FindUserByMobile(
    IN p_mobile VARCHAR(15)
)
BEGIN
SELECT * FROM user WHERE mobile = p_mobile;
END //
DELIMITER ;




