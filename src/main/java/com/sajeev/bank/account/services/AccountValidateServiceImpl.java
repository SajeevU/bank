package com.sajeev.bank.account.services;

import com.sajeev.bank.account.beans.AccountInquiryRequest;
import com.sajeev.bank.account.beans.AccountInquiryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountValidateServiceImpl implements AccountValidateService{

    @Value("#{${bank.account.providers}}")
    private Map<String,String> providers;

    @Override
    public AccountInquiryResponse validateAccountDetails(AccountInquiryRequest request){

        String accountNumber = request.getAccountNumber();

        if(accountNumber == null || accountNumber.isEmpty())
            throw new IllegalArgumentException("Account Number Field cannot be null or empty");

        List<String> dataProviders = request.getProviders();
        List<String> existingProviders = new ArrayList<>();

        if(dataProviders.size()==0 || dataProviders == null)
        {
            for ( String key : providers.keySet() ) {
                existingProviders.add(key);
            }

            dataProviders = existingProviders;
        }


        String urlString = "";

        Map<String,String> result = new HashMap<>();
        AccountInquiryResponse accountInquiryResponse = new AccountInquiryResponse();
        System.out.println("[AccountValidateServiceImpl] [Request received is : " + request+"]");
        try{

            for(String provider : dataProviders)
            {
                if(providers.containsKey(provider))
                {
                    urlString = providers.get(provider);
                    String response = getResponseFromProvider(urlString, accountNumber);
                    result.put(provider,response);
                }
            }

        }catch(Exception ex)
        {
            System.out.println("[AccountValidateServiceImpl] [ Exception occured ]");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        accountInquiryResponse.setResult(result);
        System.out.println("[AccountValidateServiceImpl] [Response is : " + accountInquiryResponse+"]");;
        return accountInquiryResponse;
    }

    private String getResponseFromProvider(String providerUrl, String accountNumber)
    {
        String result = "";
        try{
            URL url = new URL(providerUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) connection;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            String json = "{\"accountNumber\":\""+accountNumber+"\"}";
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            http.connect();
            OutputStream os = http.getOutputStream();
            result = os.toString();
            os.flush();
            os.close();

        }catch (Exception ex)
        {
            System.out.println("[AccountValidateServiceImpl] [ Exception occured ]");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            result = "No Response";
        }

        return result;
    }
}
