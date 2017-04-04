package com.yash.util;

public interface BasicInitializationsForLDAP {

	/**
	 * INITIAL_CONTEXT_FACTORY ---> Constant that holds the name of the
	 * environment property . The value of the property should be the fully
	 * qualified class name of the factory class that will create an initial
	 * context
	 */
	String LDAP_INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

	/**
	 * SECURITY_AUTHENTICATION ---> Constant that holds the name of the
	 * environment property for specifying the security level to use . Its value
	 * is one of the following strings: "none", "simple", "strong"
	 */
	String LDAP_SECURITY_AUTHENTICATION = "simple";

	/**
	 * PROVIDER_URL ---> The value of the property should contain a URL string
	 * (e.g. "ldap://somehost:389") .
	 */
	String LDAP_PROVIDER_URL = "ldap://inidradc01.yash.com/";

	/**
	 * The Base of the directory structure , from which the searching is to be
	 * start
	 */
	String LDADP_SEARCH_BASE = "DC=yash,DC=com";

}
