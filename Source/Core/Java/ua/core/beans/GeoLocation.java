package ua.core.beans;



public class GeoLocation implements IGeoLocation {
	
	private float	latitude	= 0.0f;
	private float	longitude	= 0.0f;
	
	
	public GeoLocation() { }


	public GeoLocation (float latitude, float longitude) {

		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}


	
	public float getLatitude() {
	
		return latitude;
	}


	
	public void setLatitude (float latitude) {
	
		this.latitude = latitude;
	}


	
	public float getLongitude() {
	
		return longitude;
	}


	
	public void setLongitude (float longitude) {
	
		this.longitude = longitude;
	}


	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits (latitude);
		result = prime * result + Float.floatToIntBits (longitude);
		return result;
	}

	
	public boolean equals (Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoLocation other = (GeoLocation) obj;
		if (Float.floatToIntBits (latitude) != Float.floatToIntBits (other.latitude))
			return false;
		if (Float.floatToIntBits (longitude) != Float.floatToIntBits (other.longitude))
			return false;
		return true;
	}
	
	

}
