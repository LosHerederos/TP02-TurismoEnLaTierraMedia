BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "TipoDeAtraccion" (
	"idTipoDeAtraccion"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"descripcion"	TEXT,
	PRIMARY KEY("idTipoDeAtraccion" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Promociones" (
	"idPromocion"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"tipoDePromocion"	TEXT,
	PRIMARY KEY("idPromocion" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Atracciones" (
	"idAtraccion"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"tiempoParaRealizarla"	REAL,
	"visitantes"	INTEGER,
	"cupoPersonas"	INTEGER,
	"idTipoDeAtraccion"	INTEGER,
	PRIMARY KEY("idAtraccion" AUTOINCREMENT),
	FOREIGN KEY("idTipoDeAtraccion") REFERENCES "TipoDeAtraccion"("idTipoDeAtraccion")
);
CREATE TABLE IF NOT EXISTS "Usuarios" (
	"idUsuario"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"presupuesto"	INTEGER,
	"tiempoDisponible"	INTEGER,
	"idTipoDeAtraccion"	INTEGER,
	PRIMARY KEY("idUsuario" AUTOINCREMENT),
	FOREIGN KEY("idTipoDeAtraccion") REFERENCES "TipoDeAtraccion"("idTipoDeAtraccion")
);
CREATE TABLE IF NOT EXISTS "Itinerarios" (
	"idItinerario"	INTEGER NOT NULL UNIQUE,
	"idUsuario"	INTEGER NOT NULL,
	PRIMARY KEY("idItinerario" AUTOINCREMENT),
	FOREIGN KEY("idUsuario") REFERENCES "Usuarios"("idUsuario")
);
CREATE TABLE IF NOT EXISTS "PromocionAXB" (
	"idPromocionAXB"	INTEGER NOT NULL UNIQUE,
	"idPromocion"	INTEGER NOT NULL,
	PRIMARY KEY("idPromocionAXB" AUTOINCREMENT),
	FOREIGN KEY("idPromocion") REFERENCES "Promociones"("idPromocion")
);
CREATE TABLE IF NOT EXISTS "PromocionAbsoluta" (
	"idPromocionAbsoluta"	INTEGER NOT NULL UNIQUE,
	"idPromocion"	INTEGER NOT NULL,
	"descuento"	INTEGER,
	PRIMARY KEY("idPromocionAbsoluta" AUTOINCREMENT),
	FOREIGN KEY("idPromocion") REFERENCES "Promociones"("idPromocion")
);
CREATE TABLE IF NOT EXISTS "PromocionPorcentual" (
	"idPromocionPorcentual"	INTEGER NOT NULL UNIQUE,
	"idPromocion"	INTEGER NOT NULL,
	"porcentuajeDeDescuento"	REAL,
	PRIMARY KEY("idPromocionPorcentual" AUTOINCREMENT),
	FOREIGN KEY("idPromocion") REFERENCES "Promociones"("idPromocion")
);
CREATE TABLE IF NOT EXISTS "AtraccionesDePromociones" (
	"idAtraccionDePromocion"	INTEGER NOT NULL UNIQUE,
	"idPromocion"	INTEGER NOT NULL,
	"idAtraccion"	INTEGER NOT NULL,
	"promocionNoGeneral"	INTEGER,
	PRIMARY KEY("idAtraccionDePromocion" AUTOINCREMENT),
	FOREIGN KEY("idAtraccion") REFERENCES "Atracciones"("idAtraccion")
);
CREATE TABLE IF NOT EXISTS "AtraccionesDeItinerario" (
	"idAtraccionDeItinerario"	INTEGER NOT NULL UNIQUE,
	"idItinerario"	INTEGER NOT NULL,
	"idAtraccion"	INTEGER NOT NULL,
	PRIMARY KEY("idAtraccionDeItinerario" AUTOINCREMENT),
	FOREIGN KEY("idItinerario") REFERENCES "Itinerarios"("idItinerario"),
	FOREIGN KEY("idAtraccion") REFERENCES "Atracciones"("idAtraccion")
);
CREATE TABLE IF NOT EXISTS "PromocionesDeItinerarios" (
	"idPromocionDeItinerario"	INTEGER NOT NULL UNIQUE,
	"idItinerario"	INTEGER NOT NULL,
	"idPromocion"	INTEGER NOT NULL,
	PRIMARY KEY("idPromocionDeItinerario" AUTOINCREMENT),
	FOREIGN KEY("idPromocion") REFERENCES "Promociones"("idPromocion"),
	FOREIGN KEY("idItinerario") REFERENCES "Itinerarios"("idItinerario")
);
COMMIT;
