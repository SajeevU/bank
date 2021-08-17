package com.sajeev.bank.account.controllers;

import com.sajeev.bank.account.beans.AccountInquiryRequest;
import com.sajeev.bank.account.beans.AccountInquiryResponse;
import com.sajeev.bank.account.services.AccountValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountDetailRetrieveController {

    @Autowired
    private AccountValidateService accountValidateService;

    @PostMapping(value = "/validateAccount", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AccountInquiryResponse validateAccount(@RequestBody AccountInquiryRequest request)
    {
        return accountValidateService.validateAccountDetails(request);
    }
}
