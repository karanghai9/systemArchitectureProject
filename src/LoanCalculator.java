import java.util.Scanner;

public class LoanCalculator {

    public static void main(String[] args) {
        // Get input values from the user
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter debt value (in euros): ");
        double debt = scanner.nextDouble();

        System.out.print("Enter interest rate (percentage): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter payback duration (months): ");
        int duration = scanner.nextInt();

        // Close the scanner to avoid resource leak
        scanner.close();

        // Calculate loan parameters
        LoanParameters loanParams = calculateLoan(debt, interestRate, duration);

        // Display the results
        System.out.printf("\nMonthly Payment: %.2f euros\n", loanParams.getMonthlyPayment());
        System.out.println("Number of Payments: " + loanParams.getNumOfPayments());
        System.out.printf("Overall Debt: %.2f euros\n", loanParams.getOverallDebt());
    }

    private static LoanParameters calculateLoan(double debt, double interestRate, int duration) {
        // Convert interest rate to decimal
        double interestRateDecimal = interestRate / 100.0;

        // Calculate monthly interest rate
        double monthlyInterestRate = interestRateDecimal / 12.0;

        // Calculate monthly payment using the formula for a fixed-rate loan
        double monthlyPayment = (debt * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -duration));

        // Calculate overall debt
        double overallDebt = monthlyPayment * duration;

        return new LoanParameters(monthlyPayment, duration, overallDebt);
    }
}

class LoanParameters {
    private final double monthlyPayment;
    private final int numOfPayments;
    private final double overallDebt;

    public LoanParameters(double monthlyPayment, int numOfPayments, double overallDebt) {
        this.monthlyPayment = monthlyPayment;
        this.numOfPayments = numOfPayments;
        this.overallDebt = overallDebt;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public int getNumOfPayments() {
        return numOfPayments;
    }

    public double getOverallDebt() {
        return overallDebt;
    }
}
