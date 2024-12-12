package com.academico.academico.dtos;

import com.academico.academico.models.Aluno;
import com.academico.academico.models.Disciplina;

import java.time.LocalDate;

public record NotaRecordDto(double valor, LocalDate dataAvaliacao) {
}
