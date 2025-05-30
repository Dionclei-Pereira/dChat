package me.dionclei.dchat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
		
		@NotBlank(message = "Name is required")
		String name,

		@NotBlank(message = "Password is required")
		@Size(min = 6, max = 16, message = "password must be between 6 and 16")
		String password
		)
	{
}
