package lsp;

import java.math.BigDecimal;

public class WithdrawableAccount extends Account {

	@Override
	protected void deposit(BigDecimal amount) {
		this.amount = amount;
	}

	protected void withdraw(BigDecimal amount) {
		if(this.amount.compareTo(amount) >= 0 )
			this.amount.subtract(amount);
		else
			throw new RuntimeException("Solde du compte est insuffusant!");
	}
}
