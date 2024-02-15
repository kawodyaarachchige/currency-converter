package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class MainController extends VBox {
    private TextField amountTextField;
    private ComboBox<String> fromComboBox;
    private ComboBox<String> toComboBox;
    private Button convertButton;
    private Label resultLabel;

    private Map<String, Double> conversionRates = new HashMap<>();

    public MainController() {
        // Initialize UI elements
        amountTextField = new TextField();
        fromComboBox = new ComboBox<>();
        toComboBox = new ComboBox<>();
        convertButton = new Button("Convert");
        resultLabel = new Label();

        // Populate ComboBoxes with currency options
        fromComboBox.getItems().addAll("USD", "EUR", "GBP", "JPY","LKR");

        toComboBox.getItems().addAll("USD", "EUR", "GBP", "JPY","LKR");

        // Set default selections
        fromComboBox.getSelectionModel().selectFirst();
        toComboBox.getSelectionModel().select(1); // Select EUR

        // Add action event handler for the Convert button
        convertButton.setOnAction(event -> convert());

        // Add UI elements to the layout
        setSpacing(10);
        setPadding(new Insets(10));
        getChildren().addAll(
                new Label("Amount:"), amountTextField,
                new Label("From Currency:"), fromComboBox,
                new Label("To Currency:"), toComboBox,
                convertButton,
                resultLabel
        );

        // Initialize conversion rates (for example purposes) නිවරදි රේට්ස් ඇඩ් කරගන්න ඉතිම් :)
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 0.86);
        conversionRates.put("GBP", 0.75);
        conversionRates.put("JPY", 108.85);
        conversionRates.put("LKR", 202.50);

        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        convertButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        convertButton.setTextFill(Color.WHITE);
        toComboBox.setStyle("-fx-background-color:#C5C0AD; -fx-text-fill: black;");
        fromComboBox.setStyle("-fx-background-color:#C5C0AD; -fx-text-fill: black;");
        resultLabel.setStyle("-fx-text-fill: black;");

    }

    // Method to perform currency conversion
    private void convert() {
        String fromCurrency = fromComboBox.getValue();
        String toCurrency = toComboBox.getValue();
        double amount;

        try {
            amount = Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!");
            return;
        }

        double convertedAmount = performConversion(amount, fromCurrency, toCurrency);
        resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
    }

    // Method to perform the currency conversion
    private double performConversion(double amount, String fromCurrency, String toCurrency) {
        // Retrieve conversion rates
        double fromRate = conversionRates.get(fromCurrency);
        double toRate = conversionRates.get(toCurrency);

        // Perform conversion
        return amount * (toRate / fromRate);
    }
}

