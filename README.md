<h1>Descripción del Proyecto</h1>

<p>
    Este proyecto es un <strong>manejador backend</strong> que gestiona tres entidades principales: <strong>Paciente</strong>, <strong>Odontólogo</strong> y <strong>Turno</strong>. Además, se incluye la entidad <strong>Domicilio</strong>, que está directamente asociada a <strong>Paciente</strong>. Con estas entidades, se puede llevar a cabo un <strong>CRUD completo</strong>.
</p>

<p>
    El desarrollo se realizó utilizando <strong>Java</strong> con <strong>Spring</strong> y <strong>Spring Boot</strong>, así como <strong>Maven</strong> y <strong>Hibernate</strong>. Para la gestión de la base de datos, se utiliza <strong>H2 Database</strong>, mientras que la documentación se genera a través de <strong>Swagger</strong>.
</p>

<p>
    Este proyecto está basado en el patrón de diseño <strong>MVC</strong> (Modelo-Vista-Controlador), y también implementa el patrón <strong>DTO</strong> (Data Transfer Object) utilizando <strong>JPA</strong>. Para la interacción con el ORM, se emplea <strong>ModelMapper</strong>.
</p>

<h2>Tecnologías Utilizadas</h2>
<ul>
    <li>Java</li>
    <li>Spring Framework</li>
    <li>Spring Boot</li>
    <li>Maven</li>
    <li>Hibernate</li>
    <li>H2 Database</li>
    <li>Swagger</li>
</ul>

<h2>Entidades</h2>
<ul>
    <li><strong>Paciente</strong></li>
    <li><strong>Odontólogo</strong></li>
    <li><strong>Turno</strong></li>
    <li><strong>Domicilio</strong> (ligado a Paciente)</li>
</ul>

<h2>Funcionalidades</h2>
<p>
    Este sistema permite realizar operaciones de creación, lectura, actualización y eliminación (CRUD) para las entidades mencionadas, facilitando la gestión de pacientes y odontólogos en un entorno organizado y eficiente.
</p>
<h2>Instrucciones para Correr el Programa en Local</h2>

<ol>
    <li>
        Puedes descargar el proyecto como archivo .zip desde GitHub. Para ello, haz clic en el botón <strong>Code</strong> y selecciona la opción <strong>Download ZIP</strong>.
    </li>
    <li>
        Alternativamente, puedes clonar el repositorio utilizando el siguiente comando:
        <pre><code>git clone https://github.com/NahuelSantos98/Java-Backend-odontologia.git</code></pre>
    </li>
    <li>
        Abre el proyecto en tu IDE de preferencia. Si utilizas <strong>IntelliJ IDEA</strong>, sigue estos pasos:
        <ul>
            <li>Ve a la terminal del IDE y navega al directorio del proyecto con el siguiente comando:</li>
            <pre><code>cd ./com.backend.OdontologiaBackend</code></pre>
            <li>Una vez dentro del directorio, localiza el archivo <strong>OdontologiaBackendApplication</strong> y haz clic en el botón <strong>Play</strong> para ejecutarlo.</li>
        </ul>
    </li>
    <li>
        Finalmente, abre tu navegador preferido y accede a la siguiente URL:
        <pre><code>http://localhost:63342/Java-Backend-odontologia-master/clinica-odontologica/static/index.html?_ijt=t5acp945pbjl79l5p67pgnvf7c&_ij_reload=RELOAD_ON_SAVE</code></pre>
    </li>
</ol>
