package com.sajeev.bank.account.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Data
@Component
public class AccountInquiryRequest {
    String accountNumber;
    List<String> providers;
}
