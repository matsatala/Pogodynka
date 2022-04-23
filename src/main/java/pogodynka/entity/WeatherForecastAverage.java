package Pogodynka.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class WeatherForecastAverage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weather_forecast_average_id", nullable = false)
    private Long weatherForecastAverageId;
    @Column(name = "temperature")
    private int temperature;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "atmospheric_pressure")
    private int pressure;
    @Column(name = "wind_speed")
    private int windSpeed;
    @Column(name = "wind_direction")
    @Enumerated(EnumType.STRING)
    private WindDirection windDirection;
    @Column(name = "date")
    private Date date;

    public WeatherForecastAverage() {}

    public WeatherForecastAverage(int temperature, int humidity, int pressure, int windSpeed, WindDirection windDirection, Date date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    // TODO
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    // TODO
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    // TODO
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    // TODO
    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    // TODO
    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getWeatherForecastAverageId() {
        return weatherForecastAverageId;
    }

    public void setWeatherForecastAverageId(Long weatherForecastAverageId) {
        this.weatherForecastAverageId = weatherForecastAverageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastAverage that = (WeatherForecastAverage) o;
        return temperature == that.temperature && humidity == that.humidity && pressure == that.pressure && windSpeed == that.windSpeed && Objects.equals(weatherForecastAverageId, that.weatherForecastAverageId) && windDirection == that.windDirection && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherForecastAverageId, temperature, humidity, pressure, windSpeed, windDirection, date);
    }
}
