CREATE TABLE `users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `roles` varchar(500) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `confirm_uuid` varchar(255) NOT NULL,
  `confirmed` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `confirm_uuid` (`confirm_uuid`),
  INDEX `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`roles`, `email`, `password`, `confirm_uuid`, `confirmed`) VALUES
        (NULL, 'man4j@ya.ru', '$2a$10$9pLMk62hoFXyvmpMgE6LXuqNMavUm9yx6iQpVoB6/z3kmz1siQzqO', '264e641b-cec8-44f5-9120-28e4b4011d19', 1);
        
CREATE TABLE `UserConnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255),
  `rank` int NOT NULL,
  `displayName` varchar(255),
  `profileUrl` varchar(512),
  `imageUrl` varchar(512),
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512),
  `refreshToken` varchar(512),
  `expireTime` bigint,
  PRIMARY KEY (`userId`, `providerId`, `providerUserId`));
  
CREATE INDEX `UserConnectionRank` on `UserConnection`(`userId`, `providerId`, `rank`);
CREATE INDEX `ProviderIdProviderUserId` on `UserConnection`(`providerId`, `providerUserId`);