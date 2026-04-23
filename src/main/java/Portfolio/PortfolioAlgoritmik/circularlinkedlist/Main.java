package Portfolio.PortfolioAlgoritmik.circularlinkedlist;


public class Main {

    public static void main(String[] args) {
        Node list = ListFactory.buildList(1, 2, 3, 4, 5);
        if(!hasCircle(list))
        System.out.println(list);

        Node circularList = ListFactory.buildListWithCycle();
         if(!hasCircle(circularList))
        System.out.println(circularList);
    }

    public static boolean hasCircle (Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){

             slow = slow.next;
             fast = fast.next.next;

            if(slow == fast){
                System.out.println("Den er cirkulær");
                return true;
            }
        }

        return false;
    }

}
