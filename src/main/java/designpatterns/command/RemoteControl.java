package designpatterns.command;

import java.util.ArrayList;
import java.util.List;

public class RemoteControl {
    private Command[] slots;
    private List<Integer> calls = new ArrayList<>();


    public RemoteControl(int size) {
        slots = new Command[size];
    }

    public void setCommand(int slot, Command command) {
        slots[slot] = command;
    }

    public void pressButton(int slot) {
        if (slots[slot] != null) {
            slots[slot].execute();
            calls.add(slot);
        } else {
            System.out.println("No command in slot " + slot);
        }
    }

    public void undoLast(){
        if(calls.isEmpty()){
            System.out.println("Nothing to undo");
            return;
        }

        int lastCall = calls.get(calls.size() - 1);
        slots[lastCall].undo();
        calls.remove(lastCall);
    }
}

