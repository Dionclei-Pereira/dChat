package me.dionclei.dchat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
		
		@NotBlank(message = "O nome é obrigatório")
		String name,

		@NotBlank(message = "A senha é obrigatória")
		@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
		String password
		)
	{
}
