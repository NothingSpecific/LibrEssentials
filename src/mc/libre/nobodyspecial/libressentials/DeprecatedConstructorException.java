package mc.libre.nobodyspecial.libressentials;

public class DeprecatedConstructorException extends Exception {
	private static final long serialVersionUID = -5356815869447929586L;
	private Class parent;
	private String parentName;
	private String stringValue; 
	public DeprecatedConstructorException(Class parent) {
		this.parent = parent;
		this.parentName = parent.getName();
		stringValue = "Deprecated constructor attempting to create `new " + parentName + "(...)`";
	}
	public DeprecatedConstructorException() {
		stringValue = "Deprecated constructor attempting to create `new <unknown class>(...)`";
	}
	public String toString() {
		return stringValue;
	}
}
