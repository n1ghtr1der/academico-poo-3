package com.academico.academico.dtos;

import com.academico.academico.models.Turma;

import java.time.LocalDate;

public record AlunoRecordDto(String nome, String matricula, String email, LocalDate dataNascimento) {
}
