package co.yabx.admin.portal.app.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * 
 * @author Asad.ali
 *
 */

public class DTOConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger(DTOConverter.class);

	public static ObjectMapper objectMapper;

	static {
		JsonFactory factory = new JsonFactory();
		objectMapper = new ObjectMapper(factory);
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospectorPair(secondary, primary);
		objectMapper.setAnnotationIntrospector(pair);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	public static <T> List<T> deserializeEntityList(String response, Class<T> clazz) {
		JsonNode jsonNode = null;
		List<T> dataList = Collections.emptyList();
		try {
			jsonNode = objectMapper.readValue(response, JsonNode.class);
			dataList = deserializeEntityList(jsonNode, clazz);
		} catch (JsonMappingException jme) {
			LOGGER.error("JSON could not be mapped to JSONNode:" + response);
			jme.printStackTrace();
			return Collections.emptyList();
		} catch (JsonParseException jpe) {
			LOGGER.error("Invalid JSON Format:" + response);
			jpe.printStackTrace();
			return Collections.emptyList();
		} catch (IOException e) {
			LOGGER.error("IO Exception while reading JSonNode from response:" + response);
			e.printStackTrace();
			return Collections.emptyList();
		}
		return dataList;
	}

	public static <T> List<T> deserializeEntityList(JsonNode jsonNode, Class<T> type) {
		JsonNode dataNode = jsonNode.get("data");
		if (dataNode == null || dataNode.size() == 0) {
			return new ArrayList<T>();
		}
		List<T> posts = new ArrayList<T>(dataNode.size());
		for (Iterator<JsonNode> iterator = dataNode.iterator(); iterator.hasNext();) {
			T response = deserializeEntity(type, iterator.next());
			if (response != null)
				posts.add(response);
		}
		return posts;
	}

	public static <T> List<T> deserializeEntityListGeneric(Object response, Class<T> clazz) {

		try {
			return deserializeEntityListGeneric(objectMapper.writeValueAsString(response), clazz);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static <T> List<T> deserializeEntityListGeneric(String response, Class<T> clazz) {
		JsonNode jsonNode = null;
		List<T> dataList = Collections.emptyList();
		try {
			jsonNode = objectMapper.readValue(response, JsonNode.class);
			dataList = deserializeEntityListGeneric(jsonNode, clazz);
		} catch (JsonMappingException jme) {
			LOGGER.error("JSON could not be mapped to JSONNode:" + response);
			jme.printStackTrace();
			return Collections.emptyList();
		} catch (JsonParseException jpe) {
			LOGGER.error("Invalid JSON Format:" + response);
			jpe.printStackTrace();
			return Collections.emptyList();
		} catch (IOException e) {
			LOGGER.error("IO Exception while reading JSonNode from response:" + response);
			e.printStackTrace();
			return Collections.emptyList();
		}
		return dataList;
	}

	public static <T> List<T> deserializeEntityListGeneric(JsonNode jsonNode, Class<T> type) {
		if (jsonNode == null || jsonNode.size() == 0) {
			return new ArrayList<T>();
		}
		List<T> posts = new ArrayList<T>(jsonNode.size());
		for (Iterator<JsonNode> iterator = jsonNode.iterator(); iterator.hasNext();) {
			T response = deserializeEntity(type, iterator.next());
			if (response != null)
				posts.add(response);
		}
		return posts;
	}

	public static <T> T deserializeEntity(String responseData, Class<T> clazz) throws IOException {
		T value = objectMapper.readValue(responseData, clazz);
		return value;
	}

	public static <T> T deserializeEntity(Class<T> type, JsonNode node) {
		T value = null;
		try {

			value = objectMapper.convertValue(node, type);

		} finally {
			return value;
		}
	}

}
