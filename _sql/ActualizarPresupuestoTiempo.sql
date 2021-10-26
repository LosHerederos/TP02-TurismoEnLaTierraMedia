--Consulta para actualizar presupuesto y tiempoDisponible de Usuarios*/
SELECT  sum(Atracciones.Costo) As suma, * 
	FROM Usuarios
		JOIN Itinerarios
			ON Usuarios.idUsuario = Itinerarios.idUsuario
		JOIN AtraccionesDeItinerario
			ON Itinerarios.idItinerario = AtraccionesDeItinerario.iditinerario
		JOIN Atracciones
			ON AtraccionesDeItinerario.idAtraccion = Atracciones.idAtraccion
WHERE Usuarios.idusuario = 1;
--Consulta para actualizar visitantes de Atracciones
