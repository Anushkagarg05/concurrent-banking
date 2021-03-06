package main.java.concurrentbanking;
class TransferThread extends Thread {

    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;

    public TransferThread(Bank b, int from, int max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
        	if (!bank.isOpen()) {
        		break;
        	}
            int toAccount = (int) (bank.getNumAccounts() * Math.random());
            int amount = (int) (maxAmount * Math.random());
            bank.transfer(fromAccount, toAccount, amount);
        }
        bank.closeBank();
        System.out.printf("%-30s Account[%d] has finished with its transactions.\n", Thread.currentThread().toString(), fromAccount);
    }
}