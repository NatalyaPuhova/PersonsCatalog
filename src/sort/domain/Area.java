package sort.domain;

public enum Area {
    ELECTRICIAN("Elect"), ENGINEER("Eng"), BUILDER("Build");
    private String code;

    Area(String code){
        this.code=code;
    }
    private String getCode(){
        return code;
    }

    public static Area getEnumByCode(String str){
        for (Area a:values()){
            if (a.getCode().equals(str)){
                return a;
            }
        }
        throw new IllegalArgumentException("str="+str+"   -IllegalArgumentException");
    }


}
