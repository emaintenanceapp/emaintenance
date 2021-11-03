#
************************************************************
# Sequel Pro SQL dump
# Versão 4541.191352
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.1.37-MariaDB)
# Base de Dados: emaintenance_db
# Tempo de Geração: 2019-02-14 15:39:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


#
Dump da tabela address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`    int(11) unsigned DEFAULT NULL,
    `street`     varchar(255) NOT NULL DEFAULT '',
    `number`     varchar(255) NOT NULL DEFAULT '',
    `complement` varchar(255)          DEFAULT NULL,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY          `addr_user` (`user_id`),
    CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;

INSERT INTO `address` (`id`, `user_id`, `street`, `number`, `complement`, `created_at`, `updated_at`)
VALUES (1, 1, 'rua manoel pedro vieira, 810', '810', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:39:59'),
       (2, 2, 'paraguai', '2041', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:01'),
       (3, 3, 'emilio daroz ', '107', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:02'),
       (4, 4, 'rua lavinia moreira da silva', '145', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:03'),
       (5, 5, 'padre anchieta', '121', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:04'),
       (6, 6, 'rua amoroso costa', '254', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:06'),
       (7, 7, 'alaor martins', '312', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:07'),
       (8, 8, 'rua das violetas', '330', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:09'),
       (9, 9, 'francisco carlos ', '105', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:10'),
       (10, 10, 'torino', '95', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:12'),
       (11, 11, 'rua erotidas', '64', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:13'),
       (12, 12, 'r. orquideas', '169', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:14'),
       (13, 13, 'rua joffre motta', '44', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:14'),
       (14, 14, 'rua piauí', '17', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:15'),
       (15, 15, 'fernandes marques', '1229', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:16'),
       (16, 16, 'av. beta', '07', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:17'),
       (17, 17, 'abagiba', '674', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:23'),
       (18, 18, 'gumercindo araujo', '302', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:25'),
       (19, 19, 'rua 01, quadra 35', '35b', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:26'),
       (20, 20, 'rua piauí', '23d', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:27'),
       (21, 21, 'rua leopoldina araãºjo', '380', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:28'),
       (22, 22, 'rua conceiã§ã£o', '101', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:29'),
       (23, 23, 'rua benedetto bonfilgi', '755', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:30'),
       (24, 24, 'rua sã£o francisco', '17', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:30'),
       (25, 25, 'rua dona zulmira', '479', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:32'),
       (26, 26, 'rua mampituba', '740', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:32'),
       (27, 27, 'dezeseis', '151', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:34'),
       (28, 28, 'rua dos goitacazes', '375', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:35'),
       (29, 29, 'av lucio jose de meneses', '930', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:36'),
       (30, 30, 'caetano', '3457', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:38'),
       (31, 31, 'um nova ', '335', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:39'),
       (32, 32, 'sres area especial', '19', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:40'),
       (33, 33, 'islandia', '99', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:41'),
       (34, 34, 'independência', '700', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:42'),
       (35, 35, 'sebastiã£o thomaz de oliveira', '25', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:46'),
       (36, 36, 'nogueira', '185', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:48'),
       (37, 37, 'tv londrina', '12', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:49'),
       (38, 38, 'teofilo otoni', '222', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:51'),
       (39, 39, 'joã£o rasmussen', '244', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:53'),
       (40, 40, 'travessa elizeu araãºjo', '46', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:56'),
       (41, 41, 'av. dr. joão pessoa', '185', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:58'),
       (42, 42, 'travessa brandão', '4', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:40:59'),
       (43, 43, 'coqueiros', 'SN', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:00'),
       (44, 44, 'estrada m boi mirim', '820', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:01'),
       (45, 45, 'travessa dos comerciarios ', '5', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:02'),
       (46, 46, 'dos jacarandas', '30', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:03'),
       (47, 47, 'dona ermelinda pereira', '413', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:04'),
       (48, 48, 'rua projetada 02', '742', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:05'),
       (49, 49, 'samambaia', '96', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:07'),
       (50, 50, 'rua dos gerã¢nios', '110', 'casa 1', '2018-09-03 16:40:57', '2018-09-16 19:41:09');

/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_categories
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_categories`;

CREATE TABLE `app_categories`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `sub_of`     int(11) unsigned DEFAULT NULL,
    `name`       varchar(255) NOT NULL DEFAULT '',
    `type`       varchar(15)  NOT NULL DEFAULT '',
    `order_by`   int(11) NOT NULL DEFAULT '0',
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY          `sub_of` (`sub_of`),
    CONSTRAINT `sub_of` FOREIGN KEY (`sub_of`) REFERENCES `app_categories` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_categories` WRITE;
/*!40000 ALTER TABLE `app_categories` DISABLE KEYS */;

INSERT INTO `app_categories` (`id`, `sub_of`, `name`, `type`, `order_by`, `created_at`, `updated_at`)
VALUES (1, NULL, 'Salário', 'income', 0, '2018-11-29 08:30:32', '2018-11-29 09:10:21'),
       (2, NULL, 'Investimento', 'income', 1, '2018-11-29 08:30:41', '2018-12-04 21:03:30'),
       (3, NULL, 'Empréstimo', 'income', 1, '2018-11-29 08:30:53', '2018-12-04 21:03:35'),
       (4, NULL, 'Outras receitas', 'income', 2, '2018-11-29 08:31:21', '2018-11-29 09:09:59'),
       (5, NULL, 'Alimentação', 'expense', 0, '2018-11-29 08:32:15', '2018-11-29 08:32:15'),
       (6, NULL, 'Aluguel', 'expense', 0, '2018-11-29 08:32:19', '2018-12-04 21:03:44'),
       (7, NULL, 'Compras', 'expense', 0, '2018-11-29 08:32:33', '2018-11-29 08:32:33'),
       (8, NULL, 'Educação', 'expense', 0, '2018-11-29 08:32:34', '2018-11-29 08:32:38'),
       (9, NULL, 'Entretenimento', 'expense', 0, '2018-11-29 08:32:49', '2018-11-29 08:32:51'),
       (10, NULL, 'Impostos e taxas', 'expense', 0, '2018-11-29 08:32:57', '2018-11-29 08:32:57'),
       (11, NULL, 'Saúde', 'expense', 0, '2018-11-29 08:33:08', '2018-11-29 08:33:31'),
       (12, NULL, 'Serviços', 'expense', 0, '2018-11-29 08:33:13', '2018-11-29 08:33:32'),
       (13, NULL, 'Viagen', 'expense', 0, '2018-11-29 08:33:23', '2018-12-04 21:03:56'),
       (14, NULL, 'Outras despesas', 'expense', 2, '2018-11-29 08:33:29', '2018-11-29 09:10:00');

/*!40000 ALTER TABLE `app_categories` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_credit_cards
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_credit_cards`;

CREATE TABLE `app_credit_cards`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`     int(11) unsigned NOT NULL,
    `brand`       varchar(20)  NOT NULL DEFAULT '',
    `last_digits` varchar(11)  NOT NULL DEFAULT '',
    `cvv`         varchar(11)  NOT NULL DEFAULT '',
    `hash`        varchar(255) NOT NULL DEFAULT '',
    `status`      varchar(255)          DEFAULT 'active',
    `created_at`  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY           `credit_cards_user` (`user_id`),
    CONSTRAINT `credit_cards_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_credit_cards` WRITE;
/*!40000 ALTER TABLE `app_credit_cards` DISABLE KEYS */;

INSERT INTO `app_credit_cards` (`id`, `user_id`, `brand`, `last_digits`, `cvv`, `hash`, `status`, `created_at`,
                                `updated_at`)
VALUES (1, 1, 'visa', '5386', '978', 'card_cjqh0engf02q34ka6ewd27xk8ya', 'active', '2019-01-03 17:34:27',
        '2019-02-12 08:53:42'),
       (2, 2, 'mastercard', '7058', '651', 'card_cjqmkmrtr003d6qa6d0ll1z9osa', 'active', '2019-01-07 14:59:29',
        '2019-02-12 08:53:41'),
       (3, 3, 'mastercard', '6013', '1234', 'card_cjq0ww3je014gxy6advoyhyhgx', 'active', '2019-01-04 13:52:55',
        '2019-02-12 08:53:39'),
       (4, 4, 'mastercard', '531', '45', 'card_cjqh0hgx302g68ja6eglnwamif', 'active', '2019-01-03 17:36:39',
        '2019-02-12 08:53:44'),
       (5, 51, 'amex', '9195', '6319', 'card_cjqh11uam00oksca6dz4mugccv', 'active', '2019-01-03 17:52:29',
        '2019-02-12 08:53:46'),
       (6, 51, 'diners', '7885', '888', 'card_cjqh1306j02r54ka6e8nu357kv', 'active', '2019-01-03 17:53:23',
        '2019-02-12 08:53:47');

/*!40000 ALTER TABLE `app_credit_cards` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_invoices
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_invoices`;

CREATE TABLE `app_invoices`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`       int(11) unsigned NOT NULL,
    `wallet_id`     int(11) unsigned NOT NULL,
    `category_id`   int(11) unsigned NOT NULL,
    `invoice_of`    int(11) unsigned DEFAULT NULL,
    `description`   varchar(255)   NOT NULL DEFAULT '',
    `type`          varchar(15)    NOT NULL DEFAULT '',
    `value`         decimal(10, 2) NOT NULL,
    `currency`      varchar(5)     NOT NULL DEFAULT 'BRL',
    `due_at`        date           NOT NULL,
    `repeat_when`   varchar(10)    NOT NULL DEFAULT '',
    `period`        varchar(10)    NOT NULL DEFAULT 'month',
    `enrollments`   int(11) DEFAULT NULL,
    `enrollment_of` int(11) DEFAULT NULL,
    `status`        varchar(10)    NOT NULL DEFAULT 'unpaid',
    `created_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY             `app_user` (`user_id`),
    KEY             `app_wallet` (`wallet_id`),
    KEY             `app_category` (`category_id`),
    KEY             `app_invoice` (`invoice_of`),
    CONSTRAINT `app_category` FOREIGN KEY (`category_id`) REFERENCES `app_categories` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `app_invoice` FOREIGN KEY (`invoice_of`) REFERENCES `app_invoices` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `app_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `app_wallet` FOREIGN KEY (`wallet_id`) REFERENCES `app_wallets` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_invoices` WRITE;
/*!40000 ALTER TABLE `app_invoices` DISABLE KEYS */;

INSERT INTO `app_invoices` (`id`, `user_id`, `wallet_id`, `category_id`, `invoice_of`, `description`, `type`, `value`,
                            `currency`, `due_at`, `repeat_when`, `period`, `enrollments`, `enrollment_of`, `status`,
                            `created_at`, `updated_at`)
VALUES (1, 51, 22, 1, NULL, 'Salário UpInside', 'fixed_income', 10000.00, 'BRL', '2021-07-15', 'fixed', 'month', 0, 1,
        'paid', '2018-12-26 14:59:56', '2018-12-26 14:59:56'),
       (2, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2021-07-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 14:59:56', '2018-12-26 14:59:56'),
       (3, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2018-11-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 14:59:56', '2018-12-26 14:59:56'),
       (4, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2018-12-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 14:59:56', '2018-12-26 14:59:56'),
       (5, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2019-01-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 14:59:56', '2019-01-25 13:38:46'),
       (6, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2019-02-15', 'fixed', 'month', 0, 1, 'unpaid',
        '2018-12-26 14:59:56', '2018-12-26 14:59:56'),
       (7, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2019-03-15', 'fixed', 'month', 0, 1, 'unpaid',
        '2018-12-26 14:59:56', '2018-12-26 14:59:56'),
       (8, 51, 22, 2, NULL, 'Investimentos', 'fixed_income', 1220.00, 'BRL', '2018-12-10', 'fixed', 'month', 0, 1,
        'paid', '2018-12-26 15:00:18', '2018-12-26 15:00:18'),
       (9, 51, 22, 2, 8, 'Investimentos', 'income', 1220.00, 'BRL', '2018-12-10', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:19', '2018-12-26 15:00:19'),
       (10, 51, 22, 2, 8, 'Investimentos', 'income', 1220.00, 'BRL', '2019-01-10', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:19', '2019-01-15 13:09:42'),
       (11, 51, 22, 2, 8, 'Investimentos', 'income', 1220.00, 'BRL', '2019-02-10', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:19', '2019-02-14 13:37:47'),
       (12, 51, 22, 2, 8, 'Investimentos', 'income', 1220.00, 'BRL', '2019-03-10', 'fixed', 'month', 0, 1, 'unpaid',
        '2018-12-26 15:00:19', '2018-12-26 15:00:19'),
       (13, 51, 22, 6, NULL, 'Aluguel Quinta', 'fixed_expense', 5000.00, 'BRL', '2021-07-15', 'fixed', 'month', 0, 1,
        'paid', '2018-12-26 15:00:40', '2018-12-26 15:00:40'),
       (14, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2021-07-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:41', '2018-12-26 15:00:41'),
       (15, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2018-11-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:41', '2018-12-26 15:00:41'),
       (16, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2018-12-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:41', '2018-12-26 15:00:41'),
       (17, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2019-01-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:00:41', '2019-01-25 13:38:44'),
       (18, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2019-02-15', 'fixed', 'month', 0, 1, 'unpaid',
        '2018-12-26 15:00:41', '2018-12-26 15:00:41'),
       (19, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2019-03-15', 'fixed', 'month', 0, 1, 'unpaid',
        '2018-12-26 15:00:41', '2018-12-26 15:00:41'),
       (20, 51, 22, 6, NULL, 'Condomínio Quinta', 'fixed_expense', 1000.00, 'BRL', '2021-07-15', 'fixed', 'month', 0, 1,
        'paid', '2018-12-26 15:01:04', '2018-12-26 15:01:04'),
       (21, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2021-07-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:01:05', '2018-12-26 15:01:05'),
       (22, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2018-11-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:01:05', '2018-12-26 15:01:05'),
       (23, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2018-12-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:01:05', '2018-12-26 15:01:05'),
       (24, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2019-01-15', 'fixed', 'month', 0, 1, 'paid',
        '2018-12-26 15:01:05', '2019-01-25 13:38:44'),
       (25, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2019-02-15', 'fixed', 'month', 0, 1,
        'unpaid', '2018-12-26 15:01:05', '2018-12-26 15:01:05'),
       (26, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2019-03-15', 'fixed', 'month', 0, 1,
        'unpaid', '2018-12-26 15:01:05', '2018-12-26 15:01:05'),
       (27, 51, 22, 14, NULL, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2018-11-28', 'enrollment', 'month', 15, 1,
        'paid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (28, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2018-12-28', 'enrollment', 'month', 15, 2,
        'paid', '2018-12-26 15:01:37', '2019-01-04 13:51:55'),
       (29, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-01-28', 'enrollment', 'month', 15, 3,
        'paid', '2018-12-26 15:01:37', '2019-02-01 10:07:14'),
       (30, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-02-28', 'enrollment', 'month', 15, 4,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (31, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-03-28', 'enrollment', 'month', 15, 5,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (32, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-04-28', 'enrollment', 'month', 15, 6,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (33, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-05-28', 'enrollment', 'month', 15, 7,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (34, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-06-28', 'enrollment', 'month', 15, 8,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (35, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-07-28', 'enrollment', 'month', 15, 9,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (36, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-08-28', 'enrollment', 'month', 15, 10,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (37, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-09-28', 'enrollment', 'month', 15, 11,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (38, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-10-28', 'enrollment', 'month', 15, 12,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (39, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-11-28', 'enrollment', 'month', 15, 13,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (40, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2019-12-28', 'enrollment', 'month', 15, 14,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (41, 51, 22, 14, 27, 'Parcela Carro', 'expense', 3800.00, 'BRL', '2020-01-28', 'enrollment', 'month', 15, 15,
        'unpaid', '2018-12-26 15:01:37', '2018-12-26 15:01:37'),
       (42, 51, 22, 1, NULL, 'Projeto Academia', 'income', 3800.00, 'BRL', '2018-12-28', 'single', 'month', 0, 1,
        'paid', '2018-12-26 15:01:54', '2019-01-04 13:51:53'),
       (43, 51, 23, 4, NULL, 'Projeto', 'income', 5000.00, 'BRL', '2019-01-05', 'single', 'month', 0, 1, 'paid',
        '2019-01-03 11:36:53', '2019-01-07 15:11:52'),
       (44, 51, 23, 6, NULL, 'Aluguel', 'fixed_expense', 4200.00, 'BRL', '2018-12-03', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 11:37:26', '2019-01-03 11:37:26'),
       (45, 51, 23, 6, 44, 'Aluguel', 'expense', 4200.00, 'BRL', '2018-12-03', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 11:37:26', '2019-01-03 11:37:26'),
       (46, 51, 23, 6, 44, 'Aluguel', 'expense', 4200.00, 'BRL', '2019-01-03', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 11:37:26', '2019-01-03 11:37:26'),
       (47, 51, 23, 6, 44, 'Aluguel', 'expense', 4200.00, 'BRL', '2019-02-03', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 11:37:26', '2019-02-07 11:12:15'),
       (48, 51, 23, 6, 44, 'Aluguel', 'expense', 4200.00, 'BRL', '2019-03-03', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-03 11:37:26', '2019-01-03 11:37:26'),
       (49, 51, 23, 6, 44, 'Aluguel', 'expense', 4200.00, 'BRL', '2019-04-03', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-03 11:37:26', '2019-01-03 11:37:26'),
       (54, 51, 23, 1, NULL, 'Lucros', 'fixed_income', 10000.00, 'BRL', '2018-12-05', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:13', '2019-01-03 13:27:13'),
       (55, 51, 23, 1, 54, 'Lucros', 'income', 10000.00, 'BRL', '2018-12-05', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:14', '2019-01-03 13:27:14'),
       (56, 51, 23, 1, 54, 'Lucros', 'income', 10000.00, 'BRL', '2019-01-05', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:14', '2019-01-07 15:11:52'),
       (57, 51, 23, 1, 54, 'Lucros', 'income', 10000.00, 'BRL', '2019-02-05', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:14', '2019-02-07 11:12:16'),
       (58, 51, 23, 1, 54, 'Lucros', 'income', 10000.00, 'BRL', '2019-03-05', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-03 13:27:14', '2019-01-03 13:27:14'),
       (59, 51, 23, 12, NULL, 'Anúncios', 'fixed_expense', 8000.00, 'BRL', '2018-12-10', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:46', '2019-01-03 13:27:46'),
       (60, 51, 23, 12, 59, 'Anúncios', 'expense', 8000.00, 'BRL', '2018-12-10', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:47', '2019-01-03 13:27:47'),
       (61, 51, 23, 12, 59, 'Anúncios', 'expense', 8000.00, 'BRL', '2019-01-10', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:47', '2019-01-25 13:38:43'),
       (62, 51, 23, 12, 59, 'Anúncios', 'expense', 8000.00, 'BRL', '2019-02-10', 'fixed', 'month', 0, 1, 'paid',
        '2019-01-03 13:27:47', '2019-02-14 13:37:53'),
       (63, 51, 23, 12, 59, 'Anúncios', 'expense', 8000.00, 'BRL', '2019-03-10', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-03 13:27:47', '2019-01-03 13:27:47'),
       (64, 51, 22, 1, NULL, 'Teste FREE', 'income', 500.00, 'BRL', '2019-01-10', 'single', 'month', 1, 1, 'paid',
        '2019-01-03 18:09:39', '2019-01-15 13:09:42'),
       (65, 51, 23, 1, NULL, 'PRO teste', 'income', 500.00, 'BRL', '2019-01-04', 'single', 'month', 1, 1, 'paid',
        '2019-01-03 18:10:20', '2019-01-04 13:51:54'),
       (66, 51, 23, 1, 54, 'Lucros', 'income', 10000.00, 'BRL', '2019-04-05', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-05 09:52:55', '2019-01-05 09:52:55'),
       (67, 51, 23, 2, NULL, 'Teste', 'income', 3434.44, 'BRL', '2019-01-07', 'single', 'month', 1, 1, 'paid',
        '2019-01-07 15:11:46', '2019-01-07 15:11:46'),
       (68, 51, 22, 2, 8, 'Investimentos', 'income', 1220.00, 'BRL', '2019-04-10', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-10 11:11:18', '2019-01-10 11:11:18'),
       (69, 51, 23, 12, 59, 'Anúncios', 'expense', 8000.00, 'BRL', '2019-04-10', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-10 11:11:18', '2019-01-10 11:11:18'),
       (70, 51, 22, 1, 1, 'Salário UpInside', 'income', 10000.00, 'BRL', '2019-04-15', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-15 11:37:36', '2019-01-15 11:37:36'),
       (71, 51, 22, 6, 13, 'Aluguel Quinta', 'expense', 5000.00, 'BRL', '2019-04-15', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-01-15 11:37:36', '2019-01-15 11:37:36'),
       (72, 51, 22, 6, 20, 'Condomínio Quinta', 'expense', 1000.00, 'BRL', '2019-04-15', 'fixed', 'month', 0, 1,
        'unpaid', '2019-01-15 11:37:36', '2019-01-15 11:37:36'),
       (73, 51, 23, 6, 44, 'Aluguel', 'expense', 4200.00, 'BRL', '2019-05-03', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-02-03 14:36:58', '2019-02-03 14:36:58'),
       (74, 51, 23, 1, 54, 'Lucros', 'income', 10000.00, 'BRL', '2019-05-05', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-02-06 08:25:54', '2019-02-06 08:25:54'),
       (75, 51, 22, 2, 8, 'Investimentos', 'income', 1220.00, 'BRL', '2019-05-10', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-02-14 13:37:35', '2019-02-14 13:37:35'),
       (76, 51, 23, 12, 59, 'Anúncios', 'expense', 8000.00, 'BRL', '2019-05-10', 'fixed', 'month', 0, 1, 'unpaid',
        '2019-02-14 13:37:35', '2019-02-14 13:37:35');

/*!40000 ALTER TABLE `app_invoices` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_orders`;

CREATE TABLE `app_orders`
(
    `id`              int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`         int(11) unsigned DEFAULT NULL,
    `card_id`         int(11) unsigned DEFAULT NULL,
    `subscription_id` int(11) unsigned DEFAULT NULL,
    `transaction`     varchar(255)            DEFAULT NULL,
    `amount`          decimal(10, 2) NOT NULL,
    `status`          varchar(20)    NOT NULL DEFAULT '',
    `created_at`      timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY               `orders_user` (`user_id`),
    KEY               `orders_credit_card` (`card_id`),
    KEY               `orders_subscription` (`subscription_id`),
    CONSTRAINT `orders_credit_card` FOREIGN KEY (`card_id`) REFERENCES `app_credit_cards` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `orders_subscription` FOREIGN KEY (`subscription_id`) REFERENCES `app_subscriptions` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `orders_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_orders` WRITE;
/*!40000 ALTER TABLE `app_orders` DISABLE KEYS */;

INSERT INTO `app_orders` (`id`, `user_id`, `card_id`, `subscription_id`, `transaction`, `amount`, `status`,
                          `created_at`, `updated_at`)
VALUES (5, 51, 4, 4, '4736404', 50.00, 'paid', '2019-01-03 17:38:19', '2019-01-03 17:38:19'),
       (6, 51, 5, 4, '4736461', 50.00, 'paid', '2019-01-03 17:52:31', '2019-01-03 17:52:31'),
       (7, 51, 6, 4, '4738811', 50.00, 'paid', '2019-01-04 11:12:17', '2019-01-04 11:12:17'),
       (8, 51, 6, 4, '4738816', 50.00, 'paid', '2019-01-04 11:13:11', '2019-01-04 11:13:11'),
       (9, 51, 6, 4, '4738824', 50.00, 'paid', '2019-01-04 11:14:49', '2019-01-04 11:14:49'),
       (10, 51, 6, 4, '4738839', 50.00, 'resused', '2019-01-04 11:19:54', '2019-01-04 11:20:55'),
       (11, 51, 6, 4, '4738892', 50.00, 'paid', '2019-01-04 11:32:31', '2019-01-04 11:32:31'),
       (12, 51, 6, 4, '4738898', 50.00, 'paid', '2019-01-04 11:34:01', '2019-01-04 11:34:01'),
       (13, 51, 6, 4, '4738905', 50.00, 'paid', '2019-01-04 11:35:38', '2019-01-04 11:35:38'),
       (14, 51, 6, 4, '4738916', 50.00, 'paid', '2019-01-04 11:36:43', '2019-01-04 11:36:43');

/*!40000 ALTER TABLE `app_orders` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_plans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_plans`;

CREATE TABLE `app_plans`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)   NOT NULL DEFAULT '',
    `period`     varchar(11)    NOT NULL DEFAULT '',
    `period_str` varchar(11)    NOT NULL DEFAULT '',
    `price`      decimal(10, 2) NOT NULL,
    `status`     varchar(11)    NOT NULL DEFAULT '',
    `created_at` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_plans` WRITE;
/*!40000 ALTER TABLE `app_plans` DISABLE KEYS */;

INSERT INTO `app_plans` (`id`, `name`, `period`, `period_str`, `price`, `status`, `created_at`, `updated_at`)
VALUES (1, 'PRO', '1month', 'mês', 5.00, 'active', '2018-12-21 07:02:22', '2019-01-03 16:45:18'),
       (2, 'PRO', '1year', 'ano', 50.00, 'active', '2018-12-21 07:02:57', '2019-02-06 05:57:49'),
       (3, 'EXPERT', '1month', 'mês', 75.00, 'inactive', '2018-12-21 07:04:02', '2018-12-23 19:56:33');

/*!40000 ALTER TABLE `app_plans` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_subscriptions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_subscriptions`;

CREATE TABLE `app_subscriptions`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`     int(11) unsigned DEFAULT NULL,
    `plan_id`     int(11) unsigned DEFAULT NULL,
    `card_id`     int(11) unsigned DEFAULT NULL,
    `status`      varchar(10) NOT NULL DEFAULT 'active' COMMENT 'active,inactive,past_due,canceled',
    `pay_status`  varchar(10) NOT NULL DEFAULT 'active' COMMENT 'active,inactive,past_due,canceled',
    `started`     date        NOT NULL,
    `due_day`     int(2) NOT NULL,
    `next_due`    date        NOT NULL,
    `last_charge` date        NOT NULL,
    `created_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY           `subscription_user` (`user_id`),
    KEY           `subscription_card` (`card_id`),
    KEY           `subscription_plan` (`plan_id`),
    CONSTRAINT `subscription_card` FOREIGN KEY (`card_id`) REFERENCES `app_credit_cards` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `subscription_plan` FOREIGN KEY (`plan_id`) REFERENCES `app_plans` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `subscription_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_subscriptions` WRITE;
/*!40000 ALTER TABLE `app_subscriptions` DISABLE KEYS */;

INSERT INTO `app_subscriptions` (`id`, `user_id`, `plan_id`, `card_id`, `status`, `pay_status`, `started`, `due_day`,
                                 `next_due`, `last_charge`, `created_at`, `updated_at`)
VALUES (4, 51, 2, 6, 'active', 'active', '2019-01-03', 28, '2019-10-22', '2021-07-22', '2019-01-03 17:38:19',
        '2019-02-06 14:51:49'),
       (5, 2, 1, 2, 'active', 'active', '2019-02-03', 0, '2020-03-03', '2020-03-03', '2019-02-03 13:38:24',
        '2019-02-06 06:14:48'),
       (6, 1, 2, 1, 'active', 'active', '2019-02-03', 0, '2020-03-03', '2020-03-03', '2019-02-03 13:42:57',
        '2019-02-06 06:14:47'),
       (7, 3, 2, 3, 'active', 'canceled', '2019-02-03', 0, '2020-03-03', '2020-03-03', '2019-02-06 06:06:52',
        '2019-02-06 06:14:49'),
       (8, 4, 1, 4, 'active', 'active', '2019-02-03', 0, '2020-03-03', '2020-03-03', '2019-02-06 06:13:29',
        '2019-02-06 06:14:49');

/*!40000 ALTER TABLE `app_subscriptions` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela app_wallets
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_wallets`;

CREATE TABLE `app_wallets`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`    int(11) unsigned NOT NULL,
    `wallet`     varchar(255) NOT NULL DEFAULT '',
    `free`       int(1) NOT NULL DEFAULT '0',
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY          `wallet_user` (`user_id`),
    CONSTRAINT `wallet_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `app_wallets` WRITE;
/*!40000 ALTER TABLE `app_wallets` DISABLE KEYS */;

INSERT INTO `app_wallets` (`id`, `user_id`, `wallet`, `free`, `created_at`, `updated_at`)
VALUES (22, 51, 'Home', 1, '2018-12-26 10:08:43', '2019-01-03 13:51:52'),
       (23, 51, 'UpInside', 0, '2019-01-03 11:03:35', '2019-01-03 13:51:54');

/*!40000 ALTER TABLE `app_wallets` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela categories
# ------------------------------------------------------------

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) NOT NULL DEFAULT '',
    `uri`         varchar(255) NOT NULL DEFAULT '',
    `description` text         NOT NULL,
    `cover`       varchar(255)          DEFAULT NULL,
    `type`        varchar(50)  NOT NULL DEFAULT 'post' COMMENT 'post, page, etc',
    `created_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;

INSERT INTO `categories` (`id`, `title`, `uri`, `description`, `cover`, `type`, `created_at`, `updated_at`)
VALUES (1, 'Controle', 'controle',
        'Dicas e sacadas sobre como controlar suas contas com CaféControl. Vamos tomar um ótimo café?', NULL, 'post',
        '2021-07-22 15:24:12', '2021-07-22 15:24:12'),
       (2, 'Contas', 'contas',
        'Dicas e sacadas sobre como controlar suas contas com CaféControl. Vamos tomar um ótimo café?',
        'images/2018/10/aprenda-a-criar-um-componente-de-notificacao-para-seu-site-1527515035.jpg', 'post',
        '2018-11-01 16:32:57', '2019-02-07 07:35:54'),
       (3, 'Finanças', 'financas',
        'Dicas e sacadas sobre como controlar suas contas com CaféControl. Vamos tomar um ótimo café?', NULL, 'post',
        '2018-11-01 16:33:05', '2018-11-01 16:33:27');

/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela faq_channels
# ------------------------------------------------------------

DROP TABLE IF EXISTS `faq_channels`;

CREATE TABLE `faq_channels`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `channel`     varchar(255)       DEFAULT NULL,
    `description` text,
    `created_at`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `faq_channels` WRITE;
/*!40000 ALTER TABLE `faq_channels` DISABLE KEYS */;

INSERT INTO `faq_channels` (`id`, `channel`, `description`, `created_at`, `updated_at`)
VALUES (1, 'Sobre o CaféControl', 'Saiba mais sobre o CaféControl', '2021-07-21 09:24:51', '2021-07-21 09:27:21'),
       (10, 'Sobre CMS CaféAdmin', 'Canal criado apenas para gerar paginação na implementação da tela de FAQs',
        '2019-02-07 08:05:31', '2019-02-07 08:33:16');

/*!40000 ALTER TABLE `faq_channels` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela faq_questions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `faq_questions`;

CREATE TABLE `faq_questions`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `channel_id` int(11) unsigned NOT NULL,
    `question`   varchar(255) NOT NULL DEFAULT '',
    `response`   text         NOT NULL,
    `order_by`   int(11) unsigned DEFAULT '1',
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY          `channel_id` (`channel_id`),
    CONSTRAINT `channel_id` FOREIGN KEY (`channel_id`) REFERENCES `faq_channels` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `faq_questions` WRITE;
/*!40000 ALTER TABLE `faq_questions` DISABLE KEYS */;

INSERT INTO `faq_questions` (`id`, `channel_id`, `question`, `response`, `order_by`, `created_at`, `updated_at`)
VALUES (1, 1, 'O CaféControl é gratuito?',
        '<p>Sim, o CaféControl é gratuito e com ele você pode usar os recursos de controle e automação sem qualquer custo ou cobrança.</p><p>No futuro pretendemos ter planos com recursos premium onde você terá ainda mais controle, este será um plano pago, mas você poderá optar por usa-lo ou continuar no plano gratuito.</p>',
        1, '2021-07-21 09:28:11', '2021-07-21 09:48:41'),
       (2, 1, 'O que fazer com o CaféControl?',
        '<p>Com o CaféControl você pode lançar suas contas a receber ou a pagar, além disso pode lançar recorrências e usufruir de poderosas automações de controle, tudo de forma muito simples.</p><p>Com as contas organizadas em seu painel, você passa a ter acesso a relatórios e gráficos incríveis, além de ser notificado sempre que uma ação for necessária, como validar ou pagar uma conta.</p>',
        2, '2021-07-21 09:30:04', '2021-07-21 09:49:17'),
       (4, 1, 'Como usar o CaféControl?',
        '<p>Para usar o CaféControl é simples, basta se cadastrar em nossa plataforma e começar a cadastrar suas contas.</p><p>Detalhando contas rotineiras e recorentes, todas com valor, categorias e informações de controle. A partir disso nosso APP vai gerar automações e relatórios para te ajudar a controlar.</p><p>Simples, fácil e gratuito!</p>',
        3, '2021-07-22 08:04:00', '2021-07-22 08:06:41'),
       (5, 1, 'De onde surgiu o CaféControl?',
        '<p>O CaféControl é um projeto desenvolvido na formação Full Stack PHP Developer da UpInside Treinamentos, onde o aluno tem acesso do zero ao expert em uma formação completa sobre ferramentas, HTML, CSS e jQuery.</p><p>Uma formação aprofundada com foco em PHP onde desenvolvemos esse e vários outros projetos a partir do zero.</p><p>Quer saber mais? <a target=\'_blank\' href=\'https://www.upinside.com.br/fsphp\' title=\'Full Stack PHP Developer\'>Acesse aqui</a> e conheça a formação!</p>',
        4, '2021-07-22 08:07:01', '2021-07-22 08:09:46'),
       (6, 1, 'Sobre a UpInside Treinamentos',
        '<p>A UpInside Treinamentos é uma escola de formação profissional em desenvolvimento web e programação. Hoje eleita a melhor do Brasil no segmento, tendo reconhecimento em mais de 17 países da AL.</p>',
        5, '2021-07-22 08:10:32', '2021-07-22 08:11:35'),
       (8, 1, 'Ainda com dúvidas?',
        '<p>Caso ainda tenha qualquer dúvida, fique a vontade para entrar em contato consoco em nossos canais de atendimento. Estamos aqui para te ajudar a controlar suas contas enquanto toma um ótimo café :)</p>',
        6, '2021-07-22 08:11:58', '2021-07-22 08:12:42'),
       (9, 10, 'Tudo já está implementado e testado?',
        'Todo o MVP do painel foi implementado e testado. Óbvio que cabe a cada um melhorar e gerar mais valor nessa que pode ser uma incrível ferramenta.',
        1, '2019-02-07 08:34:10', '2019-02-07 11:57:57'),
       (10, 10, 'Teremos mais implementações no curso?',
        'No curso não, mas teremos HandOns (lives) de implementação para que possamos nos aprofundar cada vez mais na ferramenta.',
        1, '2019-02-07 08:35:06', '2019-02-07 11:57:59');

/*!40000 ALTER TABLE `faq_questions` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela mail_queue
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mail_queue`;

CREATE TABLE `mail_queue`
(
    `id`              int(11) unsigned NOT NULL AUTO_INCREMENT,
    `subject`         varchar(255) NOT NULL DEFAULT '',
    `body`            text         NOT NULL,
    `from_email`      varchar(255) NOT NULL DEFAULT '',
    `from_name`       varchar(255) NOT NULL DEFAULT '',
    `recipient_email` varchar(255) NOT NULL DEFAULT '',
    `recipient_name`  varchar(255) NOT NULL DEFAULT '',
    `sent_at`         timestamp NULL DEFAULT NULL,
    `created_at`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `mail_queue` WRITE;
/*!40000 ALTER TABLE `mail_queue` DISABLE KEYS */;

INSERT INTO `mail_queue` (`id`, `subject`, `body`, `from_email`, `from_name`, `recipient_email`, `recipient_name`,
                          `sent_at`, `created_at`, `updated_at`)
VALUES (1, '[PAGAMENTO CONFIRMADO] Obrigado por assinar o CaféApp',
        '<!doctype html>\n<html>\n<head>\n    <meta name=\"viewport\" content=\"width=device-width\"/>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n    <title>[PAGAMENTO CONFIRMADO] Obrigado por assinar o CaféApp</title>\n    <style>\n        body {\n            -webkit-box-sizing: border-box;\n            -moz-box-sizing: border-box;\n            box-sizing: border-box;\n            font-family: Helvetica, sans-serif;\n        }\n\n        table {\n            max-width: 500px;\n            padding: 0 10px;\n            background: #ffffff;\n        }\n\n        .content {\n            font-size: 16px;\n            margin-bottom: 25px;\n            padding-bottom: 5px;\n            border-bottom: 1px solid #EEEEEE;\n        }\n\n        .content p {\n            margin: 25px 0;\n        }\n\n        .footer {\n            font-size: 14px;\n            color: #888888;\n            font-style: italic;\n        }\n\n        .footer p {\n            margin: 0 0 2px 0;\n        }\n    </style>\n</head>\n<body>\n<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n    <tr>\n        <td>\n            <div class=\"content\">\n                \n<h3>Obrigado Robson!</h3><p>Estamos passando apenas para agradecer por você ser um assinante CaféApp PRO.</p><p>Sua fatura deste mês venceu hoje e já está paga de acordo com seu plano. Qualquer dúvida estamos a disposição.</p>                <p>Atenciosamente, equipe CaféControl.</p>\n            </div>\n            <div class=\"footer\">\n                <p>CaféControl - Gerencie suas contas com o melhor café</p>\n                <p>SC 406 - Rod. Drº Antônio Luiz Moura Gonzaga                    , 3339, Bloco A, Sala 208</p>\n                <p>Florianópolis/SC - 88048-301</p>\n            </div>\n        </td>\n    </tr>\n</table>\n</body>\n</html>\n',
        'cursos@upinside.com.br', 'Robson V. Leite', 'robsonvleite@gmail.com', 'Robson Leite', '2019-01-31 14:23:54',
        '2019-01-04 11:13:11', '2019-02-07 11:57:26'),
       (2, '[PAGAMENTO RECUSADO] Sua conta CaféApp precisa de atenção',
        '<!doctype html>\n<html>\n<head>\n    <meta name=\"viewport\" content=\"width=device-width\"/>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n    <title>[PAGAMENTO RECUSADO] Sua conta CaféApp precisa de atenção</title>\n    <style>\n        body {\n            -webkit-box-sizing: border-box;\n            -moz-box-sizing: border-box;\n            box-sizing: border-box;\n            font-family: Helvetica, sans-serif;\n        }\n\n        table {\n            max-width: 500px;\n            padding: 0 10px;\n            background: #ffffff;\n        }\n\n        .content {\n            font-size: 16px;\n            margin-bottom: 25px;\n            padding-bottom: 5px;\n            border-bottom: 1px solid #EEEEEE;\n        }\n\n        .content p {\n            margin: 25px 0;\n        }\n\n        .footer {\n            font-size: 14px;\n            color: #888888;\n            font-style: italic;\n        }\n\n        .footer p {\n            margin: 0 0 2px 0;\n        }\n    </style>\n</head>\n<body>\n<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n    <tr>\n        <td>\n            <div class=\"content\">\n                \n<h3>Presado Robson!</h3><p>Não conseguimos cobrar seu cartão referênte a fatura deste mês para sua assinatura CaféApp. Precisamos que você veja isso.</p><p>Acesse sua conta para atualizar seus dados de pagamento, você pode cadastrar outro cartão.</p><p>Se não fizer nada agora uma nova tentativa de cobrança será feita em 3 dias. Se não der certo, sua assinatura será cancelada :/</p><p>Qualquer dúvida estamos a disposição.</p>                <p>Atenciosamente, equipe CaféControl.</p>\n            </div>\n            <div class=\"footer\">\n                <p>CaféControl - Gerencie suas contas com o melhor café</p>\n                <p>SC 406 - Rod. Drº Antônio Luiz Moura Gonzaga                    , 3339, Bloco A, Sala 208</p>\n                <p>Florianópolis/SC - 88048-301</p>\n            </div>\n        </td>\n    </tr>\n</table>\n</body>\n</html>\n',
        'cursos@upinside.com.br', 'Robson V. Leite', 'robsonvleite@gmail.com', 'Robson Leite', '2019-01-11 12:43:45',
        '2019-01-04 11:19:54', '2019-02-07 11:57:27'),
       (3, '[ASSINATURA CANCELADA] Sua conta CaféApp agora é FREE',
        '<!doctype html>\n<html>\n<head>\n    <meta name=\"viewport\" content=\"width=device-width\"/>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n    <title>[ASSINATURA CANCELADA] Sua conta CaféApp agora é FREE</title>\n    <style>\n        body {\n            -webkit-box-sizing: border-box;\n            -moz-box-sizing: border-box;\n            box-sizing: border-box;\n            font-family: Helvetica, sans-serif;\n        }\n\n        table {\n            max-width: 500px;\n            padding: 0 10px;\n            background: #ffffff;\n        }\n\n        .content {\n            font-size: 16px;\n            margin-bottom: 25px;\n            padding-bottom: 5px;\n            border-bottom: 1px solid #EEEEEE;\n        }\n\n        .content p {\n            margin: 25px 0;\n        }\n\n        .footer {\n            font-size: 14px;\n            color: #888888;\n            font-style: italic;\n        }\n\n        .footer p {\n            margin: 0 0 2px 0;\n        }\n    </style>\n</head>\n<body>\n<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n    <tr>\n        <td>\n            <div class=\"content\">\n                \n<h3>Que pena Robson :/</h3><p>Tentamos efetuar mais uma cobrança para sua assinatura PRO hoje, mas sem sucesso.</p><p>Como essa já é uma segunda tentativa, infelismente sua assinatura foi cancelada. Mas calma, você pode assinar novamente a qualquer momento.</p><p>Continue controlando com os recursos FREE, e assim que quiser basta assinar novamente e voltar de onde parou :)</p>                <p>Atenciosamente, equipe CaféControl.</p>\n            </div>\n            <div class=\"footer\">\n                <p>CaféControl - Gerencie suas contas com o melhor café</p>\n                <p>SC 406 - Rod. Drº Antônio Luiz Moura Gonzaga                    , 3339, Bloco A, Sala 208</p>\n                <p>Florianópolis/SC - 88048-301</p>\n            </div>\n        </td>\n    </tr>\n</table>\n</body>\n</html>\n',
        'cursos@upinside.com.br', 'Robson V. Leite', 'robsonvleite@gmail.com', 'Robson Leite', '2019-01-11 12:43:49',
        '2019-01-04 11:34:01', '2019-02-07 11:57:28');

/*!40000 ALTER TABLE `mail_queue` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela notifications
# ------------------------------------------------------------

DROP TABLE IF EXISTS `notifications`;

CREATE TABLE `notifications`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `image`      varchar(255) NOT NULL DEFAULT '',
    `title`      varchar(255) NOT NULL DEFAULT '',
    `link`       varchar(255) NOT NULL DEFAULT '',
    `view`       int(11) NOT NULL DEFAULT '0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK
TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;

INSERT INTO `notifications` (`id`, `image`, `title`, `link`, `view`, `created_at`, `updated_at`)
VALUES (1, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Robson V. Leite assinou o plano PRO de R$ 5,00/mês',
        'https://www.localhost/fsphp/admin/control/subscription/4', 0, '2019-02-11 08:53:35', '2019-02-12 08:53:15'),
       (2, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Eleno Santos assinou o plano PRO de R$ 50,00/ano', 'https://www.localhost/fsphp/admin/control/subscription/5',
        0, '2019-02-11 08:53:49', '2019-02-12 08:53:15'),
       (3, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Alexandre Santos assinou o plano PRO de R$ 5,00/mês',
        'https://www.localhost/fsphp/admin/control/subscription/6', 0, '2019-02-11 09:44:59', '2019-02-12 08:53:15'),
       (4, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Willian Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/7',
        0, '2019-02-11 09:44:59', '2019-02-12 08:53:15'),
       (5, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Eduardo Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/8',
        0, '2019-02-11 08:53:35', '2019-02-12 08:53:16'),
       (6, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Mateus Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/4',
        0, '2019-02-11 09:44:59', '2019-02-12 08:53:16'),
       (7, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Felipe Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/5',
        0, '2019-02-11 08:53:35', '2019-02-12 08:53:16'),
       (8, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Elton Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/6',
        0, '2019-02-11 09:44:59', '2019-02-12 08:53:16'),
       (9, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Roddrigo Santos assinou o plano PRO de R$ 5,00/mês',
        'https://www.localhost/fsphp/admin/control/subscription/7', 0, '2019-02-11 09:44:59', '2019-02-12 08:53:16'),
       (10, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Fernanda Santos assinou o plano PRO de R$ 5,00/mês',
        'https://www.localhost/fsphp/admin/control/subscription/8', 0, '2019-02-11 09:44:59', '2019-02-12 08:53:17'),
       (11, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Bia Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/4', 0,
        '2019-02-11 08:53:35', '2019-02-12 08:53:17'),
       (12, 'https://www.localhost/phptest/fsphplib/themes/cafeadm/assets/images/notify.jpg',
        'Maria Santos assinou o plano PRO de R$ 5,00/mês', 'https://www.localhost/fsphp/admin/control/subscription/5',
        0, '2019-02-11 08:53:35', '2019-02-12 08:53:17');

/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK
TABLES;


#
Dump da tabela posts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `author`     int(11) unsigned DEFAULT NULL,
    `category`   int(11) unsigned DEFAULT NULL,
    `title`      varchar(255) NOT NULL DEFAULT '',
    `uri`        varchar(255) NOT NULL,
    `subtitle`   text         NOT NULL,
    `content`    text         NOT NULL,
    `cover`      varchar(255)          DEFAULT NULL,
    `video`      varchar(50)           DEFAULT NULL,
    `views`      int(11) NOT NULL DEFAULT '0',
    `status`     varchar(20)  NOT NULL DEFAULT 'draft' COMMENT 'post, draft, trash ',
    `post_at`    timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `category_id` (`category`),
    KEY          `user_id` (`author`),
    FULLTEXT KEY `full_text` (`title`,`subtitle`),
    CONSTRAINT `category_id` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
    CONSTRAINT `user_id` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;

INSERT INTO `posts` (`id`, `author`, `category`, `title`, `uri`, `subtitle`, `content`, `cover`, `video`, `views`,
                     `status`, `post_at`, `created_at`, `updated_at`, `deleted_at`)
VALUES (1, 1, 1, 'Subindo Ambiente WEB na Amazon AWS EC2 com Recursos Gratuitos',
        'subindo-ambiente-web-na-amazon-aws-ec2-com-recursos-gratuitos',
        'Crie uma máquina que seja capaz de abrigar o wordpress com os serviços necessários como Apache, PHP7.2 e MariaDB com a infra gratis da AWS',
        '<p>Salve salve moquerido! No play de hoje vamos deixar um pouco o code em standby e vamos ver como que funciona a infraestrutura da Amazon.</p>\r\n<p>É sabido que a Amazon é uma das melhores estruturas que você pode contar, mas como com grandes poderes vem grandes responsabilidades!</p>\r\n<p>Embora você tenha grandes recursos dentro da Amazon, ela acaba sendo muito cara e um processo bastante burocratico caso você opte por gerenciar todos os serviços manualmente.</p>\r\n<p>Durante essa aula vamos criar o nosso ambiente, subir os principais serviços para que tenhamos um sistema hospedado e disponível com acesso público.</p>\r\n<p>Se você está querendo subir o seu ambiente manualmente, eu vou ter por base que você já tenha uma certa afinidade com infra-estrutura. Não precisa ser avançado não... Mas você sabe o que é um Sistema Operacional, quais são as opções, os serviços que precisamos e vai compreender o fluxo.</p>\r\n<h3>Criação de Ambiente</h3>\r\n<p>Para que a gente consiga trabalhar com a AWS, primeiramente precisamos criar a nossa instância! Basicamente é a configuração da máquina que você vai trabalhar.</p>\r\n<p>Vamos definir, sistema operacional, CPU, memória, disco, interface de rede... Os parâmetros básicos para ter uma máquina rodando.</p>\r\n<p>Você vai notar que a t2.micro é o ambiente disponibilizado pela Amazon com uma carga de 750h por mês. Portanto, se você tiver somente uma instância como essa rodando, você não vai ultrapassar o limite e não será cobrado nada no seu cartão de crédito.</p>\r\n<p>Antes de finalizar o processo ainda, é gerado a key pair, que é o mecanismo que você vai trabalhar para efetuar o seu login via SSH.</p>\r\n<p>Se você é usuário do Windows, provavelmente seja legal você trabalhar com o <a href=\"http://cmder.net/\" target=\"_blank\">Cmder</a>. É um aplicativo que vai conseguir rodar os mesmo comandos que fiz na aula. Se você tentar executar o <em>chmod 400 chave.pem</em> por exemplo no Windows, você vai ter um erro dizendo que esse comando não é reconhecido.</p>\r\n<h3>Subindo Serviços</h3>\r\n<p>Tudo o que a gente precisa fazer vai funcionar por comando. Isso quer dizer que nesse ponto não há interface gráfica para nos auxiliar, então vamos ter todas as ações sem mesmo utilizar o mouse.</p>\r\n<p>Para quem não tem nenhuma familiaridade com Linux, pode pensar que isso é um bixo de 7 cabeças. Depois que você começa a se familiarizar, o dia que você não tiver acesso a isso pode ter certeza que vai sentir falta.</p>\r\n<p>Isso fica bem claro quando eu vou descompactar o wordpress do zip, tento fazer o uso de um programa chamado unzip que não vem nativamente no Ubuntu, mas com uma linha de comando e menos de 3 segundos, ele já está disponível para uso.</p>\r\n<p>Vamos fazer algumas ações para ter os nossos serviços OK!</p>\r\n<ul>\r\n<li>Atualização dos pacotes do Sistema Operacional</li>\r\n<li>Adicionar uma nova chave ao repositório</li>\r\n<li>Instalação do PHP7.2 (junto com todos os pacotes necessários)</li>\r\n<li>Instalação do MariaDB v10</li>\r\n<li>Parametrização do banco de dados para acesso externo</li>\r\n<li>Parametrização do php.ini (timezone, tamanho do upload, tamanho do post, PDO do MySQL)</li>\r\n<li>Reiniciar os serviços</li>\r\n<li>Instalação do Wordpress</li>\r\n<li>Testes</li>\r\n</ul>\r\n<h3>Instalação de um CMS</h3>\r\n<p>Com os serviços rodando, eu faço o download via wget do WordPress. Como o intuito aqui da aula não é parametrizar todos os serviços, eu não fiz a instalação do servidor FTP... Mas fique a vontade se quiser colocar na sua aplicação.</p>\r\n<p>Fiz a instalação do WordPress porque é gratuito e a instalação é bem leve, então se você seguir o mesmo passo a passo comigo vai conseguir chegar no final tendo acesso ao WP igualzinho ao meu.</p>\r\n<p>Isso sem contar que o instalador dele vai verificar se todas as extensões necessárias estão ativas, se as configurações estão válidas, vai se conectar com o banco de dados para saber se há uma conexão correta... Basicamente é um check-up de instalação.</p>\r\n<p>No entato que você vê que em determinado momento que eu tento acessar o wordpress pela primeira vez, é retornado um erro dizendo que falta componente para o banco de dados.</p>\r\n<h3>Ambiente OK</h3>\r\n<p>Com tudo tendo sucesso, vamos ter uma máquina com um IP público disponibilizado e com acesso liberado na WEB para que possam acessar o seu novo Blog :)</p>\r\n<p>Eu deixo bem claro em determinados momentos na aula que pode ser mais vantajoso você trabalhar com o cPanel! A instalação além de ser menos burocrativa, você conta com praticamente todos esses recursos sendo disponibilizados podendo escolher versões e ter controle sobre tudo o que acontece dentro do seu servidor.</p>\r\n<p>Dependendo do tempo que você vai investir numa infra da AWS, compensa você adquirir um Cloud ou um VPS lá na <a href=\"https://www.rapidcloud.com.br/\" target=\"_blank\">Rapid Cloud</a> e solicitar o cPanel para seu servidor, e não se esqueça, aluno tem desconto na Rapid... Só conversar com a equipe deles :)</p>\r\n<h3>Feedback</h3>\r\n<p>Me fala aqui abaixo nos comentários o que você achou dessa aula e se você seguiu o passo a passo comigo! Vou estar disponível aqui respondendo todos os comentários e possíveis dúvidas que possam ter :)</p>\r\n<p>Forte abraço.</p>',
        'images/2018/10/subindo-ambiente-web-na-amazon-aws-ec2-com-recursos-gratuitos-1533905294.png', NULL, 27,
        'trash', '2018-08-10 10:00:00', '2021-07-23 10:41:58', '2019-02-06 08:23:54', NULL),
       (2, 51, 2, 'Crie um instalador de estrutura do banco de dados para o seu CMS utilizando PHP e AJAX',
        'crie-um-instalador-de-estrutura-do-banco-de-dados-para-o-seu-cms-utilizando-php-e-ajax',
        'Se você tem um CMS próprio onde disponibiliza a instalação para seus clientes, você tem que assistir essa aula onde te mostro como criar um form wizard de instalação',
        '&#60;p&#62;Salve salve moquerido! Tudo certo? Bora ver como desenvolver um installer para nosso CMS?&#60;/p&#62;&#10;&#60;p&#62;Esse recurso já existe a muito tempo no wordpress, que é um dos CMS mais utilizado no mundo... E provavelmente, durante a sua carreira de desenvolvedor você já deve ter instalado ao menos uma vez o WP em um host.&#60;/p&#62;&#10;&#60;p&#62;O que vamos criar nessa aula do Play é basicamente ele formulário inicial de conexão com o banco de dados e criação do usuário administrador.&#60;/p&#62;&#10;&#60;p&#62;É claro que para a aula ficar mais enxuta, eu fiz somente esses dois passos, mas você pode colocar quantas informações forem necessárias. Você pode seguir esse step by step solicitando diversas informações como:&#60;/p&#62;&#10;&#60;ul&#62;&#10;&#60;li&#62;Timezona que a aplicação deve responder&#60;/li&#62;&#10;&#60;li&#62;Servidor de e-mail&#60;/li&#62;&#10;&#60;li&#62;Geração de logs&#60;/li&#62;&#10;&#60;li&#62;Parametrização de ferramentas externas (autenticação do active campaing, e-mail do pagseguro...)&#60;/li&#62;&#10;&#60;li&#62;Google Analytics&#60;/li&#62;&#10;&#60;li&#62;Pixel do Facebook&#60;/li&#62;&#10;&#60;/ul&#62;&#10;&#60;p&#62;As possibilidades são inumeras :)&#60;/p&#62;&#10;&#60;h3&#62;Aplicação Install&#60;/h3&#62;&#10;&#60;p&#62;Basicamente a aplicação consiste em um diretório com o nome install dentro da aplicação. É claro que você pode dar qualquer outro nome para sistema, o importante é não deixar isso público!&#60;/p&#62;&#10;&#60;p&#62;No decorrer das aulas, você vai ver que dentro dele temos um arquivo chamado dump.sql, e ele tem toda a estrutura do banco de dados! Não é legal alguém por a mão nesse arquivo e entender a sua modelagem.&#60;/p&#62;&#10;&#60;p&#62;Sem muitas firulas, basicamente temos um css para estilizar os nossos componentes e dar uma inteface mais amigável para quem está fazendo a instalação.&#60;/p&#62;&#10;&#60;p&#62;Há também um javascript responsável por monitorar o nosso html e capturar o evento de submit dos forms e disparar a ação para o controlador. É claro que essa ação não seria necessária! Mas dessa forma, em nenhum momento temos o reload da página! Fica bem legal a interação, tratamento de erros e a dinamica das divs.&#60;/p&#62;&#10;&#60;p&#62;O controller por sua vez, está monitorando duas ações! Uma de conexão com o banco de dados e outra para a criação do usuário na base... Se você for implementar mais recursos, obviamente que você vai adicionar novos case&#39;s :)&#60;/p&#62;&#10;&#60;p&#62;O index tem a marcação do HTML como de praxe! Sem segredo.&#60;/p&#62;&#10;&#60;h3&#62;Material de Apoio&#60;/h3&#62;&#10;&#60;p&#62;Como sempre o link para o nosso repositório vai estar aqui abaixo.&#60;/p&#62;&#10;&#60;p&#62;No diretório _initial, tem o que é preciso para você seguir durante as aulas comigo! Basicamente temos esse material inicial só por conta da marcação do html e o css.&#60;/p&#62;&#10;&#60;p&#62;&#60;a href=&#34;https://github.com/UpInside/play_installer&#34; target=&#34;_blank&#34;&#62;Para acessar nosso repo, é só clicar aqui e te levo pra lá agora :)&#60;/a&#62;&#60;/p&#62;&#10;&#60;h3&#62;Criação da estrutura do banco de dados&#60;/h3&#62;&#10;&#60;p&#62;Informação importante aqui... O banco de dados já deve estar previamente criado! Isso quer dizer que seja no seu localhost ou no servidor, você deve acessar o seu aplicativo de SGDB ou o phpMyAdmin e fazer a criação do banco. Isso é necessário para que as permissões sejam concedidas.&#60;/p&#62;&#10;&#60;p&#62;Até poderiamos criar o banco de dados via código, mas o ideal é fazer isso a partir do cpanel, ou de dentro do próprio banco para evitar conflitos de permissões.&#60;/p&#62;&#10;&#60;p&#62;No nosso caso temos dois bancos de dados:&#60;/p&#62;&#10;&#60;h4&#62;play_installer_base&#60;/h4&#62;&#10;&#60;p&#62;Vai servir como origem da nossa estrutura! Então é aqui que temos que ter a modelagem da nossa aplicação, todas as tabelas, campos, chaves extrangeiras, views, triggers, functions... Tudo.&#60;/p&#62;&#10;&#60;h4&#62;play_installer&#60;/h4&#62;&#10;&#60;p&#62;É o banco que estamos simulando a instalação do nosso sistema. Por sua vez, deverá ter exatamente a mesma estrutura do _base!&#60;/p&#62;&#10;&#60;h3&#62;AJAX&#60;/h3&#62;&#10;&#60;p&#62;Você pode imaginar que o tempo de execução desses scripts pode demorar um tempo que não seja hábil para ter o retorno. Mas utilizamos algumas técnicas bem legais para fazer todas as requisições com o file_get_contents e file_put_contents.&#60;/p&#62;&#10;&#60;p&#62;Quando chamamos o dump.sql com o file_get_contents, temos todo o conteúdo que deve ser executado no banco de dados. Portanto você manda todos os comandos de uma vez só e não precisa ficar fazendo diversas requisições.&#60;/p&#62;&#10;&#60;p&#62;Isso sem contar que estamos usando o try/catch, portanto, qualquer excessão que seja lançada a gente consegue tratar :)&#60;/p&#62;&#10;&#60;p&#62;O file_put_contents usamos para criar o arquivo de conexão com o banco.&#60;/p&#62;&#10;&#60;p&#62;Como toda essa execução é feita bem rápida, não precisamos nos preocupar com o tempo. O nosso ajax dá conta!&#60;/p&#62;&#10;&#60;h3&#62;Feedback&#60;/h3&#62;&#10;&#60;p&#62;Se você curtiu esse conteúdo e vai desenvolver o seu instalador, comenta aqui abaixo para eu saber que você está usando esse recurso no seu CMS.&#60;/p&#62;',
        'images/2018/10/crie-um-instalador-de-estrutura-do-banco-de-dados-para-o-seu-cms-utilizando-php-e-ajax-1533220470.png',
        '', 5, 'post', '2019-02-06 17:31:00', '2021-07-23 10:41:58', '2019-02-06 17:31:39', NULL),
       (3, 1, 3, 'Criando relatórios em gráficos com PHP utilizando a biblioteca ChartJS e Banco de Dados',
        'criando-relatorios-em-graficos-com-php-utilizando-a-biblioteca-chartjs-e-banco-de-dados',
        'Embora a biblioteca seja em javascript, conseguimos fazer a chamada de todas as propriedades com o PHP usando uma técnica para informar os dados via data-attributes',
        '<p>Salve salve moquerido, seja muito bem vindo a mais uma aula do play!</p>\r\n<p>Agora vamos ver como trabalhar com gráficos para que você possa informar as métricas para o seu cliente de uma maneira descomplicada e que seja mais fácil de fazer a leitura.</p>\r\n<p>Para que a gente possa desenvolver essa atividade, vamos precisar fazer o uso de uma biblioteca! Assim a gente foca em de fato gerar as informações e a renderização do plano cartesiano você não precisa esquentar a cabeça.</p>\r\n<h3>Material de Apoio</h3>\r\n<p>Para lhe auxiliar nos estudos, vou deixar aqui o link do repositório do github dessa aula para que você possa fazer uma leitura do meu código e se necessário até comprarar com o que você está desenvolvendo.</p>\r\n<p>Basicamente precisamos de uma classe que nos permita fazer uma pesquisa no banco de dados, então por esse motivo, eu já deixei as classes anexas ao repositório e pra poupar tempo também já coloquei o reset.css e o arquivo index.php fazendo a requisição do jquery.</p>\r\n<p>Tudo o que vem depois disso, eu mostro para você na aula como funciona! Desde o processo de instalação da lib, até a criação da classe em PHP para nos servir como helper.</p>\r\n<p><a href=\"https://github.com/UpInside/play_chartjs\" target=\"_blank\">O link para acessar o repositório é este!</a></p>\r\n<h3>Lib javascript</h3>\r\n<p>Realmente a biblioteca que baixamos é em javascript! Você até poderia ter um caminho mais curto para desenvolver essa estrutura, se você colocasse código PHP dentro da tag script.</p>\r\n<p>Não há uma lei que proiba isso, no entanto que é até possível... Mas é claro que não é uma boa prática essa situação, pois caso isso aconteça, você não consegue modularizar o seu código, vai ter problemas com reuso e não vai poder minificar o arquivo para publicar na internet.</p>\r\n<p>Então vamos trabalhar com uma outra técnica para que possamos informar todos os parâmetros via atributo do elemento, e posteriormente a gente pega esses dados com o javascript e adapta da forma que for necessário. <strong>Já vamos falar sobre essa técnica mais adiante!</strong></p>\r\n<p>Por consequência disso, vamos ter um arquivo totalmente em javascript e que você pode modularizar ele de acordo com cada tipo de gráfico! Aqui já entra um processo um pouco mais amplo de refatoração de código, de efetuar essa carga somente se tiver um elemento que irá fazer o uso...</p>\r\n<p>Mais uma vez podemos nos aprofundar em outro tópico que daria outra aula, mas como o nosso foco aqui é criar um gráfico para colocar na nossa aplicação, vamos seguir em frente por hora.</p>\r\n<h3>Passagem de valores por data-attributes</h3>\r\n<p>Essa técnica é bastante simples e consiste em montar o nó do componente HTML já com os dados alimentados.</p>\r\n<p>Não entendeu? Pensa assim:</p>\r\n<pre class=\"brush: php;\">&lt;canvas class=\"myChart\"&gt;&lt;/canvas&gt;</pre>\r\n<p>Com isso, você não sabe quais informações vão para dentro dele, qual será a cor do seu gráfico, se o título será exibido ou se estará oculto, quantas posições serão informadas e nem mesmo o valor de cada um dos eixos!</p>\r\n<p>Agora a gente pode trabalhar com o data-* para informar algumas propriedades:</p>\r\n<pre class=\"brush: php;\">&lt;canvas class=\"myChart\" <br />      data-chart-background-color=\"blue\"<br />      data-chart-border-color=\"blue\"&gt;&lt;/canvas&gt;</pre>\r\n<p>Assim você já consegue informar alguns dados que podem ser resgatados de forma bastante simples pelo javascript, e forçar determinados parâmetros. Para recuperar essas informações, ficaria assim:</p>\r\n<pre class=\"brush: php;\">&lt;script&gt;<br />$(function(){<br />   var myChart = $(\'.myChart\');<br />   console.log(myChart.data(\'chart-background-color\'));<br />});<br />&lt;/script&gt;</pre>\r\n<p>Note que para fazer isso, estamos dentro da tag script, e inicializamos o jquery!</p>\r\n<p>Feito isso, basta criar uma variável para armazenar o objeto que foi resgatado com o seletor e usar essa variável como base para pegar o atributo desejado.</p>\r\n<p>Veja que é informado o <strong>.data(\'nome-atributo\')</strong> e não há o data- no início conforme é demarcado no HTML. Fica tranquilo que é exatamente assim que funciona.</p>\r\n<p>Uma vez feito isso, basta você replicar essa mesma técnica para outras posições que você queira!</p>\r\n<p>Quanto tiver um vetor, você pode concatenar cada uma das posições e adicionar um caracter delimitador entre elas. No caso dessa aula, eu usei o <strong>pipe (|)</strong> porque eu sei que isso não vai aparecer em nenhuma ocorrência das minhas posições.</p>\r\n<p>Então quando eu tenho que resgatar vários <strong>labels</strong> por exemplo, eu concateno todos eles e depois executo um split com o javascript e roda tudo lindo :)</p>\r\n<h3>Adicionando o PHP</h3>\r\n<p>Se você for analisar, até agora não falamos sobre o PHP! Mas além dele estar presente na consulta ao banco de dados das informações, você pode criar uma classe para funcionar como um helper para padronizar esse comportamento.</p>\r\n<p>Então você pode determinar uma certa estrutura que seja capaz de definir as cores com ou sem opacidade, obter o nome das colunas que são utilizados para definir as posições dentro do seu gráfico e ainda o valor de cada uma delas respectivamente.</p>\r\n<p>A principal função do PHP nesse momento, vai ser nos prover reuso do código e padronização das chamadas de cada atributo que precisamos informar!</p>\r\n<h3>Precisa de mais cores?</h3>\r\n<p>Se você seguir a aula, você vai notar que a nossa classe PHP possui 3 cores pré-definidas, mas que elas se repetem quando o meu gráfico possui mais indicadores...</p>\r\n<p>Então você pode querer adicionar mais cores ao seu gráfico ou ainda pegar outras tonalidades para ficar mais coerente com a sua aplicação.</p>\r\n<p><strong>Eu consegui essas cores usando um código de dentro do codepen :)</strong></p>\r\n<p>Repliquei ele para minha conta e você consegue <a href=\"https://codepen.io/guhweb/pen/XBgGRd\" target=\"_blank\">acessar clicando nesse link aqui</a>!</p>\r\n<p>Basicamente o que você precisa fazer é usar o seletor de cor até encontrar a desejada e pegar o código dela em RGB. Como você também deve saber, você deve adicionar uma propriedade a mais para informar a opacidade :)</p>\r\n<p><img title=\"Criando relatórios em gráficos com PHP utilizando a biblioteca ChartJS e Banco de Dados\" src=\"https://www.upinside.com.br/uploads/images/2018/07/354-1532616332.png\" alt=\"Criando relatórios em gráficos com PHP utilizando a biblioteca ChartJS e Banco de Dados\" /></p>\r\n<h3>Feedbacks</h3>\r\n<p>Se você curtiu essa aula e conseguiu reproduzir aí no seu ambiente, me deixe saber! E também se colocar pra rodar em algum cliente com essa mesma técnica que vimos aqui :)</p>\r\n<p>Forte abraço, e a gente se vê na próxima aula!</p>',
        'images/2018/10/criando-relatorios-em-graficos-com-php-utilizando-a-biblioteca-chartjs-e-banco-de-dados-1532694577.png',
        NULL, 8, 'draft', '2018-07-27 09:57:00', '2021-07-23 10:41:58', '2019-02-06 08:10:59', NULL);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela report_access
# ------------------------------------------------------------

DROP TABLE IF EXISTS `report_access`;

CREATE TABLE `report_access` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `users` int(11) NOT NULL DEFAULT '1',
  `views` int(11) NOT NULL DEFAULT '1',
  `pages` int(11) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `report_access` WRITE;
/*!40000 ALTER TABLE `report_access` DISABLE KEYS */;

INSERT INTO `report_access` (`id`, `users`, `views`, `pages`, `created_at`, `updated_at`)
VALUES
	(1,1,2,193,'2019-02-11 15:12:15','2019-02-11 17:57:55'),
	(2,1,1,32,'2019-02-14 13:37:35','2019-02-14 13:39:23');

/*!40000 ALTER TABLE `report_access` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela report_online
# ------------------------------------------------------------

DROP TABLE IF EXISTS `report_online`;

CREATE TABLE `report_online` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user` int(11) unsigned DEFAULT NULL,
  `ip` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(255) NOT NULL DEFAULT '',
  `agent` varchar(255) NOT NULL DEFAULT '',
  `pages` int(11) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `report_online` WRITE;
/*!40000 ALTER TABLE `report_online` DISABLE KEYS */;

INSERT INTO `report_online` (`id`, `user`, `ip`, `url`, `agent`, `pages`, `created_at`, `updated_at`)
VALUES
	(4,51,'::1','/app','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',31,'2019-02-14 13:37:38','2019-02-14 13:39:23');

/*!40000 ALTER TABLE `report_online` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `level` int(11) NOT NULL DEFAULT '1',
  `forget` varchar(255) DEFAULT NULL,
  `genre` varchar(10) DEFAULT NULL,
  `datebirth` date DEFAULT NULL,
  `document` varchar(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'registered' COMMENT 'registered, confirmed',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  FULLTEXT KEY `full_text` (`first_name`,`last_name`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `level`, `forget`, `genre`, `datebirth`, `document`, `photo`, `status`, `created_at`, `updated_at`)
VALUES
	(1,'Robson','Leite','robsonvleite@email.com.br','$2y$10$7aQNdKPaeaX0wwxShqfDN.Jwc4SzPPQGOk7fZdLgV/WmGvVx6oFwm',1,NULL,NULL,NULL,NULL,'images/2018/10/robsonvleite.jpg','confirmed','2018-09-03 16:39:07','2018-11-13 15:11:45'),
	(2,'Alexandre','Santos','alexandre27@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(3,'Willian','Santos','willian28@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(4,'Eleno','Santos','eleno29@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(5,'Lucas','Santos','lucas30@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(6,'Mateus','Santos','mateus31@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(7,'João','Santos','joão32@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(8,'Felipe','Santos','felipe33@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(9,'Anderson','Santos','anderson34@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(10,'Elton','Santos','elton35@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(11,'Leonardo','Santos','leonardo36@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(12,'Regilton','Santos','regilton37@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(13,'Sidney','Santos','sidney38@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(14,'Lourival','Santos','lourival39@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(15,'Henrique','Santos','henrique40@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(16,'Daniel','Santos','daniel41@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(17,'Pedro','Santos','pedro42@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(18,'Andre Roberto','Santos','andre roberto43@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(19,'Ozeias','Santos','ozeias44@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(20,'Arnobio','Santos','arnobio45@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(21,'Roniel','Santos','roniel46@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(22,'Caíque','Santos','caíque47@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(23,'Lucas','Santos','lucas48@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(24,'Francisco','Santos','francisco49@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(25,'Cristian','Santos','cristian50@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(26,'Eduardo','Santos','eduardo51@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(27,'Rodrigo','Santos','rodrigo52@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(28,'Raphael','Santos','raphael53@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(29,'Jose','Santos','jose54@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(30,'Rodrigo','Santos','rodrigo55@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(31,'Diego','Santos','diego56@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(32,'Alexandre','Santos','alexandre57@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(33,'Edimar','Santos','edimar58@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(34,'Jackell','Santos','jackell59@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(35,'Luis','Santos','luis60@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(36,'Lucas','Santos','lucas61@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(37,'Wander','Santos','wander62@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(38,'Tairo','Santos','tairo63@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(39,'Rubens','Santos','rubens64@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(40,'Hugo','Santos','hugo65@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(41,'Gustavo','Santos','gustavo66@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(42,'Paulo','Santos','paulo67@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(43,'Rodrigo','Santos','rodrigo68@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(44,'Denio','Santos','denio69@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(45,'Idalmir','Santos','idalmir70@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(46,'Ataide','Santos','ataide71@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(47,'Luiz','Santos','luiz72@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(48,'Luciano','Santos','luciano73@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(49,'Adir','Santos','adir74@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(50,'Tainan','Santos','tainan75@email.com.br','',1,NULL,NULL,NULL,NULL,NULL,'registered','2018-09-03 16:39:07','2021-07-24 11:26:46'),
	(51,'Robson','Leite','alvarofederal@gmail.com','$2y$10$JLLVjIopZa/bGWMaspZoVe.KohHW8WViZtV02F2FWQiTa4GeV3Gra',5,NULL,'male','1986-07-01','00953074943','images/2019/02/robson-leite.jpg','confirmed','2018-12-05 12:42:10','2019-02-14 13:39:21');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela class_extinguisher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `class_extinguisher`;

CREATE TABLE `class_extinguisher` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `class_extinguisher` WRITE;
/*!40000 ALTER TABLE `class_extinguisher` DISABLE KEYS */;

INSERT INTO `class_extinguisher` (`id`, `name`, `description`, `created_at`, `updated_at`,`status`)
VALUES
	(1,'Classe A – Aparas de papel e madeira','A indicação é feita por um triângulo verde. Incêndios em sólidos, como madeira, papel e borracha e plástico.','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
	(2,'Classe B – Líquidos inflamáveis','A indicação é feita por um quadrado vermelho. Incêndios em líquidos inflamáveis.','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
	(3,'Classe C – Equipamentos elétricos','A indicação é feita por um círculo azul. Incêndios em equipamentos elétricos.','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
	(4,'Classe D – Metais combustíveis','A indicação é feita por uma estrela amarela. Incêndios em metais pirofóricos. Pó de zinco, sódio, magnésio, alumínio, antimônio, etc.','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
	(5,'Classe K – Óleos e gorduras','A indicação é feita por um quadrado preto. Incêndios em óleos e gorduras. Utilizado em cozinhas industriais.','2021-07-21 09:24:51','2021-07-21 09:27:21','active');

/*!40000 ALTER TABLE `class_extinguisher` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela app_extinguishers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_extinguishers`;

CREATE TABLE `app_extinguishers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `localizacao` varchar(255) DEFAULT NULL,
  `nr_patrimonio` varchar(20) default null,
  `lacre` varchar(20) DEFAULT NULL,
  `selo_inmetro` varchar(20) DEFAULT NULL,
  `chassi` varchar(20) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `LT_KG` int(3) DEFAULT NULL COMMENT 'Dependente do modelo',
  `ultima_carga_at` date NOT NULL,
  `validade_recarga_at` date NOT NULL COMMENT 'Dependente da última recarga, pode valer 12 ,36 ou 60 meses',
  `ultimo_test_hydrostatic_at` date NOT NULL,
  `proximo_test_hydrostatic_at` date NOT NULL COMMENT 'Dependente do último teste hidrostático',,
  `ultima_inspecao_mensal_at` date NOT NULL,
  `proxima_inspecao_mensal_at` date NOT NULL COMMENT 'Dependente da última inspenção mensal',,
  `description` text,
  `class` int(11) unsigned DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `recarga` varchar(8) DEFAULT 'active' COMMENT 'active, inactive',
  `status` varchar(8) DEFAULT 'active' COMMENT 'active, inactive',
  PRIMARY KEY (`id`),
  KEY `class_id` (`class`),
  KEY `extinguisher_user` (`user_id`),
  FULLTEXT KEY `description` (`description`),
  CONSTRAINT `class_id` FOREIGN KEY (`class`) REFERENCES `class_extinguisher` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `extinguisher_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_extinguishers` WRITE;
/*!40000 ALTER TABLE `app_extinguishers` DISABLE KEYS */;

INSERT INTO `app_extinguishers` (`id`, `user_id`, `class`, `lacre`, `nr_inmetro`,`patrimonio`, `LT_KG`, `validade_at`, `ultima_carga_at`, `proximo_teste_at`, `test_hydrostatic_at`, `localizacao`, `description`, `created_at`, `updated_at`,`recarga`, `status`) VALUES
(1,  51, 1, '54654', '56455454', 'P0001', 1, '2021-07-21', '2020-02-01','2020-02-01','2020-02-01', 'G1-51-5º Andar', 'Teste', '2021-07-21 12:24:51', '2021-07-21 12:24:51', 'active','active'),
(2,  51, 1, '23556', '21545454', 'P0002', 1, '2021-07-21', '2020-02-02','2020-02-02','2020-02-02', 'G1-41-4º Andar', 'Teste', '2021-07-21 12:24:51', '2021-07-21 12:24:51', 'active','active'),
(3,  51, 1, '98757', '23121218', 'P0003', 1, '2021-07-21', '2020-02-03','2020-02-03','2020-05-03', 'G1-31-3º Andar', 'Teste', '2021-07-21 12:24:51', '2021-07-21 12:24:51', 'active','active'),
(4,  51, 1, '45665', '95423564', 'P0004', 1, '2021-07-21', '2020-02-04','2020-02-04','2020-03-04', 'G1-21-2º Andar', 'Teste', '2021-07-21 12:24:51', '2021-02-08 15:43:36', 'active','active'),
(5,  51, 1, '44665', '98923564', 'P0005', 1, '2021-07-21', '2020-02-05','2020-02-05','2020-02-05', 'G1-11-1º Andar', 'Teste', '2021-07-21 12:24:51', '2021-02-08 15:43:42', 'active','active'),
(6,  51, 1, '43665', '96723564', 'P0006', 1, '2021-07-21', '2020-02-06','2020-02-06','2020-02-06', 'H1-51-5º Andar', 'Teste', '2021-07-21 12:24:51', '2021-02-08 15:43:50', 'active','active'),
(7,  51, 1, '42665', '93523564', 'P0007', 1, '2021-07-21', '2020-02-07','2020-02-07','2020-02-07', 'H1-41-4º Andar', 'Teste', '2021-07-21 12:24:51', '2021-02-08 12:13:37', 'active','active'),
(8,  51, 1, '42665', '93523564', 'P0008', 1, '2021-07-21', '2020-02-08','2020-02-08','2020-02-08', 'H1-31-3º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(9,  51, 1, '42665', '93523564', 'P0009', 1, '2021-07-21', '2020-02-09','2020-02-09','2020-02-09', 'H1-21-2º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(10, 51, 1, '42665', '93523564', 'P0010', 1, '2021-07-21', '2020-02-10','2020-02-10','2020-02-10', 'H1-11-1º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(11, 51, 1, '42665', '93523564', 'P0011', 1, '2021-07-21', '2020-02-11','2020-02-11','2020-02-11', 'A1-51-5º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(12, 51, 1, '42665', '93523564', 'P0012', 1, '2021-07-21', '2020-02-12','2020-02-12','2020-02-12', 'A1-41-4º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(14, 51, 1, '42665', '93523564', 'P0013', 1, '2021-07-21', '2020-02-14','2020-02-14','2020-02-13', 'A1-31-3º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(15, 51, 1, '42665', '93523564', 'P0014', 1, '2021-07-21', '2020-02-15','2020-02-15','2020-02-14', 'A1-21-2º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(16, 51, 1, '42665', '93523564', 'P0015', 1, '2021-07-21', '2020-02-16','2020-02-16','2020-02-15', 'A1-11-1º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(17, 51, 1, '42665', '93523564', 'P0016', 1, '2021-07-21', '2020-02-17','2020-02-17','2020-02-16', 'B1-11-1º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(18, 51, 1, '42665', '93523564', 'P0016', 1, '2021-07-21', '2020-02-18','2020-02-19','2020-02-16', 'B1-11-1º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(19, 51, 1, '42665', '93523564', 'P0016', 1, '2021-07-21', '2020-02-19','2020-02-21','2020-02-16', 'B1-11-1º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active'),
(20, 51, 1, '42665', '93523564', 'P0016', 1, '2021-07-21', '2020-02-20','2020-02-21','2020-02-16', 'B1-11-1º Andar', 'Teste', '2021-07-21 15:24:51', '2021-02-08 15:13:37', 'active','active');


/*!40000 ALTER TABLE `app_extinguishers` ENABLE KEYS */;
UNLOCK TABLES;

# Dump da tabela app_equipment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_equipment`;

CREATE TABLE `app_equipment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  `patrimony_number` varchar(255) DEFAULT NULL,
  `tag_number` varchar(255) DEFAULT NULL,
  `app_manufacturer_id` int(11) unsigned DEFAULT NULL,
  `app_type_equipment_id` int(11) unsigned DEFAULT NULL,
  `operational` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  `defect` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  `localizacao` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  PRIMARY KEY (`id`),
  KEY `equipments_user` (`user_id`),
  KEY `equipments_type_equipment` (`app_type_equipment_id`),
  KEY `manufacturer_equipment` (`app_manufacturer_id`),
  FULLTEXT KEY `description` (`description`),
  CONSTRAINT `equipment_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `equipment_type_equipment` FOREIGN KEY (`app_type_equipment_id`) REFERENCES `app_type_equipment` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `manufacturer_equipment` FOREIGN KEY (`app_manufacturer_id`) REFERENCES `app_manufacturer` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_equipment` WRITE;
/*!40000 ALTER TABLE `app_equipments` DISABLE KEYS */;

INSERT INTO `app_equipment` (`id`, `nome`, `description`, `user_id`,  `app_manufacturer_id`,`app_type_equipment_id`, `validade_manutencao_at`, `ultima_manutencao_at`,
                             `ultima_teste_at`, `proximo_manutencao_at`, `proximo_teste_at`, `localizacao`, `created_at`, `updated_at`, `status`)
VALUES
 (1, 'Martelo', 'Teste', 51, 1, 2, '2021-02-01', '2021-02-01', '2021-02-01', '2021-02-01', '2021-02-01', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active'),
 (2, 'Furadeira', 'Teste', 51, 2, 3, '2021-02-02', '2021-02-02', '2021-02-02', '2021-02-02', '2021-07-21', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active'),
 (3, 'Enxada', 'Teste', 51, 1, 5, '2021-02-03', '2021-02-03', '2021-02-03', '2021-02-03', '2021-07-21', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active'),
 (4, 'Colher de Pedreiro', 'Teste', 51, 1, 3, '2021-07-21', '2021-07-21', '2021-07-21', '2021-07-21', '2021-07-21', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active'),
 (5, 'Alicate', 'Teste', 51, 1, 4, '2021-07-21', '2021-07-21', '2021-07-21', '2021-07-21', '2021-07-21', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active'),
 (6, 'Martelete', 'Teste', 51, 2, 3, '2021-02-21', '2021-02-21', '2021-02-21', '2021-02-21', '2021-07-21', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active'),
 (7, 'Serra Tico-Tico', 'Teste', 51, 1, 2, '2021-07-23', '2021-07-23', '2021-07-23', '2021-07-23', '2021-07-21', 'Teste', '2021-07-21 09:24:51', '2021-07-21 09:24:51', 'active');

/*!40000 ALTER TABLE `app_equipments` ENABLE KEYS */;
UNLOCK TABLES;

# Dump da tabela app_type_equipment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_type_equipment`;

CREATE TABLE `app_type_equipment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_type_equipment` WRITE;
/*!40000 ALTER TABLE `type_equipment` DISABLE KEYS */;

INSERT INTO `app_type_equipment` (`id`, `name`, `created_at`, `updated_at`,`status`)
VALUES
(1,'Gerador de Energia','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(2,'Compressor Externo','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(3,'Compressor Interno','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(4,'Trator','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(5,'Bomba','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(5,'Duto','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(5,'Betoneira','2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(5,'Escavadeira','2021-07-21 09:24:51','2021-07-21 09:27:21','active');

/*!40000 ALTER TABLE `app_type_equipment` ENABLE KEYS */;
UNLOCK TABLES;

# Dump da tabela app_criticality
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_criticality`;

CREATE TABLE `app_criticality` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_criticality` WRITE;
/*!40000 ALTER TABLE `type_equipment` DISABLE KEYS */;

INSERT INTO `app_criticality` (`id`, `name`, `desciption`, `created_at`, `updated_at`,`status`)
VALUES
(1,'Critico A', 'Prazo de 24 horas para resolução do problema', '2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(1,'Critico B', 'Prazo de 7 dias para a resolução do problema', '2021-07-21 09:24:51','2021-07-21 09:27:21','active'),
(2,'Critico C', 'Prazo de 30 dias para a resolução do problema', '2021-07-21 09:24:51','2021-07-21 09:27:21','active');

/*!40000 ALTER TABLE `app_criticality` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela app_maintenance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_maintenance`;
CREATE TABLE `app_maintenance` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name_item` varchar(255) DEFAULT NULL,
    `name_operator` varchar(255) DEFAULT NULL,
    `name_maintainer` varchar(255) DEFAULT NULL,
    `user_id` int(11) unsigned DEFAULT NULL,
    `app_type_maintenance_id` varchar(255) DEFAULT NULL,
    `app_equipament_id` varchar(255) DEFAULT NULL,
    `app_criticality_id` varchar(255) DEFAULT NULL,
    `validity_maintenance_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_maintenance_at` varchar(2000) DEFAULT NULL,
    `next_maintenance_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `correction_time_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `observation` varchar(2000) DEFAULT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `pause` varchar(10) DEFAULT 'play' COMMENT 'play, pause',
    `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
    PRIMARY KEY (`id`),
    CONSTRAINT `equipments_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `equipments_type_equipment` FOREIGN KEY (`app_type_equipment_id`) REFERENCES `app_type_equipment` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `equipments_type_maintenance` FOREIGN KEY (`app_type_maintenance_id`) REFERENCES `app_type_maintenance` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_maintenance` WRITE;
/*!40000 ALTER TABLE `app_maintenance` DISABLE KEYS */;

INSERT INTO `app_maintenance` (`id`, `name_item`, `name_operator`, `name_maintainer`, `user_id`, `app_type_maintenance_id`, `app_equipament_id`, `app_criticality_id`,
                               `validity_maintenance_at`, `last_maintenance_at`, `next_maintenance_at`, `correction_time_at`, `observation`, `created_at`, `updated_at`,
                               `pause`, `status`)
VALUES
(1, 'Manutenção Corretiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(2, 'Manutenção Preventiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(3, 'Manutenção Preditiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(4, 'Manutenção Detectiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(5, 'Manutenção Produtiva Total (TPM)', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active');

/*!40000 ALTER TABLE `app_maintenance` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela type_maintenance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_type_maintenance`;

CREATE TABLE `app_type_maintenance` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_type_maintenance` WRITE;
/*!40000 ALTER TABLE `type_maintenance` DISABLE KEYS */;

INSERT INTO `app_type_maintenance` (`id`, `name`, `created_at`, `updated_at`,`status`)
VALUES
(1, 'Manutenção Corretiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(2, 'Manutenção Preventiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(3, 'Manutenção Preditiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(4, 'Manutenção Detectiva', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active'),
(5, 'Manutenção Produtiva Total (TPM)', '2021-10-21 09:24:51', '2021-07-21 09:27:21','active');

/*!40000 ALTER TABLE `app_type_maintenance` ENABLE KEYS */;
UNLOCK TABLES;

# Dump da tabela app_manufacturer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_manufacturer`;

CREATE TABLE `app_manufacturer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `app_equipment_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT 'active' COMMENT 'active, inactive',
  PRIMARY KEY (`id`),
  CONSTRAINT `equipments_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `equipments_equipment` FOREIGN KEY (`app_equipment_id`) REFERENCES `app_equipment` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `app_manufacturer` WRITE;
/*!40000 ALTER TABLE `app_manufacturer` DISABLE KEYS */;

INSERT INTO `app_manufacturer` (`id`, `user_id`, `app_equipment_id`, `name`, `site`, `email`, `phone`, `created_at`, `updated_at`,`status`)
VALUES
(1, 1, 1, 'Kidde Brasil Ltda','www.kidde.com.br', '', '(19) 2101-8400', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(2, 1, 1, 'Bucka Indústria e Comércio Ltda', 'www.bucka.com.br', '', '(11) 3935-4280', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(3, 1, 1, 'Resil', 'www.resil.com.br', '', '11 2178-8100', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(4, 1, 1, 'Protege', 'www.protege.ind.br', 'protege@protege.ind.br', '11 2090-4220', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(5, 1, 1, 'Extintore Mocelin', 'http://www.extintoresmocelin.com.br', 'mocelin@extintoresmocelin.com.br', '46 3534-8000', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(6, 1, 1, 'IMCOM/ IMASTER VENCEDOR', 'www.imaster-vencedor.com.br', '', '0800-178099', '2021-07-21 09:24:51','2021-07-21 09:27:21', 'active'),
(7, 1, 1, 'FCV/EXTINPEL', 'www.fcvextintores.com.br', 'gerentedevendas@fcvextintores.com.br', '(43) 3534-4340', '2021-07-21 09:24:51','2021-07-21 09:27:21', 'active'),
(8, 1, 1, , 'www.gifel.com.br','', '11 4529-7511', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(9, 1, 1, 'IMC – Indústria Metalúrgica Caieiras Ltda/ACEPEX', '', '', '(11) 4605-3731', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active'),
(10, 1, 1, 'Mangflex', 'www.mangflex.com', 'vendas@mangflex.com', '(19) 3246.2822 / 3216.5684 / 3246.0453', '2021-07-21 09:24:51', '2021-07-21 09:27:21', 'active');

/*!40000 ALTER TABLE `app_manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

