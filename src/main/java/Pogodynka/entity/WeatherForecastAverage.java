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
    @Column(name = "temp")
    private float temp;
    @Column(name = "humidity")
    private float humidity;
    @Column(name = "atmospheric_pressure")
    private int pressure;
    @Column(name = "wind_speed")
    private float windSpeed;
    @Column(name = "wind_direction")
    private int windDirection;
    @Column(name = "date")
    private Date date;

    public WeatherForecastAverage() {}

    public WeatherForecastAverage(float temperature, float humidity, int pressure, float windSpeed, int windDirection, Date date) {
        this.temp = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.date = date;
    }

    public float getTemperature() {
        return temp;
    }

    // TODO
    public void setTemperature(float temperature) {
        this.temp = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    // TODO
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    // TODO
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    // TODO
    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    // TODO
    public void setWindDirection(int windDirection) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastAverage that = (WeatherForecastAverage) o;
        return temp == that.temp && humidity == that.humidity && pressure == that.pressure && windSpeed == that.windSpeed && Objects.equals(weatherForecastAverageId, that.weatherForecastAverageId) && windDirection == that.windDirection && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherForecastAverageId, temp, humidity, pressure, windSpeed, windDirection, date);
    }
}
