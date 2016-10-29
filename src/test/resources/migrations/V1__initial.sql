CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `roles` varchar(500) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `confirm_uuid` varchar(255) NOT NULL,
  `confirmed` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `confirm_uuid` (`confirm_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`id`, `roles`, `email`, `password`, `confirm_uuid`, `confirmed`) VALUES
        ('man4j@ya.ru', NULL, 'man4j@ya.ru', '$2a$10$9pLMk62hoFXyvmpMgE6LXuqNMavUm9yx6iQpVoB6/z3kmz1siQzqO', '264e641b-cec8-44f5-9120-28e4b4011d19', 1);
