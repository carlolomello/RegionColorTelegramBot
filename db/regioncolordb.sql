-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Dic 21, 2020 alle 18:34
-- Versione del server: 10.4.17-MariaDB
-- Versione PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `regioncolor`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `assegnazione`
--

CREATE TABLE `assegnazione` (
  `id_assegnazione` int(11) NOT NULL,
  `id_regione` int(11) NOT NULL,
  `id_mandato` int(11) NOT NULL,
  `inizio` date NOT NULL,
  `fine` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `mandato`
--

CREATE TABLE `mandato` (
  `id_mandato` int(11) NOT NULL,
  `colore` varchar(15) NOT NULL,
  `descrizione` varchar(2048) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `nota_aggiuntiva`
--

CREATE TABLE `nota_aggiuntiva` (
  `id_nota` int(11) NOT NULL,
  `id_assegnazione` int(11) NOT NULL,
  `nota` varchar(2048) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `regione`
--

CREATE TABLE `regione` (
  `id_regione` int(11) NOT NULL,
  `nome_regione` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `assegnazione`
--
ALTER TABLE `assegnazione`
  ADD PRIMARY KEY (`id_assegnazione`),
  ADD KEY `fk_mandato` (`id_mandato`),
  ADD KEY `fk_regione` (`id_regione`);

--
-- Indici per le tabelle `mandato`
--
ALTER TABLE `mandato`
  ADD PRIMARY KEY (`id_mandato`);

--
-- Indici per le tabelle `nota_aggiuntiva`
--
ALTER TABLE `nota_aggiuntiva`
  ADD PRIMARY KEY (`id_nota`),
  ADD KEY `fk_assegnazione` (`id_assegnazione`);

--
-- Indici per le tabelle `regione`
--
ALTER TABLE `regione`
  ADD PRIMARY KEY (`id_regione`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `assegnazione`
--
ALTER TABLE `assegnazione`
  MODIFY `id_assegnazione` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `mandato`
--
ALTER TABLE `mandato`
  MODIFY `id_mandato` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `nota_aggiuntiva`
--
ALTER TABLE `nota_aggiuntiva`
  MODIFY `id_nota` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `regione`
--
ALTER TABLE `regione`
  MODIFY `id_regione` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `assegnazione`
--
ALTER TABLE `assegnazione`
  ADD CONSTRAINT `fk_mandato` FOREIGN KEY (`id_mandato`) REFERENCES `mandato` (`id_mandato`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_regione` FOREIGN KEY (`id_regione`) REFERENCES `regione` (`id_regione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `nota_aggiuntiva`
--
ALTER TABLE `nota_aggiuntiva`
  ADD CONSTRAINT `fk_assegnazione` FOREIGN KEY (`id_assegnazione`) REFERENCES `assegnazione` (`id_assegnazione`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
