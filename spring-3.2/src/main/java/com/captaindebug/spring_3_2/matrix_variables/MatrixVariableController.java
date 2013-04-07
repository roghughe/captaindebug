package com.captaindebug.spring_3_2.matrix_variables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/matrixvars")
public class MatrixVariableController {

	private static final Logger logger = LoggerFactory.getLogger(MatrixVariableController.class);

	/**
	 * Deal with a URL that looks something like this:
	 * http://<hostname>:<port>/spring_3_2/matrixvars/stocks;<stock info>
	 * 
	 * For example:
	 * 
	 * http://localhost:8080/spring_3_2/matrixvars/stocks;BT.A=276.70,+10.40,+
	 * 3.91;AZN=236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07
	 */
	@RequestMapping(value = "/{stocks}", method = RequestMethod.GET)
	public String showPortfolioValues(@MatrixVariable Map<String, List<String>> matrixVars, Model model) {

		logger.info("Storing {} Values...", matrixVars.size());

		List<List<String>> outlist = map2List(matrixVars);
		model.addAttribute("stocks", outlist);

		return "stocks";
	}

	private List<List<String>> map2List(Map<String, List<String>> stocksMap) {

		List<List<String>> outlist = new ArrayList<List<String>>();

		Collection<Entry<String, List<String>>> stocksSet = stocksMap.entrySet();

		for (Entry<String, List<String>> entry : stocksSet) {

			List<String> rowList = new ArrayList<String>();

			String name = entry.getKey();
			rowList.add(name);

			List<String> stock = entry.getValue();
			rowList.addAll(stock);
			outlist.add(rowList);
		}

		return outlist;
	}

	/**
	 * Deal with a URL that looks something like this:
	 * http://<hostname>:<port>/spring_3_2/matrixvars/stocks;<stock
	 * info>/account<account info>
	 * 
	 * For example:
	 * 
	 * http://localhost:8080/spring_3_2/matrixvars/stocks;BT.A=276.70,,+3.91;AZN
	 * =236.00,+103.00;SBRY=375.50/account;name=roger;number=105;location=stoke-
	 * on-trent
	 */
	@RequestMapping(value = "/{stocks}/{account}", method = RequestMethod.GET)
	public String showPortfolioValuesWithAccountInfo(@MatrixVariable(pathVar = "stocks") Map<String, List<String>> stocks,
			@MatrixVariable(pathVar = "account") Map<String, List<String>> accounts, Model model) {

		List<List<String>> stocksView = map2List(stocks);
		model.addAttribute("stocks", stocksView);

		List<List<String>> accountDetails = map2List(accounts);
		model.addAttribute("accountDetails", accountDetails);

		return "stocks";
	}
}
