# **Script do Banco de Dados da Equipe Cão do XumeLabs**

## *Criação da tabela cliente*

```sql
CREATE TABLE `cliente` (
  `cpf` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `nome_estacionamento` varchar(45) NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  KEY `nome_estacionamento_idx` (`nome_estacionamento`)
);
```

## *Criação da tabela Estacionamento*

```sql
CREATE TABLE `estacionamento` (
  `nome_estacionamento` varchar(45) NOT NULL,
  `numerodevagas` int NOT NULL,
  PRIMARY KEY (`nome_estacionamento`)
); 
```

## *Criação da tabela Uso de Vaga*

```sql
CREATE TABLE `usodevaga` (
  `placa` varchar(45) NOT NULL,
  `numeroVaga` varchar(45) NOT NULL,
  `horachegada` datetime DEFAULT NULL,
  `horasaida` datetime DEFAULT NULL,
  `data` date DEFAULT NULL,
  `tempousado` double DEFAULT NULL,
  `valoraPagar` double DEFAULT NULL,
  `status` tinyint NOT NULL,
  `nome_estacionamento` varchar(45) NOT NULL,
  PRIMARY KEY (`placa`,`numeroVaga`),
  KEY `numeroVaga_idx` (`numeroVaga`),
  KEY `nome_estacionamento_idx` (`nome_estacionamento`)
)
```

## *Criação da tabela Vaga*

```sql
CREATE TABLE `vaga` (
  `idVaga` int NOT NULL AUTO_INCREMENT,
  `numeroVaga` varchar(45) DEFAULT NULL,
  `ocupada` tinyint(1) DEFAULT NULL,
  `tipo_vaga` varchar(45) DEFAULT NULL,
  `taxa` double DEFAULT NULL,
  `nome_estacionamento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idVaga`),
  KEY `fk_vaga_estacionamento` (`nome_estacionamento`)
);
```

## *Criação da tabela Veiculo*

```sql
CREATE TABLE `veiculo` (
  `placa` varchar(50) NOT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`placa`),
  UNIQUE KEY `placa_UNIQUE` (`placa`),
  KEY `cnpj_idx` (`cpf`)
);
```


## *Script do Insert na tabela Estacionamento*

```sql
INSERT INTO `estacionamento` (`nome_estacionamento`, `numerodevagas`) 
VALUES 
('GuardaCarros', 30),
('NorteMinas', 25),
('XumeLabs', 20);
```

## *Script de Insert na tabela Vaga(saiba que no projeto ele é preenchido automáticamente ao cadastrar um estacionamento)*

* Vagas do estacionamento XumeLabs 

```sql
INSERT INTO `vaga` (`numeroVaga`, `ocupada`, `tipo_vaga`, `taxa`, `nome_estacionamento`) 
VALUES
('Y01', 0, 'Idoso', 0.85, 'XumeLabs'),
('Y02', 0, 'Idoso', 0.85, 'XumeLabs'),
('Y03', 0, 'Idoso', 0.85, 'XumeLabs'),
('Y04', 0, 'Idoso', 0.85, 'XumeLabs'),
('Y05', 0, 'Idoso', 0.85, 'XumeLabs'),
('Y06', 0, 'PCD', 0.87, 'XumeLabs'),
('Y07', 0, 'PCD', 0.87, 'XumeLabs'),
('Y08', 0, 'PCD', 0.87, 'XumeLabs'),
('Y09', 0, 'PCD', 0.87, 'XumeLabs'),
('Y10', 0, 'PCD', 0.87, 'XumeLabs'),
('Y11', 0, 'VIP', 1.2, 'XumeLabs'),
('Y12', 0, 'VIP', 1.2, 'XumeLabs'),
('Y13', 0, 'VIP', 1.2, 'XumeLabs'),
('Y14', 0, 'VIP', 1.2, 'XumeLabs'),
('Y15', 0, 'VIP', 1.2, 'XumeLabs'),
('Y16', 0, 'Default', 1, 'XumeLabs'),
('Y17', 0, 'Default', 1, 'XumeLabs'),
('Y18', 0, 'Default', 1, 'XumeLabs'),
('Y19', 0, 'Default', 1, 'XumeLabs'),
('Y20', 0, 'Default', 1, 'XumeLabs');
```

* Vagas para o Estacionamento Norte Minas

```sql
INSERT INTO `vaga` (`numeroVaga`, `ocupada`, `tipo_vaga`, `taxa`, `nome_estacionamento`) 
VALUES
('Y01', 0, 'Idoso', 0.85, 'NorteMinas'),
('Y02', 0, 'Idoso', 0.85, 'NorteMinas'),
('Y03', 0, 'Idoso', 0.85, 'NorteMinas'),
('Y04', 0, 'Idoso', 0.85, 'NorteMinas'),
('Y05', 0, 'Idoso', 0.85, 'NorteMinas'),
('Y06', 0, 'Idoso', 0.85, 'NorteMinas'),
('Y07', 0, 'PCD', 0.87, 'NorteMinas'),
('Y08', 0, 'PCD', 0.87, 'NorteMinas'),
('Y09', 0, 'PCD', 0.87, 'NorteMinas'),
('Y10', 0, 'PCD', 0.87, 'NorteMinas'),
('Y11', 0, 'PCD', 0.87, 'NorteMinas'),
('Y12', 0, 'PCD', 0.87, 'NorteMinas'),
('Y13', 0, 'VIP', 1.2, 'NorteMinas'),
('Y14', 0, 'VIP', 1.2, 'NorteMinas'),
('Y15', 0, 'VIP', 1.2, 'NorteMinas'),
('Y16', 0, 'VIP', 1.2, 'NorteMinas'),
('Y17', 0, 'VIP', 1.2, 'NorteMinas'),
('Y18', 0, 'VIP', 1.2, 'NorteMinas'),
('Y19', 0, 'Default', 1, 'NorteMinas'),
('Y20', 0, 'Default', 1, 'NorteMinas'),
('Y21', 0, 'Default', 1, 'NorteMinas'),
('Y22', 0, 'Default', 1, 'NorteMinas'),
('Y23', 0, 'Default', 1, 'NorteMinas'),
('Y24', 0, 'Default', 1, 'NorteMinas'),
('Y25', 0, 'Default', 1, 'NorteMinas');
```

* Vagas para o estacionamento GuardaCarros

```sql
INSERT INTO `vaga` (`numeroVaga`, `ocupada`, `tipo_vaga`, `taxa`, `nome_estacionamento`) 
VALUES
('Y01', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y02', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y03', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y04', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y05', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y06', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y07', 0, 'Idoso', 0.85, 'GuardaCarros'),
('Y08', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y09', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y10', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y11', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y12', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y13', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y14', 0, 'PCD', 0.87, 'GuardaCarros'),
('Y15', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y16', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y17', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y18', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y19', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y20', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y21', 0, 'VIP', 1.2, 'GuardaCarros'),
('Y22', 0, 'Default', 1, 'GuardaCarros'),
('Y23', 0, 'Default', 1, 'GuardaCarros'),
('Y24', 0, 'Default', 1, 'GuardaCarros'),
('Y25', 0, 'Default', 1, 'GuardaCarros'),
('Y26', 0, 'Default', 1, 'GuardaCarros'),
('Y27', 0, 'Default', 1, 'GuardaCarros'),
('Y28', 0, 'Default', 1, 'GuardaCarros'),
('Y29', 0, 'Default', 1, 'GuardaCarros'),
('Y30', 0, 'Default', 1, 'GuardaCarros');
```

## *Script de Insert dos clientes*

```sql
INSERT INTO `cliente` (`cpf`, `nome`, `nome_estacionamento`)
VALUES
('123', 'João Silva', 'GuardaCarros'),
('2345', 'Maria Oliveira', 'GuardaCarros'),
('6789', 'Carlos Souza', 'GuardaCarros'),
('456', 'Ana Costa', 'NorteMinas'),
('5678', 'Paula Almeida', 'NorteMinas'),
('67890', 'Rafael Pereira', 'NorteMinas'),
('789', 'Lucas Santos', 'XumeLabs'),
('89012', 'Fernanda Lima', 'XumeLabs'),
('90123', 'Gabriela Rocha', 'XumeLabs');
```

## *Script de Insert dos Veiculos*

```sql
INSERT INTO `veiculo` VALUES ('asd012',NULL),('mno678',NULL),('vwx567',NULL),('abc123','123'),('xyz456','2345'),('jkl345','456'),('pqr901','5678'),('qwe789','6789'),('stu234','67890'),('yza890','789'),('ab2020','89012'),('ab3030','90123');    
```

## *Script de Insert dos Usos De Vaga*

* Estacionamento XumeLabs

```sql
INSERT INTO `usodevaga` VALUES ('ab2020','Y01','2024-11-21 19:40:30','2024-11-21 20:40:30','2024-11-21',60,13.6,1,'XumeLabs'),('ab2020','Y17','2024-11-21 14:07:29','2024-11-21 16:40:30','2024-11-21',153,40.8,1,'XumeLabs'),('ab3030','Y10','2024-11-21 19:40:30','2024-11-21 20:40:30','2024-11-21',60,13.92,1,'XumeLabs'),('ab3030','Y12','2024-11-21 14:09:03','2024-11-21 15:40:30','2024-11-21',91,29.119999999999997,1,'XumeLabs'),('yza890','Y03','2024-11-21 07:20:30','2024-11-21 11:40:30','2024-11-21',260,50,1,'XumeLabs'),('yza890','Y04','2024-11-21 07:20:30','2024-11-21 11:40:30','2024-11-21',260,50,1,'XumeLabs'),('yza890','Y09','2024-11-21 07:20:30','2024-11-21 07:40:30','2024-11-21',20,4.64,1,'XumeLabs'),('yza890','Y14','2024-11-21 12:20:29','2024-11-21 16:40:30','2024-11-21',260,50,1,'XumeLabs'),('yza890','Y16','2024-11-21 12:19:44','2024-11-21 16:40:30','2024-11-21',260,50,1,'XumeLabs');
```