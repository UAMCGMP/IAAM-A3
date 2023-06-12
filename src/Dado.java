import java.util.Arrays;

public class Dado {
    private double [] referencias;

    public double[] getReferencias() {
        return referencias;
    }

    public Dado(double[] referencias) {
        this.referencias = referencias;
    }

    @Override
    public String toString(){
        return "Dado{" + "vetor=" + Arrays.toString(referencias)+"}";
    }
}
