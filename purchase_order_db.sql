-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2022 at 02:31 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `purchase_order_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(60) COLLATE utf8_unicode_520_ci NOT NULL,
  `item_max` int(11) NOT NULL,
  `item_current` int(11) NOT NULL,
  `item_reorder_status` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL DEFAULT 'In stock'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`item_id`, `item_name`, `item_max`, `item_current`, `item_reorder_status`) VALUES
(1, 'Standford Calculator', 50, 11, 'In stock'),
(2, 'Hertz Pencil', 500, 6, 'In stock'),
(3, 'Papermate Pen', 400, 0, 'In stock'),
(4, 'Blank Sheets (100pk)', 150, 100, 'In stock'),
(5, 'Eraser', 1000, 200, 'In stock'),
(6, 'Novel: H.King- Dreaming', 25, 5, 'In stock'),
(7, 'Paper Clip', 80, 47, 'In stock'),
(8, 'Marker', 100, 1, 'In stock'),
(9, 'Book Wrapper', 40, 35, 'In stock'),
(10, 'Marvel Puzzle- Age 6+', 60, 30, 'In stock'),
(11, 'Rubber', 100, 20, 'In stock'),
(12, 'Triple Ink Pen', 50, 40, 'In stock'),
(13, 'Geometry Set', 60, 11, 'In stock'),
(14, 'Plastic Clear Ruler', 500, 204, 'In stock'),
(15, 'Correction Pen (WhiteOut)', 55, 23, 'In stock'),
(16, 'Wooden Ruler', 36, 34, 'In stock'),
(17, 'Note Journal', 30, 11, 'In stock'),
(18, 'Sticky Pad', 100, 99, 'In stock'),
(19, 'Three Subject Notebook', 80, 49, 'In stock'),
(20, 'Crayon (20 clr pk)', 50, 21, 'In stock'),
(21, 'StoryTime for Toddlers - E. Myers', 80, 33, 'In stock'),
(22, 'The Internet for Dummies - C. Hendricks', 25, 5, 'In stock'),
(23, 'Glue Stick Medium', 120, 99, 'In stock'),
(24, 'Craft Paper - 25 pk', 50, 4, 'In stock'),
(25, 'Ledger', 150, 50, 'In stock');

-- --------------------------------------------------------

--
-- Table structure for table `purchase_order`
--

CREATE TABLE `purchase_order` (
  `po_id` int(11) NOT NULL,
  `req_id` int(11) NOT NULL,
  `approving_emp` varchar(60) COLLATE utf8_unicode_520_ci NOT NULL,
  `dateTime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `purchase_order`
--

INSERT INTO `purchase_order` (`po_id`, `req_id`, `approving_emp`, `dateTime`) VALUES
(1, 3, 'Shelly Stewart', '2022-11-06 20:26:57'),
(2, 4, 'Tiff Irvin', '2022-11-11 21:41:30'),
(3, 5, 'Tiff Irvin', '2022-11-12 01:18:59');

-- --------------------------------------------------------

--
-- Table structure for table `requisition`
--

CREATE TABLE `requisition` (
  `req_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `unit_price` double NOT NULL,
  `total_price` double NOT NULL,
  `supplier_name` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `supplier_tel` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `supplier_email` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `associated_emp` varchar(60) COLLATE utf8_unicode_520_ci NOT NULL,
  `req_status` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `requisition`
--

INSERT INTO `requisition` (`req_id`, `item_id`, `quantity`, `unit_price`, `total_price`, `supplier_name`, `supplier_tel`, `supplier_email`, `associated_emp`, `req_status`) VALUES
(1, 8, 50, 60, 3000, 'Matrix', '8763210923', 'info@matrix.com', 'Brittany Wilson', 'Deny'),
(2, 3, 200, 15, 3000, 'Papermate', '19542210986', 'order@papermate.us', 'John Doe', 'Processing'),
(3, 6, 20, 1200, 24000, 'Carlong Puublishers', '17705421244', 'sales@carlongpub.com', 'John Doe', 'Approve'),
(4, 5, 300, 20, 6000, 'Dupri', '8765435857', 'order@dupri.com', 'John Doe', 'Approve'),
(5, 17, 10, 1200, 12000, 'Indies Booking', '8667677567', 'info@indies.com', 'Brittany Wilson', 'Approve');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `f_name` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `l_name` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `role` varchar(15) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `password`, `f_name`, `l_name`, `role`) VALUES
(1, 'password', 'John', 'Doe', 'employee'),
(4, 'testing123', 'Brittany', 'Wilson', 'employee'),
(6, 'irvin23', 'Tiff', 'Irvin', 'accounts'),
(7, 'rocket45', 'Shelly', 'Stewart', 'accounts');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `purchase_order`
--
ALTER TABLE `purchase_order`
  ADD PRIMARY KEY (`po_id`),
  ADD KEY `req_id` (`req_id`);

--
-- Indexes for table `requisition`
--
ALTER TABLE `requisition`
  ADD PRIMARY KEY (`req_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `purchase_order`
--
ALTER TABLE `purchase_order`
  MODIFY `po_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `requisition`
--
ALTER TABLE `requisition`
  MODIFY `req_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchase_order`
--
ALTER TABLE `purchase_order`
  ADD CONSTRAINT `FK_PurchaseOrder_Requisition` FOREIGN KEY (`req_id`) REFERENCES `requisition` (`req_id`);

--
-- Constraints for table `requisition`
--
ALTER TABLE `requisition`
  ADD CONSTRAINT `FK_Requisition_Product` FOREIGN KEY (`item_id`) REFERENCES `product` (`item_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
