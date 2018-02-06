# PrimeFacesArchetype
Este es un proyecto hecho para instalarlo en maven que permite generar una plantilla inicial con login para Primefaces con las librerías más populares que he conocido.

# Instrucciones de Uso
Para instarlo se debe descargar la carpeta y ejecutar el comando "mvn install" en su interior.

Ahora se puede utilizar el siguiente comando maven para generar una nueva plantilla en donde %groupId% corresponde al nombre de la clase Java con que quedará inicializar el proyecto y %artifactId% será el nombre del proyecto.

mvn archetype:generate -DarchetypeGroupId=tcero -DarchetypeArtifactId=crudPrimefaces -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=%groupId% -DartifactId=%artifactId%

También se puede simplificar el paso anterior creando un .bat (para windows) con el siguiente código:

@echo off
set /p artifactId="Nombre del Proyecto: "
set /p groupId="Nombre de la Clase: "
mvn archetype:generate -DarchetypeGroupId=tcero -DarchetypeArtifactId=crudPrimefaces -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=%groupId% -DartifactId=%artifactId%

Una vez creado el proyecto inicial se debe configurar Hibernate para que se realice correctamente la conexión a la base de datos.

Inicialmente, el proyecto está configurado para conectarse a una base de datos en MySql. Para poder verlo funcionando rapidamente con MySql se puede crear un schema en una Base de Datos MySql local con los siguientes comandos en donde se debe reemplazar el "NombreDelProyecto" con el mismo nombre utilizado en el paso anterior para el artifactId:

    create schema NombreDelProyecto;
    use NombreDelProyecto;

Luego, modificar el archivo \src\main\resources\Hibernate\hibernate.cfg.xml con los "UsuarioMySql" y "ClaveMySql" correspondientes:

    <property name="hibernate.connection.username">UsuarioMySql</property>
    <property name="hibernate.connection.password">ClaveMySql</property>
    
Finalmente, se debe incorporar el tag siguiente para que al ejecutar el proyecto se creen las tablas en la Base de Datos MySql. Recordar eliminar esta línea posteriormente para evitar pérdidas al modificar el proyecto.

    <property name="hibernate.hbm2ddl.auto">update</property>
