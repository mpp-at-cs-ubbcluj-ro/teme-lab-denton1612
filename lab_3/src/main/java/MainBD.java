import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainBD {
    public static void main(String[] args) {

        Properties props=new Properties();
        try {
            props.load(new FileReader("/Users/stiubedenis/Desktop/Facultate/Faculty/Teme/An_2/Sem_2/mpp/teme-lab-denton1612/lab_3/src/bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        CarRepository carRepo=new CarsDBRepository(props);
        carRepo.add(new Car("Tesla","Model S", 2019));
        System.out.println("Toate masinile din db");
        for(Car car:carRepo.findAll())
            System.out.println(car);
       String manufacturer="Tesla";
        System.out.println("Masinile produse de "+manufacturer);
        for(Car car:carRepo.findByManufacturer(manufacturer))
            System.out.println(car);
        Car car = carRepo.findById(2);
        System.out.println("Masina cu id = 2: " + car);
        car.setModel("AMG");
        carRepo.update(2, car);
        System.out.println("Masina cu id = 2: " + carRepo.findById(2));

    }
}
