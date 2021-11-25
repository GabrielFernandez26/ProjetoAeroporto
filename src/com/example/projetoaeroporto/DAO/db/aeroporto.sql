DROP TABLE IF EXISTS "aeroporto"."voo";
CREATE TABLE  "aeroporto"."voo" (
  "id" int(10) unsigned NOT NULL AUTO_INCREMENT,
  "origem" varchar(45) NOT NULL DEFAULT '',
  "destino" varchar(45) NOT NULL DEFAULT '',
  "preco" decimal(10,00) NOT NULL DEFAULT 0,
  "decolagem" datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  "pouso" datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  "assentos" int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY ("id")
) 

CREATE TABLE `aeroporto`.`cliente` (
  `id` INTEGER UNSIGNED NOT NULL,
  `nome` VARCHAR(150) NOT NULL DEFAULT '',
  `datanascimento` DATETIME NOT NULL DEFAULT 0,
  `cpf` VARCHAR(11) NOT NULL DEFAULT '',
  PRIMARY KEY(`id`)
)
CREATE TABLE `aeroporto`.`reserva` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_voo` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `id_cliente` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `assento` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_reserva_1` FOREIGN KEY `FK_reserva_1` (`id_voo`)
    REFERENCES `voo` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_reserva_2` FOREIGN KEY `FK_reserva_2` (`id_cliente`)
    REFERENCES `cliente` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)