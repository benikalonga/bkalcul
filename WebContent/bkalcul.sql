-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 01, 2022 at 04:36 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bkalcul`
--

-- --------------------------------------------------------

--
-- Table structure for table `records`
--

CREATE TABLE `records` (
  `id` int(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `calcRequest` text NOT NULL,
  `answer` text NOT NULL,
  `calcTime` int(20) NOT NULL,
  `dateInserted` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `records`
--

INSERT INTO `records` (`id`, `username`, `calcRequest`, `answer`, `calcTime`, `dateInserted`) VALUES
(2, 'admin', '122^5', '27027081632', 318000, '2022-07-30 14:38:12'),
(3, 'admin', '122/5', '24.4', 246800, '2022-07-30 14:38:53'),
(4, 'Me', '23323232', '23323232', 293100, '2022-07-30 14:57:07'),
(5, 'Me', '233+223', '456', 4389100, '2022-07-30 14:57:15'),
(6, 'Me', '12+776^2', '602188', 725000, '2022-07-30 19:05:11'),
(7, 'admin', '1920.00+12.00', '1932', 17692800, '2022-08-01 12:10:02'),
(8, 'admin', '1920.00+12.00', '1932', 205100, '2022-08-01 12:10:04'),
(9, 'admin', '1920.00+12.00', '1932', 166900, '2022-08-01 12:10:04'),
(10, 'admin', '1.00+2.00', '3', 336800, '2022-08-01 12:24:14'),
(11, 'admin', '21^2', '441', 387500, '2022-08-01 12:29:24'),
(12, 'admin', '55/4', '13.75', 167200, '2022-08-01 12:30:02'),
(13, 'admin', '55/4', '13.75', 186000, '2022-08-01 12:32:27'),
(14, 'admin', '55/4', '13.75', 141400, '2022-08-01 12:32:28'),
(15, 'admin', '55/4', '13.75', 135600, '2022-08-01 12:32:29'),
(16, 'admin', '55/4', '13.75', 152300, '2022-08-01 12:32:29'),
(17, 'admin', '55/4', '13.75', 128500, '2022-08-01 12:32:29'),
(18, 'admin', '55/12', '4.583333333333333', 928700, '2022-08-01 12:34:30'),
(19, 'admin', '55/12', '4.583333333333333', 135300, '2022-08-01 12:34:32'),
(20, 'admin', '55/12', '4.583333333333333', 358200, '2022-08-01 12:34:33'),
(21, 'admin', '1221', '1221', 307900, '2022-08-01 12:38:30'),
(22, 'admin', 'sasas', 'sasas', 38131100, '2022-08-01 12:57:58'),
(23, 'admin', '12^2(2)', '288', 29487600, '2022-08-01 12:58:32'),
(24, 'admin', '12+124', '136', 152902500, '2022-08-01 16:26:41'),
(25, 'admin', '12+124', '136', 697700, '2022-08-01 16:28:31');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `isAdmin`) VALUES
('admin', 'admin', 1),
('benikalonga', 'bkalcul', 0),
('benikalongadmin', 'bkalcul', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `records`
--
ALTER TABLE `records`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
