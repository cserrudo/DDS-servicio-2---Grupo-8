# servicio-grupo-8

## Pasos
1. Ejecutar `main.java`.
2. Ir a `http://localhost:8080/swagger` para visualizar los 2 endpoints disponibles y su documentación.
3. Importar la colección `Ej.postman_comunidad_y_usuarios` en Postman para ver 4 ejemplos de uso de los endpoints (usuario con apertura fraudulenta, cierre fraudulento, apertura/cierre normal y comunidad) .


Este servicio calcula el grado de confianza de usuarios y comunidades. El grado de confianza se mide en No confiable, Con reservas, Confiable Nivel 1 y Confiable Nivel 2.
Todos los usuarios inician con 5 puntos de confianza y podrán modificar los puntajes a partir de las siguientes condiciones:
Si entre la apertura de un incidente por cualquier usuario y su cierre por el usuario analizador existen menos de 3 minutos, se descontarán 0,2 puntos por cada incidente, considerando que se realizó una apertura fraudulenta.
Si entre el cierre de un incidente realizado por el usuario y la apertura de uno similar realizado por cualquier usuario existen menos de 3 minutos, se descontarán 0,2 puntos por cada incidente, estimando que se realizó un cierre fraudulento.
Si el usuario abrió un incidente o cerró un incidente durante la semana, excepto en las situaciones del punto anterior, suma 0,5 puntos por semana.
El grado de confianza de un usuario se actualiza todos los domingos a las 13 h. Esto quiere decir que durante toda la semana el usuario posee igual grado de confianza.
El grado de confianza de una comunidad es el promedio del grado de confianza de un usuario y descuenta 0,2 puntos por cada usuario "Con reservas".
El grado de confianza de una comunidad se calcula luego de haber finalizado el cálculo del grado de confianza de todos los usuarios.
Para la asignación de grado de confianza se considerarán las siguientes relaciones con los puntos: si el usuario tiene menos de 2 puntos se considera No confiable. Si tiene entre 2 y 3, ambos inclusive, se considera Con reservas. Si tiene entre 3 y 5 se considera Confiable Nivel 1 y más de 5, Confiable Nivel 2.
Aquellos usuarios y comunidades considerados “No confiables” serán inactivados.
