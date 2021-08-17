package com.sajeev.bank.account.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class AccountInquiryResponse {
    //Result - provider , isValid
    Map<String,String> result;
}
