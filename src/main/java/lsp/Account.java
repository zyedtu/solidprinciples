package lsp;

import java.math.BigDecimal;

public abstract class Account {
	protected BigDecimal amount;
	protected abstract void deposit(BigDecimal amount);
//	protected abstract void withdraw(BigDecimal amount);
}
