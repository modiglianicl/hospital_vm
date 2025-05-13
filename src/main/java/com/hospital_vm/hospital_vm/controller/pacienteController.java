package com.hospital_vm.hospital_vm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.hospital_vm.model.Paciente;
import com.hospital_vm.hospital_vm.service.pacienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("api/v1/pacientes")
public class pacienteController {


    @Autowired
    pacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getPacientes() {
        
        try {
            List<Paciente> pacientes = pacienteService.obtenerPacientes();
            if(pacientes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pacientes); 
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<?> addPaciente(@RequestBody Paciente nuevoPaciente) {
        
        try {
            pacienteService.crearPaciente(nuevoPaciente);
            return new ResponseEntity<>(nuevoPaciente,HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
        }
    }

    @PutMapping("/{rut}")
    public ResponseEntity<?> updatePaciente(@RequestBody Paciente pacienteaActualizar, @PathVariable String rut) {
        try {
            Paciente pacienteActualizado = pacienteService.actualizarPaciente(pacienteaActualizar,rut);
            return new ResponseEntity<>(pacienteActualizado,HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Paciente> obtenerPacientePorRut(@PathVariable String rut) {
        try {
            Paciente pacienteEntregar = pacienteService.obtenerPacientePorRut(rut);
            return ResponseEntity.ok(pacienteEntregar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<?> borrarPaciente(@PathVariable String rut){
        try {
            pacienteService.obtenerPacientePorRut(rut); // Si no existe lanza error
            pacienteService.borrarPacientePorRur(rut);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

    @PatchMapping("/parchar/{rut}")
    public ResponseEntity<?> parcharPaciente(@PathVariable String rut , @RequestBody Paciente paciente){
        try {
            Paciente pacienteParchar = pacienteService.parcharPaciente(rut, paciente);
            return ResponseEntity.ok(pacienteParchar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    
    

}
