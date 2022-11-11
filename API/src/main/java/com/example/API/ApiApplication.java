package com.example.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class ApiApplication {

	private static final Gson GSON = new GsonBuilder().create();
	private static final HashMap<Integer, Character> DATENBANK = new HashMap<>();

	public static void main(String[] args) {
		DATENBANK.put(0, new Character(0, "Marv", "Jester"));
		DATENBANK.put(1, new Character(1, "Moin", "Meister"));
		DATENBANK.put(2, new Character(2, "Gib", "Geld"));

		SpringApplication.run(ApiApplication.class, args);
	}

	@GetMapping("/characters")
	public String getAllCharacters() {

		return GSON.toJson(DATENBANK);
	}

	@GetMapping("/characters/{id}")
	public String getCharacters(@PathVariable(value="id") String pathVariable) {
		int id;

		try {
			id = Integer.parseInt(pathVariable);
		} catch (NumberFormatException exception) {
			return String.format("%s ist keine natürliche Zahl.", pathVariable);
		}

		if(!DATENBANK.containsKey(id)) {
			return String.format("ID %d nicht gefunden!", id);
		}

		return GSON.toJson(DATENBANK.get(id));
	}

	@GetMapping("/character")
	public String getCharacter(@RequestParam(value = "nameExtern", defaultValue = "1") String requestParam) {
		int id;

		try {
			id = Integer.parseInt(requestParam);
		} catch (NumberFormatException exception) {
			return String.format("%s ist keine natürliche Zahl.", requestParam);
		}

		if(!DATENBANK.containsKey(id)) {
			return String.format("ID %d nicht gefunden!", id);
		}

		return GSON.toJson(DATENBANK.get(id));
	}
}