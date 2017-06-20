package tools;

public class StringCreater
{
    

    public static void main(String[] args){
    
        String info="";
        System.out.println("<table border=0 cellspacing=0 cellpadding=0>");
        for(int i=0;i<10;i++){
            System.out.println("<tr><td nowrap>");
            for(int j=0;j<9;j++){
                System.out.print("<img src=maps/img"+j+"_"+i+".png>");
            }
            System.out.println("</tr></td>");
        }
        System.out.println("</table>");
        
        
        
        
    }

}
