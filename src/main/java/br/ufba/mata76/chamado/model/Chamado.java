package br.ufba.mata76.chamado.model;

public record Chamado (
    Integer id,
    String titulo,
    String solicitante,
    String setor,
    Prioridade prioridade,
    Status status
){}