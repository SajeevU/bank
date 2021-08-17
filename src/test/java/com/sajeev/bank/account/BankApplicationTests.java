package com.sajeev.bank.account;

import com.sajeev.bank.account.beans.AccountInquiryRequest;
import com.sajeev.bank.account.services.AccountValidateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;


@SpringBootTest
class BankApplicationTests {

	@Autowired
	AccountValidateService accountValidateService;

	@Mock
	HttpURLConnection connection;

	@Autowired
	AccountInquiryRequest accountInquiryRequest;

	@Test
	void contextLoads() throws IOException {
		System.out.println("================Test BankApplicationTests======================");
		accountInquiryRequest.setAccountNumber("12345678");
		List<String> providers = List.of("provider1","provider2");
		accountInquiryRequest.setProviders(providers);
		accountValidateService.validateAccountDetails(accountInquiryRequest);
	}

}
