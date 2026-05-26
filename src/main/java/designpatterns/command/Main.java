package designpatterns.command;

public class Main {
    public static void main(String[] args){
        Light kitchenLight = new Light();
        Command onCommand = new LightOnCommand(kitchenLight);
        Command offCommand = new LightOffCommand(kitchenLight);

        RemoteControl remote = new RemoteControl(3);
        remote.setCommand(0, onCommand);
        remote.setCommand(1, offCommand);

        remote.pressButton(1);
        remote.pressButton(0);
        remote.undoLast();
    }
}
