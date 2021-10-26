SELECT * 
FROM "Promociones"
JOIN "AtraccionesDePromociones" ON "AtraccionesDePromociones".idPromocion = "Promociones".idPromocion
ORDER by idPromocion