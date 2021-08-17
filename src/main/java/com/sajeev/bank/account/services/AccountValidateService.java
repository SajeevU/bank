package com.sajeev.bank.account.services;

import com.sajeev.bank.account.beans.AccountInquiryRequest;
import com.sajeev.bank.account.beans.AccountInquiryResponse;

public interface AccountValidateService {
    AccountInquiryResponse validateAccountDetails(AccountInquiryRequest request);
}
