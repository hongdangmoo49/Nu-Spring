import javax.swing.*;

public class Bus extends PublicTransport {
    int maxPassenger = 30;
    int currentPassenger;
    int fee = 1000;
    String condition = "";

    public void setNumber(int many){
        String[] carName = {"가","나","다","라","마","바","사","아","자","차","카","타","파","하"};

        for (int i = 0; i < many; i++) {
            int randomNumber = (int)(Math.random()*14)+1;
            int randomNumber2 = (int)(Math.random()+(99 - 10 + 1))+ 10;
            int randomNumber4 = (int)(Math.random()+(9999 - 1000 + 1)) + 1000;
            System.out.println("차량 번호 = " + randomNumber2 + carName[randomNumber-1] + randomNumber4);
        }
    }
    public void inPassenger(int inPassenger) {
        if (currentPassenger + inPassenger > maxPassenger) {
            JOptionPane.showMessageDialog(null, "버스 최대 승객 수 초과");
        }else {
            currentPassenger += inPassenger;
            System.out.println("버스 탑승 승객 수 = " + inPassenger);
            System.out.println("버스 잔여 승객 수 = " + (maxPassenger - currentPassenger));
            System.out.println("버스 요금 확인 = " + inPassenger * fee);
        }
    }
    public void outPassenger(int outPassenger) {
        if (currentPassenger - outPassenger < 0) {
            JOptionPane.showMessageDialog(null, "내릴 승객이 없습니다.");
        } else {
            currentPassenger -= outPassenger;
            System.out.println("버스 탑승 승객 수 = " + outPassenger);
            System.out.println("버스 잔여 승객 수 = " + (maxPassenger + outPassenger));
        }
    }

    public void setOil(int oiling) {
        if (this.oil + oiling > 100) {
            System.out.println("버스 주유량이 가득 찼습니다.");
        }else if (this.oil + oiling <= 5) {
            this.oil += oiling;
            System.out.println("버스 주유량 = " + oil);
            System.out.println("상태 = 차고지행");
            JOptionPane.showMessageDialog(null, "주유 필요");
        }else {
            this.oil += oiling;
            System.out.println("버스 주유량 = " + oil);
        }
    }

    public void setCondition(String condition){
        System.out.println(condition);
    }
}
