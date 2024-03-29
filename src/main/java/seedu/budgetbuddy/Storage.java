package seedu.budgetbuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    private final String filePath;
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());


    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // This will create the directory if it doesn't exist
        }
        try {
            file.createNewFile(); // This will create the file if it doesn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> loadExpenses() throws FileNotFoundException {
        File file = new File(filePath);
        List<Expense> expenses = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            // Assuming the order is Date|Category|Amount|Description
            String category = parts[1].trim();
            double amount = Double.parseDouble(parts[2].trim());
            String description = parts[3].trim();
            Expense expense = new Expense(category, amount, description);
            expenses.add(expense);
        }
        scanner.close();
        return expenses;
    }

    public void saveExpenses(List<Expense> expenses) throws IOException {
        ensureDirectoryExists(); // Ensure directory and file exist before writing
        FileWriter writer = new FileWriter(filePath, false); // Overwrite the file
        for (Expense expense : expenses) {
            writer.write(String.format("%s | %s | %.2f | %s\n",
                    expense.getDateAdded(), expense.getCategory(), expense.getAmount(), expense.getDescription()));
        }
        writer.close();
    }

    // Inside Storage.java

    public List<Saving> loadSavings() throws FileNotFoundException {
        File file = new File(filePath);
        List<Saving> savings = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            // Assuming the order is Category|Amount
            String category = parts[0].trim();
            double amount = Double.parseDouble(parts[1].trim());
            Saving saving = new Saving(category, amount);
            savings.add(saving);
        }
        scanner.close();
        return savings;
    }

    public void saveSavings(List<Saving> savings) throws IOException {
        ensureDirectoryExists(); // Ensure directory and file exist before writing
        FileWriter writer = new FileWriter(filePath, false); // Overwrite the file
        for (Saving saving : savings) {
            writer.write(String.format("%s | %.2f\n",
                    saving.getCategory(), saving.getAmount()));
        }
        writer.close();
    }

    // Save Currency
    public void saveCurrency(List<Expense> expenses, List<Saving> savings) throws IOException {
        ensureDirectoryExists();
        FileWriter writer = new FileWriter(filePath, false);

        try {
            if (savings.isEmpty() && expenses.isEmpty()) {
                writer.write("Default Currency: SGD");
                writer.close();
            } else if (!savings.isEmpty() && !expenses.isEmpty()) {
                // Assert savings and expenses currency are equal
                writer.write(String.format("Default Currency: %s\n", savings.get(0).getCurrency()));
                writer.close();
            } else if (savings.isEmpty()) {
                writer.write(String.format("Default Currency: %s\n", expenses.get(0).getCurrency()));
                writer.close();
            } else {
                writer.write(String.format("Default Currency: %s\n", savings.get(0).getCurrency()));
                writer.close();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problem saving currency code", e);
        }
    }

    // Load Currency
    public void loadCurrency(List<Expense> expenses, List<Saving> savings) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(": ");
            Currency currency = Currency.getInstance(parts[1].trim());

            for (Saving saving : savings) {
                saving.setCurrency(currency);
            }

            for (Expense expense : expenses) {
                expense.setCurrency(currency);
            }
        }
        scanner.close();
    }

}
