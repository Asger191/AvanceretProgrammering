package designpatterns.builder;

public class Main {
    public static void main(String[] args){
        User user = new User.Builder("Asger", "Asger@skole.dk")
                .country("Danmark")
                .phone("75849324")
                .address("Klintemarken 46")
                .newsletter(true)
                .build();

        User user2 = new User.Builder("Daniel", "Daniel@skole.dk").build();

        System.out.println(user);
        System.out.println(user2);
    }
}
