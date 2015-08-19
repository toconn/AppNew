package ua.core.service.parameters;

public class Param {
	
	private	String		name		= null;
	private int			count		= 0;
	private boolean		hasError	= false;
	
	private ParamDef	paramDef	= null;
	
	private String[]	subParamStringArray;
	
	
	public Param (String name, String[] subparamStringArray, ParamDef paramDef) {
		
		this.name		= name;
		this.paramDef	= paramDef;		
		
		setSubparamStringArray (subparamStringArray);
	}
	
	public Param cloneMe () {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		Param		param;
		String []	newOptionsList;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		newOptionsList = this.subParamStringArray.clone();
		
		param = new Param (name, newOptionsList, paramDef);
		
		return param;
	}
	
	
	public boolean hasSubParams () {
		
		if (this.subParamStringArray != null && this.subParamStringArray.length > 0) {
			
			return true;
		}
		else {
			
			return false;
		}
	}

	
	public String getName () {
	
		return name;
	}

	
	public void setName (String name) {
	
		this.name = name;
	}

	
	public boolean hasError () {
	
		return hasError;
	}

	
	public void setError (boolean hasError) {
	
		this.hasError = hasError;
	}

	
	public ParamDef getParamDef () {
	
		return paramDef;
	}

	
	public void setParamDef (ParamDef paramDef) {
	
		this.paramDef = paramDef;
	}
	
	
	public String getSubParam (int index) {
		
		if (subParamStringArray != null && subParamStringArray.length > index) {
			
			return subParamStringArray[index];
		}
		else {
			
			return null;
		}
	}

	
	public String getSubParamsAsString () {
		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringBuilder	subParamsStringBuilder	= null;
		
		boolean			first;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		if (hasSubParams()) {
			
			subParamsStringBuilder = new StringBuilder (); 
			first = true;

			for (String subParam: subParamStringArray) {
				
				if (first) {
					
					first = false;
				}
				else {
					
					subParamsStringBuilder.append (" ");
				}
				
				subParamsStringBuilder.append (subParam);
			}
			
			return subParamsStringBuilder.toString ();
		}
		else {
			
			return null;
		}
	}

	
	public String[] getSubparamStringArray () {
	
		return subParamStringArray;
	}

	
	public void setSubparamStringArray (String[] subparamStringArray) {
	
		this.subParamStringArray = subparamStringArray;
		
		if (subparamStringArray == null) {
			
			this.count = 0;
		}
		else {
			
			this.count = subparamStringArray.length;
		}
	}

	
	/**
	 * Returns the subparameter count...
	 * @return
	 */
	
	public int getSubparamCount () {
	
		return count;
	}
}
