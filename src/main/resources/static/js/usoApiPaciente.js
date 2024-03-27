window.addEventListener('load', function(){
    let formRegistrar = document.querySelector(".registrarForm");
    let formBuscar = document.querySelector(".buscarForm");
    let formBorrar = document.querySelector(".formularioBorrar");
    let formModificar = document.querySelector(".formularioModificar");
    let btnMostrar = document.querySelector(".mostrarPacientes");
    let mostrarMensajeEnList = document.querySelector(".lugarParaMostrarPacientes");

    let url = "http://localhost:8081/pacientes";

    // Registrar ENVIO
    formRegistrar.addEventListener('submit', function(event) {
        event.preventDefault(); 
        registrarPaciente();
    });

    formBuscar.addEventListener('submit', function(event){
        event.preventDefault();
        let id = document.querySelector("#idBuscar").value;
        if (id) {
            buscarPaciente(id);
        } else {
            alert("Por favor, ingrese un ID antes de buscar.");
        }
    });

    btnMostrar.addEventListener('click', ()=>{
        listarPacientes();
    })

    formBorrar.addEventListener('submit', function(event){
        event.preventDefault()
        let id = document.querySelector("#idBorrar").value;
        if (id){
            eliminarPaciente(id)
        }else{
            alert("Ingrese el id, para poder borrar el paciente.")
        }
    })

    formModificar.addEventListener('submit', (e) => {
        e.preventDefault();
        modificarPaciente();
    });




    // FUNCIONES

    function registrarPaciente(){
        let valorNombre = document.querySelector("#nombreRegistrar").value;
        let valorApellido = document.querySelector("#apellidoRegistrar").value;
        let valorDni = document.querySelector("#dniRegistrar").value;
        let valorFechaIngreso = document.querySelector("#fechaIngresoRegistrar").value;
        let valorCalle = document.querySelector("#calleRegistrar").value;
        let valorNumero = document.querySelector("#numeroRegistrar").value;
        let valorLocalidad = document.querySelector("#localidadRegistrar").value;
        let valorProvincia = document.querySelector("#provinciaRegistrar").value;

        let data = {
            nombre: valorNombre,
            apellido: valorApellido,
            dni: valorDni,
            fechaIngreso: valorFechaIngreso,
            domicilio: {
                calle: valorCalle,
                numero: valorNumero,
                localidad: valorLocalidad,
                provincia: valorProvincia
            }
        };

        let settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        fetch(`${url}/registrar`, settings)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al registrar el paciente');
            }
        })
        .then(data => {
            console.log("El paciente se registró exitosamente:", data);
            mostrarDatosPaciente(data, formRegistrar);
            mostrarMensajeExito(formRegistrar); 
            return data; 
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensajeError(formRegistrar);
            throw error; 
        });
    }

    function buscarPaciente(id){
        fetch(`${url}/buscar/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al buscar el paciente');
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                console.log(`El paciente que busca es:`, data);
                mostrarDatosPaciente(data, formBuscar);
            } else {
                console.log('No se encontró ningún paciente con el ID proporcionado.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensajeError(formBuscar);
        });
    }  


function listarPacientes(){
    fetch(`${url}/listar`)
    .then(res => res.json())
    .then(data => {
        if (Array.isArray(data)) {
            data.forEach(paciente => {
                mostrarDatosPaciente(paciente, mostrarMensajeEnList);
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

function eliminarPaciente(id){
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
            throw new Error('Error al eliminar el paciente');
        }
    })
    .then(data => {
        console.log('El paciente no pudo ser eliminado', data);
        mostrarMensajeError(formBorrar); // Mostrar mensaje de éxito
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarMensajeExito(formBorrar); 
    });
}


function modificarPaciente(){
    let valorIdModificar = document.querySelector("#idModificar").value
    let nuevoValorNombre = document.querySelector("#nombreModificar").value;
    let nuevoValorApellido = document.querySelector("#apellidoModificar").value;
    let nuevoValorDni = document.querySelector("#dniModificar").value;
    let nuevoValorFechaIngreso = document.querySelector("#fechaIngresoModificar").value;
    let nuevoValorCalle = document.querySelector("#calleModificar").value;
    let nuevoValorNumero = document.querySelector("#numeroModificar").value;
    let nuevoValorLocalidad = document.querySelector("#localidadModificar").value;
    let nuevoValorProvincia = document.querySelector("#provinciaModificar").value;

    let data = {
        nombre: nuevoValorNombre,
        apellido: nuevoValorApellido,
        dni: nuevoValorDni,
        fechaIngreso: nuevoValorFechaIngreso,
        domicilio: {
            calle: nuevoValorCalle,
            numero: nuevoValorNumero,
            localidad: nuevoValorLocalidad,
            provincia: nuevoValorProvincia
        }
    };

    let settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    fetch(`${url}/actualizar/${valorIdModificar}`, settings)
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al modificar el odontologo');
        }
    })
    .then(pacienteActualizado =>{
        console.log('Paciente modificado exitosamente:', pacienteActualizado);
        mostrarDatosPaciente(pacienteActualizado, formModificar);
        mostrarMensajeExito(formModificar);
    })
    .catch(err => {
        console.log(err);
        mostrarMensajeError(formModificar);
    })



}

















    function mostrarDatosPaciente(paciente, form) {
        let divDatosPaciente = document.createElement('div');
        divDatosPaciente.innerHTML = `
            <h3>Datos del paciente registrado:</h3>
            <p>ID: ${paciente.id}</p>
            <p>Nombre: ${paciente.nombre}</p>
            <p>Apellido: ${paciente.apellido}</p>
            <p>DNI: ${paciente.dni}</p>
            <p>Fecha de Ingreso: ${paciente.fechaIngreso}</p>
            <p>Calle: ${paciente.domicilioSalidaDto.calle}</p>
            <p>Numero: ${paciente.domicilioSalidaDto.numero}</p>
            <p>Localidad: ${paciente.domicilioSalidaDto.localidad}</p>
            <p>Provincia: ${paciente.domicilioSalidaDto.provincia}</p>
        `;
        form.appendChild(divDatosPaciente);
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
});
