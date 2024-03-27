window.addEventListener('load', function(){
    let formRegistrar = document.querySelector(".registrarForm");
    let formBuscar = document.querySelector(".buscarForm")
    let formBorrar = document.querySelector(".formularioBorrar")
    let formModificar = document.querySelector(".formularioModificar")
    let btnMostrar = document.querySelector(".mostrarTurnos")
    let mostrarMensajeEnList = document.querySelector(".listar")

    let url = "http://localhost:8081/turnos";

    //Registrar ENVIO
    formRegistrar.addEventListener('submit', (e)=>{
        e.preventDefault();
        registrarTurno()
    })

    formBuscar.addEventListener('submit', function(event){
        event.preventDefault();
        let id = document.querySelector("#idBuscar").value;
        if (id) {
            buscarTurno(id);
        } else {
            alert("Por favor, ingrese un ID antes de buscar.");
        }
    });

    btnMostrar.addEventListener('click', ()=>{
        listarTurnos()
    })

        //Eliminar ACCION
        formBorrar.addEventListener('submit', function(event){
            event.preventDefault()
            let id = document.querySelector("#idBorrar").value;
            if (id){
                eliminarTurno(id)
            }else{
                alert("Ingrese el id, para poder borrar el Turno.")
            }
        })

    //Modificar ACCION
    formModificar.addEventListener('submit', (e) => {
        e.preventDefault();
        modificarTurno();
    });




    function registrarTurno(){
        let valorIdPaciente = document.querySelector("#idPacienteRegistrar").value;
        let valorIdOdontologo = document.querySelector("#idOdontologoRegistrar").value;
        let valorFechaYHoraTurno = document.querySelector("#fechaYHoraRegistrar").value;
    
        let data = {
            pacienteId : valorIdPaciente,
            odontologoId : valorIdOdontologo,
            fechaYHora : valorFechaYHoraTurno
        }
    
        let settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }
    
        fetch(`${url}/registrar`, settings)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al registrar el turno');
            }
        })
        .then(data => {
            console.log("El turno se registró exitosamente:", data);
            mostrarDatosTurno(data, formRegistrar);
            mostrarMensajeExito(formRegistrar); 
            return data; 
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensajeError(formRegistrar);
            throw error; 
        });
    }

    function buscarTurno(id){

        fetch(`${url}/buscar/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al buscar el turno');
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                console.log(`El turno que busca es:`, data);
                mostrarDatosTurno(data, formBuscar);
            } else {
                console.log('No se encontró ningún turno con el ID proporcionado.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensajeError(formBuscar);
        })
    }


function listarTurnos(){
    fetch(`${url}/listar`)
    .then(res => res.json())
    .then(data => {
        if (Array.isArray(data)) {
            data.forEach(turno => {
                mostrarDatosTurno(turno, mostrarMensajeEnList);
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

function eliminarTurno(id){
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
            throw new Error('Error al eliminar el turno');
        }
    })
    .then(data => {
        console.log('El turno no pudo ser eliminado', data);
        mostrarMensajeError(formBorrar); // Mostrar mensaje de éxito
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarMensajeExito(formBorrar); // Mostrar mensaje de error
    });
}


function modificarTurno(){
    let idModificar = document.querySelector("#idModificar");
    let idPacienteModificar = document.querySelector("#idPacienteModificar");
    let idOdontologoModificar = document.querySelector("#idOdontologoModificar");
    let fechaYHoraModificar = document.querySelector("#fechaYHoraModificar");

    let idDelTurnoParaModificar = idModificar.value;
    let nuevoValorPaciente = idPacienteModificar.value;
    let nuevoValorOdontologo = idOdontologoModificar.value;
    let nuevoValorFechaYHora = fechaYHoraModificar.value;

    let data = {
        pacienteId : nuevoValorPaciente,
        odontologoId :  nuevoValorOdontologo,
        fechaYHora : nuevoValorFechaYHora
    };

    let settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    fetch(`${url}/actualizar/${idDelTurnoParaModificar}`, settings)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al modificar el turno');
            }
        })
        .then(turnoActualizado =>{
            console.log('Turno modificado exitosamente:', turnoActualizado);
            mostrarDatosTurno(turnoActualizado, formModificar);
            mostrarMensajeExito(formModificar);
        })
        .catch(err => {
            console.log(err);
            mostrarMensajeError(formModificar);
        })
}






    function mostrarDatosTurno(turno, form) {
        let divDatosTurno = document.createElement('div');
        divDatosTurno.innerHTML = `
            <h3>Datos del Turno registrado:</h3>
            <p>ID: ${turno.id}</p>
            <p>Nombre Odontologo: ${turno.odontologoSalidaDto.nombre + " " + turno.odontologoSalidaDto.apellido} </p>
            <p>Nombre Paciente: ${turno.pacienteSalidaDto.nombre + " " + turno.pacienteSalidaDto.apellido}</p>
            <p>Fecha y hora: ${turno.fechaYHora}</p>
        `;
        form.appendChild(divDatosTurno);
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





