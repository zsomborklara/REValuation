package hu.zsomboro.rev.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RealEstateFormValidator implements Validator {

	private Pattern latitudePattern = Pattern.compile("[0-9]+\\s+[0-9]+\\s+[0-9]+\\s+[E|W]");
	private Pattern longitudePattern = Pattern.compile("[0-9]+\\s+[0-9]+\\s+[0-9]+\\s+[S|N]");

	@Override
	public boolean supports(Class<?> clazz) {
		return RealEstateUI.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		RealEstateUI realEstateUI = (RealEstateUI) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "area", "NotEmpty.reForm.area");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coordLatitude", "NotEmpty.reForm.coordLatitude");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coordLongitude", "NotEmpty.reForm.coordLongitude");

		Double area = realEstateUI.getArea();
		if(area == null || area < 5.0 || area > 10_000){
			errors.rejectValue("area", "Valid.reForm.area");
		}

		if(realEstateUI.getCoordLatitude() != null){
			Matcher m = longitudePattern.matcher(realEstateUI.getCoordLatitude());
			if(!m.matches()) {
				errors.rejectValue("coordLatitude", "Pattern.reForm.coordLatitude");
			}
		}

		if(realEstateUI.getCoordLongitude() != null){
			Matcher m = latitudePattern.matcher(realEstateUI.getCoordLongitude());
			if(!m.matches()) {
				errors.rejectValue("coordLongitude", "Pattern.reForm.coordLongitude");
			}
		}
	}

}
