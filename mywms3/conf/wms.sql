/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : wms

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-04-01 10:03:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `dept` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('402881aa5306dbb3015306dd85690000', '蓝明涛', '部门A', '123456', '1', '13750523184', '1457988631@qq.com');
INSERT INTO `employee` VALUES ('402881aa532cb26b01532cb353eb0000', 'admin', '部门A', '123456', '1', '1', '1457988631@qq.com');
INSERT INTO `employee` VALUES ('402881b9535916ad0153591825e10000', 'qq', '部门B', '123456', '0', '1', '1457988631@qq.com');

-- ----------------------------
-- Table structure for `wms_customer`
-- ----------------------------
DROP TABLE IF EXISTS `wms_customer`;
CREATE TABLE `wms_customer` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_customer
-- ----------------------------
INSERT INTO `wms_customer` VALUES ('2c8a919c5331473701533147ac400000', '百度');
INSERT INTO `wms_customer` VALUES ('2c8a919c5331473701533147dd570001', '京东');
INSERT INTO `wms_customer` VALUES ('2c8a919c533147370153314942310002', '天猫');
INSERT INTO `wms_customer` VALUES ('2c8a919c533147370153314952ab0003', '淘宝');
INSERT INTO `wms_customer` VALUES ('402881b95373676d01537367ce5e0000', '腾讯');

-- ----------------------------
-- Table structure for `wms_form`
-- ----------------------------
DROP TABLE IF EXISTS `wms_form`;
CREATE TABLE `wms_form` (
  `id` varchar(32) NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `optime` datetime DEFAULT NULL,
  `inStorage` varchar(32) DEFAULT NULL,
  `outStorage` varchar(32) DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `inStorageId` varchar(32) DEFAULT NULL,
  `outStorageId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_inStorage` (`inStorage`) USING BTREE,
  KEY `I_outStorage` (`outStorage`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_form
-- ----------------------------
INSERT INTO `wms_form` VALUES ('402881b953731ba70153731c42bf0000', '0', '2016-03-14 11:12:25', '2016-03-14 11:12:00', '广东3323仓', '广东35仓', 'admin', '402881b9537315680153731609260000', null);
INSERT INTO `wms_form` VALUES ('402881b953731ba70153731c87af0004', '1', '2016-03-14 11:12:43', '2016-03-14 11:12:00', null, '广东3323仓', 'admin', null, '402881b9537315680153731609260000');
INSERT INTO `wms_form` VALUES ('402881b95373201e01537320cf300000', '0', '2016-03-14 11:17:23', '2016-03-14 11:17:00', '广东3323仓', null, 'admin', '402881b9537315680153731609260000', null);
INSERT INTO `wms_form` VALUES ('402881b95373201e01537320fd290004', '1', '2016-03-14 11:17:35', '2016-03-14 11:17:00', null, '广东3323仓', 'admin', null, '402881b9537315680153731609260000');
INSERT INTO `wms_form` VALUES ('402881b9537324c40153732545c50000', '0', '2016-03-14 11:22:16', '2016-03-14 11:22:00', '广东3323仓', null, 'admin', '402881b9537315680153731609260000', null);
INSERT INTO `wms_form` VALUES ('402881b9537324c401537325776a0004', '1', '2016-03-14 11:22:29', '2016-03-14 11:22:00', null, '广东3323仓', 'admin', null, '402881b9537315680153731609260000');

-- ----------------------------
-- Table structure for `wms_form_detail`
-- ----------------------------
DROP TABLE IF EXISTS `wms_form_detail`;
CREATE TABLE `wms_form_detail` (
  `id` varchar(32) NOT NULL,
  `formId` varchar(32) DEFAULT NULL,
  `materialId` varchar(32) DEFAULT NULL,
  `materialName` varchar(32) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `instorageBinCode` varchar(32) DEFAULT NULL,
  `outstorageBinCode` varchar(32) DEFAULT NULL,
  `instorageBinName` varchar(32) DEFAULT NULL,
  `outstorageBinName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_form_detail
-- ----------------------------
INSERT INTO `wms_form_detail` VALUES ('402881b953731ba70153731c42d50001', '402881b953731ba70153731c42bf0000', '402881aa530c9ac101530ca1cbfb000b', '苹果', '1000', '402881b9537315680153731624590001', null, '3', null);
INSERT INTO `wms_form_detail` VALUES ('402881b953731ba70153731c87be0005', '402881b953731ba70153731c87af0004', null, '苹果', '13', null, '402881b9537315680153731624590001', null, '3');
INSERT INTO `wms_form_detail` VALUES ('402881b95373201e01537320cf3f0001', '402881b95373201e01537320cf300000', '402881aa530c9ac101530ca1e509000c', '菠萝', '100', '402881b9537315680153731624590001', null, '3', null);
INSERT INTO `wms_form_detail` VALUES ('402881b95373201e01537320fd310005', '402881b95373201e01537320fd290004', null, '苹果', '123', null, '402881b9537315680153731624590001', null, '3');
INSERT INTO `wms_form_detail` VALUES ('402881b9537324c40153732545d40001', '402881b9537324c40153732545c50000', '402881aa532cb26b01532cc2cc6a003c', '梨子', '199', '402881b9537315680153731624590001', null, '3', null);
INSERT INTO `wms_form_detail` VALUES ('402881b9537324c40153732577760005', '402881b9537324c401537325776a0004', null, '梨子', '13', null, '402881b9537315680153731624590001', null, '3');

-- ----------------------------
-- Table structure for `wms_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory`;
CREATE TABLE `wms_inventory` (
  `id` varchar(32) NOT NULL,
  `storageName` varchar(32) DEFAULT NULL,
  `materialNme` varchar(32) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `materialName` varchar(32) DEFAULT NULL,
  `store_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_inventory
-- ----------------------------
INSERT INTO `wms_inventory` VALUES ('402881b953731ba70153731c42f20003', '广东3323仓', null, '864', '苹果', '402881b9537315680153731609260000');
INSERT INTO `wms_inventory` VALUES ('402881b95373201e01537320cf600003', '广东3323仓', null, '100', '菠萝', '402881b9537315680153731609260000');
INSERT INTO `wms_inventory` VALUES ('402881b9537324c40153732545ef0003', '广东3323仓', null, '186', '梨子', '402881b9537315680153731609260000');

-- ----------------------------
-- Table structure for `wms_inventory_bin`
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory_bin`;
CREATE TABLE `wms_inventory_bin` (
  `id` varchar(32) NOT NULL,
  `storageName` varchar(32) DEFAULT NULL,
  `storageBinName` varchar(32) DEFAULT NULL,
  `materialName` varchar(32) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `store_id` varchar(32) DEFAULT NULL,
  `store_bin_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_inventory_bin
-- ----------------------------
INSERT INTO `wms_inventory_bin` VALUES ('402881b953731ba70153731c42e60002', '广东3323仓', '3', '苹果', '864', '402881b9537315680153731609260000', '402881b9537315680153731624590001');
INSERT INTO `wms_inventory_bin` VALUES ('402881b95373201e01537320cf4d0002', '广东3323仓', '3', '菠萝', '100', '402881b9537315680153731609260000', '402881b9537315680153731624590001');
INSERT INTO `wms_inventory_bin` VALUES ('402881b9537324c40153732545e20002', '广东3323仓', '3', '梨子', '186', '402881b9537315680153731609260000', '402881b9537315680153731624590001');

-- ----------------------------
-- Table structure for `wms_location`
-- ----------------------------
DROP TABLE IF EXISTS `wms_location`;
CREATE TABLE `wms_location` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `catogery` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_location
-- ----------------------------
INSERT INTO `wms_location` VALUES ('402881aa530d48c001530d493f020000', '广东3', '0');
INSERT INTO `wms_location` VALUES ('402881aa533b3a6801533b3b21780000', '厦门', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d01534508c5680000', '深圳市4', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d01534508de1b0001', '珠海市', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153450904df0002', '汕头市', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d015345092f0f0003', '阳江市', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153450955a50004', '梅州市', '1');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153450979dd0005', '江门市', '1');
INSERT INTO `wms_location` VALUES ('402881aa5345078d01534509abab0006', '佛山市', '1');
INSERT INTO `wms_location` VALUES ('402881aa5345078d01534509d09b0007', '揭阳市', '1');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153451d0b970008', '肇庆市', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153451d45bc0009', '韶关市', '1');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153451d7468000a', '惠州市', '1');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153451dfada000b', '河源市', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153451e4d3a000c', '茂名市', '0');
INSERT INTO `wms_location` VALUES ('402881aa5345078d0153451e94c0000d', '湛江市', '1');

-- ----------------------------
-- Table structure for `wms_material`
-- ----------------------------
DROP TABLE IF EXISTS `wms_material`;
CREATE TABLE `wms_material` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `price` double(32,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_material
-- ----------------------------
INSERT INTO `wms_material` VALUES ('402881aa530c9ac101530ca1cbfb000b', '苹果', '220.00');
INSERT INTO `wms_material` VALUES ('402881aa530c9ac101530ca1e509000c', '菠萝', '220.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc1d1ad003b', '哈密瓜', '34.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc2cc6a003c', '梨子', '23.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc2e2f2003d', '李子', '23.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc43d500044', '番荔枝', '50.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc46f1e0045', '枇杷', '50.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc48db20046', '杨桃', '50.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc4a8ab0047', '芒果', '50.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc4c8ce0048', '柠檬', '50.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc502320049', '葡萄', '69.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc54f2f004a', '柿子', '56.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc56dc6004b', '草莓', '68.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc5981c004c', '香瓜', '56.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc5cede004d', '龙眼', '60.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc5f420004e', '凤梨', '88.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc643fe004f', '梅子', '188.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc6c2d30050', '柚子', '77.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc6ebed0051', '橘子', '58.00');
INSERT INTO `wms_material` VALUES ('402881aa532cb26b01532cc7150e0052', '橙子', '66.00');

-- ----------------------------
-- Table structure for `wms_sn`
-- ----------------------------
DROP TABLE IF EXISTS `wms_sn`;
CREATE TABLE `wms_sn` (
  `id` varchar(32) NOT NULL,
  `materialName` varchar(32) DEFAULT NULL,
  `addressName` varchar(32) DEFAULT NULL,
  `sn` varchar(32) DEFAULT NULL,
  `customerName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_sn
-- ----------------------------
INSERT INTO `wms_sn` VALUES ('402881b953733a46015373645f560002', '苹果', '广东3', 'SN_C1D19F06C4DA', '百度');
INSERT INTO `wms_sn` VALUES ('402881b953733a460153736472500003', '苹果', '广东3', 'SN_C1D19F06C4DA', '京东');
INSERT INTO `wms_sn` VALUES ('402881b953733a4601537364888a0004', '苹果', '广东3', 'SN_C1D19F06C4DA', '天猫');
INSERT INTO `wms_sn` VALUES ('402881b95373676d01537367f4e10001', '苹果', '广东3', '1111', '腾讯');
INSERT INTO `wms_sn` VALUES ('402881b95373676d015373681e4a0002', '苹果', '广东3', 'SN_C1D19F06C4DA', '淘宝');
INSERT INTO `wms_sn` VALUES ('402881b95373676d0153736912c20003', '菠萝', '广东3', 'SN_82EBE1720A12', '百度');
INSERT INTO `wms_sn` VALUES ('402881b95373676d015373693c020004', '菠萝', '广东3', 'SN_82EBE1720A12', '京东');

-- ----------------------------
-- Table structure for `wms_storage`
-- ----------------------------
DROP TABLE IF EXISTS `wms_storage`;
CREATE TABLE `wms_storage` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  `address_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_storage
-- ----------------------------
INSERT INTO `wms_storage` VALUES ('402881b9537315680153731609260000', '广东3323仓', '广东3', '402881aa530d48c001530d493f020000');
INSERT INTO `wms_storage` VALUES ('402881b953731ba70153731e54600006', '广东212仓', '广东3', '402881aa530d48c001530d493f020000');

-- ----------------------------
-- Table structure for `wms_storage_bin`
-- ----------------------------
DROP TABLE IF EXISTS `wms_storage_bin`;
CREATE TABLE `wms_storage_bin` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `storeName` varchar(32) DEFAULT NULL,
  `store_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wms_storage_bin
-- ----------------------------
INSERT INTO `wms_storage_bin` VALUES ('402881b9537315680153731624590001', '3', '广东3323仓', '402881b9537315680153731609260000');
INSERT INTO `wms_storage_bin` VALUES ('402881b953731568015373162fb00002', '22', '广东3323仓', '402881b9537315680153731609260000');
