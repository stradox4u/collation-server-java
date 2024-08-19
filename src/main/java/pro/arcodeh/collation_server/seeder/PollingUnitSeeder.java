package pro.arcodeh.collation_server.seeder;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;
import pro.arcodeh.collation_server.model.Lga;
import pro.arcodeh.collation_server.model.PollingUnit;
import pro.arcodeh.collation_server.model.State;
import pro.arcodeh.collation_server.model.Ward;
import pro.arcodeh.collation_server.repository.LgaRepository;
import pro.arcodeh.collation_server.repository.PollingUnitRepository;
import pro.arcodeh.collation_server.repository.StateRepository;
import pro.arcodeh.collation_server.repository.WardRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PollingUnitSeeder implements SeederInterface{
    private final PollingUnitRepository pollingUnits;
    private final WardRepository wards;
    private final LgaRepository lgas;
    private final StateRepository states;
    private final ObjectMapper objectMapper;
    private JsonNode lgaData;

    public PollingUnitSeeder(PollingUnitRepository pollingUnits, WardRepository wards, LgaRepository lgas, StateRepository states, ObjectMapper objectMapper) {
        this.pollingUnits = pollingUnits;
        this.wards = wards;
        this.lgas = lgas;
        this.states = states;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean shouldSeed() {
        return  this.states.count() == 0;
    }

    @Override
    public void run() {
        JsonNode statesData = this.readStates();
        this.readLgas();
        JsonNode allWards = this.readWards();
        JsonNode allPus = this.readPollingUnits();

        for(JsonNode state : statesData) {
            String stateName = this.cleanQuotedString(state.get("name").asText());
            int stateId = state.get("id").asInt();
            State newState = new State(stateId, stateName, true);
            JsonNode lgas = this.getLgaData(stateName).get("lgas");
            JsonNode statePus = allPus.get(String.valueOf(stateId));

            for(JsonNode lga : lgas) {
                String lgaName = lga.get("name").asText();
                Integer lgaId = lga.get("id").asInt();
                String abbr = lga.get("abbreviation").asText();
                JsonNode lgaPus = statePus.get(abbr);

                Lga newLga = new Lga(lgaId, lgaName, abbr, true);
                newState.addLga(newLga);

                List<JsonNode> relevantWards = new ArrayList<>();
                for(JsonNode ward : allWards) {
                    if(ward.get("local_government_id").asInt() == lgaId) {
                        relevantWards.add(ward);
                    }
                }

                for(JsonNode relevantWard: relevantWards) {
                    Integer wardId = relevantWard.get("id").asInt();
                    String wardName = relevantWard.get("name").asText();
                    String wardAbbr = relevantWard.get("abbreviation").asText();
                    JsonNode wardPus = lgaPus.get(String.valueOf(wardId)).get("pollingUnits");

                    Ward newWard = new Ward(wardId, wardName, wardAbbr, true);
                    newLga.addWard(newWard);
                    for(JsonNode pu : wardPus) {
                        String puName = pu.get("name").asText();
                        Integer puId = pu.get("id").asInt();
                        String puNumber = pu.get("abbreviation").asText();
                        String delimitation = pu.get("delimitation").asText();

                        PollingUnit newPu = new PollingUnit(puId, delimitation, puNumber, puName, true);
                        newWard.addPollingUnit(newPu);
                    }
                }
            }
            this.states.save(newState);
        }
    }

    private String cleanQuotedString(String input) {
        return input.replaceAll("\"", "");
    }

    private JsonNode readStates() {
        JsonNode json;
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/states.json")) {
            json = objectMapper.readValue(inputStream, JsonNode.class);
        } catch(IOException ex) {
            throw new RuntimeException("Failed to read JSON data", ex);
        }

        return this.getStateData(json);
    }

    private void readLgas() {
        JsonNode json;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/statesAndLgas.json")) {
            json = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read JSON data", ex);
        }
        this.lgaData = json;
    }

    public JsonNode readWards() {
        JsonNode json;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/allWards.json")) {
            json = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read JSON data", ex);
        }
        return this.getWardsData(json);
    }

    public JsonNode readPollingUnits() {
        JsonNode json;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/cleanedPUs.json")) {
            json = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read JSON data", ex);
        }
        return json;
    }

    private JsonNode getStateData(JsonNode json) {
        return Optional.ofNullable(json)
                .map(j -> j.get("data"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }

    private JsonNode getLgaData(String stateName) {
        return Optional.ofNullable(this.lgaData)
                .map(j -> j.get(stateName))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }

    private JsonNode getWardsData(JsonNode json) {
        return Optional.ofNullable(json)
                .map(j -> j.get("allWards"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }
}
