# Gestión de Hospital

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Descripcióndelsistema">Descripción del sistema</a></li>
    <li><a href="#Rolesdeusuario">Roles de usuario</a></li>
    <li><a href="#Entornotecnológico">Entorno tecnológico</a></li>
  </ol>
</details>

<!-- Descripción del sistema -->
## Descripción del sistema
<a name="Descripcióndelsistema"></a>
La aplicación implementa un servicio informático de gestión de un hospital para digitalizar la gestión del mismo. 
Forman parte de la aplicación distintas interfaces que permiten a los diferentes usuarios (medico, administrador y pacientes, estos últimos de una forma pasiva) interactuar, modificar, visualizar e introducir datos para la coordinación digital del propio hospital. 

Los usuarios podrán trabajar con distintas ventanas, separando las distintas funcionalidades que intervienen en la aplicación. Es decir, un médico, evidentemente, no podrá trabajar en el mismo ámbito de la aplicación que un administrativo, aunque compartan los datos sobre los que trabajan. De la misma manera, un administrativo no podrá editar los datos de la misma forma que lo hará un médico. 

Como hemos mencionado antes, un paciente tiene un rol pasivo. Significa esto que un paciente no puede interactuar directamente con la aplicación. Este es una representación de los pacientes que acuden al hospital. En el mundo analógico un paciente sería lo equivalente a la ficha del mismo.

El sistema trabaja sobre una base de datos relacional, en la que se recogen los valores de la aplicación, introducidos por los usuarios, en distintas tablas, y de la misma manera, en distintas columnas

<!-- Roles de usuario -->
## Roles de usuario
<a name="Rolesdeusuario"></a>
* Un **médico**: puede realizar las distintas acciones:
   - Intervenir en la creación de citas.
   - Comprobar su calendario de vacunas
   - Modificar datos de una cita.
   - Comprobar las distintas citas para un día
   - Asignar una prescripción médica o un medicamento después de que un paciente haya acudido a una cita, detallando qué medicamentos debe consumir o las             recomendaciones pertinentes.
   - Ver los historiales médicos de los diferentes pacientes.
   - Asignar varias causas por las que un paciente acude a la cita.

* Un **administrativo**: puede realizar las distintas acciones:
   -  Asignar vacaciones en función de las jornadas laborales ya asignadas.
   -	Asignar jornadas laborales.
   -	Indicar si una cita es urgente o no.
   -	Crear una cita con un paciente y los diferentes miembros o especialistas de la plantilla.
   -	Ver el historial del paciente asignado a una cita.
 
*	**Paciente**: como hemos dicho, un paciente tiene un rol pasivo. No puede tomar partido en la aplicación, pero creemos conveniente incluirlo en los roles de la aplicación.

<!-- Entorno tecnológico -->
## Entorno tecnológico
<a name="Entornotecnológico"></a>
El sistema ha sido desarrollado en Java, más concretamente SE13. Para su desarrollo hemos utilizado la IDE Eclipse, en su versión 2020-09. 
Hemos utilizado plugins y librerías tales como WindowBuilder, HSQLDB.jar, mail.jar, jcalendar-1.1.4.jar y activation-1.1.jar.

*	**WindowBuilder** : Este plugin, proporcionado por Eclipse, nos permite, utilizando la librería nativa de java SWING, crear unas interfaces de usuario de una manera “sencilla” y visual. Los componentes de la interfaz se pueden gestionar a través de una previsualización de la ventana, arrastrándolos, borrándolos y modificando tamaño, color, fondo, letra...
*	**Mail.jar**: Esta librería se usa, básicamente, para enviar un correo electrónico en el caso de que en la creación de una cita se marque como urgente. Dentro del correo habrá información clave sobre la cita y el paciente (fecha, hora, nombre del paciente, información de contacto, etc.).
*	**Activation-1.1.jar**: Esta es una librería para complementar el envío de los emails.
*	**JCalendar-1.1.4.jar**: Esta librería se usa en el caso de que un administrativo quiera asignarle vacaciones a un médico o a un enfermero. Para esta asignación se abrirá una ventana, con el calendario y se podrán seleccionar todos los días que se deseen siempre que sean posteriores al momento actual.
*	**HSQLDB.jar**: Esta librería interviene en la comunicación de nuestra aplicación con la base de datos. Nos permite introducir valores, modificarlos y eliminar valores.

Hemos intentado diferenciar distintas capas de implementación en el proyecto. Se componen de la siguiente manera:
* **Capa de Interfaz** de Usuario (UI): Esta capa recoge toda la implementación de la Interfaz gráfica. Solo muestra datos o los recoge, pero no los comprueba o modifica directamente.
*	**Capa de Negocio**: Las comprobaciones de datos y la integridad de la aplicación se gestionan en esta capa. Comunica la capa de negocio y la capa de base de datos y se asegura de que todos los valores que pasan a través sean coherentes y funcionales con la aplicación.
*	**Capa de base de datos**: Se limita a introducir o extraer datos y o valores de la aplicación. Al igual que la capa de interfaz no hace comprobaciones de ningún tipo, no se ocupa de la integridad de la aplicación.

Respecto a la base de datos, hemos usado SQuirreL, que nos facilitaba la carga de datos y el acceso a los mismos


