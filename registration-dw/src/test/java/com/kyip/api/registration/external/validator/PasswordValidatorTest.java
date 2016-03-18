package com.kyip.api.registration.external.validator;

import org.junit.Assert;
import org.junit.Test;

public class PasswordValidatorTest {

	PasswordValidator validator = PasswordValidator.getInstance();

	@Test
	public void testEmpty() throws Exception {
		String password = "";
		Assert.assertFalse(validator.validateNotBlank(password));
		Assert.assertFalse(validator.validateRules(password));
	}

	@Test
	public void testInvalidLength() throws Exception {
		String password = "Ab12#";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertFalse(validator.validateRules(password));
	}

	@Test
	public void testInteger() throws Exception {
		String password = "123456789";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertFalse(validator.validateRules(password));
	}

	@Test
	public void testAlphaLowerCase() throws Exception {
		String password = "fincaopppp";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertFalse(validator.validateRules(password));
	}

	@Test
	public void testAlphaUpperLowerCase() throws Exception {
		String password = "Finocapppp";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testAlphaNumeric() throws Exception {
		String password = "fin0cap78";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testAlphaSpecialChar() throws Exception {
		String password = "fin@@@@cap";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testNumericSpecialChar() throws Exception {
		String password = "123@@@@789";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testAlphaNumericSpecialChar() throws Exception {
		String password = "AA123456#";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testAlphaNumericSpecialChar2() throws Exception {
		String password = "aa12345678#";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testAlphaNumericSpecialChar3() throws Exception {
		String password = "Aa12345678#";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

	@Test
	public void testAlphaNumericSpecialCharVeryLong() throws Exception {
		String password = "Ab12345678###111111111111111111111111111ssssssssssssssssssssssssssssssssss55555555555555555555555555555";
		Assert.assertTrue(validator.validateNotBlank(password));
		Assert.assertTrue(validator.validateRules(password));
	}

}
