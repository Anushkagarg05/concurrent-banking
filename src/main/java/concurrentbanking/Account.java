package main.java.concurrentbanking;

import java.util.concurrent.CyclicBarrier;

public class Account {

    private volatile int balance;
    private final int id;
    private Bank bank;

    public Account(Bank bank, int id, int initialBalance) {
        this.id = id;
        this.balance = initialBalance;
        this.bank = bank;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            int currentBalance = balance;
           Thread.yield(); // Try to force collision
            int newBalance = currentBalance - amount;
            balance = newBalance;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void deposit(int amount) {
        int currentBalance = balance;
        Thread.yield();   // Try to force collision
        int newBalance = currentBalance + amount;
        balance = newBalance;
        notifyAll();
    }
    
    public synchronized void waitForAvailableFunds(int amount) {
    	while (bank.isOpen() && (amount > balance)) {
    		//System.out.printf("wait- Account %d, Balance %d, Amount %d\n", id, balance, amount);
			try {
			    // System.out.println("**waiting ");
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
    	}
    }
    
    @Override
    public String toString() {
        return String.format("Account[%d] balance %d", id, balance);
    }
}