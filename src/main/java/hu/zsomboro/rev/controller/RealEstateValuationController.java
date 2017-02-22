package hu.zsomboro.rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import hu.zsomboro.rev.buisness.PriceAverage;
import hu.zsomboro.rev.buisness.RealEstate;
import hu.zsomboro.rev.service.PriceAverageService;
import hu.zsomboro.rev.service.RealEstateService;
import hu.zsomboro.rev.ui.RealEstateFormValidator;
import hu.zsomboro.rev.ui.RealEstateUI;

@Controller
public class RealEstateValuationController {

	private static final String WEBAPP_ROOT = "/rev";

	@Autowired
	private PriceAverageService priceAverageSrv;

	@Autowired
	private RealEstateService realEstateSrv;

	@Autowired
	private RealEstateFormValidator validator;

	@RequestMapping("/")
	public String welcome(Model model) {
		return "redirect:" + WEBAPP_ROOT + "/estates";
	}

	@RequestMapping(method = RequestMethod.GET, value = WEBAPP_ROOT + "/estates")
	public String listRealEstates(Model model) {

		List<RealEstate> estates = realEstateSrv.getAllRealEstates();
		List<RealEstateUI> uiEstates = Lists.transform(estates, domainToUiFunction());

		model.addAttribute("realEstates", uiEstates);
		return "re_list";
	}

	@RequestMapping(method = RequestMethod.POST, value = WEBAPP_ROOT + "/estates/add")
	public String addNewRealEstate(@ModelAttribute("reForm") RealEstateUI re, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		validator.validate(re, result);

		if (!result.hasErrors()) {
			List<PriceAverage> averages = priceAverageSrv.getAllPriceAverages();

			RealEstate reBU = re.toBusinessModel();
			RealEstate pricedRealEstate = reBU.withNewPrice(averages);

			realEstateSrv.saveRealEstate(pricedRealEstate);

			model.addAttribute("reForm", pricedRealEstate.toUIModel());
		}

		return "re_new";
	}

	@RequestMapping(method = RequestMethod.GET, value = WEBAPP_ROOT + "/estates/new")
	public String displayNewRealEstateForm(Model model) {

		RealEstateUI re = new RealEstateUI();

		model.addAttribute("reForm", re);

		return "re_new";
	}

	private Function<RealEstate, RealEstateUI> domainToUiFunction() {
		return new Function<RealEstate, RealEstateUI>() {

			@Override
			public RealEstateUI apply(RealEstate estate) {
				return estate == null ? null : estate.toUIModel();
			}
		};
	}

}
