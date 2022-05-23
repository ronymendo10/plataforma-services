-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2022 a las 12:22:05
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `plataforma`
--

DELIMITER $$
--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_CURRVAL` (`SEQ_NAME` VARCHAR(11)) RETURNS BIGINT(20) BEGIN
  DECLARE EXIST_SEQ INT;
  DECLARE CUR_VALUE BIGINT(20);
  
  SET EXIST_SEQ = (SELECT COUNT(1) FROM sequence_data WHERE SEQUENCE_NAME = SEQ_NAME);
  SET CUR_VALUE = NULL;
  
  IF EXIST_SEQ > 0 THEN
    SET CUR_VALUE = (SELECT SEQUENCE_CUR_VALUE FROM sequence_data WHERE SEQUENCE_NAME = SEQ_NAME);
  END IF;
  
  RETURN CUR_VALUE; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fn_NEXTVALUE` (`SEQ_NAME` VARCHAR(50)) RETURNS BIGINT(20) BEGIN
	
  DECLARE EXIST_SEQ INT;
  DECLARE CUR_VALUE BIGINT(20);
  
  SET EXIST_SEQ = (SELECT COUNT(1) FROM sequence_data WHERE SEQUENCE_NAME = SEQ_NAME);
  
  IF EXIST_SEQ > 0 THEN
    UPDATE sequence_data
    SET SEQUENCE_CUR_VALUE = IF ( (IFNULL(SEQUENCE_CUR_VALUE,0) + SEQUENCE_INCREMENT) < SEQUENCE_MAX_VALUE ,
                                    IFNULL(SEQUENCE_CUR_VALUE,0) + SEQUENCE_INCREMENT,
                                    SEQUENCE_MAX_VALUE);
  END IF;
  
  SET CUR_VALUE = (SELECT SEQUENCE_CUR_VALUE FROM sequence_data WHERE SEQUENCE_NAME = SEQ_NAME);
  
	RETURN CUR_VALUE;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence_data`
--

CREATE TABLE `sequence_data` (
  `SEQUENCE_NAME` varchar(50) DEFAULT NULL,
  `SEQUENCE_INCREMENT` int(11) DEFAULT 1,
  `SEQUENCE_MIN_VALUE` int(11) DEFAULT 1,
  `SEQUENCE_MAX_VALUE` bigint(20) UNSIGNED DEFAULT 999999,
  `SEQUENCE_CUR_VALUE` bigint(20) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `sequence_data`
--

INSERT INTO `sequence_data` (`SEQUENCE_NAME`, `SEQUENCE_INCREMENT`, `SEQUENCE_MIN_VALUE`, `SEQUENCE_MAX_VALUE`, `SEQUENCE_CUR_VALUE`) VALUES
('SEQ_ID', 1, 1, 999999, 48);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea`
--

CREATE TABLE `tarea` (
  `id_tarea` int(11) NOT NULL,
  `codigo_tarea` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(124) NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT current_timestamp(),
  `fecha_asignacion` datetime DEFAULT NULL,
  `fecha_entrega` datetime NOT NULL,
  `fecha_actualizacion` datetime NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `dni` varchar(12) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `contraseña` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `dni`, `nombre`, `apellido`, `username`, `contraseña`) VALUES
(1, 'string', 'string', 'string', 'string', '$argon2id$v=19$m=1024,t=1,p=1$Zt5areqatFtUvM5OTH2IkQ$PFYSx1bU4S6EcADOga/kSrSDGkgfa+BKIyzu4fHWSAg'),
(2, 'strinqg', 'striqng', 'string', 'strqing', '$argon2id$v=19$m=1024,t=1,p=1$csEQD7kMhhrQAQC6mbp1pA$gXE1IY+a3uXdqC415GKO5iMD4Qot70H2Tg1gqUhNraw');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_tarea`
--

CREATE TABLE `usuario_tarea` (
  `id_usuario_tarea` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tarea` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_tarea`
--

INSERT INTO `usuario_tarea` (`id_usuario_tarea`, `id_usuario`, `id_tarea`) VALUES
(5, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`id_tarea`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `usuario_tarea`
--
ALTER TABLE `usuario_tarea`
  ADD PRIMARY KEY (`id_usuario_tarea`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tarea`
--
ALTER TABLE `tarea`
  MODIFY `id_tarea` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario_tarea`
--
ALTER TABLE `usuario_tarea`
  MODIFY `id_usuario_tarea` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
