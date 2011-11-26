package com.captaindebug.siteproperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.captaindebug.address.Address;

/**
 * Handles requests for the display address page
 */
@Controller
public class LegacyAddressController {

	private static final Logger logger = LoggerFactory.getLogger(LegacyAddressController.class);

	private LegacyAddressService addressService;

	/**
	 * Grab hold of an address and push it into the model for display.
	 * 
	 * @param id
	 *            The id of the address object as a request param in the form
	 *            id=<num>
	 * 
	 */
	@RequestMapping(value = "/props", method = RequestMethod.GET)
	public String findAddress(@RequestParam("id") int id, Model model) {

		logger.info("Processing an address page request for address with id: " + id);

		Address address = addressService.findAddress(id);
		model.addAttribute("address", address);

		return "address-display";
	}

	@Autowired
	void setAddressService(LegacyAddressService addressService) {
		this.addressService = addressService;
	}
}
