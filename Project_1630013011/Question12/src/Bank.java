import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<IAccount> accounts;
	private ArrayList<ModelListener> modelListeners;
	private ArrayList<Integer> history;
	
	/*
	 * A constructor which initializes the bank's name, accounts, model listeners
	 * and the total money history
	 */
	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
		modelListeners = new ArrayList<>();
		history = new ArrayList<>();
		history.add(0);
	}
	
	//Add an account to the bank
	public void addAccount(IAccount account) {
		accounts.add(account);
		history.add(totalMoney());
		notifyListeners();
	}
	
	//Calculate total money of all the accounts
	public int totalMoney() {
		int total = 0;
		for(IAccount i : accounts) {
			total += i.getMoney();
		}
		return total;
	}
	
	/*
	 * Get the account's money specified by the account's name
	 * If the name is not found in all the accounts, throws a UnknownCustomerExcpetion
	 */
	public int getMoney(String name) throws UnknownCustomerException {
		for(IAccount i : accounts) {
			if (i.getName().equals(name)) {
				return i.getMoney();
			}
		}
		throw new UnknownCustomerException("Customer " + name + " unknown");
	}
	
	//Withdraw money from an account
	public void withdraw(String name, int amount) throws UnknownCustomerException {
		for(IAccount i : accounts) {
			if(i.getName().equals(name)) {
				try {
					i.withdraw(amount);
					history.add(totalMoney());
					notifyListeners();
				} catch (NotEnoughMoneyException e) {
					System.out.println(e.getMessage());
				}
				return;
			}
		}
		throw new UnknownCustomerException("Customer " + name + " unknown");
	}
	
	//Add a model listener
	public void addListener(ModelListener m) {
		modelListeners.add(m);
	}
	
	//Notify all the listeners
	private void notifyListeners() {
		for(ModelListener m : modelListeners) {
			m.update();
		}
	}
	
	//Get the history of the total money of the bank
	public ArrayList<Integer> getHistory() {
		return history;
	}
	//A static method for testing the Bank class
	public static void testBank() {
		Bank b = new Bank("Aloy");
		b.addAccount(new CreditAccount("Veronica", 100));
		b.addAccount(new CreditAccount("Tove", 100));
		b.addAccount(new CreditAccount("Frances", 100));
		try {
			b.addAccount(new StudentAccount("Gloria", 20));
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			b.withdraw("Veronica", 200);
			b.withdraw("Tove", 20);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(b.getMoney("Tove") == 80);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(b.totalMoney());
	}
}