package reflection.fieldsandmethods;

import java.lang.reflect.*;

public class MysteryInspector {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("Brug: java MysteryInspector <fully.qualified.ClassName>");
            return;
        }

        String className = args[0];  // fx "reflection.fieldsandmethods.SecretBox"
        Class<?> clazz = Class.forName(className);

        // 1. Lav et objekt

        Object box = clazz.getDeclaredConstructor().newInstance();

        // 2. Udskriv klassens navn
        System.out.println("Klasse: " + clazz.getName());

        // 3. Udskriv alle metoder inkl access modifier, returtype, navn og parameterliste
        // Obs: du kan kun udskrive navnet på parametrene fordi klassen er kompileret med -parameters. '
        // Dette sættes under File -> Settings -> Build, Execution, Deployment → Compiler → Java Compiler hvor man
        // ved "Additional command line parameters" skriver -parameters. Vælg herefter Apply og Ok
        // Derefter skal du rekompilere koden. Vælg Build -> Rebuild Project





        System.out.println("Methods:");

        for (Method method : clazz.getDeclaredMethods()) {
           int synlighed = method.getModifiers();
           String returType = method.getReturnType().getSimpleName();
           String navn = method.getName();


            // Saml parameterliste
            StringBuilder params = new StringBuilder();
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                String type = parameters[i].getType().getSimpleName();
                String paramName = parameters[i].getName(); // Navnet er kun korrekt hvis kompileret med -parameters
                params.append(type).append(" ").append(paramName);
                if (i < parameters.length - 1) {
                    params.append(", ");
                }
            }

            System.out.println("Synlighed: " + synlighed + " returType: " + returType + " navn: " + navn + " parameterliste: " + "(" + params + ")");
        }



        // 4. Kald metoder på dit objekt
        Method reveal = clazz.getDeclaredMethod("reveal");
        String result = (String) reveal.invoke(box);
        System.out.println("\nKald reveal(): " + result);

        Method getSecret = clazz.getDeclaredMethod("getSecret");
        getSecret.setAccessible(true);
        String result1 = (String) getSecret.invoke(box);
        System.out.println("\nKald getSecret(): " + result1);

        // 5. Udskriv alle felter inkl værdier
        System.out.println("Felter:");

    }
}
