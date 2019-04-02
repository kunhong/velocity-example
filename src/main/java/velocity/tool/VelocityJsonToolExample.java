package velocity.tool;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.JsonTool;
import util.JsonHelper;

import java.io.StringWriter;

public class VelocityJsonToolExample {
    public static void main(String[] args) {
        final String json = "{\"id\":12123434,\"name\":\"Joe Rightsman\",\"address\":[{\"street\":\"100 East Main Street\",\"city\":\"Southernville\",\"state\":\"NY\",\"zip\":\"19910\"},{\"street\":\"100 West Birch Lane\",\"city\":\"Northnville\",\"state\":\"NY\",\"zip\":\"19911\"}]}";
        JsonTool jsonTool = new JsonTool().parse(json);
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        /*  next, get the Template  */
        Template t = ve.getTemplate( "./src/main/resources/VelocityJsonToolTemplate.vm" );
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("json", jsonTool);
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */

        JsonHelper.printPretty(json);
        System.out.println( writer.toString() );
    }
}
