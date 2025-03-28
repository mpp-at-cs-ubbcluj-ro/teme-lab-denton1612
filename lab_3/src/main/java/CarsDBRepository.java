import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private final JdbcUtils dbUtils;



    private static final Logger logger = LogManager.getLogger(CarsDBRepository.class);

    public CarsDBRepository(Properties props) {
lp        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.trace("Find cars by manufacturer");
        List<Car> cars=new ArrayList<>();
        Connection conn = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from cars where manufacturer = ?")) {
            preparedStatement.setString(1, manufacturerN);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String manufacturer = resultSet.getString("manufacturer");
                    String model = resultSet.getString("model");
                    int year = resultSet.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: " + e);
        }
        logger.traceExit(cars);
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.trace("Find cars by year");
        List<Car> cars=new ArrayList<>();
        Connection conn = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from cars where year >= ? and year <= ?")) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String manufacturer = resultSet.getString("manufacturer");
                    String model = resultSet.getString("model");
                    int year = resultSet.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: " + e);
        }
        logger.traceExit(cars);
        return cars;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("saving task {}", elem);
        Connection conn = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("insert into cars (manufacturer, model, year) values (?, ?, ?)")) {
            preparedStatement.setlString(1, elem.getManufacturer());
            preparedStatement.setString(2, elem.getModel());
            preparedStatement.setInt(3, elem.getYear());
            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer id, Car elem) {
        logger.traceEntry("updating task {}", elem);
        Connection conn = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("update cars set model = ? where id = ?")) {
            preparedStatement.setString(1, elem.getModel());
            preparedStatement.setInt(2, elem.getId());
            int result = preparedStatement.executeUpdate();
            logger.trace("Updated {} instances", result);
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: " + e);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry("Find all cars");
        Connection conn = dbUtils.getConnection();
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from cars")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String manufacturer = resultSet.getString("manufacturer");
                    String model = resultSet.getString("model");
                    int year = resultSet.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: " + e);
        }
        logger.traceExit(cars);
        return cars;
    }

    @Override
    public Car findById(int id) {
        logger.traceEntry("Find car by id {}", id);
        Connection conn = dbUtils.getConnection();
        Car car = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from cars where id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String manufacturer = resultSet.getString("manufacturer");
                    String model = resultSet.getString("model");
                    int year = resultSet.getInt("year");
                    car = new Car(manufacturer, model, year);
                    car.setId(id);
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: " + e);
        }
        return car;
    }
}
