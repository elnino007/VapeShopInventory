-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.17-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for vapeshop
CREATE DATABASE IF NOT EXISTS `vapeshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `vapeshop`;

-- Dumping structure for table vapeshop.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact_number` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.customer: ~0 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `first_name`, `middle_name`, `last_name`, `age`, `gender`, `address`, `contact_number`) VALUES
	(1, 'ato', 'ramos', 'malabiga', 22, b'0', 'marikina', '9330405');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table vapeshop.customer_points
CREATE TABLE IF NOT EXISTS `customer_points` (
  `id` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.customer_points: ~0 rows (approximately)
/*!40000 ALTER TABLE `customer_points` DISABLE KEYS */;
INSERT INTO `customer_points` (`id`, `points`) VALUES
	(1, 50);
/*!40000 ALTER TABLE `customer_points` ENABLE KEYS */;

-- Dumping structure for table vapeshop.customer_rfid
CREATE TABLE IF NOT EXISTS `customer_rfid` (
  `id` int(11) DEFAULT NULL,
  `rfid_number` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.customer_rfid: ~0 rows (approximately)
/*!40000 ALTER TABLE `customer_rfid` DISABLE KEYS */;
INSERT INTO `customer_rfid` (`id`, `rfid_number`) VALUES
	(1, '0707343604');
/*!40000 ALTER TABLE `customer_rfid` ENABLE KEYS */;

-- Dumping structure for table vapeshop.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) DEFAULT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_category_name` varchar(50) DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `purchase_price` decimal(20,2) DEFAULT NULL,
  `selling_price` decimal(20,2) DEFAULT NULL,
  `points` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.product: ~0 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `barcode`, `product_name`, `product_category_name`, `description`, `purchase_price`, `selling_price`, `points`) VALUES
	(1, 'BAR0000001', 'Muji', 'Cotton', 'This cotton is good for vape.', 5.00, 10.00, 1),
	(2, 'BAR0000002', 'Fruta Kool Grapes', 'Juice', 'This is made by Fruta Kool and the flavor is Grapes', 150.00, 200.00, 5),
	(3, 'BAR0000003', 'Clapton 24g', 'Wire', 'This wire is for variable', 33.00, 55.00, 5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table vapeshop.product_category
CREATE TABLE IF NOT EXISTS `product_category` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.product_category: ~4 rows (approximately)
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` (`id`, `name`) VALUES
	(1, 'Cotton'),
	(2, 'Mod'),
	(3, 'Wire'),
	(4, 'Juice');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;

-- Dumping structure for table vapeshop.product_stock
CREATE TABLE IF NOT EXISTS `product_stock` (
  `id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.product_stock: ~2 rows (approximately)
/*!40000 ALTER TABLE `product_stock` DISABLE KEYS */;
INSERT INTO `product_stock` (`id`, `quantity`) VALUES
	(1, 0),
	(2, 0),
	(3, 0);
/*!40000 ALTER TABLE `product_stock` ENABLE KEYS */;

-- Dumping structure for table vapeshop.rewards
CREATE TABLE IF NOT EXISTS `rewards` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `stocks` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.rewards: ~0 rows (approximately)
/*!40000 ALTER TABLE `rewards` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewards` ENABLE KEYS */;

-- Dumping structure for table vapeshop.transaction_tbl
CREATE TABLE IF NOT EXISTS `transaction_tbl` (
  `id` int(11) DEFAULT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `date_created` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.transaction_tbl: ~4 rows (approximately)
/*!40000 ALTER TABLE `transaction_tbl` DISABLE KEYS */;
INSERT INTO `transaction_tbl` (`id`, `barcode`, `quantity`, `price`, `total_amount`, `username`, `points`, `date_created`) VALUES
	(1, 'BAR0000001', 2, 10.00, 20.00, 'cashier001', 2, '2021-07-31'),
	(2, 'BAR0000001', 1, 10.00, 10.00, 'cashier001', 1, '2021-08-02'),
	(3, 'BAR0000001', 20, 10.00, 200.00, 'cashier001', 480, '2021-08-09'),
	(4, 'BAR0000001', 10, 10.00, 100.00, 'cashier002', 1, '2021-08-09');
/*!40000 ALTER TABLE `transaction_tbl` ENABLE KEYS */;

-- Dumping structure for table vapeshop.user_management
CREATE TABLE IF NOT EXISTS `user_management` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table vapeshop.user_management: ~5 rows (approximately)
/*!40000 ALTER TABLE `user_management` DISABLE KEYS */;
INSERT INTO `user_management` (`username`, `password`, `user_type`, `first_name`, `middle_name`, `last_name`, `birthday`, `age`, `address`, `contact`, `gender`) VALUES
	('owner', '123456', 'Owner', 'Shaitozen', 'Ramos', 'Malabiga', '1993-10-14', 28, 'marikina', '09663079553', b'1'),
	('manager001', '123456', 'Manager', 'Manager', 'Ramos', 'Malabiga', '1999-07-05', 22, 'Marikina City', '09085623663', b'0'),
	('manager002', '123456', 'Manager', 'Rain', 'Santos', 'Asuelo', '2000-07-13', 21, 'Marikina', '09228764576', b'0'),
	('cashier001', '123456', 'Cashier', 'Brad', 'Ramos', 'Magtanggol', '2000-07-06', 21, 'Marikina', '09665423563', b'0'),
	('cashier002', '123456', 'Cashier', 'Remy', 'Malabiga', 'Santos', '1999-07-14', 22, 'Antipolo', '09675235672', b'0'),
	('test', '123456', 'Cashier', 'ato', 'ramos', 'malabiga', '1998-07-06', 23, 'marikina city', '09085623663', b'0');
/*!40000 ALTER TABLE `user_management` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
