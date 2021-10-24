SELECT "Usuarios".*, "Itinerarios".*
FROM "Itinerarios"
INNER JOIN "Usuarios" USING ("idUsuario")
--ON "Usuarios".idUsuario = "Itinerarios".idUsuario