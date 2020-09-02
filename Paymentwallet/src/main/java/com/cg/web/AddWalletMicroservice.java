package com.cg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import com.cg.dto.AccountForm;
import com.cg.dto.SuccessResponse;
import com.cg.dto.UserDto;
import com.cg.exceptions.BankAccountException;
import com.cg.exceptions.WalletAccountExistsException;
import com.cg.service.IAddWalletService;
import com.cg.util.WalletConstants;



@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
public class AddWalletMicroservice {
	
	@Autowired
	private IAddWalletService walletService;
	//@Autowired
	//private RestTemplate rt;
	
	@PostMapping(WalletConstants.ADD_WALLET_URL)
	public SuccessResponse addNewWalletaccount(@RequestBody(required=true) UserDto userDto) throws WalletAccountExistsException {
		String message = walletService.addNewWalletAccount(userDto);
		return new SuccessResponse(message);
	}
	@PostMapping(WalletConstants.ADD_BANK_URL)
	public SuccessResponse addBankToWallet(@RequestBody AccountForm form) throws BankAccountException, WalletAccountExistsException {
		//String url = "http://localhost:8088/paymentwallet/verify";
		//String res= rt.postForObject(url, form, String.class);
		String msg = walletService.addBankAccount(form) ;
		return new SuccessResponse(msg);
		
	}
}
