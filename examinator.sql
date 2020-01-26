-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2020 at 10:39 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `examinator`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`name`) VALUES
('Advance Java'),
('bfgfg'),
('c++'),
('dfdf'),
('dsfdsf'),
('java'),
('Python'),
('sdfd'),
('sdfdf'),
('sdfdsfd'),
('sdfsdfd'),
('ttt'),
('uuuu'),
('yyy');

-- --------------------------------------------------------

--
-- Table structure for table `examinees`
--

CREATE TABLE `examinees` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE `options` (
  `id` int(30) NOT NULL,
  `questionID` int(30) NOT NULL,
  `first` varchar(60) NOT NULL,
  `second` varchar(60) NOT NULL,
  `third` varchar(60) NOT NULL,
  `fourth` varchar(60) NOT NULL,
  `correctAnswer` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`id`, `questionID`, `first`, `second`, `third`, `fourth`, `correctAnswer`) VALUES
(1, 14, 'PL', 'Coffee', 'Actor', 'Song', 'PL'),
(2, 15, 'PL', 'Machine Learning', 'Deep Learning', 'All of above', 'PL'),
(3, 16, 'Every Class', 'String Class', 'Integer Class', 'Object Class', 'Every Class'),
(4, 17, 'Ornament', 'Legend', 'Body Part', 'Nothing', 'Legend'),
(5, 18, '10,000', '20,000', '30,000', 'Not Ethical', 'Not Ethical');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `question` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `category`, `question`) VALUES
(14, 'c++', 'What is C++ ?'),
(15, 'Python', 'What is python ?'),
(16, 'java', 'Object class belongs to ?'),
(17, 'bfgfg', 'Whats a beard ?'),
(18, 'c++', 'How much for this project ?');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `examinees`
--
ALTER TABLE `examinees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id_fk` (`questionID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_fk_name` (`category`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `examinees`
--
ALTER TABLE `examinees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE `options`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `options`
--
ALTER TABLE `options`
  ADD CONSTRAINT `question_id_fk` FOREIGN KEY (`questionID`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `category_fk_name` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
