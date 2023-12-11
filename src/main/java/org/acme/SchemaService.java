package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SchemaService {

    public String getSchema() {
        // it will be dynamic (by getting the corresponding schema from database)
        return """
            {
              "$schema": "http://json-schema.org/draft-04/schema#",
              "type": "object",
              "properties": {
                "animal": {
                  "type": "string"
                },
                "class": {
                  "enum": [
                          "mammal",
                          "bird",
                          "reptile"
                        ]
                }
              },
              "required": [
                "class"
              ]
            }""";
    }
}
