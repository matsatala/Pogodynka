package pogodynka.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Localization_id", nullable = false)
    private Long localizationId;
    @Column(name = "city_name", nullable = false)
    private String cityName;
    @Column(name = "longitude")
    private float longitude; // -90 -> S   90 -> N
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "country_name", nullable = false)
    private String countryName;
    @OneToOne(mappedBy = "localization", fetch = FetchType.EAGER)
    private WeatherForecastAverage weatherForecastAverage;



    public Localization(){}

    public Localization( String cityName, float longitude, float latitude, String regionName, String countryName) {
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.regionName = regionName;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getLocalizationId() {
        return localizationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localization that = (Localization) o;
        return Objects.equals(localizationId, that.localizationId) && Objects.equals(cityName, that.cityName) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude) && Objects.equals(regionName, that.regionName) && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localizationId, cityName, longitude, latitude, regionName, countryName);
    }

    @Override
    public String toString() {
        return "Localization{" +
                "localizationId=" + localizationId +
                ", cityName='" + cityName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", regionName='" + regionName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
