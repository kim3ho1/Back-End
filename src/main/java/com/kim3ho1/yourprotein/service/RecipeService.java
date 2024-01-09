package com.kim3ho1.yourprotein.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim3ho1.yourprotein.domain.Recipe;
import com.kim3ho1.yourprotein.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {
	@Value("${api.recipe.key}")
	private String apiKey;
	@Value("${api.recipe.url}")
	private String apiUrl;
	private final RecipeRepository recipeRepository;
	@Transactional
	public void crawlingRecipe() {
		int count = 100;

		System.out.println("=========================");
		try {
			String url = String.format(apiUrl, apiKey, count);

			System.out.println(url);

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonResponse = objectMapper.readTree(response.toString());
				JsonNode rows = jsonResponse.get("COOKRCP01").get("row");

				for (int i = 0; i < count; i++) {
					String images = "";
					String manual = "";

					JsonNode recipe = rows.get(i);


					for (int j = 1; j <= 20; j++) {
						String num = j < 10 ? "0" + String.valueOf(j) : String.valueOf(j);
						String img = recipe.get(String.format("MANUAL_IMG%s", num)).asText().equals("") ?
							"" : recipe.get(String.format("MANUAL_IMG%s", num)).asText()+"\n";

						images += img;

						String tmp = recipe.get(String.format("MANUAL%s", num)).asText().equals("") ?
							"" : recipe.get(String.format("MANUAL%s", num)).asText()+"\n";

						manual += tmp;
					}


					Recipe recipeEntity = Recipe.builder()
						.recipeName(recipe.get("RCP_NM").asText())
						.details(recipe.get("RCP_PARTS_DTLS").asText())
						.kcal(recipe.get("INFO_ENG").asText())
						.carbo(recipe.get("INFO_CAR").asText())
						.protein(recipe.get("INFO_PRO").asText())
						.fat(recipe.get("INFO_FAT").asText())
						.na(recipe.get("INFO_NA").asText())
						.kind(recipe.get("RCP_PAT2").asText())
						.images(images)
						.manual(manual)
						.build();

					recipeRepository.save(recipeEntity);
					System.out.println(recipeEntity);

				}
			} else {
				System.out.println("HTTP request failed with response code: " + responseCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
