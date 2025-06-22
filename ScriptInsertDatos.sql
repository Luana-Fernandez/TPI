INSERT jueces(nombre, apellido,telefono,email) 
VALUES ('BAUTISTA', 'THOMANN', '3534589665','elmascapo@gmail.com'),
('ELOY','MOLINA','3534589657','eloy@gmail.com'),
('LUANA','FERNANDEZ','4655222455','LUANA@GMAIL.COM');

INSERT competidores(nombre,apellido,telefono,email)
VALUES('DAVID','RUIZ','3534555888','DRUIZ@GMAIL.COM'),
('JERO','ALVAREZ','55568647858','JERO@GMAIL.COM'),
('MAGA','PEREZ','44557789','MAGA@GMAIL.COM'),
('JUAN','PEREZ','455467897','JUANPEREZ@GMAIL.COM'),
('NANO','RIVERA','546588979','NANO@GMAIL.COM'),
('ALVARO','RIVERA','3534192139','ALVARO@GMAIL.COM');

INSERT carreras(categoria,idJuez,horaInicio,ubicacion)
VALUES ('5K',3,'12/05/2025 10:00','VILLA MARIA - CORDOBA'),
('10K',2,'12/05/2025 11:00','VILLA MARIA - CORDOBA'),
('25K ELITE',1,'12/05/2025 07:00','VILLA MARIA- CORDOBA');

INSERT resultados(idCompetidor,idCarrera,tiempoCompetidor,estado,numeroCorredor,faltas)
VALUES (1,1,'01:25:45','completo',42,'0'),(2,1,'01:38:59','completo',18,'0'),
(3,2,'02:00:15','completo',22,'0'),(4,3,'--:--:--','abandono',36,'1'),
(5,3,'01:25:15','completo',4,'0'),(6,2,'00:45:22','completo',1,'0');
