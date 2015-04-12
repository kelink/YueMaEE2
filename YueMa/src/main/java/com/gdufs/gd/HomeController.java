package com.gdufs.gd;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.util.JacksonUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			final HttpServletResponse response, final HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		request.getHeader("");
		return "home";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(Locale locale, Model model,
			final HttpServletResponse response) {
		TransferMessage msg = new TransferMessage();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("request", "api");
		map1.put("requests", "kkk");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("request2", "api");
		map2.put("request22", "kkk");
		HashMap<String, HashMap<String, String>> resultMap = new HashMap<String, HashMap<String, String>>();
		resultMap.put("map1", map1);
		resultMap.put("map2", map2);
		msg.setCode("10000");
		msg.setMessage("test");
		msg.setResultMap(resultMap);
		return JacksonUtil.writeEntity2JSON(new String[] { "a", "b" });
	}
}
