package br.sgc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciais para obtenção do JWT")
public record LoginRequest(
        @Schema(description = "E-mail cadastrado", example = "admin@papelaria.com") String email,
        @Schema(description = "Senha em texto claro (somente na requisição)", example = "123456") String password
) {}