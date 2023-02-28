/**
 * Structure of Computer class
 */
public class Computer {
    private String brand;
    private String model;
    private long SN;
    private double price;

    private static int computerCount = 0;

    /**
     * A parameterized constructor
     *
     * @param brand name of brand
     * @param model name of model
     * @param SN    serial number
     * @param price price of computer
     */
    public Computer(String brand, String model, long SN, double price) {
        this.brand = brand;
        this.model = model;
        this.SN = SN;
        this.price = price;
        computerCount++;
    }

    /**
     * Set value of brand
     *
     * @param brand
     */
    public void setBrand(String brand) {
        if (brand.isEmpty()) {
            System.out.println("Brand name cannot be empty");
        } else {
            this.brand = brand;
        }
    }

    /**
     * Set the value of model
     *
     * @param model
     */
    public void setModel(String model) {
        if (model.isEmpty()) {
            System.out.println("Model name cannot be empty");
        } else {
            this.model = model;
        }
    }

    /**
     * Set value of price
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set value of Serial number
     *
     * @param SN
     */
    public void setSN(long SN) {
        this.SN = SN;
    }

    /**
     * Get the value of Price
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the value of brand
     *
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get the value of Model
     *
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * Get the value of Serial Number
     *
     * @return
     */
    public long getSN() {
        return SN;
    }

    /**
     * Returns a string that conatins allthe information about the computer
     *
     * @return
     */
    @Override
    public String toString() {
        return "Brand:" + brand + ", Model:" + model + ", SN:" + SN + ", Price:$" + price;
    }

    /**
     * Checks if two computer objects are equal or not
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        boolean flag = false;

        if (this.brand.equals(((Computer) obj).brand) &&
                this.model.equals((((Computer) obj).model)) &&
                this.price == ((Computer) obj).price) {
            flag = true;
        }
        return flag;
    }

    /**
     * Returns the number of computer objects created
     *
     * @return
     */
    public static int findNumberOfCreatedComputers() {
        return computerCount;
    }
}
