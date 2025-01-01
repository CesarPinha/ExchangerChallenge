import com.google.gson.annotations.SerializedName;

public class CambioAPI {
    private String result;
    private String documentation;
    private String terms_of_use;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("target_code")
    private String targetCode;

    @SerializedName("conversion_rate")
    private Double conversionRate;

    // Getters y Setters

    public String getResult() {
        return result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getTerms_of_use() {
        return terms_of_use;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    // Opcional: toString para depuración
    @Override
    public String toString() {
        return "CambioAPI{" +
                "result='" + result + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", targetCode='" + targetCode + '\'' +
                ", conversionRate=" + conversionRate +
                '}';
    }
}
