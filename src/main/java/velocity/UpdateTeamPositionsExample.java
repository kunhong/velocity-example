package velocity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import dto.TeamPosition;
import enumeration.FormationChangeGroup;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static enumeration.FormationChangeGroup.CHANGE;
import static java.util.stream.Collectors.groupingBy;

public class UpdateTeamPositionsExample {
    public static void main(String[] args) throws JsonProcessingException, IOException {
        List<TeamPosition> teamPositionList = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            teamPositionList.add(
                    TeamPosition.builder().
                            id("id" + String.valueOf(i)).
                            _id("_id" + String.valueOf(i))
                            .pos(i)
                            .startings(Lists.newArrayList(((i + 1)* 10) + 0.00001, ((i+1) * 10) + 0.00002))
                            .build()
            );
        }

        System.out.println("*************************************");
        teamPositionList.stream()
                .forEach(System.out::println);
        System.out.println("*************************************");

        // Java Object to json
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(teamPositionList);

        System.out.println("#####################################");
        System.out.println(jsonString);
        System.out.println("#####################################");

        VelocityEngine ve = new VelocityEngine();
        ve.init();
        /*  next, get the Template  */
        Template t = ve.getTemplate( "/src/main/resources/template/TeamPosition.vm" );

        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        //context.put("formationName", "formation_3_4_2");
        context.put("formationName", "formation_4_4_1");

        Map<FormationChangeGroup, List<TeamPosition> > positionMap = teamPositionList.stream()
                .collect(groupingBy(teamPosition -> FormationChangeGroup.getChangeGroup(teamPosition.getPos())));

        context.put("currentTeamPositions", positionMap.get(FormationChangeGroup.CHANGE));

        StringWriter writer = new StringWriter();
        t.merge( context, writer );

        System.out.println(">>>>>>>>>>>>>>>>> Show Template result");
        System.out.println( writer.toString() );


        // JSON to Java Object
        List<TeamPosition> jsonToList
                = objectMapper.readValue(writer.toString(), new TypeReference<List<TeamPosition>>(){});

        jsonToList.addAll(positionMap.get(FormationChangeGroup.NOT_CHANGE));

        jsonToList.stream()
                .forEach(System.out::println);
    }
}
