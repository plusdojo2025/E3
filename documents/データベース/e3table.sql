-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバーのバージョン:                   8.0.26 - MySQL Community Server - GPL
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- e3 のデータベース構造をダンプしています
CREATE DATABASE IF NOT EXISTS `e3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `e3`;

--  テーブル e3.chat の構造をダンプしています
CREATE TABLE IF NOT EXISTS `chat` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'チャット文のid',
  `room_id` int NOT NULL DEFAULT '0' COMMENT '申請idを引用',
  `sender_id` int NOT NULL DEFAULT '0' COMMENT '送信者id',
  `chat_text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT 'テキスト本文',
  `chat_date` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

--  テーブル e3.idpw の構造をダンプしています
CREATE TABLE IF NOT EXISTS `idpw` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pass` varchar(20) NOT NULL DEFAULT '0',
  `email` varchar(320) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

--  テーブル e3.request の構造をダンプしています
CREATE TABLE IF NOT EXISTS `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `id` int NOT NULL DEFAULT '0' COMMENT '申請者自身の個別id',
  `partner_id` int NOT NULL DEFAULT '0' COMMENT '申請相手のid',
  `stand_by_id` int NOT NULL DEFAULT '0' COMMENT '待機情報id',
  `current_latitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '現在地緯度',
  `current_longitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '現在地経度',
  `drop_off_latitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '降車位置緯度',
  `drop_off_longitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '降車位置経度',
  `headcount` int NOT NULL DEFAULT '0' COMMENT '乗車人数',
  `status` int NOT NULL DEFAULT '0' COMMENT '申請状態(0:申請中 1:承認状態 2:却下状態)',
  `talking` int NOT NULL DEFAULT '0' COMMENT '0:チェック無し 1:会話無し',
  `smoking` int NOT NULL DEFAULT '0' COMMENT '0:チェック無し 1:非喫煙',
  `partner_gender` int NOT NULL DEFAULT '0' COMMENT '0:希望無し 1:男性 2:女性',
  `registration_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '登録日時',
  `date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '希望日時',
  PRIMARY KEY (`request_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

--  テーブル e3.requestdemo の構造をダンプしています
CREATE TABLE IF NOT EXISTS `requestdemo` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `id` int NOT NULL DEFAULT '0' COMMENT '申請者自身の個別id',
  `partner_id` int NOT NULL DEFAULT '0' COMMENT '申請相手のid',
  `stand_by_id` int NOT NULL DEFAULT '0' COMMENT '待機情報id',
  `current_address` varchar(100) NOT NULL DEFAULT '0' COMMENT '現在地住所',
  `drop_off_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '降車位置住所',
  `headcount` int NOT NULL DEFAULT '0' COMMENT '乗車人数',
  `status` int NOT NULL DEFAULT '0' COMMENT '申請状態(0:申請中 1:承認状態 2:却下状態)',
  `talking` int NOT NULL DEFAULT '0' COMMENT '0:チェック無し 1:会話無し',
  `smoking` int NOT NULL DEFAULT '0' COMMENT '0:チェック無し 1:非喫煙',
  `partner_gender` int NOT NULL DEFAULT '0' COMMENT '0:希望無し 1:男性 2:女性',
  `registration_date` varchar(100) NOT NULL DEFAULT '0' COMMENT '登録日時',
  `map_pass` varchar(260) NOT NULL DEFAULT '0' COMMENT 'デモ用の地図画像のパスを格納',
  `distance` varchar(100) NOT NULL DEFAULT '0' COMMENT '走行距離',
  `required_time` varchar(100) NOT NULL DEFAULT '0' COMMENT '単位:分',
  PRIMARY KEY (`request_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- エクスポートするデータが選択されていません

--  テーブル e3.standbyuser の構造をダンプしています
CREATE TABLE IF NOT EXISTS `standbyuser` (
  `stand_by_id` int NOT NULL AUTO_INCREMENT COMMENT '待機登録id',
  `id` int NOT NULL DEFAULT '0' COMMENT 'ユーザーの個別ID',
  `date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '希望日時',
  `current_latitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '現在地緯度',
  `current_longitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '現在地経度',
  `drop_off_latitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '降車位置緯度',
  `drop_off_longitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '降車位置経度',
  `headcount` int NOT NULL DEFAULT '0' COMMENT '乗車人数',
  `flag` int NOT NULL DEFAULT (0) COMMENT '待機状態(0:非待機状態 1:待機状態)',
  `registration_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '登録日時',
  `talking` int NOT NULL DEFAULT (0) COMMENT '0:チェック無し 1:会話無し',
  `smoking` int NOT NULL DEFAULT (0) COMMENT '0:チェック無し 1:非喫煙',
  `partner_gender` int NOT NULL DEFAULT '0' COMMENT '0:希望無し 1:男性 2:女性',
  PRIMARY KEY (`stand_by_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

--  テーブル e3.standbyuserdemo の構造をダンプしています
CREATE TABLE IF NOT EXISTS `standbyuserdemo` (
  `stand_by_id` int NOT NULL AUTO_INCREMENT COMMENT '待機登録id',
  `id` int NOT NULL DEFAULT '0' COMMENT 'ユーザーの個別ID',
  `date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '希望日時',
  `current_address` varchar(100) NOT NULL DEFAULT '0' COMMENT '現在地住所(デモ用)',
  `drop_off_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '降車位置住所(デモ用)',
  `headcount` int NOT NULL DEFAULT '0' COMMENT '乗車人数',
  `flag` int NOT NULL DEFAULT '0' COMMENT '待機状態(0:非待機状態 1:待機状態)',
  `registration_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '登録日時',
  `talking` int NOT NULL DEFAULT '0' COMMENT '0:チェック無し 1:会話無し',
  `smoking` int NOT NULL DEFAULT '0' COMMENT '0:チェック無し 1:非喫煙',
  `partner_gender` int NOT NULL DEFAULT '0' COMMENT '0:希望無し 1:男性 2:女性',
  `map_pass` varchar(260) NOT NULL DEFAULT '0' COMMENT 'デモ用地図画像パス',
  `distance` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '走行距離',
  `required_time` varchar(100) NOT NULL DEFAULT '0' COMMENT '単位:分',
  PRIMARY KEY (`stand_by_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- エクスポートするデータが選択されていません

--  テーブル e3.taxi の構造をダンプしています
CREATE TABLE IF NOT EXISTS `taxi` (
  `taxinumber` int NOT NULL AUTO_INCREMENT COMMENT '会社番号',
  `company` varchar(32) NOT NULL DEFAULT '0' COMMENT 'タクシー会社名',
  `phone` varchar(20) NOT NULL DEFAULT '0' COMMENT '電話番号',
  `taxi_address_latitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '会社住所緯度',
  `taxi_address_longitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '会社住所経度',
  `price` int NOT NULL DEFAULT (0) COMMENT '距離単価',
  `taxi_address` varchar(100) NOT NULL DEFAULT '0' COMMENT '会社住所(デモ用)',
  PRIMARY KEY (`taxinumber`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

--  テーブル e3.user の構造をダンプしています
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL DEFAULT (0) COMMENT 'ユーザー個別ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` int NOT NULL COMMENT '0:男性 1:女性',
  `address_latitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '自宅住所緯度',
  `address_longitude` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '自宅住所経度',
  `talking` int NOT NULL DEFAULT (0) COMMENT '0:チェック無し 1:会話無し',
  `smoking` int NOT NULL DEFAULT (0) COMMENT '0:チェック無し 1:非喫煙',
  `partner_gender` int NOT NULL DEFAULT '0' COMMENT '0:希望無し 1:男性 2:女性',
  `address` varchar(100) NOT NULL DEFAULT '0' COMMENT '自宅住所(デモ用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
