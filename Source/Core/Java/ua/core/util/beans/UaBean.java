package ua.core.util.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;


public abstract class UaBean {
	
	
	public static final char	TYPE_BOOLEAN	= 'B';
	public static final char	TYPE_CHAR		= 'C';
	public static final char	TYPE_DATE		= 'T';
	public static final char	TYPE_DOUBLE		= 'D';
	public static final char	TYPE_FLOAT		= 'F';
	public static final char	TYPE_INT		= 'I';
	public static final char	TYPE_STRING		= 'S';
	
	
	abstract public UaProperty[] getPropertyArray();

	
	public boolean getPropertyBoolean (String name) {
		
		return false;
	}
	
	public void setPropertyBoolean (String name, Boolean value) {
		
	}
	
	public char getPropertyChar (String name) {
		
		return 0;
	}
	
	public void setPropertyChar (String name, char value) {
		
	}
	
	public Date getPropertyDate (String name) {
		
		return null;
	}
	
	public void setPropertyDate (String name, Date value) {
		
	}
	
	public double getPropertyDouble (String name) {
		
		return 0.0;
	}
	
	public void setPropertyDouble (String name, double value) {
		
	}
	
	public float getPropertyFloat (String name) {
		
		return 0;
	}
	
	public void setPropertyFloat (String name, float value) {
		
	}
	
	public int getPropertyInt (String name) {
		
		return 0;
	}
	
	public void setPropertyInt (String name, int value) {
		
	}
	
	public String getPropertyString (String name) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		Class <?>[]	paramClassTypes	= null;
		Method		method			= null;
		Object 		result			= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		try {
			
			paramClassTypes	= new Class[] {};
			method			= this.getClass().getMethod (name, paramClassTypes);
			
			result			= method.invoke (this, (Object[]) null);
		}

		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		catch (NoSuchMethodException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result != null) {
			
			return ((String) result).toString();
		}
		else {
			
			return null;
		}
	}
	
	public void setPropertyString (String name, String value) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		Class <?>[]	paramClassTypes	= null;
		Method		method			= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		try {
			
			paramClassTypes	= new Class[] {String.class};
			method			= this.getClass().getMethod (name, paramClassTypes);
			
			method.invoke (this, (Object[]) null);
		}

		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		catch (NoSuchMethodException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
