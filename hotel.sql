-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 08 jan. 2022 à 13:03
-- Version du serveur :  10.4.19-MariaDB
-- Version de PHP : 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hotel`
--

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `ID_C` int(11) NOT NULL,
  `ID_T` int(11) NOT NULL,
  `NUM_CHAMBRE` int(11) DEFAULT NULL,
  `DESC_CHAMBRE` text DEFAULT NULL,
  `DISPO` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `ID_CL` int(11) NOT NULL,
  `CIN` text DEFAULT NULL,
  `NOM_CLT` text DEFAULT NULL,
  `PRENOM_CLT` text DEFAULT NULL,
  `EMAIL` text DEFAULT NULL,
  `NUMTEL_CLT` text DEFAULT NULL,
  `ADRESSE` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `concerner`
--

CREATE TABLE `concerner` (
  `ID_R` int(11) NOT NULL,
  `ID_C` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `ID_EMP` int(11) NOT NULL,
  `CIN_E` text DEFAULT NULL,
  `NOM_EMP` text DEFAULT NULL,
  `PRENOM_EMP` text DEFAULT NULL,
  `NUMTEL` text DEFAULT NULL,
  `EMAIL_EMP` text DEFAULT NULL,
  `FONCTION` text DEFAULT NULL,
  `PASSWORD` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `gerer`
--

CREATE TABLE `gerer` (
  `ID_EMP` int(11) NOT NULL,
  `ID_R` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `ID_R` int(11) NOT NULL,
  `NUMERO_RSV` int(11) DEFAULT NULL,
  `NOMBRE_PER` int(11) DEFAULT NULL,
  `NOMBRE_CH` int(11) DEFAULT NULL,
  `DATE_ARIV` date DEFAULT NULL,
  `DATE_SORT` date DEFAULT NULL,
  `TOTAL_RSV` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reserver`
--

CREATE TABLE `reserver` (
  `ID_CL` int(11) NOT NULL,
  `ID_R` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `type_c`
--

CREATE TABLE `type_c` (
  `ID_T` int(11) NOT NULL,
  `INTITULE` text DEFAULT NULL,
  `CAPACITE` int(11) DEFAULT NULL,
  `PRIX` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`ID_C`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ID_CL`);

--
-- Index pour la table `concerner`
--
ALTER TABLE `concerner`
  ADD PRIMARY KEY (`ID_R`,`ID_C`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`ID_EMP`);

--
-- Index pour la table `gerer`
--
ALTER TABLE `gerer`
  ADD PRIMARY KEY (`ID_EMP`,`ID_R`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`ID_R`);

--
-- Index pour la table `reserver`
--
ALTER TABLE `reserver`
  ADD PRIMARY KEY (`ID_CL`,`ID_R`);

--
-- Index pour la table `type_c`
--
ALTER TABLE `type_c`
  ADD PRIMARY KEY (`ID_T`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
