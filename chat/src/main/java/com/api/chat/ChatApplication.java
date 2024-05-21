package com.api.chat;

import com.api.chat.annotations.Author;
import com.api.chat.annotations.Authors;
import com.api.chat.annotations.Creator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ChatApplication.class, args);
	}

}
