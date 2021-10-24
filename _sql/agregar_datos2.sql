-- Carga de datos 2

-- Carga de Atracciones de Itinerarios
INSERT INTO "AtraccionesDeItinerario" (
	"idItinerario",
	"idAtraccion"
)
VALUES
-- idItinerario: integer, idAtraccion: integer
	(1, 1),
	(2, 2),
	(2, 5)
;


-- Carga de Promociones de Itinerarios
INSERT INTO "PromocionesDeItinerarios" (
	"idItinerario",
	"idPromocion"
)
VALUES
-- idItinerario: integer, idAtraccion: integer
	(3, 2),
	(4, 3)
;