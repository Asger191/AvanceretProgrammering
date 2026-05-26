package designpatterns.adapter;

public class Main {
    public static void main(String[] args){
        NewPrinter printerAdapter = new PrinterAdapter(new OldPrinter());

        printerAdapter.print();
    }
}
