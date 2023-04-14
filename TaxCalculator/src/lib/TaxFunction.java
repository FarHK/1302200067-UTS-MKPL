package lib;

public class TaxFunction {

	private static final int TAX_EXEMPTION_SINGLE = 54000000;
    private static final int TAX_EXEMPTION_MARRIED = 58500000;
    private static final int TAX_EXEMPTION_CHILD = 4500000;

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthsWorking > 12) {
            throw new IllegalArgumentException("Number of working months cannot exceed 12.");
        }

        int taxExemption = calculateTaxExemption(isMarried, numberOfChildren);
        int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthsWorking, deductible, taxExemption);
        int tax = (int) Math.round(0.05 * taxableIncome);

        return tax < 0 ? 0 : tax;
    }

    private static int calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorking, int deductible, int taxExemption) {
        int taxableMonthlyIncome = monthlySalary + otherMonthlyIncome - deductible;
        int totalTaxableIncome = taxableMonthlyIncome * numberOfMonthsWorking - taxExemption;

        return totalTaxableIncome < 0 ? 0 : totalTaxableIncome;
    }

    private static int calculateTaxExemption(boolean isMarried, int numberOfChildren) {
        int taxExemption = isMarried ? TAX_EXEMPTION_MARRIED : TAX_EXEMPTION_SINGLE;
        taxExemption += numberOfChildren * TAX_EXEMPTION_CHILD;

        return taxExemption;
    }
}
