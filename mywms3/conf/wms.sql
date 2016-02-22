/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015-12-29 12:30:12                          */
/*@Anthor :        LMT                                          */
/*==============================================================*/


drop table if exists employee;

drop table if exists wms_form;

drop table if exists wms_form_detail;

drop table if exists wms_inventory;

drop table if exists wms_inventory_bin;

drop table if exists wms_location;

drop table if exists wms_sn;

drop table if exists wms_storage;

drop table if exists wms_storage_bin;

drop table if exists wms_material;

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   id                   varchar(32) not null,
   name                 varchar(32),
   dept                 varchar(32),
   password             varchar(32),
   gender               boolean,
   mobile               varchar(32),
   email                 varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: wms_form                                              */
/*==============================================================*/
create table wms_form
(
   id                   varchar(32) not null,
   type                 varchar(32),
   createDate           datetime,
   optime               datetime,
   inStorage            varchar(32),
   outStorage           varchar(32),
   operator             varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: wms_form_detail                                       */
/*==============================================================*/
create table wms_form_detail
(
   id                   varchar(32) not null,
   formId               varchar(32),
   materialId           varchar(32),
   materialName         varchar(32),
   quantity             int,
   instorageBinCode     varchar(32),
   outstorageBinCode    varchar(32),
   instorageBinName     varchar(32),
   outstorageBinName    varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: wms_inventory                                         */
/*==============================================================*/
create table wms_inventory
(
   id                   varchar(32) not null,
   storageName          varchar(32),
   materialNme          varchar(32),
   quantity             int,
   primary key (id)
);

/*==============================================================*/
/* Table: wms_inventory_bin                                     */
/*==============================================================*/

create table wms_inventory_bin
(
   id                   varchar(32) not null,
   storageName          varchar(32),
   storageBinName       varchar(32),
   materialName         varchar(32),
   quantity             int,
   primary key (id)
);
/*==============================================================*/
/* Table: wms_location                                          */
/*==============================================================*/
create table wms_location
(
   id                   varchar(32) not null,
   name                 varchar(20),
   primary key (id)
);

/*==============================================================*/
/* Table: wms_sn                                                */
/*==============================================================*/
/*==============================================================*/
/* Table: wms_sn                                                */
/*==============================================================*/
create table wms_sn
(
   id                   varchar(32) not null,
   materialName         varchar(32),
   addressName          varchar(32),
   sn                   varchar(32),
   primary key (id)
);


/*==============================================================*/
/* Table: wms_storage                                           */
/*==============================================================*/
create table wms_storage
(
   id                   varchar(32) not null,
   name                 varchar(32),
   address              varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: wms_storage_bin                                       */
/*==============================================================*/
create table wms_storage_bin
(
   id                   varchar(32) not null,
   name                 varchar(32),
   storeName            varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: wms_material                                          */
/*==============================================================*/
create table wms_material
(
   id                   varchar(32) not null,
   name                 varchar(32),
   price                double(32,2),
   primary key (id)
);


select * from wms_form;
select * from wms_form_detail;
select * from wms_inventory_bin;
select * from wms_inventory;
select * from wms_sn;

