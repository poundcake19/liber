package com.marshmallowswisdom.liber.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/articles/{articleId}/versions")
public class ArticleVersionController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveVersion() {
		final ModelAndView mav = new ModelAndView();
		return mav;
	}

}
