import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Pizza ordering GUI.
 */
public class PizzaGUIFrame extends JFrame
{
    private JRadioButton thinButton;
    private JRadioButton regularButton;
    private JRadioButton deepDishButton;

    private JComboBox<String> sizeComboBox;

    private JCheckBox pepperoniBox;
    private JCheckBox sausageBox;
    private JCheckBox mushroomBox;
    private JCheckBox onionBox;
    private JCheckBox baconBox;
    private JCheckBox extraCheeseBox;

    private JTextArea receiptArea;

    private JButton orderButton;
    private JButton clearButton;
    private JButton quitButton;

    /**
     * Creates the pizza order GUI.
     */
    public PizzaGUIFrame()
    {
        setTitle("Pizza Order Form");

        setSize(700, 650);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        topPanel.setLayout(new GridLayout(1, 3, 10, 10));

        topPanel.add(createCrustPanel());
        topPanel.add(createSizePanel());
        topPanel.add(createToppingsPanel());

        add(topPanel, BorderLayout.CENTER);

        JPanel bottomSection = new JPanel();

        bottomSection.setLayout(new BorderLayout());

        bottomSection.add(createReceiptPanel(), BorderLayout.CENTER);

        bottomSection.add(createButtonPanel(), BorderLayout.SOUTH);

        add(bottomSection, BorderLayout.SOUTH);
    }

    /**
     * Creates the crust selection panel.
     *
     * @return crust panel
     */
    private JPanel createCrustPanel()
    {
        JPanel crustPanel = new JPanel();

        crustPanel.setLayout(new GridLayout(3, 1));

        crustPanel.setBorder(
                new TitledBorder("Crust"));

        thinButton = new JRadioButton("Thin");
        regularButton = new JRadioButton("Regular");
        deepDishButton = new JRadioButton("Deep-dish");

        ButtonGroup crustGroup = new ButtonGroup();

        crustGroup.add(thinButton);
        crustGroup.add(regularButton);
        crustGroup.add(deepDishButton);

        crustPanel.add(thinButton);
        crustPanel.add(regularButton);
        crustPanel.add(deepDishButton);

        return crustPanel;
    }

    /**
     * Creates the size panel.
     *
     * @return size panel
     */
    private JPanel createSizePanel()
    {
        JPanel sizePanel = new JPanel();

        sizePanel.setBorder(
                new TitledBorder("Size"));

        String[] sizes =
                {"Select Size", "Small", "Medium", "Large", "Super"};

        sizeComboBox = new JComboBox<>(sizes);

        sizePanel.add(sizeComboBox);

        return sizePanel;
    }

    /**
     * Creates the topping panel.
     *
     * @return toppings panel
     */
    private JPanel createToppingsPanel()
    {
        JPanel toppingsPanel = new JPanel();

        toppingsPanel.setLayout(new GridLayout(6, 1));

        toppingsPanel.setBorder(
                new TitledBorder("Toppings - $1 Each"));

        pepperoniBox = new JCheckBox("Pepperoni");
        sausageBox = new JCheckBox("Sausage");
        mushroomBox = new JCheckBox("Mushrooms");
        onionBox = new JCheckBox("Onions");
        baconBox = new JCheckBox("Bacon");
        extraCheeseBox = new JCheckBox("Extra Cheese");

        toppingsPanel.add(pepperoniBox);
        toppingsPanel.add(sausageBox);
        toppingsPanel.add(mushroomBox);
        toppingsPanel.add(onionBox);
        toppingsPanel.add(baconBox);
        toppingsPanel.add(extraCheeseBox);

        return toppingsPanel;
    }

    /**
     * Creates the receipt panel.
     *
     * @return receipt panel
     */
    private JPanel createReceiptPanel()
    {
        JPanel receiptPanel = new JPanel();

        receiptPanel.setLayout(new BorderLayout());

        receiptPanel.setBorder(
                new TitledBorder("Receipt"));

        receiptArea = new JTextArea(12, 50);

        receiptArea.setEditable(false);

        receiptArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane =
                new JScrollPane(receiptArea);

        receiptPanel.add(scrollPane, BorderLayout.CENTER);

        return receiptPanel;
    }

    /**
     * Creates the button panel.
     *
     * @return button panel
     */
    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel();

        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        orderButton.addActionListener(e ->
                makeOrder());

        clearButton.addActionListener(e ->
                clearForm());

        quitButton.addActionListener(e ->
                quitProgram());

        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    /**
     * Creates and displays the pizza order.
     */
    private void makeOrder()
    {
        String crust = "";

        if (thinButton.isSelected())
        {
            crust = "Thin";
        }
        else if (regularButton.isSelected())
        {
            crust = "Regular";
        }
        else if (deepDishButton.isSelected())
        {
            crust = "Deep-dish";
        }

        String size =
                (String) sizeComboBox.getSelectedItem();

        if (crust.equals(""))
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a crust.");

            return;
        }

        if (sizeComboBox.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a pizza size.");

            return;
        }

        int toppingCount = 0;

        if (pepperoniBox.isSelected())
        {
            toppingCount++;
        }

        if (sausageBox.isSelected())
        {
            toppingCount++;
        }

        if (mushroomBox.isSelected())
        {
            toppingCount++;
        }

        if (onionBox.isSelected())
        {
            toppingCount++;
        }

        if (baconBox.isSelected())
        {
            toppingCount++;
        }

        if (extraCheeseBox.isSelected())
        {
            toppingCount++;
        }

        if (toppingCount == 0)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select at least one topping.");

            return;
        }

        double basePrice = 0.0;

        if (size.equals("Small"))
        {
            basePrice = 8.00;
        }
        else if (size.equals("Medium"))
        {
            basePrice = 12.00;
        }
        else if (size.equals("Large"))
        {
            basePrice = 16.00;
        }
        else if (size.equals("Super"))
        {
            basePrice = 20.00;
        }

        double toppingPrice = toppingCount * 1.00;

        double subtotal =
                basePrice + toppingPrice;

        double tax =
                subtotal * 0.07;

        double total =
                subtotal + tax;

        String receipt = "";

        receipt += "=========================================\n";

        receipt += crust + " Crust - "
                + size
                + " Pizza"
                + String.format("       $%.2f\n", basePrice);

        receipt += "-----------------------------------------\n";

        if (pepperoniBox.isSelected())
        {
            receipt += String.format(
                    "%-25s $%.2f\n",
                    "Pepperoni", 1.00);
        }

        if (sausageBox.isSelected())
        {
            receipt += String.format(
                    "%-25s $%.2f\n",
                    "Sausage", 1.00);
        }

        if (mushroomBox.isSelected())
        {
            receipt += String.format(
                    "%-25s $%.2f\n",
                    "Mushrooms", 1.00);
        }

        if (onionBox.isSelected())
        {
            receipt += String.format(
                    "%-25s $%.2f\n",
                    "Onions", 1.00);
        }

        if (baconBox.isSelected())
        {
            receipt += String.format(
                    "%-25s $%.2f\n",
                    "Bacon", 1.00);
        }

        if (extraCheeseBox.isSelected())
        {
            receipt += String.format(
                    "%-25s $%.2f\n",
                    "Extra Cheese", 1.00);
        }

        receipt += "-----------------------------------------\n";

        receipt += String.format(
                "Sub-total:              $%.2f\n",
                subtotal);

        receipt += String.format(
                "Tax:                    $%.2f\n",
                tax);

        receipt += "-----------------------------------------\n";

        receipt += String.format(
                "Total:                  $%.2f\n",
                total);

        receipt += "=========================================\n";

        receiptArea.setText(receipt);
    }

    /**
     * Clears all form selections.
     */
    private void clearForm()
    {
        thinButton.setSelected(false);
        regularButton.setSelected(false);
        deepDishButton.setSelected(false);

        sizeComboBox.setSelectedIndex(0);

        pepperoniBox.setSelected(false);
        sausageBox.setSelected(false);
        mushroomBox.setSelected(false);
        onionBox.setSelected(false);
        baconBox.setSelected(false);
        extraCheeseBox.setSelected(false);

        receiptArea.setText("");
    }

    /**
     * Confirms before quitting the program.
     */
    private void quitProgram()
    {
        int answer =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to quit?",
                        "Confirm Quit",
                        JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}