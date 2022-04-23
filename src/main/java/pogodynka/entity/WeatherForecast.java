package pogodynka.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class WeatherForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weather_forecast_id", nullable = false)
    private Long weatherForecastId;
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

    public WeatherForecast() {}

    public WeatherForecast(float temperature, float humidity, int pressure, float windSpeed, int windDirection, Date date) {
        this.temp = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getWeatherForecastId() {
        return weatherForecastId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecast that = (WeatherForecast) o;
        return temp == that.temp && humidity == that.humidity && pressure == that.pressure && windSpeed == that.windSpeed && Objects.equals(weatherForecastId, that.weatherForecastId) && windDirection == that.windDirection && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherForecastId, temp, humidity, pressure, windSpeed, windDirection, date);
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "weatherForecastId=" + weatherForecastId +
                ", temperature=" + temp +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                ", date=" + date +
                '}';
    }
}
