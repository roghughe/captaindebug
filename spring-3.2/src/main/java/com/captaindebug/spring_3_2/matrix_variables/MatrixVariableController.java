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

@Controller
public class MatrixVariableController {

	private static final String GAP = "    ";

	private static final Logger logger = LoggerFactory.getLogger(MatrixVariableController.class);

	@RequestMapping(value = "/stocks/{portfolio}", method = RequestMethod.GET)
	public String storeLatestPortfolioValues(@MatrixVariable Map<String, List<String>> matrixVars, Model model) {

		logger.info("Storing {} Values...", matrixVars.size());

		List<String> outList = new ArrayList<String>();

		Set<String> stocks = matrixVars.keySet();

		for (String stock : stocks) {

			List<String> values = matrixVars.get(stock);
			logger.info("Found stock {} and value: {}", new Object[] { stock, values });

			StringBuilder sb = new StringBuilder(GAP);
			sb.append(stock);
			sb.append(GAP);
			for (String value : values) {
				sb.append(value);
				sb.append(GAP);
			}
			outList.add(sb.toString());

		}
		logger.info("Added outlist: {} ", outList);
		model.addAttribute("stocks", outList);
		return "stocks";
	}
}
