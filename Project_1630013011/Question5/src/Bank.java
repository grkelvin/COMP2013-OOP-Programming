import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<IAccount> accounts;
	
	//initializes the bank's name, accounts and model listeners
	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
	}
	
	//Add an account
	public void addAccount(IAccount account) {
		accounts.add(account);
	}
	
	//Calculate total money
	public int totalMoney() {
		int total = 0;
		for(IAccount i : accounts) {
			total += i.getMoney();
		}
		return total;
	}
	
	public int getMoney(String name) throws UnknownCustomerException {
		for(IAccount i : accounts) {
			if (i.getName().equals(name)) {
				return i.getMoney();
			}
		}
		throw new UnknownCustomerException("Customer " + name + " unknown");
	}
	
	//Withdraw money
	public void withdraw(String name, int amount) throws UnknownCustomerException {
		for(IAccount i : accounts) {
			if(i.getName().equals(name)) {
				try {
					i.withdraw(amount);
				} catch (NotEnoughMoneyException e) {
					System.out.println(e.getMessage());
				}
				return;
			}
		}
		throw new UnknownCustomerException("Customer " + name + " unknown");
	}

	
	// testing
	public static void testBank() {
		// a bank named ABC
		Bank b = new Bank("ABC");
		// the bank have four users
		b.addAccount(new CreditAccount("Kelvin", 1000));
		b.addAccount(new CreditAccount("Baby", 1000));
		b.addAccount(new CreditAccount("Haha", 800));
		try {
			b.addAccount(new StudentAccount("Gloria", 200));
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		}
		// get total money of the bank 3000
		System.out.println(b.totalMoney() == 3000);
		try {
			b.withdraw("Kelvin", 200);
			b.withdraw("Baby", 200);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		// Baby has 800 left
		try {
			System.out.println(b.getMoney("Baby") == 800);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		// the bank has total 2600
		System.out.println(b.totalMoney() == 2600);
	}
}