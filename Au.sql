-- audb.oauth_access_token definition

CREATE TABLE `oauth_access_token` (
                                      `token_id` varchar(256) DEFAULT NULL,
                                      `token` mediumblob,
                                      `authentication_id` varchar(128) NOT NULL,
                                      `user_name` varchar(256) DEFAULT NULL,
                                      `client_id` varchar(256) DEFAULT NULL,
                                      `authentication` mediumblob,
                                      `refresh_token` varchar(256) DEFAULT NULL,
                                      PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.oauth_approvals definition

CREATE TABLE `oauth_approvals` (
                                   `userId` varchar(128) DEFAULT NULL,
                                   `clientId` varchar(256) DEFAULT NULL,
                                   `scope` varchar(256) DEFAULT NULL,
                                   `status` varchar(10) DEFAULT NULL,
                                   `expiresAt` timestamp NULL DEFAULT NULL,
                                   `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.oauth_client_details definition

CREATE TABLE `oauth_client_details` (
                                        `client_id` varchar(128) NOT NULL,
                                        `client_secret` varchar(255) NOT NULL,
                                        `web_server_redirect_uri` varchar(2048) DEFAULT NULL,
                                        `scope` varchar(255) DEFAULT NULL,
                                        `access_token_validity` int DEFAULT NULL,
                                        `refresh_token_validity` int DEFAULT NULL,
                                        `resource_ids` varchar(1024) DEFAULT NULL,
                                        `authorized_grant_types` varchar(1024) DEFAULT NULL,
                                        `authorities` varchar(1024) DEFAULT NULL,
                                        `additional_information` varchar(4096) DEFAULT NULL,
                                        `autoapprove` varchar(255) DEFAULT NULL,
                                        PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.oauth_client_token definition

CREATE TABLE `oauth_client_token` (
                                      `token_id` varchar(256) DEFAULT NULL,
                                      `token` mediumblob,
                                      `authentication_id` varchar(128) NOT NULL,
                                      `user_name` varchar(256) DEFAULT NULL,
                                      `client_id` varchar(256) DEFAULT NULL,
                                      PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.oauth_code definition

CREATE TABLE `oauth_code` (
                              `code` varchar(256) DEFAULT NULL,
                              `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.oauth_refresh_token definition

CREATE TABLE `oauth_refresh_token` (
                                       `token_id` varchar(128) DEFAULT NULL,
                                       `token` mediumblob,
                                       `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.`role` definition

CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `remark` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.role_permission definition

CREATE TABLE `role_permission` (
                                   `role_id` int NOT NULL,
                                   `permission_id` int NOT NULL,
                                   PRIMARY KEY (`role_id`,`permission_id`),
                                   KEY `FK_role_module_module` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.permission definition

CREATE TABLE `permission` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.`user` definition

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(100) NOT NULL,
                        `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `enabled` tinyint NOT NULL,
                        `accountNonExpired` tinyint NOT NULL,
                        `credentialsNonExpired` tinyint NOT NULL,
                        `accountNonLocked` tinyint NOT NULL,
                        `first_name` varchar(100) DEFAULT NULL,
                        `last_name` varchar(100) DEFAULT NULL,
                        `deleted` tinyint DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.verification_token definition

CREATE TABLE `verification_token` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `type` varchar(25) NOT NULL,
                                      `token` varchar(250) DEFAULT NULL,
                                      `expiry_date` timestamp NULL DEFAULT NULL,
                                      `used_at` timestamp NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- audb.role_user definition

CREATE TABLE `role_user` (
                             `role_id` int DEFAULT NULL,
                             `user_id` int DEFAULT NULL,
                             KEY `FK_role_user_role` (`role_id`),
                             KEY `FK_role_user_user` (`user_id`),
                             CONSTRAINT `FK_role_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                             CONSTRAINT `FK_role_user_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO audb.oauth_client_details
(client_id, client_secret, web_server_redirect_uri, `scope`, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, authorities, additional_information, autoapprove)
VALUES('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code', 'read,write', 3600, 10000, 'mobile', 'authorization_code,password,refresh_token,implicit', NULL, '{}', NULL);
INSERT INTO audb.oauth_client_details
(client_id, client_secret, web_server_redirect_uri, `scope`, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, authorities, additional_information, autoapprove)
VALUES('web', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code', 'read,write', 3600, 10000, 'web', 'authorization_code,password,refresh_token,implicit', NULL, '{}', NULL);

INSERT INTO audb.`user`
(id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, first_name, last_name, deleted)
VALUES(4, 'tranngoc897', '{bcrypt}$2a$10$dGu6h8fkE1KdVtE8rSuAPOKGdZ/K9WMRC6d5ntP3WuWtSwOb.WfCO', 'tranngoc897@gmail.com', 1, 1, 1, 1, NULL, NULL, 0);

INSERT INTO audb.`role`
(id, name, description, remark)
VALUES(1, 'User', 'User', NULL);

INSERT INTO audb.permission
(id, name, description)
VALUES(1, 'READ', 'READ');
INSERT INTO audb.permission
(id, name, description)
VALUES(2, 'WRITE', 'WRITE');

INSERT INTO audb.role_permission
(role_id, permission_id)
VALUES(1, 1);

INSERT INTO audb.role_user
(role_id, user_id)
VALUES(1, 4);

