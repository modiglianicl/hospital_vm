package com.hospital_vm.cl.hospital_vm.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> verPacientes(){
        List<Paciente> pacientes = pacienteService.findAllPacientes();
        System.out.println("Mostrando pacientes...");
        if(pacientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pacientes);
        
    }

    @GetMapping("{id}")
    public ResponseEntity<Paciente> verPacientePorId(@PathVariable int id){
        try {
            Paciente paciente = pacienteService.findPacienteById(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente nuevoPaciente) {
        Paciente pacienteCreado = pacienteService.savePaciente(nuevoPaciente);
        return new ResponseEntity<>(pacienteCreado,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer id,@RequestBody Paciente paciente){
        try {
            Paciente pac = pacienteService.findPacienteById(id);
            pac.setId(id);
            pac.setRut(paciente.getRut());
            pac.setNombre(paciente.getNombre());
            pac.setApellido(paciente.getApellido());
            pac.setFechaNacimiento(paciente.getFechaNacimiento());
            pac.setCorreo(paciente.getCorreo());

            pacienteService.savePaciente(pac);
            return ResponseEntity.ok(pac);
        } catch (Exception e){
            System.out.println("Error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){

        
        try {
            Paciente pacienteEliminar = pacienteService.findPacienteById(id);
            pacienteService.deletePaciente(pacienteEliminar.getId());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
