-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 06, 2022 lúc 04:48 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ecobike`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bike`
--

CREATE TABLE `bike` (
  `bikeId` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `value` int(11) NOT NULL,
  `weight` float NOT NULL,
  `bikeCode` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `stationId` int(11) NOT NULL,
  `isInUse` tinyint(1) NOT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `producer` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateSX` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bike`
--

INSERT INTO `bike` (`bikeId`, `name`, `value`, `weight`, `bikeCode`, `stationId`, `isInUse`, `type`, `producer`, `dateSX`) VALUES
(1, 'bike 1', 10, 10, 'b1', 6, 0, 'b', NULL, NULL),
(2, 'ebike 1', 10, 10, 'eb1', 5, 0, 'eb', NULL, NULL),
(3, 'twin bike 1', 10, 10, 'tb1', 3, 0, 'tb', NULL, NULL),
(4, 'bike 2', 10, 10, 'b2', 5, 0, 'b', NULL, NULL),
(5, 'bike 3', 10, 10, 'b3', 3, 0, 'b', 'yamaha', '11/11/2020'),
(6, 'ebike 2', 10, 10, 'eb2', 2, 0, 'eb', NULL, NULL),
(7, 'bike 1', 10, 10, 'b6', 4, 0, 'b', NULL, NULL),
(8, 'xe đạp', 10000, 10, 'b123', 2, 0, 'b', 'yamaha', '11/11/2021'),
(9, 'tên xe', 10000, 10, 'eb123', 1, 0, 'eb', 'honda', '11/11/2020');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `credit_card`
--

CREATE TABLE `credit_card` (
  `creditCardId` int(11) NOT NULL,
  `cardCode` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `owner` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `cvvCode` int(11) NOT NULL,
  `dateExpired` varchar(128) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment_transaction`
--

CREATE TABLE `payment_transaction` (
  `id` int(11) NOT NULL,
  `errorCode` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `transactionId` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `transactionContent` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  `createdAt` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `cardCode` varchar(128) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `payment_transaction`
--

INSERT INTO `payment_transaction` (`id`, `errorCode`, `transactionId`, `transactionContent`, `amount`, `createdAt`, `cardCode`) VALUES
(4, '00', '61cb08162e001a0016d3e051', 'rent bike', 1, '2021-12-28 19:50:27', 'kscq2_group20_2021'),
(5, '00', '61d3f98caeae790016eb8069', 'rent bike', 700000, '2022-01-04 14:38:47', 'kscq2_group20_2021'),
(6, '00', '61d40037aeae790016eb806e', 'rent bike', 550000, '2022-01-04 15:07:15', 'kscq2_group20_2021'),
(7, '00', '61d4004baeae790016eb8070', 'rent bike', 550000, '2022-01-04 15:07:37', 'kscq2_group20_2021'),
(8, '00', '61d4012baeae790016eb8073', 'rent bike', 400000, '2022-01-04 15:11:21', 'kscq2_group20_2021'),
(9, '00', '61d40139aeae790016eb8075', 'rent bike', 400000, '2022-01-04 15:11:35', 'kscq2_group20_2021'),
(10, '00', '61d40854aeae790016eb8087', 'rent bike', 400000, '2022-01-04 15:41:53', 'kscq2_group20_2021'),
(11, '00', '61d4085eaeae790016eb8088', 'rent bike', 700000, '2022-01-04 15:42:04', 'kscq2_group20_2021'),
(12, '00', '61d40b93aeae790016eb8094', 'rent bike', 700000, '2022-01-04 15:55:44', 'kscq2_group20_2021'),
(13, '00', '61d40ba4aeae790016eb8095', 'rent bike', 700000, '2022-01-04 15:56:02', 'kscq2_group20_2021'),
(14, '00', '61d41345aeae790016eb80a9', 'rent bike', 700000, '2022-01-04 16:28:33', 'kscq2_group20_2021'),
(15, '00', '61d41362aeae790016eb80aa', 'rent bike', 550000, '2022-01-04 16:29:04', 'kscq2_group20_2021'),
(16, '00', '61d41f03aeae790016eb80c0', 'rent bike', 550000, '2022-01-04 17:18:40', 'kscq2_group20_2021'),
(17, '00', '61d41f1aaeae790016eb80c1', 'rent bike', 700000, '2022-01-04 17:19:04', 'kscq2_group20_2021'),
(18, '00', '61d44dbfaeae790016eb8107', 'rent bike', 700000, '2022-01-04 20:38:03', 'kscq2_group20_2021'),
(19, '00', '61d44dd5aeae790016eb8108', 'rent bike', 400000, '2022-01-04 20:38:28', 'kscq2_group20_2021'),
(20, '00', '61d45bb4aeae790016eb812e', 'rent bike', 400000, '2022-01-04 21:37:37', 'kscq2_group20_2021'),
(21, '00', '61d45bcdaeae790016eb8130', 'rent bike', 700000, '2022-01-04 21:38:04', 'kscq2_group20_2021'),
(22, '00', '61d45bf6aeae790016eb8131', 'rent bike', 700000, '2022-01-04 21:38:41', 'kscq2_group20_2021'),
(23, '00', '61d45f2caeae790016eb8148', 'rent bike', 700000, '2022-01-04 21:52:27', 'kscq2_group20_2021'),
(24, '00', '61d45f78aeae790016eb814b', 'rent bike', 700000, '2022-01-04 21:53:41', 'kscq2_group20_2021'),
(25, '00', '61d46937aeae790016eb818d', 'rent bike', 400000, '2022-01-04 22:35:15', 'kscq2_group20_2021'),
(26, '00', '61d46956aeae790016eb818e', 'rent bike', 0, '2022-01-04 22:35:48', 'kscq2_group20_2021'),
(27, '00', '61d473feaeae790016eb81d7', 'rent bike', 700000, '2022-01-04 23:21:15', 'kscq2_group20_2021'),
(28, '00', '61d4759baeae790016eb81e0', 'rent bike', 0, '2022-01-04 23:28:09', 'kscq2_group20_2021'),
(29, '00', '61d475adaeae790016eb81e1', 'rent bike', 400000, '2022-01-04 23:28:28', 'kscq2_group20_2021'),
(30, '00', '61d477c1aeae790016eb81ee', 'rent bike', 0, '2022-01-04 23:37:18', 'kscq2_group20_2021'),
(31, '00', '61d47a17aeae790016eb81f7', 'rent bike', 0, '2022-01-04 23:47:17', 'kscq2_group20_2021'),
(32, '00', '61d47b24aeae790016eb81fa', 'rent bike', 400000, '2022-01-04 23:21:15', 'kscq2_group20_2021'),
(33, '00', '61d47cadaeae790016eb81fe', 'rent bike', 13000, '2022-01-04 23:58:19', 'kscq2_group20_2021'),
(34, '00', '61d51dfa9f85b10016ee72eb', 'rent bike', 400000, '2022-01-05 11:26:31', 'kscq2_group20_2021'),
(35, '00', '61d51e119f85b10016ee72ec', 'rent bike', 0, '2022-01-05 11:26:53', 'kscq2_group20_2021'),
(36, '00', '61d5204b9f85b10016ee72f1', 'rent bike', 700000, '2022-01-05 11:36:25', 'kscq2_group20_2021'),
(37, '00', '61d520a49f85b10016ee72f3', 'rent bike', 0, '2022-01-05 11:37:54', 'kscq2_group20_2021'),
(38, '00', '61d520d49f85b10016ee72f5', 'rent bike', 400000, '2022-01-05 11:38:40', 'kscq2_group20_2021'),
(39, '00', '61d5a9843b9e23001602cd9e', 'rent bike', 121000, '2022-01-05 21:21:51', 'kscq2_group20_2021'),
(40, '00', '61d5c8133b9e23001602ce25', 'rent bike', 400000, '2022-01-05 23:32:15', 'kscq2_group20_2021'),
(41, '00', '61d5c8263b9e23001602ce26', 'rent bike', 0, '2022-01-05 23:32:36', 'kscq2_group20_2021'),
(42, '00', '61d5c8353b9e23001602ce27', 'rent bike', 400000, '2022-01-05 23:32:51', 'kscq2_group20_2021'),
(43, '00', '61d5cc2b3b9e23001602ce31', 'rent bike', 10000, '2022-01-05 23:49:44', 'kscq2_group20_2021'),
(44, '00', '61d5cc443b9e23001602ce32', 'rent bike', 400000, '2022-01-05 23:50:09', 'kscq2_group20_2021'),
(45, '00', '61d5cc533b9e23001602ce33', 'rent bike', 0, '2022-01-05 23:50:25', 'kscq2_group20_2021'),
(46, '00', '61d5cc783b9e23001602ce34', 'rent bike', 400000, '2022-01-05 23:51:03', 'kscq2_group20_2021'),
(47, '00', '61d5cfce3b9e23001602ce41', 'rent bike', 400000, '2022-01-06 00:05:15', 'kscq2_group20_2021'),
(48, '00', '61d5cfe43b9e23001602ce43', 'rent bike', 0, '2022-01-06 00:05:37', 'kscq2_group20_2021'),
(49, '00', '61d64eaba43c0b001620a326', 'rent bike', 115000, '2022-01-06 09:06:30', 'kscq2_group20_2021'),
(50, '00', '61d65538a43c0b001620a32c', 'thuï¿½ xe', 400000, '2022-01-06 09:34:31', 'kscq2_group20_2021'),
(51, '00', '61d65d5ca43c0b001620a333', 'rent bike', 13000, '2022-01-06 10:09:13', 'kscq2_group20_2021'),
(52, '00', '61d65d99a43c0b001620a334', 'rent bike', 400000, '2022-01-06 10:10:14', 'kscq2_group20_2021'),
(53, '00', '61d65e8da43c0b001620a335', 'rent bike', 0, '2022-01-06 10:14:18', 'kscq2_group20_2021'),
(54, '00', '61d65e96a43c0b001620a336', 'rent bike', 400000, '2022-01-06 10:14:28', 'kscq2_group20_2021'),
(55, '00', '61d665e7a43c0b001620a349', 'rent bike', 10000, '2022-01-06 10:45:41', 'kscq2_group20_2021'),
(56, '00', '61d66612a43c0b001620a34b', 'rent bike', 400000, '2022-01-06 10:46:24', 'kscq2_group20_2021'),
(57, '00', '61d6661fa43c0b001620a34c', 'rent bike', 0, '2022-01-06 10:46:37', 'kscq2_group20_2021');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rent_bike_transaction`
--

CREATE TABLE `rent_bike_transaction` (
  `rentBikeTransactionId` int(11) NOT NULL,
  `bikeId` int(11) NOT NULL,
  `transactionId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ownerId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `rent_bike_transaction`
--

INSERT INTO `rent_bike_transaction` (`rentBikeTransactionId`, `bikeId`, `transactionId`, `ownerId`) VALUES
(21, 6, '61cb08162e001a0016d3e051', 1),
(22, 3, '61d40037aeae790016eb806e', 1),
(23, 1, '61d4012baeae790016eb8073', 1),
(24, 4, '61d40139aeae790016eb8075', 3),
(25, 2, '61d4085eaeae790016eb8088', 1),
(26, 2, '61d40ba4aeae790016eb8095', 1),
(27, 3, '61d41362aeae790016eb80aa', 1),
(28, 2, '61d41f1aaeae790016eb80c1', 1),
(29, 7, '61d44dd5aeae790016eb8108', 1),
(30, 2, '61d45bcdaeae790016eb8130', 1),
(31, 2, '61d45f2caeae790016eb8148', 1),
(32, 5, '61d46937aeae790016eb818d', 1),
(33, 2, '61d473feaeae790016eb81d7', 1),
(34, 1, '61d475adaeae790016eb81e1', 1),
(35, 1, '61d47b24aeae790016eb81fa', 1),
(36, 8, '61d51dfa9f85b10016ee72eb', 1),
(37, 2, '61d5204b9f85b10016ee72f1', 1),
(38, 1, '61d520d49f85b10016ee72f5', 1),
(39, 1, '61d5c8133b9e23001602ce25', 1),
(40, 1, '61d5c8353b9e23001602ce27', 1),
(41, 1, '61d5cc443b9e23001602ce32', 3),
(42, 4, '61d5cc783b9e23001602ce34', 3),
(43, 5, '61d5cfce3b9e23001602ce41', 1),
(44, 4, '61d65538a43c0b001620a32c', 1),
(45, 8, '61d65d99a43c0b001620a334', 1),
(46, 8, '61d65e96a43c0b001620a336', 1),
(47, 4, '61d66612a43c0b001620a34b', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `station`
--

CREATE TABLE `station` (
  `stationId` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `totalParking` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `station`
--

INSERT INTO `station` (`stationId`, `name`, `address`, `totalParking`) VALUES
(1, 'station 1', 'address 1', 15),
(2, 'station 2', 'address 2', 10),
(3, 'station 3', 'address 3', 10),
(4, 'station 4', 'address 4', 10),
(5, 'station 5', 'address 5', 10),
(6, 'station6', 'address6', 12),
(7, 'station7', 'addr7', 10),
(8, 'station8', 'addr8', 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL,
  `role` varchar(128) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`userId`, `username`, `password`, `active`, `role`) VALUES
(1, 'user', 'user', 0, 'user'),
(2, 'admin', 'admin', 0, 'admin'),
(3, 'user2', 'user2', 0, 'user');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`bikeId`),
  ADD KEY `forignkey_bike_vs_station` (`stationId`);

--
-- Chỉ mục cho bảng `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`creditCardId`);

--
-- Chỉ mục cho bảng `payment_transaction`
--
ALTER TABLE `payment_transaction`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `rent_bike_transaction`
--
ALTER TABLE `rent_bike_transaction`
  ADD PRIMARY KEY (`rentBikeTransactionId`),
  ADD KEY `forignkey_rent_bike_transaction_vs_bike` (`bikeId`);

--
-- Chỉ mục cho bảng `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`stationId`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bike`
--
ALTER TABLE `bike`
  MODIFY `bikeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `credit_card`
--
ALTER TABLE `credit_card`
  MODIFY `creditCardId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `payment_transaction`
--
ALTER TABLE `payment_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `rent_bike_transaction`
--
ALTER TABLE `rent_bike_transaction`
  MODIFY `rentBikeTransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT cho bảng `station`
--
ALTER TABLE `station`
  MODIFY `stationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bike`
--
ALTER TABLE `bike`
  ADD CONSTRAINT `forignkey_bike_vs_station` FOREIGN KEY (`stationId`) REFERENCES `station` (`stationId`);

--
-- Các ràng buộc cho bảng `rent_bike_transaction`
--
ALTER TABLE `rent_bike_transaction`
  ADD CONSTRAINT `forignkey_rent_bike_transaction_vs_bike` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`bikeId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
