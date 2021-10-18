package common;

public class Validate {
    public static boolean regexCodeCustomer(String string){
        String regex = "^(KH)-[0-9]{4}$";
        return string.matches(regex);
    }
    public static boolean regexCodeService(String string){
        String regex = "^(DV)-[0-9]{4}$";
        return string.matches(regex);
    }

    public static boolean regexPhone(String string){
        String regex = "(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$";
        return string.matches(regex);
    }

    public static boolean regexIdCard(String string){
        String regex = "^[0-9]{9}|[0-9]{12}$";
        return string.matches(regex);
    }
    public static boolean regexEmail(String string){
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
        return string.matches(regex);
    }

    public static boolean regexDate(String string){
        String regex = "^([012][0-9]{1}|3[01]{1})\\/(0{1}[1-9]{1}|1[012]{1})\\/[12]{1}[0-9]{3}$";
        return string.matches(regex);
    }

    public static boolean regexNumber(String string){
        String regex ="^[0-9]{1,}[0-9]*$";
        return string.matches(regex);
    }


}
