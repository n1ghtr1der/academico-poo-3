package com.academico.academico.controllers;

import com.academico.academico.dtos.ProfessorRecordDto;
import com.academico.academico.models.Professor;
import com.academico.academico.repositories.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Professor> listarProfessores(){
        return professorRepository.findAll();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody ProfessorRecordDto professorRecordDto){
        Professor professor = new Professor();
        BeanUtils.copyProperties(professorRecordDto, professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorRepository.save(professor));
    }

    @PutMapping("/{id}")
    public Professor atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor){
        professor.setId(id);
        return professorRepository.save(professor);
    }

    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable Long id){
        professorRepository.deleteById(id);
    }
}
