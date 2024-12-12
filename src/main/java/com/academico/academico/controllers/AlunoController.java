package com.academico.academico.controllers;

import com.academico.academico.dtos.AlunoRecordDto;
import com.academico.academico.models.Aluno;
import com.academico.academico.repositories.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody AlunoRecordDto alunoRecordDto) {
        Aluno aluno = new Aluno();
        BeanUtils.copyProperties(alunoRecordDto, aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoRepository.save(aluno));
    }

    @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void removerAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}
