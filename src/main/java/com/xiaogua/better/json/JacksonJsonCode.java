package com.xiaogua.better.json;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

public class JacksonJsonCode {
	private static ObjectMapper mapper = new ObjectMapper();

	public static JsonNode readAsTree(String filePath) throws Exception {
		JsonNode jsonNode = mapper.readTree(Files.newInputStream(Paths.get(filePath)));
		return jsonNode;
	}

	public static void writeJsonToFile(JsonNode rootNode, String destFile) throws Exception {
		SequenceWriter writer = mapper.writerWithDefaultPrettyPrinter()
				.writeValues(Files.newOutputStream(Paths.get(destFile)));
		writer.write(rootNode);
		writer.close();
	}

	public static void printJsonNodeFileldName(JsonNode jsonNode) throws Exception {
		Iterator<String> fieldNames = jsonNode.fieldNames();
		while (fieldNames.hasNext()) {
			String fieldName = fieldNames.next();
			System.out.println(fieldName);
		}
	}
}
