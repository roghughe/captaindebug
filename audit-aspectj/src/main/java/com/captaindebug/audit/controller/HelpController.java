/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.audit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.captaindebug.audit.aspectj.Audit;

/**
 * Help Controller, displays some help...
 * 
 * @author Roger
 * 
 */
@Controller()
public class HelpController {

	@Audit("Help")  // User has visited the help page
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String showHelp(@RequestParam int pageId, Model model) {

		String help = getHelpPage(pageId);

		model.addAttribute("helpText", help);
		return "help";
	}

	private String getHelpPage(int pageId) {
		return "This is the help text - The Beetles are in Stereo";
	}

}
