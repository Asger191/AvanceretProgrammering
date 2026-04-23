package algorithms.circularlinkedlist;



import java.util.List;

public class Main {

    public static void main(String[] args) {
        Node list = ListFactory.buildList(1, 2, 3, 4, 5);
        if(!hasCircle(list))
        System.out.println(list);

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

        Node circularList = ListFactory.buildListWithCycle();
        System.out.println("Tjekker cirkulær liste for cyklus...");
        if(!hasCircle(circularList)) {
            System.out.println("Ingen cyklus fundet - udskriver liste:");
            System.out.println(circularList);
        }
    }

    public static boolean hasCircle(Node head){

        Node slow = head;
        Node fast = head;
        int step = 0;

        while(fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;
            step++;

            // Udskriver hvor skilpadden og haren er efter hvert skridt
            System.out.println("Skridt " + step + ":");
            System.out.println("  Skilpadde (slow) -> node med værdi: " + slow.value);
            System.out.println("  Hare (fast)       -> node med værdi: " + fast.value);

            if(slow == fast){
                System.out.println("  >> Skilpadde og hare mødes! Cyklus opdaget ved node med værdi: " + slow.value);
                return true;
            }

            System.out.println("  >> Ingen møde endnu, fortsætter...");
        }

        System.out.println("Haren nåede slutningen - ingen cyklus fundet");
        return false;
    }

}
