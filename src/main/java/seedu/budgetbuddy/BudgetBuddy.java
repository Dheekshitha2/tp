package seedu.budgetbuddy;

import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.commons.ExpenseList;
import seedu.budgetbuddy.commons.RecurringExpenseLists;
import seedu.budgetbuddy.commons.SavingList;
import seedu.budgetbuddy.commons.SplitExpenseList;
import seedu.budgetbuddy.exception.InvalidRecurringExpensesFileException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BudgetBuddy {
    private Ui ui;
    private Parser parser;
    private ExpenseList expenses;
    private SavingList savings;
    private SplitExpenseList splitexpenses;
    private RecurringExpenseLists recurringExpenseLists;
    private Storage expensesStorage;
    private Storage savingsStorage;
    private Storage recurringExpensesStorage;
    private Storage splitexpensesStorage;
    private Storage defaultCurrency;
    private Storage budgetStorage;



    public BudgetBuddy() {
        ui = new Ui();
        parser = new Parser();
        expenses = new ExpenseList();
        savings = new SavingList();
        recurringExpenseLists = new RecurringExpenseLists();
        splitexpenses = new SplitExpenseList();
        expensesStorage = new Storage("./data/ExpenseFile.txt");
        savingsStorage = new Storage("./data/SavingsFile.txt");
        recurringExpensesStorage = new Storage("./data/RecurringExpensesFile.txt");
        splitexpensesStorage = new Storage("./data/SplitExpensesFile.txt");
        defaultCurrency = new Storage("./data/DefaultCurrency.txt");
        budgetStorage = new Storage("./data/BudgetFile.txt");
    }

    public void handleCommands(String input) {
        Command command = parser.parseCommand(expenses, savings, splitexpenses, recurringExpenseLists, input);


        if (command != null) {
            command.execute();
        } else {
            System.out.println("(Invalid command)");
        }

        try {
            expensesStorage.saveExpenses(expenses.getExpenses());
            savingsStorage.saveSavings(savings.getSavings());
            recurringExpensesStorage.saveRecurringExpenses(recurringExpenseLists);
            budgetStorage.saveBudgets(expenses.getBudgets());
            splitexpensesStorage.saveSplitExpenses(splitexpenses.getSplitExpenses());

            defaultCurrency.saveCurrency();
        } catch (IOException e) {
            System.out.println("Error saving to file.");

        } catch (InvalidRecurringExpensesFileException e) {
            System.out.println(e.getMessage());
        }

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            defaultCurrency.loadCurrency();
            this.expenses.getExpenses().addAll(expensesStorage.loadExpenses());
            this.savings.getSavings().addAll(savingsStorage.loadSavings());
            this.splitexpenses.getSplitExpenses().addAll(splitexpensesStorage.loadSplitExpenses());
            this.recurringExpenseLists = recurringExpensesStorage.loadRecurringExpensesList();
            this.expenses.getBudgets().addAll(budgetStorage.loadBudgets());

        } catch (FileNotFoundException e) {
            System.out.println("No existing files found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Could not create files. Please ensure all files are present and are not directories");
        }

        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

            if (parser.isExitCommand(input)) {
                isExit = true;
            } else {
                handleCommands(input);
            }
        }

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) {
        new BudgetBuddy().run();
    }
}
