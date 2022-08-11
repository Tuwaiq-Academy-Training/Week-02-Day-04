package com.example.authspring.service;


import com.example.authspring.model.BankAccount;
import com.example.authspring.model.MyUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BankService {

    private ArrayList<BankAccount> bankAccounts=new ArrayList<>();
    private AuthService authService;

    public BankService(AuthService authService){
        this.authService=authService;
    }

    public ArrayList<BankAccount> getAccounts() {
        return bankAccounts;
    }

    public boolean addAccount(BankAccount bankAccount) {
       ArrayList<MyUser> users=authService.getUsers();

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId()==bankAccount.getUserId()){
                bankAccounts.add(bankAccount);
                return true;
            }
        }

        return false;

    }

    public boolean deposit(int accountId, int balance) {
        for (int i = 0; i < bankAccounts.size(); i++) {
            BankAccount account=bankAccounts.get(i);
            if(bankAccounts.get(i).getUserId()==accountId){
                account.setBalance(account.getBalance()+balance);
                return true;
            }
        }

        return false;
    }

    public int withdraw(int accountId, int balance) {
        for (int i = 0; i < bankAccounts.size(); i++) {
            BankAccount account=bankAccounts.get(i);
            if(bankAccounts.get(i).getUserId()==accountId){

                if(account.getBalance()<balance){
                    return -1;
                }
                account.setBalance(account.getBalance()-balance);
                return 1;
            }
        }

        return 0;
    }
}
