package com.kyip.api.registration.external.validator;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;

public class PasswordValidator {
	private static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();
	private org.passay.PasswordValidator validator;

	public static PasswordValidator getInstance() {
		return PASSWORD_VALIDATOR;
	}

	protected PasswordValidator() {
		CharacterCharacteristicsRule characterRule = new CharacterCharacteristicsRule();
		characterRule.setNumberOfCharacteristics(2); // need to match at least 2 of the character rule

		// Define elements of all available rules (upper, lower, digit, symbol)
		characterRule.getRules().add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
		characterRule.getRules().add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
		characterRule.getRules().add(new CharacterRule(EnglishCharacterData.Digit, 1));
		characterRule.getRules().add(new CharacterRule(EnglishCharacterData.Special, 1));

		List<Rule> validationRules = Arrays.asList(new LengthRule(8, Integer.MAX_VALUE), characterRule);
		validator = new org.passay.PasswordValidator(validationRules);
	}

	/**
	 * Validate password by finocap standards defines in FP-150
	 *
	 * rule1: Password length at least 8 chars.
	 *
	 * rule2: Minimum two of the following (UPPERCASE, lowercase, Special Char
	 * (e.g. @!#$%&*) or a number)
	 *
	 * @param password
	 * @return isValid
	 */
	public boolean validateRules(String password) {
		RuleResult result = validator.validate(new PasswordData(password));
		return result.isValid();
	}

	/**
	 * validateRules validate empty too but need this one for empty validation
	 * for displaying message
	 *
	 * @param password
	 * @return isValid
	 */
	public boolean validateNotBlank(String password) {
		return StringUtils.isNotBlank(password);
	}

	public Integer checkPasswordStrength(String password) {
		RuleResult result = validator.validate(new PasswordData(password));
		List<String> failMessages = validator.getMessages(result);

		if (CollectionUtils.isEmpty(failMessages)) {
			return 0;
		}
		return failMessages.size();
	}
}
