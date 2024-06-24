package com.simple.bank.service;

import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Customer;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.response.BankResponse;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.helper.EntityDtoConversionHelper;
import com.simple.bank.utility.AccountNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.simple.bank.constant.Constant.ACCOUNT_SUCCESS_MESSAGE;
import static com.simple.bank.constant.Constant.FAILURE_CUSTOMER_NOT_FOUND_MESSAGE;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityDtoConversionHelper entityDtoConversionHelper;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Method to add an Account to the System
     *
     * @param createAccountDto The account data to be added
     * @return The response entity with success or failure if customer number is not found
     */
    @Override
    public ResponseEntity<WsResponse> createAccount(CreateAccountDto createAccountDto) {
        AccountNumberUtils accountNumberUtils = new AccountNumberUtils();

        Optional<Customer> customer = customerRepository.findByCustomerNumber(createAccountDto.getCustomerNumber());
        if (customer.isPresent()) {
            Account account = entityDtoConversionHelper.convertToAccountEntity(createAccountDto);
            account.setAccountNumber(accountNumberUtils.generate());
            accountRepository.save(account);
            return BankResponse.successResponse(ACCOUNT_SUCCESS_MESSAGE, HttpStatus.CREATED.value(), entityDtoConversionHelper.convertToAccountDto(account));
        }
        return BankResponse.failureResponse(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Find All the accounts in the system
     *
     * @return Response entity with all account details
     */
    @Override
    public ResponseEntity<?> getAllAccountWithBalance() {
        List<Account> allAccountDetails = accountRepository.findAll();
        return BankResponse.getResponse(allAccountDetails);
    }

    /**
     * Find account from system with account number
     *
     * @param accountNumber Account Number to be fetched
     * @return Response entity wrapped with WsResponse with account details
     */
    @Override
    public ResponseEntity<WsResponse> findByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.map(value -> BankResponse.getResponse(entityDtoConversionHelper.convertToAccountDto(value)))
                .orElseGet(() -> BankResponse.failureResponse(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.value()));
    }
}
