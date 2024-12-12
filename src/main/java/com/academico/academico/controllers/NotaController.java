package com.academico.academico.controllers;

import com.academico.academico.dtos.NotaRecordDto;
import com.academico.academico.models.Nota;
import com.academico.academico.repositories.NotaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notas")
public class NotaController {
    @Autowired
    NotaRepository notaRepository;

    @GetMapping
    public List<Nota> listarNotas(){
        return notaRepository.findAll();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Nota> cadastrarNota(@RequestBody NotaRecordDto notaRecordDto){
        Nota nota = new Nota();
        BeanUtils.copyProperties(notaRecordDto, nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaRepository.save(nota));
    }

    @PutMapping("/{id}")
    public Nota atualizarNota(@PathVariable Long id, @RequestBody Nota nota){
        nota.setId(id);
        return notaRepository.save(nota);
    }

    @DeleteMapping("/{id}")
    public void removerNota(@PathVariable Long id){
        notaRepository.deleteById(id);
    }
}
