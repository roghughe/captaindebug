package com.captaindebug.spring_3_2.matrix_variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/matrixvars")
public class MatrixVariableController {

	private static final Logger logger = LoggerFactory.getLogger(MatrixVariableController.class);

	@RequestMapping(value = "/{stocks}", method = RequestMethod.GET)
	public String showPortfolioValues(@MatrixVariable Map<String, List<String>> matrixVars, Model model) {

		logger.info("Storing {} Values...", matrixVars.size());

		List<List<String>> outlist = new ArrayList<List<String>>();
		model.addAttribute("stocks", outlist);

		Set<String> stocks = matrixVars.keySet();

		for (String stock : stocks) {

			List<String> rowList = new ArrayList<String>();
			rowList.add(stock);

			List<String> values = matrixVars.get(stock);
			logger.info("Found stock {} and value: {}", new Object[] { stock, values });

			rowList.addAll(values);

			logger.info("Added outlist: {} ", rowList);
			outlist.add(rowList);
		}
		return "stocks";
	}

	@RequestMapping(value = "/{stocks}/{account}", method = RequestMethod.GET)
	public String showPortfolioValuesWithAccountInfo(@MatrixVariable(pathVar = "stocks") Map<String, List<String>> stocks,
			@MatrixVariable(pathVar = "account") Map<String, List<String>> accounts, Model model) {
		System.out.println("Account call");
		return "TODO";
	}

}
