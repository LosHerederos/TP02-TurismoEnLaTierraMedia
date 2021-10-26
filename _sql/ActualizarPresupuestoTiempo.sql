--Consulta para actualizar presupuesto y tiempoDisponible de Usuarios
UPDATE Usuarios
SET presupuesto = presupuesto - totalPago, 
	tiempoDisponible = tiempoDisponible - tiempoTotal
FROM (SELECT  sum(Atracciones.Costo) As totalPago, sum(tiempoParaRealizarla) As tiempoTotal
	FROM Atracciones 
		JOIN AtraccionesDeItinerario
			ON Atracciones.idAtraccion = AtraccionesDeItinerario.idAtraccion
		JOIN Itinerarios
			ON Itinerarios.idItinerario = AtraccionesDeItinerario.idItinerario
		WHERE idUsuario = 1
		)
		WHERE usuarios.idUsuario = 1;
--Consulta para actualizar presupuesto y tiempoDisponible de Usuarios
