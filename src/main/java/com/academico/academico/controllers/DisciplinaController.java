package com.academico.academico.controllers;

import com.academico.academico.dtos.DisciplinaRecordDto;
import com.academico.academico.models.Disciplina;
import com.academico.academico.repositories.DisciplinaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @PostMapping("/cadastrar")
    ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody DisciplinaRecordDto disciplinaRecordDto) {
        Disciplina disciplina = new Disciplina();
        BeanUtils.copyProperties(disciplinaRecordDto, disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaRepository.save(disciplina));
    }

    @PutMapping("/{id}")
    public Disciplina atualizarDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplina.setId(id);
        return disciplinaRepository.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public void removerDisciplina(@PathVariable Long id) {
        disciplinaRepository.deleteById(id);
    }
}
