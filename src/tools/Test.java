package tools;

public class Test
{
    

    public static void main(String[] args){
        System.out.println("millis:"+System.currentTimeMillis());
        System.out.println("max:"+Integer.MAX_VALUE);
        int time=(int)(System.currentTimeMillis()%Integer.MAX_VALUE);
        System.out.println("end:"+time);
        System.out.println("end:"+time/(1000*60*24));
    }

}
