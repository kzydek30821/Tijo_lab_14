package pl.edu.pwsztar;

import java.util.Optional;

final class UserId implements UserIdChecker {

    private final String id;    // NR. PESEL

    public UserId(final String id) {
        this.id = id.trim();
    }

    @Override
    public boolean isCorrectSize() {
        return id.length() == 11;
    }

    @Override
    public Optional<Sex> getSex() {
        return Integer.parseInt(this.id.substring(9, 10)) % 2 == 1 ? Optional.of(Sex.MAN) : Optional.of(Sex.WOMAN);
    }

    @Override
    public boolean isCorrect() {
        return isCorrectSize() && checkSum();
    }

    @Override
    public Optional<String> getDate() {
        if(!isCorrectSize()){
            return Optional.empty();
        }
        return Optional.of(getProperDate(getProperCentury()));
    }

    private boolean checkSum(){
        int[] weight = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3, 1};
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            sum += Integer.parseInt(id.substring(i, i + 1)) * weight[i];
        }

        return sum % 10 == 0;
    }

    private int getProperCentury(){
        int month = Integer.valueOf(id.charAt(2)+""+id.charAt(3));
        if(month >= 81 &&  month <= 92){
            return 80;
        }
        else if(month >= 21 && month <= 32){
            return 20;
        }
        else if(month >= 41 && month <= 52){
            return 40;
        }
        else if(month >= 61 && month <= 72){
            return 60;
        }
        return 1;
    }
    private String getProperDate(int century){
        String month = id.charAt(2)+""+id.charAt(3);

        if(century == 80){
            return id.charAt(4)+""+id.charAt(5)+"-"+month+"-"+"18"+id.charAt(0)+id.charAt(1);
        }
        else if(century == 20){
            return id.charAt(4)+""+id.charAt(5)+"-"+month+"-"+"20"+id.charAt(0)+id.charAt(1);
        }
        else if(century == 40){
            return id.charAt(4)+""+id.charAt(5)+"-"+month+"-"+"21"+id.charAt(0)+id.charAt(1);
        }
        else if(century == 60){
            return id.charAt(4)+""+id.charAt(5)+"-"+month+"-"+"22"+id.charAt(0)+id.charAt(1);
        }
        return id.charAt(4)+""+id.charAt(5)+"-"+month+"-"+"19"+id.charAt(0)+id.charAt(1);
    }
}
