import javax.swing.*;

public class Taxi extends PublicTransport{
    int maxPassenger = 4;
    int currentPassenger = 0;
    int fee = 3000;

    public void setNumber(int many){
        String[] carName = {"가","나","다","라","마","바","사","아","자","차","카","타","파","하"};

        for (int i = 0; i < many; i++) {
            int randomNumber = (int)(Math.random() * 13) + 1;
            int randomNumber2 = (int)(Math.random() * (99 - 10 + 1)) + 10;
            int randomNumber4 = (int)(Math.random() * (9999 - 1000 + 1)) + 1000;
            System.out.println("차량 번호 = " + randomNumber2 + carName[randomNumber-1] + randomNumber4);
            System.out.println("주유량 = " + oil);
            System.out.println(startStaus());
        }
    }

    public String startStaus() { return "상태 = 운행중";}

    public String stopStatus() { return "상태 = 일반";}

    public String failStatus() { return "상태 = 운행불가";}
    int pay = 0;
    public void inPassenger(int inPassenger, String destination, int distance) {
        int taxiFee = fee + ((distance - 1) * 1000);
        if (currentPassenger + inPassenger > maxPassenger) {
            JOptionPane.showMessageDialog(null, "택시 최대 승객 수 초과");
        }else {
            currentPassenger += inPassenger;
            System.out.println("택시 탑승 승객 수 = " + inPassenger);
            System.out.println("택시 잔여 승객 수 = " + (maxPassenger - currentPassenger));
            System.out.println("기본 요금 확인 = " + fee);
            System.out.println("목적지 = " + destination);
            System.out.println("목적지까지 거리 = " + distance + "km");
            System.out.println("지불할 요금 = " + taxiFee);
            pay += taxiFee;
            currentPassenger = 0;
            System.out.println(startStaus());
        }
    }
    public String pay() {
        System.out.println("누적 요금 = " + pay);
        return "";
    }

    public void setOil(int oiling) {
        if (this.oil + oiling > 100) {
            System.out.println("택시 주유량이 가득 찼습니다.");
        }else if (this.oil + oiling <= 5){
            this.oil += oiling;
            System.out.println("택시 주유량 = " + oil);
            System.out.println(failStatus());
            System.out.println(pay());
            JOptionPane.showMessageDialog(null, "주유 필요");
        }else {
            this.oil += oiling;
            System.out.println("택시 주유량 = " + oil);
        }
    }
}
