/*
每次執行此sql都會把原本的資料表覆蓋掉
*/

DROP DATABASE IF EXISTS social_platform;
CREATE DATABASE IF NOT EXISTS social_platform;

USE social_platform;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user`
(
    `user_id`     int          NOT NULL AUTO_INCREMENT,
    `user_name`   varchar(255) NOT NULL,
    `mobile`       varchar(15)  NOT NULL,
    `email`       varchar(255) NOT NULL,
    `password`    varchar(255) NOT NULL,
    `cover_image` longblob DEFAULT NULL,
    `biography`   varchar(12500) DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `Mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for post
-- ----------------------------
CREATE TABLE `post`
(
    `post_id`    int  NOT NULL AUTO_INCREMENT,
    `user_id`    int  NOT NULL,
    `content`    varchar(12500) NOT NULL,
    `image`      longblob,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`post_id`),
    KEY          `user_id` (`user_id`),
    CONSTRAINT `fk_post_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- ----------------------------
-- Table structure for comment
-- ----------------------------
CREATE TABLE `comment`
(
    `comment_id` int  NOT NULL AUTO_INCREMENT,
    `user_id`    int  NOT NULL,
    `post_id`    int  NOT NULL,
    `content`    varchar(1250) NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`comment_id`),
    KEY          `user_id` (`user_id`),
    KEY          `post_id` (`post_id`),
    CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `fk_comment_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





