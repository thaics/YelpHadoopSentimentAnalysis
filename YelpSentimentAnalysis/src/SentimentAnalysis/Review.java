package SentimentAnalysis;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Review{
	public String business_id;
    public String date;
    public String stars;
    public String text;
    public String type;
    public String user_id;
    
	public final String getBusiness_id() {
		return business_id;
	}
	public final void setBusiness_id(String review_id) {
		this.business_id = review_id;
	}
	public final String getDate() {
		return date;
	}
	public final void setDate(String date) {
		this.date = date;
	}
	public final String getStars() {
		return stars;
	}
	public final void setStars(String stars) {
		this.stars = stars;
	}
	public final String getText() {
		return text;
	}
	public final void setText(String text) {
		this.text = text;
	}
	public final String getType() {
		return type;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final String getUser_id() {
		return user_id;
	}
	public final void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    
    
}