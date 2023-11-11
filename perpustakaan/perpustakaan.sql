-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2023 at 10:08 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `bukupopuler`
-- (See below for the actual view)
--
CREATE TABLE `bukupopuler` (
`kdBuku` varchar(10)
,`judulBuku` varchar(50)
,`jmlBuku` bigint(21)
);

-- --------------------------------------------------------

--
-- Table structure for table `hak_akses`
--

CREATE TABLE `hak_akses` (
  `kode` int(10) NOT NULL,
  `userHak` varchar(20) NOT NULL,
  `userNama` varchar(30) NOT NULL,
  `userId` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tipe_transaksi`
--

CREATE TABLE `tipe_transaksi` (
  `tipeId` int(1) NOT NULL,
  `kode` varchar(3) NOT NULL,
  `ket` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tm_anggota`
--

CREATE TABLE `tm_anggota` (
  `kdAnggota` varchar(10) NOT NULL,
  `nmAnggota` varchar(50) NOT NULL,
  `jnsKelamin` enum('L','P') NOT NULL,
  `almtAnggota` varchar(50) NOT NULL,
  `nmrTelp` varchar(13) NOT NULL,
  `potoAnggota` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tm_denda`
--

CREATE TABLE `tm_denda` (
  `kdDenda` varchar(10) NOT NULL,
  `kdPinjam` varchar(10) NOT NULL,
  `tglPengembalian` varchar(10) NOT NULL,
  `terlambat` varchar(20) NOT NULL,
  `jmlDenda` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tm_peminjaman`
--

CREATE TABLE `tm_peminjaman` (
  `kdPinjam` varchar(10) NOT NULL,
  `tglPinjam` date NOT NULL,
  `kdAnggota` varchar(10) NOT NULL,
  `kdBuku` varchar(10) NOT NULL,
  `tglKembali` date NOT NULL,
  `status` varchar(20) NOT NULL,
  `userId` varchar(20) NOT NULL,
  `jmlBuku` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Triggers `tm_peminjaman`
--
DELIMITER $$
CREATE TRIGGER `kembaliBuku` AFTER UPDATE ON `tm_peminjaman` FOR EACH ROW BEGIN
 INSERT INTO tr_buku SET
 kdBuku = NEW.kdBuku
 , jmlBuku=New.jmlBuku
 ON DUPLICATE KEY UPDATE jmlBuku=jmlBuku+New.jmlBuku;
 END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `pinjamBuku` AFTER INSERT ON `tm_peminjaman` FOR EACH ROW BEGIN
 UPDATE tr_buku
 SET jmlBuku = jmlBuku - NEW.jmlBuku
 WHERE
 kdBuku = NEW.kdBuku;
 END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tm_user`
--

CREATE TABLE `tm_user` (
  `userId` varchar(10) NOT NULL,
  `userNama` varchar(50) NOT NULL,
  `userPass` varchar(32) NOT NULL,
  `userHak` varchar(10) NOT NULL,
  `userSts` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tr_buku`
--

CREATE TABLE `tr_buku` (
  `kdBuku` varchar(10) NOT NULL,
  `kdKategori` varchar(10) NOT NULL,
  `kdPenerbit` varchar(10) NOT NULL,
  `kdPengarang` varchar(10) NOT NULL,
  `judulBuku` varchar(50) NOT NULL,
  `sinopsis` varchar(255) NOT NULL,
  `jmlBuku` int(4) NOT NULL,
  `gbrBuku` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tr_kategori`
--

CREATE TABLE `tr_kategori` (
  `kdKategori` varchar(10) NOT NULL,
  `nmKategori` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tr_penerbit`
--

CREATE TABLE `tr_penerbit` (
  `kdPenerbit` varchar(10) NOT NULL,
  `nmPenerbit` varchar(50) NOT NULL,
  `almtPenerbit` varchar(100) NOT NULL,
  `tlpPenerbit` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tr_pengarang`
--

CREATE TABLE `tr_pengarang` (
  `kdPengarang` varchar(10) NOT NULL,
  `nmPengarang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_peminjaman`
-- (See below for the actual view)
--
CREATE TABLE `view_peminjaman` (
`kdPinjam` varchar(10)
,`tglPinjam` date
,`tglKembali` date
,`nmAnggota` varchar(50)
,`judulBuku` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_pengembalian`
-- (See below for the actual view)
--
CREATE TABLE `view_pengembalian` (
`kdPinjam` varchar(10)
,`tglPinjam` date
,`tglKembali` date
,`nmAnggota` varchar(50)
,`judulBuku` varchar(50)
);

-- --------------------------------------------------------

--
-- Structure for view `bukupopuler`
--
DROP TABLE IF EXISTS `bukupopuler`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bukupopuler`  AS   (select `b`.`kdBuku` AS `kdBuku`,`b`.`judulBuku` AS `judulBuku`,count(`a`.`kdBuku`) AS `jmlBuku` from (`tm_peminjaman` `a` left join `tr_buku` `b` on(`b`.`kdBuku` = `a`.`kdBuku`)))  ;

-- --------------------------------------------------------

--
-- Structure for view `view_peminjaman`
--
DROP TABLE IF EXISTS `view_peminjaman`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_peminjaman`  AS   (select `tm_peminjaman`.`kdPinjam` AS `kdPinjam`,`tm_peminjaman`.`tglPinjam` AS `tglPinjam`,`tm_peminjaman`.`tglKembali` AS `tglKembali`,`tm_anggota`.`nmAnggota` AS `nmAnggota`,`tr_buku`.`judulBuku` AS `judulBuku` from ((`tr_buku` join `tm_anggota`) join `tm_peminjaman`) where `tm_peminjaman`.`kdBuku` = `tr_buku`.`kdBuku` and `tm_peminjaman`.`kdAnggota` = `tm_anggota`.`kdAnggota` and `tm_peminjaman`.`status` = 1)  ;

-- --------------------------------------------------------

--
-- Structure for view `view_pengembalian`
--
DROP TABLE IF EXISTS `view_pengembalian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_pengembalian`  AS   (select `tm_peminjaman`.`kdPinjam` AS `kdPinjam`,`tm_peminjaman`.`tglPinjam` AS `tglPinjam`,`tm_peminjaman`.`tglKembali` AS `tglKembali`,`tm_anggota`.`nmAnggota` AS `nmAnggota`,`tr_buku`.`judulBuku` AS `judulBuku` from ((`tr_buku` join `tm_anggota`) join `tm_peminjaman`) where `tm_peminjaman`.`kdBuku` = `tr_buku`.`kdBuku` and `tm_peminjaman`.`kdAnggota` = `tm_anggota`.`kdAnggota` and `tm_peminjaman`.`status` = 2)  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hak_akses`
--
ALTER TABLE `hak_akses`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `tipe_transaksi`
--
ALTER TABLE `tipe_transaksi`
  ADD PRIMARY KEY (`tipeId`);

--
-- Indexes for table `tm_anggota`
--
ALTER TABLE `tm_anggota`
  ADD PRIMARY KEY (`kdAnggota`);

--
-- Indexes for table `tm_denda`
--
ALTER TABLE `tm_denda`
  ADD PRIMARY KEY (`kdDenda`);

--
-- Indexes for table `tm_peminjaman`
--
ALTER TABLE `tm_peminjaman`
  ADD PRIMARY KEY (`kdPinjam`);

--
-- Indexes for table `tm_user`
--
ALTER TABLE `tm_user`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `tr_buku`
--
ALTER TABLE `tr_buku`
  ADD PRIMARY KEY (`kdBuku`);

--
-- Indexes for table `tr_kategori`
--
ALTER TABLE `tr_kategori`
  ADD PRIMARY KEY (`kdKategori`);

--
-- Indexes for table `tr_penerbit`
--
ALTER TABLE `tr_penerbit`
  ADD PRIMARY KEY (`kdPenerbit`);

--
-- Indexes for table `tr_pengarang`
--
ALTER TABLE `tr_pengarang`
  ADD PRIMARY KEY (`kdPengarang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tipe_transaksi`
--
ALTER TABLE `tipe_transaksi`
  MODIFY `tipeId` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
