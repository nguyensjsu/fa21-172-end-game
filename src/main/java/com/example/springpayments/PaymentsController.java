package com.example.springpayments;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Optional;
import java.time.*; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import lombok.Getter;
import lombok.Setter;

import com.example.springcybersource.*;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Controller
@RequestMapping("/")
public class PaymentsController 
{  
    
    @Value("${cybersource.apihost}") String apiHost;
    @Value("${cybersource.merchantkeyid}") String merchantKeyId;
    @Value("${cybersource.merchantsecretkey}") String merchantsecretKey;
    @Value("${cybersource.merchantid}") String merchantId;

    private CyberSourceAPI api = new CyberSourceAPI();

    private static Map<String, String> months = new HashMap<>();
    static 
    {
        months.put("January", "01");
        months.put("February", "02"); 
        months.put("March", "03");
        months.put("April", "04");
        months.put("May", "05");
        months.put("June", "06");
        months.put("July", "07");
        months.put("August", "08");
        months.put("September", "09");
        months.put("October", "10");
        months.put("November", "11");
        months.put("December", "12");
    }

    private static Map<String,String> states = new HashMap<>();
    static
    {
        states.put("AK", "Alaska");
        states.put("AL", "Alabama");
        states.put("AR", "Arkansas");
        states.put("AZ", "Arizona");
        states.put("CA", "California");
        states.put("CO", "Colorado");
        states.put("CT", "Connecticut");
        states.put("DC", "District of Columbia");
        states.put("DE", "Delaware");
        states.put("FL", "Florida");
        states.put("GA", "Georgia");
        states.put("HI", "Hawaii");
        states.put("IA", "Iowa");
        states.put("ID", "Idaho");
        states.put("IL", "Illinois");
        states.put("IN", "Indiana");
        states.put("KS", "Kansas");
        states.put("KY", "Kentucky");
        states.put("LA", "Louisiana");
        states.put("MA", "Massachusetts");
        states.put("MD", "Maryland");
        states.put("ME", "Maine");
        states.put("MI", "Michigan");
        states.put("MN", "Minnesota");
        states.put("MO", "Missouri");
        states.put("MS", "Mississippi");
        states.put("MT", "Montana");
        states.put("NC", "North Carolina");
        states.put("ND", "North Dakota");
        states.put("NE", "Nebraska");
        states.put("NH", "New Hampshire");
        states.put("NJ", "New Jersey");
        states.put("NM", "New Mexico");
        states.put("NV", "Nevada");
        states.put("NY", "New York");
        states.put("OH", "Ohio");
        states.put("OK", "Oklahoma");
        states.put("OR", "Oregon");
        states.put("PA", "Pennsylvania");
        states.put("RI", "Rhode Island");
        states.put("SC", "South Carolina");
        states.put("SD", "South Dakota");
        states.put("TN", "Tennessee");
        states.put("TX", "Texas");
        states.put("UT", "Utah");
        states.put("VA", "Virginia");
        states.put("VT", "Vermont");
        states.put("WA", "Washington");
        states.put("WI", "Wisconsin");
        states.put("WV", "West Virginia");
        states.put("WY", "Wyoming");
    }

    @Autowired
    private PaymentsCommandRepository repository;

    @Getter
    @Setter
    class Message 
    {
        private String msg;
        public Message(String m)
        {
            msg = m;
        }
    }

    class ErrorMessages
    {
        private ArrayList<Message> messages = new ArrayList<Message>();
        public void add(String msg)
        {
            messages.add(new Message(msg));
        }
        public ArrayList<Message> getMessages()
        {
            return messages;
        }
        public void print()
        {
            for (Message m : messages)
            {
                System.out.println(m.msg);
            }
        }
    }


    @GetMapping
    public String getAction( @ModelAttribute("command") PaymentsCommand command, Model model) 
    {
        return "creditcards" ;
    }

    @PostMapping
    public String postAction(@Valid @ModelAttribute("command") PaymentsCommand command,  
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) {
    
        log.info( "Action: " + action ) ;
        log.info( "Command: " + command ) ;
                                
        CyberSourceAPI.setHost(apiHost);
        CyberSourceAPI.setKey(merchantKeyId);
        CyberSourceAPI.setSecret(merchantsecretKey);
        CyberSourceAPI.setMerchant(merchantId);
        CyberSourceAPI.debugConfig();

        ErrorMessages msgs = new ErrorMessages();
                            
        boolean hasErrors = false;
        // Form validation making all Form Fields "Required" except for the Notes field
        if (command.firstname().equals(""))
        {
            hasErrors = true;
            msgs.add("First Name Required.");
        }
        if (command.lastname().equals(""))
        {
            hasErrors = true;
            msgs.add("Last Name Required.");
        }
        if (command.address().equals(""))
        {
            hasErrors = true;
            msgs.add("Address Required.");
        }
        if (command.city().equals(""))
        {
            hasErrors = true;
            msgs.add("City Required.");
        }
        if (command.state().equals(""))
        {
            hasErrors = true;
            msgs.add("State Required.");
        }
        if (command.zip().equals(""))
        {
            hasErrors = true;
            msgs.add("Zip Required.");
        }
        if (command.phone().equals(""))
        {
            hasErrors = true;
            msgs.add("Phone Required.");
        }
        if (command.cardnum().equals(""))
        {
            hasErrors = true;
            msgs.add("Credit Card Number Required.");
        }
        if (command.cardexpmon().equals(""))
        {
            hasErrors = true;
            msgs.add("Credit Card Expiration Month Required.");
        }
        if (command.cardexpyear().equals(""))
        {
            hasErrors = true;
            msgs.add("Credit Card Expiration Year Required.");
        }
        if (command.cardcvv().equals(""))
        {
            hasErrors = true;
            msgs.add("Credit Card CVV Required.");
        }
        if (command.email().equals(""))
        {
            hasErrors = true;
            msgs.add("Email Address Required.");
        }

        // Zip Code should be 5 digits
        if(!command.zip().matches("\\d{5}"))
        {
            hasErrors = true;
            msgs.add("Invalid Zip");
        }
        // Phone Number should have the format: (###) ###-####
        if(!command.phone().matches("[()]\\d{3}[)] \\d{3}-\\d{4}"))
        {
            hasErrors = true;
            msgs.add("Invalid Phone Number");
        }        
        // Credit Card Number should have the format: ####-####-####-####
        if(!command.cardnum().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}"))
        {
            hasErrors = true;
            msgs.add("Invalid Credit Card Number");
        }
        // Credit Card Expiration Year should be 4 digits
        if(!command.cardexpyear().matches("\\d{4}"))
        {
            hasErrors = true;
            msgs.add("Invalid Credit Card Expiration Year");
        }
        // Credit Card CVV should be 3 digits
        if(!command.cardcvv().matches("\\d{3}"))
        {
            hasErrors = true;
            msgs.add("Invalid Credit Card CVV");
        }
        // Credit Card Expiration Month should be the full month spelled out
        if(months.get(command.cardexpmon()) == null)
        {
            hasErrors = true;
            msgs.add("Invalid Card Expiration Month");
        }
        // State should be one of the two letter abbreviations for the 50 U.S. States
        if(states.get(command.state()) == null)
        {
            hasErrors = true;
            msgs.add("Invalid State:" + command.state());
        }

        if (hasErrors)
        {
            msgs.print();
            model.addAttribute("messages", msgs.getMessages());
            return "creditcards";
        }

        int min = 1234567;
        int max = 9876543;
        int random_int = (int) Math.floor(Math.random()*(max-min+1)+min);
        String order_num = String.valueOf(random_int);
        AuthRequest auth = new AuthRequest();
        auth.reference = order_num;
        auth.billToFirstName = command.firstname();
        auth.billToLastName = command.lastname();
        auth.billToAddress = command.address();
        auth.billToCity = command.city();
        auth.billToState = command.state();
        auth.billToZipCode = command.zip();
        auth.billToPhone = command.phone();
        auth.billToEmail = command.email();
        auth.cardNumber = command.cardnum();
        auth.cardExpMonth = months.get(command.cardexpmon());
        auth.cardExpYear = command.cardexpyear();
        auth.cardCVV = command.cardcvv();
        auth.cardType = CyberSourceAPI.getCardType(auth.cardNumber);
        auth.transactionAmount = "30.00";
        auth.transactionCurrency = "USD";
        if(auth.cardType.equals("ERROR"))
        {
            System.out.println("Unsupported Credit Card");
            model.addAttribute("message", "Unsupported Credit Card Type");
            return "creditcards";
        }
        boolean authValid = true;
        AuthResponse authResponse = new AuthResponse();
        System.out.println("\n\nAuth Request: " + auth.toJson());
        authResponse = api.authorize(auth);
        System.out.println("\n\nAuth Response: " + authResponse.toJson());
        if(!authResponse.status.equals("AUTHORIZED"))
        {
            authValid = false;
            System.out.println(authResponse.message);
            model.addAttribute("message", authResponse.message);
            return "creditcards";
        }

        boolean captureValid = true;
        CaptureRequest capture = new CaptureRequest();
        CaptureResponse captureResponse = new CaptureResponse();
        if(authValid)
        {
            capture.reference = order_num;
            capture.paymentId = authResponse.id;
            capture.transactionAmount = "30.00";
            capture.transactionCurrency = "USD";
            System.out.println("\n\nCapture Request: " + capture.toJson());
            captureResponse = api.capture(capture);
            System.out.println("\n\nCapture Response: " + captureResponse.toJson());
            if(!captureResponse.status.equals("PENDING"))
            {
                captureValid = false;
                System.out.println(captureResponse.message);
                model.addAttribute("message", captureResponse.message);
                return "creditcards";
            }
        }

        /* Render View */
        if(authValid && captureValid)
        {
            command.setOrderNumber(order_num);
            command.setTransactionAmount("30.00");
            command.setTransactionCurrency("USD");
            command.setAuthId(authResponse.id);
            command.setAuthStatus(authResponse.status);
            command.setCaptureId(captureResponse.id);
            command.setCaptureStatus(captureResponse.status);
            repository.save(command);
            System.out.println("Thank You for Your Payment! Your Order Number is: " + order_num );
            model.addAttribute("message", "Thank You for Your Payment! Your Order Number is: " + order_num );
        }
        return "creditcards";
    }

}
