Feature: Exploración y Visualización de Kudos (Read Model)

  Background:
    Given que el sistema SofkianOS se encuentra operativo
    And el usuario accede a la URL del "Explorar Kudos"

  Scenario Outline: Visualización de Kudos inyectados mediante integración API
    # Carga de datos
    Given que se realiza una petición POST al endpoint de Kudos con:
      | from     | <correo_remitente>   |
      | to       | <correo_destinatario>|
      | category | <categoria>          |
      | message  | <mensaje>           |
    And la respuesta del servicio es exitosa (202 Accepted)

    # Validacion frontend
    When el empleado filtra la lista por la categoría "<categoria>"
    And realiza la búsqueda del destinatario "<correo_destinatario>"
    Then el sistema debe mostrar el registro en el Explorador con el mensaje "<mensaje>"
    And el autor visible debe ser "<correo_remitente>"
    And la marca de tiempo debe corresponder a la fecha actual

    Examples:
      | correo_remitente       | correo_destinatario       | categoria | mensaje                             |
      | frontend@sofkianos.com | christopher@sofkianos.com | Innovation | Mentoría excepcional en Java 21.    |
      | backend@sofkianos.com  | santiago@sofkianos.com    | Passion   | Compromiso total con el Roadmap.    |
      | santiago@sofkianos.com | backend@sofkianos.com     | Teamwork  | Soporte en pruebas de carga MVP.    |