package com.gdufs.gd.util;

import java.io.IOException;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JacksonUtil {

	public JacksonUtil() {
		super();
	}

	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;

	/**
	 * 創建objectMapper，實例化objectMapper
	 */
	public void init() {
		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 銷毀jsonGenerator
	 */
	public void destory() {
		try {
			if (jsonGenerator != null) {
				jsonGenerator.flush();
			}
			if (!jsonGenerator.isClosed()) {
				jsonGenerator.close();
			}
			jsonGenerator = null;
			objectMapper = null;
			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将java对象转换成json字符串
	 */
	public static String writeEntity2JSON(Object obj) {
		String jsonStr = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonStr = objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 将json字符串转换成JavaBean对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readJson2Entity(String jsonStr, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		Object object = null;
		try {
			object = objectMapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) object;

	}

	/**
	 * json 字符串轉化為對應的類型 获取泛型的Collection Type
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类型
	 */
	public static <T> T readJson(String jsonStr, Class<?> collectionClass,
			Class<?>... elementClasses) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
		return mapper.readValue(jsonStr, javaType);

	}
	
//	/**
//     * 复杂对象的解析-测试用例
//     * 
//     * @throws JsonProcessingException
//     * @throws IOException
//     */
//    @Test
//    public void testParseComplexJson() throws JsonProcessingException, IOException {
//        List<PlazaContent> list = new ArrayList<PlazaContent>();
// 
//        Map<String, Object> json = new HashMap<String, Object>();
//        json.put("msg", "test");
//        json.put("code", "111");
//        Map<String, List<PlazaContent>> map = new HashMap<String, List<PlazaContent>>();
//        for (int i = 0; i < 3; i++) {
//            PlazaContent content = new PlazaContent();
//            content.setType(new Integer(1));
//            content.setAppType(3);
//            content.setContent("苍井空美图" + i);
//            content.setContentUrl("http://xxx/专辑url.jpg");
//            content.setPicUrl("http://xxx/pic_url.jpg");
//            content.setPublishPassport("fastwei@sohu.com");
//            content.setPublishTime(new Date());
//            content.setCount(new Integer(100 + i));
//            content.setCommentCount(new Integer(50 + i));
//            list.add(content);
//        }
// 
//        map.put("fastwei@sohu.com", list);
//        map.put("cangjingkong@sohu.com", list);
// 
//        json.put("data", map);
//        String s = JsonUtils.toJson(json);
// 
//        //可以很好的反序列化为Java的泛型对象,居家必备之良品呀。
//        Map<String, List<PlazaContent>> map2 = JsonUtils.jsonNode2GenericObject(JsonUtils.getNode(s, "data"),
//                new TypeReference<Map<String, List<PlazaContent>>>() {});
//        List<PlazaContent> list2 = map2.get("cangjingkong@sohu.com");
//        System.out.println(list2.get(1).getContent());
// 
//        assertNotNull(s);
//    }
////新添加的两个方法
//    /**
//     * 根据json串和节点名返回节点
//     * 
//     * @param json
//     * @param nodeName
//     * @return
//     */
//    public static JsonNode getNode(String json, String nodeName) {
//        JsonNode node = null;
//        try {
//            node = JsonUtils.getObjectMapper().readTree(json);
//            return node.get(nodeName);
//        } catch (JsonProcessingException e) {
//            log.warn("json error:" + e.getMessage());
//        } catch (IOException e) {
//            log.warn("json error:" + e.getMessage());
//        }
//        return node;
//    }
// 
//    /**
//     * JsonNode转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
//     * 
//     * @param <T>
//     * @param node JsonNode
//     * @param tr TypeReference,例如: new TypeReference< List<FamousUser> >(){}
//     * @return List对象列表
//     */
//    public static <T> T jsonNode2GenericObject(JsonNode node, TypeReference<T> tr) {
// 
//        if (node == null || "".equals(node)) {
//            return null;
//        } else {
//            try {
//                return (T) objectMapper.readValue(node, tr);
//            } catch (Exception e) {
//                log.warn("json error:" + e.getMessage());
//            }
//        }
//        return null;
//    }
 
	
}
