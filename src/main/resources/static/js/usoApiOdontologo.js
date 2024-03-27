window.addEventListener('load', function(){

    //FORMS
    let formRegistrar = document.querySelector(".registrarForm");
    let formBuscar = document.querySelector(".buscarForm")
    let formBorrar = document.querySelector(".formularioBorrar")
    let formModificar = document.querySelector(".formularioModificar")
    let btnMostrar = document.querySelector(".mostrarOdontologos")
    let mostrarMensajeEnList = document.querySelector(".listar")

    //URL
    let url = "http://localhost:8081/odontologos";


//Registrar ENVIO
    formRegistrar.addEventListener('submit', function(event) {
        event.preventDefault(); 
        registrarOdontologo();
    });


//Buscar ENVIO

    formBuscar.addEventListener('submit', function(event){
        event.preventDefault();
        let id = document.querySelector("#idBuscar").value;
        if (id) {
            buscarOdontologo(id);
        } else {
            alert("Por favor, ingrese un ID antes de buscar.");
        }
    });

    //Mostrar ACCION
    btnMostrar.addEventListener('click', ()=>{
        listarOdontologos()
    })

    //Eliminar ACCION
    formBorrar.addEventListener('submit', function(event){
        event.preventDefault()
        let id = document.querySelector("#idBorrar").value;
        if (id){
            eliminarOdontologo(id)
        }else{
            alert("Ingrese el id, para poder borrar el odontologo.")
        }
    })

    //Modificar ACCION
    formModificar.addEventListener('submit', (e) => {
        e.preventDefault();
        modificarOdontologo();
    });



// Registrar FUNCION
function registrarOdontologo() {
    let numeroMatricula = document.querySelector("#numeroMatricula").value;
    let nombre = document.querySelector("#nombreRegistro").value;
    let apellido = document.querySelector("#apellidoRegistro").value;

    let data = {
        numeroMatricula: numeroMatricula,
        nombre: nombre,
        apellido: apellido
    };

    let settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    return fetch(`${url}/registrar`, settings)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al registrar el odontologo');
            }
        })
        .then(data => {
            console.log("El odontólogo se registró exitosamente:", data);
            mostrarDatosOdontologo(data, formRegistrar);
            mostrarMensajeExito(formRegistrar); 
            return data; 
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensajeError(formRegistrar);
            throw error; 
        });
}


//Buscar FUNCION
function buscarOdontologo(id){

    fetch(`${url}/buscar/${id}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al buscar el odontologo');
        }
        return response.json();
    })
    .then(data => {
        if (data) {
            console.log(`El odontólogo que busca es:`, data);
            mostrarDatosOdontologo(data, formBuscar);
        } else {
            console.log('No se encontró ningún odontólogo con el ID proporcionado.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarMensajeError(formBuscar);
    })
}

function listarOdontologos() {
    fetch(`${url}/listar`)
        .then(res => res.json())
        .then(data => {
            if (Array.isArray(data)) {
                data.forEach(odontologo => {
                    mostrarDatosOdontologo(odontologo, mostrarMensajeEnList);
                });
            } else {
                console.error('Error: los datos recibidos no son una lista.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensajeError(mostrarMensajeEnList); 
        });
}


function eliminarOdontologo(id){

    fetch(`${url}/borrar/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al eliminar el odontólogo');
        }
    })
    .then(data => {
        console.log('El odontólogo no pudo ser eliminado', data);
        mostrarMensajeError(formBorrar); // Mostrar mensaje de éxito
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarMensajeExito(formBorrar); // Mostrar mensaje de error
    });
}


function modificarOdontologo(){
    let idModificar = document.querySelector("#idModificar")
    let numeroMatriculaModificar = document.querySelector("#numeroMatriculaModificar")
    let nombreModificar = document.querySelector("#nombreModificar")
    let apellidoModificar = document.querySelector("#apellidoModificar")

    let buscarOdontologoConId = idModificar.value
    let nuevoValorNumeroMatricula = numeroMatriculaModificar.value;
    let nuevoValorNombre = nombreModificar.value;
    let nuevoValorApellido = apellidoModificar.value;

    let data = {
        numeroMatricula: nuevoValorNumeroMatricula,
        nombre: nuevoValorNombre,
        apellido: nuevoValorApellido
    };

    let settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    fetch(`${url}/actualizar/${buscarOdontologoConId}`, settings)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al modificar el odontologo');
            }
        })
        .then(odontologoActualizado =>{
            console.log('Odontólogo modificado exitosamente:', odontologoActualizado);
            mostrarDatosOdontologo(odontologoActualizado, formModificar);
            mostrarMensajeExito(formModificar);
        })
        .catch(err => {
            console.log(err);
            mostrarMensajeError(formModificar);
        })
}


// Mostrar datos del odontólogo en pantalla
function mostrarDatosOdontologo(odontologo, form) {
    let divDatosOdontologo = document.createElement('div');
    divDatosOdontologo.innerHTML = `
        <h3>Datos del odontólogo registrado:</h3>
        <p>ID: ${odontologo.id}</p>
        <p>Número de Matrícula: ${odontologo.numeroMatricula}</p>
        <p>Nombre: ${odontologo.nombre}</p>
        <p>Apellido: ${odontologo.apellido}</p>
    `;
    form.appendChild(divDatosOdontologo);
}


function mostrarMensajeExito(form) {
    let divExito = document.createElement('div');
    divExito.innerHTML = `<p style="color: green;">El proceso fue exitoso.</p>`;
    form.appendChild(divExito);
}

function mostrarMensajeError(form) {
    let divError = document.createElement('div');
    divError.innerHTML = `<p style="color: red;">Hubo un error. Por favor, inténtelo nuevamente.</p>`;
    form.appendChild(divError);
}

})