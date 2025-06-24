INSERT jueces(nombre, apellido,telefono,email,dni) 
VALUES ('BAUTISTA', 'THOMANN', '3534589665','elmascapo@gmail.com','45222333'),
('ELOY','MOLINA','3534589657','eloy@gmail.com','35666666'),
('LUANA','FERNANDEZ','4655222455','LUANA@GMAIL.COM','40550111');

INSERT competidores(nombre,apellido,telefono,email,dni)
VALUES('DAVID','RUIZ','3534555888','DRUIZ@GMAIL.COM','47555610'),
('JERO','ALVAREZ','55568647858','JERO@GMAIL.COM','22222222'),
('MAGA','PEREZ','44557789','MAGA@GMAIL.COM','44444444'),
('JUAN','PEREZ','455467897','JUANPEREZ@GMAIL.COM','12000000'),
('NANO','RIVERA','546588979','NANO@GMAIL.COM','34992862'),
('ALVARO','RIVERA','3534192139','ALVARO@GMAIL.COM','33411494');

INSERT carreras(categoria,idJuez,horaInicio,ubicacion)
VALUES ('5K',3,'12/05/2025 10:00','VILLA MARIA - CORDOBA'),
('10K',2,'12/05/2025 11:00','VILLA MARIA - CORDOBA'),
('25K ELITE',1,'12/05/2025 07:00','VILLA MARIA- CORDOBA');

INSERT resultados(idCompetidor,idCarrera,tiempoCompetidor,estado,numeroCorredor,faltas)
VALUES (1,1,'01:25:45','completo',42,'0'),(2,1,'01:38:59','completo',18,'0'),
(3,2,'02:00:15','completo',22,'0'),(4,3,'--:--:--','abandono',36,'1'),
(5,3,'01:25:15','completo',4,'0'),(6,2,'00:45:22','completo',1,'0');
