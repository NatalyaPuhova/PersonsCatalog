package sort;

import java.time.LocalDate;

public class CheckProperties {
    public boolean checkSymmetry(Object p1,Object p2){
        if ((p1.equals(p2))&&(p2.equals(p1))){
            return  true;
        }
        return false;
    }
    public boolean checkReflection(Object p){
        if ((p.equals(p))&&(p.equals(p))){
            return  true;
        }
        return false;
    }
    public boolean checkTransitivity(Object p1,Object p2,Object p3){
        if ((p1.equals(p2))&&(p2.equals(p3)&&(p1.equals(p3)))){
            return  true;
        }
        return false;
    }




}
