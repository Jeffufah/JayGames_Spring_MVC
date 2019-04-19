-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2019 at 09:21 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jaygame2_dtccjaygames`
--

-- --------------------------------------------------------

--
-- Table structure for table `banned_accounts`
--

CREATE TABLE `banned_accounts` (
  `Ban_id` int(11) NOT NULL,
  `BA_user_id` int(11) NOT NULL,
  `BA_ban_type` int(1) NOT NULL,
  `Ban_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banned_accounts`
--

INSERT INTO `banned_accounts` (`Ban_id`, `BA_user_id`, `BA_ban_type`, `Ban_date`) VALUES
(1, 1, 2, '2018-12-18 01:36:46');

-- --------------------------------------------------------

--
-- Table structure for table `ban_types`
--

CREATE TABLE `ban_types` (
  `Type_id` int(11) NOT NULL,
  `Type_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ban_types`
--

INSERT INTO `ban_types` (`Type_id`, `Type_description`) VALUES
(1, 'One Week Ban.'),
(2, 'One Month Ban.');

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE `devices` (
  `Device_id` int(11) NOT NULL,
  `Device_name` varchar(25) NOT NULL,
  `Device_hardware` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`Device_id`, `Device_name`, `Device_hardware`) VALUES
(1, 'Web', 'Web Stuff'),
(2, 'Game Client', 'Game Stuff');

-- --------------------------------------------------------

--
-- Table structure for table `discounts`
--

CREATE TABLE `discounts` (
  `Discount_id` int(11) NOT NULL,
  `Discount_percent` int(3) NOT NULL,
  `Discount_start_date` datetime(6) NOT NULL,
  `Discount_end_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discounts`
--

INSERT INTO `discounts` (`Discount_id`, `Discount_percent`, `Discount_start_date`, `Discount_end_date`) VALUES
(1, 10, '2018-12-17 12:00:00.000000', '2018-12-31 12:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `forums`
--

CREATE TABLE `forums` (
  `Forum_id` int(11) NOT NULL,
  `Forum_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forums`
--

INSERT INTO `forums` (`Forum_id`, `Forum_type`) VALUES
(1, 'Developer Suggestions');

-- --------------------------------------------------------

--
-- Table structure for table `forum_posts`
--

CREATE TABLE `forum_posts` (
  `post_id` int(11) NOT NULL,
  `FP_thread_id` int(11) NOT NULL,
  `FP_user_id` int(11) NOT NULL,
  `post_contents` longtext NOT NULL,
  `post_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forum_posts`
--

INSERT INTO `forum_posts` (`post_id`, `FP_thread_id`, `FP_user_id`, `post_contents`, `post_date`) VALUES
(1, 1, 1, 'It would be nice to play something other than connect four for a change lol. js', '2018-12-20 00:28:31');

-- --------------------------------------------------------

--
-- Table structure for table `forum_threads`
--

CREATE TABLE `forum_threads` (
  `Thread_id` int(11) NOT NULL,
  `FT_forum_id` int(11) NOT NULL,
  `Thread_title` varchar(250) NOT NULL,
  `Thread_post_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forum_threads`
--

INSERT INTO `forum_threads` (`Thread_id`, `FT_forum_id`, `Thread_title`, `Thread_post_date`) VALUES
(1, 1, 'When will there be more games than connect 4?', '2018-12-19 23:54:12');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `FR_user_id` int(11) NOT NULL,
  `FR_friend_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `friend_events`
--

CREATE TABLE `friend_events` (
  `Friend_event_id` int(11) NOT NULL,
  `FE_user_id` int(11) NOT NULL,
  `FR_friend_id` int(11) NOT NULL,
  `Friend_status` tinyint(4) NOT NULL,
  `Friend_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `game_events`
--

CREATE TABLE `game_events` (
  `Game_event_id` int(11) NOT NULL,
  `GE_game_id` int(11) NOT NULL,
  `GE_user_id` int(11) NOT NULL,
  `Start_date` datetime(6) NOT NULL,
  `End_date` datetime(6) NOT NULL,
  `Game_Rating` decimal(2,1) NOT NULL,
  `Game_review` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game_events`
--

INSERT INTO `game_events` (`Game_event_id`, `GE_game_id`, `GE_user_id`, `Start_date`, `End_date`, `Game_Rating`, `Game_review`) VALUES
(1, 1, 1, '2018-12-17 12:12:06.000000', '2018-12-17 12:29:45.204017', '4.5', 'This game was pretty fun but it could use a better gui and a quit button would be helpful.');

-- --------------------------------------------------------

--
-- Table structure for table `game_info`
--

CREATE TABLE `game_info` (
  `Game_id` int(11) NOT NULL,
  `Game_title` varchar(50) NOT NULL,
  `Game_download_link` varchar(50) NOT NULL,
  `Game_summary` varchar(300) NOT NULL,
  `Game_details` text NOT NULL,
  `Game_specs` text NOT NULL,
  `Game_price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game_info`
--

INSERT INTO `game_info` (`Game_id`, `Game_title`, `Game_download_link`, `Game_summary`, `Game_details`, `Game_specs`, `Game_price`) VALUES
(1, 'Connect Four', '/public_html/games/PlotFour.zip', 'Line four colored chips in a row to win.', 'Play on a 7 x 6 grid by clicking an empty circle in a column to place your color chip. Line four colored chips in a row vertically, horizontally, or diagonally to win.', 'TBA', 0);

-- --------------------------------------------------------

--
-- Table structure for table `game_servers`
--

CREATE TABLE `game_servers` (
  `GS_game_id` int(11) NOT NULL,
  `GS_user_id` int(11) NOT NULL,
  `ip_address` varchar(15) NOT NULL,
  `port` int(5) NOT NULL,
  `access_level` int(1) NOT NULL,
  `heartbeat_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `log_events`
--

CREATE TABLE `log_events` (
  `Log_event_id` int(11) NOT NULL,
  `LE_user_id` int(11) NOT NULL,
  `LE_device_id` int(11) NOT NULL,
  `Log_type` tinyint(1) NOT NULL,
  `Log_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_events`
--

INSERT INTO `log_events` (`Log_event_id`, `LE_user_id`, `LE_device_id`, `Log_type`, `Log_date`) VALUES
(1, 1, 2, 1, '2018-12-18 01:34:24'),
(2, 1, 1, 1, '2019-01-30 21:45:30'),
(3, 1, 1, 1, '2019-01-30 21:51:49'),
(4, 1, 1, 1, '2019-01-30 21:55:55'),
(5, 1, 1, 1, '2019-01-30 22:04:57'),
(6, 1, 1, 0, '2019-01-30 22:06:44'),
(7, 1, 1, 1, '2019-01-30 23:17:24'),
(8, 1, 1, 0, '2019-01-30 23:19:13'),
(9, 1, 1, 1, '2019-01-30 23:19:42'),
(10, 1, 1, 0, '2019-01-30 23:21:03'),
(11, 1, 1, 1, '2019-01-30 23:21:22'),
(12, 1, 1, 0, '2019-01-30 23:23:13'),
(13, 1, 1, 1, '2019-01-31 00:16:59'),
(14, 1, 1, 1, '2019-01-31 00:18:41'),
(15, 1, 1, 0, '2019-01-31 00:19:46'),
(16, 1, 1, 1, '2019-01-31 00:57:12'),
(17, 1, 1, 1, '2019-01-31 00:57:35'),
(18, 1, 1, 0, '2019-01-31 00:59:27'),
(19, 4, 1, 1, '2019-01-31 06:00:21'),
(20, 4, 1, 0, '2019-01-31 06:01:32'),
(21, 4, 1, 1, '2019-01-31 06:16:16'),
(22, 4, 1, 0, '2019-01-31 06:17:40'),
(23, 1, 1, 1, '2019-02-02 21:40:43'),
(24, 1, 1, 0, '2019-02-02 21:57:19');

-- --------------------------------------------------------

--
-- Table structure for table `privacy`
--

CREATE TABLE `privacy` (
  `Privacy_id` int(11) NOT NULL,
  `Privacy_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `privacy`
--

INSERT INTO `privacy` (`Privacy_id`, `Privacy_description`) VALUES
(1, 'Public account details are invisible to other users.'),
(2, 'Public details are available to other accounts.');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `Transaction_id` int(11) NOT NULL,
  `TR_user_id` int(11) NOT NULL,
  `TR_game_id` int(11) NOT NULL,
  `Card_holder` varchar(30) NOT NULL,
  `Card_number` varchar(30) NOT NULL,
  `Card_expiration` date NOT NULL,
  `Card_cvs` varchar(10) NOT NULL,
  `TR_discount_id` int(11) NOT NULL,
  `Transaction_amount` float(4,2) NOT NULL,
  `Transaction_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`Transaction_id`, `TR_user_id`, `TR_game_id`, `Card_holder`, `Card_number`, `Card_expiration`, `Card_cvs`, `TR_discount_id`, `Transaction_amount`, `Transaction_date`) VALUES
(1, 1, 1, 'Jeffrey S McMullen II', '4008123456789123', '2021-10-31', '999', 1, 8.99, '2018-12-18 02:25:40');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User_id` int(11) NOT NULL,
  `Username` varchar(25) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `USR_privacy_id` int(1) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `Country` varchar(30) NOT NULL,
  `State` varchar(20) NOT NULL,
  `Language` varchar(20) NOT NULL,
  `Creation_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_id`, `Username`, `Email`, `Password`, `USR_privacy_id`, `Gender`, `Country`, `State`, `Language`, `Creation_Date`) VALUES
(1, 'Jeffro Bodeen', 'jeffrey.mcmullen@yahoo.com', 'jeffufah', 1, 'Male', 'United States', 'Delaware', 'English', '2018-12-18 01:32:31'),
(2, 'jeffufah', 'jeffrey.mcmullen@yahoo.com', 'jeffro bodeen', 1, 'male', 'US', 'GA', 'english', '2019-01-31 00:29:48'),
(3, 'asdfasdf', 'asdfasdf', 'asdfasdf', 1, 'asdfasdf', 'asdfasdf', 'sdd', 'sdfsdf', '2019-01-31 00:56:26'),
(4, 'skitty105', 'skitty110@gmail.com', 'rabbit105', 2, 'Skitty', 'Nigeria', 'Kogi', 'Fula', '2019-01-31 05:59:59');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banned_accounts`
--
ALTER TABLE `banned_accounts`
  ADD PRIMARY KEY (`Ban_id`),
  ADD KEY `BA_ban_type` (`BA_ban_type`),
  ADD KEY `BA_user_id` (`BA_user_id`);

--
-- Indexes for table `ban_types`
--
ALTER TABLE `ban_types`
  ADD PRIMARY KEY (`Type_id`);

--
-- Indexes for table `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`Device_id`);

--
-- Indexes for table `discounts`
--
ALTER TABLE `discounts`
  ADD PRIMARY KEY (`Discount_id`);

--
-- Indexes for table `forums`
--
ALTER TABLE `forums`
  ADD PRIMARY KEY (`Forum_id`);

--
-- Indexes for table `forum_posts`
--
ALTER TABLE `forum_posts`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `FP_thread_id` (`FP_thread_id`),
  ADD KEY `FP_user_id` (`FP_user_id`);

--
-- Indexes for table `forum_threads`
--
ALTER TABLE `forum_threads`
  ADD PRIMARY KEY (`Thread_id`),
  ADD KEY `FT_forum_id` (`FT_forum_id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD KEY `FR_user_id` (`FR_user_id`),
  ADD KEY `FR_friend_id` (`FR_friend_id`);

--
-- Indexes for table `friend_events`
--
ALTER TABLE `friend_events`
  ADD PRIMARY KEY (`Friend_event_id`),
  ADD KEY `FE_user_id` (`FE_user_id`),
  ADD KEY `FR_friend_id` (`FR_friend_id`);

--
-- Indexes for table `game_events`
--
ALTER TABLE `game_events`
  ADD PRIMARY KEY (`Game_event_id`),
  ADD KEY `GE_game_id` (`GE_game_id`),
  ADD KEY `GE_user_id` (`GE_user_id`);

--
-- Indexes for table `game_info`
--
ALTER TABLE `game_info`
  ADD PRIMARY KEY (`Game_id`);

--
-- Indexes for table `game_servers`
--
ALTER TABLE `game_servers`
  ADD KEY `GS_game_id` (`GS_game_id`),
  ADD KEY `GS_user_id` (`GS_user_id`);

--
-- Indexes for table `log_events`
--
ALTER TABLE `log_events`
  ADD PRIMARY KEY (`Log_event_id`),
  ADD KEY `LE_user_id` (`LE_user_id`),
  ADD KEY `LE_device_id` (`LE_device_id`);

--
-- Indexes for table `privacy`
--
ALTER TABLE `privacy`
  ADD PRIMARY KEY (`Privacy_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`Transaction_id`),
  ADD KEY `TR_user_id` (`TR_user_id`),
  ADD KEY `TR_game_id` (`TR_game_id`),
  ADD KEY `TR_discount_id` (`TR_discount_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`User_id`),
  ADD KEY `USR_privacy_id` (`USR_privacy_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banned_accounts`
--
ALTER TABLE `banned_accounts`
  MODIFY `Ban_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ban_types`
--
ALTER TABLE `ban_types`
  MODIFY `Type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `devices`
--
ALTER TABLE `devices`
  MODIFY `Device_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `discounts`
--
ALTER TABLE `discounts`
  MODIFY `Discount_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `forums`
--
ALTER TABLE `forums`
  MODIFY `Forum_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `forum_posts`
--
ALTER TABLE `forum_posts`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `forum_threads`
--
ALTER TABLE `forum_threads`
  MODIFY `Thread_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `friend_events`
--
ALTER TABLE `friend_events`
  MODIFY `Friend_event_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `game_events`
--
ALTER TABLE `game_events`
  MODIFY `Game_event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `game_info`
--
ALTER TABLE `game_info`
  MODIFY `Game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `log_events`
--
ALTER TABLE `log_events`
  MODIFY `Log_event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `privacy`
--
ALTER TABLE `privacy`
  MODIFY `Privacy_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `Transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `User_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `banned_accounts`
--
ALTER TABLE `banned_accounts`
  ADD CONSTRAINT `banned_accounts_ibfk_1` FOREIGN KEY (`BA_ban_type`) REFERENCES `ban_types` (`Type_id`),
  ADD CONSTRAINT `banned_accounts_ibfk_2` FOREIGN KEY (`BA_user_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `banned_accounts_ibfk_3` FOREIGN KEY (`BA_user_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `forum_posts`
--
ALTER TABLE `forum_posts`
  ADD CONSTRAINT `forum_posts_ibfk_1` FOREIGN KEY (`FP_thread_id`) REFERENCES `forum_threads` (`Thread_id`),
  ADD CONSTRAINT `forum_posts_ibfk_2` FOREIGN KEY (`FP_user_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `forum_threads`
--
ALTER TABLE `forum_threads`
  ADD CONSTRAINT `forum_threads_ibfk_1` FOREIGN KEY (`FT_forum_id`) REFERENCES `forums` (`Forum_id`);

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`FR_user_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`FR_friend_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `friend_events`
--
ALTER TABLE `friend_events`
  ADD CONSTRAINT `friend_events_ibfk_1` FOREIGN KEY (`FE_user_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `friend_events_ibfk_2` FOREIGN KEY (`FR_friend_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `game_events`
--
ALTER TABLE `game_events`
  ADD CONSTRAINT `game_events_ibfk_1` FOREIGN KEY (`GE_game_id`) REFERENCES `game_info` (`Game_id`),
  ADD CONSTRAINT `game_events_ibfk_2` FOREIGN KEY (`GE_user_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `game_servers`
--
ALTER TABLE `game_servers`
  ADD CONSTRAINT `game_servers_ibfk_1` FOREIGN KEY (`GS_game_id`) REFERENCES `game_info` (`Game_id`),
  ADD CONSTRAINT `game_servers_ibfk_2` FOREIGN KEY (`GS_user_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `log_events`
--
ALTER TABLE `log_events`
  ADD CONSTRAINT `log_events_ibfk_1` FOREIGN KEY (`LE_user_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `log_events_ibfk_2` FOREIGN KEY (`LE_device_id`) REFERENCES `devices` (`Device_id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`TR_user_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`TR_game_id`) REFERENCES `game_info` (`Game_id`),
  ADD CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`TR_discount_id`) REFERENCES `discounts` (`Discount_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`USR_privacy_id`) REFERENCES `privacy` (`Privacy_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
