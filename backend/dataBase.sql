-- phpMyAdmin SQL Dump
-- version 5.2.1deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Lug 19, 2025 alle 14:56
-- Versione del server: 10.11.13-MariaDB-0ubuntu0.24.04.1
-- Versione PHP: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dataBase`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `accessi`
--

CREATE TABLE `accessi` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `id_utente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `accessi`
--

INSERT INTO `accessi` (`id`, `data`, `id_utente`) VALUES
(1, '2025-07-14 01:01:21', 17),
(2, '2025-07-14 01:02:05', 17),
(3, '2025-07-14 01:03:55', 17),
(4, '2025-07-14 01:05:01', 17),
(5, '2025-07-14 02:20:25', 17),
(6, '2025-07-14 02:32:26', 17),
(7, '2025-07-14 02:45:34', 17),
(8, '2025-07-14 02:47:57', 17),
(9, '2025-07-14 02:53:10', 17),
(10, '2025-07-14 02:53:56', 17),
(11, '2025-07-14 02:57:51', 17),
(12, '2025-07-14 02:59:21', 17),
(13, '2025-07-14 03:20:48', 17),
(14, '2025-07-14 03:22:22', 17),
(15, '2025-07-14 03:23:38', 17),
(16, '2025-07-14 03:27:47', 17),
(17, '2025-07-14 03:29:41', 17),
(18, '2025-07-14 03:33:20', 17),
(19, '2025-07-14 09:19:34', 17),
(20, '2025-07-14 09:44:46', 17),
(21, '2025-07-14 10:05:15', 19),
(22, '2025-07-14 10:57:35', 17),
(23, '2025-07-14 11:36:27', 18),
(24, '2025-07-14 11:58:11', 17),
(25, '2025-07-14 12:23:09', 19),
(26, '2025-07-14 12:32:30', 17),
(27, '2025-07-14 16:04:38', 17),
(28, '2025-07-14 16:05:56', 18),
(29, '2025-07-14 16:07:51', 18),
(30, '2025-07-14 16:11:46', 18),
(31, '2025-07-14 16:11:52', 17),
(32, '2025-07-14 16:13:11', 19),
(33, '2025-07-14 16:16:56', 39),
(34, '2025-07-14 16:18:06', 17),
(35, '2025-07-14 16:22:14', 39),
(36, '2025-07-14 16:26:22', 39),
(37, '2025-07-14 16:28:51', 17),
(38, '2025-07-14 16:30:41', 17),
(39, '2025-07-14 16:31:11', 17),
(40, '2025-07-14 16:31:28', 18),
(41, '2025-07-14 16:35:32', 17),
(42, '2025-07-14 16:37:01', 18),
(43, '2025-07-14 16:46:38', 17),
(44, '2025-07-14 16:49:20', 17),
(45, '2025-07-14 16:50:09', 17),
(46, '2025-07-14 16:50:58', 17),
(47, '2025-07-14 16:55:16', 18),
(48, '2025-07-14 16:56:10', 17),
(49, '2025-07-14 16:57:07', 17),
(50, '2025-07-14 16:57:49', 17),
(51, '2025-07-14 17:01:00', 18),
(52, '2025-07-14 17:04:02', 18),
(53, '2025-07-14 17:07:37', 17),
(54, '2025-07-14 17:07:53', 17),
(55, '2025-07-14 17:09:06', 18),
(56, '2025-07-14 17:09:26', 17),
(57, '2025-07-14 17:11:48', 18),
(58, '2025-07-14 17:14:37', 18),
(59, '2025-07-14 17:16:24', 18),
(60, '2025-07-14 17:24:43', 18),
(61, '2025-07-14 17:26:40', 19),
(62, '2025-07-14 17:27:21', 19),
(63, '2025-07-14 17:27:36', 18),
(64, '2025-07-14 17:31:00', 18),
(65, '2025-07-14 17:31:17', 17),
(66, '2025-07-14 17:31:31', 19),
(67, '2025-07-14 17:31:46', 17),
(68, '2025-07-14 17:36:53', 18),
(69, '2025-07-14 17:37:05', 17),
(70, '2025-07-14 17:47:35', 17),
(71, '2025-07-14 17:52:19', 17),
(72, '2025-07-14 17:55:05', 17),
(73, '2025-07-14 18:00:37', 17),
(74, '2025-07-14 18:04:44', 17),
(75, '2025-07-14 18:08:44', 17),
(76, '2025-07-14 18:09:10', 18),
(77, '2025-07-14 18:12:55', 17),
(78, '2025-07-14 18:13:52', 18),
(79, '2025-07-14 18:14:43', 18),
(80, '2025-07-14 18:16:25', 17),
(81, '2025-07-14 18:17:53', 17),
(82, '2025-07-14 18:18:22', 18),
(83, '2025-07-14 18:19:03', 17),
(84, '2025-07-14 18:19:29', 18),
(85, '2025-07-14 18:20:04', 17),
(86, '2025-07-14 18:21:56', 17),
(87, '2025-07-14 18:22:44', 17),
(88, '2025-07-14 18:27:57', 18),
(89, '2025-07-14 18:29:43', 17),
(90, '2025-07-14 18:30:36', 17),
(91, '2025-07-14 18:32:47', 17),
(92, '2025-07-15 21:43:06', 18),
(93, '2025-07-15 22:07:54', 18),
(94, '2025-07-15 22:10:17', 18),
(95, '2025-07-15 22:13:07', 18),
(96, '2025-07-15 22:13:58', 18),
(97, '2025-07-15 22:15:08', 18),
(98, '2025-07-15 22:16:47', 18),
(99, '2025-07-15 22:30:36', 18),
(100, '2025-07-15 22:34:09', 18),
(101, '2025-07-15 22:38:15', 18),
(102, '2025-07-15 22:39:20', 17),
(103, '2025-07-15 22:41:18', 18),
(104, '2025-07-15 22:42:30', 18),
(105, '2025-07-15 22:47:11', 18),
(106, '2025-07-15 22:51:58', 18),
(107, '2025-07-15 22:53:10', 18),
(108, '2025-07-15 22:54:07', 18),
(109, '2025-07-15 22:58:01', 18),
(110, '2025-07-15 22:59:19', 18),
(111, '2025-07-15 23:01:51', 18),
(112, '2025-07-15 23:26:40', 18),
(113, '2025-07-15 23:30:52', 18),
(114, '2025-07-15 23:34:26', 18),
(115, '2025-07-15 23:36:52', 18),
(116, '2025-07-15 23:43:55', 18),
(117, '2025-07-15 23:44:46', 18),
(118, '2025-07-15 23:49:34', 18),
(119, '2025-07-15 23:59:14', 18),
(120, '2025-07-16 00:01:57', 18),
(121, '2025-07-16 00:05:23', 18),
(122, '2025-07-16 00:11:05', 18),
(123, '2025-07-16 00:17:06', 18),
(124, '2025-07-16 00:18:23', 18),
(125, '2025-07-16 00:25:14', 18),
(126, '2025-07-16 00:25:57', 18),
(127, '2025-07-16 00:42:09', 18),
(128, '2025-07-16 00:45:47', 18),
(129, '2025-07-16 00:48:44', 18),
(130, '2025-07-16 00:51:50', 18),
(131, '2025-07-16 00:52:44', 18),
(132, '2025-07-16 00:55:34', 18),
(133, '2025-07-16 00:58:45', 17),
(134, '2025-07-16 00:59:10', 18),
(135, '2025-07-16 01:00:23', 18),
(136, '2025-07-16 01:04:02', 17),
(137, '2025-07-16 01:07:35', 17),
(138, '2025-07-16 01:13:47', 17),
(139, '2025-07-16 01:14:42', 17),
(140, '2025-07-16 01:21:48', 18),
(141, '2025-07-16 01:21:55', 17),
(142, '2025-07-16 01:24:34', 17),
(143, '2025-07-16 01:53:12', 18),
(144, '2025-07-16 01:53:22', 17),
(145, '2025-07-16 01:56:25', 17),
(146, '2025-07-16 01:58:58', 17),
(147, '2025-07-16 02:00:30', 19),
(148, '2025-07-16 02:05:13', 17),
(149, '2025-07-16 02:18:27', 17),
(150, '2025-07-16 02:24:23', 17),
(151, '2025-07-16 08:43:12', 17),
(152, '2025-07-16 08:43:39', 18),
(153, '2025-07-16 08:44:03', 17),
(154, '2025-07-16 08:45:03', 39),
(155, '2025-07-16 08:45:59', 18),
(156, '2025-07-16 08:47:34', 18),
(157, '2025-07-16 10:37:25', 17),
(158, '2025-07-16 10:37:48', 19),
(159, '2025-07-16 10:38:22', 18),
(160, '2025-07-16 10:40:14', 17),
(161, '2025-07-16 10:40:54', 18),
(162, '2025-07-16 10:41:19', 18),
(163, '2025-07-16 10:42:04', 18),
(164, '2025-07-16 10:43:47', 18),
(165, '2025-07-16 10:45:42', 19),
(166, '2025-07-16 10:48:54', 19),
(167, '2025-07-16 10:54:44', 18),
(168, '2025-07-16 11:03:39', 19),
(169, '2025-07-16 11:07:27', 17),
(170, '2025-07-16 11:08:09', 17),
(171, '2025-07-16 11:15:48', 17),
(172, '2025-07-16 11:19:00', 17),
(173, '2025-07-16 11:21:24', 17),
(174, '2025-07-16 11:35:13', 17),
(175, '2025-07-16 11:37:03', 18),
(176, '2025-07-16 11:40:08', 17),
(177, '2025-07-16 11:48:41', 17),
(178, '2025-07-16 11:55:49', 19),
(179, '2025-07-16 11:59:57', 19),
(181, '2025-07-16 12:02:33', 17),
(182, '2025-07-16 12:05:18', 17),
(184, '2025-07-16 12:07:09', 19),
(185, '2025-07-16 12:07:35', 17),
(186, '2025-07-16 12:10:40', 17),
(187, '2025-07-16 12:12:29', 17),
(188, '2025-07-16 12:14:27', 17),
(189, '2025-07-16 12:18:39', 17),
(190, '2025-07-16 12:22:22', 17),
(191, '2025-07-16 12:24:54', 17),
(192, '2025-07-16 12:25:25', 19),
(193, '2025-07-16 12:25:45', 17),
(194, '2025-07-16 12:31:34', 17),
(195, '2025-07-16 12:32:48', 18),
(196, '2025-07-16 12:33:56', 19),
(197, '2025-07-16 12:35:36', 39),
(198, '2025-07-16 12:36:30', 49),
(199, '2025-07-16 12:37:36', 17),
(200, '2025-07-16 12:38:39', 49),
(201, '2025-07-16 12:38:54', 18),
(202, '2025-07-16 12:39:13', 17),
(203, '2025-07-16 12:41:44', 18),
(204, '2025-07-16 13:06:04', 18),
(205, '2025-07-16 13:08:49', 18),
(206, '2025-07-16 13:09:51', 18),
(207, '2025-07-16 13:16:20', 18),
(208, '2025-07-16 13:16:35', 17),
(209, '2025-07-16 13:19:18', 17),
(210, '2025-07-16 13:19:43', 18),
(211, '2025-07-16 13:23:48', 18),
(212, '2025-07-16 13:25:26', 18),
(213, '2025-07-16 13:25:44', 17),
(214, '2025-07-16 13:26:42', 17),
(215, '2025-07-16 13:28:05', 17),
(216, '2025-07-16 13:35:32', 19),
(217, '2025-07-16 14:03:50', 18),
(218, '2025-07-16 14:05:04', 18),
(219, '2025-07-16 14:06:03', 18),
(220, '2025-07-16 14:06:11', 18),
(221, '2025-07-16 14:07:24', 18),
(222, '2025-07-16 14:08:52', 18),
(223, '2025-07-16 14:13:29', 18),
(224, '2025-07-16 14:20:04', 18),
(225, '2025-07-16 14:50:10', 17),
(226, '2025-07-16 14:54:36', 17),
(227, '2025-07-16 14:57:29', 18),
(228, '2025-07-16 14:58:29', 18),
(229, '2025-07-16 14:59:25', 18),
(230, '2025-07-16 15:00:27', 18),
(231, '2025-07-16 15:01:05', 18),
(232, '2025-07-16 15:01:25', 18),
(233, '2025-07-16 15:06:05', 17),
(234, '2025-07-16 15:07:52', 17),
(235, '2025-07-16 15:08:20', 18),
(236, '2025-07-16 15:09:28', 19),
(237, '2025-07-16 15:13:00', 19),
(238, '2025-07-16 15:16:57', 17),
(239, '2025-07-16 15:18:56', 18),
(240, '2025-07-16 15:20:07', 18),
(241, '2025-07-16 15:21:45', 39),
(242, '2025-07-16 15:22:21', 60),
(243, '2025-07-16 15:22:36', 17),
(244, '2025-07-16 15:23:12', 60),
(245, '2025-07-16 15:24:25', 19),
(246, '2025-07-16 15:24:38', 34),
(247, '2025-07-16 15:24:51', 18),
(248, '2025-07-16 15:25:15', 34),
(249, '2025-07-16 15:26:04', 60),
(250, '2025-07-16 15:26:42', 18),
(251, '2025-07-16 15:26:45', 60),
(252, '2025-07-16 15:27:55', 18),
(253, '2025-07-16 15:31:29', 19),
(254, '2025-07-16 15:35:48', 18),
(255, '2025-07-16 15:39:39', 19),
(256, '2025-07-16 15:40:28', 18),
(257, '2025-07-16 15:40:58', 17),
(258, '2025-07-16 16:25:30', 18),
(259, '2025-07-16 16:27:14', 17),
(260, '2025-07-16 16:28:12', 18),
(261, '2025-07-16 16:34:15', 18),
(262, '2025-07-16 16:38:41', 19),
(263, '2025-07-16 16:41:24', 18),
(264, '2025-07-16 16:41:51', 17),
(265, '2025-07-16 16:43:02', 18),
(266, '2025-07-16 17:36:20', 18),
(267, '2025-07-16 17:37:18', 18),
(268, '2025-07-16 17:38:07', 17),
(269, '2025-07-16 17:38:45', 18),
(270, '2025-07-16 17:42:00', 17),
(271, '2025-07-16 17:42:28', 18),
(272, '2025-07-16 17:44:56', 19),
(273, '2025-07-16 18:15:20', 19),
(274, '2025-07-16 18:19:32', 19),
(275, '2025-07-16 18:20:53', 17),
(276, '2025-07-16 18:30:23', 18),
(277, '2025-07-16 22:56:50', 18);

-- --------------------------------------------------------

--
-- Struttura della tabella `assegnazioni_medici`
--

CREATE TABLE `assegnazioni_medici` (
  `id` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL,
  `id_paziente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `assegnazioni_medici`
--

INSERT INTO `assegnazioni_medici` (`id`, `id_medico`, `id_paziente`) VALUES
(5, 17, 9),
(8, 17, 4),
(10, 1, 35),
(16, 17, 18),
(19, 17, 32),
(20, 17, 39),
(52, 40, 45),
(53, 40, 46),
(54, 40, 47),
(55, 41, 48),
(56, 41, 49),
(57, 41, 50),
(58, 42, 51),
(59, 42, 52),
(60, 42, 53),
(61, 43, 54),
(62, 43, 55),
(63, 43, 56),
(64, 44, 57),
(68, 17, 60);

-- --------------------------------------------------------

--
-- Struttura della tabella `assegnazioni_studi`
--

CREATE TABLE `assegnazioni_studi` (
  `id` int(11) NOT NULL,
  `id_studio` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `assegnazioni_studi`
--

INSERT INTO `assegnazioni_studi` (`id`, `id_studio`, `id_medico`) VALUES
(4, 1, 1),
(5, 2, 3),
(6, 3, 5),
(7, 4, 40),
(8, 5, 41),
(9, 6, 42),
(10, 7, 43),
(11, 8, 44);

-- --------------------------------------------------------

--
-- Struttura della tabella `associazioni_farmaci`
--

CREATE TABLE `associazioni_farmaci` (
  `id` int(11) NOT NULL,
  `id_terapia` int(11) NOT NULL,
  `id_farmaco` int(11) NOT NULL,
  `numero_assunzioni` int(11) NOT NULL,
  `dose` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `associazioni_farmaci`
--

INSERT INTO `associazioni_farmaci` (`id`, `id_terapia`, `id_farmaco`, `numero_assunzioni`, `dose`) VALUES
(3, 3, 1, 3, 500),
(4, 6, 2, 2, 50),
(5, 28, 4, 14, 25),
(6, 29, 4, 14, 25),
(7, 30, 5, 32, 15),
(8, 31, 5, 32, 15),
(9, 32, 3, 12, 25),
(10, 33, 2, 14, 58),
(11, 34, 2, 25, 24),
(12, 35, 5, 25, 4),
(26, 36, 24, 1, 500),
(27, 37, 25, 3, 500),
(28, 38, 27, 2, 500),
(29, 39, 28, 1, 20),
(30, 40, 29, 1, 15),
(31, 41, 30, 1, 100),
(32, 42, 31, 2, 50),
(33, 43, 32, 3, 10),
(34, 44, 33, 2, 200),
(35, 45, 34, 1, 10),
(36, 46, 35, 2, 100),
(37, 47, 36, 1, 20),
(43, 55, 4, 1, 1),
(44, 56, 2, 1, 2),
(46, 58, 29, 2, 15),
(47, 59, 1, 2, 15);

-- --------------------------------------------------------

--
-- Struttura della tabella `assunzioni`
--

CREATE TABLE `assunzioni` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `id_associazione_farmaco` int(11) NOT NULL,
  `stato` enum('assunto','dimenticato') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `assunzioni`
--

INSERT INTO `assunzioni` (`id`, `data`, `id_associazione_farmaco`, `stato`) VALUES
(5, '2025-06-05 11:30:00', 3, 'assunto'),
(6, '2025-06-06 12:22:00', 3, 'assunto'),
(7, '2025-06-08 11:11:00', 3, 'assunto'),
(8, '2025-06-13 07:10:00', 3, 'assunto'),
(9, '2025-06-29 11:45:00', 3, 'assunto'),
(39, '2025-07-05 12:25:04', 3, 'assunto'),
(40, '2025-07-05 12:25:05', 3, 'assunto'),
(41, '2025-07-02 14:14:25', 4, 'assunto'),
(42, '2025-07-05 12:14:14', 4, 'assunto'),
(43, '2025-07-06 15:25:14', 3, 'assunto'),
(44, '2025-07-06 14:25:25', 3, 'assunto'),
(45, '2025-07-06 14:25:18', 3, 'assunto'),
(46, '2025-07-06 15:22:00', 4, 'assunto'),
(47, '2025-07-06 20:59:00', 4, 'assunto'),
(52, '2025-07-09 12:16:00', 3, 'assunto'),
(53, '2025-07-09 12:12:00', 3, 'assunto'),
(58, '2025-07-10 11:10:00', 3, 'assunto'),
(59, '2025-07-13 12:12:00', 3, 'assunto'),
(60, '2025-07-13 08:02:00', 3, 'assunto'),
(70, '2025-07-16 10:06:00', 3, 'assunto'),
(71, '2025-07-16 08:03:00', 3, 'assunto'),
(72, '2025-07-16 08:04:00', 3, 'assunto');

-- --------------------------------------------------------

--
-- Struttura della tabella `farmaci`
--

CREATE TABLE `farmaci` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descrizione` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `farmaci`
--

INSERT INTO `farmaci` (`id`, `nome`, `descrizione`) VALUES
(1, 'Paracetamoloo', 'Farmaco analgesico e antipiretico, usato per il trattamento del dolore lieve o moderato e febbre'),
(2, 'Ibuprofene', 'Antinfiammatorio non steroideo (FANS), utile contro infiammazioni, dolori muscolari e febbre.'),
(3, 'Amoxicillina', 'Antibiotico appartenente alla famiglia delle penicilline, usato per infezioni batteriche.'),
(4, 'Loratadina', 'Antistaminico usato per trattare allergie, raffreddore da fieno e orticaria.'),
(5, 'Metformina', 'Farmaco per il trattamento del diabete di tipo 2, aiuta a controllare i livelli di glucosio nel sangue.'),
(24, 'Antiparlante', 'parla troppo in biblioteca'),
(25, 'Tachipirina', 'Farmaco: Tachipirina Principi Attivi: Paracetamolo. Interazioni: Non assumere con alcol e droghe'),
(27, 'Aspirina', 'Farmaco antinfiammatorio, analgesico e antipiretico, utilizzato per alleviare il dolore e ridurre l’infiammazione.'),
(28, 'Omeprazolo', 'Farmaco inibitore della pompa protonica utilizzato per il trattamento delle ulcere gastriche e reflusso gastroesofageo.'),
(29, 'Simvastatina', 'Farmaco utilizzato per abbassare i livelli di colesterolo nel sangue e ridurre il rischio di malattie cardiovascolari.'),
(30, 'Captopril', 'Farmaco antipertensivo usato per trattare l’ipertensione e prevenire complicazioni cardiovascolari.'),
(31, 'Losartan', 'Farmaco utilizzato per trattare l’ipertensione e ridurre il rischio di ictus e infarto.'),
(32, 'Fexofenadina', 'Antistaminico utilizzato per trattare i sintomi di allergie stagionali come rinite allergica e orticaria.'),
(33, 'Salbutamolo', 'Farmaco broncodilatatore usato per trattare l’asma e altre malattie respiratorie ostruttive.'),
(34, 'Diazepam', 'Farmaco ansiolitico appartenente alla classe delle benzodiazepine, utilizzato per trattare ansia e convulsioni.'),
(35, 'Acido Acetilsalicilico', 'Farmaco antinfiammatorio non steroideo (FANS), usato come analgesico, antipiretico e antiaggregante piastrinico.'),
(36, 'Lansoprazolo', 'Farmaco inibitore della pompa protonica usato per trattare le ulcere gastriche e il reflusso gastroesofageo.'),
(40, 'Nurofen', 'Farmaco: prova2 Principi Attivi: Paracetamolo. Effetti Indesiderati: Mal di testa. '),
(41, 'provap', 'Farmaco: provap Principi Attivi: Ibuprofene. Effetti Indesiderati: Nausea. ');

-- --------------------------------------------------------

--
-- Struttura della tabella `info_pazienti`
--

CREATE TABLE `info_pazienti` (
  `id` int(11) NOT NULL,
  `id_paziente` int(11) NOT NULL,
  `note` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`note`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `info_pazienti`
--

INSERT INTO `info_pazienti` (`id`, `id_paziente`, `note`) VALUES
(3, 18, '{\"fumo\":\"Fumatore\",\"alcol\":\"Consumo occasionale\",\"obesita\":\"Sovrappeso\",\"ipertensione\":\"Normoteso\",\"colesterolo\":\"No\",\"stupefacenti\":\"Attuale\",\"attivita\":\"Moderata\",\"dieta\":\"Dieta povera di carboidrati\",\"descrizione\":\"Il paziente non si dimostra collaborativo\"}'),
(4, 54, '{\"fumo\":\"Fumatore\",\"alcol\":\"Consumo abituale\",\"obesita\":\"Obeso\",\"ipertensione\":\"Ipertensione diagnosticata\",\"colesterolo\":\"S\\u00ec\",\"stupefacenti\":\"Attuale\",\"attivita\":\"Moderata\",\"dieta\":\"Dieta a basso contenuto di zuccheri\",\"descrizione\":\"\"}');

-- --------------------------------------------------------

--
-- Struttura della tabella `messaggi`
--

CREATE TABLE `messaggi` (
  `id` int(11) NOT NULL,
  `id_sender` int(11) NOT NULL,
  `id_receiver` int(11) NOT NULL,
  `data_invio` date NOT NULL,
  `ora_invio` time NOT NULL,
  `oggetto` varchar(255) NOT NULL,
  `corpo` text NOT NULL,
  `tipo` char(1) NOT NULL,
  `letto` varchar(5) NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `messaggi`
--

INSERT INTO `messaggi` (`id`, `id_sender`, `id_receiver`, `data_invio`, `ora_invio`, `oggetto`, `corpo`, `tipo`, `letto`) VALUES
(1, 9, 17, '2025-07-04', '11:21:57', 'Avviso Paziente: X Y Z', 'Il paziente non ha aderito alle sue prescrizioni negli ultimi 3 giorni', 'N', 'false'),
(2, 17, 18, '2025-07-04', '11:21:57', 'messaggio inviato da Medico Medico', 'prova prova', 'D', 'false'),
(3, 18, 17, '2025-07-04', '11:21:57', 'messaggio invitao da PAz Paz a MEdico Medico', 'provaaa', 'D', 'false'),
(4, 18, 18, '2025-07-04', '11:21:57', 'MEssaggio da PAZ PAz a PAZ PAZ', 'Hai dimenticato di assumere farmaco X', 'A', 'false'),
(6, 18, 17, '2025-07-08', '14:32:00', 'Avviso paziente: Mario Rossi mario.rossi@example.com', 'Il paziente non ha aderito alle sue prescrizioni negli ultimi 3 giorni', 'N', 'false'),
(14, 18, 17, '2025-07-09', '15:59:51', 'salve', 'richiesta appuntamento ', 'D', 'false'),
(21, 4, 17, '2025-07-10', '09:30:54', 'Avviso paziente: Giulia Neri giulia.neri@example.com', 'il paziente non ha aderito alle sue prescrizioni negli ultimi 3 giorni', 'N', 'false'),
(22, 20, 17, '2025-07-10', '09:30:55', 'Avviso paziente: Matteo Tessera matteo.tessera04@gmail.com', 'il paziente non ha aderito alle sue prescrizioni negli ultimi 3 giorni', 'N', 'false'),
(27, 18, 18, '2025-07-10', '10:33:31', 'Farmaco: Amoxicillina dimenticato', 'In data: 2025-07-09 hai dimenticato di assumere il farmaco: Amoxicillina', 'A', 'false'),
(28, 18, 17, '2025-07-10', '10:41:15', 'Avviso Glicemia alta: paz@paz.it', 'Il paziente paziente paziente ha rilevato una glicemia di valore: 190.02 ore dopo di un pasto', 'G', 'false'),
(29, 18, 17, '2025-07-10', '10:50:07', 'ciao', 'bello', 'D', 'false'),
(30, 18, 17, '2025-07-10', '10:57:10', 'Avviso Glicemia alta: paz@paz.it', 'Il paziente paziente paziente ha rilevato una glicemia di valore: 192.0 prima di un pasto', 'G', 'false'),
(31, 18, 17, '2025-07-10', '11:13:30', 'Avviso Glicemia bassa: paz@paz.it', 'Il paziente paziente paziente ha rilevato una glicemia di valore: 60.0 prima di un pasto', 'G', 'false'),
(33, 18, 18, '2025-07-11', '09:23:27', 'Farmaco: Paracetamolo dimenticato', 'In data: 2025-07-10 hai dimenticato di assumere il farmaco: Paracetamolo', 'A', 'false'),
(34, 18, 17, '2025-07-11', '15:48:00', 'Avviso Glicemia alta: paz@paz.it', 'Il paziente paziente paz ha rilevato una glicemia di valore: 190.0, dopo di un pasto', 'G', 'false'),
(36, 18, 18, '2025-07-12', '00:37:19', 'Farmaco: Amoxicillina dimenticato', 'In data: 2025-07-11 hai dimenticato di assumere il farmaco: Amoxicillina', 'A', 'false'),
(37, 18, 17, '2025-07-12', '00:45:07', 'Avviso Glicemia Alta', 'Paziente:\npaziente paz\nEmail: paz@paz.it\n\nValore glicemia rilevato: 852.0\nMomento della misurazione: PRIMA del pasto.', 'G', 'false'),
(38, 18, 17, '2025-07-12', '00:48:50', 'Avviso Glicemia Alta', 'Paziente: paziente paz\nEmail: paz@paz.it\n\nValore glicemia rilevato: 789.0\nMomento della misurazione: prima di un pasto.', 'G', 'false'),
(39, 18, 17, '2025-07-12', '01:02:05', '⚠ Avviso Glicemia Alta ⚠', 'Paziente: paziente paz\nEmail: paz@paz.it\n\nValore glicemia rilevato: 666.0\nMomento della misurazione: dopo un pasto.', 'G', 'false'),
(40, 18, 18, '2025-07-13', '10:09:07', 'Farmaco: Paracetamolo dimenticato', 'In data: 2025-07-12 hai dimenticato di assumere il farmaco: Paracetamolo', 'A', 'false'),
(41, 18, 18, '2025-07-14', '11:36:33', 'Farmaco: Amoxicillina dimenticato', 'In data: 2025-07-13 hai dimenticato di assumere il farmaco: Amoxicillina', 'A', 'false'),
(42, 17, 39, '2025-07-14', '16:19:10', 'Benvenuto Fasuto', 'Siamo lieti di accoglierti nel nostro gruppo. Che questa esperienza possa essere costruttiva, stimolante e, perché no, anche divertente.  Fausto, il tuo nome è già una garanzia di stile. Benvenuto a bordo — il team ti aspetta! 🚀', 'D', 'false'),
(43, 39, 17, '2025-07-14', '16:20:01', 'Grazie De medici', 'My G ', 'D', 'false'),
(44, 17, 39, '2025-07-14', '16:21:19', 'Moglie', 'Ora, una domanda di rito: come sta tua moglie, Eufrasina Gorgonzola? Salutacela, mi raccomando — dicono che abbia ancora vinto il premio “Miglior Risotto Fantasma 2024”.  Fausto, ci aspettiamo grandi cose da te. Benvenuto nel gruppo più sigillato della storia! 🔥', 'D', 'false'),
(45, 39, 17, '2025-07-14', '16:21:33', 'dd', 'dd', 'D', 'false'),
(46, 17, 39, '2025-07-14', '16:25:10', 'Oh Fausto, come va quel diabete?', 'Che fa, oggi ti ha concesso una tregua o sei ancora più dolce di una pastiera napoletana lasciata al sole? Guarda che a questo punto il sensore glicemico lo colleghiamo direttamente al citofono: così quando arrivi, sappiamo già se hai mangiato la Nutella di nascosto.  Fausto, ricordati che il pancreas l’hai in leasing e io sono l’officina autorizzata. Tienilo bene, altrimenti ti metto a dieta a base di riso in bianco e aria fritta.  Ti tengo d’occhio, zuccherino! 😎  ', 'D', 'false'),
(48, 18, 18, '2025-07-15', '21:43:10', 'Farmaco: Paracetamolo dimenticato', 'In data: 2025-07-14 hai dimenticato di assumere il farmaco: Paracetamolo', 'A', 'false'),
(49, 18, 17, '2025-07-15', '22:08:09', '⚠ Avviso Glicemia Bassa', 'Paziente: paziente pazientee\nEmail: paz@paz.it\n\nValore glicemia rilevato: 25.0\nMomento della misurazione: PRIMA di un pasto.', 'G', 'false'),
(50, 18, 17, '2025-07-15', '22:35:00', '⚠ Avviso Glicemia Bassa', 'Paziente: paziente pazientee\nEmail: paz@paz.it\n\nValore glicemia rilevato: 25.0\nMomento della misurazione: PRIMA di un pasto.', 'G', 'false'),
(52, 18, 17, '2025-07-16', '00:37:56', '⚠ Avviso Glicemia Bassa', 'Paziente: paziente pazientee\nEmail: paz@paz.it\n\nValore glicemia rilevato: 25.0\nMomento della misurazione: PRIMA di un pasto.', 'G', 'false'),
(53, 18, 17, '2025-07-16', '00:42:44', '⚠ Avviso Glicemia Alta', 'Paziente: paziente pazientee\nEmail: paz@paz.it\n\nValore glicemia rilevato: 2552.0\nMomento della misurazione: dopo un pasto.', 'G', 'false'),
(54, 18, 17, '2025-07-16', '00:46:09', '⚠ Avviso Glicemia Bassa', 'Paziente: paziente pazientee\nEmail: paz@paz.it\n\nValore glicemia rilevato: 15.25\nMomento della misurazione: PRIMA di un pasto.', 'G', 'false'),
(56, 39, 39, '2025-07-16', '08:45:05', 'Farmaco: prova dimenticato', 'In data: 2025-07-15 hai dimenticato di assumere il farmaco: prova', 'A', 'false'),
(58, 18, 18, '2025-07-16', '10:41:21', 'Farmaco: Amoxicillina dimenticato', 'In data: 2025-07-15 hai dimenticato di assumere il farmaco: Amoxicillina', 'A', 'false'),
(59, 39, 17, '2025-07-16', '11:08:17', 'Avviso Non Aderenza', 'Paziente:\nPausto Sigillato\nEmail: fausto@sigillato.com\n\nNon ha aderito alle prescrizioni negli ultimi 3 giorni.', 'N', 'false'),
(60, 17, 18, '2025-07-16', '11:21:21', 'Buongiorno', 'Le ricordo del appuntamento fissato in data 20/07/2025', 'D', 'false'),
(64, 49, 49, '2025-07-16', '12:36:35', 'Farmaco: Simvastatina dimenticato', 'In data: 2025-07-15 hai dimenticato di assumere il farmaco: Simvastatina', 'A', 'false'),
(65, 60, 60, '2025-07-16', '15:23:19', 'Farmaco: Simvastatina dimenticato', 'In data: 2025-07-15 hai dimenticato di assumere il farmaco: Simvastatina', 'A', 'false'),
(70, 60, 17, '2025-07-16', '18:20:56', 'Avviso Non Aderenza', 'Paziente:\nMarco Leone\nEmail: marco.leone@email.it\n\nNon ha aderito alle prescrizioni negli ultimi 3 giorni.', 'N', 'false'),
(71, 17, 49, '2025-07-16', '18:29:43', 'dfd', 'dfdf', 'D', 'false'),
(72, 18, 18, '2025-07-16', '18:30:29', 'Farmaco: Paracetamoloo dimenticato', 'In data: 2025-07-15 hai dimenticato di assumere il farmaco: Paracetamoloo', 'A', 'false'),
(73, 18, 18, '2025-07-16', '18:30:31', 'Farmaco: Ibuprofene dimenticato', 'In data: 2025-07-15 hai dimenticato di assumere il farmaco: Ibuprofene', 'A', 'false'),
(74, 18, 17, '2025-07-16', '18:31:47', '⚠ Avviso Glicemia Alta', 'Paziente: Tommaso Conti\nEmail: paz@paz.it\n\nValore glicemia rilevato: 190.0\nMomento della misurazione: dopo un pasto.', 'G', 'false');

-- --------------------------------------------------------

--
-- Struttura della tabella `modifiche`
--

CREATE TABLE `modifiche` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `id_utente` int(11) NOT NULL,
  `tabella` varchar(50) NOT NULL,
  `colonna` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `patologie`
--

CREATE TABLE `patologie` (
  `id` int(11) NOT NULL,
  `paziente_id` int(11) NOT NULL,
  `nome_patologia` varchar(255) NOT NULL,
  `data_diagnosi` date NOT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `patologie`
--

INSERT INTO `patologie` (`id`, `paziente_id`, `nome_patologia`, `data_diagnosi`, `note`) VALUES
(1, 18, 'Ipertensione arteriosa', '2025-06-24', 'note varie'),
(2, 18, 'Obesità', '2025-06-02', 'note sulla patologia'),
(10, 18, 'patologia', '2025-07-09', 'ciao');

-- --------------------------------------------------------

--
-- Struttura della tabella `rilevazioni`
--

CREATE TABLE `rilevazioni` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `valore` double NOT NULL,
  `tipo` enum('pre','post') NOT NULL,
  `id_paziente` int(11) NOT NULL,
  `pasto` enum('colazione','pranzo','cena') NOT NULL DEFAULT 'colazione'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `rilevazioni`
--

INSERT INTO `rilevazioni` (`id`, `data`, `valore`, `tipo`, `id_paziente`, `pasto`) VALUES
(1, '2025-05-24 08:00:00', 120, 'pre', 1, 'colazione'),
(2, '2025-05-24 08:15:00', 130, 'post', 4, 'colazione'),
(4, '2025-05-24 09:10:00', 125, 'post', 4, 'colazione'),
(6, '2025-05-24 10:05:00', 128, 'post', 4, 'colazione'),
(8, '2025-05-24 11:15:00', 131, 'post', 4, 'colazione'),
(10, '2025-05-24 12:10:00', 129, 'post', 4, 'colazione'),
(13, '2025-06-02 14:16:40', 10, 'pre', 1, 'colazione'),
(14, '2025-06-02 14:27:42', 69, 'pre', 1, 'colazione'),
(19, '2025-06-05 16:09:10', 69, 'pre', 18, 'colazione'),
(20, '2025-06-09 11:28:10', 96, 'pre', 18, 'pranzo'),
(21, '2025-06-11 16:35:35', 120, 'pre', 18, 'colazione'),
(22, '2025-06-11 16:42:00', 140, 'pre', 18, 'colazione'),
(23, '2025-06-11 16:44:00', 182, 'pre', 18, 'pranzo'),
(24, '2025-06-11 16:56:47', 130, 'pre', 18, 'colazione'),
(30, '2025-06-05 16:09:10', 130, 'post', 18, 'colazione'),
(31, '2025-06-09 11:28:10', 90, 'post', 18, 'colazione'),
(32, '2025-06-11 16:35:35', 155, 'post', 18, 'colazione'),
(33, '2025-06-11 16:42:36', 192, 'post', 18, 'colazione'),
(34, '2025-06-11 16:44:00', 166, 'post', 18, 'colazione'),
(35, '2025-06-11 16:56:47', 110, 'post', 18, 'colazione'),
(36, '2025-07-10 10:39:15', 190, 'post', 18, 'colazione'),
(37, '2025-07-10 10:41:15', 190, 'post', 18, 'pranzo'),
(38, '2025-07-10 10:57:09', 192, 'pre', 18, 'colazione'),
(39, '2025-07-10 11:13:30', 60, 'pre', 18, 'colazione'),
(40, '2025-07-10 16:20:17', 120, 'post', 18, 'cena'),
(41, '2025-07-10 16:25:41', 90, 'post', 18, 'cena'),
(42, '2025-07-11 15:48:00', 190, 'post', 18, 'colazione'),
(43, '2025-07-12 00:37:34', 580, 'post', 18, 'pranzo'),
(44, '2025-07-12 00:37:40', 580, 'post', 18, 'pranzo'),
(45, '2025-07-12 00:45:07', 852, 'pre', 18, 'colazione'),
(46, '2025-07-12 00:48:50', 789, 'pre', 18, 'pranzo'),
(47, '2025-07-12 01:02:05', 666, 'post', 18, 'colazione'),
(48, '2025-06-01 00:00:00', 98, 'pre', 45, 'colazione'),
(49, '2025-06-02 00:00:00', 100, 'pre', 45, 'colazione'),
(50, '2025-06-03 00:00:00', 95, 'pre', 45, 'colazione'),
(51, '2025-06-04 00:00:00', 105, 'pre', 45, 'colazione'),
(52, '2025-06-05 00:00:00', 102, 'pre', 45, 'colazione'),
(53, '2025-06-06 00:00:00', 97, 'pre', 45, 'colazione'),
(54, '2025-06-07 00:00:00', 99, 'pre', 45, 'colazione'),
(55, '2025-06-01 00:00:00', 110, 'pre', 46, 'colazione'),
(56, '2025-06-02 00:00:00', 108, 'pre', 46, 'colazione'),
(57, '2025-06-03 00:00:00', 112, 'pre', 46, 'colazione'),
(58, '2025-06-04 00:00:00', 107, 'pre', 46, 'colazione'),
(59, '2025-06-05 00:00:00', 109, 'pre', 46, 'colazione'),
(60, '2025-06-06 00:00:00', 111, 'pre', 46, 'colazione'),
(61, '2025-06-07 00:00:00', 113, 'pre', 46, 'colazione'),
(62, '2025-06-01 00:00:00', 90, 'pre', 47, 'colazione'),
(63, '2025-06-02 00:00:00', 92, 'pre', 47, 'colazione'),
(64, '2025-06-03 00:00:00', 89, 'pre', 47, 'colazione'),
(65, '2025-06-04 00:00:00', 95, 'pre', 47, 'colazione'),
(66, '2025-06-05 00:00:00', 91, 'pre', 47, 'colazione'),
(67, '2025-06-06 00:00:00', 88, 'pre', 47, 'colazione'),
(68, '2025-06-07 00:00:00', 94, 'pre', 47, 'colazione'),
(69, '2025-06-01 00:00:00', 102, 'pre', 48, 'colazione'),
(70, '2025-06-02 00:00:00', 104, 'pre', 48, 'colazione'),
(71, '2025-06-03 00:00:00', 103, 'pre', 48, 'colazione'),
(72, '2025-06-04 00:00:00', 107, 'pre', 48, 'colazione'),
(73, '2025-06-05 00:00:00', 105, 'pre', 48, 'colazione'),
(74, '2025-06-06 00:00:00', 108, 'pre', 48, 'colazione'),
(75, '2025-06-07 00:00:00', 109, 'pre', 48, 'colazione'),
(76, '2025-06-01 00:00:00', 85, 'pre', 49, 'colazione'),
(77, '2025-06-02 00:00:00', 87, 'pre', 49, 'colazione'),
(78, '2025-06-03 00:00:00', 88, 'pre', 49, 'colazione'),
(79, '2025-06-04 00:00:00', 91, 'pre', 49, 'colazione'),
(80, '2025-06-05 00:00:00', 89, 'pre', 49, 'colazione'),
(81, '2025-06-06 00:00:00', 83, 'pre', 49, 'colazione'),
(82, '2025-06-07 00:00:00', 90, 'pre', 49, 'colazione'),
(83, '2025-06-01 00:00:00', 96, 'pre', 50, 'colazione'),
(84, '2025-06-02 00:00:00', 98, 'pre', 50, 'colazione'),
(85, '2025-06-03 00:00:00', 95, 'pre', 50, 'colazione'),
(86, '2025-06-04 00:00:00', 100, 'pre', 50, 'colazione'),
(87, '2025-06-05 00:00:00', 97, 'pre', 50, 'colazione'),
(88, '2025-06-06 00:00:00', 94, 'pre', 50, 'colazione'),
(89, '2025-06-07 00:00:00', 99, 'pre', 50, 'colazione'),
(90, '2025-06-01 00:00:00', 105, 'pre', 51, 'colazione'),
(91, '2025-06-02 00:00:00', 106, 'pre', 51, 'colazione'),
(92, '2025-06-03 00:00:00', 107, 'pre', 51, 'colazione'),
(93, '2025-06-04 00:00:00', 110, 'pre', 51, 'colazione'),
(94, '2025-06-05 00:00:00', 108, 'pre', 51, 'colazione'),
(95, '2025-06-06 00:00:00', 109, 'pre', 51, 'colazione'),
(96, '2025-06-07 00:00:00', 111, 'pre', 51, 'colazione'),
(97, '2025-06-01 00:00:00', 85, 'pre', 52, 'colazione'),
(98, '2025-06-02 00:00:00', 84, 'pre', 52, 'colazione'),
(99, '2025-06-03 00:00:00', 83, 'pre', 52, 'colazione'),
(100, '2025-06-04 00:00:00', 87, 'pre', 52, 'colazione'),
(101, '2025-06-05 00:00:00', 86, 'pre', 52, 'colazione'),
(102, '2025-06-06 00:00:00', 88, 'pre', 52, 'colazione'),
(103, '2025-06-07 00:00:00', 90, 'pre', 52, 'colazione'),
(104, '2025-06-01 00:00:00', 92, 'pre', 53, 'colazione'),
(105, '2025-06-02 00:00:00', 94, 'pre', 53, 'colazione'),
(106, '2025-06-03 00:00:00', 93, 'pre', 53, 'colazione'),
(107, '2025-06-04 00:00:00', 95, 'pre', 53, 'colazione'),
(108, '2025-06-05 00:00:00', 97, 'pre', 53, 'colazione'),
(109, '2025-06-06 00:00:00', 98, 'pre', 53, 'colazione'),
(110, '2025-06-07 00:00:00', 99, 'pre', 53, 'colazione'),
(111, '2025-06-01 00:00:00', 100, 'pre', 54, 'colazione'),
(112, '2025-06-02 00:00:00', 102, 'pre', 54, 'colazione'),
(113, '2025-06-03 00:00:00', 101, 'pre', 54, 'colazione'),
(114, '2025-06-04 00:00:00', 103, 'pre', 54, 'colazione'),
(115, '2025-06-05 00:00:00', 104, 'pre', 54, 'colazione'),
(116, '2025-06-06 00:00:00', 105, 'pre', 54, 'colazione'),
(117, '2025-06-07 00:00:00', 106, 'pre', 54, 'colazione'),
(118, '2025-06-01 00:00:00', 110, 'pre', 55, 'colazione'),
(119, '2025-06-02 00:00:00', 112, 'pre', 55, 'colazione'),
(120, '2025-06-03 00:00:00', 113, 'pre', 55, 'colazione'),
(121, '2025-06-04 00:00:00', 114, 'pre', 55, 'colazione'),
(122, '2025-06-05 00:00:00', 115, 'pre', 55, 'colazione'),
(123, '2025-06-06 00:00:00', 116, 'pre', 55, 'colazione'),
(124, '2025-06-07 00:00:00', 117, 'pre', 55, 'colazione'),
(125, '2025-06-01 00:00:00', 105, 'pre', 56, 'colazione'),
(126, '2025-06-02 00:00:00', 107, 'pre', 56, 'colazione'),
(127, '2025-06-03 00:00:00', 108, 'pre', 56, 'colazione'),
(128, '2025-06-04 00:00:00', 109, 'pre', 56, 'colazione'),
(129, '2025-06-05 00:00:00', 110, 'pre', 56, 'colazione'),
(130, '2025-06-06 00:00:00', 111, 'pre', 56, 'colazione'),
(131, '2025-06-07 00:00:00', 112, 'pre', 56, 'colazione'),
(132, '2025-06-01 00:00:00', 98, 'pre', 57, 'colazione'),
(133, '2025-06-02 00:00:00', 100, 'pre', 57, 'colazione'),
(134, '2025-06-03 00:00:00', 101, 'pre', 57, 'colazione'),
(135, '2025-06-04 00:00:00', 102, 'pre', 57, 'colazione'),
(136, '2025-06-05 00:00:00', 103, 'pre', 57, 'colazione'),
(137, '2025-06-06 00:00:00', 104, 'pre', 57, 'colazione'),
(138, '2025-06-07 00:00:00', 105, 'pre', 57, 'colazione'),
(140, '2025-07-15 05:00:00', 65, 'pre', 18, 'pranzo'),
(141, '2025-07-16 00:37:56', 25, 'pre', 18, 'colazione'),
(142, '2025-07-16 00:42:43', 2552, 'post', 18, 'pranzo'),
(143, '2025-07-16 00:46:08', 15.25, 'pre', 18, 'pranzo'),
(144, '2025-07-16 13:31:00', 190, 'post', 18, 'pranzo');

-- --------------------------------------------------------

--
-- Struttura della tabella `sintomi`
--

CREATE TABLE `sintomi` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `descrizione` text NOT NULL,
  `id_paziente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `sintomi`
--

INSERT INTO `sintomi` (`id`, `data`, `descrizione`, `id_paziente`) VALUES
(2, '2025-05-24 09:30:00', 'Febbre alta', 4),
(4, '2025-05-24 11:00:00', 'Dolore alla gola', 4),
(6, '2025-05-25 08:30:00', 'Stanchezza e affaticamento', 4),
(8, '2025-05-25 10:00:00', 'Bruciore agli occhi', 4),
(10, '2025-05-25 12:00:00', 'Perdita di appetito', 4),
(12, '2025-06-06 17:30:00', 'Sintomo1', 18),
(13, '2025-07-10 17:59:00', 'Cancro al solenoide', 18),
(14, '2025-06-08 17:15:00', 'Mal di Testa', 18),
(15, '2025-06-09 11:30:42', 'Nausea ciao', 18),
(16, '2025-06-25 17:56:35', 'Minzione frequente', 18),
(17, '2025-07-05 19:28:00', 'Sintomo2', 18),
(18, '2025-07-19 19:28:00', 'prova', 18),
(19, '2025-07-05 19:28:00', 'Sintomo3', 18),
(20, '2025-07-05 19:28:14', 'Nausea', 18),
(21, '2025-07-05 19:28:15', 'Nausea', 18),
(23, '2025-07-09 17:09:33', 'Perdita di peso inspiegabile', 18),
(24, '2025-07-10 11:13:02', 'Nausea frequente', 18),
(25, '2025-07-15 23:04:33', 'prova', 18),
(26, '2025-07-25 04:59:00', 'Nausea', 18),
(27, '2025-07-16 00:48:56', 'Mal di Testa', 18),
(28, '2025-07-16 18:36:11', 'Mal di Test', 18);

-- --------------------------------------------------------

--
-- Struttura della tabella `sintomi_concomitanti`
--

CREATE TABLE `sintomi_concomitanti` (
  `id` int(11) NOT NULL,
  `paziente_id` int(11) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `data_inizio` date NOT NULL,
  `frequenza` varchar(100) NOT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `sintomi_concomitanti`
--

INSERT INTO `sintomi_concomitanti` (`id`, `paziente_id`, `descrizione`, `data_inizio`, `frequenza`, `note`) VALUES
(1, 18, 'Mal di testa persistente', '2025-06-24', '10 volte al giorno ', 'la sera'),
(2, 18, 'Dolore muscolare alle gambe', '2025-06-24', '2', 'al mattino'),
(6, 45, 'Mal di testa persistente', '2025-06-01', 'Ogni giorno', 'Frequente al mattino'),
(7, 46, 'Dolore muscolare alle gambe', '2025-06-01', 'Ogni settimana', 'Aumenta dopo l\'attività fisica'),
(8, 47, 'Fiato corto e difficoltà respiratoria', '2025-06-01', '3 volte a settimana', 'Dovuto probabilmente alla stagione allergica'),
(9, 48, 'Gonfiore addominale dopo i pasti', '2025-06-01', 'Ogni giorno', 'Probabile legato alla digestione'),
(10, 49, 'Vertigini e nausea', '2025-06-01', 'Ogni volta che si alza in piedi', 'Sospetta disidratazione');

-- --------------------------------------------------------

--
-- Struttura della tabella `studi`
--

CREATE TABLE `studi` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `indirizzo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `studi`
--

INSERT INTO `studi` (`id`, `nome`, `indirizzo`) VALUES
(1, 'Studio Medico Verona', 'Via Roma 10, Verona'),
(2, 'Studio Medico Milano', 'Via Milano 20, Milano'),
(3, 'Studio Medico Napoli', 'Via Napoli 30, Napoli'),
(4, 'Studio Medico Roma', 'Via Roma 1, Roma'),
(5, 'Studio Medico Firenze', 'Via Firenze 5, Firenze'),
(6, 'Studio Medico Torino', 'Via Torino 7, Torino'),
(7, 'Studio Medico Bologna', 'Via Bologna 15, Bologna'),
(8, 'Studio Medico Palermo', 'Via Palermo 10, Palermo');

-- --------------------------------------------------------

--
-- Struttura della tabella `terapie`
--

CREATE TABLE `terapie` (
  `id` int(11) NOT NULL,
  `data_inizio` date NOT NULL,
  `data_fine` date NOT NULL,
  `note` text DEFAULT NULL,
  `id_paziente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `terapie`
--

INSERT INTO `terapie` (`id`, `data_inizio`, `data_fine`, `note`, `id_paziente`) VALUES
(3, '2025-07-09', '2025-07-31', 'terapia paz paz', 18),
(6, '2025-07-08', '2025-07-23', 'Terapia per il paziente paz paz', 18),
(28, '2025-06-09', '2025-06-21', 'prova', 20),
(29, '2025-06-02', '2025-06-28', 'prova', 20),
(30, '2025-06-10', '2025-06-14', 'prova', 20),
(31, '2025-06-10', '2025-06-14', 'prova', 20),
(32, '2025-06-02', '2025-06-21', 'prova', 20),
(33, '2025-06-10', '2025-06-20', 'prova', 9),
(34, '2025-07-16', '2025-07-23', 'Lontano dai pasti, Durante i pasti, A stomaco vuoto, Prima di coricarsi, Ogni 12 ore', 4),
(35, '2025-07-16', '2025-07-23', 'A stomaco vuoto, Prima di coricarsi, Al mattino, Alla sera', 20),
(36, '2025-07-16', '2025-07-23', 'Terapia per il paziente Francesca Romano', 45),
(37, '2025-07-16', '2025-07-23', 'Terapia per il paziente Giorgio Lupo', 46),
(38, '2025-07-16', '2025-07-23', 'Terapia per il paziente Sofia Rossi', 47),
(39, '2025-07-16', '2025-07-23', 'Terapia per il paziente Marta Verdi', 48),
(40, '2025-07-16', '2025-07-23', 'Terapia per il paziente Andrea Bianchi', 49),
(41, '2025-07-16', '2025-07-23', 'Terapia per il paziente Luca Santoro', 50),
(42, '2025-07-16', '2025-07-23', 'Terapia per il paziente Elisabetta Greco', 51),
(43, '2025-07-16', '2025-07-23', 'Terapia per il paziente Simone Verdi', 52),
(44, '2025-07-16', '2025-07-23', 'Terapia per il paziente Alice Rossetti', 53),
(45, '2025-07-16', '2025-07-23', 'Terapia per il paziente Giuseppe Neri', 54),
(46, '2025-07-16', '2025-07-23', 'Terapia per il paziente Giovanni Neri', 55),
(47, '2025-07-16', '2025-07-23', 'Terapia per il paziente Alessandra Tori', 56),
(55, '2025-07-16', '2025-07-23', 'Ogni 8 ore', 9),
(56, '2025-07-16', '2025-07-23', 'Ogni 8 ore', 9),
(58, '2025-07-08', '2025-07-31', 'A stomaco vuoto', 60),
(59, '2025-07-16', '2025-07-31', 'Dopo i pasti', 9);

-- --------------------------------------------------------

--
-- Struttura della tabella `terapie_concomitanti`
--

CREATE TABLE `terapie_concomitanti` (
  `id` int(11) NOT NULL,
  `paziente_id` int(11) NOT NULL,
  `farmaco` varchar(255) NOT NULL,
  `data_inizio` date NOT NULL,
  `data_fine` date NOT NULL,
  `frequenza` varchar(100) NOT NULL,
  `dose` varchar(100) NOT NULL,
  `indicazioni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `terapie_concomitanti`
--

INSERT INTO `terapie_concomitanti` (`id`, `paziente_id`, `farmaco`, `data_inizio`, `data_fine`, `frequenza`, `dose`, `indicazioni`) VALUES
(1, 18, 'MDMA', '2025-06-03', '2025-06-27', 'ogni 2 giorni', '500 mg', 'alla sera'),
(6, 45, 'MDMA', '2025-06-03', '2025-06-27', 'Ogni 2 giorni', '500 mg', 'Alla sera'),
(7, 46, 'Provacina', '2025-06-24', '2025-06-27', 'Adwwa', 'Aleoleee', 'Dawd'),
(8, 47, 'Loratadina', '2025-06-01', '2025-06-10', 'Ogni giorno', '10 mg', 'Trattamento delle allergie stagionali'),
(9, 48, 'Ibuprofene', '2025-06-01', '2025-06-14', '3 volte al giorno', '200 mg', 'Per il trattamento del dolore muscolare'),
(10, 49, 'Paracetamolo', '2025-06-05', '2025-06-12', 'Ogni 4 ore', '500 mg', 'Per febbre e mal di testa'),
(11, 18, 'Sertralina', '2025-04-01', '2025-09-30', 'Ogni giorno', '25', '2 compresse al giorno');

-- --------------------------------------------------------

--
-- Struttura della tabella `terapie_generiche`
--

CREATE TABLE `terapie_generiche` (
  `id` int(11) NOT NULL,
  `data_inizio` date NOT NULL,
  `data_fine` date DEFAULT NULL,
  `id_paziente` int(11) NOT NULL,
  `id_farmaco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` enum('paziente','medico','admin') NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cognome` varchar(100) NOT NULL,
  `cod_fiscale` varchar(16) NOT NULL,
  `data_nascita` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `indirizzo` varchar(255) NOT NULL,
  `genere` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `password`, `ruolo`, `nome`, `cognome`, `cod_fiscale`, `data_nascita`, `email`, `telefono`, `indirizzo`, `genere`) VALUES
(1, '$2y$10$z7bngaz.D1X3ewo4E/ViKe1UMhgSz3WhpP6EKvW3iVk6.nyKqo3fe', 'medico', 'Mario', 'Rossignolo', 'RSSMRA80A01H501U', '1980-01-01', 'mario.rossi@example.com', '3331234567', 'Via Roma 1, Verona', 'M'),
(3, '$2y$10$uZzK04NAAiBoOhNX7DbpKuLYsDUEVElERlQmfXr6SNfUE3f5gDCLy', 'medico', 'Anna', 'Verdi', 'VRDANN90C20H501W', '1990-03-20', 'anna.verdi@example.com', '3335558888', 'Via Torino 3, Milano', 'F'),
(4, '$2y$10$kNRw07Vq.PoFycj6jp0Jre7bPDtPrk0PVlJ20HRUBu9OM4KQL./Qu', 'paziente', 'Giulia', 'Neri', 'NRGGLL85D25H501X', '1985-04-25', 'giulia.neri@example.com', '3334442222', 'Via Firenze 4, Napoli', 'F'),
(5, '$2y$10$Hx9ekj961R3R2lACIp9ckeqZnJLAcQOklHTylHDwSP9JUMqorNsWW', 'medico', 'Paolo', 'Santoro', 'SNTPAO70E30H501Y', '1970-05-30', 'paolo.santoro@example.com', '3337776666', 'Via Venezia 5, Roma', 'M'),
(8, '$2y$10$1ycGZGwfUo6C/e/1TKQ9SeX.PExWlz2YB5UHD5Nj3Vllhx./vRI2e', 'medico', 'Carlo', 'Bianchi', 'BNCCRL75A10H501Z', '1975-01-10', 'carlo.bianchi@example.com', '3331122334', 'Via Napoli 10, Torino', 'M'),
(9, '$2y$10$oss16cyLXNcIQ1h5dzT2teH2ioSdK/WX1/fUAZmtZCJIxiK/EQkLe', 'paziente', 'Sara', 'Rossi', 'RSSSRA92B15H501Y', '1992-02-15', 'sara.rossi@example.com', '3339988776', 'Via Palermo 15, Firenze', 'F'),
(17, '$2y$10$dYhcZTG6uM5mytF05e.gUedY7Los0D68kU1smjiJz2PQVN6TMPPqS', 'medico', 'Lorenzo', 'De Medici', 'TSFGTN25H25B658T', '2025-07-11', 'medico@medico.it', '3385695658', 'Via verdi', 'M'),
(18, '$2y$10$e3TRAgmcnmWPO3HFAStvd.s6OjR90F1pWauhEXFSI1Vl5u3OHeBz2', 'paziente', 'Tommaso', 'Conti', 'TSFGTN25H25B658Q', '2004-06-12', 'paz@paz.it', '36958861452', 'Via verdi 25, Verona', 'M'),
(19, '$2y$10$vFzkukqMg6Jm5Vy13MQDjetqRhJOBgfV7VmR75hSB2vpAYX10GEC.', 'admin', 'Mark', 'Zuckerberg', 'MRKZUC15F14B258T', '1985-12-21', 'admin@admin.it', '3562584759', 'Via verona, ', 'F'),
(20, '$2y$10$URGLjXyOPlPlrxBCA95t/u09b/WrkYwdr5GTHzBvpfzxLa7T91SbO', 'paziente', 'Matteo', 'Tessera', 'TSSMTT04H12V588E', '2025-06-12', 'matteo.tessera04@gmail.com', '3694851425', 'Via Salvo D\'Acquisto, 14, Padova', 'M'),
(32, '$2y$10$laBLIDPWj.nW3jlKI3X/TO5mgDXLiMlhxQShQCwEGOF7b28v7jJ.G', 'paziente', 'Matteo', 'Tessera', 'TSSMTT04H12B296T', '2025-06-25', 'teo@gmail.com', '3526369878', 'Via roma 14, Roma', 'M'),
(34, '$2y$10$.lVv6gVHGM7N0UQ9RtRTH.nxmYjBSvYeF1Y6v/WDKUVebNeuMyXEi', 'paziente', 'Arianna', 'Vaccaro', 'ARNVCC25F15B569T', '2025-06-05', 'arianna.vaccaro@example.it', '3391224467', 'Via Centro, Bolzano', 'F'),
(35, '$2y$10$oAJFJZ5LGrXx4McQrAj.1.d0XykqSl/HuRCOWVD1A8eoku0qLGzJC', 'paziente', 'Lugio', 'Nugio', 'GPPMAL04N19U463I', '2025-06-05', 'Lupo@lucio.it', '3385335667', 'Via Fago 14B, Bolzano', 'M'),
(37, '$2y$10$SbHTyKyVuJNGizTwFlhX4eTGYmLU2QAoJaAYYZV3xxNKnXZlwoOme', 'medico', 'Andrea', 'Panini', 'ANDMSN05H58B296R', '1965-05-12', 'andrea.panini@gmail.com', '352698585657', 'Strada le Grazie, 25', 'M'),
(38, '$2y$10$qRhjqbwErLHGK/2iJd6Ebufr4776l/QN2r/cgkUiTEdwmo1/g6Upy', 'medico', 'Luca ', 'Geretti', 'LCAGRT05H15B296T', '1985-06-12', 'luca.geretti@gmail.com', '355369585645848', 'Via Roma 16B', 'M'),
(39, '$2y$10$mq.xzk1pKUxM/pWqp61qjeVGNBAZh5LPLovlRkNutTiL11nLhfw/S', 'paziente', 'Fausto', 'Sigillato', 'GMNMNO02P18A034K', '2001-09-11', 'fausto@sigillato.com', '2341127652', 'Via Napoli 25, Napoli', 'M'),
(40, '$2y$10$DrnQvEG1HkR6NkYFIgCtY.jrDX/N1SZnCns2gNpd7RLD/m1HQaf0m', 'medico', 'Mario', 'Bianchi', 'MARIOCF1234', '1980-01-01', 'mario.bianchi@example.com', '3331234567', 'Via Roma 1, Verona', 'M'),
(41, '$2y$10$ncJnFRmlE.p0IUqxeiuQhu0bNbUfENqTJ4N8xhs7z7jyH9WHEl4KS', 'medico', 'Giulia', 'Verdi', 'GIULIACF1234', '1985-05-12', 'giulia.verdi@example.com', '3332345678', 'Via Milano 10, Milano', 'F'),
(42, '$2y$10$JxFdEOBHL5VRG.s9BW0izuWUQrMkUdVEYAvb43.DMoN4TsaT5G7By', 'medico', 'Marco', 'Rossi', 'MARCACF1234', '1975-11-21', 'marco.rossi@example.com', '3333456789', 'Via Napoli 15, Roma', 'M'),
(43, '$2y$10$2z6NHD1445hfAz4Zuhys4.fijpgFKASEeiS1z/hu0wBeCz7v/CMgW', 'medico', 'Federico', 'Neri', 'FREDCF1234', '1982-03-30', 'federico.neri@example.com', '3334567890', 'Via Torino 7, Firenze', 'M'),
(44, '$2y$10$XtXMmwG3s4TQyjWIWXilwe9Dv5errGMQRsivwylN8xiIDtiv7nrZe', 'medico', 'Elena', 'Santoro', 'ELENACF1234', '1990-06-18', 'elena.santoro@example.com', '3335678901', 'Via Bologna 12, Napoli', 'F'),
(45, '$2y$10$xzsXPzFLTr3u.6cdjVYE2ONQHP4LdXN.mcAgap6d/vVyVNsfsogL.', 'paziente', 'Francesca', 'Romano', 'FRAROM05B15V256R', '1995-07-15', 'francesca.romano@example.com', '3336789012', 'Via Milano 3, Torino', 'F'),
(46, '$2y$10$vZcBggriwGfb5wmrHbJJ3eW5HnLGkUZM0GSDoAXglUXfVvuamIiW6', 'paziente', 'Giorgio', 'Lupo', 'GIOLUP25H14G456T', '1990-05-10', 'giorgio.lupo@example.com', '3337890123', 'Via Firenze 2, Roma', 'M'),
(47, '$2y$10$WEkY.0ZCY8UAxsV7iQh5SeyfZqQwFiKkFX/ZIjE9anyZrGnYz3/oe', 'paziente', 'Sofia', 'Rossi', 'SOFROS05H13B958T', '1992-11-30', 'sofia.rossi@example.com', '3338901234', 'Via Bologna 8, Milano', 'F'),
(48, '$2y$10$7kqdtcjfFn/f8FuIHSYw/u12V0QkSa97jZjpoq61OZcJTFIm3ocDq', 'paziente', 'Marta', 'Verdi', 'MARVER14D36B856P', '1997-01-25', 'marta.verdi@example.com', '3339012345', 'Via Torino 5, Firenze', 'F'),
(49, '$2y$10$3bG3FGv0C8tNw6nLEZsL5O5F0WF5nqKAAKmk5rgwxnJqMi3bQ.mQ6', 'paziente', 'Andrea', 'Bianchi', 'ANDBNI04G25B258T', '1993-08-12', 'andrea.bianchi@example.com', '3330123456', 'Via Roma 6, Napoli', 'M'),
(50, '$2y$10$JzRKmhK6l4u0CZF1J8nBvui4TFm5FgZGwRoVKhXTnuKS.NUdirZpS', 'paziente', 'Luca', 'Santoro', 'LUCSAN25D26B256R', '1994-02-28', 'luca.santoro@example.com', '3331234567', 'Via Milano 4, Roma', 'M'),
(51, '$2y$10$aaaUcVOCMLt9p0eBHYHBSeq5QBMW3Z3KZeXFeUFyDFLIfwdRxpa2S', 'paziente', 'Elisabetta', 'Greco', 'ELIGRE25H14T256T', '1998-04-16', 'elisabetta.greco@example.com', '3332345678', 'Via Napoli 10, Torino', 'F'),
(52, '$2y$10$vvlOpD.LicUONk0sE6g4xuuYDquqk45y2A8cKZrXzwFHunegf5/n.', 'paziente', 'Simone', 'Verdi', 'SIMVER14S25V369Y', '1991-12-02', 'simone.verdi@example.com', '3333456789', 'Via Bologna 12, Roma', 'M'),
(53, '$2y$10$K8pFrVMqfpcMMHXRxeaHyuTz4GEZBNWHfvY6b9x8KQDj.KSW8b5jS', 'paziente', 'Alice', 'Rossetti', 'ALIROS25H16B296R', '1988-07-20', 'alice.rossetti@example.com', '3334567890', 'Via Torino 15, Milano', 'F'),
(54, '$2y$10$o2oUt8eI2MDnpHay9zUAaOVOMIux7rCDoOenVxfMdVGo0vHG2CNHy', 'paziente', 'Giuseppe', 'Neri', 'GIUNER05E19S365H', '1987-05-14', 'giuseppe.neri@example.com', '3335678901', 'Via Roma 3, Firenze', 'M'),
(55, '$2y$10$BQ3I9ZiXrv2f.ZPSfW/sDuu7YKBo6XkkTCmPgDbK6LIBFf2SSZrju', 'paziente', 'Giovanni', 'Neri', 'GIONER23D29N256V', '1989-11-03', 'giovanni.neri@example.com', '3336789012', 'Via Milano 9, Napoli', 'M'),
(56, '$2y$10$Q.LpcGfZcHFI2vvCDL4fzuLPxSVYOVkIuMaBQzKRL/FeIj8lsiMZe', 'paziente', 'Alessandra', 'Tori', 'ALETOR14F25C365T', '1994-12-16', 'alessandra.tori@example.com', '3337890125', 'Via Roma 7, Torino', 'F'),
(57, '$2y$10$38Z8Ta.nuzVHWy6WeXul4.LzTb9aZPYXdm3Gv7ha.9BHXM6BzGN3.', 'paziente', 'Paolo', 'De Luca', 'PAODLC14D25B147T', '1985-01-14', 'paolo.deluca@example.com', '3338901234', 'Via Napoli 20, Firenze', 'M'),
(60, '$2y$10$.f5orJg9/9Vo6sRwTen7G.F7hxkx7R1gK//mdDQVF8vT5m5aymjgu', 'paziente', 'Marco', 'Leone', 'LNCMRC88M12H501Y', '1988-08-12', 'marco.leone@email.it', '3381234567', 'Via Verdi 10, 40121 Bologna (BO)', 'M'),
(61, '$2y$10$CtffsRakJg9bf.zNKwnxp.MXfHozQENvtTE0Tbf.SMWN/Ocut3B4a', 'paziente', 'matteo', 'tessera', 'FDFFGH25B14B259Y', '2025-07-16', 'm@gmail.com', '0123456789', 'as', 'M'),
(62, '$2y$10$P0UX5RpJKvobTyMNIK2Z2uawechoVv31hXszkhXvrsfzue4rbZlOG', 'medico', 'greg', 'gert', 'TSSMTT04H12B296R', '2025-07-15', 'em@lettere.it', '3381884446', 'scs', 'M'),
(63, '$2y$10$VabOBBaeHm2w3bSBuvGL4eTB26wpWnF0KOJN1c0QUFeTp.UR3AVoC', 'medico', 'marco', 'rossi', 'TSSMTT04H12B296R', '2025-07-09', 'marco@gmail.com', '0123456789', 'via roma', 'M');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `accessi`
--
ALTER TABLE `accessi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indici per le tabelle `assegnazioni_medici`
--
ALTER TABLE `assegnazioni_medici`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_paziente` (`id_paziente`),
  ADD KEY `id_medico` (`id_medico`);

--
-- Indici per le tabelle `assegnazioni_studi`
--
ALTER TABLE `assegnazioni_studi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_medico` (`id_medico`),
  ADD KEY `id_studio` (`id_studio`);

--
-- Indici per le tabelle `associazioni_farmaci`
--
ALTER TABLE `associazioni_farmaci`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_terapia` (`id_terapia`),
  ADD KEY `id_farmaco` (`id_farmaco`);

--
-- Indici per le tabelle `assunzioni`
--
ALTER TABLE `assunzioni`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_associazione_farmaco` (`id_associazione_farmaco`);

--
-- Indici per le tabelle `farmaci`
--
ALTER TABLE `farmaci`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `info_pazienti`
--
ALTER TABLE `info_pazienti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_paziente` (`id_paziente`);

--
-- Indici per le tabelle `messaggi`
--
ALTER TABLE `messaggi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sender` (`id_sender`),
  ADD KEY `id_receiver` (`id_receiver`);

--
-- Indici per le tabelle `modifiche`
--
ALTER TABLE `modifiche`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indici per le tabelle `patologie`
--
ALTER TABLE `patologie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_patologie_paziente` (`paziente_id`);

--
-- Indici per le tabelle `rilevazioni`
--
ALTER TABLE `rilevazioni`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_paziente` (`id_paziente`);

--
-- Indici per le tabelle `sintomi`
--
ALTER TABLE `sintomi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_paziente` (`id_paziente`);

--
-- Indici per le tabelle `sintomi_concomitanti`
--
ALTER TABLE `sintomi_concomitanti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paziente_id` (`paziente_id`);

--
-- Indici per le tabelle `studi`
--
ALTER TABLE `studi`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `terapie`
--
ALTER TABLE `terapie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_paziente` (`id_paziente`);

--
-- Indici per le tabelle `terapie_concomitanti`
--
ALTER TABLE `terapie_concomitanti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paziente_id` (`paziente_id`);

--
-- Indici per le tabelle `terapie_generiche`
--
ALTER TABLE `terapie_generiche`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_paziente` (`id_paziente`),
  ADD KEY `id_farmaco` (`id_farmaco`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `accessi`
--
ALTER TABLE `accessi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=278;

--
-- AUTO_INCREMENT per la tabella `assegnazioni_medici`
--
ALTER TABLE `assegnazioni_medici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT per la tabella `assegnazioni_studi`
--
ALTER TABLE `assegnazioni_studi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `associazioni_farmaci`
--
ALTER TABLE `associazioni_farmaci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT per la tabella `assunzioni`
--
ALTER TABLE `assunzioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT per la tabella `farmaci`
--
ALTER TABLE `farmaci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT per la tabella `info_pazienti`
--
ALTER TABLE `info_pazienti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `messaggi`
--
ALTER TABLE `messaggi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT per la tabella `modifiche`
--
ALTER TABLE `modifiche`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `patologie`
--
ALTER TABLE `patologie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `rilevazioni`
--
ALTER TABLE `rilevazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT per la tabella `sintomi`
--
ALTER TABLE `sintomi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT per la tabella `sintomi_concomitanti`
--
ALTER TABLE `sintomi_concomitanti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `studi`
--
ALTER TABLE `studi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `terapie`
--
ALTER TABLE `terapie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT per la tabella `terapie_concomitanti`
--
ALTER TABLE `terapie_concomitanti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `terapie_generiche`
--
ALTER TABLE `terapie_generiche`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `accessi`
--
ALTER TABLE `accessi`
  ADD CONSTRAINT `accessi_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `assegnazioni_medici`
--
ALTER TABLE `assegnazioni_medici`
  ADD CONSTRAINT `assegnazioni_medici_ibfk_1` FOREIGN KEY (`id_medico`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `assegnazioni_medici_ibfk_2` FOREIGN KEY (`id_paziente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `assegnazioni_studi`
--
ALTER TABLE `assegnazioni_studi`
  ADD CONSTRAINT `assegnazioni_studi_ibfk_1` FOREIGN KEY (`id_studio`) REFERENCES `studi` (`id`),
  ADD CONSTRAINT `assegnazioni_studi_ibfk_2` FOREIGN KEY (`id_medico`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `associazioni_farmaci`
--
ALTER TABLE `associazioni_farmaci`
  ADD CONSTRAINT `associazioni_farmaci_ibfk_1` FOREIGN KEY (`id_terapia`) REFERENCES `terapie` (`id`),
  ADD CONSTRAINT `associazioni_farmaci_ibfk_2` FOREIGN KEY (`id_farmaco`) REFERENCES `farmaci` (`id`);

--
-- Limiti per la tabella `assunzioni`
--
ALTER TABLE `assunzioni`
  ADD CONSTRAINT `fk_associazione_farmaco` FOREIGN KEY (`id_associazione_farmaco`) REFERENCES `associazioni_farmaci` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `info_pazienti`
--
ALTER TABLE `info_pazienti`
  ADD CONSTRAINT `info_pazienti_ibfk_1` FOREIGN KEY (`id_paziente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `messaggi`
--
ALTER TABLE `messaggi`
  ADD CONSTRAINT `messaggi_ibfk_1` FOREIGN KEY (`id_sender`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `messaggi_ibfk_2` FOREIGN KEY (`id_receiver`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `modifiche`
--
ALTER TABLE `modifiche`
  ADD CONSTRAINT `modifiche_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `patologie`
--
ALTER TABLE `patologie`
  ADD CONSTRAINT `fk_patologie_paziente` FOREIGN KEY (`paziente_id`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `rilevazioni`
--
ALTER TABLE `rilevazioni`
  ADD CONSTRAINT `rilevazioni_ibfk_1` FOREIGN KEY (`id_paziente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `sintomi`
--
ALTER TABLE `sintomi`
  ADD CONSTRAINT `sintomi_ibfk_1` FOREIGN KEY (`id_paziente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `sintomi_concomitanti`
--
ALTER TABLE `sintomi_concomitanti`
  ADD CONSTRAINT `sintomi_concomitanti_ibfk_1` FOREIGN KEY (`paziente_id`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `terapie`
--
ALTER TABLE `terapie`
  ADD CONSTRAINT `terapie_ibfk_1` FOREIGN KEY (`id_paziente`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `terapie_concomitanti`
--
ALTER TABLE `terapie_concomitanti`
  ADD CONSTRAINT `terapie_concomitanti_ibfk_1` FOREIGN KEY (`paziente_id`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `terapie_generiche`
--
ALTER TABLE `terapie_generiche`
  ADD CONSTRAINT `terapie_generiche_ibfk_1` FOREIGN KEY (`id_paziente`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `terapie_generiche_ibfk_2` FOREIGN KEY (`id_farmaco`) REFERENCES `farmaci` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
