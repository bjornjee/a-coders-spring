package com.example.acodersspringapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {

	@Id
	private long id;
	private String username;
	private String password;
	private String email;
	private int activated;
}
