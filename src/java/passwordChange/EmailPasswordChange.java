package passwordChange;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Eneko
 */
public class EmailPasswordChange {

    static String sSalt = "abcdefghijklmnop";
    private static byte[] salt = sSalt.getBytes();
    private static final String clave = "turra";
    private static final String emailClaro = "bloomingnerdsl@gmail.com";
    private static final String contraseñaClaro = "npatjmknwrnjdfsq";
    //Get the mail and password using simetric decrypt
    private static String email = descifrarTextoMail(clave);
    private static String contraseña = descifrarTextoPasswd(clave);
    private static String receiver = "";
    private static String recuperacion = generateRandomPassword(25);

    public String getRecuperacion() {
        return recuperacion;
    }
    private static final String emailBody = "<div class=\"\"><div class=\"aHl\"></div><div id=\":13j\" tabindex=\"-1\"></div><div id=\":13c\" class=\"ii gt\" jslog=\"20277; u014N:xr6bB; 4:W251bGwsbnVsbCxbXV0.\"><div id=\":14n\" class=\"a3s aiL msg6794591027225329141\"><u></u>\n"
            + "\n"
            + "\n"
            + "  \n"
            + "  \n"
            + "  \n"
            + "  \n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "    \n"
            + "    \n"
            + "  \n"
            + "\n"
            + "<div style=\"background:#f9f9f9\">\n"
            + "  <div style=\"background-color:#f9f9f9\">\n"
            + "  \n"
            + "<div style=\"margin:0px auto;max-width:640px;background:transparent\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:transparent\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:40px 0px\"><div aria-labelledby=\"mj-column-per-100\" class=\"m_6794591027225329141mj-column-per-100\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;padding:0px\" align=\"center\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border-spacing:0px\" align=\"center\" border=\"0\"><tbody><tr><td style=\"width:138px\"></td></tr></tbody></table></td></tr></tbody></table></div></td></tr></tbody></table></div>\n"
            + "      <div style=\"max-width:640px;margin:0 auto;border-radius:4px;overflow:hidden\"><div style=\"margin:0px auto;max-width:640px;background:#ffffff\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:#ffffff\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:40px 50px\"><div aria-labelledby=\"mj-column-per-100\" class=\"m_6794591027225329141mj-column-per-100\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;padding:0px\" align=\"left\"><div style=\"color:#737f8d;font-family:Whitney,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;font-size:16px;line-height:24px;text-align:left\">\n"
            + "            \n"
            + "  <h2 style=\"font-family:Whitney,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;font-weight:500;font-size:20px;color:#4f545c;letter-spacing:0.27px\">Hey Dear Client,</h2>\n"
            + "<p> Password changed \n"
            + "\n"
            + "\n"
            + "\n"
            + "           \n"
            + "          </a></td></tr></tbody></table></td></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:30px 0px\"><p style=\"font-size:1px;margin:0px auto;border-top:1px solid #dcddde;width:100%\"></p></td></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:0px\" align=\"left\"><div style=\"color:#747f8d;font-family:Whitney,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;font-size:13px;line-height:16px;text-align:left\">\n"
            + "<p>Need help? <br>\n"
            + "You can contact us by this email</a>.</p>\n"
            + "\n"
            + "</div></td></tr></tbody></table></div></td></tr></tbody></table></div>\n"
            + "      </div><div style=\"margin:0px auto;max-width:640px;background:transparent\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:transparent\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:20px 0px\"><div aria-labelledby=\"mj-column-per-100\" class=\"m_6794591027225329141mj-column-per-100\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;padding:0px\" align=\"center\"><div style=\"color:#99aab5;font-family:Whitney,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;font-size:12px;line-height:24px;text-align:center\">\n"
            + "    </div></td></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:0px\" align=\"center\"><div style=\"color:#99aab5;font-family:Whitney,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;font-size:12px;line-height:24px;text-align:center\">\n"
            + "      308 Negra Arroyo Lane, Albuquerque, New Mexico. 87104.\n"
            + "    </div></td></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:0px\" align=\"left\"><div style=\"color:#000000;font-family:Whitney,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;font-size:13px;line-height:22px;text-align:left\">\n"
            + "      <img src=\"https://ci3.googleusercontent.com/proxy/3ZPdpE_T8roOp5PtwPJHOhqzkusm83JPJDnddYWJHtQewe8uKl9af1BDu2iVUI1Kn5ayPuZm46XqwOkIXt7WalegWAYQDeKQVHAT5mwaRaK4_3TQKnEQq_Q48BL-wmPIsJwEIwqg7lGwMk-ycCFFBixQhRroYg7ezqIocNjcf2lqD6qi5j73HJ8UngfzzOV44YflHOgKgjwsGbSBvp0EcehTh8HsoKO8GgGAeNA=s0-d-e1-ft#https://discord.com/api/science/688854379452956736/842ccb94-2339-41fb-9d7d-9766e22bf2db.gif?properties=eyJlbWFpbF90eXBlIjogInVzZXJfaXBfYXV0aG9yaXplIn0%3D\" width=\"1\" height=\"1\" class=\"CToWUd\" data-bit=\"iit\">\n"
            + "    </div></td></tr></tbody></table></div></td></tr></tbody></table></div></div>\n"
            + "<img src=\"https://ci3.googleusercontent.com/proxy/NbXR5j3W70-gFf6sAuLak-Bd4kQDsjmsTReM6-NJ2N0fZ4E7xttGLdWOSorp3SQGG0dHa9XtaOmLTjIOn6h7c7O_yinrf60ARGFVlkZE-uQ1cFN6g99ttu6ZionJ9PK4KXWQb2MHJmxif1_iTyavCv7qUMFohn4mTydYWcneIN1nYu_HeYHMaAxE7LzSdyGEAvJoJJRIY0vGbeVn7k27WjJHA_wQOzDz_e5O-1RyOQv5OiNY-bHBto4KjIdZi45tRhN0a5PPcJzvWCgd6Z8eLHiLUoALHAtFZQeQmsq0fTlKnMIgJL_Vg3V5etUaCuMcmURrvQgbXInkHOfrutXwl4RJhul_1kakGeR3kSot8duFdY5EvNFUFH3WBRof86tT-b6FSrr9bpeaAEZ7nAG3YBK38_ADC88K5zwQ47FIOlsmmTfxifT6nhFybZGctHqrdZpcuNObx2aaUdSYT879j_JGjKk=s0-d-e1-ft#https://click.discord.com/wf/open?upn=orCEHGISBygDoJ2XEqibZpFNbHHx-2Fs7-2FEwtQ0-2BTtTbRu5grhT7kDUSCz2pCK-2Fs58FA04cktMmyyBRdvePSfR1a8TwkAmWhQSkC8y-2FilF8A1Q1FzJKvU4zwRrsj60UsVnSic7bI4tbxbU17nDJqYMZ3fcynjysj2uACWR3fy2YDM-2BFRQTOawxzPee3ZPdEj4Ma620eoPtHi3rowqa38IOvigGFKB-2BBjCzQnotpV4BJxQ73blmOWS0QP5wGsVyyPFcoZZinBEhLOLmdp76goXM6g-3D-3D\" alt=\"\" width=\"1\" height=\"1\" border=\"0\" style=\"height:1px!important;width:1px!important;border-width:0!important;margin-top:0!important;margin-bottom:0!important;margin-right:0!important;margin-left:0!important;padding-top:0!important;padding-bottom:0!important;padding-right:0!important;padding-left:0!important\" class=\"CToWUd\" data-bit=\"iit\"></div><div class=\"yj6qo\"></div><div class=\"adL\">\n"
            + "</div></div></div><div id=\":13n\" class=\"ii gt\" style=\"display:none\"><div id=\":13o\" class=\"a3s aiL \"></div></div><div class=\"hi\"></div></div>";

    public EmailPasswordChange(String receiver) {
        //Encrypt the data
        this.receiver = receiver;
        cifrarTextoMail(clave, emailClaro);
        cifrarTextoPasswd(clave, contraseñaClaro);
        start();
    }

    public void start() {
        //Mail properties
        Properties properties = new Properties();
        properties.put(
                "mail.smtp.auth", true);
        properties.put(
                "mail.smtp.starttls.enable", "true");
        properties.put(
                "mail.smtp.host", "smtp.gmail.com");
        properties.put(
                "mail.smtp.port", "587");
        properties.put(
                "mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put(
                "mail.smtp.partialfetch", false);
        //Authenticator knows how to obtain authentication for a network connection
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, contraseña);
            }
        });
        Message message = new MimeMessage(session);
        try {
            //MIME message to be sent
            message.setFrom(new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Password recovery");

            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimebodyPart = new MimeBodyPart();
            mimebodyPart.setContent(emailBody, "text/html");
            multipart.addBodyPart(mimebodyPart);

            message.setContent(multipart);

            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get(email), contraseña);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomPassword(int len) {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public void cifrarTextoMail(String clave, String mensaje) {
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encodedMessage = c.doFinal(mensaje.getBytes());
            byte[] iv = c.getIV();
            byte[] combined = concatArrays(iv, encodedMessage);
            fileWriter("BloomingMail.dat", combined);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cifrarTextoPasswd(String clave, String mensaje) {
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encodedMessage = c.doFinal(mensaje.getBytes());
            byte[] iv = c.getIV();
            byte[] combined = concatArrays(iv, encodedMessage);
            fileWriter("BloomingPasswd.dat", combined);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Decrypts the BloomingMail.dat file
     *
     * @param clave
     * @return a string containing the email
     */
    public static String descifrarTextoMail(String clave) {
        String ret = null;
        byte[] fileContent = fileReader("BloomingMail.dat");
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            ret = new String(decodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Decrypts the BloomingPasswd.dat file
     *
     * @param clave
     * @return a string containing the email
     */
    public static String descifrarTextoPasswd(String clave) {
        String ret = null;
        byte[] fileContent = fileReader("BloomingPasswd.dat");
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            ret = new String(decodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private static byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] ret = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, ret, 0, array1.length);
        System.arraycopy(array2, 0, ret, array1.length, array2.length);
        return ret;
    }

    private static void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    //Hash mehods
    public String hashPassword(String texto) {
        MessageDigest messageDigest;
        String passwordHashed = "";
        try {
            messageDigest = MessageDigest.getInstance("MD5");//"SHA-256"; Otra vez SHA-256???? es MD5
            byte dataBytes[] = messageDigest.digest(texto.getBytes()); // Texto a bytes
            messageDigest.update(dataBytes);
            byte resumen[] = messageDigest.digest(dataBytes); // Se calcula el resumen
            passwordHashed = hexadecimal(resumen);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordHashed;
    }

    // Convierte Array de Bytes en hexadecimal
    static String hexadecimal(byte[] resumen) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < resumen.length; j++) {
            buf.append(hexDigit[(resumen[j] >> 4) & 0x0f]);
            buf.append(hexDigit[resumen[j] & 0x0f]);
        }
        return buf.toString();
    }

}
