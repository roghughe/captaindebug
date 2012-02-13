package com.captaindebug.exceptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ImageLoaderController {

	private static final Logger logger = LoggerFactory.getLogger(ImageLoaderController.class);

	private static final int ARRAY_SIZE = 512;

	// TODO Move the image file to somewhere useful before running the app
	@Value("/tmp/images/")
	private String basePath;

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void loadImage(@RequestParam("imageName") String imageName, HttpServletResponse response) throws IOException {

		String fullName = basePath + imageName;
		logger.debug("About to load file: " + fullName);

		OutputStream out = response.getOutputStream();
		InputStream in = new FileInputStream(fullName);

		int read;
		byte[] buffer = new byte[ARRAY_SIZE];
		do {
			read = in.read(buffer);
			if (read != -1) {
				out.write(buffer);
			}

		} while (read != -1);

		in.close();
		out.close();
	}
}
