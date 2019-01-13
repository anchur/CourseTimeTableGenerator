
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author project1
 */
public class FileUploadBean {
    public void doUpload(HttpServletRequest request) throws
IOException {
PrintWriter pw = new PrintWriter(
new BufferedWriter(new FileWriter("FileUploaded.csv")));
ServletInputStream in = request.getInputStream();
int i = in.read();
while (i != -1) {
pw.print((char) i);
i = in.read();
}
pw.close();
}
    
}
