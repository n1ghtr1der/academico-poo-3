package com.academico.academico.controllers;

import com.academico.academico.dtos.TurmaRecordDto;
import com.academico.academico.models.Turma;
import com.academico.academico.repositories.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
public class TurmaController {
    @Autowired
    TurmaRepository turmaRepository;

    @GetMapping
    public List<Turma> listarTurmas(){
        return turmaRepository.findAll();
    }

//    @PostMapping
//    public Turma cadastrarTurma(@RequestBody Turma turma){
//        return turmaRepository.save(turma);
//    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Turma> adicionarTurma(@RequestBody TurmaRecordDto turmaRecordDto){
        var novaTurma = new Turma();
        BeanUtils.copyProperties(turmaRecordDto, novaTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaRepository.save(novaTurma));
    }

    @PutMapping("/{id}")
    public Turma atualizarTurma(@PathVariable Long id, @RequestBody Turma turma){
        turma.setId(id);
        return turmaRepository.save(turma);
    }

    @DeleteMapping("/{id}")
    public void removerTurma(@PathVariable Long id){
        turmaRepository.deleteById(id);
    }
}
