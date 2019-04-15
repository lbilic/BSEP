**Za generisanje i eksportovanje sertifikata servera:**

- keytool -genkey -alias SiemServer -keyalg RSA -validity 1825 -keystore "SiemServer.jks" -storetype JKS -dname "CN=megatravel.com,OU=MegaTravel,O=MegaTravel,L=Novi Sad,ST=Serbia,C=RS" -keypass password -storepass password

- keytool -exportcert -alias SiemServer -keystore SiemServer.jks -file SiemServer.cer
	
**Za generisanje i eksportovanje sertifikata agenta:**

- keytool -genkey -alias SiemAgent -keyalg RSA -validity 1825 -keystore SiemAgent.jks -storetype JKS -dname "CN=siemagent.com,OU=Siem Agent,O=Siem Agent,L=Novi Sad,ST=Serbia,C=RS" -keypass password -storepass password

- keytool -exportcert -alias SiemAgent -keystore SiemAgent.jks -file SiemAgentPublic.cer

**Za importovanje sertifikata u keystore:**

- keytool -importcert -alias SiemServer -keystore SiemAgent.jks -file SiemServer.cer

- keytool -importcert -alias SiemAgent -keystore SiemServer.jks -file SiemAgentPublic.cer
