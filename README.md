# Trabajo 2

* Si hizo alguno de estos puntos coloquele [Hecho], por favor no hagamos caso omiso a algún requerimiento, el los puso porque son duros, no lo hagamos como sea más facil.

* Si le va agregar algo que no esté dentro de los requerimientos hablarlo como grupo y ya procedemos a consultar con el profesor.

## Ventana Inicial

* Brindar un saludo de bienvenida al sistema [Hecho]
* Breve hoja de vida de cada desarrollador. Cada hoja de vida cambia por click del ratón sobre la
región del texto de la hoja de vida.[Hecho]
* Validar usuarios registrados y nuevos (opcional: si esta implementado en la práctica 1) e ir a la
siguiente ventana (Ventana Principal de cualquier usuario)  [Hecho]
* Imágenes asociadas al sistema. Se podrán cambiar por evento del ratón al pasar sobre la misma
región de la foto. Presentar 5 imágenes y repetir el ciclo.[Hecho]

Esto debe estar en el formato que el profesor puso

### Nota:
Para su implementación utilizar los controladores Label, TextField, Button y TextArea. Para el manejo de
imágenes usar los elementos asociados a ImageView. [Hecho]
Esta ventana manejará un menú llamado Inicio quien tendrá las opciones de menú:
* Salir de la aplicación [Hecho]
* Descripción del sistema (con esta aparecerá en alguna parte de la ventana de inicio una breve
descripción de lo que hace el sistema) [Hecho]

## Ventana principal de cualquier usuario (administrador, usuario registrado)

### Distribución ventana del usuario

La ventana principal del Usuario deberá estar distribuida como lo indica la figura 1. Debe implementarse
usando Panes anidados y no mediante posiciones fijas. Esta ventana deberá llevar como titulo el nombre del
usuario (zona 0 en rojo de la figura1) si existe la implementación de varios usuarios. Si no irá el nombre de la
aplicación. [Hecho]
La ventana tendrá una zona de menús que permitirán implementar los menús y funcionalidades desarrolladas
en la práctica 1 (zona 1 en rojo de la figura 1). La otra zona es donde se implementarán los componentes de
interfaz que manejan la información necesaria de los procesos o consultas que implementen las
funcionalidades del sistema asociada a cada usuario (zona 2 en rojo de la figura 1). [Hecho pero falta agregar la descripción de cada funcionalidad]

### Menú superior (Zona 1 de la interfaz)
Su estructura será la siguiente:
* Archivo
    * Usuario: Si existe la implementación de varios usuarios, se despliega una ventana de diálogo con la información básica del usuario actual (nombre, cédula, etc). Si no, este menú mostrará el nombre de la aplicación.[Falta]
    * Salir: retorna a la Ventana de Inicio del programa. Si existe la implementación de varios usuarios, esta además de volver a la ventana de inicio, cierra la sesión del usuario actual.[Hecho]
* Procesos y Consultas
    * Listara todos los procesos y consultas que corresponden a ese usuario acorde a la primera práctica. [Hecho]
* Ayuda
    * Acerca de: muestra una ventana de diálogo con los nombres de los autores de la aplicación. Formato libre. [Hecho]
    
    
    

### Zona de interacción usuario (Zona 2 de la interfaz)
La parte inferior de la interfaz mostrará tanto los diálogos de texto (solicitudes de información a los usuarios)
como los resultados de procesos y/o consultas al usuario. [Falta- preguntar]

#### Los diálogos de texto: [Hecho]
En algunas etapas de la aplicación se emplean diálogos que contienen formularios parecidos al mostrado en
la parte inferior de sección 2 de la figura 1. Es decir, una sucesión de campos donde se introducen los valores
solicitados.
Para su implementación se pide implementar este tipo de formulario como un componente genérico. El
componente creado debe cumplir los siguientes requisitos: Se deberá implementar un componente FieldPanel
que herede de Panel para visualizar y gestionar listas de atributo-valor.

#### Muestra de resultados de procesos y/o consultas:[Preguntar]
La parte inferior de la ventana de la aplicación mostrará los resultados de los procesos y las consultas. La lista
(que es de tipo texto) deberá aumentar de tamaño ocupando todo el espacio disponible cuando se
redimensiona la ventana. Se debe considerar además que existen algunos procesos que pueden necesitar de
otras ventanas para representar la actividad. Este tipo de procesos y/o consultas deben considerar en su
nueva ventana, una estructura similar a la ya planteada, donde se presente un menú superior y una lista de
resultados.
##### Nota sobre la zona de componentes: [falta]
Al ingresar a esta ventana por primera vez, se deberá mostrar la
ventana anterior donde el formulario para el ingreso de la información es reemplazado por una “Interfaz de
inicio” que tiene un formato libre y deberá darle información al usuario de cómo usar esta aplicación y que se
puede hacer. Una vez que se ejecute uno de los menús de procesos y consultas desaparece la anterior
interfaz de inicio y aparece lo mostrado en la sección 2 de la figura 1 .

## Requerimientos adicionales

### Manejo de errores [Falta]

Para el manejo de errores se deberá hacer una jerarquía de errores empezando por una clase de error
llamada ErrorAplicacion que es hija de la clase Exception. Es decir existirá la clase ErrorAplicacion y dos
clases de errores que agrupen el manejo de los errores no solo de los descritos hasta ahora sino que se
abarcarán otros 4 tipos de errores que se deberán inventar y que serán lanzados desde algún método. Esta
jerarquía deberá tener en cuenta lo siguiente: Cuando se lance una excepción la clase ErrorAplicacion
aportará la primera parte del mensaje de error: “Manejo de errores de la Aplicación:” y a continuación la clase
particular que pertenecerá a las ramas del árbol de errores adicionará el respectivo complemento del mensaje
de error. Cada una de estas excepciones inventadas hará una rutina deferente de manejo de errores. Las
Excepciones sugeridas a lo largo del numeral 1 (Requerimientos básicos) deberán hacer parte de alguna de
las dos categorías de errores propuestas por ustedes. El árbol de errores quedará así: Ver imagen en documento


