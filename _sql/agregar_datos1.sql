-- Carga de datos

-- Carga de Tipo de Atraccion
INSERT INTO "TipoDeAtraccion" (
	"nombre",
	"descripcion"
)
VALUES
-- nombre: text, descripcion: text
	('Aventura', 'Aventura'),
	('Paisaje', 'Paisaje'),
	('Degustacion', 'Degustación')
;

-- Carga de Usuarios
INSERT INTO "Usuarios" (
	"nombre",
	"presupuesto",
	"tiempoDisponible",
	"idTipoDeAtraccion"
)
VALUES
-- nombre: text, presupuesto: integer, tiempoDisponible: real, idTipoDeAtraccion: integer
	('Eowyn', 10, 8.0, 1),
	('Gandalf', 100, 5.0, 2),
	('Sam', 36, 8.0, 3),
	('Galadriel', 120, 3.0, 2)
;

-- Carga de Atracciones
INSERT INTO "Atracciones" (
	"nombre",
	"tiempoParaRealizarla",
	"visitantes",
	"cupoPersonas",
	"idTipoDeAtraccion"
)
VALUES
-- nombre: text, tiempoParaRealizarla: real, visitantes: integer, cupoPersonas: integer, idTipoDeAtraccion: integer
	('Moria', 2.0, 0, 6, 1),
	('Minas Tirith', 2.5, 0, 25, 2),
	('La Comarca', 6.5, 0, 150, 3),
	('Mordor', 3.0, 0, 4, 1),
	('Abismo del Helm', 2.0, 0, 15, 2),
	('Lothlórien', 1.0, 0, 30, 3),
	('Erebor', 3.0, 0, 32, 2),
	('Bosque Negro', 4.0, 0, 12, 1)
;

-- Carga de Promociones base
INSERT INTO "Promociones" (
	"nombre",
	"tipoDePromocion"
)
VALUES
-- nombre: text, tipoDePromocion: text
	('Pack aventura', 'PromocionPorcentual'),
	('Pack degustación', 'PromocionAbsoluta'),
	('Pack paisajes', 'PromocionAXB') 
;

-- Carga de Atracciones de Promociones
INSERT INTO "AtraccionesDePromociones" (
	"idPromocion",
	"idAtraccion",
	"promocionNoGeneral"
)
VALUES
-- idPromocion: integer, idAtraccion: integer, promocionNoGeneral: integer
	(1, 8, 0),
	(1, 4, 0),
	(2, 6, 0),
	(2, 3, 0),
	(3, 2, 0),
	(3, 5, 0)
;

-- Carga de Promociones absolutas
INSERT INTO "PromocionAbsoluta" (
	"idPromocion",
	"descuento"
)
VALUES
-- idPromocion: integer, descuento: integer
	(2, 36)
;

-- Carga de Promociones Porcentuales
INSERT INTO "PromocionPorcentual" (
	"idPromocion",
	"porcentuajeDeDescuento"
)
VALUES
-- idPromocion: integer, porcentuajeDeDescuento: real
	(1, 20.0)
;

-- Carga de Promociones AxB
INSERT INTO "PromocionAXB" (
	"idPromocion"
)
VALUES
-- idPromocion: integer
	(3)
;

-- Carga de Atracciones de Promociones AxB
INSERT INTO "AtraccionesDePromociones" (
	"idPromocion",
	"idAtraccion",
	"promocionNoGeneral"
)
VALUES
	(1, 7, 1)
;