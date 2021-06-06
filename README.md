# concurrent-banking
- Banking Simulation
  - Main Thread creates a bank instance
  - Each subsequent thread is associated to an account and runs 10000 times.
  - In each run it transfer a random amount to a random account.
 
### Race-conditions:
  - When multiple threads are inside withdraw/deposite method for same account.

### Resolution:
  - New thread tries to run the test() method
  - Waits within test method if the atomic integer is >0 signalling one thread has not completed it's full transfer.
  
Read comments in code for more details.
